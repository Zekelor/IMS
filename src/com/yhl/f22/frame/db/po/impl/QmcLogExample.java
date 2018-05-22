package com.yhl.f22.frame.db.po.impl;

import java.util.ArrayList;
import java.util.List;

public class QmcLogExample {
	/**
	 * 主键字段
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:48
	 */
	protected String pk_name = "qmc_log_id";

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

	public QmcLogExample() {
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

		public Criteria andQmcLogIdIsNull() {
			addCriterion("qmc_log_id is null");
			return (Criteria) this;
		}

		public Criteria andQmcLogIdIsNotNull() {
			addCriterion("qmc_log_id is not null");
			return (Criteria) this;
		}

		public Criteria andQmcLogIdEqualTo(Long value) {
			addCriterion("qmc_log_id =", value, "qmcLogId");
			return (Criteria) this;
		}

		public Criteria andQmcLogIdNotEqualTo(Long value) {
			addCriterion("qmc_log_id <>", value, "qmcLogId");
			return (Criteria) this;
		}

		public Criteria andQmcLogIdGreaterThan(Long value) {
			addCriterion("qmc_log_id >", value, "qmcLogId");
			return (Criteria) this;
		}

		public Criteria andQmcLogIdGreaterThanOrEqualTo(Long value) {
			addCriterion("qmc_log_id >=", value, "qmcLogId");
			return (Criteria) this;
		}

		public Criteria andQmcLogIdLessThan(Long value) {
			addCriterion("qmc_log_id <", value, "qmcLogId");
			return (Criteria) this;
		}

		public Criteria andQmcLogIdLessThanOrEqualTo(Long value) {
			addCriterion("qmc_log_id <=", value, "qmcLogId");
			return (Criteria) this;
		}

		public Criteria andQmcLogIdIn(List<Long> values) {
			addCriterion("qmc_log_id in", values, "qmcLogId");
			return (Criteria) this;
		}

		public Criteria andQmcLogIdNotIn(List<Long> values) {
			addCriterion("qmc_log_id not in", values, "qmcLogId");
			return (Criteria) this;
		}

		public Criteria andQmcLogIdBetween(Long value1, Long value2) {
			addCriterion("qmc_log_id between", value1, value2, "qmcLogId");
			return (Criteria) this;
		}

		public Criteria andQmcLogIdNotBetween(Long value1, Long value2) {
			addCriterion("qmc_log_id not between", value1, value2, "qmcLogId");
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

		public Criteria andQmcIpIsNull() {
			addCriterion("qmc_ip is null");
			return (Criteria) this;
		}

		public Criteria andQmcIpIsNotNull() {
			addCriterion("qmc_ip is not null");
			return (Criteria) this;
		}

		public Criteria andQmcIpEqualTo(String value) {
			addCriterion("qmc_ip =", value, "qmcIp");
			return (Criteria) this;
		}

		public Criteria andQmcIpNotEqualTo(String value) {
			addCriterion("qmc_ip <>", value, "qmcIp");
			return (Criteria) this;
		}

		public Criteria andQmcIpGreaterThan(String value) {
			addCriterion("qmc_ip >", value, "qmcIp");
			return (Criteria) this;
		}

		public Criteria andQmcIpGreaterThanOrEqualTo(String value) {
			addCriterion("qmc_ip >=", value, "qmcIp");
			return (Criteria) this;
		}

		public Criteria andQmcIpLessThan(String value) {
			addCriterion("qmc_ip <", value, "qmcIp");
			return (Criteria) this;
		}

		public Criteria andQmcIpLessThanOrEqualTo(String value) {
			addCriterion("qmc_ip <=", value, "qmcIp");
			return (Criteria) this;
		}

		public Criteria andQmcIpLike(String value) {
			addCriterion("qmc_ip like", value, "qmcIp");
			return (Criteria) this;
		}

		public Criteria andQmcIpNotLike(String value) {
			addCriterion("qmc_ip not like", value, "qmcIp");
			return (Criteria) this;
		}

		public Criteria andQmcIpIn(List<String> values) {
			addCriterion("qmc_ip in", values, "qmcIp");
			return (Criteria) this;
		}

		public Criteria andQmcIpNotIn(List<String> values) {
			addCriterion("qmc_ip not in", values, "qmcIp");
			return (Criteria) this;
		}

		public Criteria andQmcIpBetween(String value1, String value2) {
			addCriterion("qmc_ip between", value1, value2, "qmcIp");
			return (Criteria) this;
		}

		public Criteria andQmcIpNotBetween(String value1, String value2) {
			addCriterion("qmc_ip not between", value1, value2, "qmcIp");
			return (Criteria) this;
		}

		public Criteria andQmcLastDateIsNull() {
			addCriterion("qmc_last_date is null");
			return (Criteria) this;
		}

		public Criteria andQmcLastDateIsNotNull() {
			addCriterion("qmc_last_date is not null");
			return (Criteria) this;
		}

		public Criteria andQmcLastDateEqualTo(Long value) {
			addCriterion("qmc_last_date =", value, "qmcLastDate");
			return (Criteria) this;
		}

		public Criteria andQmcLastDateNotEqualTo(Long value) {
			addCriterion("qmc_last_date <>", value, "qmcLastDate");
			return (Criteria) this;
		}

		public Criteria andQmcLastDateGreaterThan(Long value) {
			addCriterion("qmc_last_date >", value, "qmcLastDate");
			return (Criteria) this;
		}

		public Criteria andQmcLastDateGreaterThanOrEqualTo(Long value) {
			addCriterion("qmc_last_date >=", value, "qmcLastDate");
			return (Criteria) this;
		}

		public Criteria andQmcLastDateLessThan(Long value) {
			addCriterion("qmc_last_date <", value, "qmcLastDate");
			return (Criteria) this;
		}

		public Criteria andQmcLastDateLessThanOrEqualTo(Long value) {
			addCriterion("qmc_last_date <=", value, "qmcLastDate");
			return (Criteria) this;
		}

		public Criteria andQmcLastDateIn(List<Long> values) {
			addCriterion("qmc_last_date in", values, "qmcLastDate");
			return (Criteria) this;
		}

		public Criteria andQmcLastDateNotIn(List<Long> values) {
			addCriterion("qmc_last_date not in", values, "qmcLastDate");
			return (Criteria) this;
		}

		public Criteria andQmcLastDateBetween(Long value1, Long value2) {
			addCriterion("qmc_last_date between", value1, value2, "qmcLastDate");
			return (Criteria) this;
		}

		public Criteria andQmcLastDateNotBetween(Long value1, Long value2) {
			addCriterion("qmc_last_date not between", value1, value2, "qmcLastDate");
			return (Criteria) this;
		}

		public Criteria andQmcQqIsNull() {
			addCriterion("qmc_qq is null");
			return (Criteria) this;
		}

		public Criteria andQmcQqIsNotNull() {
			addCriterion("qmc_qq is not null");
			return (Criteria) this;
		}

		public Criteria andQmcQqEqualTo(String value) {
			addCriterion("qmc_qq =", value, "qmcQq");
			return (Criteria) this;
		}

		public Criteria andQmcQqNotEqualTo(String value) {
			addCriterion("qmc_qq <>", value, "qmcQq");
			return (Criteria) this;
		}

		public Criteria andQmcQqGreaterThan(String value) {
			addCriterion("qmc_qq >", value, "qmcQq");
			return (Criteria) this;
		}

		public Criteria andQmcQqGreaterThanOrEqualTo(String value) {
			addCriterion("qmc_qq >=", value, "qmcQq");
			return (Criteria) this;
		}

		public Criteria andQmcQqLessThan(String value) {
			addCriterion("qmc_qq <", value, "qmcQq");
			return (Criteria) this;
		}

		public Criteria andQmcQqLessThanOrEqualTo(String value) {
			addCriterion("qmc_qq <=", value, "qmcQq");
			return (Criteria) this;
		}

		public Criteria andQmcQqLike(String value) {
			addCriterion("qmc_qq like", value, "qmcQq");
			return (Criteria) this;
		}

		public Criteria andQmcQqNotLike(String value) {
			addCriterion("qmc_qq not like", value, "qmcQq");
			return (Criteria) this;
		}

		public Criteria andQmcQqIn(List<String> values) {
			addCriterion("qmc_qq in", values, "qmcQq");
			return (Criteria) this;
		}

		public Criteria andQmcQqNotIn(List<String> values) {
			addCriterion("qmc_qq not in", values, "qmcQq");
			return (Criteria) this;
		}

		public Criteria andQmcQqBetween(String value1, String value2) {
			addCriterion("qmc_qq between", value1, value2, "qmcQq");
			return (Criteria) this;
		}

		public Criteria andQmcQqNotBetween(String value1, String value2) {
			addCriterion("qmc_qq not between", value1, value2, "qmcQq");
			return (Criteria) this;
		}
	}

	/**
	 * qmc_log
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