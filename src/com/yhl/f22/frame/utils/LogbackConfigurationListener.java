/**
 * @License:
 * @Copyright:
 */
package com.yhl.f22.frame.utils;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;

import org.logicalcobwebs.proxool.ProxoolFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * <pre>
 * 用于WEB项目中加载Logback的Listener，附加了Proxool自动关闭选项
 * </pre>
 * 
 * @author Unicorn-Wang 2015年7月21日下午5:58:35
 * @since JDK
 */
public class LogbackConfigurationListener implements ServletContextListener {
	private static final Logger LOG = LoggerFactory.getLogger(LogbackConfigurationListener.class);

	/**
	 * 上下文参数_xmlFile(XML文件)
	 */
	private static final String CONTEXT_PARAM_XMLFILE = "logbackXml";

	/**
	 * 上下文参数_autoShutdown(自动关闭)
	 */
	private static final String AUTO_SHUTDOWN_PROPERTY = "autoShutdown";

	/**
	 * Servlet上下路径_根
	 */
	private static final String SERVLET_PATH_ROOT = "/";

	/**
	 * 自动退出
	 */
	private boolean autoShutdown = true;

	/**
	 * 监听初始化。<br>
	 * 
	 * @param servletContextEvent
	 *            servlet上下文事件参数对象
	 */
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		// 获取Servlet上下文对象
		ServletContext context = servletContextEvent.getServletContext();
		// 获取Servlet上下文根的绝对路径
		String appDir = servletContextEvent.getServletContext().getRealPath(SERVLET_PATH_ROOT);

		// 获取上下文初始化参数集合
		Enumeration<?> names = context.getInitParameterNames();

		// 逐个循环处理上下文参数信息
		while (names.hasMoreElements()) {
			// 获取参数名
			String name = names.nextElement().toString();
			// 根据参数名，获取参数值
			String value = context.getInitParameter(name);

			if (CONTEXT_PARAM_XMLFILE.equals(name)) {
				// XML配置的情况
				// 尝试从系统信息的CPU核心识别是公司的电脑还是家里的电脑，以便读取不同的logback.xml配置文件
				Map<String, String> envMap = System.getenv();
				String cpuIdentify = envMap.get("PROCESSOR_IDENTIFIER");

				// 公司的电脑CPU是Intel核心
				if (StringUtils.defaultIfEmpty(cpuIdentify, "").toLowerCase().indexOf("intel64") != -1) {
					value = value.replaceAll(".xml", "_dev.xml");
					System.out.println("加载测试环境logback配置");
				}
				else {
					System.out.println("加载现网环境logback配置");
				}

				try {
					// 获取配置文件句柄
					File file = new File(value);

					if (file.isAbsolute()) {
						// 绝对路径
						LoggerContext ctx = (LoggerContext) LoggerFactory.getILoggerFactory();
						JoranConfigurator config = new JoranConfigurator();
						config.setContext(ctx);
						ctx.reset();
						try {
							config.doConfigure(file);
						}
						catch (JoranException e) {
							e.printStackTrace();
						}
						StatusPrinter.printInCaseOfErrorsOrWarnings(ctx);
						System.out.println("--------------------------------->");
						LOG.info("Logback已经被设置");
					}
					else {
						// 相对路径
						LoggerContext ctx = (LoggerContext) LoggerFactory.getILoggerFactory();
						JoranConfigurator config = new JoranConfigurator();
						config.setContext(ctx);
						ctx.reset();
						try {
							config.doConfigure(appDir + File.separator + value);
						}
						catch (JoranException e) {
							e.printStackTrace();
						}
						StatusPrinter.printInCaseOfErrorsOrWarnings(ctx);
						System.out.println("--------------------------------->");
						LOG.info("Logback已经被设置");
					}
				}
				catch (Exception e) {
					LOG.error("Problem configuring " + value, e);
				}
			}
			else if (AUTO_SHUTDOWN_PROPERTY.equals(name)) {
				// 自动关闭的情况
				autoShutdown = Boolean.valueOf(value).booleanValue();
			}
		}
	}

	/**
	 * 监听销毁。<br>
	 * 
	 * @param servletContextEvent
	 *            servlet上下文事件参数对象
	 */
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		if (this.autoShutdown) {
			ProxoolFacade.shutdown(0);
		}
	}
}
