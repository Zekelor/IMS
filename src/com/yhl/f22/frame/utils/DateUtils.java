/**
 * @License:
 * @Copyright:
 */
package com.yhl.f22.frame.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * <pre>
 * </pre>
 * 
 * @author Unicorn-Wang 2015年6月29日上午11:50:56
 * @since JDK
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	private static final Logger log = LoggerFactory.getLogger(DateUtils.class);

	public static final String format(Date date, DateFormatStyle style) {
		log.debug("format date string using {}", style.name());
		return style.format(date);
	}

	public static final Date parse(String dateString, DateFormatStyle style) throws ParseException {
		log.debug("parse date string using {}", style.name());
		return style.parse(dateString);
	}

	public static final String getCurrent8() {
		return format(Calendar.getInstance().getTime(), DateFormatStyle.FORMAT_8);
	}

	public static final String getCurrent10() {
		return format(Calendar.getInstance().getTime(), DateFormatStyle.FORMAT_10);
	}

	public static final String getCurrent14() {
		return format(Calendar.getInstance().getTime(), DateFormatStyle.FORMAT_14);
	}

	public static final String getCurrent17() {
		return format(Calendar.getInstance().getTime(), DateFormatStyle.FORMAT_17);
	}

	public static final String getCurrent19() {
		return format(Calendar.getInstance().getTime(), DateFormatStyle.FORMAT_19);
	}

	public static final String getCurrent23() {
		return format(Calendar.getInstance().getTime(), DateFormatStyle.FORMAT_23);
	}
}