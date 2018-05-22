package com.yhl.f22.frame.db.po.impl;

import java.util.ArrayList;
import java.util.List;

public class QmcQqExample {
	/**
	 * 主键字段
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:47
	 */
	protected String pk_name = "qmc_qqid";

	/**
	 * 排序字段
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:47
	 */
	protected String orderByClause;

	/**
	 * 去重复
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:47
	 */
	protected boolean distinct;

	/**
	 * 条件集
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:47
	 */
	protected List<Criteria> oredCriteria;

	public QmcQqExample() {
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
	 * @ibatorgenerated 2017-04-18 13:53:47
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
	 * @ibatorgenerated 2017-04-18 13:53:47
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
	 * @ibatorgenerated 2017-04-18 13:53:47
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
	 * @ibatorgenerated 2017-04-18 13:53:47
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

		public Criteria andQmcQqidIsNull() {
			addCriterion("qmc_qqid is null");
			return (Criteria) this;
		}

		public Criteria andQmcQqidIsNotNull() {
			addCriterion("qmc_qqid is not null");
			return (Criteria) this;
		}

		public Criteria andQmcQqidEqualTo(Long value) {
			addCriterion("qmc_qqid =", value, "qmcQqid");
			return (Criteria) this;
		}

		public Criteria andQmcQqidNotEqualTo(Long value) {
			addCriterion("qmc_qqid <>", value, "qmcQqid");
			return (Criteria) this;
		}

		public Criteria andQmcQqidGreaterThan(Long value) {
			addCriterion("qmc_qqid >", value, "qmcQqid");
			return (Criteria) this;
		}

		public Criteria andQmcQqidGreaterThanOrEqualTo(Long value) {
			addCriterion("qmc_qqid >=", value, "qmcQqid");
			return (Criteria) this;
		}

		public Criteria andQmcQqidLessThan(Long value) {
			addCriterion("qmc_qqid <", value, "qmcQqid");
			return (Criteria) this;
		}

		public Criteria andQmcQqidLessThanOrEqualTo(Long value) {
			addCriterion("qmc_qqid <=", value, "qmcQqid");
			return (Criteria) this;
		}

		public Criteria andQmcQqidIn(List<Long> values) {
			addCriterion("qmc_qqid in", values, "qmcQqid");
			return (Criteria) this;
		}

		public Criteria andQmcQqidNotIn(List<Long> values) {
			addCriterion("qmc_qqid not in", values, "qmcQqid");
			return (Criteria) this;
		}

		public Criteria andQmcQqidBetween(Long value1, Long value2) {
			addCriterion("qmc_qqid between", value1, value2, "qmcQqid");
			return (Criteria) this;
		}

		public Criteria andQmcQqidNotBetween(Long value1, Long value2) {
			addCriterion("qmc_qqid not between", value1, value2, "qmcQqid");
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

		public Criteria andQmcQquinIsNull() {
			addCriterion("qmc_qquin is null");
			return (Criteria) this;
		}

		public Criteria andQmcQquinIsNotNull() {
			addCriterion("qmc_qquin is not null");
			return (Criteria) this;
		}

		public Criteria andQmcQquinEqualTo(String value) {
			addCriterion("qmc_qquin =", value, "qmcQquin");
			return (Criteria) this;
		}

		public Criteria andQmcQquinNotEqualTo(String value) {
			addCriterion("qmc_qquin <>", value, "qmcQquin");
			return (Criteria) this;
		}

		public Criteria andQmcQquinGreaterThan(String value) {
			addCriterion("qmc_qquin >", value, "qmcQquin");
			return (Criteria) this;
		}

		public Criteria andQmcQquinGreaterThanOrEqualTo(String value) {
			addCriterion("qmc_qquin >=", value, "qmcQquin");
			return (Criteria) this;
		}

		public Criteria andQmcQquinLessThan(String value) {
			addCriterion("qmc_qquin <", value, "qmcQquin");
			return (Criteria) this;
		}

		public Criteria andQmcQquinLessThanOrEqualTo(String value) {
			addCriterion("qmc_qquin <=", value, "qmcQquin");
			return (Criteria) this;
		}

		public Criteria andQmcQquinLike(String value) {
			addCriterion("qmc_qquin like", value, "qmcQquin");
			return (Criteria) this;
		}

		public Criteria andQmcQquinNotLike(String value) {
			addCriterion("qmc_qquin not like", value, "qmcQquin");
			return (Criteria) this;
		}

		public Criteria andQmcQquinIn(List<String> values) {
			addCriterion("qmc_qquin in", values, "qmcQquin");
			return (Criteria) this;
		}

		public Criteria andQmcQquinNotIn(List<String> values) {
			addCriterion("qmc_qquin not in", values, "qmcQquin");
			return (Criteria) this;
		}

		public Criteria andQmcQquinBetween(String value1, String value2) {
			addCriterion("qmc_qquin between", value1, value2, "qmcQquin");
			return (Criteria) this;
		}

		public Criteria andQmcQquinNotBetween(String value1, String value2) {
			addCriterion("qmc_qquin not between", value1, value2, "qmcQquin");
			return (Criteria) this;
		}
	}

	/**
	 * qmc_qq
	 * 
	 * @ibatorgenerated do_not_delete_during_merge 2017-04-18 13:53:47
	 */
	public static class Criteria extends GeneratedCriteria {

		protected Criteria() {
			super();
		}
	}

	/**
	 * 内类部，系统内部调用1
	 * 
	 * @ibatorgenerated 2017-04-18 13:53:47
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