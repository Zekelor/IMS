/**
 * @License:
 * @Copyright:
 */
package com.yhl.f22.frame.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.yhl.f22.frame.resources.ErrorHelper;

/**
 * <pre>
 * </pre>
 * 
 * @author Administrator
 * @date 2017年4月18日上午11:37:20
 * @since JDK 1.7
 * @since
 */
public abstract class AbstractController extends MultiActionController {
	protected abstract String getMVCModule();

	protected Map<String, Object> getResultInfoBean(int resultCode) {
		StringBuffer msgCode = new StringBuffer(getMVCModule()).append(".").append(resultCode);
		String msg = ErrorHelper.getMessage(msgCode.toString());

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", resultCode == 0);
		resultMap.put("message", msg);
		return resultMap;
	}
}
