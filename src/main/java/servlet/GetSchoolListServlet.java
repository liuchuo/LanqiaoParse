package servlet;

import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetSchoolListServlet extends HttpServlet {
	private static final long serialVersionUID = -3565728007994021470L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * 获取文件中的所有学校的名字，存储在一个变量名为schools的SortedSet<String>中
	 */
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
}
