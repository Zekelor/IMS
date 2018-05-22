package com.yhl.f22.frame.db.po.impl;

import com.yhl.f22.frame.db.po.AbstractDatabasePojo;

public class QmcGroups extends AbstractDatabasePojo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7111116866626490859L;

	/**
	 * qmc_groups.qmc_gid (组id)
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:47
	 */
	private Long qmcGid;

	/**
	 * qmc_groups.qmc_no
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:47
	 */
	private String qmcNo;

	/**
	 * qmc_groups.qmc_qq
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:47
	 */
	private String qmcQq;

	/**
	 * qmc_groups.qmc_group
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:47
	 */
	private String qmcGroup;

	/**
	 * qmc_groups.qmc_group_name
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:47
	 */
	private String qmcGroupName;

	public Long getQmcGid() {
		return qmcGid;
	}

	public void setQmcGid(Long qmcGid) {
		this.qmcGid = qmcGid;
	}

	public String getQmcNo() {
		return qmcNo;
	}

	public void setQmcNo(String qmcNo) {
		this.qmcNo = qmcNo;
	}

	public String getQmcQq() {
		return qmcQq;
	}

	public void setQmcQq(String qmcQq) {
		this.qmcQq = qmcQq;
	}

	public String getQmcGroup() {
		return qmcGroup;
	}

	public void setQmcGroup(String qmcGroup) {
		this.qmcGroup = qmcGroup;
	}

	public String getQmcGroupName() {
		return qmcGroupName;
	}

	public void setQmcGroupName(String qmcGroupName) {
		this.qmcGroupName = qmcGroupName;
	}
}