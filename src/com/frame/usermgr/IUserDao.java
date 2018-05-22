package com.frame.usermgr;

import java.util.List;

public interface IUserDao {
	public abstract UserBean save(UserBean bean) throws Exception;
	
	public abstract List<UserBean> query() throws Exception;
}
