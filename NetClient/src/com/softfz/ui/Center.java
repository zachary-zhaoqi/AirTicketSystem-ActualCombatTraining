package com.softfz.ui;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

import com.softfz.NetContext;
import com.softfz.RMIFactory;
import com.softfz.model.Flight;
import com.softfz.model.NetDealer;
import com.softfz.resources.Resources;
import com.softfz.service.INetService;
import com.softfz.ui.panel.FlightInfo;
import com.softfz.ui.panel.ModifyPassword;
import com.softfz.ui.panel.ReturnTicket;
import com.softfz.ui.panel.SalesCount;
import com.softfz.ui.panel.SignChange;
import com.softfz.ui.panel.Ticketing;
import com.softfz.ui.panel.Welcome;


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
public class Center extends javax.swing.JFrame {
	private JPanel jPanel_UP;
	private JPanel jPanel_DOWN;
	private JButton jButton_ModifyPassword;
	private JButton jButton_FlightInfo;
	private JButton jButton_ReturnTicket;
	private JButton jButton_SalesCount;
	private JButton jButton_SignChange;
	private JPanel modifyPassword = new ModifyPassword();
	private JPanel flightInfo = new FlightInfo(Center.this);
	private JPanel returnTicket = new ReturnTicket();
	private JPanel signChange = new SignChange();
	private JPanel salesCount = new SalesCount();
	private JPanel welcome = new Welcome();
	private Ticketing ticketing;
	private CardLayout cardLayout;

	/**
	* Auto-generated main method to display this JFrame
	*/
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Center inst = new Center();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public Center() {
		//获取当前登陆的销售网点对象
		NetDealer dealer = NetContext.LOGIN_NETDEALER;
		setTitle("销售网点客户端：" + dealer.getNetname());
		
		initGUI();
		//jButton_SalesCount.grabFocus();//设置焦点方法1
		jPanel_DOWN.requestFocus();//设置焦点方法2
		listener();
	}
	
	private void listener(){
		ButtonListener bl = new ButtonListener();
		jButton_ModifyPassword.addActionListener(bl);
		jButton_FlightInfo.addActionListener(bl);
		jButton_ReturnTicket.addActionListener(bl);
		jButton_SignChange.addActionListener(bl);
		jButton_SalesCount.addActionListener(bl);
		Center.this.addWindowListener(new myWindowsListener());
	}
	
	private class myWindowsListener extends WindowAdapter{

		@Override
		public void windowClosing(WindowEvent e) {
			super.windowClosing(e);
			int exitFlag = JOptionPane.showConfirmDialog(Center.this, "确认退出航空售票系统 ？", "退出确认", JOptionPane.YES_NO_OPTION);
			if(exitFlag == 0){
				INetService netService = RMIFactory.getService();
				if(netService == null){
						JOptionPane.showMessageDialog(null, "丢失与服务器的连接，航空售票系统即将退出!\n如果重复出现该问题，请检查网络或联系系统管理员\n(可能原因：服务器已经关闭)", "错误--丢失与航空票务系统服务器的连接", JOptionPane.ERROR_MESSAGE);
						System.exit(0);
				}
				
				try {
					netService.showMsgToServer("销售网点： " + NetContext.LOGIN_NETDEALER.getNetname() + " 退出");
				} catch (RemoteException e1) {
					e1.printStackTrace();
					
				}
				System.exit(0);
			}
		}
		
	}
	
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if(cmd.equals("修改密码")) {
				ButtonColor(0);
				cardLayout.show(jPanel_DOWN, "ModifyPassword");
				jPanel_DOWN.requestFocus();
			}else if(cmd.equals("航班查询")){
				ButtonColor(1);
				cardLayout.show(jPanel_DOWN, "FlightInfo");
				jPanel_DOWN.requestFocus();
			}else if(cmd.equals("退票")){
				ButtonColor(2);
				cardLayout.show(jPanel_DOWN, "ReturnTicket");
				jPanel_DOWN.requestFocus();
			}else if(cmd.equals("转签")){
				ButtonColor(3);
				cardLayout.show(jPanel_DOWN, "SignChange");
				jPanel_DOWN.requestFocus();
			}else if(cmd.equals("销售统计")){
				ButtonColor(4);
				cardLayout.show(jPanel_DOWN, "SalesCount");
				jPanel_DOWN.requestFocus();
			}
		}
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setPreferredSize(new java.awt.Dimension(800, 600));
			this.setResizable(false);//禁止窗口拉伸
			{
				jPanel_UP = new JPanel();
				FlowLayout jPanel_UPLayout = new FlowLayout();
				getContentPane().add(jPanel_UP);
				jPanel_UP.setBounds(0, 0, 784, 74);
				jPanel_UP.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
				jPanel_UP.setBackground(new java.awt.Color(255,255,128));
				jPanel_UP.setLayout(jPanel_UPLayout);
				jPanel_UP.setSize(800, 74);
				{ 
					jButton_ModifyPassword = new JButton();
					jPanel_UP.add(jButton_ModifyPassword);
					jButton_ModifyPassword.setText("\u4fee\u6539\u5bc6\u7801");
					jButton_ModifyPassword.setIcon(Resources.getPassswordIcon());
					jButton_ModifyPassword.setBackground(new java.awt.Color(255,255,255));
					jButton_ModifyPassword.setSize(120, 36);
					jButton_ModifyPassword.setPreferredSize(new java.awt.Dimension(120, 36));
				}
				{
					jButton_FlightInfo = new JButton();
					jPanel_UP.add(jButton_FlightInfo);
					jButton_FlightInfo.setText("\u822a\u73ed\u67e5\u8be2");
					jButton_FlightInfo.setIcon(Resources.getFindIcon());
					jButton_FlightInfo.setBackground(new java.awt.Color(255,255,255));
					jButton_FlightInfo.setSize(120, 36);
					jButton_FlightInfo.setPreferredSize(new java.awt.Dimension(120, 36));
				}
				{
					jButton_ReturnTicket = new JButton();
					jPanel_UP.add(jButton_ReturnTicket);
					jButton_ReturnTicket.setText("\u9000\u7968");
					jButton_ReturnTicket.setIcon(Resources.getUndoIcon());
					jButton_ReturnTicket.setPreferredSize(new java.awt.Dimension(120, 36));
					jButton_ReturnTicket.setBackground(new java.awt.Color(255,255,255));
				}
				{
					jButton_SignChange = new JButton();
					jPanel_UP.add(jButton_SignChange);
					jButton_SignChange.setText("\u8f6c\u7b7e");
					jButton_SignChange.setIcon(Resources.getTicketIcon());
					jButton_SignChange.setPreferredSize(new java.awt.Dimension(120, 36));
					jButton_SignChange.setBackground(new java.awt.Color(255,255,255));
				}
				{
					jButton_SalesCount = new JButton();
					jPanel_UP.add(jButton_SalesCount);
					jButton_SalesCount.setText("\u9500\u552e\u7edf\u8ba1");
					jButton_SalesCount.setIcon(Resources.getPaperIcon());
					jButton_SalesCount.setPreferredSize(new java.awt.Dimension(120, 36));
					jButton_SalesCount.setBackground(new java.awt.Color(255,255,255));
				}
			}
			{
				jPanel_DOWN = new JPanel();
				cardLayout = new CardLayout();
				jPanel_DOWN.setLayout(cardLayout);
				
				jPanel_DOWN.add("Welcome", welcome);
				jPanel_DOWN.add("ModifyPassword", modifyPassword);
				jPanel_DOWN.add("FlightInfo", flightInfo);
				jPanel_DOWN.add("ReturnTicket", returnTicket);
				jPanel_DOWN.add("SignChange", signChange);
				jPanel_DOWN.add("SalesCount", salesCount);
				
				cardLayout.show(jPanel_DOWN, "Welcome");
				getContentPane().add(jPanel_DOWN);
				jPanel_DOWN.setBounds(0, 74, 794, 498);//可以拉伸时候784,488。不可拉伸时候794,498
			}
			pack();
			this.setSize(800, 600);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	/**
	 * 将选中的按钮设置成橙色
	 * @param ButtonNum
	 */
	private void ButtonColor (int ButtonNum){
		JButton[] button = new JButton[]{jButton_ModifyPassword, jButton_FlightInfo, jButton_ReturnTicket,
				jButton_SignChange, jButton_SalesCount};
		Boolean[] buttonFlag = new Boolean[5];
		for(int i=0; i<buttonFlag.length; i++){
			buttonFlag[i] = false;
		}
		switch(ButtonNum){
		case 0:
			buttonFlag[0] = true;
			break;
		case 1:
			buttonFlag[1] = true;
			break;
		case 2:
			buttonFlag[2] = true;
			break;
		case 3:
			buttonFlag[3] = true;
			break;
		case 4:
			buttonFlag[4] = true;
			break;
		}
		for(int i=0; i<buttonFlag.length; i++){
			if(buttonFlag[i]){
				button[i].setBackground(new java.awt.Color(255,128,64));
			}else{
				button[i].setBackground(new java.awt.Color(255,255,255));
			}
		}
	}
	
	/**
	 * 跳转到订票页面
	 * @param flight
	 * @param planDate
	 */
	public void goTicket(Flight flight, java.sql.Date planDate){
		if(ticketing == null){
			ticketing = new Ticketing(Center.this);
			jPanel_DOWN.add("Ticketing", ticketing);
		}
		ticketing.buildTicket(flight,planDate);
		cardLayout.show(jPanel_DOWN, "Ticketing");
	}
	
	/**
	 * 跳转到机票查询页面
	 */
	public void goFlightInfo(){
		ButtonColor(1);
		cardLayout.show(jPanel_DOWN, "FlightInfo");
		jPanel_DOWN.requestFocus();
	}	
	
}
