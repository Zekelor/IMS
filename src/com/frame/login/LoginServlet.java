package com.frame.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.frame.logmgr.LogBean;
import com.frame.logmgr.LogDAO;
import com.frame.role.RoleService;
import com.yhl.f22.frame.mvc.bean.UserLoginToken;
import com.yhl.f22.frame.utils.StringUtils;

public class LoginServlet extends HttpServlet {
	LoginDAO dao = new LoginDAO();
	RoleService roleService = new RoleService();

	private static final long serialVersionUID = 1L;

	public LoginServlet() {
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
		String req = request.getServletPath();
		String username = request.getParameter("userLoginToken.userName");
		String password = request.getParameter("userLoginToken.userPassword");
		String loginIp = getIpAddr(request);
		
		UserLoginToken userLoginToken = new UserLoginToken();
		userLoginToken.setUserName(username);
		userLoginToken.setUserPassword(password);

		if ("/servlet/login.do".equals(req)) {

			if (username == "" || password == "") {
				response.getWriter().print("PLEASE COMPELET YOUR LOGIN INFO");
			} else {
				String flag = dao.queryLoginInfo(username, password);
				if (flag == "1") {
					LogBean logBean = new LogBean();
					logBean.setQmcLoginName(username);
					logBean.setQmcLoginIp(loginIp);
					logBean.setQmcLastDate(String.valueOf(System.currentTimeMillis() / 1000));

					String msg = userLogin(userLoginToken, request.getSession());
					logBean.setQmcDesc(msg);
					LogDAO.writeSysLog(logBean);
					System.out.println("msg::::::::" + msg);
					//response.getWriter().print(msg);
					response.sendRedirect("/jsp/example/data.jsp");
				} else {
					response.getWriter().print("PLEASE CHECK YOUR PASSWORD");

				}
			}

		} else if ("/servlet/logout.do".equals(req)) {
			String user =request.getParameter("loginUser");
			LogBean logBean = new LogBean();
			logBean.setQmcLoginName(user);
			logBean.setQmcLoginIp(loginIp);
			logBean.setQmcLastDate(String.valueOf(System.currentTimeMillis() / 1000));
			String msg = userLogout(userLoginToken, request.getSession());
			logBean.setQmcDesc(msg);
			LogDAO.writeSysLog(logBean);
			System.out.println("msg::::::" + msg);
			response.sendRedirect("/");
		}

	}

	/**
	 * 获取访问者IP
	 * 
	 * @param request
	 * @return
	 */
	private String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isBlank(ip) && "unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isBlank(ip) && "unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值,第一个为真实IP
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}

	/**
	 * 用户登录信息
	 * 
	 * @param userLoginToken
	 * @param session
	 * @return
	 */
	public String userLogin(UserLoginToken userLoginToken, HttpSession session) {
		String role = roleService.queryRoleType(userLoginToken.getUserName());
		session.setAttribute("USER_LOGIN_TOKEN", userLoginToken);
		session.setAttribute("username", userLoginToken.getUserName());
		session.setAttribute("role", role);
		String msg = "用户登录成功";
		return msg;
	}

	/**
	 * 用户登出
	 * 
	 * @param userLoginToken
	 * @param session
	 * @return
	 */
	public String userLogout(UserLoginToken userLoginToken, HttpSession session) {
		session.removeAttribute("USER_LOGIN_TOKEN");
		session.removeAttribute("username");
		session.invalidate();
		String msg = "用户注销成功";
		return msg;
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
