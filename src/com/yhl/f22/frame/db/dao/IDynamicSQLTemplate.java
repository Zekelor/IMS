/**
 * 
 */
package com.yhl.f22.frame.db.dao;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * </pre>
 * 
 * @author Administrator
 * @date 2017年4月18日上午11:54:21
 * @since JDK 1.7
 * @since
 */
public interface IDynamicSQLTemplate {
	/**
	 * 设置动态sql，要求sql为普通的mybatis sql即可
	 * 如 ： SELECT JOB_NAME FROM QRTZ_JOB_DETAILS WHERE SCHED_NAME=#{schedName}
	 * 
	 * @param sql
	 *            动态sql语句
	 */
	public void setDynamicSql(String sql);

	/**
	 * <pre>
	 * 设置sql使用的参数
	 * </pre>
	 * 
	 * @param parameterMap
	 */
	public void setParameters(Map<String, Object> parameterMap);

	/**
	 * 设置sql使用参数
	 * 
	 * @param parameterKey
	 *            参数名称
	 * @param parameterValue
	 *            参数值
	 */
	public void addParameter(String parameterKey, Object parameterValue);

	/**
	 * 重置当前动态模板，将清除设置的sql和参数
	 */
	public void reset();

	/**
	 * 查询单个对象
	 * 
	 * @return
	 */
	public Map<String, Object> selectOne();

	/**
	 * 执行列表查询
	 * 
	 * @return
	 */
	public List<Map<String, Object>> selectList();

	/**
	 * 执行新增操作
	 * 
	 * @return
	 */
	public int insert();

	/**
	 * 执行更新
	 * 
	 * @return
	 */
	public int update();
}
