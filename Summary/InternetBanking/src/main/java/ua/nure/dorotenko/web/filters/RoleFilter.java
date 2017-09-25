package ua.nure.dorotenko.web.filters;

import org.apache.log4j.Logger;
import ua.nure.dorotenko.utils.Path;
import ua.nure.dorotenko.utils.Util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class RoleFilter implements Filter {
    private Map<String, List<String>> accessMap = new HashMap<>();
    private List<String> commons = new ArrayList<>();
    private List<String> unauthorized = new ArrayList<>();
    private static final Logger LOG = Logger.getLogger(RoleFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.debug("Filter initialization starts");
        accessMap.put("administrator", Util.asList(filterConfig.getInitParameter("administrator")));
        accessMap.put("user", Util.asList(filterConfig.getInitParameter("user")));
        LOG.trace("Access map --> " + accessMap);
        commons = Util.asList(filterConfig.getInitParameter("common"));
        LOG.trace("Common commands --> " + commons);
        unauthorized = Util.asList(filterConfig.getInitParameter("unauthorized"));
        LOG.trace("Unauthorized commands --> " + unauthorized);
        LOG.debug("Filter initialization finished");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOG.debug("Filter starts");
        if (accessAllowed(servletRequest)) {
            LOG.debug("Filter finished");
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String errMsg = "You do not have permission to access the requested resource.";
            servletRequest.setAttribute("error", errMsg);
            LOG.trace("Set the request attribute: errorMessage --> " + errMsg);
            servletRequest.getRequestDispatcher(Path.ERROR_PAGE)
                    .forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }

    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String commandName = request.getParameter("command");
        if (commandName == null || commandName.isEmpty()) {
            return false;
        }
        HttpSession session = httpRequest.getSession();
        if (session.getAttribute("userRole") == null) {
            if (unauthorized.contains(commandName)) {
                return true;
            }
        }
        String userRole = (String) session.getAttribute("userRole");
        if (userRole == null) {
            return false;
        }

        return accessMap.get(userRole).contains(commandName)
                || commons.contains(commandName);
    }
}
