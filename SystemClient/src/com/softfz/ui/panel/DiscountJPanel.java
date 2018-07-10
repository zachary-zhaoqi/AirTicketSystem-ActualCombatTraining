package com.softfz.ui.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.eltima.components.ui.DatePicker;
import com.softfz.RMIFactory;
import com.softfz.model.Discount;
import com.softfz.model.Flight;
import com.softfz.resources.Resources;
import com.softfz.service.ISystemService;
import com.softfz.ui.table.PageTable;
import com.softfz.ui.table.PageTableModel;
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
public class DiscountJPanel extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7344098267237196254L;
	private JPanel jPanel1;
	private JPanel jPanel_DOWN;
	private JLabel jLabel1;
	private JLabel jLabel3;
	private JLabel jLabel5;
	private JButton jButton_Cancel;
	private JButton jButton_OK;
	private JTextField jTextField_DiscountVal;
	private JLabel jLabel2;
	private JButton jButton_AddDiscount;
	private DatePicker datePicker;
	private JButton jButton_Search;
	private JTextField jTextField_Discount;
	private JTextField jTextField_FlightNo;
	private PageTable pageTable;
	private MyTableModel tableModel;
	private String[] header = new String[]{"航班编号","折扣率","日期"};

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new DiscountJPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public DiscountJPanel() {
		tableModel = new MyTableModel(header);
		initGUI();
		listener();
	}
	
	private void listener(){
		ButtonListener bl = new ButtonListener();
		jButton_OK.addActionListener(bl);
		jButton_Cancel.addActionListener(bl);
	}
	
	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if (cmd.equals("保存")) {
				
			}else if (cmd.equals("取消")){
				datePicker.setText("");
				jTextField_FlightNo.setText("");
				jTextField_DiscountVal.setText("");
			} 
		}
	}
	
	private class MyTableModel extends PageTableModel {
		

		/**
		 * 
		 */
		private static final long serialVersionUID = -837010301399484647L;

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
				value = "<html>起飞城市：" + flight.getFromcity() + "<br>到达城市：" + flight.getTocity() + "</html>";
				break;
			case 2:
				value = "<html>起飞机场：" + flight.getStartairport() + "<br>到达机场：" + flight.getEndairport() + "</html>";
				break;
			case 3:
				value = "<html>起飞时间：" + flight.getPlanstarttime() + "<br>到达时间：" + flight.getPlanendtime() + "</html>";
				break;
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
			
		}

	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(630, 554));
			this.setSize(630, 554);
			this.setLayout(null);
			{
				jPanel1 = new JPanel();
				this.add(jPanel1);
				jPanel1.setBounds(0, 0, 630, 554);
				jPanel1.setLayout(null);
				{
					jLabel1 = new JLabel();
					jPanel1.add(jLabel1);
					jLabel1.setText("\u822a\u73ed\u7f16\u53f7\uff1a");
					jLabel1.setBounds(137, 118, 80, 20);
					jLabel1.setSize(80, 30);
				}
				{
					jLabel2 = new JLabel();
					jPanel1.add(jLabel2);
					jLabel2.setText("\u6253\u6298\u65e5\u671f\uff1a");
					jLabel2.setBounds(137, 185, 60, 15);
					jLabel2.setSize(80, 30);
				}
				{
					jLabel3 = new JLabel();
					jPanel1.add(jLabel3);
					jLabel3.setText("\u6298\u6263\u7387\uff1a");
					jLabel3.setBounds(137, 255, 36, 15);
					jLabel3.setSize(80, 30);
				}
				{
					jTextField_FlightNo = new JTextField();
					jPanel1.add(jTextField_FlightNo);
					jTextField_FlightNo.setBounds(210, 119, 255, 30);
					jTextField_FlightNo.setText("");
				}
				{
					jTextField_DiscountVal = new JTextField();
					jPanel1.add(jTextField_DiscountVal);
					jTextField_DiscountVal.setBounds(210, 256, 255, 30);
					jTextField_DiscountVal.setText("");
				}
				{
					jButton_OK = new JButton();
					jPanel1.add(jButton_OK);
					jButton_OK.setText("\u4fdd\u5b58");
					jButton_OK.setBounds(137, 379, 96, 38);
					jButton_OK.setIcon(Resources.getSaveIcon());
				}
				{
					jButton_Cancel = new JButton();
					jPanel1.add(jButton_Cancel);
					jButton_Cancel.setText("\u53d6\u6d88");
					jButton_Cancel.setBounds(369, 379, 96, 38);
					jButton_Cancel.setIcon(Resources.getStopIcon());
				}
				{
					datePicker = new DatePicker(this, new Date());
					datePicker.setPattern("yyyy-MM-dd");
					jPanel1.add(datePicker);
					datePicker.setBounds(210,185,123,29);
					datePicker.setText("");
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
