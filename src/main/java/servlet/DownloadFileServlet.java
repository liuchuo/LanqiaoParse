package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

/**
 * Created by JoyHwong on 9/23/16.
 * copyright @ 2016 All right reserved.
 * follow me on github https://github.com/JoyHwong
 */
@WebServlet(urlPatterns = {"/download"})
public class DownloadFileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String filename = (String) session.getAttribute("filename");
		File file = new File(request.getServletContext().getRealPath("/WEB-INF"), filename);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);

        byte[] buffer = new byte[1024];
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            OutputStream outputStream = response.getOutputStream();
            int i = bufferedInputStream.read(buffer);
            while (i != -1) {
                outputStream.write(buffer, 0, i);
                i = bufferedInputStream.read(buffer);
            }
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            System.out.println(e.toString());
        } finally {
            try {
                if (bufferedInputStream != null) bufferedInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (fileInputStream != null) fileInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
