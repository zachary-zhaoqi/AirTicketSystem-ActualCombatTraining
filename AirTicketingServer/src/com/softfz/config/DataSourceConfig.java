package com.softfz.config;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


/**
 * 数据库连接信息配置类，该类中设置全局的静态变量用于存放数据库连接信息
 * @author Administrator
 *
 */
public class DataSourceConfig {
	/**
	 * 数据库IP地址
	 */
	public static String ip = "127.0.0.1";
//	public static String ip;
	/**
	 * 数据库端口
	 */
	public static String port = "3306";
//	public static String port;
	/**
	 * 数据库SID
	 */
	public static String sid = "mysql";
//	public static String sid;
	/**
	 * 数据库帐号
	 */
	public static String user = "jn131201";
//	public static String user;
	/**
	 * 数据库密码
	 */
	public static String password = "jn131201";
//	public static String password;
	
	
	/**
	 * 数据源
	 */
	private static DataSource dataSource; 
	
	
	
	/**
	 * 获取数据源，即连接池对象
	 * @return Java.sql.DataSource
	 */
	public static synchronized DataSource getDataSource(){
		if(dataSource == null){
			initialization();
		}
		return dataSource;
	}
	/**
	 * 初始化数据库连接池
	 */
	public static void initialization(){
		//1、先判断dataSource是否==null，是，先关闭
		//2、c3p0连接池创建并返回DataSource
		if (dataSource != null) {
			//关闭dataSource
			((ComboPooledDataSource) dataSource).close();
		}
		dataSource=new ComboPooledDataSource();
		try {
			((ComboPooledDataSource) dataSource).setDriverClass("com.mysql.cj.jdbc.Driver");
			((ComboPooledDataSource) dataSource).setJdbcUrl("jdbc:"+sid+"://"+ip+":"+port+"/airticket?characterEncoding=utf-8&serverTimezone=GMT&useSSL=false");
			((ComboPooledDataSource) dataSource).setUser(user);
			((ComboPooledDataSource) dataSource).setPassword(password);
			((ComboPooledDataSource) dataSource).setLoginTimeout(3000);
			((ComboPooledDataSource) dataSource).setMaxPoolSize(25);
		} catch (PropertyVetoException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public static void closeDataSource() {
		if (dataSource != null) {
			//关闭dataSource
			((ComboPooledDataSource) dataSource).close();
			dataSource=null;
		}
	}
	public static void main(String[] args) {
		DataSource dataSource = DataSourceConfig.getDataSource();
		Connection conn;
		try {
			conn = dataSource.getConnection();
			System.out.println(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
