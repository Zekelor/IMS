package com.frame.querymgr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.frame.db.DBUtil;
import com.frame.qqgroup.GroupBean;
import com.yhl.f22.frame.utils.StringUtils;

public class QueryDAO {

	public String sqlWhere(String account, String group, String msg) {
		StringBuffer sbWhere = new StringBuffer();
		if (!StringUtils.isEmpty(account)) {
			sbWhere.append(" m.qmc_sender like '%").append(account).append("%' or ").append("m.qmc_from_qq like '").append(account).append("%' and ");
		}
		if (!StringUtils.isEmpty(group)) {
			sbWhere.append(" m.qmc_group like '%").append(group).append("%' and ");
		}
		if (!StringUtils.isEmpty(msg)) {
			sbWhere.append(" m.qmc_msg like '%").append(msg).append("%' and ");
		}

		return sbWhere.toString();

	}

	public String sqlWhere2(String account, String group) {
		StringBuffer sbWhere = new StringBuffer();
		if (!StringUtils.isEmpty(account)) {
			sbWhere.append(" A.qmc_qq like '%").append(account).append("%' and ");
		}
		if (!StringUtils.isEmpty(group)) {
			sbWhere.append(" A.qmc_group like '%").append(group).append("%' and ");
		}

		return sbWhere.toString();

	}

	public int queryMsgCount(String[] searchValue) {
		int count = 0;

		StringBuffer sql = new StringBuffer("select count(1) from qmc_msgs m where 1=1 and (");
		StringBuffer temp =new StringBuffer();
		temp.append(sqlWhere(searchValue[0], searchValue[1], searchValue[2]));
		sql.append(temp.substring(0, temp.length() -4 ));
		sql.append(" )");

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
	
	public List<GroupBean> queryMsgList(int pageSize, int startRecord, String sortColumn, String sortDir, String[] searchValue) {
		List<GroupBean> results = new ArrayList<GroupBean>();
		StringBuffer sql = new StringBuffer();
			sql.append("select m.qmc_mid,m.qmc_sender,m.qmc_sender_nick,m.qmc_msg,m.qmc_group,m.qmc_from_qq,");
			sql.append("(SELECT GROUP_CONCAT(qmc_group_name) from qmc_groups WHERE qmc_group=m.qmc_group) qmc_group_name, ");
			sql.append("FROM_UNIXTIME(m.qmc_msg_date,'%Y-%m-%d %H:%i:%s') qmc_msg_date ");
			sql.append("from qmc_msgs m  where 1=1 and ( ");
			StringBuffer temp =new StringBuffer();
			temp.append(sqlWhere(searchValue[0], searchValue[1],searchValue[2]));
			sql.append(temp.substring(0, temp.length() -4 ));
			sql.append(" ) ");
			
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
				if(startRecord==0){
					pstmt.setInt(1,pageSize);
				}else{
					pstmt.setInt(2, pageSize);
					pstmt.setInt(1, startRecord);
					
				}
				System.out.println("\\\\\\\\页码数:"+pstmt);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					GroupBean GroupBean = new GroupBean();
					GroupBean.setQmcMId(rs.getInt(1));
					GroupBean.setQmcSender(rs.getString(2));
					GroupBean.setQmcSenderNick(rs.getString(3));
					GroupBean.setQmcMsg(rs.getString(4));
					GroupBean.setQmcGroup(rs.getString(5));
					GroupBean.setQmcGroupName(rs.getString(6));
					GroupBean.setQmcFromQq(rs.getString(7));
					GroupBean.setQmcMsgDate(rs.getString(8));
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
	
	public int queryGroupCount(String [] searchValue) {
		int count = 0;

		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) from ");
		sql.append(" ( SELECT g.qmc_gid,g.qmc_group,g.qmc_group_name,g.qmc_qq,L1.tids,L1.tn FROM ");
		sql.append(" qmc_groups g LEFT JOIN (select GROUP_CONCAT(qt.qmc_tid) tids,GROUP_CONCAT(qt.qmc_tag_name) tn,qtu.qmc_gid from qmc_tag qt left join qmc_tag_union qtu on qt.qmc_tid =qtu.qmc_tid GROUP BY qtu.qmc_gid )L1 ON L1.qmc_gid =g.qmc_gid  ");
		sql.append(" ) A where 1=1 and (");
		StringBuffer temp =new StringBuffer();
		temp.append(sqlWhere2(searchValue[0], searchValue[1]));
		sql.append(temp.substring(0, temp.length() -4 ));
		sql.append(" )");

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
	 * 根据关键词查询群相关记录
	 * @param pageSize
	 * @param startRecord
	 * @param sortColumn
	 * @param sortDir
	 * @param searchValue
	 * @return
	 */
	public List<GroupBean> queryGroupList(int pageSize, int startRecord, String sortColumn, String sortDir, String[] searchValue) {
		List<GroupBean> results = new ArrayList<GroupBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT B.*,");
		sql.append(" ( select count(1) from qmc_msgs where qmc_group =B.qmc_group and qmc_msg_date >UNIX_TIMESTAMP(DATE_SUB(CURDATE(),INTERVAL 1 DAY))) daycount,");
		sql.append(" ( select count(1) from qmc_msgs where qmc_group =B.qmc_group and qmc_msg_date >UNIX_TIMESTAMP(DATE_SUB(CURDATE(),INTERVAL 1 WEEK))) weekcount FROM");
		sql.append(" ( SELECT A.* FROM ");
		sql.append(" ( SELECT g.qmc_gid,g.qmc_group,g.qmc_group_name,g.qmc_qq,L1.tids,L1.tn FROM ");
		sql.append(" qmc_groups g LEFT JOIN (select GROUP_CONCAT(qt.qmc_tid) tids,GROUP_CONCAT(qt.qmc_tag_name) tn,");
		sql.append("qtu.qmc_gid from qmc_tag qt left join qmc_tag_union qtu on qt.qmc_tid =qtu.qmc_tid GROUP BY qtu.qmc_gid )L1 ON L1.qmc_gid =g.qmc_gid  ");
		sql.append(") A where 1=1 and ( ");
		StringBuffer temp =new StringBuffer();
		temp.append(sqlWhere2(searchValue[0], searchValue[1]));
		sql.append(temp.substring(0, temp.length() -4 ));
		sql.append(" ) ");
		
		// for order
		sql.append(" order by " + sortColumn + " " + sortDir + " ");
		
		// for pagination
		if (startRecord == 0) {
			sql.append(" limit ?");
		} else {
			sql.append(" limit ?,? ");

		}
		sql.append(") B");
		System.out.println(sql.toString());
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			conn = DriverManager.getConnection("proxool.f22_pool");

			pstmt = conn.prepareStatement(sql.toString());
			if(startRecord==0){
				pstmt.setInt(1,pageSize);
			}else{
				pstmt.setInt(2, pageSize);
				pstmt.setInt(1, startRecord);
				
			}
			System.out.println("\\\\\\\\页码数:"+pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GroupBean GroupBean = new GroupBean();
				GroupBean.setQmcGId(rs.getInt(1));
				GroupBean.setQmcGroup(rs.getString(2));
				GroupBean.setQmcGroupName(rs.getString(3));
				GroupBean.setQmcQQ(rs.getString(4));
				if(rs.getString(5)==null){
					GroupBean.setQmcTagIds(" ");
				}else{
					GroupBean.setQmcTagIds(rs.getString(5));
					
				}
				if(rs.getString(6)==null){
					GroupBean.setQmcTag("");
				}else{
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
}
