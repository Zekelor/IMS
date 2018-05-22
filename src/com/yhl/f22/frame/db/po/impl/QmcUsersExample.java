package com.yhl.f22.frame.db.po.impl;

import java.util.ArrayList;
import java.util.List;

public class QmcUsersExample {
	/**
	 * 主键字段
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:48
	 */
	protected String pk_name = "qmc_uid";

	/**
	 * 排序字段
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:48
	 */
	protected String orderByClause;

	/**
	 * 去重复
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:48
	 */
	protected boolean distinct;

	/**
	 * 条件集
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:48
	 */
	protected List<Criteria> oredCriteria;

	public QmcUsersExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	public void setPk_name(String pk_name) {
		this.pk_name = pk_name;
	}

	public String getPk_name() {
		return pk_name;
	}

	/**
	 * 排序字段
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:48
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * 设置去重复
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:48
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * 条件查询要先创建Criteria
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:48
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * 内类部，系统内部调用1
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:48
	 */
	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andQmcUidIsNull() {
			addCriterion("qmc_uid is null");
			return (Criteria) this;
		}

		public Criteria andQmcUidIsNotNull() {
			addCriterion("qmc_uid is not null");
			return (Criteria) this;
		}

		public Criteria andQmcUidEqualTo(Long value) {
			addCriterion("qmc_uid =", value, "qmcUid");
			return (Criteria) this;
		}

		public Criteria andQmcUidNotEqualTo(Long value) {
			addCriterion("qmc_uid <>", value, "qmcUid");
			return (Criteria) this;
		}

		public Criteria andQmcUidGreaterThan(Long value) {
			addCriterion("qmc_uid >", value, "qmcUid");
			return (Criteria) this;
		}

		public Criteria andQmcUidGreaterThanOrEqualTo(Long value) {
			addCriterion("qmc_uid >=", value, "qmcUid");
			return (Criteria) this;
		}

		public Criteria andQmcUidLessThan(Long value) {
			addCriterion("qmc_uid <", value, "qmcUid");
			return (Criteria) this;
		}

		public Criteria andQmcUidLessThanOrEqualTo(Long value) {
			addCriterion("qmc_uid <=", value, "qmcUid");
			return (Criteria) this;
		}

		public Criteria andQmcUidIn(List<Long> values) {
			addCriterion("qmc_uid in", values, "qmcUid");
			return (Criteria) this;
		}

		public Criteria andQmcUidNotIn(List<Long> values) {
			addCriterion("qmc_uid not in", values, "qmcUid");
			return (Criteria) this;
		}

		public Criteria andQmcUidBetween(Long value1, Long value2) {
			addCriterion("qmc_uid between", value1, value2, "qmcUid");
			return (Criteria) this;
		}

		public Criteria andQmcUidNotBetween(Long value1, Long value2) {
			addCriterion("qmc_uid not between", value1, value2, "qmcUid");
			return (Criteria) this;
		}

		public Criteria andQmcUnameIsNull() {
			addCriterion("qmc_uname is null");
			return (Criteria) this;
		}

		public Criteria andQmcUnameIsNotNull() {
			addCriterion("qmc_uname is not null");
			return (Criteria) this;
		}

		public Criteria andQmcUnameEqualTo(String value) {
			addCriterion("qmc_uname =", value, "qmcUname");
			return (Criteria) this;
		}

		public Criteria andQmcUnameNotEqualTo(String value) {
			addCriterion("qmc_uname <>", value, "qmcUname");
			return (Criteria) this;
		}

		public Criteria andQmcUnameGreaterThan(String value) {
			addCriterion("qmc_uname >", value, "qmcUname");
			return (Criteria) this;
		}

		public Criteria andQmcUnameGreaterThanOrEqualTo(String value) {
			addCriterion("qmc_uname >=", value, "qmcUname");
			return (Criteria) this;
		}

		public Criteria andQmcUnameLessThan(String value) {
			addCriterion("qmc_uname <", value, "qmcUname");
			return (Criteria) this;
		}

		public Criteria andQmcUnameLessThanOrEqualTo(String value) {
			addCriterion("qmc_uname <=", value, "qmcUname");
			return (Criteria) this;
		}

		public Criteria andQmcUnameLike(String value) {
			addCriterion("qmc_uname like", value, "qmcUname");
			return (Criteria) this;
		}

		public Criteria andQmcUnameNotLike(String value) {
			addCriterion("qmc_uname not like", value, "qmcUname");
			return (Criteria) this;
		}

		public Criteria andQmcUnameIn(List<String> values) {
			addCriterion("qmc_uname in", values, "qmcUname");
			return (Criteria) this;
		}

		public Criteria andQmcUnameNotIn(List<String> values) {
			addCriterion("qmc_uname not in", values, "qmcUname");
			return (Criteria) this;
		}

		public Criteria andQmcUnameBetween(String value1, String value2) {
			addCriterion("qmc_uname between", value1, value2, "qmcUname");
			return (Criteria) this;
		}

		public Criteria andQmcUnameNotBetween(String value1, String value2) {
			addCriterion("qmc_uname not between", value1, value2, "qmcUname");
			return (Criteria) this;
		}

		public Criteria andQmcPwdIsNull() {
			addCriterion("qmc_pwd is null");
			return (Criteria) this;
		}

		public Criteria andQmcPwdIsNotNull() {
			addCriterion("qmc_pwd is not null");
			return (Criteria) this;
		}

		public Criteria andQmcPwdEqualTo(String value) {
			addCriterion("qmc_pwd =", value, "qmcPwd");
			return (Criteria) this;
		}

		public Criteria andQmcPwdNotEqualTo(String value) {
			addCriterion("qmc_pwd <>", value, "qmcPwd");
			return (Criteria) this;
		}

		public Criteria andQmcPwdGreaterThan(String value) {
			addCriterion("qmc_pwd >", value, "qmcPwd");
			return (Criteria) this;
		}

		public Criteria andQmcPwdGreaterThanOrEqualTo(String value) {
			addCriterion("qmc_pwd >=", value, "qmcPwd");
			return (Criteria) this;
		}

		public Criteria andQmcPwdLessThan(String value) {
			addCriterion("qmc_pwd <", value, "qmcPwd");
			return (Criteria) this;
		}

		public Criteria andQmcPwdLessThanOrEqualTo(String value) {
			addCriterion("qmc_pwd <=", value, "qmcPwd");
			return (Criteria) this;
		}

		public Criteria andQmcPwdLike(String value) {
			addCriterion("qmc_pwd like", value, "qmcPwd");
			return (Criteria) this;
		}

		public Criteria andQmcPwdNotLike(String value) {
			addCriterion("qmc_pwd not like", value, "qmcPwd");
			return (Criteria) this;
		}

		public Criteria andQmcPwdIn(List<String> values) {
			addCriterion("qmc_pwd in", values, "qmcPwd");
			return (Criteria) this;
		}

		public Criteria andQmcPwdNotIn(List<String> values) {
			addCriterion("qmc_pwd not in", values, "qmcPwd");
			return (Criteria) this;
		}

		public Criteria andQmcPwdBetween(String value1, String value2) {
			addCriterion("qmc_pwd between", value1, value2, "qmcPwd");
			return (Criteria) this;
		}

		public Criteria andQmcPwdNotBetween(String value1, String value2) {
			addCriterion("qmc_pwd not between", value1, value2, "qmcPwd");
			return (Criteria) this;
		}

		public Criteria andQmcNoIsNull() {
			addCriterion("qmc_no is null");
			return (Criteria) this;
		}

		public Criteria andQmcNoIsNotNull() {
			addCriterion("qmc_no is not null");
			return (Criteria) this;
		}

		public Criteria andQmcNoEqualTo(String value) {
			addCriterion("qmc_no =", value, "qmcNo");
			return (Criteria) this;
		}

		public Criteria andQmcNoNotEqualTo(String value) {
			addCriterion("qmc_no <>", value, "qmcNo");
			return (Criteria) this;
		}

		public Criteria andQmcNoGreaterThan(String value) {
			addCriterion("qmc_no >", value, "qmcNo");
			return (Criteria) this;
		}

		public Criteria andQmcNoGreaterThanOrEqualTo(String value) {
			addCriterion("qmc_no >=", value, "qmcNo");
			return (Criteria) this;
		}

		public Criteria andQmcNoLessThan(String value) {
			addCriterion("qmc_no <", value, "qmcNo");
			return (Criteria) this;
		}

		public Criteria andQmcNoLessThanOrEqualTo(String value) {
			addCriterion("qmc_no <=", value, "qmcNo");
			return (Criteria) this;
		}

		public Criteria andQmcNoLike(String value) {
			addCriterion("qmc_no like", value, "qmcNo");
			return (Criteria) this;
		}

		public Criteria andQmcNoNotLike(String value) {
			addCriterion("qmc_no not like", value, "qmcNo");
			return (Criteria) this;
		}

		public Criteria andQmcNoIn(List<String> values) {
			addCriterion("qmc_no in", values, "qmcNo");
			return (Criteria) this;
		}

		public Criteria andQmcNoNotIn(List<String> values) {
			addCriterion("qmc_no not in", values, "qmcNo");
			return (Criteria) this;
		}

		public Criteria andQmcNoBetween(String value1, String value2) {
			addCriterion("qmc_no between", value1, value2, "qmcNo");
			return (Criteria) this;
		}

		public Criteria andQmcNoNotBetween(String value1, String value2) {
			addCriterion("qmc_no not between", value1, value2, "qmcNo");
			return (Criteria) this;
		}

		public Criteria andQmcTelIsNull() {
			addCriterion("qmc_tel is null");
			return (Criteria) this;
		}

		public Criteria andQmcTelIsNotNull() {
			addCriterion("qmc_tel is not null");
			return (Criteria) this;
		}

		public Criteria andQmcTelEqualTo(String value) {
			addCriterion("qmc_tel =", value, "qmcTel");
			return (Criteria) this;
		}

		public Criteria andQmcTelNotEqualTo(String value) {
			addCriterion("qmc_tel <>", value, "qmcTel");
			return (Criteria) this;
		}

		public Criteria andQmcTelGreaterThan(String value) {
			addCriterion("qmc_tel >", value, "qmcTel");
			return (Criteria) this;
		}

		public Criteria andQmcTelGreaterThanOrEqualTo(String value) {
			addCriterion("qmc_tel >=", value, "qmcTel");
			return (Criteria) this;
		}

		public Criteria andQmcTelLessThan(String value) {
			addCriterion("qmc_tel <", value, "qmcTel");
			return (Criteria) this;
		}

		public Criteria andQmcTelLessThanOrEqualTo(String value) {
			addCriterion("qmc_tel <=", value, "qmcTel");
			return (Criteria) this;
		}

		public Criteria andQmcTelLike(String value) {
			addCriterion("qmc_tel like", value, "qmcTel");
			return (Criteria) this;
		}

		public Criteria andQmcTelNotLike(String value) {
			addCriterion("qmc_tel not like", value, "qmcTel");
			return (Criteria) this;
		}

		public Criteria andQmcTelIn(List<String> values) {
			addCriterion("qmc_tel in", values, "qmcTel");
			return (Criteria) this;
		}

		public Criteria andQmcTelNotIn(List<String> values) {
			addCriterion("qmc_tel not in", values, "qmcTel");
			return (Criteria) this;
		}

		public Criteria andQmcTelBetween(String value1, String value2) {
			addCriterion("qmc_tel between", value1, value2, "qmcTel");
			return (Criteria) this;
		}

		public Criteria andQmcTelNotBetween(String value1, String value2) {
			addCriterion("qmc_tel not between", value1, value2, "qmcTel");
			return (Criteria) this;
		}

		public Criteria andQmcAccessTokenIsNull() {
			addCriterion("qmc_access_token is null");
			return (Criteria) this;
		}

		public Criteria andQmcAccessTokenIsNotNull() {
			addCriterion("qmc_access_token is not null");
			return (Criteria) this;
		}

		public Criteria andQmcAccessTokenEqualTo(String value) {
			addCriterion("qmc_access_token =", value, "qmcAccessToken");
			return (Criteria) this;
		}

		public Criteria andQmcAccessTokenNotEqualTo(String value) {
			addCriterion("qmc_access_token <>", value, "qmcAccessToken");
			return (Criteria) this;
		}

		public Criteria andQmcAccessTokenGreaterThan(String value) {
			addCriterion("qmc_access_token >", value, "qmcAccessToken");
			return (Criteria) this;
		}

		public Criteria andQmcAccessTokenGreaterThanOrEqualTo(String value) {
			addCriterion("qmc_access_token >=", value, "qmcAccessToken");
			return (Criteria) this;
		}

		public Criteria andQmcAccessTokenLessThan(String value) {
			addCriterion("qmc_access_token <", value, "qmcAccessToken");
			return (Criteria) this;
		}

		public Criteria andQmcAccessTokenLessThanOrEqualTo(String value) {
			addCriterion("qmc_access_token <=", value, "qmcAccessToken");
			return (Criteria) this;
		}

		public Criteria andQmcAccessTokenLike(String value) {
			addCriterion("qmc_access_token like", value, "qmcAccessToken");
			return (Criteria) this;
		}

		public Criteria andQmcAccessTokenNotLike(String value) {
			addCriterion("qmc_access_token not like", value, "qmcAccessToken");
			return (Criteria) this;
		}

		public Criteria andQmcAccessTokenIn(List<String> values) {
			addCriterion("qmc_access_token in", values, "qmcAccessToken");
			return (Criteria) this;
		}

		public Criteria andQmcAccessTokenNotIn(List<String> values) {
			addCriterion("qmc_access_token not in", values, "qmcAccessToken");
			return (Criteria) this;
		}

		public Criteria andQmcAccessTokenBetween(String value1, String value2) {
			addCriterion("qmc_access_token between", value1, value2, "qmcAccessToken");
			return (Criteria) this;
		}

		public Criteria andQmcAccessTokenNotBetween(String value1, String value2) {
			addCriterion("qmc_access_token not between", value1, value2, "qmcAccessToken");
			return (Criteria) this;
		}
	}

	/**
	 * qmc_users
	 * 
	 * @ibatorgenerated do_not_delete_during_merge 2017-04-18 13:53:48
	 */
	public static class Criteria extends GeneratedCriteria {

		protected Criteria() {
			super();
		}
	}

	/**
	 * 内类部，系统内部调用1
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:48
	 */
	public static class Criterion {
		private String condition;

		private Object value;

		private Object secondValue;

		private boolean noValue;

		private boolean singleValue;

		private boolean betweenValue;

		private boolean listValue;

		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			}
			else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}
}