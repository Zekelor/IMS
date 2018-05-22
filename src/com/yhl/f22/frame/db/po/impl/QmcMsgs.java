package com.yhl.f22.frame.db.po.impl;

import com.yhl.f22.frame.db.po.AbstractDatabasePojo;

public class QmcMsgs extends AbstractDatabasePojo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6972736406232270410L;

	/**
	 * qmc_msgs.qmc_mid (massage id)
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:48
	 */
	private Long qmcMid;

	/**
	 * qmc_msgs.qmc_group (发送群号)
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:48
	 */
	private String qmcGroup;

	/**
	 * qmc_msgs.qmc_group_uin (发送群号内部编码)
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:48
	 */
	private String qmcGroupUin;

	/**
	 * qmc_msgs.qmc_sender (发送QQ号)
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:48
	 */
	private String qmcSender;

	/**
	 * qmc_msgs.qmc_sender_uin (发送QQ号内部编码)
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:48
	 */
	private String qmcSenderUin;

	/**
	 * qmc_msgs.qmc_sender_nick (发送QQ号昵称)
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:48
	 */
	private String qmcSenderNick;

	/**
	 * qmc_msgs.qmc_msg_date (消息日期)
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:48
	 */
	private Long qmcMsgDate;

	/**
	 * qmc_msgs.qmc_from_qq (消息来源qq)
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:48
	 */
	private String qmcFromQq;

	/**
	 * qmc_msgs.qmc_no (消息来源客户端编码)
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:48
	 */
	private String qmcNo;

	/**
	 * qmc_msgs.qmc_msg (QQ消息)
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:48
	 */
	private String qmcMsg;

	public Long getQmcMid() {
		return qmcMid;
	}

	public void setQmcMid(Long qmcMid) {
		this.qmcMid = qmcMid;
	}

	public String getQmcGroup() {
		return qmcGroup;
	}

	public void setQmcGroup(String qmcGroup) {
		this.qmcGroup = qmcGroup;
	}

	public String getQmcGroupUin() {
		return qmcGroupUin;
	}

	public void setQmcGroupUin(String qmcGroupUin) {
		this.qmcGroupUin = qmcGroupUin;
	}

	public String getQmcSender() {
		return qmcSender;
	}

	public void setQmcSender(String qmcSender) {
		this.qmcSender = qmcSender;
	}

	public String getQmcSenderUin() {
		return qmcSenderUin;
	}

	public void setQmcSenderUin(String qmcSenderUin) {
		this.qmcSenderUin = qmcSenderUin;
	}

	public String getQmcSenderNick() {
		return qmcSenderNick;
	}

	public void setQmcSenderNick(String qmcSenderNick) {
		this.qmcSenderNick = qmcSenderNick;
	}

	public Long getQmcMsgDate() {
		return qmcMsgDate;
	}

	public void setQmcMsgDate(Long qmcMsgDate) {
		this.qmcMsgDate = qmcMsgDate;
	}

	public String getQmcFromQq() {
		return qmcFromQq;
	}

	public void setQmcFromQq(String qmcFromQq) {
		this.qmcFromQq = qmcFromQq;
	}

	public String getQmcNo() {
		return qmcNo;
	}

	public void setQmcNo(String qmcNo) {
		this.qmcNo = qmcNo;
	}

	public String getQmcMsg() {
		return qmcMsg;
	}

	public void setQmcMsg(String qmcMsg) {
		this.qmcMsg = qmcMsg;
	}
}