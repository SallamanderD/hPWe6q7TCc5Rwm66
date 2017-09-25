package ua.nure.dorotenko.web.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private static Logger LOG = Logger.getLogger(EncodingFilter.class);
    String encoding;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.debug("Filter init");
        encoding = filterConfig.getInitParameter("encoding");
        LOG.trace("Encoding from web.xml --> " + encoding);
        LOG.debug("Filter init finished");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOG.debug("Filter starts");
        HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
        LOG.trace("Request uri -> " + httpRequest.getRequestURI());

        String requestEncoding = servletRequest.getCharacterEncoding();
        if (requestEncoding == null) {
            LOG.trace("Request encoding = null, set encoding --> " + encoding);
            servletRequest.setCharacterEncoding(encoding);
        }

        LOG.debug("Filter finished");
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        LOG.debug("Filter destroyed");
    }
}
