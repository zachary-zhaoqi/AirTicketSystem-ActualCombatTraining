package com.softfz.ui.mainUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;

import com.softfz.RMIFactory;
import com.softfz.SystemContext;
import com.softfz.service.ISystemService;
import com.softfz.ui.Login;

public class BackManagerFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8036301104688701162L;

	private JSplitPane WorkSplitPane;

	private JPanel workPanel;
	private MenuPanel menuPanel;
	private CardLayout workPanelLayout;

	public BackManagerFrame() {
		this.setTitle("航空售票系统-后台管理");
		initGUI();
		listener();
	}
	
	private void listener(){
		BackManagerFrame.this.addWindowListener(new myWindowsListener());
	}
	
	private class myWindowsListener extends WindowAdapter{

		@Override
		public void windowClosing(WindowEvent e) {
			super.windowClosing(e);
					
			System.exit(0);//调试后更改以下代码段
			
//			int exitFlag = JOptionPane.showConfirmDialog(BackManagerFrame.this, "确认退出后台管理系统 ？", "退出确认", JOptionPane.YES_NO_OPTION);
//			if(exitFlag == 0){
//				ISystemService systemService = RMIFactory.getService();
//				if(systemService == null){
//					JOptionPane.showMessageDialog(null, "丢失与服务器的连接，航空售票系统即将退出!\n如果重复出现该问题，请检查网络或联系系统管理员\n(可能原因：服务器已经关闭)", "错误--丢失与航空票务系统服务器的连接", JOptionPane.ERROR_MESSAGE);
//					System.exit(0);
//				}
//				try {
//					systemService.showMsgToServer("管理员： " + SystemContext.LOGIN_SYSTEMUSER.getUsername() + " 退出");
//				} catch (RemoteException e1) {
//					e1.printStackTrace();
//				}
//				System.exit(0);
//			}
		}
		
	}

	private void initGUI() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		{
			WorkSplitPane = new JSplitPane();
			WorkSplitPane.setOneTouchExpandable(true);
			getContentPane().add(WorkSplitPane, BorderLayout.CENTER);

			
				workPanel = new JPanel();
				WorkSplitPane.add(workPanel, JSplitPane.RIGHT);
				workPanelLayout = new CardLayout();
				workPanel.setLayout(workPanelLayout);
				workPanel.setPreferredSize(new java.awt.Dimension(150, 600));
			
				menuPanel = new MenuPanel(workPanel, workPanelLayout);
				WorkSplitPane.add(menuPanel, JSplitPane.LEFT);

			
		}
		pack();
		this.setSize(800, 600);
	}

	public static void main(String[] args) {
		
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				BackManagerFrame main = new BackManagerFrame();				
				main.setLocationRelativeTo(null);
				main.setVisible(true);
			}
		});
	}
}
