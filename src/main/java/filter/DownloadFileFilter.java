package filter;

import org.springframework.http.HttpRequest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by JoyHwong on 9/23/16.
 * copyright @ 2016 All right reserved.
 * follow me on github https://github.com/JoyHwong
 */
@WebFilter(urlPatterns = {"/download.jsp", "/download"})
public class DownloadFileFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        if (session.getAttribute("filename") == null) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect("index.jsp");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    public void destroy() {

    }
}
