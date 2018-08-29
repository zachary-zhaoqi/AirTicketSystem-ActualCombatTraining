package com.softfz.ui.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.softfz.NetContext;
import com.softfz.RMIFactory;
import com.softfz.model.Flight;
import com.softfz.model.SaleRecord;
import com.softfz.resources.Resources;
import com.softfz.service.INetService;
import com.softfz.ui.Center;
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
/**
* 航班查询后的出票页面，在此页面上确定订票信息
*/
public class Ticketing extends javax.swing.JPanel {
	
	private static final long serialVersionUID = -8113550963939401085L;
	private JScrollPane jPanel_UP;
	private JPanel jPanel_MID;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JButton jButton_Cancel;
	private JLabel jLabel4;
	private JButton jButton_OK;
	private JPanel jPanel_DOWN;
	private JTextField jTextField_Tel;
	private JTextField jTextField_ID;
	private JTextField jTextField_Name;
	private DefaultTableModel tableModel;
	
	private Flight flight;
	private String[] header = new String[]{"航班编号","起飞/到达机场","起飞/到达时间","机型/经停","票面价格",
			"燃油税", "机场建设费", "支付金额"};
	private java.sql.Date planDate;
	private int oiltax;
	private JTable table;
	private Center center;
	private int errorNoticeInt;//错误信息代码暂存变量
	
	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new Ticketing(null));
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public Ticketing(Center center) {
		this.center = center;
		tableModel = new DefaultTableModel(null,header){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;//返回true表示能编辑，false表示不能编辑
			}
		};
		initGUI();
		listener();
	}
	
	/**
	 * 通过设置tableModel，构建需要显示的航班信息
	 * @param flight
	 * @param planDate
	 */
	public void buildTicket(Flight flight, java.sql.Date planDate){
		this.flight = flight;
		this.planDate = planDate;
		String[][] data = new String[1][8];
		String[] flightdata = new String[8];
		flightdata[0] = flight.getFlightno();
		flightdata[1] = "<html>" + flight.getStartairport() + "<br>" + flight.getEndairport() + "</html>";
		String begin = planDate.toString() + " " + flight.getPlanstarttime();
		String end = planDate.toString() + " " + flight.getPlanendtime();
		flightdata[2] = "<html>" + begin + "<br>" + end + "</html>";
		flightdata[3] = "<html>" + flight.getFlighttype() + "/" 
				+ (flight.getIsstop()==0?"无经停":"经停") + "</html>" ;
		flightdata[4] = "" + (int)(flight.getPrice() * flight.getDiscount());
		
		if(flight.getAirrange() >= NetContext.OIL_TAX.getBreakpoint()){
			oiltax = NetContext.OIL_TAX.getHighfee();
		}else{
			oiltax = NetContext.OIL_TAX.getLowfee();
		}
		flightdata[5] = "" + oiltax;
		flightdata[6] = "50";
		flightdata[7] = (int)(flight.getPrice() * flight.getDiscount())
				+ 50 + oiltax + "";
		data[0] = flightdata;
		tableModel.setDataVector(data,header);
		table.repaint();
		setSuitableColumn(table);//设置列宽以匹配显示
	}
	
	private void listener(){
		ButtonListener bl = new ButtonListener();
		jButton_OK.addActionListener(bl);
		jButton_Cancel.addActionListener(bl);
	}
	
	private void checkIsError(int inputTextInt) throws Exception{
		String errorNoticeString = CheckUtil.getMapNoticeInfo(inputTextInt);
		if(!errorNoticeString.equals("OK")){
			throw new Exception("订票失败：" + errorNoticeString);
		}
	}
	
	private void cleanAllInput(Boolean isReturnToFlightInfo){
		jTextField_Name.setText("");
		jTextField_Tel.setText("");
		jTextField_ID.setText("");
		if(isReturnToFlightInfo){
//			tableModel.setRowCount(0);
//			center.goFlightInfo();
			//todo:关闭当前窗口
		}
	}
	
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if(cmd.equals("确定订票")){
				String name=jTextField_Name.getText().trim();
				String tell=jTextField_Tel.getText().trim();
				String id=jTextField_ID.getText().trim();
				
				try {
					checkIsError(CheckUtil.checkName(name));
					checkIsError(CheckUtil.checkIdCard(id));
					checkIsError(CheckUtil.checkPhoneNumber(tell));
					
					ticketing(name, tell, id);
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
			}else if(cmd.equals("取消订票")){
				cleanAllInput(true);
			}
		}
		
	}
	
	private void ticketing(String name, String tel, String ID){
		INetService netService=RMIFactory.getService();
		SaleRecord saleRecord1=null;
		if (netService!=null) {
			try {
				saleRecord1 = netService.queryRecordForTicket(ID);
			} catch (RemoteException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "远程服务器可能未开启。错误："+e1.getMessage());
			}
			if (saleRecord1!=null) {
				JOptionPane.showMessageDialog(null, "该身份证不可再订票\n记录：\n"+saleRecord1.toString());
			}else{
				SaleRecord saleRecord=new SaleRecord();
				saleRecord.setNetid(NetContext.LOGIN_NETDEALER.getNetid());
				saleRecord.setFlightid(flight.getFlightid());
				saleRecord.setTicketmoney(Integer.parseInt((String) tableModel.getValueAt(0, 4)));
				saleRecord.setAirporttax(Integer.parseInt((String) tableModel.getValueAt(0, 6)));
				saleRecord.setOiltax(Integer.parseInt((String) tableModel.getValueAt(0, 5)));
				saleRecord.setCustname(name);
				saleRecord.setCusttel(tel);
				saleRecord.setIdcard(ID);
				saleRecord.setStartairport(flight.getStartairport());
				saleRecord.setEndairpotr(flight.getEndairport());
				saleRecord.setStoreid(flight.getStoreid());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date starttime;
				try {
					starttime = sdf.parse(planDate.toString() + " " + flight.getPlanstarttime());
					Date endtime=sdf.parse(planDate.toString() + " " + flight.getPlanendtime());
					saleRecord.setStarttime(starttime);
					saleRecord.setArrtime(endtime);
					saleRecord.setSalestate("0");
					String result=netService.saleTicket(saleRecord);
					if (result.equals("OK")) {
						cleanAllInput(true);
						JOptionPane.showMessageDialog(null, "订票成功");
					}else {
						JOptionPane.showMessageDialog(null, "出错了，请重试！");
					}
					
				} catch (ParseException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (RemoteException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,e.getMessage());
					
					
				}
			}
		}
		
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(794, 498));
			BoxLayout thisLayout = new BoxLayout(this, javax.swing.BoxLayout.Y_AXIS);
			this.setLayout(thisLayout);
			this.setSize(794, 498);
			{
				jPanel_UP = new JScrollPane();
				this.add(jPanel_UP);
				jPanel_UP.setBorder(BorderFactory.createTitledBorder("\u822a\u73ed\u4fe1\u606f"));
				jPanel_UP.setPreferredSize(new java.awt.Dimension(794, 144));
				
				table = new JTable();
				jPanel_UP.setViewportView(table);
				
				table.setModel(tableModel);
				table.setRowHeight(80);
				setSuitableColumn(table);//设置列宽以匹配显示
			}
			{
				jPanel_MID = new JPanel();
				this.add(jPanel_MID);
				jPanel_MID.setBorder(BorderFactory.createTitledBorder("\u65c5\u5ba2\u4fe1\u606f"));
				jPanel_MID.setLayout(null);
				jPanel_MID.setPreferredSize(new java.awt.Dimension(794, 332));
				{
					jLabel1 = new JLabel();
					jPanel_MID.add(jLabel1);
					jLabel1.setText("\u65c5\u5ba2\u59d3\u540d\uff1a");
					jLabel1.setBounds(80, 70, 60, 17);
					jLabel1.setSize(80, 20);
				}
				{
					jLabel2 = new JLabel();
					jPanel_MID.add(jLabel2);
					jLabel2.setText("\u8eab\u4efd\u8bc1\u53f7\u7801\uff1a");
					jLabel2.setBounds(80, 145, 72, 17);
					jLabel2.setSize(80, 20);
				}
				{
					jLabel3 = new JLabel();
					jPanel_MID.add(jLabel3);
					jLabel3.setText("\u65c5\u5ba2\u7535\u8bdd\uff1a");
					jLabel3.setBounds(384, 70, 60, 17);
					jLabel3.setSize(80, 20);
				}
				{
					jTextField_Name = new JTextField();
					jPanel_MID.add(jTextField_Name);
					jTextField_Name.setBounds(165, 70, 190, 24);
				}
				{
					jTextField_ID = new JTextField();
					jPanel_MID.add(jTextField_ID);
					jTextField_ID.setBounds(165, 142, 190, 24);
				}
				{
					jTextField_Tel = new JTextField();
					jPanel_MID.add(jTextField_Tel);
					jTextField_Tel.setBounds(464, 70, 203, 24);
				}
				{
					jLabel4 = new JLabel();
					jPanel_MID.add(jLabel4);
					jLabel4.setText("*\u6ce8\u610f\uff1a\u4e00\u4e2a\u8eab\u4efd\u8bc1\u53f7\u53ea\u80fd\u8ba2\u8d2d\u4e00\u5f20\u673a\u7968\uff0c\u5728\u8be5\u7968\u72b6\u6001\u6b63\u5e38(\u672a\u9000\u7968\u3001\u672a\u8f6c\u7b7e)\u4e14\u672a\u8fc7\u671f\u524d\uff0c\u6b64\u8eab\u4efd\u8bc1\u4e0d\u80fd\u91cd\u65b0\u8ba2\u7968");
					jLabel4.setBounds(5, 257, 784, 17);
				}
			}
			{
				jPanel_DOWN = new JPanel();
				this.add(jPanel_DOWN);
				jPanel_DOWN.setSize(794, 106);
				jPanel_DOWN.setPreferredSize(new java.awt.Dimension(794, 106));
				jPanel_DOWN.setLayout(null);
				{
					jButton_OK = new JButton();
					jPanel_DOWN.add(jButton_OK);
					jButton_OK.setText("\u786e\u5b9a\u8ba2\u7968");
					jButton_OK.setBounds(153, 18, 127, 46);
					jButton_OK.setIcon(Resources.getSaveIcon());
				}
				{
					jButton_Cancel = new JButton();
					jPanel_DOWN.add(jButton_Cancel);
					jButton_Cancel.setText("\u53d6\u6d88\u8ba2\u7968");
					jButton_Cancel.setBounds(503, 18, 127, 46);
					jButton_Cancel.setIcon(Resources.getStopIcon());
				}
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
}
