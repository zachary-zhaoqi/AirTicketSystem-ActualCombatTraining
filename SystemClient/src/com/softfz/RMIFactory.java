package com.softfz;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.softfz.config.ServerConfig;
import com.softfz.service.ISystemService;

public class RMIFactory {
	private RMIFactory(){}
	
	/**
	 * 获取系统管理端业务组件
	 * @return
	 */
	public static ISystemService getService(){
		
		try {
			return (ISystemService) Naming.lookup("rmi://"+ServerConfig.RMI_IP+":"+ServerConfig.RMI_PORT+"/systemService");
		} catch (MalformedURLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}
}
