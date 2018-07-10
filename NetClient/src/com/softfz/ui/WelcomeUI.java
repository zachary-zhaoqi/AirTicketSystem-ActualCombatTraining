package com.softfz.ui;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;


public class WelcomeUI extends JFrame{
	
	private JPanel jPanel1;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	public static void main(String[] args) {
		WelcomeUI st = new WelcomeUI();
		st.setVisible(true);
		
	}
	
	public WelcomeUI(){
		init();
		
	}
	
	private void init(){
		
		
		{
			this.setSize(830, 600);
			getContentPane().setLayout(null);
//			this.setLocationRelativeTo(null);
			this.setUndecorated(true); // 去掉窗口的装饰 
		    this.getRootPane().setWindowDecorationStyle(JRootPane.NONE); //采用指定的窗口装饰风格 
		    //设置居中显示
		    int w = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth()) / 2;
		    int h = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getHeight()) / 2;
		    this.setLocation(w, h);
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1);
				jPanel1.setBounds(0, 0, 830, 600);
				jPanel1.setLayout(null);
				{
					jLabel2 = new JLabel();
					jPanel1.add(jLabel2);
					jLabel2.setBounds(20, 500, 100, 100);
					jLabel2.setIcon(new ImageIcon("./picture/1-1.GIF"));
				}
				
				{
					jLabel3 = new JLabel();
					jPanel1.add(jLabel3);
					jLabel3.setText("请稍后，程序加载中...");
					jLabel3.setBounds(100, 550, 200, 30);
				}
				
				{
					jLabel1 = new JLabel();
					jPanel1.add(jLabel1);
					jLabel1.setBounds(0, 0, 830, 600);
					jLabel1.setIcon(new ImageIcon("./picture/loadSale.jpg"));
				}
			}
		}
	}
}

