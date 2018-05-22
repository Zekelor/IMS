package com.frame.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public FileDownloadServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
		
		String fileName =new String(request.getParameter("fileName").getBytes("ISO8859-1"),"UTF-8");
		String dirName = "/frame";
		String sep = System.getProperty("file.separator");
		String path = request.getSession(true).getServletContext().getRealPath("/" + dirName) + sep + fileName;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		OutputStream os = null;
		InputStream is = null;
		try {
			File downloadFile = new File(path);
			if (downloadFile.exists()) {
				is = new FileInputStream(downloadFile);
				bis = new BufferedInputStream(is);
				os = response.getOutputStream();
				bos = new BufferedOutputStream(os);
				//response.setContentType("text/comma-separated-values");
				response.setContentType(getServletContext().getMimeType(fileName));
				response.setHeader("Content-Disposition","attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
				response.setContentLength((int) downloadFile.length());
				int bytesRead = 0;
				byte[] buffer = new byte[4096];
				while ((bytesRead = bis.read(buffer, 0, 4096)) != -1) {
					bos.write(buffer, 0, bytesRead);
				}
				bos.flush();//清空缓冲区
				is.close();
				bis.close();
				os.close();
				bos.close();
			} else {
				PrintWriter out = response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert(\"No data\");");
				out.println("</script>");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		//request.getRequestDispatcher("/frame/reportmgr/report_list.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
	public void init() throws ServletException {
		// Put your code here
	}

}
