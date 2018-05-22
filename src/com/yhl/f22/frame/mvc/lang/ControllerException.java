/**
 * @License:
 * @Copyright:
 */
package com.yhl.f22.frame.mvc.lang;

import com.yhl.f22.frame.lang.FrameException;

/**
 * <pre>
 * </pre>
 * 
 * @author Administrator
 * @date 2017年4月18日上午11:40:35
 * @since JDK 1.7
 * @since
 */
public class ControllerException extends FrameException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 533860818693362488L;

	/**
	 * 
	 */
	public ControllerException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCode
	 * @param errorText
	 * @param cause
	 */
	public ControllerException(String errorCode, String errorText, Throwable cause) {
		super(errorCode, errorText, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCode
	 * @param replacements
	 */
	public ControllerException(String errorCode, String... replacements) {
		super(errorCode, replacements);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCode
	 * @param errorText
	 */
	public ControllerException(String errorCode, String errorText) {
		super(errorCode, errorText);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCode
	 * @param throwable
	 * @param replacements
	 */
	public ControllerException(String errorCode, Throwable throwable, String... replacements) {
		super(errorCode, throwable, replacements);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCode
	 * @param cause
	 */
	public ControllerException(String errorCode, Throwable cause) {
		super(errorCode, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCode
	 */
	public ControllerException(String errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public ControllerException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
