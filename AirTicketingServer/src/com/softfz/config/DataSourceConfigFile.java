package com.softfz.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 该类负责保存数据库相关的连接信息到配置文件中
 * @author Administrator
 *
 */
public class DataSourceConfigFile {
	private static final String DATASOURCE_CONFIG_FILE = "config/datasource.properties";
	/**
	 * 读取数据库连接信息配置文件，把信息存放在DataSourceConfig中
	 */
	public static void readDataSourceConfig(){
		//TODO:
		try {
			FileInputStream fileInputStream=new FileInputStream(DATASOURCE_CONFIG_FILE);
			Properties properties=new Properties();
			properties.load(fileInputStream);
			DataSourceConfig.ip=properties.getProperty("ip");
			DataSourceConfig.password=properties.getProperty("password");
			DataSourceConfig.sid=properties.getProperty("sid");
			DataSourceConfig.user=properties.getProperty("user");
			DataSourceConfig.port=properties.getProperty("port");
			
		} 
		catch (FileNotFoundException fileNotFoundException) {
			// TODO Auto-generated catch block
			fileNotFoundException.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 从DataSourceConfig中读取配置信息保存到配置文件中
	 */
	public static void saveDataSourceConfig(){
		//TODO:
		try {
			FileOutputStream fileOutputStream=new FileOutputStream(DATASOURCE_CONFIG_FILE);
			Properties properties=new Properties();
			properties.setProperty("sid", DataSourceConfig.sid);
			properties.setProperty("user", DataSourceConfig.user);
			properties.setProperty("port", DataSourceConfig.port);
			properties.setProperty("password", DataSourceConfig.password);
			properties.setProperty("ip", DataSourceConfig.ip);
			properties.store(fileOutputStream, DATASOURCE_CONFIG_FILE);
			} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	
	public static void main(String[] args) {
		saveDataSourceConfig();
	}
}
