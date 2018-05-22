package com.frame.usermgr;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.frame.role.RoleService;
import com.yhl.f22.frame.utils.MD5Util;
import com.yhl.f22.frame.utils.StringUtils;

public class UserEditServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	UserDAO dao =new UserDAO();
	RoleService roleService=new RoleService();


	/**
	 * Constructor of the object.
	 */
	public UserEditServlet() {
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
		String req =request.getServletPath();
		
		if(req.equals("/servlet/UserEditServlet")){
			String flag =update(request);
			if(flag.equals("1")){
				response.getWriter().print("1");
			} else {
				response.getWriter().print("0");
			}
		}
	}
	
	public String update(HttpServletRequest request){
		String flag="";
		String department="";
		UserBean bean =UserBean.getBeanFromRequest(request);
		int salt = new Random().nextInt(99999999);
		String access_token = new StringBuffer(bean.getQmcPwd()).append(salt).toString();
		access_token = MD5Util.string2MD5(access_token);
		
		bean.setQmcAccessToken(access_token);
		bean.setQmcNo(bean.getQmcUname());
		if(!StringUtils.isEmpty(bean.getQmcDepartment())){
			 department =dao.queryDepartById(bean.getQmcDepartment());
		}
		String sql ="update qmc_users set qmc_pwd=?,qmc_no=?,qmc_tel=?,qmc_access_token=?,qmc_role=?,qmc_department=? where qmc_uname=?";
		flag=dao.UpdateUser(sql,new Object[]{bean.getQmcPwd(),bean.getQmcNo(),bean.getQmcTel(),bean.getQmcAccessToken(),bean.getQmcRole(),department,bean.getQmcUname()});
		
		return flag;
		
	}

	
	public void init() throws ServletException {
		// Put your code here
	}

}
