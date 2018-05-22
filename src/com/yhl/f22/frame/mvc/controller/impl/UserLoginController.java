/**
 * @License:
 * @Copyright:
 */
package com.yhl.f22.frame.mvc.controller.impl;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.frame.role.RoleService;
import com.yhl.f22.frame.mvc.bean.UserLoginToken;
import com.yhl.f22.frame.mvc.controller.AbstractController;
import com.yhl.f22.frame.mvc.lang.ControllerException;
import com.yhl.f22.frame.service.IUserBusinessService;

/**
 * <pre>
 * </pre>
 * 
 * @author Administrator
 * @date 2017年4月18日上午11:37:55
 * @since JDK 1.7
 * @since
 */
@Controller("UserLoginController")
@RequestMapping(value = "/user")
public class UserLoginController extends AbstractController {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(UserLoginController.class);
	RoleService roleService=new RoleService();

	/* (non-Javadoc)
	 * @see com.yhl.f22.frame.mvc.controller.AbstractController#getMVCModule()
	 */
	@Override
	protected String getMVCModule() {
		return "user";
	}

	@Resource(name = "UserBusinessService")
	private IUserBusinessService userService;

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	@ResponseBody
	public String userLogin(UserLoginToken userLoginToken, HttpSession session) throws ControllerException {
		int loginResult = userService.userLogin(userLoginToken.getUserName(), userLoginToken.getUserPassword());

		if (loginResult == 0) {
			String role=roleService.queryRoleType(userLoginToken.getUserName());
			session.setAttribute("USER_LOGIN_TOKEN", userLoginToken);
			session.setAttribute("username", userLoginToken.getUserName());
			session.setAttribute("role", role);
		}

		return JSONObject.toJSONString(getResultInfoBean(loginResult));
	}

	@RequestMapping(value = "/logout.do")
	public void logout(HttpServletResponse response, HttpSession session) {
		session.removeAttribute("USER_LOGIN_TOKEN");
		session.removeAttribute("username");
		session.invalidate();

		try {
			response.sendRedirect("/");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
