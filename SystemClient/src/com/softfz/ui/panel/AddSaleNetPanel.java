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

import com.softfz.RMIFactory;
import com.softfz.SystemContext;
import com.softfz.model.NetDealer;
import com.softfz.resources.Resources;
import com.softfz.service.ISystemService;
import com.softfz.ui.MessagePanel;
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
public class AddSaleNetPanel extends javax.swing.JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3735022488370529370L;
	
	private JPanel jPanel_UP;
	private JButton jButton_OK;
	private JButton jButton_Cancel;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JTextField jTextField_Address;
	private JTextField jTextField_Tel;
	private JTextField jTextField_Director;
	private JLabel jLabel3;
	private JTextField jTextField_NetName;
	private JLabel jLabel2;
	private JTextField jTextField_NetCode;
	private JLabel jLabel1;
	private JPanel jPanel_DOWN;
	private int errorNoticeInt;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new AddSaleNetPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public AddSaleNetPanel() {
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
			throw new Exception("新增销售网点失败：" + errorNoticeString);
		}
	}
	
	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if (cmd.equals("保存")) {
				String netcode=jTextField_NetCode.getText();
				String netname=jTextField_NetName.getText();
				String director=jTextField_Director.getText();
				String telephone=jTextField_Tel.getText();
				String state="0";
				String address=jTextField_Address.getText();
				try {
					checkIsError(CheckUtil.checkNetCode(netcode));
					checkIsError(CheckUtil.checkNetName(netname));
					checkIsError(CheckUtil.checkDirector(director));
					checkIsError(CheckUtil.checkPhoneNumber(telephone));
					checkIsError(CheckUtil.checkAddress(address));
					addNewSalNet(netcode, netname, director, telephone, address);
				} catch (Exception e2) {
					// TODO: handle exception
					MessagePanel.showInfo(AddSaleNetPanel.this, e2.getMessage());
					return;
				}
				
			}else if (cmd.equals("取消")) {
				jTextField_NetCode.setText("");
				jTextField_NetName.setText("");
				jTextField_Director.setText("");
				jTextField_Tel.setText("");
				jTextField_Address.setText("");
			}
		}
	}
	
	
	private void addNewSalNet(String netcode, String netname, String director, String telephone, String address){
		String password="888888";
		String state="0";
		ISystemService service=RMIFactory.getService();
		try {
			if(service.addNetDealer(SystemContext.LOGIN_SYSTEMUSER.getUserid(), netcode, netname, password, director, telephone, state, address))			
			{
				JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
						"新增销售网点成功", "一个很温馨的通知", JOptionPane.INFORMATION_MESSAGE); 
			}else {
				JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
						"新增销售网点失败", "一个令人难过的通知", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (RemoteException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
					"出现错误了，有可能是远程服务器关闭", "一个令人难过的通知", JOptionPane.INFORMATION_MESSAGE);
		}	
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
				jPanel_UP.setBorder(BorderFactory.createTitledBorder("\u9500\u552e\u7f51\u70b9\u57fa\u672c\u4fe1\u606f"));
				jPanel_UP.setBounds(1, 5, 628, 113);
				jPanel_UP.setLayout(null);
				{
					jLabel1 = new JLabel();
					jPanel_UP.add(jLabel1);
					jLabel1.setText("\u7f51\u70b9\u7f16\u53f7\uff1a");
					jLabel1.setBounds(47, 44, 27, 17);
					jLabel1.setSize(80, 20);
				}
				{
					jTextField_NetCode = new JTextField();
					jPanel_UP.add(jTextField_NetCode);
					jTextField_NetCode.setBounds(109, 44, 148, 24);
				}
				{
					jLabel2 = new JLabel();
					jPanel_UP.add(jLabel2);
					jLabel2.setText("\u7f51\u70b9\u540d\u79f0\uff1a");
					jLabel2.setBounds(323, 45, 80, 20);
				}
				{
					jTextField_NetName = new JTextField();
					jPanel_UP.add(jTextField_NetName);
					jTextField_NetName.setBounds(386, 44, 164, 24);
				}
			}
			{
				jPanel_DOWN = new JPanel();
				this.add(jPanel_DOWN);
				jPanel_DOWN.setSize(628, 10);
				jPanel_DOWN.setPreferredSize(new java.awt.Dimension(628, 340));
				jPanel_DOWN.setBorder(BorderFactory.createTitledBorder("\u9500\u552e\u7f51\u70b9\u8be6\u7ec6\u4fe1\u606f"));
				jPanel_DOWN.setBounds(1, 123, 628, 340);
				jPanel_DOWN.setLayout(null);
				{
					jLabel3 = new JLabel();
					jPanel_DOWN.add(jLabel3);
					jLabel3.setText("\u8d1f\u8d23\u4eba\uff1a");
					jLabel3.setBounds(65, 89, 80, 20);
				}
				{
					jLabel4 = new JLabel();
					jPanel_DOWN.add(jLabel4);
					jLabel4.setText("\u8054\u7cfb\u7535\u8bdd\uff1a");
					jLabel4.setBounds(65, 145, 80, 20);
				}
				{
					jLabel5 = new JLabel();
					jPanel_DOWN.add(jLabel5);
					jLabel5.setText("\u8054\u7cfb\u5730\u5740\uff1a");
					jLabel5.setBounds(65, 203, 80, 20);
				}
				{
					jTextField_Director = new JTextField();
					jPanel_DOWN.add(jTextField_Director);
					jTextField_Director.setBounds(144, 88, 387, 24);
				}
				{
					jTextField_Tel = new JTextField();
					jPanel_DOWN.add(jTextField_Tel);
					jTextField_Tel.setBounds(145, 144, 386, 24);
				}
				{
					jTextField_Address = new JTextField();
					jPanel_DOWN.add(jTextField_Address);
					jTextField_Address.setBounds(145, 202, 386, 24);
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
