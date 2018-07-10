package com.softfz.config;
/**
 * 服务器信息对象，成员变量采用全局静态成员变量方式
 * @author Administrator
 *
 */
public class ServerConfig {
	/**
	 * RMI通讯端口（防火墙需要开放该端口）
	 */
//	public static String RMI_PORT = "1099";
	public static String RMI_PORT = "6600";
	/**
	 * 服务端和客户端SOCKET通讯端口(防火墙需要开放该端口)
	 */
	public static String SERVER_CLIENT_PORT = "1024";
	/**
	 * 服务器IP地址，客户端连接服务器时需要该属性
	 */
	public static String RMI_IP = "127.0.0.1";
//	public static String RMI_IP = "localhost";
}
