/**
 * @License:
 * @Copyright:
 */
package com.yhl.f22.frame.utils;

import org.slf4j.helpers.MessageFormatter;

/**
 * <pre>
 * </pre>
 * 
 * @author Unicorn-Wang
 *         2015年6月29日上午11:50:36
 * @since JDK
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
	public static final String toString(Object object, String defaultString) {
		return object == null ? defaultString : object.toString();
	}

	public static final String toString(Object object) {
		return toString(object, "");
	}

	public static final Boolean isEmpty(String string) {
		return org.apache.commons.lang3.StringUtils.isEmpty(string) || org.apache.commons.lang3.StringUtils.isBlank(string);
	}

	public static final String format(String string, Object... replacements) {
		return MessageFormatter.arrayFormat(string, replacements).getMessage();
	}
}