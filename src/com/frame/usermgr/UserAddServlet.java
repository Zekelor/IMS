package com.frame.usermgr;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





import com.frame.db.DBUtil;
import com.yhl.f22.frame.utils.MD5Util;
import com.yhl.f22.frame.utils.StringUtils;

public class UserAddServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DBUtil db =new DBUtil();
	UserDAO dao= new UserDAO();

	/**
	 * Constructor of the object.
	 */
	public UserAddServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String req = request.getServletPath();
		if (req.equals("/servlet/UserAddServlet")) {
			String flag = save(request);

			if (flag.equals("1")) {
				response.getWriter().print("1");

			} else {
				response.getWriter().print("0");
			}
		}

	}
	
	public String save(HttpServletRequest request){
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
		
		String sql ="insert into qmc_users (qmc_uname,qmc_pwd,qmc_no,qmc_tel,qmc_access_token,qmc_role,qmc_department) values(?,?,?,?,?,?,?)";
		try {
			flag=dao.InsertUser(sql,new Object[]{bean.getQmcUname(),bean.getQmcPwd(),bean.getQmcNo(),bean.getQmcTel(),bean.getQmcAccessToken(),bean.getQmcRole(),department});
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(flag);
		
		return flag;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
