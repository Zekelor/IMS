/**
 * 
 */
package com.yhl.f22.frame.db.lang;

import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.configuration.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import javax.annotation.PostConstruct;

/**
 * <pre>
 * </pre>
 * 
 * @author Administrator
 * @date 2017年4月18日上午11:27:46
 * @since JDK 1.7
 * @since
 */
public class ProxoolDatasourceStarter {
	private Logger logger = LoggerFactory.getLogger(ProxoolDatasourceStarter.class.getName());

	private final String PROP_PREFIX = "jdbc-";
	private String order = "0";// datasource 次序
	private String alias = "";
	private String driver = "";
	private String driverUrl = "";
	private String user = "";
	private String password = "";
	private String prototypeCount = "5";// If there are fewer than this number of connections available then we will build some more (assuming the maximum-connection-count is not exceeded). For example. Of we have 3
										// active connections and 2 available, but our prototype-count is 4 then it will attempt to build another 2. This differs from minimum-connection-count because it takes into
										// account the number of active connections. minimum-connection-count is absolute and doesn't care how many are in use. prototype-count is the number of spare connections it
										// strives to keep over and above the ones that are currently active. Default is 0.
	private String maximumConnectionCount = "20";// The maximum number of connections to the database. Default is 15.
	private String simultaneousBuildThrottle = "30";
	private String minimumConnectionCount = "3";// The minimum number of connections we will keep open, regardless of whether anyone needs them or not. Default is 5
	private String trace = "true";// If true then each SQL call gets logged (DEBUG level) along with the execution time. You can also get this information by registering a ConnectionListener (see ProxoolFacade). Default
									// is false.
	private String verbose = "false";// Either false (quiet) or true (loud). Default is false

	private String houseKeepingSleepTime = "40000";// How long the house keeping thread sleeps for (milliseconds). The house keeper is responsible for checking the state of all the connections and tests whether any need
													// to be destroyed or created. Default is 30 seconds.
	private String houseKeepingTestSql = "select CURRENT_DATE";// If the house keeping thread finds and idle connections it will test them with this SQL statement. It should be _very_ quick to execute. Something like
																// checking the current date or something. If not defined then this test is omitted.
	private String maximumConnectionLifeTime = "18000000";// The maximum amount of time that a connection exists for before it is killed (milliseconds). Default is 4 hours.
	private String recentlyStartedThreshold = "40000";// This helps us determine whether the pool status is up, down or overloaded. As long as at least one connection was started within this threshold (milliseconds) or
														// there are some spare connections available then we assume the pool is up. Default is 60 seconds.
	private String overloadWithoutRefusalLifetime = "50000";// This helps us determine the pool status. If we have refused a connection within this threshold (milliseconds) then we are overloaded. Default is 60 seconds.
	private String maximumActiveTime = "60000";// If the housekeeper comes across a thread that has been active for longer than this then it will kill it. So make sure you set this to a number bigger than your slowest
												// expected response! Default is 5 minutes.
	private String fatalSqlException = "Fatal error";// A comma separated list of message fragments. When an SQLException occurs its message is compared to each of these fragments. If it contains any of them (case
														// sensitive) then it is detected as a Fatal SQL Exception. This causes that connection to be discarded. Regardless of what happens, the exception is thrown again
														// so that the user knows what has happened. You can optionally configure a different exception to be thrown (see fatal-sql-exception-wrapper-class property)
														// Default is null.

	/**
	 * proxool 配置初始化
	 */
	@PostConstruct
	public void initialize() {
		Properties prop = new Properties();

		addProp(prop);

		try {
			PropertyConfigurator.configure(prop);
		}
		catch (ProxoolException e) {
			logger.error("[ProxoolDatasourceStarter->initialize]加载proxool配置出现异常", e);
		}
	}

	/**
	 * 添加数据源的配置属性
	 * 
	 * @param prop
	 */
	private void addProp(Properties prop) {
		String prefixFlag = this.PROP_PREFIX + this.order + ".proxool.";
		prop.setProperty(prefixFlag + "alias", this.alias);
		prop.setProperty(prefixFlag + "driver-class", this.driver);
		prop.setProperty(prefixFlag + "driver-url", this.driverUrl);
		prop.setProperty(this.PROP_PREFIX + this.order + ".user", this.user);
		prop.setProperty(this.PROP_PREFIX + this.order + ".password", this.password);
		prop.setProperty(prefixFlag + "prototype-count", this.prototypeCount);
		prop.setProperty(prefixFlag + "maximum-connection-count", this.maximumConnectionCount);
		prop.setProperty(prefixFlag + "simultaneous-build-throttle", this.simultaneousBuildThrottle);
		prop.setProperty(prefixFlag + "minimum-connection-count", this.minimumConnectionCount);
		prop.setProperty(prefixFlag + "trace", this.trace);
		prop.setProperty(prefixFlag + "verbose", this.verbose);

		prop.setProperty(prefixFlag + "house-keeping-sleep-time", this.houseKeepingSleepTime);
		prop.setProperty(prefixFlag + "house-keeping-test-sql", this.houseKeepingTestSql);
		prop.setProperty(prefixFlag + "maximum-connection-lifetime", this.maximumConnectionLifeTime);
		prop.setProperty(prefixFlag + "recently-started-threshold", this.recentlyStartedThreshold);
		prop.setProperty(prefixFlag + "overload-without-refusal-lifetime", this.overloadWithoutRefusalLifetime);
		prop.setProperty(prefixFlag + "maximum-active-time", this.maximumActiveTime);
		prop.setProperty(prefixFlag + "fatal-sql-exception", this.fatalSqlException);

	}

	/**
	 * @return the order
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * @param order
	 *            the order to set
	 */
	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @param alias
	 *            the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * @return the driver
	 */
	public String getDriver() {
		return driver;
	}

	/**
	 * @param driver
	 *            the driver to set
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}

	/**
	 * @return the driverUrl
	 */
	public String getDriverUrl() {
		return driverUrl;
	}

	/**
	 * @param driverUrl
	 *            the driverUrl to set
	 */
	public void setDriverUrl(String driverUrl) {
		this.driverUrl = driverUrl;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the prototypeCount
	 */
	public String getPrototypeCount() {
		return prototypeCount;
	}

	/**
	 * @param prototypeCount
	 *            the prototypeCount to set
	 */
	public void setPrototypeCount(String prototypeCount) {
		this.prototypeCount = prototypeCount;
	}

	/**
	 * @return the maximumConnectionCount
	 */
	public String getMaximumConnectionCount() {
		return maximumConnectionCount;
	}

	/**
	 * @param maximumConnectionCount
	 *            the maximumConnectionCount to set
	 */
	public void setMaximumConnectionCount(String maximumConnectionCount) {
		this.maximumConnectionCount = maximumConnectionCount;
	}

	/**
	 * @return the simultaneousBuildThrottle
	 */
	public String getSimultaneousBuildThrottle() {
		return simultaneousBuildThrottle;
	}

	/**
	 * @param simultaneousBuildThrottle
	 *            the simultaneousBuildThrottle to set
	 */
	public void setSimultaneousBuildThrottle(String simultaneousBuildThrottle) {
		this.simultaneousBuildThrottle = simultaneousBuildThrottle;
	}

	/**
	 * @return the minimumConnectionCount
	 */
	public String getMinimumConnectionCount() {
		return minimumConnectionCount;
	}

	/**
	 * @param minimumConnectionCount
	 *            the minimumConnectionCount to set
	 */
	public void setMinimumConnectionCount(String minimumConnectionCount) {
		this.minimumConnectionCount = minimumConnectionCount;
	}

	/**
	 * @return the trace
	 */
	public String getTrace() {
		return trace;
	}

	/**
	 * @param trace
	 *            the trace to set
	 */
	public void setTrace(String trace) {
		this.trace = trace;
	}

	/**
	 * @return the verbose
	 */
	public String getVerbose() {
		return verbose;
	}

	/**
	 * @param verbose
	 *            the verbose to set
	 */
	public void setVerbose(String verbose) {
		this.verbose = verbose;
	}

	/**
	 * @return the houseKeepingSleepTime
	 */
	public String getHouseKeepingSleepTime() {
		return houseKeepingSleepTime;
	}

	/**
	 * @param houseKeepingSleepTime
	 *            the houseKeepingSleepTime to set
	 */
	public void setHouseKeepingSleepTime(String houseKeepingSleepTime) {
		this.houseKeepingSleepTime = houseKeepingSleepTime;
	}

	/**
	 * @return the houseKeepingTestSql
	 */
	public String getHouseKeepingTestSql() {
		return houseKeepingTestSql;
	}

	/**
	 * @param houseKeepingTestSql
	 *            the houseKeepingTestSql to set
	 */
	public void setHouseKeepingTestSql(String houseKeepingTestSql) {
		this.houseKeepingTestSql = houseKeepingTestSql;
	}

	/**
	 * @return the maximumConnectionLifeTime
	 */
	public String getMaximumConnectionLifeTime() {
		return maximumConnectionLifeTime;
	}

	/**
	 * @param maximumConnectionLifeTime
	 *            the maximumConnectionLifeTime to set
	 */
	public void setMaximumConnectionLifeTime(String maximumConnectionLifeTime) {
		this.maximumConnectionLifeTime = maximumConnectionLifeTime;
	}

	/**
	 * @return the recentlyStartedThreshold
	 */
	public String getRecentlyStartedThreshold() {
		return recentlyStartedThreshold;
	}

	/**
	 * @param recentlyStartedThreshold
	 *            the recentlyStartedThreshold to set
	 */
	public void setRecentlyStartedThreshold(String recentlyStartedThreshold) {
		this.recentlyStartedThreshold = recentlyStartedThreshold;
	}

	/**
	 * @return the overloadWithoutRefusalLifetime
	 */
	public String getOverloadWithoutRefusalLifetime() {
		return overloadWithoutRefusalLifetime;
	}

	/**
	 * @param overloadWithoutRefusalLifetime
	 *            the overloadWithoutRefusalLifetime to set
	 */
	public void setOverloadWithoutRefusalLifetime(String overloadWithoutRefusalLifetime) {
		this.overloadWithoutRefusalLifetime = overloadWithoutRefusalLifetime;
	}

	/**
	 * @return the maximumActiveTime
	 */
	public String getMaximumActiveTime() {
		return maximumActiveTime;
	}

	/**
	 * @param maximumActiveTime
	 *            the maximumActiveTime to set
	 */
	public void setMaximumActiveTime(String maximumActiveTime) {
		this.maximumActiveTime = maximumActiveTime;
	}

	/**
	 * @return the fatalSqlException
	 */
	public String getFatalSqlException() {
		return fatalSqlException;
	}

	/**
	 * @param fatalSqlException
	 *            the fatalSqlException to set
	 */
	public void setFatalSqlException(String fatalSqlException) {
		this.fatalSqlException = fatalSqlException;
	}

}
