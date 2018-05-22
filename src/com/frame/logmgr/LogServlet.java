package com.frame.logmgr;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.frame.common.DataVO;
import com.google.gson.Gson;

public class LogServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	LogDAO dao = new LogDAO();

	public LogServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String req = request.getServletPath();
		System.out.println(req);
		if (req.equals("/servlet/toLogList.do")) {

			request.getRequestDispatcher("/frame/logmgr/log_list.jsp").forward(request, response);

		} else if (req.equals("/servlet/queryLogList")) {
			// 分页
			int pageSize = 10;
			int startRecord = 0;
			String size = request.getParameter("length");
			if (!"".equals(size) && size != null) {
				pageSize = Integer.parseInt(size);
			}
			String currentRecord = request.getParameter("start");
			if (!"".equals(currentRecord) && currentRecord != null) {
				startRecord = Integer.parseInt(currentRecord);
			}
			// For sortable
			String sortOrder = request.getParameter("order[0][column]");
			String[] columnsName = { "qmc_no", "qmc_qq", "qmc_ip", "qmc_last_date" };
			// String sortOrder = "qmc_last_date";
			String sortDir = request.getParameter("order[0][dir]");
			System.out.println("sortOrder: " + sortOrder);
			System.out.println("sortDir: " + sortDir);

			// For search
			String searchValue = new String(request.getParameter("search[value]").getBytes("iso-8859-1"), "utf-8");
			int count = 0;
			List<LogBean> results = new ArrayList<LogBean>();
			count = dao.count(pageSize, startRecord, searchValue);

			results = dao.queryDataList(pageSize, startRecord, columnsName[Integer.parseInt(sortOrder)], sortDir, searchValue);
			DataVO<LogBean> result = new DataVO<LogBean>();
			result.setDraw(Integer.parseInt(request.getParameter("draw") == null ? "0" : request.getParameter("draw")) + 1);
			result.setData(results);
			result.setRecordsTotal(count);
			result.setRecordsFiltered(count);
			Gson gson = new Gson();
			String output = gson.toJson(result);
			System.out.println("Output JSON: \n" + output);
			PrintWriter out = response.getWriter();
			out.write(output);
			out.flush();
			out.close();

		} else if ("/servlet/queryLogTable".equals(req)) {
			// 分页
			int pageSize = 10;
			int startRecord = 0;
			String size = request.getParameter("length");
			if (!"".equals(size) && size != null) {
				pageSize = Integer.parseInt(size);
			}
			String currentRecord = request.getParameter("start");
			if (!"".equals(currentRecord) && currentRecord != null) {
				startRecord = Integer.parseInt(currentRecord);
			}
			// For sortable
			String sortOrder = request.getParameter("order[0][column]");
			String[] columnsName = { "qmc_login_name", "qmc_description", "qmc_login_ip", "qmc_last_date" };
			// String sortOrder = "qmc_last_date";
			String sortDir = request.getParameter("order[0][dir]");
			System.out.println("sortOrder: " + sortOrder);
			System.out.println("sortDir: " + sortDir);

			// For search
			String searchValue = new String(request.getParameter("search[value]").getBytes("iso-8859-1"), "utf-8");
			int count = 0;
			List<LogBean> results = new ArrayList<LogBean>();
			count = dao.count2(pageSize, startRecord, searchValue);

			results = dao.queryDataList2(pageSize, startRecord, columnsName[Integer.parseInt(sortOrder)], sortDir, searchValue);
			DataVO<LogBean> result = new DataVO<LogBean>();
			result.setDraw(Integer.parseInt(request.getParameter("draw") == null ? "0" : request.getParameter("draw")) + 1);
			result.setData(results);
			result.setRecordsTotal(count);
			result.setRecordsFiltered(count);
			Gson gson = new Gson();
			String output = gson.toJson(result);
			System.out.println("Output JSON: \n" + output);
			PrintWriter out = response.getWriter();
			out.write(output);
			out.flush();
			out.close();

		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
