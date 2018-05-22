package com.frame.qqgroup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.frame.db.DBUtil;

public class GroupDetailDAO {

	public List<GroupBean> queryDetailList(String group,int pageSize, int startRecord, String sortColumn, String sortDir, String searchValue) {
		List<GroupBean> results = new ArrayList<GroupBean>();
		StringBuffer sql = new StringBuffer();
			sql.append("select m.qmc_mid,m.qmc_sender,m.qmc_sender_nick,m.qmc_msg,FROM_UNIXTIME(m.qmc_msg_date,'%Y-%m-%d %H:%i:%s') qmc_msg_date from qmc_msgs m where m.qmc_group=?");

		// for search
		/*String[] columnsName = { "m.qmc_sender", "m.qmc_sender_nick", "m.qmc_msg", "m.qmc_msg_date" };*/
		String[] columnsName = {"m.qmc_msg"};

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
		if(startRecord==0){
			sql.append(" limit ?");
		}else{
			sql.append(" limit ?,? ");
			
		}
		System.out.println(sql.toString());
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = DriverManager.getConnection("proxool.f22_pool");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1,group);
			if(startRecord==0){
				pstmt.setInt(2,pageSize);
			}else{
				pstmt.setInt(3, pageSize);
				pstmt.setInt(2, startRecord);
				
			}
			System.out.println("\\\\\\\\页码数:"+pstmt);
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

	public int count(String group,int pageSize, int startRecord, String searchValue) {
		int count = 0;

		StringBuffer sql = new StringBuffer("select count(1) from qmc_msgs m where qmc_group=? ");
		// for search
		String[] columnsName = {"m.qmc_msg"};
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
			pstmt.setString(1,group);
			rs =pstmt.executeQuery();
			while(rs.next()){
				count =rs.getInt(1);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, pstmt, rs);
		}

		return count;
	}
}
