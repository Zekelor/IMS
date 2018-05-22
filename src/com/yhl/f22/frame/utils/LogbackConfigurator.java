/**
 * @License:
 * @Copyright:
 */
package com.yhl.f22.frame.utils;

import ch.qos.logback.access.joran.JoranConfigurator;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import java.io.File;

/**
 * <pre>
 * </pre>
 * 
 * @author Unicorn-Wang 2015年7月21日下午5:45:54
 * @since JDK
 */
public class LogbackConfigurator {
	private static final Logger LOG = LoggerFactory.getLogger(LogbackConfigurator.class);

	/**
	 * logback.xml配置文件路径
	 */
	private String configFilePath;

	/**
	 * @return the configFilePath
	 */
	public String getConfigFilePath() {
		return this.configFilePath;
	}

	/**
	 * @param configFilePath
	 *            the configFilePath to set
	 */
	@Required
	public void setConfigFilePath(String configFilePath) {
		this.configFilePath = configFilePath;
	}

	/**
	 * WEB工程中上下文的绝对路径
	 */
	private String appDir;

	/**
	 * @return the appDir
	 */
	public String getAppDir() {
		return this.appDir;
	}

	/**
	 * @param appDir
	 *            the appDir to set
	 */
	public void setAppDir(String appDir) {
		this.appDir = appDir;
	}

	/**
	 * <pre>
	 * </pre>
	 */
	public void config() {
		File file = new File(configFilePath);
		LoggerContext ctx = (LoggerContext) LoggerFactory.getILoggerFactory();
		JoranConfigurator config = new JoranConfigurator();
		config.setContext(ctx);
		ctx.reset();

		try {
			if (file.isAbsolute()) {
				config.doConfigure(file);
			}
			else {
				config.doConfigure(appDir + File.separator + configFilePath);
			}
		}
		catch (JoranException e) {
			e.printStackTrace();
		}
		StatusPrinter.printInCaseOfErrorsOrWarnings(ctx);
		System.out.println("--------------------------------->");
		LOG.info("Logback已经被设置");
	}
}
