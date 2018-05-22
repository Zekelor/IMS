package com.frame.usermgr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.SessionAttributes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.frame.role.RoleService;
import com.yhl.f22.frame.utils.StringUtils;

public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	UserDAO dao = new UserDAO();
	RoleService roleService =new RoleService();

	/**
	 * Constructor of the object.
	 */
	public UserServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request.setCharacterEncoding("UTF-8");
		// response.setContentType("text/html");
		String req = request.getServletPath();
		System.out.println(req);
		String username =StringUtils.toString(request.getSession().getAttribute("username"));
		String role=roleService.queryRoleType(username);
		
		//System.out.println(req);
		// 重定向至添加用户页面
		if (req.equals("/servlet/toAddUser.do")) {
			request.getRequestDispatcher("/frame/usermgr/user_add.jsp").forward(request, response);
			// 重定向至用户列表页面
		} else if (req.equals("/servlet/toUserList.do")) {
			
			request.setAttribute("role", role);
			request.getRequestDispatcher("/frame/usermgr/user_list.jsp").forward(request, response);
		} else if (req.equals("/servlet/toEdit.do")) {
			String id = request.getParameter("id");
			
			//Map<String, String> resMap = new HashMap<String, String>();
			List<UserBean> userList=queryDataById(id);
			
			List<UserBean> no_authList =queryDataByName(username);
			
			
			request.setAttribute("no_authList", no_authList);
			request.setAttribute("userList", userList);
			request.setAttribute("role", role);
			request.getRequestDispatcher("/frame/usermgr/user_edit.jsp").forward(request, response);
		} else if (req.equals("/servlet/queryAccount")){
			List<UserBean> list = queryAccount();
			response.setContentType("application/json;charset=utf-8");
			
			response.getWriter().print("{\"data\":" + JSONArray.toJSONString(list) + "}");
			System.out.println("{\"data\":" + JSONArray.toJSONString(list) + "}");
			
		} else if(req.equals("/servlet/UserDelServlet")){
			String id =request.getParameter("id"); 
			String flag =delete(id);
			if (flag.equals("1")) {
				response.getWriter().print("1");

			} else {
				response.getWriter().print("0");
			}
		} else if(req.equals("/servlet/queryUserNameExsit")){
			String userName =new String(request.getParameter("userName").getBytes("ISO8859-1"), "UTF-8");
			Boolean flag =dao.queryUserNameExist(userName);
			if(flag){
				response.getWriter().print("1");
			}else{
				
				response.getWriter().print("0");
			}
		}

	}
	
	/**
	 * 查询用户账号信息
	 * @return
	 */
	public List<UserBean> queryAccount() {

		List<UserBean> list = new ArrayList<UserBean>();

		String sql = "select qmc_uid,qmc_uname,qmc_pwd,qmc_no,qmc_tel,qmc_access_token,qmc_role,qmc_department from qmc_users  ";
		list = dao.query(sql);

		return list;

	}
	
	public List<UserBean> queryDataById(String id){
		new ArrayList<UserBean>();
		String sql ="select qmc_uname,qmc_pwd,qmc_no,qmc_tel,qmc_access_token,qmc_role,qmc_department from qmc_users where qmc_uid=?";
		return dao.queryDataById(sql,id);
		
	}
	
	public List<UserBean> queryDataByName(String username){
		new ArrayList<UserBean>();
		String sql ="select qmc_uname,qmc_pwd,qmc_no,qmc_tel,qmc_access_token,qmc_role,qmc_department from qmc_users where qmc_uname=?";
		return dao.queryDataByName(sql,username);
		
		
	}
	
	public String delete(String id){
		
		String sql ="delete from qmc_users where qmc_uid=?";
		String flag=dao.delete(sql, id);
		return flag;
	}
	
	
	/**
	 * 查询用户角色
	 * @param username
	 * @return
	 */
	/*public String queryRoleType(String username){
		
		String sql ="select qmc_role from qmc_users where qmc_uname=?";
		
		return dao.queryRoleType(sql, username);
	}*/

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
