package com.frame.monitormgr;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.frame.common.DataVO;
import com.frame.departmgr.DepartBean;
import com.frame.qqgroup.GroupBean;
import com.google.gson.Gson;
import com.yhl.f22.frame.utils.StringUtils;

public class MonitorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private MonitorDAO dao = new MonitorDAO();

	public MonitorServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String req = request.getServletPath();
		String username = StringUtils.toString(request.getSession().getAttribute("username"));
		String uid = dao.queryUserId(username);
		System.out.println(req);
		if ("/monitor/toMonitor.do".equals(req)) {
			request.getRequestDispatcher("/frame/monitormgr/monitor_list.jsp").forward(request, response);
		} else if ("/monitor/queryMonitor".equals(req)) {
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
			String[] columnsName = { "qq.qmc_qqid", "qq.qmc_qq", "groupcount", "daycount", "weekcount" };

			String sortDir = request.getParameter("order[0][dir]");
			System.out.println("sortOrder: " + sortOrder);
			System.out.println("sortDir: " + sortDir);

			// For search
			String searchValue = new String(request.getParameter("search[value]").getBytes("iso-8859-1"), "utf-8");
			int count = 0;
			List<MonitorBean> results = new ArrayList<MonitorBean>();
			count = dao.count(searchValue);

			results = dao.queryDataList(pageSize, startRecord, columnsName[Integer.parseInt(sortOrder)], sortDir, searchValue);
			DataVO<MonitorBean> result = new DataVO<MonitorBean>();
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

		} else if ("/monitor/toGroup.do".equals(req)) {
			String qmcQQ = request.getParameter("qmcQQ");

			request.setAttribute("qmcQQ", qmcQQ);
			request.getRequestDispatcher("/frame/monitormgr/group_list.jsp").forward(request, response);
		} else if ("/monitor/queryGroup".equals(req)) {
			String qmcQQ = request.getParameter("qmcQQ");
			// For Page
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
			String[] columnsName = { "A.qmc_gid", "A.qmc_group", "A.qmc_group_name", "A.tn", "A.daycount", "A.weekcount",
					"A.qmc_qq" };
			String sortDir = request.getParameter("order[0][dir]");
			System.out.println("sortOrder: " + sortOrder);
			System.out.println("sortDir: " + sortDir);

			// For search
			String searchValue = new String(request.getParameter("search[value]").getBytes("iso-8859-1"), "utf-8");
			int count = 0;
			List<GroupBean> results = new ArrayList<GroupBean>();
			count = dao.queryGroupCount(searchValue, qmcQQ);

			results = dao.queryGroupList(pageSize, startRecord, columnsName[Integer.parseInt(sortOrder)], sortDir, searchValue,
					qmcQQ);
			DataVO<GroupBean> result = new DataVO<GroupBean>();
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
		} else if ("/monitor/toDetail.do".equals(req)) {
			String group = request.getParameter("qmcGroup");
			String groupName = new String(request.getParameter("qmcGroupName").getBytes("ISO8859-1"), "UTF-8");
			String account = request.getParameter("qmcQQ");
			String tag = new String(request.getParameter("qmcTag").getBytes("ISO8859-1"), "UTF-8");
			String isDetail = request.getParameter("isDetail");
			System.out.println("list-groupname:" + group);
			request.setAttribute("isDetail", isDetail);
			request.setAttribute("qmcGroup", group);
			request.setAttribute("qmcGroupName", groupName);
			request.setAttribute("qmcTag", tag);
			request.setAttribute("qmcQQ", account);
			request.getRequestDispatcher("/frame/monitormgr/groupDetail_list.jsp").forward(request, response);

		} else if ("/monitor/queryGroupDetail".equals(req)) {
			String group = request.getParameter("groupName");
			System.out.println("group:" + group);

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
			// String sortOrder = "qmc_msg_date";
			String[] columnsName = { "m.qmc_sender", "m.qmc_sender_nick", "m.qmc_msg", "m.qmc_msg_date" };
			String sortDir = request.getParameter("order[0][dir]");
			System.out.println("sortOrder: " + sortOrder);
			System.out.println("sortDir: " + sortDir);

			// For search
			// String searchValue = request.getParameter("search[value]");
			String searchValue = new String(request.getParameter("search[value]").getBytes("iso-8859-1"), "utf-8");
			int count = 0;
			List<GroupBean> results = new ArrayList<GroupBean>();
			count = dao.detailCount(group, pageSize, startRecord, searchValue);

			results = dao.queryDetailList(group, pageSize, startRecord, columnsName[Integer.parseInt(sortOrder)], sortDir,
					searchValue);
			DataVO<GroupBean> result = new DataVO<GroupBean>();
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
		} else if ("/monitor/queryDepart".equals(req)) {
			List<DepartBean> list = dao.queryDepart();
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			Map<String, Object> dataMap = new HashMap<String, Object>();
			for (DepartBean depart : list) {
				dataMap = new HashMap<String, Object>();
				dataMap.put("id", depart.getQmcDId());
				dataMap.put("text", depart.getQmcDName());
				dataList.add(dataMap);
			}

			Gson gson = new Gson();
			String output = gson.toJson(dataList);
			System.out.println("Output JSON: \n" + output);
			PrintWriter out = response.getWriter();
			out.write(output);
			out.flush();
			out.close();

		} else if ("/monitor/monitorDepart".equals(req)) {
			String qids = request.getParameter("qmcQId");
			qids = qids.substring(qids.indexOf(",") + 1);
			String dids = request.getParameter("qmcDId");
			String flag = "";
			System.out.println(qids + dids);
			if (!"null".equals(dids)) {
				flag = dao.monitorDepart(dids, qids, uid);
			} else {
				Boolean delFlag = dao.deleteMDepart(qids);
				if (delFlag) {
					flag = "1";
				} else {
					flag = "0";
				}
			}
			if (flag.equals("1")) {
				response.getWriter().print("1");

			} else {
				response.getWriter().print("0");
			}

		}

	}

	public void init() throws ServletException {
		// Put your code here
	}

}
