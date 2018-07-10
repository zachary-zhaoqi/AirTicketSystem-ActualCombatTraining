package com.softfz.server;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMISocketFactory;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.softfz.config.DataSourceConfig;
import com.softfz.config.DataSourceConfigFile;
import com.softfz.config.ServerConfig;
import com.softfz.io.ServerConfigFile;
import com.softfz.service.INetService;
import com.softfz.service.ISystemService;
import com.softfz.service.NetServiceImpl;
import com.softfz.service.SystemServiceImpl;
import com.softfz.utils.CheckUtil;

public class ServerOperatorImpl implements IServiceOperator{

	private static IServiceOperator instance = new ServerOperatorImpl();
	private static INetService netService;
	private static ISystemService systemService;
	private static Registry registry;  //同一个服务对象控制启停
	
	
	private ServerOperatorImpl(){
		// 1、注册自定义的RMI SOCKET FACTORY：指定通讯端口（防火墙要开放该端口），防止被防火墙拦截
		try {
			RMISocketFactory.setSocketFactory(new CustomerSocketFactory());
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	/**
	 * 单例
	 * @return
	 */
	public static IServiceOperator getInstance(){
		return instance;
	}
	
	@Override
	public boolean start(){
		try {
			
			// 2.开启RMI服务注册通讯端口
			registry = LocateRegistry.createRegistry(6600);
			// 3.实例化业务组件
			netService = new NetServiceImpl();
			systemService=new SystemServiceImpl();
			// 4.将业务组件注册到RMI服务器上(可注册多个业务组件）
			Naming.rebind("rmi://"+ServerConfig.RMI_IP+":"+ServerConfig.RMI_PORT+"/netService", netService);
			Naming.rebind("rmi://"+ServerConfig.RMI_IP+":"+ServerConfig.RMI_PORT+"/systemService", systemService);
//			Naming.rebind(rmiHead+"netService", systemService);
			
			System.out.println("RMI服务已启动！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean stop(){
		try {
			if (registry != null) {
				Naming.unbind("rmi://"+ServerConfig.RMI_IP+":"+ServerConfig.RMI_PORT+"/netService");
				Naming.unbind("rmi://"+ServerConfig.RMI_IP+":"+ServerConfig.RMI_PORT+"/systemService");
				UnicastRemoteObject.unexportObject(registry, true);
				registry = null;
				System.out.println("RMI服务已停止！");
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public void updateNetPort(String rmiPort, String socketPort) {
		ServerConfig.RMI_PORT=rmiPort;
		ServerConfig.SERVER_CLIENT_PORT=socketPort;
	}

	@Override
	public void initNetPort() {
		ServerConfigFile.readServerConfig();
	}

	@Override
	public void updateDbConfig(String ip, String port, String sid, String user,
			String password) {
		DataSourceConfig.ip=ip;
		DataSourceConfig.port=port;
		DataSourceConfig.sid=sid;
		DataSourceConfig.user=user;
		DataSourceConfig.password=password;
		DataSourceConfigFile.saveDataSourceConfig();
		
	}

	@Override
	public void initDbConfig() {
		DataSourceConfigFile.readDataSourceConfig();
	}

	public static Registry getRegistry() {
		return registry;
	}

	public  void setRegistry(Registry registry) {
		ServerOperatorImpl.registry = registry;
	}

	
}
