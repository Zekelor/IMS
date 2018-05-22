/**
 * @License:
 * @Copyright:
 */
package com.yhl.f22.frame.lang;

/**
 * <pre>
 * </pre>
 * 
 * @author Administrator
 * @date 2017年4月18日上午11:27:59
 * @since JDK 1.7
 * @since
 */
public class AccessRefuseException extends FrameException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7278286091067175064L;

	/**
	 * 
	 */
	public AccessRefuseException() {
		super();
	}

	/**
	 * @param errorCode
	 * @param errorText
	 * @param cause
	 */
	public AccessRefuseException(String errorCode, String errorText, Throwable cause) {
		super(errorCode, errorText, cause);
	}

	/**
	 * @param errorCode
	 * @param errorText
	 */
	public AccessRefuseException(String errorCode, String errorText) {
		super(errorCode, errorText);
	}

	/**
	 * @param errorCode
	 * @param cause
	 */
	public AccessRefuseException(String errorCode, Throwable cause) {
		super(errorCode, cause);
	}

	/**
	 * @param errorCode
	 */
	public AccessRefuseException(String errorCode) {
		super(errorCode);
	}

	/**
	 * @param cause
	 */
	public AccessRefuseException(Throwable cause) {
		super(cause);
	}

}
