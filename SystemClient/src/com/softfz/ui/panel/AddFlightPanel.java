package com.softfz.ui.panel;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.WindowConstants;

import com.softfz.RMIFactory;
import com.softfz.SystemContext;
import com.softfz.model.Dirctory;
import com.softfz.model.Flight;
import com.softfz.model.FlightStop;
import com.softfz.resources.Resources;
import com.softfz.service.ISystemService;
import com.softfz.utils.CheckUtil;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class AddFlightPanel extends javax.swing.JPanel {
	private JPanel jPanel0;
	private JPanel jPanel1;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JComboBox jComboBox2;
	private JComboBox jComboBox1;
	private JTextField jTextField9;
	private JTextField jTextField8;
	private JTextField jTextField7;
	private JTextField jTextField6;
	private JButton jButton_IsStop;
	private JButton jButton_Cancel;
	private JButton jButton_Save;
	private JComboBox jComboBox5;
	private JLabel jLabel20;
	private JComboBox jComboBox4;
	private JTextField jTextField15;
	private JTextField jTextField14;
	private JTextField jTextField13;
	private JTextField jTextField12;
	private JTextField jTextField11;
	private JTextField jTextField10;
	private JLabel jLabel19;
	private JLabel jLabel18;
	private JLabel jLabel17;
	private JLabel jLabel16;
	private JLabel jLabel15;
	private JLabel jLabel14;
	private JLabel jLabel13;
	private JComboBox jComboBox3;
	private JTextField jTextField5;
	private JTextField jTextField4;
	private JTextField jTextField3;
	private JTextField jTextField2;
	private JTextField jTextField1;
	private JLabel jLabel12;
	private JLabel jLabel11;
	private JLabel jLabel10;
	private JLabel jLabel9;
	private JLabel jLabel8;
	private JLabel jLabel7;
	private JLabel jLabel6;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JPanel jPanel2;
	private Boolean flagIsStop = false;

	private List<Dirctory> allAirPorts;
	private List<Dirctory> allAirLines;
	private AirModel airLineModel2;
	private AirModel airLineModel3;
	private AirModel airLineModel4;
	private AirModel airLineModel5;
	private AirRender render = new AirRender();
	private ISystemService systemService;

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new AddFlightPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public AddFlightPanel() {

		getExistAirportAndCompany();
		initGUI();
		listener();
		isStopFun();
	}

	private void listener() {
		ButtonListener bl = new ButtonListener();
		jButton_Save.addActionListener(bl);
		jButton_Cancel.addActionListener(bl);
		jButton_IsStop.addActionListener(bl);
	}

	private void checkInputInfo() throws Exception {

	}

	private void checkIsError(int inputTextInt) throws Exception {
		String errorNoticeString = CheckUtil.getMapNoticeInfo(inputTextInt);
		if (!errorNoticeString.equals("OK")) {
			throw new Exception(errorNoticeString);
		}
	}

	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if (cmd.equals("保存")) {

			} else if (cmd.equals("取消")) {
				
			} else if (cmd.equals("是否经停(无经停)")){

			} else if (cmd.equals("是否经停(有经停)")){

			}
		}

	}

	private void cleanInputInfo() {
		jTextField1.setText("");
		jTextField2.setText("");
		jTextField3.setText("");
		jTextField4.setText("");
		jTextField5.setText("");
		jTextField6.setText("");
		jTextField7.setText("");
		jTextField8.setText("");
		jTextField9.setText("");
		jTextField10.setText("");
		jTextField11.setText("");
		jTextField12.setText("");
		jTextField13.setText("");
		jTextField14.setText("");
		jTextField15.setText("");
	}

	/**
	 * 获取界面上航班基本信息
	 * 
	 * @return
	 */
	private Flight getFlightBasicInfo() {

		return null;
	}

	/**
	 * 获取界面上航班经停信息
	 * 
	 * @return
	 */
	private FlightStop getFlightStopInfo(int flightid) {
		// 以下涉及经停表中字段
		return null;

	}

	/**
	 * 获取数据库中存在的机场和航空公司数据
	 */
	private void getExistAirportAndCompany() {

	}
	
	/**
	 * 定义AirModel
	 * @author Administrator
	 *
	 */
	private class AirModel extends DefaultComboBoxModel {
		public AirModel(List<Dirctory> airPorts) {
			if (airPorts != null) {
				for (Dirctory dirctory : airPorts) {
					this.addElement(dirctory);
				}
			}
		}
	}
	
	/**
	 * 设置渲染器
	 * @author Administrator
	 *
	 */
	private class AirRender implements ListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			String text = "";
			if (value instanceof Dirctory) {
				Dirctory o = (Dirctory) value;
				text = o.getDicname();
			}
			JLabel jLabel = new JLabel(text);
			return jLabel;
		}

	}

	private void initGUI() {
		try {
			{
				this.setPreferredSize(new java.awt.Dimension(630, 554));
				this.setLayout(null);
			}

			jPanel0 = new JPanel();
			this.add(jPanel0);
			BoxLayout thisLayout = new BoxLayout(jPanel0,
					javax.swing.BoxLayout.Y_AXIS);
			jPanel0.setLayout(thisLayout);
			jPanel0.setBorder(BorderFactory
					.createTitledBorder("\u822a\u73ed\u4fe1\u606f"));
			jPanel0.setBounds(5, 5, 620, 502);
			{
				jButton_Save = new JButton();
				this.add(jButton_Save);
				jButton_Save.setText("\u4fdd\u5b58");
				jButton_Save.setBounds(175, 513, 95, 29);
				jButton_Save.setIcon(Resources.getSaveIcon());
			}
			{
				jButton_Cancel = new JButton();
				this.add(jButton_Cancel);
				jButton_Cancel.setText("\u53d6\u6d88");
				jButton_Cancel.setBounds(351, 513, 95, 29);
				jButton_Cancel.setIcon(Resources.getStopIcon());
			}
			{
				jPanel1 = new JPanel();
				jPanel0.add(jPanel1);
				jPanel1.setBounds(5, 18, 610, 322);
				jPanel1.setBorder(BorderFactory
						.createTitledBorder("\u57fa\u672c\u4fe1\u606f"));
				jPanel1.setLayout(null);
				jPanel1.setPreferredSize(new java.awt.Dimension(610, 118));
				{
					jLabel1 = new JLabel();
					jPanel1.add(jLabel1);
					jLabel1.setText("\u822a\u73ed\u7f16\u53f7\uff1a");
					jLabel1.setBounds(17, 35, 72, 21);
				}
				{
					jLabel2 = new JLabel();
					jPanel1.add(jLabel2);
					jLabel2.setText("\u822a\u73ed\u673a\u578b\uff1a");
					jLabel2.setBounds(18, 89, 72, 21);
				}
				{
					jLabel3 = new JLabel();
					jPanel1.add(jLabel3);
					jLabel3.setText("\u7968\u9762\u4ef7\u683c\uff1a");
					jLabel3.setBounds(17, 131, 72, 21);
				}
				{
					jLabel4 = new JLabel();
					jPanel1.add(jLabel4);
					jLabel4.setText("\u822a\u73ed\u7c7b\u578b\uff1a");
					jLabel4.setBounds(17, 173, 72, 21);
				}
				{
					jLabel5 = new JLabel();
					jPanel1.add(jLabel5);
					jLabel5.setText("\u822a\u73ed\u91cc\u7a0b\uff1a");
					jLabel5.setBounds(17, 257, 72, 21);
				}
				{
					jLabel6 = new JLabel();
					jPanel1.add(jLabel6);
					jLabel6.setText("\u51fa\u53d1\u57ce\u5e02\uff1a");
					jLabel6.setBounds(201, 35, 72, 21);
				}
				{
					jLabel7 = new JLabel();
					jPanel1.add(jLabel7);
					jLabel7.setText("\u8d77\u98de\u65f6\u95f4\uff1a");
					jLabel7.setBounds(204, 89, 72, 21);
				}
				{
					jLabel8 = new JLabel();
					jPanel1.add(jLabel8);
					jLabel8.setText("\u8d77\u98de\u673a\u573a\uff1a");
					jLabel8.setBounds(205, 131, 72, 21);
				}
				{
					jLabel9 = new JLabel();
					jPanel1.add(jLabel9);
					jLabel9.setText("\u5230\u8fbe\u673a\u573a\uff1a");
					jLabel9.setBounds(205, 173, 72, 21);
				}
				{
					jLabel10 = new JLabel();
					jPanel1.add(jLabel10);
					jLabel10.setText("\u5ea7\u4f4d\u6570\u91cf\uff1a");
					jLabel10.setBounds(209, 258, 72, 21);
				}
				{
					jLabel11 = new JLabel();
					jPanel1.add(jLabel11);
					jLabel11.setText("\u5230\u8fbe\u57ce\u5e02\uff1a");
					jLabel11.setBounds(392, 35, 72, 21);
				}
				{
					jLabel12 = new JLabel();
					jPanel1.add(jLabel12);
					jLabel12.setText("\u5230\u8fbe\u65f6\u95f4\uff1a");
					jLabel12.setBounds(392, 89, 72, 21);
				}
				{
					jTextField1 = new JTextField();
					jPanel1.add(jTextField1);
					jTextField1.setBounds(87, 35, 102, 22);
				}
				{
					jTextField2 = new JTextField();
					jPanel1.add(jTextField2);
					jTextField2.setBounds(87, 89, 102, 22);
				}
				{
					jTextField3 = new JTextField();
					jPanel1.add(jTextField3);
					jTextField3.setBounds(87, 131, 102, 22);
				}
				{
					jTextField4 = new JTextField();
					jPanel1.add(jTextField4);
					jTextField4.setBounds(89, 257, 102, 22);
				}
				{
					jTextField5 = new JTextField();
					jPanel1.add(jTextField5);
					jTextField5.setBounds(273, 35, 102, 22);
				}
				{
					jTextField6 = new JTextField();
					jPanel1.add(jTextField6);
					jTextField6.setBounds(271, 89, 102, 22);
				}
				{
					jTextField7 = new JTextField();
					jPanel1.add(jTextField7);
					jTextField7.setBounds(276, 258, 102, 22);
				}
				{
					jTextField8 = new JTextField();
					jPanel1.add(jTextField8);
					jTextField8.setBounds(464, 35, 102, 22);
				}
				{
					jTextField9 = new JTextField();
					jPanel1.add(jTextField9);
					jTextField9.setBounds(463, 89, 102, 22);
				}
				{
					ComboBoxModel jComboBox1Model = new DefaultComboBoxModel(
							new String[] { "国内航班", "国际航班" });
					jComboBox1 = new JComboBox();
					jPanel1.add(jComboBox1);
					jComboBox1.setModel(jComboBox1Model);
					jComboBox1.setBounds(87, 172, 103, 22);
				}
				{
					// ComboBoxModel jComboBox2Model =
					// new DefaultComboBoxModel(
					// new String[] { "Item One", "Item Two" });
					jComboBox2 = new JComboBox();
					jPanel1.add(jComboBox2);
					// jComboBox2.setModel(jComboBox2Model);
					jComboBox2.setBounds(272, 130, 294, 22);

					airLineModel2 = new AirModel(this.allAirPorts);
					jComboBox2.setModel(airLineModel2);
					jComboBox2.setRenderer(render);
				}
				{
					// ComboBoxModel jComboBox3Model =
					// new DefaultComboBoxModel(
					// new String[] { "Item One", "Item Two" });
					jComboBox3 = new JComboBox();
					jPanel1.add(jComboBox3);
					// jComboBox3.setModel(jComboBox3Model);
					jComboBox3.setBounds(272, 172, 295, 22);

					airLineModel3 = new AirModel(this.allAirPorts);
					jComboBox3.setModel(airLineModel3);
					jComboBox3.setRenderer(render);
				}
				{
					jLabel20 = new JLabel();
					jPanel1.add(jLabel20);
					jLabel20.setText("\u6240\u5c5e\u822a\u7a7a\u516c\u53f8\uff1a");
					jLabel20.setBounds(17, 219, 92, 15);
				}
				{
					// ComboBoxModel jComboBox5Model =
					// new DefaultComboBoxModel(
					// new String[] { "Item One", "Item Two" });
					jComboBox5 = new JComboBox();
					jPanel1.add(jComboBox5);
					// jComboBox5.setModel(jComboBox5Model);
					jComboBox5.setBounds(109, 216, 458, 22);

					airLineModel5 = new AirModel(this.allAirLines);
					jComboBox5.setModel(airLineModel5);
					jComboBox5.setRenderer(render);

				}
				{
					jPanel1.add(getJButton_IsStop());
				}
			}
			{
				jPanel2 = new JPanel();
				jPanel0.add(jPanel2);
				jPanel2.setBounds(5, 346, 610, 170);
				jPanel2.setBorder(BorderFactory
						.createTitledBorder("\u7ecf\u505c\u4fe1\u606f"));
				jPanel2.setLayout(null);
				{
					jLabel13 = new JLabel();
					jPanel2.add(jLabel13);
					jLabel13.setText("\u7ecf\u505c\u57ce\u5e02\uff1a");
					jLabel13.setBounds(17, 42, 72, 15);
				}
				{
					jLabel14 = new JLabel();
					jPanel2.add(jLabel14);
					jLabel14.setText("\u91cc\u7a0b\u6570\uff1a");
					jLabel14.setBounds(17, 85, 60, 15);
				}
				{
					jLabel15 = new JLabel();
					jPanel2.add(jLabel15);
					jLabel15.setText("\u7ecf\u505c\u673a\u573a\uff1a");
					jLabel15.setBounds(17, 136, 67, 15);
				}
				{
					jLabel16 = new JLabel();
					jPanel2.add(jLabel16);
					jLabel16.setText("\u7968\u9762\u4ef7\u683c\uff1a");
					jLabel16.setBounds(211, 42, 72, 15);
				}
				{
					jLabel17 = new JLabel();
					jPanel2.add(jLabel17);
					jLabel17.setText("\u8d77\u98de\u7968\u4ef7\uff1a");
					jLabel17.setBounds(211, 85, 72, 15);
				}
				{
					jLabel18 = new JLabel();
					jPanel2.add(jLabel18);
					jLabel18.setText("\u5230\u8fbe\u65f6\u95f4\uff1a");
					jLabel18.setBounds(411, 42, 66, 15);
				}
				{
					jLabel19 = new JLabel();
					jPanel2.add(jLabel19);
					jLabel19.setText("\u8d77\u98de\u65f6\u95f4\uff1a");
					jLabel19.setBounds(411, 85, 66, 15);
				}
				{
					jTextField10 = new JTextField();
					jPanel2.add(jTextField10);
					jTextField10.setBounds(90, 39, 103, 22);
				}
				{
					jTextField11 = new JTextField();
					jPanel2.add(jTextField11);
					jTextField11.setBounds(89, 85, 104, 22);
				}
				{
					jTextField12 = new JTextField();
					jPanel2.add(jTextField12);
					jTextField12.setBounds(283, 39, 104, 22);
				}
				{
					jTextField13 = new JTextField();
					jPanel2.add(jTextField13);
					jTextField13.setBounds(283, 85, 104, 22);
				}
				{
					jTextField14 = new JTextField();
					jPanel2.add(jTextField14);
					jTextField14.setBounds(477, 42, 105, 22);
				}
				{
					jTextField15 = new JTextField();
					jPanel2.add(jTextField15);
					jTextField15.setBounds(477, 82, 105, 22);
				}
				{
					// ComboBoxModel jComboBox4Model =
					// new DefaultComboBoxModel(
					// new String[] { "Item One", "Item Two" });
					jComboBox4 = new JComboBox();
					jPanel2.add(jComboBox4);
					// jComboBox4.setModel(jComboBox4Model);
					jComboBox4.setBounds(89, 132, 355, 22);

					airLineModel4 = new AirModel(this.allAirPorts);
					jComboBox4.setModel(airLineModel4);
					jComboBox4.setRenderer(render);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private JButton getJButton_IsStop() {
		if(jButton_IsStop == null) {
			jButton_IsStop = new JButton();
			jButton_IsStop.setText("是否经停(无经停)");
			jButton_IsStop.setBounds(432, 258, 135, 22);
			jButton_IsStop.setBackground(new java.awt.Color(255,128,64));//白色
		}
		return jButton_IsStop;
	}
	
	private void isStopFun(){
		if(flagIsStop){
			jButton_IsStop.setBackground(new java.awt.Color(255,255,255));
//			jButton_IsStop.setBackground(Color.blue);
			jButton_IsStop.setText("是否经停(有经停)");
			jTextField10.setEnabled(true);
			jTextField11.setEnabled(true);
			jTextField12.setEnabled(true);
			jTextField13.setEnabled(true);
			jTextField14.setEnabled(true);
			jTextField15.setEnabled(true);
			jComboBox4.setEnabled(true);
			jLabel13.setEnabled(true);
			jLabel14.setEnabled(true);
			jLabel15.setEnabled(true);
			jLabel16.setEnabled(true);
			jLabel17.setEnabled(true);
			jLabel18.setEnabled(true);
			jLabel19.setEnabled(true);			
		}else{
			jButton_IsStop.setBackground(new java.awt.Color(255,128,64));
			jButton_IsStop.setText("是否经停(无经停)");
			jTextField10.setEnabled(false);
			jTextField11.setEnabled(false);
			jTextField12.setEnabled(false);
			jTextField13.setEnabled(false);
			jTextField14.setEnabled(false);
			jTextField15.setEnabled(false);
			jComboBox4.setEnabled(false);
			jLabel13.setEnabled(false);
			jLabel14.setEnabled(false);
			jLabel15.setEnabled(false);
			jLabel16.setEnabled(false);
			jLabel17.setEnabled(false);
			jLabel18.setEnabled(false);
			jLabel19.setEnabled(false);
			jTextField10.setText("");
			jTextField11.setText("");
			jTextField12.setText("");
			jTextField13.setText("");
			jTextField14.setText("");
			jTextField15.setText("");		
		}
	}

}
