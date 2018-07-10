package com.softfz.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import com.softfz.config.ServerConfig;



/**
 * 该类负责保存服务器通讯端口以及IP地址到配置文件中
 * @author Administrator
 *
 */
public class ServerConfigFile {
	private static final String SERVER_PORT_CONFIG = "config/server.properties";
	
	private ServerConfigFile(){
		
	}
	
	/**
	 * 读取配置文件到ServerConfig中
	 */
	public static void readServerConfig(){
		//TODO：2018-6-13
		try {
			FileInputStream fileInputStream=new FileInputStream(SERVER_PORT_CONFIG);
			Properties properties=new Properties();
			properties.load(fileInputStream);
			ServerConfig.RMI_PORT=properties.getProperty("RMI_PORT");
			ServerConfig.SERVER_CLIENT_PORT=properties.getProperty("SERVER_CLIENT_PORT");
			ServerConfig.RMI_IP=properties.getProperty("RMI_IP");
			
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
	 * 从ServerConfig中读取配置信息,保存服务器通讯信息到配置文件中
	 */
	public static void saveServerConfig(){
		//TODO：2018-6-13：把ServerConfig.***变量保存到配置文件
		try {
			FileOutputStream fileOutputStream=new FileOutputStream(SERVER_PORT_CONFIG);
			Properties properties=new Properties();
			properties.setProperty("RMI_IP", ServerConfig.RMI_IP);
			properties.setProperty("SERVER_CLIENT_PORT", ServerConfig.SERVER_CLIENT_PORT);
			properties.setProperty("RMI_PORT", ServerConfig.RMI_PORT);
			properties.store(fileOutputStream, SERVER_PORT_CONFIG);
			} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
