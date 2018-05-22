/**
 * @License:
 * @Copyright:
 */
package com.yhl.f22.frame.service.lang;

import com.yhl.f22.frame.lang.FrameException;

/**
 * <pre>
 * </pre>
 * 
 * @author Administrator
 * @date 2017年4月18日上午11:45:00
 * @since JDK 1.7
 * @since
 */
public class ServiceException extends FrameException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7024082693676806198L;

	/**
	 * 
	 */
	public ServiceException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCode
	 * @param errorText
	 * @param cause
	 */
	public ServiceException(String errorCode, String errorText, Throwable cause) {
		super(errorCode, errorText, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCode
	 * @param replacements
	 */
	public ServiceException(String errorCode, String... replacements) {
		super(errorCode, replacements);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCode
	 * @param errorText
	 */
	public ServiceException(String errorCode, String errorText) {
		super(errorCode, errorText);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCode
	 * @param throwable
	 * @param replacements
	 */
	public ServiceException(String errorCode, Throwable throwable, String... replacements) {
		super(errorCode, throwable, replacements);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCode
	 * @param cause
	 */
	public ServiceException(String errorCode, Throwable cause) {
		super(errorCode, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCode
	 */
	public ServiceException(String errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public ServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
