package com.frame.db;

import java.sql.*;

public class DBUtil {

	/**
	 * 关闭数据库 conn pstmt rs
	 * 
	 * @param conn
	 * @param pstmt
	 * @param rs
	 */
	public static void closeDB(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
