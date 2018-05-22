/**
 * @License:
 * @Copyright:
 */
package com.yhl.f22.frame.resources;

import java.util.Locale;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yhl.f22.frame.utils.StringUtils;

/**
 * <pre>
 * </pre>
 * 
 * @author Administrator
 * @date 2017年4月18日上午11:28:31
 * @since JDK 1.7
 * @since
 */
public class ErrorHelper {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(ErrorHelper.class);

	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("META-INF/resources/error",Locale.CHINA);

	public static final String getMessage(String key) {
		return resourceBundle.getString(key);
	}

	public static final String getMessage(String key, Object... replacements) {
		String message = getMessage(key);

		if (!StringUtils.isEmpty(message)) {
			return StringUtils.format(message, replacements);
		}

		return "";
	}
}
