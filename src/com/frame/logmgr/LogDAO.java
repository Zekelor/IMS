package com.frame.logmgr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.frame.db.DBUtil;

public class LogDAO {

	/**
	 * 查询账号数量
	 * 
	 * @param pageSize
	 * @param startRecord
	 * @param searchValue
	 * @return
	 */
	public int count(int pageSize, int startRecord, String searchValue) {
		int count = 0;

		StringBuffer sql = new StringBuffer("select count(1) from qmc_log  ");
		// for search
		String[] columnsName = { "qmc_ip", "qmc_qq" };
		boolean searchAble = false;
		if (searchValue != null && !"".equals(searchValue)) {
			sql.append(" where ");
			searchAble = true;
		}

		if (searchAble) {
			StringBuffer temp = new StringBuffer();
			for (String column : columnsName) {
				temp.append(column + " like '%" + searchValue + "%' or ");
			}
			sql.append(temp.substring(0, temp.length() - 3));
		}

		System.out.println(sql.toString());
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, pstmt, rs);
		}

		return count;
	}

	/**
	 * 查询账号列表
	 * 
	 * @param pageSize
	 * @param startRecord
	 * @param sortOrder
	 * @param sortDir
	 * @param searchValue
	 * @return
	 */
	public List<LogBean> queryDataList(int pageSize, int startRecord, String sortColumn, String sortDir, String searchValue) {
		List<LogBean> results = new ArrayList<LogBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("select qmc_log_id,qmc_no,qmc_qq,qmc_ip,FROM_UNIXTIME(qmc_last_date,'%Y-%m-%d %H:%i:%s') qmc_last_date from qmc_log");

		// for search
		String[] columnsName = { "qmc_ip", "qmc_qq" };
		boolean searchAble = false;
		if (searchValue != null && !"".equals(searchValue)) {
			sql.append(" where ");
			searchAble = true;
		}

		if (searchAble) {
			StringBuffer temp = new StringBuffer();
			for (String column : columnsName) {
				temp.append(column + " like '%" + searchValue + "%' or ");
			}
			sql.append(temp.substring(0, temp.length() - 3));
		}

		// for order
		sql.append(" order by " + sortColumn + " " + sortDir + " ");

		// for pagination
		if (startRecord == 0) {
			sql.append(" limit ?");
		} else {
			sql.append(" limit ?,? ");

		}
		System.out.println(sql.toString());
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection("proxool.f22_pool");

			pstmt = conn.prepareStatement(sql.toString());
			if (startRecord == 0) {
				pstmt.setInt(1, pageSize);
			} else {
				pstmt.setInt(2, pageSize);
				pstmt.setInt(1, startRecord + 1);

			}
			System.out.println("\\\\\\\\页码数:" + pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				LogBean LogBean = new LogBean();
				LogBean.setQmcLogId(rs.getInt(1));
				LogBean.setQmcNo(rs.getString(2));
				LogBean.setQmcQQ(rs.getString(3));
				LogBean.setQmcIp(rs.getString(4));
				LogBean.setQmcLastDate(rs.getString(5));
				results.add(LogBean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, pstmt, rs);
		}

		return results;
	}

	/**
	 * 写入日志
	 * 
	 * @param logBean
	 */
	public static void writeSysLog(LogBean logBean) {
		String sql = "insert into qmc_logtable (qmc_login_name,qmc_login_ip,qmc_description,qmc_last_date) values(?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, logBean.getQmcLoginName());
			pstmt.setString(2, logBean.getQmcLoginIp());
			pstmt.setString(3, logBean.getQmcDesc());
			pstmt.setString(4,logBean.getQmcLastDate());
			int n = pstmt.executeUpdate();
			if (n == 1) {
				System.out.println("\\\\\\\\\\插入日志成功");
			} else {
				System.out.println("\\\\\\\\\\插入日志失败");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, pstmt, rs);
		}

	}

	public int count2(int pageSize, int startRecord, String searchValue) {
		int count = 0;

		StringBuffer sql = new StringBuffer("select count(1) from qmc_logtable  ");
		// for search
		String[] columnsName = { "qmc_login_name", "qmc_login_ip" };
		boolean searchAble = false;
		if (searchValue != null && !"".equals(searchValue)) {
			sql.append(" where ");
			searchAble = true;
		}

		if (searchAble) {
			StringBuffer temp = new StringBuffer();
			for (String column : columnsName) {
				temp.append(column + " like '%" + searchValue + "%' or ");
			}
			sql.append(temp.substring(0, temp.length() - 3));
		}

		System.out.println(sql.toString());
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, pstmt, rs);
		}

		return count;
	}

	public List<LogBean> queryDataList2(int pageSize, int startRecord, String sortColumn, String sortDir, String searchValue) {
		List<LogBean> results = new ArrayList<LogBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("select qmc_lid,qmc_login_name,qmc_description,qmc_login_ip,FROM_UNIXTIME(qmc_last_date,'%Y-%m-%d %H:%i:%s') qmc_last_date from qmc_logtable ");

		// for search
		String[] columnsName = { "qmc_login_name", "qmc_login_ip" };
		boolean searchAble = false;
		if (searchValue != null && !"".equals(searchValue)) {
			sql.append(" where ");
			searchAble = true;
		}

		if (searchAble) {
			StringBuffer temp = new StringBuffer();
			for (String column : columnsName) {
				temp.append(column + " like '%" + searchValue + "%' or ");
			}
			sql.append(temp.substring(0, temp.length() - 3));
		}

		// for order
		sql.append(" order by " + sortColumn + " " + sortDir + " ");

		// for pagination
		if (startRecord == 0) {
			sql.append(" limit ?");
		} else {
			sql.append(" limit ?,? ");

		}
		System.out.println(sql.toString());
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection("proxool.f22_pool");

			pstmt = conn.prepareStatement(sql.toString());
			if (startRecord == 0) {
				pstmt.setInt(1, pageSize);
			} else {
				pstmt.setInt(2, pageSize);
				pstmt.setInt(1, startRecord + 1);

			}
			System.out.println("\\\\\\\\页码数:" + pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				LogBean bean = new LogBean();
				bean.setQmcLId(rs.getInt(1));
				bean.setQmcLoginName(rs.getString(2));
				bean.setQmcDesc(rs.getString(3));
				bean.setQmcLoginIp(rs.getString(4));
				bean.setQmcLastDate(rs.getString(5));
				results.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, pstmt, rs);
		}

		return results;
	}

}
