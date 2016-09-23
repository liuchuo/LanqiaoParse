package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "download-filter", urlPatterns = "/download")
public class DownloadFilter implements Filter {

    public void destroy() {
    }

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 *
	 * 判断request里面有没有名字为school的参数 如果没有参数，则跳转到/getSchoolList 如果有参数，则执行过链
	 */

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        
    	HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		if (request.getParameter("school") == null) {
			response.sendRedirect("getSchoolList");
		} else {
			filterChain.doFilter(request, response);
		}

    }

    public void init(FilterConfig arg0) throws ServletException {

    }

}
