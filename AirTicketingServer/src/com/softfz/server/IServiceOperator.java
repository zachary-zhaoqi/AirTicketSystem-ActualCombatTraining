package com.softfz.server;

import java.io.IOException;

/**
 * 该接口定义了服务器管理子系统相关业务逻辑
 * @author Administrator
 *
 */
public interface IServiceOperator {
	
	
	/**
	 * 启动服务器
	 * @return 
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public boolean start();
	
	
	/**
	 * 停止服务
	 */
	public boolean stop();
	
	
	/**
	 * 修改服务器通信端口
	 */
	public void updateNetPort(String rmiPort, String socketPort);
	
	
	/**
	 * 读取配置文件初始化通讯端口		//RMI端口
	 */
	public void initNetPort();
		
	
	/**
	 * 修改数据库连接信息并保存到配置文件中
	 */
	public void updateDbConfig(String ip, String port, String sid, String user,String password);
	
	
	/**
	 * 读取配置文件初始化数据库连接相关信息
	 */
	public void initDbConfig();
}
