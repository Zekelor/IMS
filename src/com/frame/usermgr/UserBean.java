package com.frame.usermgr;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.yhl.f22.frame.utils.StringUtils;

@JSONType
public class UserBean {
	@JSONField(name = "id")
	private int qmcUid;		//ID
	//@JSONField(name = "title")
	private String qmcUname;	//用户名
	private String qmcPwd;		//密码
	private String qmcNo;		//编号
	private String qmcTel;		//联系方式
	private String qmcAccessToken;//token
	private String qmcRole;		//角色
	private String qmcDepartment;//部门
	
	public static UserBean getBeanFromRequest(HttpServletRequest request){
		UserBean bean =new UserBean();
		if(null !=request){
			String userName =request.getParameter("userName").trim();
			if(StringUtils.isNotBlank(userName)){
				bean.setQmcUname(userName);
			}
			String password =request.getParameter("password").trim();
			if(StringUtils.isNotBlank(password)){
				bean.setQmcPwd(password);
			}
			String confirmPassword =request.getParameter("confirmPassword").trim();
			if(StringUtils.isNotBlank(confirmPassword)){
				bean.setQmcPwd(confirmPassword);
			}
			String mobileNumber =request.getParameter("mobileNumber").trim();
			if(StringUtils.isNotBlank(mobileNumber)){
				bean.setQmcTel(mobileNumber);
			}
			String role =request.getParameter("role").trim();
			if(StringUtils.isNoneBlank(role)){
				bean.setQmcRole(role);
			}
			String department =request.getParameter("department").trim();
			if(StringUtils.isNotBlank(department)){
				bean.setQmcDepartment(department);
			}
		}
		return bean;
	}
	
	
	public int getQmcUid() {
		return qmcUid;
	}
	public void setQmcUid(int qmcUid) {
		this.qmcUid = qmcUid;
	}
	public String getQmcUname() {
		return qmcUname;
	}
	public void setQmcUname(String qmcUname) {
		this.qmcUname = qmcUname;
	}
	public String getQmcPwd() {
		return qmcPwd;
	}
	public void setQmcPwd(String qmcPwd) {
		this.qmcPwd = qmcPwd;
	}
	public String getQmcNo() {
		return qmcNo;
	}
	public void setQmcNo(String qmcNo) {
		this.qmcNo = qmcNo;
	}
	public String getQmcTel() {
		return qmcTel;
	}
	public void setQmcTel(String qmcTel) {
		this.qmcTel = qmcTel;
	}
	public String getQmcAccessToken() {
		return qmcAccessToken;
	}
	public void setQmcAccessToken(String qmcAccessToken) {
		this.qmcAccessToken = qmcAccessToken;
	}
	public String getQmcRole() {
		return qmcRole;
	}
	public void setQmcRole(String qmcRole) {
		this.qmcRole = qmcRole;
	}
	public String getQmcDepartment() {
		return qmcDepartment;
	}
	public void setQmcDepartment(String qmcDepartment) {
		this.qmcDepartment = qmcDepartment;
	}
	
	
	
	
}
