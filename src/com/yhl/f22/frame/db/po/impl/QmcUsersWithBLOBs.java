package com.yhl.f22.frame.db.po.impl;

public class QmcUsersWithBLOBs extends QmcUsers {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8780442718155999279L;

	/**
	 * qmc_users.qmc_allow_qq (允许的qq)
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:48
	 */
	private String qmcAllowQq;

	/**
	 * qmc_users.qmc_allow_group (允许的group)
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:48
	 */
	private String qmcAllowGroup;

	public String getQmcAllowQq() {
		return qmcAllowQq;
	}

	public void setQmcAllowQq(String qmcAllowQq) {
		this.qmcAllowQq = qmcAllowQq;
	}

	public String getQmcAllowGroup() {
		return qmcAllowGroup;
	}

	public void setQmcAllowGroup(String qmcAllowGroup) {
		this.qmcAllowGroup = qmcAllowGroup;
	}
}