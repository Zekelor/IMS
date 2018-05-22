package com.frame.feedbackmgr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.frame.db.DBUtil;
import com.yhl.f22.frame.utils.StringUtils;

public class FeedBackDAO {

	public int count(int pageSize, int startRecord, String searchValue) {
		int count = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) from qmc_feedback qf ");
		// for search
		String[] columnsName = { "qf.qmc_feedback_title", "qf.qmc_feedback_content" };
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

	public List<FeedBackBean> queryDataList(int pageSize, int startRecord, String sortColumn, String sortDir, String searchValue) {
		List<FeedBackBean> results = new ArrayList<FeedBackBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("select qf.qmc_fid,qf.qmc_feedback_title,qf.qmc_feedback_content,qf.qmc_feedback_filepath,qf.qmc_creater,qu.qmc_role,");
		sql.append("FROM_UNIXTIME(qf.qmc_create_time,'%Y-%m-%d %H:%i:%s') qmc_create_time,FROM_UNIXTIME(qf.qmc_update_time,'%Y-%m-%d %H:%i:%s') qmc_update_time from qmc_feedback qf");
		sql.append(" LEFT JOIN qmc_users qu ON qf.qmc_creater=qu.qmc_uname ");

		// for search
		String[] columnsName = { "qf.qmc_feedback_title", "qf.qmc_feedback_content" };
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
				FeedBackBean bean = new FeedBackBean();
				bean.setQmcFId(rs.getInt(1));
				bean.setQmcFdTitle(rs.getString(2));
				bean.setQmcFdContent(rs.getString(3));
				bean.setQmcFdFilePath(rs.getString(4));
				bean.setQmcCreater(rs.getString(5));
				bean.setQmcRole(rs.getString(6));
				bean.setQmcCreateTime(rs.getString(7));
				bean.setQmcUpdateTime(rs.getString(8));
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
	 * 查询反馈主题是否存在
	 * @param fdTitle
	 * @return
	 */
	public Boolean queryFdTitleExist(String fdTitle) {
		Boolean flag =true;
		String sql ="select count(1) from qmc_feedback where qmc_feedback_title = ?";
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fdTitle);
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
	 *  保存添加记录
	 * @param bean
	 * @return
	 */
	public String save(FeedBackBean bean) {
		String sql = "insert into qmc_feedback (qmc_feedback_title,qmc_feedback_content,qmc_feedback_filepath,qmc_creater,qmc_create_time,qmc_update_time) values(?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String flag = "1";

		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql);
			String filePath =(String)bean.getQmcFdFilePath();
			pstmt.setString(1, bean.getQmcFdTitle());
			pstmt.setString(2, bean.getQmcFdContent());
			if(!StringUtils.isEmpty(filePath)){
				pstmt.setString(3, bean.getQmcFdFilePath());
			}else{
				pstmt.setString(3, "");
			}
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
	 * 根据ID查询账号记录
	 * @param id
	 * @return
	 */
	public List<FeedBackBean> queryDataById(String id) {
		String sql = "select qf.qmc_fid,qf.qmc_feedback_title,qf.qmc_feedback_content,qf.qmc_feedback_filepath,qf.qmc_creater from qmc_feedback qf where qf.qmc_fid =?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<FeedBackBean> list = new ArrayList<FeedBackBean>();

		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(id));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				FeedBackBean fd = new FeedBackBean();
				fd.setQmcFId(rs.getInt(1));
				fd.setQmcFdTitle(rs.getString(2));
				fd.setQmcFdContent(rs.getString(3));
				fd.setQmcFdFilePath(rs.getString(4));
				fd.setQmcCreater(rs.getString(5));
				list.add(fd);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DBUtil.closeDB(conn, pstmt, rs);
		}
		return list;
	}
	
	/**
	 * 编辑时查询相关记录是否存在
	 * @param fdTitle
	 * @param fdFId
	 * @return
	 */
	public Boolean queryFdTitleExist(String fdTitle, String fdFId) {
		Boolean flag =true;
		String sql ="select count(1) from qmc_feedback where qmc_fid <>? and qmc_feedback_title =? ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(fdFId));
			pstmt.setString(2, fdTitle);
			rs=pstmt.executeQuery();
			int count =0;
			while(rs.next()){
				count =rs.getInt(1);
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
	 * 删除记录
	 * @param id
	 * @return
	 */
	public Boolean delete(String id) {
		Boolean flag = true;
		String sql = "delete from qmc_feedback where qmc_fid=?";
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
			e.printStackTrace();
		} finally {

			DBUtil.closeDB(conn, pstmt, rs);
		}
		return flag;
	}
	
	/**
	 * 来人把我的地址取出来
	 * @param id
	 * @return
	 */
	public String queryFeedBackById(String id) {
		String sql = "select qmc_feedback_filepath from qmc_feedback where qmc_fid =?";
		String filePath = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, Integer.parseInt(id));
			rs = pstmt.executeQuery();

			while (rs.next()) {
				filePath = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, pstmt, rs);
		}
		return filePath;
	}
	
	
	/**
	 * 居然还有更新这种操作
	 * @param bean
	 * @return
	 */
	public String update(FeedBackBean bean) {
		String sql = "update qmc_feedback set qmc_feedback_title=?,qmc_feedback_content=?,qmc_feedback_filepath=?,qmc_update_time=? where qmc_fid =?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String flag = "1";

		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bean.getQmcFdTitle());
			pstmt.setString(2, bean.getQmcFdContent());
			pstmt.setString(3, bean.getQmcFdFilePath());
			pstmt.setString(4, bean.getQmcUpdateTime());
			pstmt.setInt(5, bean.getQmcFId());

			System.out.println("\\\\\\\\更新反馈信息:" + pstmt.toString());
			int n = pstmt.executeUpdate();
			if (n == 1) {
				System.out.println("\\\\\\\\\\更新反馈信息成功");
				flag = "1";

			} else {
				System.out.println("\\\\\\\\\\更新反馈信息失败");
				flag = "0";
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, pstmt, rs);
		}

		return flag;
	}

}
