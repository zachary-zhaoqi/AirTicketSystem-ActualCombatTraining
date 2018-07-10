package com.softfz.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;
import javax.sql.DataSource;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.xml.crypto.Data;
import javax.xml.stream.events.StartElement;

import com.softfz.config.DataSourceConfig;
import com.softfz.config.DataSourceConfigFile;
import com.softfz.resources.Resources;
import com.softfz.server.IServiceOperator;
import com.softfz.server.ServerOperatorImpl;
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
public class DBConfigJPanel extends javax.swing.JPanel {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JButton jButton1;
	private JButton jButton2;  //保存設置按鈕
	private JTextField jTextField5;
	private JTextField jTextField4;
	private JTextField jTextField3;
	private JTextField jTextField2;
	private JTextField jTextField1;
	private int errorNum;
	private IServiceOperator serverOperatorImpl;
	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new DBConfigJPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public DBConfigJPanel() {
		super();
		serverOperatorImpl=ServerOperatorImpl.getInstance();
		initGUI();
		listener();
		initData();
	}

	private void listener() {
		ButtonListener bl = new ButtonListener();
		jButton1.addActionListener(bl);
		jButton2.addActionListener(bl);

	}

	private void initData() {
		//TODO: 读取datasource.properties配置文件中的数据显示到界面上
		serverOperatorImpl.initDbConfig();
		jTextField1.setText(DataSourceConfig.ip);
		jTextField2.setText(DataSourceConfig.port);
		jTextField3.setText(DataSourceConfig.sid);
		jTextField4.setText(DataSourceConfig.user);
		jTextField5.setText(DataSourceConfig.password);
	}

	/**
	 * 从核查类中取得核查的错误信息
	 * 
	 * @param errorNum
	 * @throws Exception
	 */
	private void checkIsError(int errorNum) throws Exception {
		if (errorNum != CheckUtil.OK) {
			String errorInfo = CheckUtil.ERRMsgMap.get(errorNum);
			throw new Exception(errorInfo);
		}
	}

	/**
	 * 测试连接
	 */
	private void testConnection(String ip, String port, String sid,
			String user, String password) {
		DataSource dataSource=DataSourceConfig.getDataSource();
		try {
			Connection connection=dataSource.getConnection();
			String sql="select * from BOUNCERECORD;";
			Statement statement=connection.createStatement();
			ResultSet resultSet=statement.executeQuery(sql);
			if (resultSet.next()) {
				JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
						"连接成功", "一个很温馨的通知", JOptionPane.INFORMATION_MESSAGE); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
					"连接失败了", "一个令人难过的通知", JOptionPane.INFORMATION_MESSAGE); 
			e.printStackTrace();
		}
		finally {
			DataSourceConfig.closeDataSource();
		}
	}

	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if (cmd.equals("测试连接")) {
				initData();
				testConnection(jTextField1.getText(), jTextField2.getText(), jTextField3.getText(), jTextField4.getText(), jTextField5.getText());
			} else if (cmd.equals("保存配置")) {
				//TODO:
				String ip=jTextField1.getText();
				String port=jTextField2.getText();
				String sid=jTextField3.getText();
				String user=jTextField4.getText();
				String password=jTextField5.getText();
				try {
					checkIsError(CheckUtil.checkSidUserPwd(sid, user, password));
					checkIsError(CheckUtil.checkIp(ip));
					checkIsError(CheckUtil.checkPort(port));
					serverOperatorImpl.updateDbConfig(ip, port, sid, user, password);
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
							e1.getMessage(), "一个令人难过的通知", JOptionPane.INFORMATION_MESSAGE);
				}
		
				
			}
		}
	}

	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(580, 336));
			this.setSize(580, 336);
			this.setLayout(null);
			{
				jLabel1 = new JLabel();
				this.add(jLabel1);
				jLabel1.setText("IP\u5730\u5740\uff1a");
				jLabel1.setBounds(138, 33, 79, 34);
			}
			{
				jLabel2 = new JLabel();
				this.add(jLabel2);
				jLabel2.setText("\u7aef\u53e3\uff1a");
				jLabel2.setBounds(138, 73, 79, 34);
			}
			{
				jLabel3 = new JLabel();
				this.add(jLabel3);
				jLabel3.setText("SID\uff1a");
				jLabel3.setBounds(138, 113, 79, 34);
			}
			{
				jLabel4 = new JLabel();
				this.add(jLabel4);
				jLabel4.setText("\u8d26\u53f7\uff1a");
				jLabel4.setBounds(138, 153, 79, 34);
			}
			{
				jLabel5 = new JLabel();
				this.add(jLabel5);
				jLabel5.setText("\u5bc6\u7801\uff1a");
				jLabel5.setBounds(138, 193, 79, 34);
			}
			{ // IP
				jTextField1 = new JTextField();
				this.add(jTextField1);
				jTextField1.setText("127.0.0.1");
				jTextField1.setBounds(218, 37, 232, 27);
			}
			{ // port
				jTextField2 = new JTextField();
				this.add(jTextField2);
				jTextField2.setText("3306");
				jTextField2.setBounds(218, 78, 232, 27);
			}
			{ // SID
				jTextField3 = new JTextField();
				this.add(jTextField3);
				jTextField3.setText("mysql");
				jTextField3.setBounds(217, 118, 232, 27);
			}
			{ // 账号
				jTextField4 = new JTextField();
				this.add(jTextField4);
				jTextField4.setText("jn131201");
				jTextField4.setBounds(217, 158, 232, 27);
			}
			{ // 密码
				jTextField5 = new JTextField();
				this.add(jTextField5);
				jTextField5.setText("jn131201");
				jTextField5.setBounds(217, 198, 232, 27);
			}
			{
				jButton1 = new JButton();
				this.add(jButton1);
				jButton1.setText("\u6d4b\u8bd5\u8fde\u63a5");
				jButton1.setBounds(138, 276, 120, 30);
				jButton1.setIcon(Resources.getConnectIcon());
			}
			{
				jButton2 = new JButton();
				this.add(jButton2);
				jButton2.setText("\u4fdd\u5b58\u914d\u7f6e");
				jButton2.setBounds(329, 276, 120, 30);
				jButton2.setIcon(Resources.getSaveIcon());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
