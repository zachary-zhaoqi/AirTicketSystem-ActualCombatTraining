package com.softfz;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.softfz.config.ServerConfig;
import com.softfz.service.INetService;

public class RMIFactory {
	private RMIFactory(){}
	
	/**
	 * 获取销售网点业务组件
	 * @return
	 */
	public static INetService getService(){
		
		try {
			return (INetService)Naming.lookup("rmi://"+ServerConfig.RMI_IP+":"+ServerConfig.RMI_PORT+"/netService");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
