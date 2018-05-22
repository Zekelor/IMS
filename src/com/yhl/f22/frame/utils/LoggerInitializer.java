/**
 * @License:
 * @Copyright:
 */
package com.yhl.f22.frame.utils;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

import com.yhl.f22.frame.lang.FrameException;

/**
 * <pre>
 * </pre>
 * 
 * @author Unicorn-Wang
 *         2015年8月10日下午2:24:58
 * @since JDK
 */
public class LoggerInitializer {
	private static final Logger log = LoggerFactory.getLogger(LoggerInitializer.class);

	/**
	 * <pre>
	 * 使用Classpath中的logback.xml文件配置Logger
	 * 一般在JAVA Application中使用此工具类
	 * 直接调用{@code LoggerInitializer.initLogger()}方法，将会使用平台默认的logback.xml文件进行日志配置
	 * </pre>
	 * 
	 * @throws FrameException
	 */
	public static final void initLogger() throws FrameException {
		InputStream inputStream = LoggerInitializer.class.getResourceAsStream("/META-INF/logger/logback.xml");
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		JoranConfigurator configurator = new JoranConfigurator();
		configurator.setContext(loggerContext);
		loggerContext.reset();

		try {
			configurator.doConfigure(inputStream);
			System.out.println(configurator.getContext().getName());

			log.info("Logger已经被配置");
		}
		catch (JoranException e) {
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}

			throw new FrameException(e);
		}
		finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			}
			catch (IOException ioe) {
				if (log.isDebugEnabled()) {
					ioe.printStackTrace();
				}

				throw new FrameException(ioe);
			}
		}
	}
}