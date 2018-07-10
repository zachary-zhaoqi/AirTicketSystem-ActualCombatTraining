package com.softfz.ui.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import com.softfz.model.PageModel;
import com.softfz.resources.Resources;
import com.softfz.service.INetService;
import com.softfz.service.ISystemService;
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
public class FlightListPanel extends javax.swing.JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7106463560325610075L;
	private JPanel jPanel_UP;
	private JPanel jPanel_DOWN;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JButton jButton_Search;
	private JComboBox jComboBox_IsStop;
	private JComboBox jComboBox_FlightType;
	private JTextField jTextField_ArrCity;
	private JTextField jTextField_StartCity;
	private JTextField jTextField_FlightID;
	private PageTable pageTable;
	private MyTableModel tableModel;
	private String[] header = new String[]{"航班编号","城市","机场","时间","是否经停"};

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new FlightListPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public FlightListPanel() {
		tableModel = new MyTableModel(header);
		initGUI();
		listener();
	}
	
	private void listener(){
		ButtonListener bl = new ButtonListener();
		jButton_Search.addActionListener(bl);
	}
	
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if(cmd.equals("查询")) {

			}
		}
	}
	
	private class MyTableModel extends PageTableModel {
		/**
		 * 
		 */
		private static final long serialVersionUID = -1762490750935851629L;

		public MyTableModel(String[] header) {
			super(header);
		}
	
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// 该方法实现表格需要显示的内容
			//rowIndex需要显示的行记录
			Flight flight =(Flight)this.getPageModel().getResult().get(rowIndex);
			//columnIndex需要显示的列记录数
			Object value = null;
			switch (columnIndex) {
			
			case 0:
				value = flight.getFlightno();
				break;
			case 1:
				value = "<html><a style=\"font-size:8px; font-family:'宋体'\">起飞城市：" + flight.getFromcity() + "<br>到达城市：" + flight.getTocity() + "</a></html>";
				break;
			case 2:
				value = "<html><a style=\"font-size:8px; font-family:'宋体'\">起飞机场：" + flight.getStartairport() + "<br>到达机场：" + flight.getEndairport() + "</a></html>";
				break;
			case 3:
				value = "<html><a style=\"font-size:8px; font-family:'宋体'\">起飞时间：" + flight.getPlanstarttime() + "<br>到达时间：" + flight.getPlanendtime() + "</a></html>";
				break;
			case 4:
				if(flight.getIsstop() == 0){
					value = "<html><a style=\"font-size:8px; font-family:'宋体'\">无经停</a></html>";
					break;
				}
				else{
					value = "<html><a style=\"font-size:8px; font-family:'宋体'\">有经停</a></html>";
					break;
				}
			default:
				break;
			}
			return value;
		}

		@Override
		public void doPageQuery(int currentPage, int pageSize) {
			// 该方法是表格内容更新方法，比如在分页按钮和查询时调用.
			// 其中分页按钮已经实现了内部调用无需关联，
			// 在界面的查询按钮中需要监听点击方法调用本方法

			// 该方法具体实现只需要调用远程服务器中的业务方法即可。
			
			ISystemService systemService = RMIFactory.getService();
			if(systemService != null){
				//获得界面上的值
				String flightno = jTextField_FlightID.getText().trim();
				String startcity = jTextField_StartCity.getText().trim();
				String arrcity = jTextField_ArrCity.getText().trim();
				String flighttypeString = (String) jComboBox_FlightType.getSelectedItem();
				String isstopString = (String) jComboBox_IsStop.getSelectedItem();
				int flighttype = 0;
				int isstop = 0;
				if(flighttypeString.trim().equals("国内航班")){
					flighttype = 0;
				}else if(flighttypeString.trim().equals("国际航班")){
					flighttype = 1;
				}
				
				if(isstopString.trim().equals("无经停")){
					isstop = 0;
				}else if(isstopString.trim().equals("有经停")){
					isstop = 1;
				}
				
				
				try {
					PageModel<Flight> pageModel = systemService.queryFlights(flightno, startcity, arrcity, flighttype, isstop, currentPage, pageSize);
					super.setPageModel(pageModel);
				} catch (Exception e) {
					e.printStackTrace();
					String eStr = e.getMessage().substring(e.getMessage().lastIndexOf(":")+1);
					JOptionPane.showMessageDialog(null, eStr);
				}
			}
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
					jLabel1.setText("\u822a\u73ed\u7f16\u53f7\uff1a");
					jLabel1.setBounds(18, 20, 80, 20);
				}
				{
					jLabel2 = new JLabel();
					jPanel_UP.add(jLabel2);
					jLabel2.setText("\u822a\u73ed\u7c7b\u578b\uff1a");
					jLabel2.setBounds(18, 70, 80, 20);
				}
				{
					jLabel3 = new JLabel();
					jPanel_UP.add(jLabel3);
					jLabel3.setText("\u8d77\u98de\u57ce\u5e02\uff1a");
					jLabel3.setBounds(221, 20, 80, 20);
				}
				{
					jLabel4 = new JLabel();
					jPanel_UP.add(jLabel4);
					jLabel4.setText("\u662f\u5426\u7ecf\u505c\uff1a");
					jLabel4.setBounds(221, 70, 80, 20);
				}
				{
					jLabel5 = new JLabel();
					jPanel_UP.add(jLabel5);
					jLabel5.setText("\u5230\u8fbe\u57ce\u5e02\uff1a");
					jLabel5.setBounds(413, 20, 80, 20);
				}
				{
					jTextField_FlightID = new JTextField();
					jPanel_UP.add(jTextField_FlightID);
					jTextField_FlightID.setBounds(90, 17, 113, 24);
				}
				{
					jTextField_StartCity = new JTextField();
					jPanel_UP.add(jTextField_StartCity);
					jTextField_StartCity.setBounds(286, 17, 115, 24);
				}
				{
					jTextField_ArrCity = new JTextField();
					jPanel_UP.add(jTextField_ArrCity);
					jTextField_ArrCity.setBounds(480, 17, 132, 24);
				}
				{
					ComboBoxModel jComboBox_FlightTypeModel = 
							new DefaultComboBoxModel(
									new String[] { "国内", "国际" });
					jComboBox_FlightType = new JComboBox();
					jPanel_UP.add(jComboBox_FlightType);
					jComboBox_FlightType.setModel(jComboBox_FlightTypeModel);
					jComboBox_FlightType.setBounds(90, 66, 113, 24);
				}
				{
					ComboBoxModel jComboBox_IsStopModel = 
							new DefaultComboBoxModel(
									new String[] { "无经停", "有经停" });
					jComboBox_IsStop = new JComboBox();
					jPanel_UP.add(jComboBox_IsStop);
					jComboBox_IsStop.setModel(jComboBox_IsStopModel);
					jComboBox_IsStop.setBounds(286, 66, 115, 24);
				}
				{
					jButton_Search = new JButton();
					jPanel_UP.add(jButton_Search);
					jButton_Search.setText("\u67e5\u8be2");
					jButton_Search.setBounds(458, 58, 90, 32);
					jButton_Search.setIcon(Resources.getFindIcon());
				}
			}
			{
				// 分页表格
				pageTable = new PageTable(tableModel);
				pageTable.setEnabled(true);
				pageTable.setBounds(10, 21, 610, 430);
				jPanel_DOWN = new JPanel();
				jPanel_DOWN.add(pageTable);
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
