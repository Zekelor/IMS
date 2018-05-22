/**
 * @License:
 * @Copyright:
 */
package com.yhl.f22.frame.db.lang;

import com.yhl.f22.frame.lang.FrameException;

/**
 * <pre>
 * </pre>
 * 
 * @author Administrator
 * @date 2017年4月18日上午11:27:38
 * @since JDK 1.7
 * @since
 */
public class DAOException extends FrameException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 601730856142136716L;

	/**
	 * 
	 */
	public DAOException() {
		super();
	}

	/**
	 * @param errorCode
	 */
	public DAOException(String errorCode) {
		super(errorCode);
	}

	/**
	 * @param cause
	 */
	public DAOException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param errorCode
	 * @param errorText
	 * @param cause
	 */
	public DAOException(String errorCode, String errorText, Throwable cause) {
		super(errorCode, errorText, cause);
	}

	/**
	 * @param errorCode
	 * @param errorText
	 */
	public DAOException(String errorCode, String errorText) {
		super(errorCode, errorText);
	}

	/**
	 * @param errorCode
	 * @param cause
	 */
	public DAOException(String errorCode, Throwable cause) {
		super(errorCode, cause);
	}

}
