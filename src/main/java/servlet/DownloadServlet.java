package servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "download-servlet", urlPatterns = {"/download"})
public class DownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 7591476022721449364L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * 从request里面提取出学校的名字 再源excel文件中找到这些学校进行分析 然后调用下载文件方法fileDownload(response,
	 * filename)下载文件 最后跳转到download.jsp页面
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	private void fileDown(HttpServletResponse response, String filename) {
		File file = new File(filename);
		if (file.exists()) {
			response.setContentType("application/excel");
			response.addHeader("Content-Disposition", "attachment; filename=" + filename);

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
			} catch (IOException e) {
				System.out.println(e.toString());
			} finally {
				try {
					bufferedInputStream.close();
				} catch (Exception e) {
				}
				try {
					fileInputStream.close();
				} catch (Exception e) {
				}
			}
		}
	}
}
