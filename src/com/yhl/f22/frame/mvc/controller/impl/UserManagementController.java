/**
 * @License:
 * @Copyright:
 */
package com.yhl.f22.frame.mvc.controller.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yhl.f22.frame.db.po.impl.QmcUsers;
import com.yhl.f22.frame.mvc.controller.AbstractController;
import com.yhl.f22.frame.mvc.lang.ControllerException;
import com.yhl.f22.frame.service.IUserBusinessService;

/**
 * <pre>
 * </pre>
 * 
 * @author Administrator
 * @date 2017年4月18日下午7:46:22
 * @since JDK 1.7
 * @since
 */
@Controller("UserManagementController")
@RequestMapping(value = "/user")
public class UserManagementController extends AbstractController {

	/* (non-Javadoc)
	 * @see com.yhl.f22.frame.mvc.controller.AbstractController#getMVCModule()
	 */
	@Override
	protected String getMVCModule() {
		return "user";
	}

	@Resource(name = "UserBusinessService")
	private IUserBusinessService userService;

	@RequestMapping(value = "/toAddUser.do", method = RequestMethod.GET)
	public String toAddUser() throws ControllerException {
		return "/jsp/inc/sections/addUser_form.jsp";
	}

	@RequestMapping(value = "/addUser.do", method = RequestMethod.POST)
	@ResponseBody
	public String addUser(String userName, String password, String confirmPassword, String qqGroupNo, String mobileNumber) throws ControllerException {
		if (!password.equals(confirmPassword)) {
			return JSONObject.toJSONString(getResultInfoBean(9999));
		}

		QmcUsers userInfo = new QmcUsers();
		userInfo.setQmcNo(qqGroupNo);
		userInfo.setQmcPwd(password);
		userInfo.setQmcTel(mobileNumber);
		userInfo.setQmcUname(userName);
		int savedCount = userService.addNewUser(userInfo);

		if (savedCount == 1) {
			return JSONObject.toJSONString(getResultInfoBean(0));
		}

		return JSONObject.toJSONString(getResultInfoBean(1));
	}

	@RequestMapping(value = "/userList.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String userList(HttpServletRequest request, Integer pageSize, Integer pageNumber) throws ControllerException {
		if (pageSize == null) {
			pageSize = 20;
		}
		if (pageNumber == null) {
			pageNumber = 1;
		}
		
		int total = userService.queryCount();
		int pageCount = total < pageSize ? 1 : (total % pageSize == 0 ? total / pageSize : total % pageSize + 1);
		
		List<QmcUsers> userList = userService.queryAllUserList(pageSize, pageNumber);
		request.setAttribute("userList", userList);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("pageCount", Integer.valueOf(pageCount));
		request.setAttribute("total", Integer.valueOf(total));

		return "/jsp/inc/sections/userList.jsp";
	}

}
