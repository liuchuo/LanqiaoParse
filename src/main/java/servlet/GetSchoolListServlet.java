package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@WebServlet(name = "schoolList-servlet", urlPatterns = { "/getSchoolList" })
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

        SortedSet<String> schools = new TreeSet<String>();
        String directory = request.getServletContext().getRealPath("/WEB-INF");
        try {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(new File(directory, "2016年软件类-江苏赛区获奖名单.xlsx")));
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);

            Iterator<Row> rows = xssfSheet.rowIterator();
            rows.next();
            rows.next();
            rows.next();

            while (rows.hasNext()) {
                XSSFRow row = (XSSFRow) rows.next();
                XSSFCell cell = row.getCell(1);

                String value = cell.getStringCellValue();
                if (!schools.contains(value) && !value.equals("")) {
                    schools.add(cell.getStringCellValue());
                }

            }
            xssfWorkbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        session.setAttribute("schools", schools);
        response.sendRedirect("index.jsp");
    }
}
