/**
 * @License:
 * @Copyright:
 */
package com.yhl.f22.frame.service;

import java.util.List;

import com.yhl.f22.frame.db.po.impl.QmcUsers;
import com.yhl.f22.frame.service.lang.ServiceException;

/**
 * <pre>
 * </pre>
 * 
 * @author Administrator
 * @date 2017年4月18日上午11:42:28
 * @since JDK 1.7
 * @since
 */
public interface IUserBusinessService {
	int userLogin(String userName, String password) throws ServiceException;
	
	int addNewUser(QmcUsers userInfo) throws ServiceException;
	
	int queryCount() throws ServiceException;
	
	List<QmcUsers> queryAllUserList(int pageSize, int pageNumber) throws ServiceException;
}
