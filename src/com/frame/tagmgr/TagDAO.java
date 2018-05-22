package com.frame.tagmgr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.frame.db.DBUtil;
import com.yhl.f22.frame.utils.StringUtils;

public class TagDAO {

	/**
	 * 查询标签
	 * 
	 * @param gid
	 * @return
	 */
	public List<TagBean> queryTag(String gid) {
		List<TagBean> list = new ArrayList<TagBean>();
		// String sql =
		// "select tu.qmc_gid,t.* from qmc_tag_union tu LEFT JOIN qmc_tag t ON tu.qmc_tid=t.qmc_tid where tu.qmc_gid=?";
		String sql = "select qmc_tid,qmc_tag_name,qmc_create_uid,qmc_create_time,qmc_remark from qmc_tag";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql);
			// pstmt.setString(1, gid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				TagBean TagBean = new TagBean();
				// TagBean.setQmcGId(rs.getInt(1));
				TagBean.setQmcTId(rs.getInt(1));
				TagBean.setQmcTagName(rs.getString(2));
				TagBean.setQmcCreateUId(rs.getInt(3));
				TagBean.setQmcCreateTime(rs.getInt(4));
				TagBean.setQmcRemark(rs.getString(5));
				list.add(TagBean);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, pstmt, rs);
		}

		return list;
	}

	/**
	 * 查询关联表中是否存在账号记录
	 * 
	 * @param gid
	 * @param tid
	 * @return
	 */
	public String queryTagById(String gid, String tid) {
		String flag = "1";
		String sql = "select count(1) from qmc_tag_union where qmc_tid=? and qmc_gid =?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(tid));
			pstmt.setInt(2, Integer.parseInt(gid));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt(1) > 0) {
					flag = "0";

				} else {
					flag = "1";
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, pstmt, rs);
		}
		return flag;
	}

	/**
	 * 新增标签
	 * 
	 * @param gid
	 * @param tagName
	 * @return
	 */
	public String addTag(String uid, String tagName) {
		String flag = "";
		String sql1 = "insert into qmc_tag (qmc_tag_name,qmc_create_uid,qmc_create_time,qmc_remark) VALUES(?,?,?,?)";
		String remark = null;
		int createTime = new Long(System.currentTimeMillis() / 1000L).intValue();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, tagName);
			pstmt.setInt(2, Integer.parseInt(uid));
			pstmt.setInt(3, createTime);
			pstmt.setString(4, remark);
			int n = pstmt.executeUpdate();
			if (n == 1) {
				System.out.println("\\\\\\\\\\数据库插入成功");
				flag = "1";

			} else {
				System.out.println("\\\\\\\\\\数据库插入失败");
				flag = "0";
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, pstmt, rs);
		}
		return flag;

	}

	/**
	 * 新增标签关联
	 * 
	 * @param gid
	 * @param uid
	 * @return
	 */
	public String addTagU(String tids, String gids, String uid) {
		String flag = "";
		String sql2 = "insert into qmc_tag_union(qmc_tid,qmc_gid,qmc_create_uid,qmc_create_time,qmc_remark) VALUES(?,?,?,?,?)";
		String remark = null;
		int createTime = new Long(System.currentTimeMillis() / 1000L).intValue();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String[] gidArray = gids.split(",");
		String[] tidArray = tids.split(",");
		Boolean delSuc = deleteUTag(gids);
		for (int j = 0; j < gidArray.length; j++) {
			String gid = gidArray[j];
			if(!StringUtils.isEmpty(tids)){
			for (int i = 0; i < tidArray.length; i++) {
				String tid = tidArray[i];
				try {
					conn = DriverManager.getConnection("proxool.f22_pool");
					conn.setAutoCommit(false);
				
					if (delSuc) {
						pstmt = conn.prepareStatement(sql2);
						pstmt.setInt(1, Integer.parseInt(tid));
						pstmt.setInt(2, Integer.parseInt(gid));
						pstmt.setInt(3, Integer.parseInt(uid));
						pstmt.setInt(4, createTime);
						pstmt.setString(5, remark);
						int n = pstmt.executeUpdate();
						if (n == 1) {
							System.out.println("\\\\\\\\\\数据库插入成功");
							flag = "1";

						} else {
							System.out.println("\\\\\\\\\\数据库插入失败");
							flag = "0";
						}
					} else {
						delSuc = false;
					}

					if (delSuc&&flag.equals("1")) {
						conn.commit();
						conn.setAutoCommit(true);
					}

				} catch (SQLException e) {

					e.printStackTrace();
					if (conn != null) {
						try {
							conn.rollback();
							conn.setAutoCommit(true);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}

				} finally {
					DBUtil.closeDB(conn, pstmt, rs);
				}
			}
			} else{
				System.out.println("\\\\\\清空当前标签");
			}

		}
		return flag;
	}

	/**
	 * 新增标签之前先删除之前的标签
	 * 
	 * @param gids
	 * @return
	 */
	public Boolean deleteUTag(String gids) {
		Boolean flag = true;
		String sql1 = "delete from qmc_tag_union where qmc_gid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String[] gidArray = gids.split(",");
		for (int j = 0; j < gidArray.length; j++) {
			String gid = gidArray[j];
			try {
				conn = DriverManager.getConnection("proxool.f22_pool");
				pstmt = conn.prepareStatement(sql1);
				pstmt.setInt(1, Integer.parseInt(gid));
				int n = pstmt.executeUpdate();
				System.out.println("\\\\\\\\删除"+n+"条记录");
				/*if (n == 1) {
					System.out.println("\\\\\\\\\\删除标签成功");
					flag = true;

				} else {
					System.out.println("\\\\\\\\\\删除标签失败");
					flag = false;
				}*/
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBUtil.closeDB(conn, pstmt, rs);
			}
		}
		return flag;

	}

	/**
	 * 根据用户名查询用户ID
	 * 
	 * @param username
	 * @return
	 */
	public String queryUserId(String username) {
		String sql = "select qmc_uid from qmc_users where qmc_uname=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String userId = "";
		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				userId = rs.getString(1);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, pstmt, rs);
		}

		return userId;
	}

	/**
	 * 查询标签是否存在
	 * @return
	 */
	public Boolean queryTagExist(String tagName) {
		Boolean flag =true;
		String sql ="select count(1) from qmc_tag where qmc_tag_name =?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tagName);
			rs = pstmt.executeQuery();
			int count =0;
			while (rs.next()) {
				count = rs.getInt(1);
			}
			if(count>0){
				flag=false;
			}else{
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closeDB(conn, pstmt, rs);
		}
		return flag;
	}

}
