package com.softfz.ui;

import java.awt.Dimension;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.softfz.resources.Resources;

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
public class DIYFlightListPanel extends javax.swing.JPanel {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JButton jButton_Select;
	private JTable jTable1;
	private JScrollPane jScrollPane1;
	private JButton jButton_GO;
	private JTextField jTextField_RecordPerPage;
	private JLabel jLabel_RecordPerPage;
	private JButton jButton_LastPage;
	private JButton jButton_PageDown;
	private JButton jButton_PageUp;
	private JButton jButton_FirstPage;
	private JLabel jLabel_TotalRecord;
	private JLabel jLabel_Page;
	private JComboBox jComboBox_StopBy;
	private JComboBox jComboBox_Type;
	private JTextField jTextField_StopCity;
	private JTextField jTextField_StartCity;
	private JTextField jTextField_Num;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new DIYFlightListPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public DIYFlightListPanel() {
		super();
		initGUI();
	}
	
	@SuppressWarnings("unchecked")
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(630, 554));
			this.setSize(630, 554);
			this.setLayout(null);
			{
				jLabel1 = new JLabel();
				this.add(jLabel1);
				jLabel1.setText("\u822a\u73ed\u7f16\u53f7\uff1a");
				jLabel1.setBounds(49, 24, 70, 15);
			}
			{
				jLabel2 = new JLabel();
				this.add(jLabel2);
				jLabel2.setText("\u822a\u73ed\u7c7b\u578b\uff1a");
				jLabel2.setBounds(47, 62, 60, 15);
				jLabel2.setSize(70, 15);
			}
			{
				jLabel3 = new JLabel();
				this.add(jLabel3);
				jLabel3.setText("\u8d77\u98de\u57ce\u5e02\uff1a");
				jLabel3.setBounds(217, 24, 60, 15);
				jLabel3.setSize(70, 15);
			}
			{
				jLabel4 = new JLabel();
				this.add(jLabel4);
				jLabel4.setText("\u662f\u5426\u7ecf\u505c\uff1a");
				jLabel4.setBounds(217, 62, 60, 15);
				jLabel4.setSize(70, 15);
			}
			{
				jLabel5 = new JLabel();
				this.add(jLabel5);
				jLabel5.setText("\u5230\u8fbe\u57ce\u5e02\uff1a");
				jLabel5.setBounds(398, 24, 60, 15);
				jLabel5.setSize(70, 15);
			}
			{
				jTextField_Num = new JTextField();
				this.add(jTextField_Num);
				jTextField_Num.setBounds(113, 21, 86, 22);
			}
			{
				jTextField_StartCity = new JTextField();
				this.add(jTextField_StartCity);
				jTextField_StartCity.setBounds(282, 21, 98, 22);
			}
			{
				jTextField_StopCity = new JTextField();
				this.add(jTextField_StopCity);
				jTextField_StopCity.setBounds(464, 22, 106, 22);
			}
			{
				ComboBoxModel jComboBox_TypeModel = 
						new DefaultComboBoxModel(
								new String[] { "Item One", "Item Two" });
				jComboBox_Type = new JComboBox();
				this.add(jComboBox_Type);
				jComboBox_Type.setModel(jComboBox_TypeModel);
				jComboBox_Type.setBounds(113, 55, 86, 30);
				jComboBox_Type.setSize(86, 30);
			}
			{
				ComboBoxModel jComboBox_StopByModel = 
						new DefaultComboBoxModel(
								new String[] { "Item One", "Item Two" });
				jComboBox_StopBy = new JComboBox();
				this.add(jComboBox_StopBy);
				jComboBox_StopBy.setModel(jComboBox_StopByModel);
				jComboBox_StopBy.setBounds(282, 55, 97, 30);
			}
			{
				jLabel_Page = new JLabel();
				this.add(jLabel_Page);
				jLabel_Page.setText("\u5f53\u524d\u663e\u793a1/1\u9875");
				jLabel_Page.setBounds(12, 524, 86, 15);
			}
			{
				jLabel_TotalRecord = new JLabel();
				this.add(jLabel_TotalRecord);
				jLabel_TotalRecord.setText("\u51710\u6761\u8bb0\u5f55");
				jLabel_TotalRecord.setBounds(104, 524, 63, 15);
			}
			{
				jButton_FirstPage = new JButton();
				this.add(jButton_FirstPage);
				jButton_FirstPage.setText("\u9996\u9875");
				jButton_FirstPage.setBounds(167, 521, 70, 22);
			}
			{
				jButton_PageUp = new JButton();
				this.add(jButton_PageUp);
				jButton_PageUp.setText("\u4e0a\u9875");
				jButton_PageUp.setBounds(242, 521, 70, 22);
			}
			{
				jButton_PageDown = new JButton();
				this.add(jButton_PageDown);
				jButton_PageDown.setText("\u4e0b\u9875");
				jButton_PageDown.setBounds(317, 521, 70, 22);
			}
			{
				jButton_LastPage = new JButton();
				this.add(jButton_LastPage);
				jButton_LastPage.setText("\u5c3e\u9875");
				jButton_LastPage.setBounds(392, 521, 70, 22);
			}
			{
				jLabel_RecordPerPage = new JLabel();
				this.add(jLabel_RecordPerPage);
				jLabel_RecordPerPage.setText("\u6bcf\u9875\u663e\u793a");
				jLabel_RecordPerPage.setBounds(469, 524, 60, 15);
			}
			{
				jTextField_RecordPerPage = new JTextField();
				this.add(jTextField_RecordPerPage);
				jTextField_RecordPerPage.setText("0");
				jTextField_RecordPerPage.setBounds(529, 521, 31, 22);
			}
			{
				jButton_GO = new JButton();
				this.add(jButton_GO);
				jButton_GO.setText("GO");
				jButton_GO.setBounds(566, 521, 53, 22);
			}
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setBounds(12, 91, 606, 418);
				{
					TableModel jTable1Model = 
							new DefaultTableModel(
									new String[][] { { "One", "Two" }, { "Three", "Four" } },
									new String[] { "航班编号", "城市", "机场", "时间", "是否经停" });
					jTable1 = new JTable();
					jScrollPane1.setViewportView(jTable1);
					jTable1.setModel(jTable1Model);
				}
			}
			{
				jButton_Select = new JButton();
				this.add(jButton_Select);
				jButton_Select.setText("\u67e5\u8be2");
				jButton_Select.setBounds(464, 57, 106, 28);
				jButton_Select.setIcon(Resources.getFindIcon());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
