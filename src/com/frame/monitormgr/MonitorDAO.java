package com.frame.monitormgr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.frame.common.CommonConst;
import com.frame.db.DBUtil;
import com.frame.departmgr.DepartBean;
import com.frame.qqgroup.GroupBean;
import com.yhl.f22.frame.utils.StringUtils;

public class MonitorDAO {

	public int count(String searchValue) {
		int count = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) from qmc_qq qq ");
		// for search
		String[] columnsName = { "qq.qmc_qq" };
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

	public List<MonitorBean> queryDataList(int pageSize, int startRecord, String sortColumn, String sortDir, String searchValue) {
		List<MonitorBean> results = new ArrayList<MonitorBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("select qq.qmc_qqid,qq.qmc_qq,(SELECT count(1) from qmc_groups WHERE qmc_qq=qq.qmc_qq )groupcount,");
		sql.append("(select count(1) from qmc_msgs where qmc_from_qq =qq.qmc_qq and qmc_msg_date >UNIX_TIMESTAMP(DATE_SUB(CURDATE(),INTERVAL 1 DAY))) daycount,");
		sql.append("(select count(1) from qmc_msgs where qmc_from_qq =qq.qmc_qq and qmc_msg_date >UNIX_TIMESTAMP(DATE_SUB(CURDATE(),INTERVAL 1 WEEK))) weekcount");
		sql.append(" from qmc_qq qq");

		// for search
		String[] columnsName = { "qq.qmc_qq" };
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
				MonitorBean bean = new MonitorBean();
				bean.setQmcQqId(rs.getInt(1));
				bean.setQmcQQ(rs.getString(2));
				bean.setQmcGCount(rs.getInt(3));
				bean.setQmcDCount(rs.getInt(4));
				bean.setQmcWCount(rs.getInt(5));
				results.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, pstmt, rs);
		}

		return results;
	}

	public int queryGroupCount(String searchValue, String qmcQQ) {
		int count = 0;

		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) from ");
		sql.append(" ( SELECT g.qmc_gid,g.qmc_group,g.qmc_group_name,g.qmc_qq,L1.tids,L1.tn FROM ");
		sql.append(" qmc_groups g LEFT JOIN (select GROUP_CONCAT(qt.qmc_tid) tids,GROUP_CONCAT(qt.qmc_tag_name) tn,qtu.qmc_gid from qmc_tag qt left join qmc_tag_union qtu on qt.qmc_tid =qtu.qmc_tid GROUP BY qtu.qmc_gid )L1 ON L1.qmc_gid =g.qmc_gid where g.qmc_qq =? ");
		sql.append(" ) A");
		// for search
		String[] columnsName = { "A.qmc_group_name", "A.qmc_group", "A.tn", "A.qmc_qq" };
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
			pstmt.setString(1, qmcQQ);
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

	public List<GroupBean> queryGroupList(int pageSize, int startRecord, String sortColumn, String sortDir, String searchValue,
			String qmcQQ) {
		List<GroupBean> results = new ArrayList<GroupBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT B.*,");
		sql.append(" ( select count(1) from qmc_msgs where qmc_group =B.qmc_group and qmc_msg_date >UNIX_TIMESTAMP(DATE_SUB(CURDATE(),INTERVAL 1 DAY))) daycount,");
		sql.append(" ( select count(1) from qmc_msgs where qmc_group =B.qmc_group and qmc_msg_date >UNIX_TIMESTAMP(DATE_SUB(CURDATE(),INTERVAL 1 WEEK))) weekcount FROM");
		sql.append(" ( SELECT A.* FROM ");
		sql.append(" ( SELECT g.qmc_gid,g.qmc_group,g.qmc_group_name,g.qmc_qq,L1.tids,L1.tn FROM ");
		sql.append(" qmc_groups g LEFT JOIN (select GROUP_CONCAT(qt.qmc_tid) tids,GROUP_CONCAT(qt.qmc_tag_name) tn,");
		sql.append("qtu.qmc_gid from qmc_tag qt left join qmc_tag_union qtu on qt.qmc_tid =qtu.qmc_tid GROUP BY qtu.qmc_gid )L1 ON L1.qmc_gid =g.qmc_gid where g.qmc_qq=? ");
		sql.append(") A ");

		// for search
		String[] columnsName = { "A.qmc_group_name", "A.qmc_group", "A.tn", "A.qmc_qq" };
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
		sql.append(")B");

		System.out.println(sql.toString());
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = DriverManager.getConnection("proxool.f22_pool");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, qmcQQ);
			if (startRecord == 0) {
				pstmt.setInt(2, pageSize);
			} else {
				pstmt.setInt(3, pageSize);
				pstmt.setInt(2, startRecord);

			}
			System.out.println("\\\\\\\\页码数:" + pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GroupBean GroupBean = new GroupBean();
				GroupBean.setQmcGId(rs.getInt(1));
				GroupBean.setQmcGroup(rs.getString(2));
				GroupBean.setQmcGroupName(rs.getString(3));
				GroupBean.setQmcQQ(rs.getString(4));
				if (rs.getString(5) == null) {
					GroupBean.setQmcTagIds(" ");
				} else {
					GroupBean.setQmcTagIds(rs.getString(5));

				}
				if (rs.getString(6) == null) {
					GroupBean.setQmcTag("");
				} else {
					GroupBean.setQmcTag(rs.getString(6));
				}
				GroupBean.setQmcDayCount(rs.getInt(7));
				GroupBean.setQmcWeekCount(rs.getInt(8));

				results.add(GroupBean);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, pstmt, rs);
		}

		return results;
	}

	public int detailCount(String group, int pageSize, int startRecord, String searchValue) {
		int count = 0;

		StringBuffer sql = new StringBuffer("select count(1) from qmc_msgs m where qmc_group=? ");
		// for search
		String[] columnsName = { "m.qmc_msg" };
		boolean searchAble = false;
		if (searchValue != null && !"".equals(searchValue)) {
			sql.append("and ");
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
			pstmt.setString(1, group);
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

	public List<GroupBean> queryDetailList(String group, int pageSize, int startRecord, String sortColumn, String sortDir,
			String searchValue) {
		List<GroupBean> results = new ArrayList<GroupBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("select m.qmc_mid,m.qmc_sender,m.qmc_sender_nick,m.qmc_msg,FROM_UNIXTIME(m.qmc_msg_date,'%Y-%m-%d %H:%i:%s') qmc_msg_date from qmc_msgs m where m.qmc_group=?");

		// for search
		/*
		 * String[] columnsName = { "m.qmc_sender", "m.qmc_sender_nick",
		 * "m.qmc_msg", "m.qmc_msg_date" };
		 */
		String[] columnsName = { "m.qmc_msg" };

		boolean searchAble = false;
		if (searchValue != null && !"".equals(searchValue)) {
			sql.append("and ");
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
			pstmt.setString(1, group);
			if (startRecord == 0) {
				pstmt.setInt(2, pageSize);
			} else {
				pstmt.setInt(3, pageSize);
				pstmt.setInt(2, startRecord);

			}
			System.out.println("\\\\\\\\页码数:" + pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GroupBean GroupBean = new GroupBean();
				GroupBean.setQmcMId(rs.getInt(1));
				GroupBean.setQmcSender(rs.getString(2));
				GroupBean.setQmcSenderNick(rs.getString(3));
				GroupBean.setQmcMsg(rs.getString(4));
				GroupBean.setQmcMsgDate(rs.getString(5));
				results.add(GroupBean);
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
	 * 查询部门
	 * 
	 * @return
	 */
	public List<DepartBean> queryDepart() {
		List<DepartBean> list = new ArrayList<DepartBean>();
		String sql = "select qmc_did,qmc_depart_name,qmc_creater,qmc_create_time from qmc_department ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DepartBean bean = new DepartBean();
				bean.setQmcDId(rs.getInt(1));
				bean.setQmcDName(rs.getString(2));
				list.add(bean);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, pstmt, rs);
		}

		return list;
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
	 * 授权账号
	 * 
	 * @param dids
	 * @param qids
	 * @param uid
	 * @return
	 */
	public String monitorDepart(String dids, String qids, String uid) {
		String flag = "";
		String sql = "insert into qmc_qq_union(qmc_qqid,qmc_receive_gid,qmc_type,qmc_create_uid,qmc_create_time) values(?,?,?,?,?)";
		int createTime = new Long(System.currentTimeMillis() / 1000L).intValue();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String[] qidArray = qids.split(",");
		String[] didArray = dids.split(",");
		Boolean delSuc = deleteMDepart(qids);
		for (int j = 0; j < qidArray.length; j++) {
			String qid = qidArray[j];
			if (!StringUtils.isEmpty(dids)) {
				for (int i = 0; i < didArray.length; i++) {
					String did = didArray[i];
					try {
						conn = DriverManager.getConnection("proxool.f22_pool");
						conn.setAutoCommit(false);
						if (delSuc) {
							pstmt = conn.prepareStatement(sql);
							pstmt.setInt(1,Integer.parseInt(qid));
							pstmt.setInt(2, Integer.parseInt(did));
							pstmt.setInt(3, CommonConst.MONITOR_TYPE_DEPART);
							pstmt.setInt(4, Integer.parseInt(uid));
							pstmt.setInt(5, createTime);
							int n = pstmt.executeUpdate();
							if (n == 1) {
								System.out.println("\\\\\\\\\\\\数据库插入成功");
								flag = "1";
							} else {
								System.out.println("\\\\\\\\\\\\数据库插入失败");
								flag = "0";
							}

						} else {
							delSuc = false;
						}

						if (delSuc && flag.equals("1")) {
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
			} else {
				System.out.println("\\\\\\清空当前标签");
			}
		}

		return flag;
	}

	/**
	 * 删除之前授权记录
	 * 
	 * @param qids
	 * @return
	 */
	public Boolean deleteMDepart(String qids) {
		Boolean flag = true;
		String sql1 = "delete from qmc_qq_union where qmc_receive_gid =? ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String[] qidArray = qids.split(",");
		for (int j = 0; j < qidArray.length; j++) {
			String qid = qidArray[j];
			try {
				conn = DriverManager.getConnection("proxool.f22_pool");
				pstmt = conn.prepareStatement(sql1);
				pstmt.setInt(1, Integer.parseInt(qid));
				int n = pstmt.executeUpdate();
				System.out.println("\\\\\\删除qmc_qq_union表中" + n + "条记录");
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			} finally {
				DBUtil.closeDB(conn, pstmt, rs);
			}
		}

		return flag;
	}

}
