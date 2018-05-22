package com.frame.usermgr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.frame.db.DBUtil;
import com.yhl.f22.frame.utils.StringUtils;

public class UserDAO {

	/**
	 * 添加用户
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public String InsertUser(String sql, Object[] params) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String flag = "1";
		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, StringUtils.toString(params[0]));
			pstmt.setString(2, StringUtils.toString(params[1]));
			pstmt.setString(3, StringUtils.toString(params[2]));
			pstmt.setString(4, StringUtils.toString(params[3]));
			pstmt.setString(5, StringUtils.toString(params[4]));
			pstmt.setInt(6, Integer.parseInt(params[5].toString()));
			pstmt.setString(7, StringUtils.toString(params[6]));
			// rs = pstmt.executeQuery();
			int n = pstmt.executeUpdate();
			if (n == 1) {
				System.out.println("\\\\\\\\\\数据库插入成功");
				flag = "1";

			} else {
				System.out.println("\\\\\\\\\\数据库插入失败");
				flag = "0";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, pstmt, null);
		}
		return flag;
	}

	public String UpdateUser(String sql, Object[] params) {
		String flag = "1";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, StringUtils.toString(params[0]));
			pstmt.setString(2, StringUtils.toString(params[1]));
			pstmt.setString(3, StringUtils.toString(params[2]));
			pstmt.setString(4, StringUtils.toString(params[3]));
			pstmt.setInt(5, Integer.parseInt(params[4].toString()));
			pstmt.setString(6, StringUtils.toString(params[5]));
			pstmt.setString(7, StringUtils.toString(params[6]));

			int n = pstmt.executeUpdate();

			if (n == 1) {
				System.out.println("\\\\\\\\\\更新用户成功");
				flag = "1";
			} else {
				System.out.println("\\\\\\\\\\更新用户失败");
				flag = "0";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, pstmt, rs);

		}

		return flag;
	}

	/**
	 * 查询用户
	 * 
	 * @param sql
	 */
	public List<UserBean> query(String sql) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<UserBean> list = new ArrayList<UserBean>();
		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				UserBean user = new UserBean();
				user.setQmcUid(rs.getInt(1));
				user.setQmcUname(rs.getString(2));
				user.setQmcPwd(rs.getString(3));
				user.setQmcNo(rs.getString(4));
				user.setQmcTel(rs.getString(5));
				user.setQmcAccessToken(rs.getString(6));
				user.setQmcRole(rs.getString(7));
				user.setQmcDepartment(rs.getString(8));
				list.add(user);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, pstmt, rs);
		}

		return list;

	}

	/**
	 * 查询用户对应的角色
	 * 
	 * @param sql
	 * @param username
	 * @return
	 */
	public String queryRoleType(String sql, String username) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String role = "";
		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				role = rs.getString(1);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, pstmt, rs);
		}

		return role;
	}

	/**
	 * d
	 * 
	 * @return
	 */
	public List<UserBean> queryDataById(String sql, String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<UserBean> list = new ArrayList<UserBean>();
		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				UserBean user = new UserBean();
				// user.setQmcUid(rs.getInt(1));
				user.setQmcUname(rs.getString(1));
				user.setQmcPwd(rs.getString(2));
				user.setQmcNo(rs.getString(3));
				user.setQmcTel(rs.getString(4));
				user.setQmcAccessToken(rs.getString(5));
				user.setQmcRole(rs.getString(6));
				user.setQmcDepartment(rs.getString(7));
				list.add(user);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, pstmt, rs);
		}

		return list;
	}

	/**
	 * 
	 * @param sql
	 * @param username
	 * @return
	 */
	public List<UserBean> queryDataByName(String sql, String username) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<UserBean> list = new ArrayList<UserBean>();
		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				UserBean user = new UserBean();
				user.setQmcUname(rs.getString(1));
				user.setQmcPwd(rs.getString(2));
				user.setQmcNo(rs.getString(3));
				user.setQmcTel(rs.getString(4));
				user.setQmcAccessToken(rs.getString(5));
				user.setQmcRole(rs.getString(6));
				user.setQmcDepartment(rs.getString(7));
				list.add(user);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, pstmt, rs);
		}

		return list;
	}

	/**
	 * 删除用户
	 * 
	 * @param sql
	 * @param id
	 */
	public String delete(String sql, String id) {
		String flag = "1";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			int n = pstmt.executeUpdate();

			if (n == 1) {
				System.out.println("\\\\\\\\\\删除用户成功");
				flag = "1";
			} else {
				System.out.println("\\\\\\\\\\删除用户失败");
				flag = "0";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, pstmt, rs);
		}

		return flag;
	}

	/**
	 * 查询用户名是否存在
	 * 
	 * @param userName
	 * @return
	 */
	public Boolean queryUserNameExist(String userName) {
		Boolean flag = true;
		String sql = "select count(1) from qmc_users where qmc_uname =?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			rs = pstmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				flag = false;
			} else {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, pstmt, rs);
		}
		return flag;
	}

	/**
	 * 根据ID查询department名称
	 * 
	 * @param qmcDepartment
	 * @return
	 */
	public String queryDepartById(String qmcDepartment) {
		String sql = "select qmc_depart_name from qmc_department where qmc_did =?";
		String qmc_depart_name = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(qmcDepartment));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				qmc_depart_name = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, pstmt, rs);
		}

		return qmc_depart_name;
	}

	/**
	 * 根据登录用户名查询所在部门ID
	 * 
	 * @param username
	 * @return
	 */
	public String queryDIdByname(String sql, String username) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String qmcDId = "";
		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			System.out.println("\\\\\\\\页码数:"+pstmt);
			rs = pstmt.executeQuery();
			if(rs.isAfterLast()==rs.isBeforeFirst()){
				qmcDId="";
			}else{
				while (rs.next()) {
					qmcDId = String.valueOf(rs.getInt(1));
				}
			}
			
			
			System.out.println("qmcDId:"+qmcDId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, pstmt, rs);

		}

		return qmcDId;
	}

}
