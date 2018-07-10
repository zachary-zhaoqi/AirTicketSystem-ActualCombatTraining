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
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.softfz.RMIFactory;
import com.softfz.SystemContext;
import com.softfz.service.INetService;
import com.softfz.service.ISystemService;
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
public class PasswordChangePanel extends javax.swing.JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8478976330351803359L;
	private JPanel jPanel1;
	private JLabel jLabel3;
	private JTextField jTextField_OldPassword;
	private JButton jButton_Cancel;
	private JButton jButton_OK;
	private JTextField jTextField_NewPassword2;
	private JTextField jTextField_NewPassword1;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private int errorNoticeInt;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new PasswordChangePanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public PasswordChangePanel() {
		super();
		initGUI();
		listener();
	}
	
	private void listener(){
		ButtonListener bl = new ButtonListener();
		jButton_OK.addActionListener(bl);
		jButton_Cancel.addActionListener(bl);
		
	}
	
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if(cmd.equals("确定修改")){
				if (jTextField_NewPassword1.getText().equals(jTextField_NewPassword2.getText())) {
					ISystemService systemService=RMIFactory.getService();
					try {
						if(systemService.updatePassword(SystemContext.LOGIN_SYSTEMUSER.getUsername(), jTextField_OldPassword.getText(), jTextField_NewPassword1.getText())) {
							JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
									"密码修改成功", "一个很温馨的通知", JOptionPane.INFORMATION_MESSAGE); 
						}else {
							JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
									"密码修改失败", "一个令人难过的通知", JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (RemoteException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
				}
				
			}else if(cmd.equals("清空输入")){
				cleanInput();
			}
			
		}
		
	}
	
	/**
	 * 修改密码
	 * @param username
	 * @param oldPassword
	 * @param newPassword
	 */
	private void modifyPassword(String username, String oldPassword, String newPassword){

	}
	
	/**
	 *清空界面上的输入项 
	 */
	private void cleanInput(){
		jTextField_OldPassword.setText("");
		jTextField_NewPassword1.setText("");
		jTextField_NewPassword2.setText("");
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(630, 554));
			this.setSize(630, 554);
			{
				jPanel1 = new JPanel();
				this.add(jPanel1);
				jPanel1.setSize(630, 554);
				jPanel1.setPreferredSize(new java.awt.Dimension(578, 419));
				jPanel1.setLayout(null);
				{
					jLabel1 = new JLabel();
					jPanel1.add(jLabel1);
					jLabel1.setText("\u539f\u59cb\u5bc6\u7801\uff1a");
					jLabel1.setBounds(148, 126, 80, 20);
				}
				{
					jLabel2 = new JLabel();
					jPanel1.add(jLabel2);
					jLabel2.setText("\u8f93\u5165\u65b0\u5bc6\u7801\uff1a");
					jLabel2.setBounds(148, 166, 80, 20);
				}
				{
					jLabel3 = new JLabel();
					jPanel1.add(jLabel3);
					jLabel3.setText("\u786e\u8ba4\u65b0\u5bc6\u7801\uff1a");
					jLabel3.setBounds(148, 208, 80, 20);
				}
				{
					jTextField_OldPassword = new JTextField();
					jPanel1.add(jTextField_OldPassword);
					jTextField_OldPassword.setBounds(228, 125, 201, 24);
				}
				{
					jTextField_NewPassword1 = new JTextField();
					jPanel1.add(jTextField_NewPassword1);
					jTextField_NewPassword1.setBounds(228, 165, 201, 24);
				}
				{
					jTextField_NewPassword2 = new JTextField();
					jPanel1.add(jTextField_NewPassword2);
					jTextField_NewPassword2.setBounds(228, 207, 201, 24);
				}
				{
					jButton_OK = new JButton();
					jPanel1.add(jButton_OK);
					jButton_OK.setText("\u786e\u5b9a\u4fee\u6539");
					jButton_OK.setBounds(148, 310, 94, 36);
				}
				{
					jButton_Cancel = new JButton();
					jPanel1.add(jButton_Cancel);
					jButton_Cancel.setText("\u6e05\u7a7a\u8f93\u5165");
					jButton_Cancel.setBounds(335, 310, 94, 36);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void checkIsError(int inputTextInt) throws Exception{
		String errorNoticeString = CheckUtil.getMapNoticeInfo(inputTextInt);
		if(!errorNoticeString.equals("OK")){
			throw new Exception(errorNoticeString);
		}
	}

}
