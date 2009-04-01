package tudu.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;

/**
 * Monitor HTTP requests with JAMon.
 * 
 * @author Julien Dubois
 */
public class JAMonFilter extends HttpServlet implements Filter {

    private static final long serialVersionUID = 2100494092174538871L;

    /**
     * @see javax.servlet.Servlet#destroy()
     */
    @Override
    public void destroy() {
    }

    /**
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain filterChain) throws IOException, ServletException {

        Monitor monitor = MonitorFactory.start(getURI(request));

        try {
            filterChain.doFilter(request, response);
        } finally {
            monitor.stop();
        }
    }

    /**
     * Get the requested URI.
     * 
     * @param request
     *            The HTTP request
     * @return The requested URI
     */
    protected static String getURI(ServletRequest request) {
        if (request instanceof HttpServletRequest) {
            return ((HttpServletRequest) request).getRequestURI();
        } else {
            return "Not an HttpServletRequest";
        }
    }

    /**
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}
