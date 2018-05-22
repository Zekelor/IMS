/**
 * 
 */
package com.yhl.f22.frame.db.dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.mybatis.spring.SqlSessionTemplate;

import com.yhl.f22.frame.db.dao.IDynamicSQLTemplate;
import com.yhl.f22.frame.db.lang.DAOException;

/**
 * <pre>
 * </pre>
 * 
 * @author Administrator
 * @date 2017年4月18日上午11:55:17
 * @since JDK 1.7
 * @since
 */
public class DynamicSQLTemplate implements IDynamicSQLTemplate {
	/**
	 * mybatis sql模板
	 */
	private SqlSessionTemplate sqlSessionTemplate;

	/**
	 * @param sqlSessionTemplate
	 */
	public DynamicSQLTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	private String dynamicSql;// 输入的sql语句

	private String sql;// 输出的sql语句

	private Map<String, Object> parameters = new HashMap<String, Object>();// 输出使用的参数

	/**
	 * @return the sql
	 */
	public String getSql() {
		return sql;
	}

	/**
	 * @param sql
	 *            the sql to set
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}

	/**
	 * @return the parameters
	 */
	public Map<String, Object> getParameters() {
		return parameters;
	}

	/**
	 * @param parameters
	 *            the parameters to set
	 */
	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	/**
	 * 设置动态sql，要求sql为普通的mybatis sql即可
	 * 如 ： SELECT JOB_NAME FROM QRTZ_JOB_DETAILS WHERE SCHED_NAME=#{schedName}
	 * 
	 * @param sql
	 *            动态sql语句
	 */
	public void setDynamicSql(String sql) {
		this.dynamicSql = sql;
	}

	/**
	 * 设置sql使用参数
	 * 
	 * @param parameter
	 */
	public void addParameter(String parameterKey, Object parameterValue) {
		parameters.put(parameterKey, parameterValue);
	}

	/**
	 * 拼装mysql使用的对象
	 * 
	 * @return
	 */
	private void getCompleteSQL() {
		if (dynamicSql == null || dynamicSql.length() < 1) {
			throw new DAOException("dynamicSql expect not null but get : " + this.dynamicSql);
		}
		StringBuilder sb = new StringBuilder(dynamicSql);
		if (parameters.size() > 0) {
			Set<Entry<String, Object>> tempSet = parameters.entrySet();
			Iterator<Entry<String, Object>> tempIterator = tempSet.iterator();
			while (tempIterator.hasNext()) {
				Entry<String, Object> entry = tempIterator.next();
				String replaceFlag = "#{" + entry.getKey() + "}";
				while (sb.indexOf(replaceFlag) != -1) {
					sb.replace(sb.indexOf(replaceFlag), sb.indexOf(replaceFlag) + replaceFlag.length(), "#{parameters." + entry.getKey() + "}");
				}// end repeat parameter replace loop
			}// end all parameter replace loop
		}
		sql = sb.toString();
		dynamicSql = null;
	}

	/**
	 * 重置当前动态模板，将清除设置的sql和参数
	 */
	public void reset() {
		this.dynamicSql = null;
		this.sql = null;
		this.parameters.clear();
	}

	/**
	 * 执行单个对象查询
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectOne() {
		getCompleteSQL();
		return (Map<String, Object>) this.sqlSessionTemplate.selectOne("com.dlw.sap.db.DynamicSQLTemplate.dynamicSql", this);
	}

	/**
	 * 执行列表查询
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectList() {
		getCompleteSQL();
		return (List<Map<String, Object>>) this.sqlSessionTemplate.selectList("com.dlw.sap.db.DynamicSQLTemplate.dynamicSql", this);
	}

	/**
	 * 执行新增操作
	 * 
	 * @return
	 */
	public int insert() {
		getCompleteSQL();
		return this.sqlSessionTemplate.insert("com.dlw.sap.db.DynamicSQLTemplate.dynamicSql", this);
	}

	/**
	 * 执行更新
	 * 
	 * @return
	 */
	public int update() {
		getCompleteSQL();
		return this.sqlSessionTemplate.update("com.dlw.sap.db.DynamicSQLTemplate.dynamicSql", this);
	}
}
