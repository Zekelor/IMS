/**
 * Copyright
 */
package com.yhl.f22.frame.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.yhl.f22.frame.db.dao.impl.DynamicSQLTemplate;

/**
 * <pre>
 * </pre>
 * 
 * @author Administrator
 * @date 2017年4月18日上午11:53:30
 * @since JDK 1.7
 * @since
 */
public abstract class AbstractDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	/**
	 * 获取当前操作对象
	 * 
	 * @return 当前mybatis操作对象
	 */
	public SqlSessionTemplate getSqlMapClient() {
		return this.sqlSessionTemplate;
	}

	/**
	 * 获取动态sql模板
	 * 
	 * @return
	 */
	public final IDynamicSQLTemplate getDynamicSQLTemplate() {
		return (IDynamicSQLTemplate) new DynamicSQLTemplate(this.sqlSessionTemplate);
	}

	/**
	 * 将mybatis查询出的Object list转换为具体的entity list
	 * 
	 * @param objList
	 *            Object list
	 * @param t
	 *            需要转换成的entity class
	 * @return 转换完成后的entity list
	 */
	protected <T> List<T> castObjectToEntity(List<Object> objList, Class<T> t) {
		List<T> resultList = null;
		if (objList != null && objList.size() > 0) {
			resultList = new ArrayList<T>(objList.size());
			for (Object temp : objList) {
				resultList.add(t.cast(temp));
			}// end for
		}// end if

		return resultList;
	}
}
