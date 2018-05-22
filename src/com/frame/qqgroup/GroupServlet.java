package com.frame.qqgroup;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.frame.common.CommonConst;
import com.frame.common.DataVO;
import com.frame.role.RoleService;
import com.google.gson.Gson;
import com.yhl.f22.frame.utils.StringUtils;

public class GroupServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	GroupDAO dao = new GroupDAO();
	GroupDetailDAO detailDao = new GroupDetailDAO();
	private RoleService roleService = new RoleService();

	public GroupServlet() {
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
		String username = StringUtils.toString(request.getSession().getAttribute("username"));

		String role = roleService.queryRoleType(username);
		
		System.out.println(req);
		if (req.equals("/servlet/toGroupList.do")) {
			request.getRequestDispatcher("/frame/qqgroup/group_list.jsp").forward(request, response);

		} else if (req.equals("/servlet/queryGroup")) {
			String depart = roleService.queryDepartment(username);
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
			String[] columnsName = { "A.qmc_gid", "A.qmc_group", "A.qmc_group_name", "A.tn", "A.daycount", "A.weekcount","A.qmc_qq" };

			// String sortOrder ="A.daycount";
			String sortDir = request.getParameter("order[0][dir]");
			System.out.println("sortOrder: " + sortOrder);
			System.out.println("sortDir: " + sortDir);

			// For search
			String searchValue = new String(request.getParameter("search[value]").getBytes("iso-8859-1"), "utf-8");
			int count = 0;
			List<GroupBean> results = new ArrayList<GroupBean>();
			String departId = "";

			if (CommonConst.ROLE_ADMIN.equals(role)) {
				departId = "";
			} else {
				if (StringUtils.isEmpty(depart)) {
					departId = CommonConst.ROLE_NOAUTH;
				} else {
					departId = depart;
				}
			}
			count = dao.count(departId, pageSize, startRecord, searchValue);

			results = dao.queryDataList(departId, pageSize, startRecord, columnsName[Integer.parseInt(sortOrder)], sortDir,
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
		} else if (req.equals("/servlet/toDetail.do")) {
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
			request.getRequestDispatcher("/frame/qqgroup/groupDetail_list.jsp").forward(request, response);

		} else if (req.equals("/servlet/queryGroupDetail")) {
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
			count = detailDao.count(group, pageSize, startRecord, searchValue);

			results = detailDao.queryDetailList(group, pageSize, startRecord, columnsName[Integer.parseInt(sortOrder)], sortDir,
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
		} else if (req.equals("/servlet/queryGroupByTag")) {
			String tagId = request.getParameter("tagId");
			String depart = roleService.queryDepartment(username);
			System.out.println("tagId:" + tagId);
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
			String[] columnsName = { "A.qmc_gid", "A.qmc_group", "A.qmc_group_name", "A.tn", "A.daycount", "A.weekcount",
					"A.qmc_qq" };

			// String sortOrder ="A.daycount";
			String sortDir = request.getParameter("order[0][dir]");
			System.out.println("sortOrder: " + sortOrder);
			System.out.println("sortDir: " + sortDir);

			// For search
			String searchValue = new String(request.getParameter("search[value]").getBytes("iso-8859-1"), "utf-8");
			int count = 0;
			List<GroupBean> results = new ArrayList<GroupBean>();
			String departId = "";

			if (CommonConst.ROLE_ADMIN.equals(role)) {
				departId = "";
			} else {
				if (StringUtils.isEmpty(depart)) {
					departId = CommonConst.ROLE_NOAUTH;
				} else {
					departId = depart;
				}
			}
			count = dao.countByTag(departId,tagId, pageSize, startRecord, searchValue);

			results = dao.queyrDataByTag(departId,tagId, pageSize, startRecord, columnsName[Integer.parseInt(sortOrder)], sortDir,searchValue);
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

		}

	}

	public List<GroupBean> queryDataList() {
		return null;

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
