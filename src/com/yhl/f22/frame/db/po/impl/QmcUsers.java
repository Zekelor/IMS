package com.yhl.f22.frame.db.po.impl;

import com.yhl.f22.frame.db.po.AbstractDatabasePojo;

public class QmcUsers extends AbstractDatabasePojo {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7934372007831554666L;

	/**
	 * qmc_users.qmc_uid (用户id)
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:48
	 */
	private Long qmcUid;

	/**
	 * qmc_users.qmc_uname (用户名称)
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:48
	 */
	private String qmcUname;

	/**
	 * qmc_users.qmc_pwd (密码)
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:48
	 */
	private String qmcPwd;

	/**
	 * qmc_users.qmc_no (客户端编号)
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:48
	 */
	private String qmcNo;

	/**
	 * qmc_users.qmc_tel (联系方式)
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:48
	 */
	private String qmcTel;

	/**
	 * qmc_users.qmc_access_token (token)
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:48
	 */
	private String qmcAccessToken;

	public Long getQmcUid() {
		return qmcUid;
	}

	public void setQmcUid(Long qmcUid) {
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
}