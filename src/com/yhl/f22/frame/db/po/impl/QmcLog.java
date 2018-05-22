package com.yhl.f22.frame.db.po.impl;

import com.yhl.f22.frame.db.po.AbstractDatabasePojo;

public class QmcLog extends AbstractDatabasePojo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6574506936880704171L;

	/**
	 * qmc_log.qmc_log_id (日志id)
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:48
	 */
	private Long qmcLogId;

	/**
	 * qmc_log.qmc_no (客户端id)
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:48
	 */
	private String qmcNo;

	/**
	 * qmc_log.qmc_ip (ip)
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:48
	 */
	private String qmcIp;

	/**
	 * qmc_log.qmc_last_date (日期)
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:48
	 */
	private Long qmcLastDate;

	/**
	 * qmc_log.qmc_qq
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:48
	 */
	private String qmcQq;

	public Long getQmcLogId() {
		return qmcLogId;
	}

	public void setQmcLogId(Long qmcLogId) {
		this.qmcLogId = qmcLogId;
	}

	public String getQmcNo() {
		return qmcNo;
	}

	public void setQmcNo(String qmcNo) {
		this.qmcNo = qmcNo;
	}

	public String getQmcIp() {
		return qmcIp;
	}

	public void setQmcIp(String qmcIp) {
		this.qmcIp = qmcIp;
	}

	public Long getQmcLastDate() {
		return qmcLastDate;
	}

	public void setQmcLastDate(Long qmcLastDate) {
		this.qmcLastDate = qmcLastDate;
	}

	public String getQmcQq() {
		return qmcQq;
	}

	public void setQmcQq(String qmcQq) {
		this.qmcQq = qmcQq;
	}
}