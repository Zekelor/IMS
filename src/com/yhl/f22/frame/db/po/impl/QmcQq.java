package com.yhl.f22.frame.db.po.impl;

import com.yhl.f22.frame.db.po.AbstractDatabasePojo;

public class QmcQq extends AbstractDatabasePojo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5557326735014834729L;

	/**
	 * qmc_qq.qmc_qqid (qqid)
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:47
	 */
	private Long qmcQqid;

	/**
	 * qmc_qq.qmc_no (客户端编号)
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:47
	 */
	private String qmcNo;

	/**
	 * qmc_qq.qmc_qq (QQ号码)
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:47
	 */
	private String qmcQq;

	/**
	 * qmc_qq.qmc_qquin (qq内部编码)
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:47
	 */
	private String qmcQquin;

	public Long getQmcQqid() {
		return qmcQqid;
	}

	public void setQmcQqid(Long qmcQqid) {
		this.qmcQqid = qmcQqid;
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

	public String getQmcQquin() {
		return qmcQquin;
	}

	public void setQmcQquin(String qmcQquin) {
		this.qmcQquin = qmcQquin;
	}
}