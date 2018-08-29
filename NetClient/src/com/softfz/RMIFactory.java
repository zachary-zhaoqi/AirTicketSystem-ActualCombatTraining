package com.softfz;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
					"可能是远程服务器未开启，请联系服务器管理员！", "一个令人难过的通知", JOptionPane.INFORMATION_MESSAGE); 
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
					"可能是远程服务器未开启，请联系服务器管理员！", "一个令人难过的通知", JOptionPane.INFORMATION_MESSAGE); 
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
					"可能是远程服务器未开启，请联系服务器管理员！", "一个令人难过的通知", JOptionPane.INFORMATION_MESSAGE); 
		}
		return null;
	}
}
