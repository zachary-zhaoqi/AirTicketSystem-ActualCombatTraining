package com.softfz.ui.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

import com.softfz.RMIFactory;
import com.softfz.model.SystemUser;
import com.softfz.resources.Resources;
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
public class AddAdminPanel extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8287377447681724479L;
	private JPanel jPanel_UP;
	private JButton jButton_OK;
	private JButton jButton_Cancel;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JTextField jTextField_Telephone;
	private JTextField jTextField_RealName;
	private JTextField jTextField_Email;
	private JLabel jLabel3;
	private JTextField jTextField_Password;
	private JLabel jLabel2;
	private JTextField jTextField_Name;
	private JLabel jLabel1;
	private JPanel jPanel_DOWN;
	private int errorNoticeInt;
	
	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new AddAdminPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public AddAdminPanel() {
		super();
		initGUI();
		listener();
	}
	
	private void listener(){
		ButtonListener bl = new ButtonListener();
		jButton_OK.addActionListener(bl);
		jButton_Cancel.addActionListener(bl);
	}
	
	
	private void checkIsError(int inputTextInt) throws Exception{
		String errorNoticeString = CheckUtil.getMapNoticeInfo(inputTextInt);
		if(!errorNoticeString.equals("OK")){
			throw new Exception("新增用户失败：" + errorNoticeString);
		}
	}
	
	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if (cmd.equals("保存")) {
				ISystemService service=RMIFactory.getService();
				try {
					if(service.addSystemUser(jTextField_Name.getText(), jTextField_Password.getText(), jTextField_Email.getText(), jTextField_RealName.getText(), jTextField_Telephone.getText())) {
						JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
								"新增管理员成功", "一个很温馨的通知", JOptionPane.INFORMATION_MESSAGE); 
					}else {
						JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
								"新增管理员失败", "一个很温馨的通知", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (RemoteException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}else if (cmd.equals("取消")) {
				jTextField_Name.setText("");
				jTextField_Password.setText("");
				jTextField_Email.setText("");
				jTextField_RealName.setText("");
				jTextField_Telephone.setText("");
			}
		}
	}
	
	
	private void addNewUser(String name, String password, String email, String realname, String telephone){

	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(630, 554));
			this.setSize(630, 554);
			this.setLayout(null);
			{
				jPanel_UP = new JPanel();
				this.add(jPanel_UP);
				jPanel_UP.setPreferredSize(new java.awt.Dimension(628, 113));
				jPanel_UP.setBorder(BorderFactory.createTitledBorder(null, "\u767b\u9646\u4fe1\u606f", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION));
				jPanel_UP.setBounds(1, 5, 628, 113);
				jPanel_UP.setLayout(null);
				{
					jLabel1 = new JLabel();
					jPanel_UP.add(jLabel1);
					jLabel1.setText("\u8d26\u53f7:");
					jLabel1.setBounds(47, 44, 27, 17);
					jLabel1.setSize(80, 20);
				}
				{
					jTextField_Name = new JTextField();
					jPanel_UP.add(jTextField_Name);
					jTextField_Name.setBounds(88, 44, 148, 24);
				}
				{
					jLabel2 = new JLabel();
					jPanel_UP.add(jLabel2);
					jLabel2.setText("\u5bc6\u7801\uff1a");
					jLabel2.setBounds(323, 45, 80, 20);
				}
				{
					jTextField_Password = new JTextField();
					jPanel_UP.add(jTextField_Password);
					jTextField_Password.setBounds(365, 44, 164, 24);
					jTextField_Password.setText("888888");
					jTextField_Password.setEditable(false);
				}
			}
			{
				jPanel_DOWN = new JPanel();
				this.add(jPanel_DOWN);
				jPanel_DOWN.setSize(628, 10);
				jPanel_DOWN.setPreferredSize(new java.awt.Dimension(628, 340));
				jPanel_DOWN.setBorder(BorderFactory.createTitledBorder("\u4e2a\u4eba\u4fe1\u606f"));
				jPanel_DOWN.setBounds(1, 123, 628, 340);
				jPanel_DOWN.setLayout(null);
				{
					jLabel3 = new JLabel();
					jPanel_DOWN.add(jLabel3);
					jLabel3.setText("\u7535\u5b50\u90ae\u7bb1\uff1a");
					jLabel3.setBounds(65, 89, 80, 20);
				}
				{
					jLabel4 = new JLabel();
					jPanel_DOWN.add(jLabel4);
					jLabel4.setText("\u771f\u5b9e\u59d3\u540d\uff1a");
					jLabel4.setBounds(65, 145, 80, 20);
				}
				{
					jLabel5 = new JLabel();
					jPanel_DOWN.add(jLabel5);
					jLabel5.setText("\u7535\u8bdd\u53f7\u7801\uff1a");
					jLabel5.setBounds(65, 203, 80, 20);
				}
				{
					jTextField_Email = new JTextField();
					jPanel_DOWN.add(jTextField_Email);
					jTextField_Email.setBounds(144, 88, 387, 24);
				}
				{
					jTextField_RealName = new JTextField();
					jPanel_DOWN.add(jTextField_RealName);
					jTextField_RealName.setBounds(145, 144, 386, 24);
				}
				{
					jTextField_Telephone = new JTextField();
					jPanel_DOWN.add(jTextField_Telephone);
					jTextField_Telephone.setBounds(145, 202, 386, 24);
				}
			}
			{
				jButton_OK = new JButton();
				this.add(jButton_OK);
				jButton_OK.setText("\u4fdd\u5b58");
				jButton_OK.setBounds(130, 490, 94, 36);
				jButton_OK.setIcon(Resources.getSaveIcon());
			}
			{
				jButton_Cancel = new JButton();
				this.add(jButton_Cancel);
				jButton_Cancel.setText("\u53d6\u6d88");
				jButton_Cancel.setBounds(382, 490, 94, 36);
				jButton_Cancel.setIcon(Resources.getStopIcon());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
