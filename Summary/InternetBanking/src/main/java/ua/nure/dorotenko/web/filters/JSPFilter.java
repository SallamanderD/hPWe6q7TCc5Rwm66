package ua.nure.dorotenko.web.filters;

import org.apache.log4j.Logger;
import ua.nure.dorotenko.utils.Path;
import ua.nure.dorotenko.utils.Util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSPFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(JSPFilter.class);
    private List<String> forbiddenForLogged = new ArrayList<>();
    private List<String> forbidden = new ArrayList<>();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.debug("Filter init");
        forbiddenForLogged = Util.asList(filterConfig.getInitParameter("forbiddenForLogged"));
        forbidden = Util.asList(filterConfig.getInitParameter("forbidden"));
        LOG.debug("Filter initing finished");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOG.debug("Filter starts.");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        LOG.trace("URI -> " + request.getRequestURI());
        if(session.getAttribute("userRole") != null && session.getAttribute("userRole") != "administrator"){
            if(forbiddenForLogged.contains(request.getRequestURI())){
                request.getRequestDispatcher(Path.HOMEPAGE_COMMAND).forward(servletRequest, servletResponse);
            }
        } else{
            if(forbidden.contains(request.getRequestURI())){
                request.getRequestDispatcher(Path.LOGIN_PAGE).forward(servletRequest, servletResponse);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
        LOG.debug("Filter finished.");
    }

    @Override
    public void destroy() {
        LOG.debug("JSPFilter destroy");
    }
}
