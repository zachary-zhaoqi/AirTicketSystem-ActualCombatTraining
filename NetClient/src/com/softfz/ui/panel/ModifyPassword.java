package com.softfz.ui.panel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.softfz.NetContext;
import com.softfz.RMIFactory;
import com.softfz.resources.Resources;
import com.softfz.service.INetService;
import com.softfz.utils.CheckUtil;

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
public class ModifyPassword extends javax.swing.JPanel {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JButton jButton_Cancel;
	private JButton jButton_Modify;
	private JTextField jTextField_NewPwd2;
	private JTextField jTextField_NewPwd1;
	private JTextField jTextField_OldPwd;
	private int errorNoticeInt;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new ModifyPassword());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public ModifyPassword() {
		super();
		initGUI();
		listener();
	}
	
	private void listener(){
		ButtonListener bl = new ButtonListener();
		jButton_Modify.addActionListener(bl);
		jButton_Cancel.addActionListener(bl);
		
	}
	
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if(cmd.equals("修改")){
				int netid=NetContext.LOGIN_NETDEALER.getNetid();
				String oldPassword=jTextField_OldPwd.getText();
				String newPwd1=jTextField_NewPwd1.getText();
				String newPwd2=jTextField_NewPwd2.getText();
				try {
					checkIsError(CheckUtil.checkPwd(newPwd1));
					checkIsError(CheckUtil.checkTwoPwd(newPwd1,newPwd2));
					modifyPassword(netid, oldPassword, newPwd1);
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
							e1.getMessage(), "一个通知", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}if(cmd.equals("取消")){
				cleanInput();
			}
			
		}
		
	}
	/**
	 * 修改密码
	 * @param netid
	 * @param oldPassword
	 * @param newPassword
	 * @throws RemoteException 
	 */
	private void modifyPassword	(int netid, String oldPassword, String newPassword) throws RemoteException{
		INetService netService=RMIFactory.getService();
		if (netService.updateNetClientPwd(netid,oldPassword, newPassword)) {
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
					"修改成功", "一个通知", JOptionPane.INFORMATION_MESSAGE);
			NetContext.LOGIN_NETDEALER.setPassword(newPassword);
		} else {
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
					"修改失败", "一个通知", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 *清空界面上的输入项 
	 */
	private void cleanInput(){
		jTextField_OldPwd.setText("");
		jTextField_NewPwd1.setText("");
		jTextField_NewPwd2.setText("");
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(794, 498));
			this.setSize(794, 498);
			this.setLayout(null);
			{
				jLabel1 = new JLabel();
				this.add(jLabel1);
				jLabel1.setText("\u65e7\u5bc6\u7801\uff1a");
				jLabel1.setBounds(224, 153, 48, 17);
				jLabel1.setSize(70, 20);
			}
			{
				jLabel2 = new JLabel();
				this.add(jLabel2);
				jLabel2.setText("\u65b0\u5bc6\u7801\uff1a");
				jLabel2.setBounds(224, 195, 48, 17);
				jLabel2.setSize(70, 20);
			}
			{
				jLabel3 = new JLabel();
				this.add(jLabel3);
				jLabel3.setText("\u786e\u8ba4\u5bc6\u7801\uff1a");
				jLabel3.setBounds(224, 239, 60, 17);
				jLabel3.setSize(70, 20);
			}
			{
				jTextField_OldPwd = new JTextField();
				this.add(jTextField_OldPwd);
				jTextField_OldPwd.setBounds(319, 150, 202, 24);
			}
			{
				jTextField_NewPwd1 = new JTextField();
				this.add(jTextField_NewPwd1);
				jTextField_NewPwd1.setBounds(319, 192, 202, 24);
			}
			{
				jTextField_NewPwd2 = new JTextField();
				this.add(jTextField_NewPwd2);
				jTextField_NewPwd2.setBounds(319, 236, 202, 24);
			}
			{
				jButton_Modify = new JButton();
				this.add(jButton_Modify);
				jButton_Modify.setText("\u4fee\u6539");
				jButton_Modify.setBounds(224, 319, 90, 46);
				jButton_Modify.setIcon(Resources.getPassswordIcon());
			}
			{
				jButton_Cancel = new JButton();
				this.add(jButton_Cancel);
				jButton_Cancel.setText("\u53d6\u6d88");
				jButton_Cancel.setBounds(431, 319, 90, 46);
				jButton_Cancel.setIcon(Resources.getStopIcon());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void checkIsError(int inputTextInt) throws Exception{
		String errorNoticeString = CheckUtil.getMapNoticeInfo(inputTextInt);
		if(!errorNoticeString.equals("OK")){
			throw new Exception("修改失败：" + errorNoticeString);
		}
	}

}
