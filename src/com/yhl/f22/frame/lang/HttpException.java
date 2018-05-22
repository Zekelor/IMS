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
 * @date 2017年4月18日上午11:28:13
 * @since JDK 1.7
 * @since
 */
public class HttpException extends FrameException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1843035965421765801L;

	/**
	 * 
	 */
	public HttpException() {
		super();
	}

	/**
	 * @param errorCode
	 * @param errorText
	 * @param cause
	 */
	public HttpException(String errorCode, String errorText, Throwable cause) {
		super(errorCode, errorText, cause);
	}

	/**
	 * @param errorCode
	 * @param errorText
	 */
	public HttpException(String errorCode, String errorText) {
		super(errorCode, errorText);
	}

	/**
	 * @param errorCode
	 * @param cause
	 */
	public HttpException(String errorCode, Throwable cause) {
		super(errorCode, cause);
	}

	/**
	 * @param errorCode
	 */
	public HttpException(String errorCode) {
		super(errorCode);
	}

	/**
	 * @param cause
	 */
	public HttpException(Throwable cause) {
		super(cause);
	}

}
