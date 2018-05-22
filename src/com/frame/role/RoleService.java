package com.frame.role;

import com.frame.usermgr.UserDAO;

public class RoleService {
	UserDAO dao = new UserDAO();

	/**
	 * 查询用户角色
	 * 
	 * @param username
	 * @return
	 */
	public String queryRoleType(String username) {

		String sql = "select qmc_role from qmc_users where qmc_uname=?";

		return dao.queryRoleType(sql, username);
	}

	/**
	 * 查询用户所在部门ID
	 * 
	 * @param username
	 * @return
	 */
	public String queryDepartment(String username) {

		String sql = "SELECT qd.qmc_did from qmc_users qu LEFT JOIN qmc_department qd ON qu.qmc_department =qd.qmc_depart_name WHERE qu.qmc_uname =? ";

		return dao.queryDIdByname(sql, username);
	}

}
