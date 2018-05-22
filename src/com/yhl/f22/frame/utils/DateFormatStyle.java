/**
 * @License:
 * @Copyright:
 */
package com.yhl.f22.frame.utils;

import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <pre>
 * </pre>
 * 
 * @author Administrator
 * @date 2017年4月18日上午11:36:06
 * @since JDK 1.7
 * @since
 */
public enum DateFormatStyle {
	FORMAT_8 {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		/*
		 * (non-Javadoc)
		 * @see com.dlw.sap.base.utils.DateFormatStyle#format(java.util.Date)
		 */
		@Override
		public String format(Date date) {
			return dateFormat.format(date);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dlw.sap.base.utils.DateFormatStyle#parse(java.lang.String)
		 */
		@Override
		public Date parse(String dateString) throws ParseException {
			return dateFormat.parse(dateString);
		}
	},
	FORMAT_10 {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		/*
		 * (non-Javadoc)
		 * @see com.dlw.sap.base.utils.DateFormatStyle#format(java.util.Date)
		 */
		@Override
		public String format(Date date) {
			return dateFormat.format(date);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dlw.sap.base.utils.DateFormatStyle#parse(java.lang.String)
		 */
		@Override
		public Date parse(String dateString) throws ParseException {
			return dateFormat.parse(dateString);
		}
	},
	FORMAT_14 {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

		/*
		 * (non-Javadoc)
		 * @see com.dlw.sap.base.utils.DateFormatStyle#format(java.util.Date)
		 */
		@Override
		public String format(Date date) {
			return dateFormat.format(date);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dlw.sap.base.utils.DateFormatStyle#parse(java.lang.String)
		 */
		@Override
		public Date parse(String dateString) throws ParseException {
			return dateFormat.parse(dateString);
		}
	},
	FORMAT_17 {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

		/*
		 * (non-Javadoc)
		 * @see com.dlw.sap.base.utils.DateFormatStyle#format(java.util.Date)
		 */
		@Override
		public String format(Date date) {
			return dateFormat.format(date);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dlw.sap.base.utils.DateFormatStyle#parse(java.lang.String)
		 */
		@Override
		public Date parse(String dateString) throws ParseException {
			return dateFormat.parse(dateString);
		}
	},
	FORMAT_19 {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		/*
		 * (non-Javadoc)
		 * @see com.dlw.sap.base.utils.DateFormatStyle#format(java.util.Date)
		 */
		@Override
		public String format(Date date) {
			return dateFormat.format(date);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dlw.sap.base.utils.DateFormatStyle#parse(java.lang.String)
		 */
		@Override
		public Date parse(String dateString) throws ParseException {
			return dateFormat.parse(dateString);
		}
	},
	FORMAT_23 {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss#SSS");

		/*
		 * (non-Javadoc)
		 * @see com.dlw.sap.base.utils.DateFormatStyle#format(java.util.Date)
		 */
		@Override
		public String format(Date date) {
			return dateFormat.format(date);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dlw.sap.base.utils.DateFormatStyle#parse(java.lang.String)
		 */
		@Override
		public Date parse(String dateString) throws ParseException {
			return dateFormat.parse(dateString);
		}
	};

	/**
	 * <pre>
	 * 将Date类型转换为String类型
	 * </pre>
	 * 
	 * @param date
	 * @return
	 */
	public abstract String format(Date date);

	/**
	 * <pre>
	 * 将String类型转换为Date类型
	 * </pre>
	 * 
	 * @param dateString
	 * @return
	 * @throws ParseException
	 */
	public abstract Date parse(String dateString) throws ParseException;
}
