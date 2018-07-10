package com.softfz.ui.panel;

import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.eltima.components.ui.DatePicker;
import com.softfz.model.Flight;
import com.softfz.resources.Resources;
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
public class FlightDiscountPanel extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7344098267237196254L;
	private JPanel jPanel_UP;
	private JPanel jPanel_DOWN;
	private JLabel jLabel1;
	private JLabel jLabel3;
	private JLabel jLabel5;
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
		frame.getContentPane().add(new FlightDiscountPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public FlightDiscountPanel() {
		tableModel = new MyTableModel(header);
		initGUI();
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
				jPanel_UP = new JPanel();
				this.add(jPanel_UP);
				jPanel_UP.setBounds(0, 0, 630, 101);
				jPanel_UP.setLayout(null);
				jPanel_UP.setBorder(BorderFactory.createTitledBorder("\u67e5\u8be2\u6761\u4ef6"));
				{
					jLabel1 = new JLabel();
					jPanel_UP.add(jLabel1);
					jLabel1.setText("\u822a\u73edID\uff1a");
					jLabel1.setBounds(30, 22, 80, 20);
				}
				{
					jLabel3 = new JLabel();
					jPanel_UP.add(jLabel3);
					jLabel3.setText("\u6298\u6263\u7387\uff1a");
					jLabel3.setBounds(30, 65, 80, 20);
				}
				{
					jLabel5 = new JLabel();
					jPanel_UP.add(jLabel5);
					jLabel5.setText("\u65e5\u671f\uff1a");
					jLabel5.setBounds(225, 20, 80, 20);
				}
				{
					jTextField_FlightNo = new JTextField();
					jPanel_UP.add(jTextField_FlightNo);
					jTextField_FlightNo.setBounds(86, 20, 113, 24);
				}
				{
					jTextField_Discount = new JTextField();
					jPanel_UP.add(jTextField_Discount);
					jTextField_Discount.setBounds(86, 65, 113, 24);
				}
				{
					datePicker = new DatePicker(this, new Date());
					datePicker.setPattern("yyyy-MM-dd");
					jPanel_UP.add(datePicker);
					datePicker.setBounds(270,18,123,29);
				}
				
				{
					jButton_Search = new JButton();
					jPanel_UP.add(jButton_Search);
					jButton_Search.setText("\u67e5\u8be2");
					jButton_Search.setBounds(300, 60, 90, 32);
					jButton_Search.setIcon(Resources.getFindIcon());
				}
				{
					jButton_AddDiscount = new JButton();
					jPanel_UP.add(jButton_AddDiscount);
					jButton_AddDiscount.setText("\u65b0\u589e\u6298\u6263\u4fe1\u606f");
					jButton_AddDiscount.setBounds(459, 18, 136, 67);
				}
			}
			{
				// 分页表格
				pageTable = new PageTable(tableModel);
				pageTable.setEnabled(true);
				pageTable.setBounds(10, 20, 610, 430);
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
