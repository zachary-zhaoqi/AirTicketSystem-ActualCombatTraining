package com.softfz.ui.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.softfz.RMIFactory;
import com.softfz.model.Flight;
import com.softfz.model.NetDealer;
import com.softfz.model.PageModel;
import com.softfz.model.SystemUser;
import com.softfz.resources.Resources;
import com.softfz.service.ISystemService;
import com.softfz.ui.table.PageMutiTable;
import com.softfz.ui.table.PageMutiTableModel;
import com.softfz.ui.table.PageRadioTable;
import com.softfz.ui.table.PageTable;
import com.softfz.ui.table.PageTableModel;

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
public class SaleNetListPanel extends javax.swing.JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3505221224371043626L;
	private JPanel jPanel_UP;
	private JPanel jPanel_DOWN;
	private JLabel jLabel1;
	private JLabel jLabel3;
	private JButton jButton_ResetPassword;
	private JButton jButton_Lock;
	private JButton jButton_Unlock;
	private JButton jButton_Search;
	private JTextField jTextField_NetName;
	private JTextField jTextField_NetCode;
//	private PageTable pageTable;
//	private MyTableModel tableModel;
	private String[] header = new String[]{"","网点编号","网点名称","负责人","联系电话","状态"};
	private PageMutiTable mutiTable;
	private MyMutiTableModel tableModel;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new SaleNetListPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public SaleNetListPanel() {
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

			}else if(cmd.equals("重置密码")){

			}else if(cmd.equals("冻结网点")){
				
			}else if(cmd.equals("解冻网点")){
				
			}else{
					JOptionPane.showMessageDialog(null, "没有选中需要解冻的销售网点");
				}
		}
	}
	
	private class MyMutiTableModel extends PageMutiTableModel {
		public MyMutiTableModel(String[] header) {
			super(header);
			setCheckColumn(0);//设值复选框的位置
		}
		//如果要获取选中的内容
		public List<NetDealer> getCheckValue(){
			List<NetDealer> list = new ArrayList<NetDealer>();
			boolean[] flag=this.getCheckboxflags();//获取该表格中选中的
			//遍历这个数组就如果内容为true表示有选中
			for(int i=0;i<flag.length;i++){
				if(flag[i]){
					NetDealer netDealer = (NetDealer)this.getPageModel().getResult().get(i);
					list.add(netDealer);
				}
			}
			return list;
		}
		
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// 该方法实现表格需要显示的内容
			//rowIndex需要显示的行记录
			NetDealer netDealer =(NetDealer)this.getPageModel().getResult().get(rowIndex);
			//columnIndex需要显示的列记录数
			switch (columnIndex) {
			case 0:
				return this.getCheckboxflags()[rowIndex];
			case 1:
				return netDealer.getNetcode();
			case 2:
				return netDealer.getNetname();
			case 3:
				return netDealer.getDirector();
			case 4:
				return netDealer.getTelphone();
			case 5:
				String state = netDealer.getState();
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
				String netcode = jTextField_NetCode.getText().trim();
				String netname = jTextField_NetName.getText().trim();
				try {
					PageModel<NetDealer> pageModel = systemService.queryAllDealer(netcode, netname, currentPage, pageSize);
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
					jLabel1.setText("\u7f51\u70b9\u7f16\u53f7\uff1a");
					jLabel1.setBounds(18, 20, 80, 20);
				}
				{
					jLabel3 = new JLabel();
					jPanel_UP.add(jLabel3);
					jLabel3.setText("\u7f51\u70b9\u540d\u79f0\uff1a");
					jLabel3.setBounds(18, 62, 80, 20);
				}
				{
					jTextField_NetCode = new JTextField();
					jPanel_UP.add(jTextField_NetCode);
					jTextField_NetCode.setBounds(84, 20, 165, 24);
				}
				{
					jTextField_NetName = new JTextField();
					jPanel_UP.add(jTextField_NetName);
					jTextField_NetName.setBounds(84, 61, 163, 24);
					jTextField_NetName.setSize(165, 24);
				}
				
				{
					jButton_Search = new JButton();
					jPanel_UP.add(jButton_Search);
					jButton_Search.setText("\u67e5\u8be2");
					jButton_Search.setIcon(Resources.getFindIcon());
					jButton_Search.setBounds(338, 19, 120, 28);
				}
				{
					jButton_Unlock = new JButton();
					jPanel_UP.add(jButton_Unlock);
					jButton_Unlock.setText("\u89e3\u51bb\u7f51\u70b9");
					jButton_Unlock.setIcon(Resources.getUnLockIcon());
					jButton_Unlock.setBounds(475, 59, 120, 28);
				}
				{
					jButton_Lock = new JButton();
					jPanel_UP.add(jButton_Lock);
					jButton_Lock.setText("\u51bb\u7ed3\u7f51\u70b9");
					jButton_Lock.setIcon(Resources.getLockIcon());
					jButton_Lock.setBounds(475, 19, 120, 28);
				}
				{
					jButton_ResetPassword = new JButton();
					jPanel_UP.add(jButton_ResetPassword);
					jButton_ResetPassword.setText("\u91cd\u7f6e\u5bc6\u7801");
					jButton_ResetPassword.setIcon(Resources.getRePwdIcon());
					jButton_ResetPassword.setBounds(338, 59, 120, 28);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
