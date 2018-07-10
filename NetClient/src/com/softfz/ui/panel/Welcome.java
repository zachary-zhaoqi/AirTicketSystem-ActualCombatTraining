package com.softfz.ui.panel;

import java.awt.Dimension;

import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
public class Welcome extends javax.swing.JPanel {
	private JLabel jLabel1;
	private JLabel jLabel2;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new Welcome());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public Welcome() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(784, 488));
			this.setSize(784, 488);
			this.setLayout(null);
			{
				jLabel1 = new JLabel();
				this.add(jLabel1);
				jLabel1.setText("\u6b22\u8fce\u4f7f\u7528\u822a\u7a7a\u7968\u52a1\u7cfb\u7edf");
				jLabel1.setBounds(248, 114, 290, 157);
				jLabel1.setFont(new java.awt.Font("华文隶书",0,28));
			}
			{
				jLabel2 = new JLabel();
				this.add(jLabel2);
				jLabel2.setText("\u9500\u552e\u7f51\u70b9\u5b50\u7cfb\u7edf");
				jLabel2.setFont(new java.awt.Font("华文隶书",0,28));
				jLabel2.setBounds(293, 164, 200, 157);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
