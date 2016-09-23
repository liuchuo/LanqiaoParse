package filter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "schoolList-filter", urlPatterns = {"/index.jsp"})
public class GetSchoolListFilter implements Filter{

	public void destroy() {

	}

	// 如果session里面没有schools，则跳转到/getSchoolList，否则执行过滤链
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();

		if (session.getAttribute("schools") == null) {
			response.sendRedirect("getSchoolList");            
		} else {
			filterChain.doFilter(request, response);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {

	}
}
