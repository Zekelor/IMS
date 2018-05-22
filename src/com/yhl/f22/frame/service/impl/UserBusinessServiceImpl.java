/**
 * @License:
 * @Copyright:
 */
package com.yhl.f22.frame.service.impl;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.yhl.f22.frame.db.dao.QmcUsersMapper;
import com.yhl.f22.frame.db.po.impl.QmcUsers;
import com.yhl.f22.frame.db.po.impl.QmcUsersExample;
import com.yhl.f22.frame.db.po.impl.QmcUsersWithBLOBs;
import com.yhl.f22.frame.service.IUserBusinessService;
import com.yhl.f22.frame.service.lang.ServiceException;
import com.yhl.f22.frame.utils.CollectionUtils;
import com.yhl.f22.frame.utils.MD5Util;

/**
 * <pre>
 * </pre>
 * 
 * @author Administrator
 * @date 2017年4月18日上午11:42:39
 * @since JDK 1.7
 * @since
 */
@Service("UserBusinessService")
@Scope("prototype")
public class UserBusinessServiceImpl implements IUserBusinessService {

	@Resource(name = "qmcUsersMapper")
	private QmcUsersMapper userDAO;

	/* (non-Javadoc)
	 * @see com.yhl.f22.frame.service.IUserBusinessService#userLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public int userLogin(String userName, String password) throws ServiceException {
		QmcUsersExample query = new QmcUsersExample();
		query.createCriteria().andQmcUnameEqualTo(userName);
		List<QmcUsers> userList = userDAO.selectByExample(query);
		
		if (CollectionUtils.isEmpty(userList)) {
			return 9998;
		}
		
		QmcUsers user = userList.get(0);
		/*String realPwd = user.getQmcPwd();
		String inputPwd = MD5Util.string2MD5(new StringBuffer(password).append(user.getQmcAccessToken()).toString());*/
		
		/*String realPwd = user.getQmcAccessToken();
		String inputPwd = MD5Util.string2MD5(new StringBuffer(password).toString());*/
		String realPwd =user.getQmcPwd();
		String inputPwd =password;
		
		
		if (!realPwd.equals(inputPwd)) {
			return 9997;
		}
		
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.yhl.f22.frame.service.IUserBusinessService#addNewUser(com.yhl.f22.frame.db.po.impl.QmcUsers)
	 */
	@Override
	public int addNewUser(QmcUsers userInfo) throws ServiceException {
		String userName = userInfo.getQmcUname();
		QmcUsersExample query = new QmcUsersExample();
		query.createCriteria().andQmcUnameEqualTo(userName);
		List<QmcUsers> existsUserList = userDAO.selectByExample(query);

		if (CollectionUtils.isEmpty(existsUserList)) {
			int salt = new Random().nextInt(99999999);

			String password = new StringBuffer(userInfo.getQmcPwd()).append(salt).toString();
			password = MD5Util.string2MD5(password);

			QmcUsersWithBLOBs user = new QmcUsersWithBLOBs();
			user.setQmcAccessToken(String.valueOf(salt));
			user.setQmcNo(userInfo.getQmcNo());
			user.setQmcPwd(password);
			user.setQmcTel(userInfo.getQmcTel());
			user.setQmcUname(userInfo.getQmcUname());

			return userDAO.insertSelective(user);
		}
		else {
			throw new ServiceException();
		}
	}

	/* (non-Javadoc)
	 * @see com.yhl.f22.frame.service.IUserBusinessService#queryCount()
	 */
	@Override
	public int queryCount() throws ServiceException {
		QmcUsersExample query = new QmcUsersExample();
		return userDAO.countByExample(query);
	}

	/* (non-Javadoc)
	 * @see com.yhl.f22.frame.service.IUserBusinessService#queryAllUserList(int, int)
	 */
	@Override
	public List<QmcUsers> queryAllUserList(int pageSize, int pageNumber) throws ServiceException {
		QmcUsersExample query = new QmcUsersExample();
		query.setOrderByClause("qmc_uname asc");
		List<QmcUsers> userList = userDAO.selectByExampleAndPage(query, new RowBounds((pageNumber - 1) * pageSize, pageSize));
		return userList;
	}

}
