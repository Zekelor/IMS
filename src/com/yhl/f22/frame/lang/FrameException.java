/**
 * @License:
 * @Copyright:
 */
package com.yhl.f22.frame.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

import com.yhl.f22.frame.resources.ErrorHelper;
import com.yhl.f22.frame.utils.StringUtils;

/**
 * <pre>
 * </pre>
 * 
 * @author Administrator
 * @date 2017年4月18日上午11:28:04
 * @since JDK 1.7
 * @since
 */
public class FrameException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8591674650001756847L;

	private static final Logger log = LoggerFactory.getLogger(FrameException.class);

	private String errorCode;// 错误码

	private String errorText;// 自定义错误内容描述

	private Object[] replacements;

	private String errorMessage;// 资源文件错误内容描述

	private Throwable cause;// 异常堆栈

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return this.errorCode;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorText
	 */
	public String getErrorText() {
		return this.errorText;
	}

	/**
	 * @param errorText
	 *            the errorText to set
	 */
	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

	/**
	 * @return the cause
	 */
	public Throwable getCause() {
		return this.cause;
	}

	/**
	 * @param cause
	 *            the cause to set
	 */
	public void setCause(Throwable cause) {
		this.cause = cause;
	}

	public FrameException() {
		super();
	}

	public FrameException(String errorCode) {
		this();
		this.errorCode = errorCode;
	}

	public FrameException(String errorCode, String errorText) {
		this(errorCode);
		this.errorText = errorText;
	}

	public FrameException(Throwable cause) {
		super(cause);
		this.cause = cause;
	}

	public FrameException(String errorCode, Throwable cause) {
		this(errorCode);
		this.cause = cause;
	}

	public FrameException(String errorCode, String errorText, Throwable cause) {
		this(errorCode, cause);
		this.errorText = errorText;
	}

	public FrameException(String errorCode, String... replacements) {
		this(errorCode);
		this.replacements = replacements;

		if (!StringUtils.isEmpty(errorCode)) {
			String resourceErrorMessage = ErrorHelper.getMessage(errorCode);

			if (!StringUtils.isEmpty(resourceErrorMessage)) {
				FormattingTuple formattingTuple = MessageFormatter.arrayFormat(resourceErrorMessage, replacements);
				errorMessage = formattingTuple.getMessage();
			}
		}
	}

	public FrameException(String errorCode, Throwable throwable, String... replacements) {
		this(errorCode, replacements);
		this.cause = throwable;
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return StringUtils.defaultString(errorMessage, super.getMessage());
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#printStackTrace()
	 */
	@Override
	public void printStackTrace() {
		// 超类打印
		super.printStackTrace();

		// 如果填写了错误码，则尝试从properties文件内获取描述信息
		if (!StringUtils.isEmpty(this.errorCode)) {
			log.error(StringUtils.defaultString(ErrorHelper.getMessage(this.errorCode, this.replacements), StringUtils.format("没有找到{}对应的错误描述信息", this.errorCode)));
		}
	}
}