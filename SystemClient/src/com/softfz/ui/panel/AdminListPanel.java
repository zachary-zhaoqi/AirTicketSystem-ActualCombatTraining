package com.softfz.ui.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.softfz.RMIFactory;
import com.softfz.model.Flight;
import com.softfz.model.PageModel;
import com.softfz.model.SystemUser;
import com.softfz.resources.Resources;
import com.softfz.service.INetService;
import com.softfz.service.ISystemService;
import com.softfz.ui.MessagePanel;
import com.softfz.ui.table.PageMutiTable;
import com.softfz.ui.table.PageMutiTableModel;
import com.softfz.ui.table.PageTable;
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
public class AdminListPanel extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 354310944274691451L;
	private JPanel jPanel_UP;
	private JPanel jPanel_DOWN;
	private JLabel jLabel1;
	private JButton jButton_Search;
	private JButton jButton_Lock;
	private JButton jButton_Unlock;
	private JButton jButton_ResetPassword;
	private JTextField jTextField_Name;
//	private PageTable pageTable;
//	private MyTableModel tableModel;
	private String[] header = new String[]{"","账号","电子邮箱","电话号码","状态"};
	private PageMutiTable mutiTable;
	private MyMutiTableModel tableModel;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new AdminListPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public AdminListPanel() {
//		tableModel = new MyTableModel(header);
		tableModel = new MyMutiTableModel(header);
		initGUI();
		listener();
	}
	
	private void listener(){
		ButtonListener bl = new ButtonListener();
		jButton_Search.addActionListener(bl);
		jButton_ResetPassword.addActionListener(bl);
		jButton_Lock.addActionListener(bl);
		jButton_Unlock.addActionListener(bl);
		
	}

	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if(cmd.equals("查询")){
				tableModel.doPageQuery(1, PageModel.DEFAULT_PAGESIZE);
				mutiTable.reflashTable();
			}else if(cmd.equals("重置密码")){
				List<SystemUser> list = tableModel.getCheckValue();
				if(list.size() != 0){
					ISystemService systemService = RMIFactory.getService();
					if(systemService != null){
						for (SystemUser systemUser : list) {
							try {
								if (systemService.updatePassword(systemUser.getUsername(), systemUser.getPassword(), "888888")){
									MessagePanel.showInfo(AdminListPanel.this, systemUser.getUsername()+"重置成功！");
								}else {
									MessagePanel.showInfo(AdminListPanel.this, systemUser.getUsername()+"重置失败！");
								}
							} catch (RemoteException e1) {
								// TODO 自动生成的 catch 块
								e1.printStackTrace();
								JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
										"不小心重置失败了呢，可能远程服务器被关闭了哦~", "一个令人难过的通知", JOptionPane.INFORMATION_MESSAGE); 
							}
						}
					}
				}else{
					JOptionPane.showMessageDialog(null, "没有选中账户");
				}
			}else if(cmd.equals("冻结账号")){
				List<SystemUser> list = tableModel.getCheckValue();
				if(list.size() != 0){
					ISystemService systemService = RMIFactory.getService();
					if(systemService != null){
						for (SystemUser systemUser : list) {
							try {
								systemUser.setState("1");
								if(!systemService.modifySystemUser(systemUser)) {
									MessagePanel.showInfo(AdminListPanel.this, systemUser.getUsername()+"冻结失败，请重试！");
								}
							} catch (RemoteException e1) {
								// TODO 自动生成的 catch 块
								e1.printStackTrace();
								JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
										"不小心失败了呢，可能远程服务器被关闭了哦~", "一个令人难过的通知", JOptionPane.INFORMATION_MESSAGE); 
							}
						}
						mutiTable.reflashTable();
					}
				}else{
					JOptionPane.showMessageDialog(null, "没有选中账户");
				}
			}else if(cmd.equals("解冻账号")){
				List<SystemUser> list = tableModel.getCheckValue();
				if(list.size() != 0){
					ISystemService systemService = RMIFactory.getService();
					if(systemService != null){
						for (SystemUser systemUser : list) {
							try {
								systemUser.setState("0");
								if(!systemService.modifySystemUser(systemUser)) {
									MessagePanel.showInfo(AdminListPanel.this, systemUser.getUsername()+"解冻失败，请重试！");
								}
							} catch (RemoteException e1) {
								// TODO 自动生成的 catch 块
								e1.printStackTrace();
								JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
										"不小心失败了呢，可能远程服务器被关闭了哦~", "一个令人难过的通知", JOptionPane.INFORMATION_MESSAGE); 
							}
						}
						mutiTable.reflashTable();
					}
				}else{
					JOptionPane.showMessageDialog(null, "没有选中账户");
				}
			}
		}
	}
	
	private class MyMutiTableModel extends PageMutiTableModel {
		public MyMutiTableModel(String[] header) {
			super(header);
			setCheckColumn(0);//设值复选框的位置
		}
		//如果要获取选中的内容
		public List<SystemUser> getCheckValue(){
			List<SystemUser> list = new ArrayList<SystemUser>();
			boolean[] flag=this.getCheckboxflags();//获取该表格中选中的
			//遍历这个数组就如果内容为true表示有选中
			for(int i=0;i<flag.length;i++){
				if(flag[i]){
					SystemUser systemUser = (SystemUser)this.getPageModel().getResult().get(i);
					list.add(systemUser);
				}
			}
			return list;
		}
		
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// 该方法实现表格需要显示的内容
			//rowIndex需要显示的行记录
			SystemUser systemUser =(SystemUser)this.getPageModel().getResult().get(rowIndex);
			//columnIndex需要显示的列记录数
			switch (columnIndex) {
			case 0:
				return this.getCheckboxflags()[rowIndex];
			case 1:
				return systemUser.getUsername();
			case 2:
				return systemUser.getEmail();
			case 3:
				return systemUser.getTelephone();
			case 4:
				String state = systemUser.getState();
				String val = null;
				if(state.equals("0")){
					val = "正常";
					return val;
				}else if(state.equals("1")){
					val = "<html><FONT color=red>冻结</html>";
					return val;
				}break;
			default:
				break;
			}
			return null;
		}

		@Override
		public void doPageQuery(int currentPage, int pageSize) {
			// 该方法是表格内容更新方法，比如在分页按钮和查询时调用.
			// 其中分页按钮已经实现了内部调用无需关联，
			// 在界面的查询按钮中需要监听点击方法调用本方法

			// 该方法具体实现只需要调用远程服务器中的业务方法即可。
				
			ISystemService systemService = RMIFactory.getService();
			if(systemService != null){
				String username = jTextField_Name.getText().trim();
				try {
					PageModel<SystemUser> pageModel = systemService.queryAllSystemUser(username, currentPage, pageSize);
					super.setPageModel(pageModel);
				} catch (Exception e) {
					String eStr = e.getMessage().substring(e.getMessage().lastIndexOf(":")+1);
					JOptionPane.showMessageDialog(null, eStr);
				}
			}
			
			//调用方法设值需要显示的内容
			//super.setPageModel(pageModel);
			
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
				jPanel_UP.setBounds(0, 0, 630, 101);
				jPanel_UP.setLayout(null);
				jPanel_UP.setBorder(BorderFactory.createTitledBorder("\u67e5\u8be2\u6761\u4ef6"));
				{
					jLabel1 = new JLabel();
					jPanel_UP.add(jLabel1);
					jLabel1.setText("\u8d26\u53f7\uff1a");
					jLabel1.setBounds(54, 42, 80, 20);
				}
				{
					jTextField_Name = new JTextField();
					jPanel_UP.add(jTextField_Name);
					jTextField_Name.setBounds(97, 37, 177, 32);
				}
				
				{
					jButton_Search = new JButton();
					jPanel_UP.add(jButton_Search);
					jButton_Search.setText("\u67e5\u8be2");
					jButton_Search.setBounds(338, 19, 120, 28);
					jButton_Search.setIcon(Resources.getFindIcon());
				}
				{
					jButton_Lock = new JButton();
					jPanel_UP.add(jButton_Lock);
					jButton_Lock.setText("\u51bb\u7ed3\u8d26\u53f7");
					jButton_Lock.setBounds(475, 19, 120, 28);
					jButton_Lock.setIcon(Resources.getLockIcon());
				}
				{
					jButton_Unlock = new JButton();
					jPanel_UP.add(jButton_Unlock);
					jButton_Unlock.setText("\u89e3\u51bb\u8d26\u53f7");
					jButton_Unlock.setBounds(475, 59, 120, 28);
					jButton_Unlock.setIcon(Resources.getUnLockIcon());
				}
				{
					jButton_ResetPassword = new JButton();
					jPanel_UP.add(jButton_ResetPassword);
					jButton_ResetPassword.setText("\u91cd\u7f6e\u5bc6\u7801");
					jButton_ResetPassword.setBounds(338, 59, 120, 28);
					jButton_ResetPassword.setIcon(Resources.getRePwdIcon());
				}
			}	
			{
				// 带多选框的分页表格
				mutiTable = new PageMutiTable(tableModel);
				this.add(mutiTable,BorderLayout.CENTER);
				mutiTable.setEnabled(true);
				mutiTable.setBounds(10, 20, 610, 430);
				jPanel_DOWN = new JPanel();
				jPanel_DOWN.add(mutiTable);
				jPanel_DOWN.setBounds(0, 101, 630, 453);
				jPanel_DOWN.setLayout(null);
				jPanel_DOWN.setBorder(BorderFactory.createTitledBorder("\u67e5\u8be2\u7ed3\u679c"));
				this.add(jPanel_DOWN);
			}
			jTextField_Name.requestFocus();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
