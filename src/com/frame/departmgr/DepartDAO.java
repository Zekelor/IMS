package com.frame.departmgr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.frame.db.DBUtil;

public class DepartDAO {

	public int count(String searchValue) {
		int count = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) from qmc_department qd ");
		// For search
		String[] columnsName = { "qd.qmc_depart_name", "qd.qmc_depart_remark" };
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
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, pstmt, rs);
		}

		return count;
	}

	public List<DepartBean> queryDataList(int pageSize, int startRecord, String sortColumn, String sortDir, String searchValue) {
		List<DepartBean> results = new ArrayList<DepartBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("select qd.qmc_did,qd.qmc_depart_name,qd.qmc_depart_remark ,qd.qmc_creater,FROM_UNIXTIME(qd.qmc_create_time,'%Y-%m-%d %H:%i:%s') qmc_create_time");
		sql.append(" from qmc_department qd ");

		// For search
		String[] columnsName = { "qd.qmc_depart_name", "qd.qmc_depart_remark" };
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
				pstmt.setInt(1, startRecord);

			}
			System.out.println("\\\\\\\\页码数:" + pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DepartBean bean = new DepartBean();
				bean.setQmcDId(rs.getInt(1));
				bean.setQmcDName(rs.getString(2));
				bean.setQmcDRemark(rs.getString(3));
				bean.setQmcCreater(rs.getString(4));
				bean.setQmcCreateTime(rs.getString(5));
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

	/**
	 * 添加部门
	 * 
	 * @param bean
	 * @return
	 */
	public String save(DepartBean bean) {
		String sql = "insert into qmc_department (qmc_depart_name,qmc_depart_remark,qmc_creater,qmc_create_time) values(?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String flag = "0";

		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getQmcDName());
			pstmt.setString(2, bean.getQmcDRemark());
			pstmt.setString(3, bean.getQmcCreater());
			pstmt.setString(4, bean.getQmcCreateTime());

			int n = pstmt.executeUpdate();
			if (n == 1) {
				System.out.println("\\\\\\\\\\数据库插入成功");
				flag = "1";

			} else {
				System.out.println("\\\\\\\\\\数据库插入失败");
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
	 * 查询是否存在
	 * 
	 * @param departName
	 * @return
	 */
	public Boolean queryDepartExist(String departName) {
		Boolean flag = true;
		String sql = "select count(1) from qmc_department where qmc_depart_name =?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, departName);
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
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, pstmt, rs);
		}
		return flag;
	}

	public List<DepartBean> queryDataById(String id) {
		List<DepartBean> results = new ArrayList<DepartBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("select qd.qmc_depart_name,qd.qmc_depart_remark ,qd.qmc_creater,FROM_UNIXTIME(qd.qmc_create_time,'%Y-%m-%d %H:%i:%s') qmc_create_time");
		sql.append(" from qmc_department qd where qd.qmc_did =?");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, Integer.parseInt(id));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DepartBean bean = new DepartBean();
				bean.setQmcDName(rs.getString(1));
				bean.setQmcDRemark(rs.getString(2));
				bean.setQmcCreater(rs.getString(3));
				bean.setQmcCreateTime(rs.getString(4));
				results.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DBUtil.closeDB(conn, pstmt, rs);
		}
		return results;
	}

	public Boolean queryDepartExist(String id, String departName) {
		Boolean flag = true;
		String sql = "select count(1) from qmc_department where qmc_depart_name =? and qmc_did<>?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, departName);
			pstmt.setInt(2, Integer.parseInt(id));
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
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, pstmt, rs);
		}
		return flag;
	}

	public String update(DepartBean bean) {
		String sql = "update qmc_department set qmc_depart_name=? ,qmc_depart_remark=? where qmc_did=?";
		String flag = "0";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getQmcDName());
			pstmt.setString(2, bean.getQmcDRemark());
			pstmt.setInt(3, bean.getQmcDId());
			int n = pstmt.executeUpdate();

			if (n == 1) {
				System.out.println("\\\\\\\\\\数据库更新成功");
				flag = "1";

			} else {
				System.out.println("\\\\\\\\\\数据库更新失败");
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

	public List<DepartBean> queryDepart() {
		List<DepartBean> results = new ArrayList<DepartBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("select qd.qmc_did,qd.qmc_depart_name,qd.qmc_depart_remark ,qd.qmc_creater,FROM_UNIXTIME(qd.qmc_create_time,'%Y-%m-%d %H:%i:%s') qmc_create_time");
		sql.append(" from qmc_department qd  ");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DepartBean bean = new DepartBean();
				bean.setQmcDId(rs.getInt(1));
				bean.setQmcDName(rs.getString(2));
				bean.setQmcDRemark(rs.getString(3));
				bean.setQmcCreater(rs.getString(4));
				bean.setQmcCreateTime(rs.getString(5));
				results.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DBUtil.closeDB(conn, pstmt, rs);
		}
		return results;
	}

}
