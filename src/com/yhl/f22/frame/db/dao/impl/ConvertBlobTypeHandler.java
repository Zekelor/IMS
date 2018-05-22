/**
 * 
 */
package com.yhl.f22.frame.db.dao.impl;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * <pre>
 * </pre>
 * 
 * @author Administrator
 * @date 2017年4月18日上午11:55:04
 * @since JDK 1.7
 * @since
 */
public class ConvertBlobTypeHandler extends BaseTypeHandler {
	private static final String DEFAUTL_CHARSET = "utf-8";

	/* (non-Javadoc)
	 * @see org.apache.ibatis.type.BaseTypeHandler#setNonNullParameter(java.sql.PreparedStatement, int, java.lang.Object, org.apache.ibatis.type.JdbcType)
	 */
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
		ByteArrayInputStream bis;
		try {
			bis = new ByteArrayInputStream(parameter.toString().getBytes(DEFAUTL_CHARSET));
		}
		catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Blob Encoding Error!");
		}
		ps.setBinaryStream(i, bis, parameter.toString().length());
	}

	/* (non-Javadoc)
	 * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.ResultSet, java.lang.String)
	 */
	@Override
	public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
		Blob blob = rs.getBlob(columnName);
		byte[] returnValue = null;
		if (null != blob) {
			returnValue = blob.getBytes(1, (int) blob.length());
		}
		try {
			return new String(returnValue, DEFAUTL_CHARSET);
		}
		catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Blob Encoding Error!");
		}
	}

	/* (non-Javadoc)
	 * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.CallableStatement, int)
	 */
	@Override
	public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		Blob blob = cs.getBlob(columnIndex);
		byte[] returnValue = null;
		if (null != blob) {
			returnValue = blob.getBytes(1, (int) blob.length());
		}
		try {
			return new String(returnValue, DEFAUTL_CHARSET);
		}
		catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Blob Encoding Error!");
		}
	}

}
