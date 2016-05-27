/**
 * Project Name:PAF_HC
 * File Name:DatabaseUtils.java
 * Package Name:com.hp.utility
 * Date:Sep 7, 20139:34:48 AM
 * Copyright (c) 2013, alterhu2020@gmail.com All Rights Reserved.
 *
 */

package com.framework.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * ClassName:DatabaseUtils Function: TODO ADD FUNCTION. Reason: Used for
 * operating the mysql database Date: Sep 7, 2013 9:34:48 AM
 * 
 * @author huchan
 * 
 * @since JDK 1.6
 * @see
 * @version $Revision: 1.0 $
 */
public class DBUtils {

	public static Logger log = Logger.getLogger(DBUtils.class);

	public static String drivername = null;
	public static String driverurl = null;
	public static String user = null;
	public static String password = null;
	public static Connection con = null;
	public static ResultSet rs = null;

	
	/** 
	* @Title: getConnection 
	* @Description: TODO
	* @author ahu@greendotcorp.com
	* @param @param drivername
	* @param @param driverurl
	* @param @param user
	* @param @param password
	* @param @return    
	* @return Connection    return type
	* @throws 
	*/ 
	
	public static Connection getConnection(String drivername, String driverurl,
			String user, String password) {

		try {
			Class.forName(drivername);
			con = DriverManager.getConnection(driverurl, user, password);
			log.info("Build the JDBC driver connection successfully....");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return con;
	}


	/** 
	* @Title: getConnection 
	* @Description: TODO
	* @author ahu@greendotcorp.com
	* @param @return    
	* @return Connection    return type
	* @throws 
	*/ 
	
	public static Connection getConnection() {

		try {
			ClassLoader loader = DBUtils.class.getClassLoader();

			try {
				InputStream dbfile = loader
						.getResourceAsStream("config.properties");
				Properties p = new Properties();
				p.load(dbfile);
				String env = PropertiesUtils.getEnv("ENVIRONMENT")
						.toLowerCase().trim();
				log.info("JDBC Connection environment is: " + env);
				drivername = p.getProperty(env + ".driver.name");
				driverurl = p.getProperty(env + ".driver.url");
				user = p.getProperty(env + ".driver.user");
				password = p.getProperty(env + ".driver.password");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Class.forName(drivername);
			con = DriverManager.getConnection(driverurl, user, password);
			// logger.info("Build the JDBC driver connection successfully....");
		} catch (ClassNotFoundException e) {
			log.error("Sorry cannot find the database driver file,cannot connect to the database..."
					+ e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("Sorry cannot find the sql url host ,cannot connect to the database..."
					+ e.getMessage());
		}

		return con;
	}

	
	/** 
	* @Title: selectRecord 
	* @Description: TODO
	* @author ahu@greendotcorp.com
	* @param @param con
	* @param @param sql
	* @param @return    
	* @return ResultSet    return type
	* @throws 
	*/ 
	
	public static ResultSet selectRecord(Connection con, String sql) {
		try {
			log.info("SQL Query Statement is: "+sql);
			rs = con.prepareStatement(sql).executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("Sorry cannot find the sql resultset  ,cannot execute the sql:"
					+ sql + "..." + e.getMessage());
		}

		return rs;
	}

	/** 
	* @Title: callStoreProcedure 
	* @Description: TODO
	* @author ahu@greendotcorp.com
	* @param @param con
	* @param @param procedure
	* @param @return    
	* @return CallableStatement    return type
	* @throws 
	*/ 
	
	public static CallableStatement callStoreProcedure(Connection con,
			String procedure) {
		try {
			log.info("SQL Store Procedure is: "+procedure);
			CallableStatement prepareCall = con.prepareCall(procedure);
			return prepareCall;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Method updateRecord.
	 * 
	 * @param con
	 *            Connection
	 * @param deletesql
	 *            String
	 * @return int
	 */
	public static int updateRecord(Connection con, String sql) {
		int updaterows = 0;
		try {
			log.info("SQL Update Statement is: "+sql);
			updaterows = con.prepareStatement(sql).executeUpdate();
			log.info("SQL Update Rows: "+updaterows);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updaterows;

	}

	/**
	 * Method closeAllConnections.
	 * 
	 * @param con
	 *            Connection
	 * @param rs
	 *            ResultSet
	 */
	public static void closeAllConnections(Connection con, ResultSet rs) {
		try {
			if (con != null) {
				con.close();
			}
			if (rs != null) {
				rs.close();
			}
			log.info("had disconnect all the connections from the database now ...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("met error to close the connections..."
					+ e.getMessage());
		}

	}

}
