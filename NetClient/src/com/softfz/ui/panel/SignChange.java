package com.softfz.ui.panel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.eltima.components.ui.DatePicker;
import com.softfz.NetContext;
import com.softfz.RMIFactory;
import com.softfz.model.BounceRecord;
import com.softfz.model.Flight;
import com.softfz.model.SaleRecord;
import com.softfz.model.TicketStore;
import com.softfz.resources.Resources;
import com.softfz.service.INetService;
import com.softfz.ui.panel.ReturnTicket.ButtonListener;
import com.softfz.utils.CheckUtil;
import com.softfz.utils.DateUtil;

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
public class SignChange extends javax.swing.JPanel {
	private JPanel jPanel_UP;
	private JScrollPane jPanel_MID;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane jScrollPane1;
	private JButton jButton_Search;
	private JTextField jTextField_ToCity;
	private JTextField jTextField_FromCity;
	private JTextField jTextField_IdCard;
	private JButton jButton_Cancel;
	private JLabel jLabel4;
	private JLabel jLabel3;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JButton jButton_OK;
	private JPanel jPanel_DOWN;
	private DatePicker datePicker;
	private DatePicker dateChangePicker;
	private String[] header = new String[]{"航班编号","起飞/到达机场","起飞/到达时间","票面价格","燃油税","机场建设费","支付金额"};
	private int errorNoticeInt;
	private Boolean returnFlag = false;
	private HashMap<String, String> errorMap = new HashMap<String, String>();
	private SaleRecord saleRecord;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new SignChange());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public SignChange() {
		tableModel = new DefaultTableModel(null,header){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;//返回true表示能编辑，false表示不能编辑
			}
		};
		initGUI();
		listener();
	}
	
	public void listener(){
		ButtonListener bl = new ButtonListener();
		jButton_Search.addActionListener(bl);
		jButton_OK.addActionListener(bl);
		jButton_Cancel.addActionListener(bl);
	}
	
	
	public void buildTable(SaleRecord saleRecord){
		
		Flight flight = new Flight();
		INetService netService = RMIFactory.getService();
		try {
			flight = netService.queryFlights(saleRecord.getFlightid());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		String flightNo = flight.getFlightno();
		
		String[][] data = new String[1][7];
		String[] ticketdata = new String[7];
		ticketdata[0] = flightNo;
		ticketdata[1] = "<html>" + saleRecord.getStartairport() + "<br>" + saleRecord.getEndairpotr() + "</html>";
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String start = (String)formatter.format(saleRecord.getStarttime());
		String end = (String)formatter.format(saleRecord.getArrtime());
		ticketdata[2] = "<html>" + start + "<br>" + end + "</html>";
		ticketdata[3] = String.valueOf(saleRecord.getTicketmoney());
		ticketdata[4] = String.valueOf(saleRecord.getOiltax());
		ticketdata[5] = String.valueOf(saleRecord.getAirporttax());
		ticketdata[6] = String.valueOf(saleRecord.getTicketmoney() + saleRecord.getOiltax() + saleRecord.getAirporttax());
	
		data[0] = ticketdata;
		tableModel.setDataVector(data,header);
		table.repaint();
		setSuitableColumn(table);//设置列宽以匹配显示
		this.saleRecord=saleRecord;
		
	}
	
	private void cleanAllInput(){
		//清空所有输入框中的内容
		jTextField_IdCard.setText("");
		jTextField_FromCity.setText("");
		jTextField_ToCity.setText("");
		datePicker.setText("");
		dateChangePicker.setText("");
		//清空table表中的内容
		tableModel.setRowCount(0);
		//设置转签按钮不可用
		jButton_OK.setEnabled(false);
	}
	
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if(cmd.equals("查询")){
				String id=jTextField_IdCard.getText().trim();
				String fromcity=jTextField_FromCity.getText().trim();
				String tocity=jTextField_ToCity.getText().trim();
				Date date=(Date) datePicker.getValue();
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				checkIsError(CheckUtil.checkIdCard(id));
				
				if (returnFlag) {
					INetService netService=RMIFactory.getService();
					if (netService!=null) {
						try {
							buildTable(netService.getSaleRecord(NetContext.LOGIN_NETDEALER.getNetid(), fromcity, tocity, id, sqlDate));
						} catch (RemoteException e1) {
							// TODO 自动生成的 catch 块
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
					}
				}
				jButton_OK.setEnabled(true);
				
			}else if(cmd.equals("确认转签")){
				INetService netService=RMIFactory.getService();
				if (netService!=null) {
					try {
						String result=netService.changeTicketDate(saleRecord.getSaleid(), (Date) dateChangePicker.getValue());
						JOptionPane.showMessageDialog(null, result);
						cleanAllInput();
					} catch (RemoteException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
			
				
				
			}if(cmd.equals("取消")){
				cleanAllInput();
			}
			
		}
		
	}
	
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(794, 498));
			this.setSize(794, 498);
			this.setLayout(null);
			{
				jPanel_UP = new JPanel();
				this.add(jPanel_UP);
				jPanel_UP.setBounds(0, 0, 794, 179);
				jPanel_UP.setBorder(BorderFactory.createTitledBorder("\u67e5\u8be2\u6761\u4ef6"));
				jPanel_UP.setLayout(null);
				{
					jLabel1 = new JLabel();
					jPanel_UP.add(jLabel1);
					jLabel1.setText("\u8eab\u4efd\u8bc1\u53f7\u7801\uff1a");
					jLabel1.setBounds(44, 41, 80, 20);
				}
				{
					jLabel2 = new JLabel();
					jPanel_UP.add(jLabel2);
					jLabel2.setText("\u8ba1\u5212\u65f6\u95f4\uff1a");
					jLabel2.setBounds(44, 110, 80, 20);
				}
				{
					jLabel3 = new JLabel();
					jPanel_UP.add(jLabel3);
					jLabel3.setText("\u51fa\u53d1\u57ce\u5e02\uff1a");
					jLabel3.setBounds(341, 42, 80, 20);
				}
				{
					jLabel4 = new JLabel();
					jPanel_UP.add(jLabel4);
					jLabel4.setText("\u5230\u8fbe\u57ce\u5e02\uff1a");
					jLabel4.setBounds(341, 110, 80, 20);
				}
				{
					jTextField_IdCard = new JTextField();
					jPanel_UP.add(jTextField_IdCard);
					jTextField_IdCard.setBounds(124, 40, 178, 24);
				}
				{
					jTextField_FromCity = new JTextField();
					jPanel_UP.add(jTextField_FromCity);
					jTextField_FromCity.setBounds(406, 41, 163, 24);
				}
				{
					jTextField_ToCity = new JTextField();
					jPanel_UP.add(jTextField_ToCity);
					jTextField_ToCity.setBounds(406, 109, 163, 24);
				}
				{
					datePicker = new DatePicker(this, new Date());
					datePicker.setPattern("yyyy-MM-dd");
					jPanel_UP.add(datePicker);
					datePicker.setBounds(124,106,123,29);
					datePicker.setText("");
				}
				{
					jButton_Search = new JButton();
					jPanel_UP.add(jButton_Search);
					jButton_Search.setText("\u67e5\u8be2");
					jButton_Search.setBounds(649, 65, 87, 53);
					jButton_Search.setIcon(Resources.getFindIcon());
				}
			}
			{
				jPanel_MID = new JScrollPane();
				this.add(jPanel_MID);
				jPanel_MID.setBorder(BorderFactory.createTitledBorder("\u7968\u636e\u4fe1\u606f"));
				jPanel_MID.setBounds(0, 176, 794, 112);
				jPanel_MID.setSize(794, 144);

				table = new JTable();
				jPanel_MID.setViewportView(table);
				
				table.setModel(tableModel);
				table.setRowHeight(80);
				setSuitableColumn(table);//设置列宽以匹配显示
			}
			{
				jPanel_DOWN = new JPanel();
				this.add(jPanel_DOWN);
				jPanel_DOWN.setBounds(0, 320, 794, 102);
				jPanel_DOWN.setBorder(BorderFactory.createTitledBorder("\u53d8\u66f4\u51fa\u884c\u65e5\u671f"));
				{					
					dateChangePicker = new DatePicker(this, new Date());
					dateChangePicker.setPattern("yyyy-MM-dd");
					jPanel_DOWN.add(dateChangePicker);
					dateChangePicker.setPreferredSize(new java.awt.Dimension(184, 52));
					dateChangePicker.setText("");
				}
			}
			{
				jButton_OK = new JButton();
				this.add(jButton_OK);
				jButton_OK.setText("\u786e\u8ba4\u8f6c\u7b7e");
				jButton_OK.setBounds(195, 436, 102, 42);
				jButton_OK.setIcon(Resources.getSaveIcon());
				jButton_OK.setSize(120, 42);
				jButton_OK.setEnabled(false);
			}
			{
				jButton_Cancel = new JButton();
				this.add(jButton_Cancel);
				jButton_Cancel.setText("\u53d6\u6d88");
				jButton_Cancel.setBounds(481, 436, 120, 42);
				jButton_Cancel.setIcon(Resources.getStopIcon());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 设置列宽以匹配显示内容
	 * @param table
	 */
	public void setSuitableColumn(JTable table){
		TableColumnModel tcm = table.getColumnModel();
		TableColumn tc = tcm.getColumn(2);
		tc.setPreferredWidth(120);
		tc.setMaxWidth(120);
		tc.setMinWidth(120);
	}
	
	private void addToErrorMap(){
		errorMap = new HashMap<String, String>();
		errorMap.put(jTextField_IdCard.getText(),"身份证号码");
		errorMap.put(jTextField_FromCity.getText(),"出发城市");
		errorMap.put(jTextField_ToCity.getText(),"到达城市");
		errorMap.put(datePicker.getText(),"计划出行日期");
	}
	
	private void checkIsError(int inputTextInt){
		String errorNoticeString = CheckUtil.getMapNoticeInfo(inputTextInt);
		if(!errorNoticeString.equals("OK")){
			JOptionPane.showMessageDialog(SignChange.this, errorNoticeString);
			returnFlag = false;
		}else {
			returnFlag = true;
		}
	}

}
