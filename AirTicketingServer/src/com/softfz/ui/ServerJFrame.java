package com.softfz.ui;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import com.softfz.resources.Resources;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class ServerJFrame extends javax.swing.JFrame {

	private JTabbedPane mainPane;
	private JPanel mainWinJPanel;
	private JPanel dbConfigJPanel;
	private JPanel serverPort;
	
	{
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ServerJFrame inst = new ServerJFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public ServerJFrame() {
		super();
		//TODO：初始化配置数据（从配置文件读取并更新到配置类中）
		initGUI();
		listener();
	}
	
	private void listener(){
		ServerJFrame.this.addWindowListener(new myWindowsListener());
	}
	
	private class myWindowsListener extends WindowAdapter{

		@Override
		public void windowClosing(WindowEvent e) {
			int exitFlag = JOptionPane.showConfirmDialog(ServerJFrame.this, "确认退出服务器 ？", "退出确认", JOptionPane.YES_NO_OPTION);
			if(exitFlag == 0){
				System.exit(0);
			}
		}
		
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			this.setTitle("\u7968\u52a1\u7cfb\u7edf\u670d\u52a1\u5668");
			pack();
			this.setSize(600, 400);
			this.setResizable(false);//禁止窗口拉伸
			
			JPanel conPane = (JPanel)this.getContentPane();
			mainPane = new JTabbedPane();
			mainWinJPanel = new MainWinJPanel();
			dbConfigJPanel = new DBConfigJPanel();
			serverPort = new ServerPortJPanel();
			
			mainPane.addTab("主窗口",Resources.getDisplayIcon(),mainWinJPanel);
			mainPane.addTab("数据库配置",Resources.getSaveIcon(),dbConfigJPanel);//图标未设
			mainPane.addTab("服务器端口",Resources.getSocketIcon(),serverPort);
			conPane.add(mainPane);
			
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
