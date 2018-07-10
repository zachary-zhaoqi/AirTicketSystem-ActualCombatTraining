package com.softfz.ui.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.eltima.components.ui.DatePicker;
import com.softfz.model.Flight;
import com.softfz.resources.Resources;
import com.softfz.service.INetService;
import com.softfz.ui.Center;
import com.softfz.ui.table.PageRadioTable;
import com.softfz.ui.table.PageRadioTableModel;


/**
* 航班查询界面，显示符合条件的航班信息
*/
public class FlightInfo extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2307425322358541964L;
	private JPanel jPanel1;
	private JPanel bottom;
	private PageRadioTable radioTable;
	private JLabel jLabel3;
	private JButton searchBtn;
	private DatePicker datePicker;
	private JTextField toCityField;
	private JTextField fromCityField;
	private JButton orderBtn;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private Center center;
	
	
	
	private String[] header = new String[]{"","时间","机场","航空公司/机型","价格/折扣","剩余票数"};
	private SearchTableModel tableModel;
	private INetService facade;
	
	
//	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		frame.getContentPane().add(new FlightInfo());
//		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//		frame.pack();
//		frame.setVisible(true);
//	}
	
	public FlightInfo(Center center) {
		this.center = center;
		tableModel = new SearchTableModel();
		initGUI();
		listener();
	}
	
	private void listener(){
		ButtonListener bl = new ButtonListener();
		searchBtn.addActionListener(bl);
		orderBtn.addActionListener(bl);
	}
		
	private void initGUI() {
		try {
			{
				this.setPreferredSize(new java.awt.Dimension(794, 498));
				this.setLayout(new BorderLayout());
				{
					jPanel1 = new JPanel();
					this.add(jPanel1, BorderLayout.NORTH);
					
					jPanel1.setSize(794,80);
					jPanel1.setLayout(null);
					jPanel1.setPreferredSize(new java.awt.Dimension(794, 80));
				}
				{
					jLabel1 = new JLabel();
					jPanel1.add(jLabel1);
					jLabel1.setText("出发城市：");
					jLabel1.setBounds(10,16,80,35);
				}
				{
					jLabel2 = new JLabel();
					jPanel1.add(jLabel2);
					jLabel2.setText("到达城市：");
					jLabel2.setBounds(210,16,80,35);
				}
				{
					jLabel3 = new JLabel();
					jPanel1.add(jLabel3);
					jLabel3.setText("出发日期：");
					jLabel3.setBounds(420,16,80,35);
				}
				{
					fromCityField = new JTextField();
					jPanel1.add(fromCityField);
					fromCityField.setBounds(80,16,120,30);
				}
				{
					toCityField = new JTextField();
					jPanel1.add(toCityField);
					toCityField.setBounds(290,16,120,30);
				}
				{
					datePicker = new DatePicker(this, new Date());
					datePicker.setPattern("yyyy-MM-dd");
					jPanel1.add(datePicker);
					datePicker.setBounds(500,16,123,29);
				}
				{
					searchBtn = new JButton();
					jPanel1.add(searchBtn);
					searchBtn.setText("查询");
					searchBtn.setIcon(Resources.getFindIcon());
					searchBtn.setBounds(680,16,100,30);
				}
			}
			{
				// 带单选框的分页表格
				radioTable = new PageRadioTable(tableModel);
				this.add(radioTable,BorderLayout.CENTER);
				radioTable.setEnabled(true);
				radioTable.setPreferredSize(new java.awt.Dimension(794, 369));
			}	
			{
				bottom = new JPanel();
				orderBtn = new JButton("订票");
				orderBtn.setIcon(Resources.getNewIcon());
				bottom.add(orderBtn);
				this.add(bottom,BorderLayout.SOUTH);
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if(cmd.equals("查询")){

			}else if(cmd.equals("订票")){

			}
			
		}
		
	}
	
	
	private class SearchTableModel extends PageRadioTableModel {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 4152552028516706843L;

		public SearchTableModel(){
			super(header);
		}
		/**
		 * 获取选中的航班信息
		 * @return
		 */
		public Flight getCheckFlight(){
			int index = super.getSelectedIndex();//返回选中的是第几行，下标从0开始。
			if(index == -1){
				JOptionPane.showMessageDialog(null, "没有选中航班，无法订票");
				return null;
			}
			Flight flight = (Flight) super.getPageModel().getResult().get(index);
			return flight;
		}
		
		/**
		 * 查询的方法
		 * @param rowIndex
		 * @param columnIndex
		 * @return
		 */
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return null;
		}
		
		/**
		 * 找出要显示的数据并显示到界面上
		 * @param currentPage
		 * @param pageSize
		 */
		@Override
		public void doPageQuery(int currentPage, int pageSize) {

		}
	}
		

}
