package com.frame.login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.frame.db.DBUtil;
import com.frame.usermgr.UserBean;
public class LoginDAO {
	UserBean bean =new UserBean();

	public String queryLoginInfo(String username,String password) {
		String flag ="0";
		String sql ="select qmc_pwd from qmc_users where qmc_uname =?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("proxool.f22_pool");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			String realPass="";
			while(rs.next()){
				realPass=rs.getString(1);
			}
			if(password.equals(realPass)){
				flag="1";
			}else{
				flag="0";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, pstmt, rs);
		}
		return flag;
	}
	
}
