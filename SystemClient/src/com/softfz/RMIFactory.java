package com.softfz;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
					"可能是远程服务器未开启，请联系服务器管理员！", "一个令人难过的通知", JOptionPane.INFORMATION_MESSAGE); 
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
					"可能是远程服务器未开启，请联系服务器管理员！", "一个令人难过的通知", JOptionPane.INFORMATION_MESSAGE); 
		} catch (NotBoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
					"可能是远程服务器未开启，请联系服务器管理员！", "一个令人难过的通知", JOptionPane.INFORMATION_MESSAGE); 
		}
		return null;
	}
}
