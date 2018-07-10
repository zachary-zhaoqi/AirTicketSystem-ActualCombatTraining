package com.softfz.ui;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.eltima.components.ui.DatePicker;


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
public class AddDiscountJDialog extends javax.swing.JDialog {
	private JPanel jPanel1;
	private JLabel jLabel3;
	private JButton jButton_Cancel;
	private JTextField jTextField_DiscountVal;
	private JTextField jTextField_FlightID;
	private JButton jButton_OK;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private DatePicker datePicker;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				AddDiscountJDialog inst = new AddDiscountJDialog(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public AddDiscountJDialog(JFrame frame) {
		super(frame);
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
			if (cmd.equals("确定")) {
				
				
			}else if(cmd.equals("取消")){
				
			}
		}
	}
	private void initGUI() {
		try {
			{
				this.setTitle("\u65b0\u589e\u6298\u6263\u4fe1\u606f");
			}
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				jPanel1.setLayout(null);
				{
					jLabel1 = new JLabel();
					jPanel1.add(jLabel1);
					jLabel1.setText("\u822a\u73edID\uff1a");
					jLabel1.setBounds(76, 41, 80, 20);
				}
				{
					jLabel2 = new JLabel();
					jPanel1.add(jLabel2);
					jLabel2.setText("\u65e5\u671f\uff1a");
					jLabel2.setBounds(76, 86, 80, 20);
				}
				{
					jLabel3 = new JLabel();
					jPanel1.add(jLabel3);
					jLabel3.setText("\u6298\u6263\u7387\uff1a");
					jLabel3.setBounds(76, 138, 80, 20);
				}
				{
					jButton_OK = new JButton();
					jPanel1.add(jButton_OK);
					jButton_OK.setText("\u786e\u5b9a");
					jButton_OK.setBounds(76, 190, 95, 43);
				}
				{
					jButton_Cancel = new JButton();
					jPanel1.add(jButton_Cancel);
					jButton_Cancel.setText("\u53d6\u6d88");
					jButton_Cancel.setBounds(227, 190, 95, 43);
				}
				{
					jTextField_FlightID = new JTextField();
					jPanel1.add(jTextField_FlightID);
					jTextField_FlightID.setBounds(140, 39, 179, 26);
				}
				{
					jTextField_DiscountVal = new JTextField();
					jPanel1.add(jTextField_DiscountVal);
					jTextField_DiscountVal.setBounds(140, 138, 179, 26);
				}
				{
					datePicker = new DatePicker(this, new Date());
					datePicker.setPattern("yyyy-MM-dd");
					jPanel1.add(datePicker);
					datePicker.setBounds(140,80,123,29);
				}
			}
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
