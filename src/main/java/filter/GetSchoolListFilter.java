package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "schoolList-filter", urlPatterns = {"/index.jsp"})
public class GetSchoolListFilter implements Filter{

    public void destroy() {
        // TODO Auto-generated method stub
    }

    // 如果session里面没有schools，则跳转到/getSchoolList，否则执行过滤链
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
            throws IOException, ServletException {


    }

    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
