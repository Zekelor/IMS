package com.frame.tagmgr;

public class TagBean {

	private int qmcTId;		//标签ID
	private int qmcGId;		//群ID
	private String qmcTagName;	//标签名称
	private int qmcCreateUId;	//创建人ID
	private int qmcCreateTime;	//创建时间
	private String qmcRemark;	//备注

	public int getQmcTId() {
		return qmcTId;
	}

	public void setQmcTId(int qmcTId) {
		this.qmcTId = qmcTId;
	}

	public int getQmcGId() {
		return qmcGId;
	}

	public void setQmcGId(int qmcGId) {
		this.qmcGId = qmcGId;
	}

	public String getQmcTagName() {
		return qmcTagName;
	}

	public void setQmcTagName(String qmcTagName) {
		this.qmcTagName = qmcTagName;
	}

	public int getQmcCreateUId() {
		return qmcCreateUId;
	}

	public void setQmcCreateUId(int qmcCreateUId) {
		this.qmcCreateUId = qmcCreateUId;
	}

	public int getQmcCreateTime() {
		return qmcCreateTime;
	}

	public void setQmcCreateTime(int qmcCreateTime) {
		this.qmcCreateTime = qmcCreateTime;
	}

	public String getQmcRemark() {
		return qmcRemark;
	}

	public void setQmcRemark(String qmcRemark) {
		this.qmcRemark = qmcRemark;
	}

}
