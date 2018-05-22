package com.frame.qqgroup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.frame.common.CommonConst;
import com.frame.db.DBUtil;
import com.yhl.f22.frame.utils.StringUtils;

public class GroupDAO {

	public List<GroupBean> queryDataList(String departId, int pageSize, int startRecord, String sortColumn, String sortDir, String searchValue) {
		List<GroupBean> results = new ArrayList<GroupBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT B.*,");
		sql.append(" ( select count(1) from qmc_msgs where qmc_group =B.qmc_group and qmc_msg_date >UNIX_TIMESTAMP(DATE_SUB(CURDATE(),INTERVAL 1 DAY))) daycount,");
		sql.append(" ( select count(1) from qmc_msgs where qmc_group =B.qmc_group and qmc_msg_date >UNIX_TIMESTAMP(DATE_SUB(CURDATE(),INTERVAL 1 WEEK))) weekcount FROM");
		sql.append(" ( SELECT A.* FROM ");
		sql.append(" ( SELECT g.qmc_gid,g.qmc_group,g.qmc_group_name,g.qmc_qq,L1.tids,L1.tn FROM ");
		sql.append(" qmc_groups g LEFT JOIN (select GROUP_CONCAT(qt.qmc_tid) tids,GROUP_CONCAT(qt.qmc_tag_name) tn,");
		sql.append("qtu.qmc_gid from qmc_tag qt left join qmc_tag_union qtu on qt.qmc_tid =qtu.qmc_tid GROUP BY qtu.qmc_gid )L1 ON L1.qmc_gid =g.qmc_gid  ");
		sql.append(") A left join qmc_qq qq ON A.qmc_qq=qq.qmc_qq  ");
		
		//权限控制
		if(CommonConst.ROLE_NOAUTH.equals(departId)){
			sql.append("where 1=2 ");
		}else if(!StringUtils.isEmpty(departId)){
			sql.append("where qq.qmc_qqid = (select qmc_qqid from qmc_qq_union qu where qu.qmc_receive_gid =");
			sql.append(departId+" )");
		}else {
			sql.append("where 1=1");
		}
		
		// for search
		String[] columnsName = { "A.qmc_group_name", "A.qmc_group", "A.tn", "A.qmc_qq" };
		boolean searchAble = false;
		if (searchValue != null && !"".equals(searchValue)) {
			sql.append(" and ");
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

	public int count(String departId, int pageSize, int startRecord, String searchValue) {
		int count = 0;

		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) from ");
		sql.append(" ( select g.qmc_gid,g.qmc_group,g.qmc_group_name,g.qmc_qq,L1.tids,L1.tn FROM ");
		sql.append(" qmc_groups g LEFT JOIN (select GROUP_CONCAT(qt.qmc_tid) tids,GROUP_CONCAT(qt.qmc_tag_name) tn,qtu.qmc_gid from qmc_tag qt left join qmc_tag_union qtu on qt.qmc_tid =qtu.qmc_tid GROUP BY qtu.qmc_gid )L1 ON L1.qmc_gid =g.qmc_gid  ");
		sql.append(" ) A LEFT JOIN qmc_qq qq ON A.qmc_qq=qq.qmc_qq  ");
		if(CommonConst.ROLE_NOAUTH.equals(departId)){
			sql.append("where 1=2 ");
		}else if(!StringUtils.isEmpty(departId)){
			sql.append("where qq.qmc_qqid = (select qmc_qqid from qmc_qq_union qu where qu.qmc_receive_gid =");
			sql.append(departId+" )");
		}else {
			sql.append("where 1=1");
		}
		// for search
		String[] columnsName = {"A.qmc_group_name","A.qmc_group","A.tn","A.qmc_qq"};
		boolean searchAble = false;
		if (searchValue != null && !"".equals(searchValue)) {
			sql.append(" and ");
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

	public int countByTag(String departId,String tagId, int pageSize, int startRecord, String searchValue) {
		int count = 0;
		String []tagStr =tagId.split(",");
		String tagIds ="";
		for(int j=0;j<tagStr.length;j++){
			tagIds+="?,";
		}
		
		String tempStr ="";
		if(!StringUtils.isEmpty(tagIds)){
			 tempStr =tagIds.substring(0, tagIds.lastIndexOf(","));
			
		}

		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) from ");
		sql.append(" ( SELECT g.qmc_gid,g.qmc_group,g.qmc_group_name,g.qmc_qq,L1.tids,L1.tn FROM ");
		sql.append(" qmc_groups g LEFT JOIN (select GROUP_CONCAT(qt.qmc_tid) tids,GROUP_CONCAT(qt.qmc_tag_name) tn,qtu.qmc_gid from qmc_tag qt left join qmc_tag_union qtu on qt.qmc_tid =qtu.qmc_tid where qt.qmc_tid  in ("+tempStr+") GROUP BY qtu.qmc_gid )L1 ON L1.qmc_gid =g.qmc_gid  ");
		sql.append(" ) A LEFT JOIN qmc_qq qq ON A.qmc_qq=qq.qmc_qq where A.tids is not null ");
		if(CommonConst.ROLE_NOAUTH.equals(departId)){
			sql.append("and 1=2 ");
		}else if(!StringUtils.isEmpty(departId)){
			sql.append("and qq.qmc_qqid = (select qmc_qqid from qmc_qq_union qu where qu.qmc_receive_gid =");
			sql.append(departId+" )");
		}else {
			sql.append("and 1=1");
		}
		// for search
		String[] columnsName = {"A.qmc_group_name","A.qmc_group","A.tn","A.qmc_qq"};
		boolean searchAble = false;
		if (searchValue != null && !"".equals(searchValue)) {
			sql.append(" and ");
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
			int [] tagInt =new int[tagStr.length];
			for(int i=0;i<tagStr.length;i++){
				tagInt[i]=Integer.parseInt(tagStr[i]);
				pstmt.setInt(i+1,tagInt[i]);
			}
			rs =pstmt.executeQuery();
			while(rs.next()){
				count =rs.getInt(1);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, pstmt, rs);
		}

		return count;
	}
	
	/**
	 * 根据标签来过滤
	 * @param tagId
	 * @param pageSize
	 * @param startRecord
	 * @param sortColumn
	 * @param sortDir
	 * @param searchValue
	 * @return
	 */
	public List<GroupBean> queyrDataByTag(String departId,String tagId, int pageSize, int startRecord, String sortColumn, String sortDir,String searchValue) {
		List<GroupBean> results = new ArrayList<GroupBean>();
		String []tagStr =tagId.split(",");
		String tagIds ="";
		for(int j=0;j<tagStr.length;j++){
			tagIds+="?,";
		}
		
		String tempStr ="";
		if(!StringUtils.isEmpty(tagIds)){
			 tempStr =tagIds.substring(0, tagIds.lastIndexOf(","));
			
		}
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT B.*,");
		sql.append(" ( select count(1) from qmc_msgs where qmc_group =B.qmc_group and qmc_msg_date >UNIX_TIMESTAMP(DATE_SUB(CURDATE(),INTERVAL 1 DAY))) daycount,");
		sql.append(" ( select count(1) from qmc_msgs where qmc_group =B.qmc_group and qmc_msg_date >UNIX_TIMESTAMP(DATE_SUB(CURDATE(),INTERVAL 1 WEEK))) weekcount FROM");
		sql.append(" ( SELECT A.* FROM ");
		sql.append(" ( SELECT g.qmc_gid,g.qmc_group,g.qmc_group_name,g.qmc_qq,L1.tids,L1.tn FROM ");
		sql.append(" qmc_groups g LEFT JOIN (select GROUP_CONCAT(qt.qmc_tid) tids,GROUP_CONCAT(qt.qmc_tag_name) tn,");
		sql.append("qtu.qmc_gid from qmc_tag qt left join qmc_tag_union qtu on qt.qmc_tid =qtu.qmc_tid where qt.qmc_tid in ("+tempStr+")");
		sql.append(" GROUP BY qtu.qmc_gid )L1 ON L1.qmc_gid =g.qmc_gid  ");
		sql.append(") A left join qmc_qq qq ON A.qmc_qq=qq.qmc_qq where A.tids is not null ");
		
		if(CommonConst.ROLE_NOAUTH.equals(departId)){
			sql.append("and 1=2 ");
		}else if(!StringUtils.isEmpty(departId)){
			sql.append("and qq.qmc_qqid = (select qmc_qqid from qmc_qq_union qu where qu.qmc_receive_gid =");
			sql.append(departId+" )");
		}else {
			sql.append("and 1=1");
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

		// for search
		String[] columnsName = {"B.qmc_group_name","B.qmc_group","B.tn","B.qmc_qq"};
		boolean searchAble = false;
		if (searchValue != null && !"".equals(searchValue)) {
			sql.append(" and ");
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
			
			
			int [] tagInt =new int[tagStr.length];
			for(int i=0;i<tagStr.length;i++){
				tagInt[i]=Integer.parseInt(tagStr[i]);
				pstmt.setInt(i+1,tagInt[i]);
			}
			
			if(startRecord==0){
				pstmt.setInt(tagInt.length+1,pageSize);
			}else{
				pstmt.setInt(tagInt.length+2, pageSize);
				pstmt.setInt(tagInt.length+1, startRecord);
				
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
