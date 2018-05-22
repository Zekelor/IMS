package com.frame.reportmgr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.frame.db.DBUtil;

public class ReportDAO {

	/**
	 * 查询记录数量
	 * 
	 * @param pageSize
	 * @param startRecord
	 * @param searchValue
	 * @return
	 */
	public int count(int pageSize, int startRecord, String searchValue) {
		int count = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) from qmc_report qr ");
		// for search
		String[] columnsName = { "qr.qmc_report_name", "qr.qmc_report_desc" };
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

	/**
	 * 查询记录列表
	 * 
	 * @param pageSize
	 * @param startRecord
	 * @param sortColumn
	 * @param sortDir
	 * @param searchValue
	 * @return
	 */
	public List<ReportBean> queryDataList(int pageSize, int startRecord, String sortColumn, String sortDir, String searchValue) {
		List<ReportBean> results = new ArrayList<ReportBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("select qr.qmc_rid,qr.qmc_report_name,qr.qmc_report_desc,qr.qmc_report_filepath,qr.qmc_creater,qu.qmc_role,");
		sql.append("FROM_UNIXTIME(qr.qmc_create_time,'%Y-%m-%d %H:%i:%s') qmc_create_time,FROM_UNIXTIME(qr.qmc_update_time,'%Y-%m-%d %H:%i:%s') qmc_update_time from qmc_report qr ");
		sql.append(" LEFT JOIN qmc_users qu ON qr.qmc_creater=qu.qmc_uname ");

		// for search
		String[] columnsName = { "qr.qmc_report_name", "qr.qmc_report_desc" };
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
				ReportBean ReportBean = new ReportBean();
				ReportBean.setQmcRId(rs.getInt(1));
				ReportBean.setQmcReportName(rs.getString(2));
				ReportBean.setQmcReportDesc(rs.getString(3));
				ReportBean.setQmcReportFilePath(rs.getString(4));
				ReportBean.setQmcCreater(rs.getString(5));
				ReportBean.setQmcRole(rs.getString(6));
				ReportBean.setQmcCreateTime(rs.getString(7));
				ReportBean.setQmcUpdateTime(rs.getString(8));
				results.add(ReportBean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, pstmt, rs);
		}

		return results;
	}

	/**
	 * 添加报告
	 * 
	 * @param bean
	 * @return
	 */
	public String save(ReportBean bean) {
		String sql = "insert into qmc_report (qmc_report_name,qmc_report_desc,qmc_report_filepath,qmc_creater,qmc_create_time,qmc_update_time) values(?,?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String flag = "1";

		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getQmcReportName());
			pstmt.setString(2, bean.getQmcReportDesc());
			pstmt.setString(3, bean.getQmcReportFilePath());
			pstmt.setString(4, bean.getQmcCreater());
			pstmt.setString(5, bean.getQmcCreateTime());
			pstmt.setString(6, bean.getQmcUpdateTime());

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
	 * 查询报告名称是否存在
	 * 
	 * @param reportName
	 * @return
	 */
	public Boolean queryReportNameExist(String reportName) {
		Boolean flag = true;
		String sql = "select count(1) from qmc_report where qmc_report_name =?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reportName);
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

	/**
	 * 查询报告名称是否存在
	 * 
	 * @param reportName
	 * @return
	 */
	public Boolean queryReportNameExist(String reportName, String reportRid) {
		Boolean flag = true;
		String sql = "select count(1) from qmc_report where qmc_rid <>? and  qmc_report_name =?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(reportRid));
			pstmt.setString(2, reportName);
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

	/**
	 * 根据ID查询账号记录
	 * 
	 * @param id
	 * @return
	 */
	public List<ReportBean> queryDataById(String id) {
		String sql = "select qr.qmc_rid,qr.qmc_report_name,qr.qmc_report_desc,qr.qmc_report_filepath,qr.qmc_creater from qmc_report qr where qr.qmc_rid= ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReportBean> list = new ArrayList<ReportBean>();
		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(id));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReportBean report = new ReportBean();
				report.setQmcRId(rs.getInt(1));
				report.setQmcReportName(rs.getString(2));
				report.setQmcReportDesc(rs.getString(3));
				report.setQmcReportFilePath(rs.getString(4));
				report.setQmcCreater(rs.getString(5));
				list.add(report);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DBUtil.closeDB(conn, pstmt, rs);
		}
		return list;
	}

	/**
	 * 更新记录
	 * 
	 * @param bean
	 * @return
	 */
	public String update(ReportBean bean) {
		String sql = "update qmc_report set qmc_report_name=?,qmc_report_desc=?,qmc_report_filepath=?,qmc_update_time=? where qmc_rid =? ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String flag = "1";

		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bean.getQmcReportName());
			pstmt.setString(2, bean.getQmcReportDesc());
			pstmt.setString(3, bean.getQmcReportFilePath());
			pstmt.setString(4, bean.getQmcUpdateTime());
			pstmt.setInt(5, bean.getQmcRId());

			System.out.println("\\\\\\\\插入语句:" + pstmt.toString());
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

	/**
	 * 删除记录
	 * 
	 * @param id
	 * @return
	 */
	public Boolean delete(String id) {
		Boolean flag = true;
		String sql = "delete from qmc_report where qmc_rid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, Integer.parseInt(id));

			int n = pstmt.executeUpdate();
			if (n > 0) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			DBUtil.closeDB(conn, pstmt, rs);
		}
		return flag;
	}

	public String queryReportById(String id) {
		
		String sql ="select qmc_report_filepath from qmc_report where qmc_rid =?";
		String filePath=null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, Integer.parseInt(id));
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				filePath=rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBUtil.closeDB(conn, pstmt, rs);
		}
		return filePath;
	}

}
