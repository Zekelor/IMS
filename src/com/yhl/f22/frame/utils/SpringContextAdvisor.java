/**
 * @License:
 * @Copyright:
 */
package com.yhl.f22.frame.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * <pre>
 * </pre>
 * 
 * @author Unicorn-Wang 2015年6月23日下午3:17:01
 * @since JDK
 */
public class SpringContextAdvisor implements ApplicationContextAware {
	private static final Logger logger = LoggerFactory.getLogger(SpringContextAdvisor.class);

	private static ApplicationContext context;
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		if (context == null) {
			context = arg0;
			logger.debug("SpringContextAdvisor上下文向导类环境被初始化");
		}
	}

	public static final Object getBean(String beanId) {
		return context.getBean(beanId);
	}

	public static final <T> T getBean(String beanId, Class<T> clazz) {
		return context.getBean(beanId, clazz);
	}
}
