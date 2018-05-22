/**
 * @License:
 * @Copyright:
 */
package com.yhl.f22.frame.utils;

import java.util.Map;

/**
 * <pre>
 * </pre>
 * 
 * @author Unicorn-Wang
 *         2015年7月13日下午5:25:45
 * @since JDK
 */
public class MapUtils extends CollectionUtils {
	public static final Boolean isEmpty(Map<?, ?> map) {
		return map == null || map.size() <= 0;
	}
}
