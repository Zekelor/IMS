package com.frame.departmgr;

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
import com.frame.role.RoleService;
import com.google.gson.Gson;
import com.yhl.f22.frame.utils.StringUtils;

public class DepartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RoleService roleService = new RoleService();
	private DepartDAO dao = new DepartDAO();

	public DepartServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String req = request.getServletPath();
		System.out.println(req);
		String username = StringUtils.toString(request.getSession().getAttribute("username"));
		String role = roleService.queryRoleType(username);
		if ("/department/toDepart.do".equals(req)) {
			request.setAttribute("role", role);
			request.getRequestDispatcher("/frame/departmgr/depart_list.jsp").forward(request, response);
		} else if ("/department/queryDepart".equals(req)) {
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
			String[] columnsName = { "qd.qmc_depart_name", "qd.qmc_depart_remark", "qd.qmc_creater", "qd.qmc_create_time" };

			String sortDir = request.getParameter("order[0][dir]");
			System.out.println("sortOrder: " + sortOrder);
			System.out.println("sortDir: " + sortDir);

			// For search
			String searchValue = new String(request.getParameter("search[value]").getBytes("iso-8859-1"), "utf-8");
			int count = 0;
			List<DepartBean> results = new ArrayList<DepartBean>();
			count = dao.count(searchValue);

			results = dao.queryDataList(pageSize, startRecord, columnsName[Integer.parseInt(sortOrder)], sortDir, searchValue);
			DataVO<DepartBean> result = new DataVO<DepartBean>();
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

		} else if ("/department/toAdd.do".equals(req)) {

			request.getRequestDispatcher("/frame/departmgr/depart_add.jsp").forward(request, response);
		} else if ("/department/addDepart".equals(req)) {
			String departName = request.getParameter("departName");
			String departRemark = request.getParameter("departRemark");

			Boolean isExist = dao.queryDepartExist(departName);
			if (isExist) {
				DepartBean bean = new DepartBean();
				bean.setQmcDName(departName);
				bean.setQmcDRemark(departRemark);
				bean.setQmcCreater(username);
				bean.setQmcCreateTime(String.valueOf((System.currentTimeMillis() / 1000)));
				String flag = dao.save(bean);
				if (flag.equals("1")) {
					response.getWriter().print("1");

				} else {
					response.getWriter().print("0");
				}

			} else {
				response.getWriter().print("3");// 表示数据库中已存在这条记录

			}
		} else if ("/department/toEdit.do".equals(req)) {
			String id = request.getParameter("qmcDId");
			List<DepartBean> list = dao.queryDataById(id);

			request.setAttribute("list", list);
			request.setAttribute("qmcDId", id);
			request.getRequestDispatcher("/frame/departmgr/depart_edit.jsp").forward(request, response);
		} else if ("/department/editDepart".equals(req)) {
			String id = request.getParameter("qmcDId");
			String departName = request.getParameter("departName");
			String departRemark = request.getParameter("departRemark");

			Boolean isExist = dao.queryDepartExist(id, departName);
			if (isExist) {
				DepartBean bean = new DepartBean();
				bean.setQmcDId(Integer.parseInt(id));
				bean.setQmcDName(departName);
				bean.setQmcDRemark(departRemark);
				String flag = dao.update(bean);
				if (flag.equals("1")) {
					response.getWriter().print("1");

				} else {
					response.getWriter().print("0");
				}

			} else {
				response.getWriter().print("3");// 表示数据库中已存在这条记录

			}
		} else if ("/department/queryAddDepart".equals(req)) {
			List<DepartBean> list = dao.queryDepart();
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			Map<String, Object> dataMap = new HashMap<String, Object>();
			for (DepartBean bean : list) {
				dataMap = new HashMap<String, Object>();
				dataMap.put("id", bean.getQmcDId());
				dataMap.put("text", bean.getQmcDName());
				dataList.add(dataMap);
			}
			Gson gson = new Gson();
			String output = gson.toJson(dataList);
			System.out.println("Output JSON: \n" + output);
			PrintWriter out = response.getWriter();
			out.write(output);
			out.flush();
			out.close();
		}

	}

	public void init() throws ServletException {

	}

}
