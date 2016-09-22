package servlet;

import java.io.IOException;
import java.util.SortedSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

<<<<<<< HEAD
@WebServlet(name = "schoolList-servlet", urlPatterns = {"/getSchoolList"})
=======
import com.joyhwong.AllSchool;

>>>>>>> 9050883477f650e332c73670e130d85343b21726
public class GetSchoolListServlet extends HttpServlet {
    private static final long serialVersionUID = -3565728007994021470L;

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * 获取文件中的所有学校的名字，存储在一个变量名为schools的SortedSet<String>中
	 */

<<<<<<< HEAD
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO

        HttpSession session = request.getSession();
        SortedSet<String> schools = new TreeSet<String>();
        schools.add("NanJing");
        schools.add("HangZhou");
        session.setAttribute("schools", schools);
        response.sendRedirect("index.jsp");
    }
=======
		HttpSession session = request.getSession();
		SortedSet<String> schools = new AllSchool().getAllSchools();
		
		session.setAttribute("schools", schools);
		response.sendRedirect("index.jsp");
	}
>>>>>>> 9050883477f650e332c73670e130d85343b21726
}
