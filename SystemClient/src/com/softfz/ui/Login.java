package com.softfz.ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import com.softfz.RMIFactory;
import com.softfz.SystemContext;
import com.softfz.config.ServerConfig;
import com.softfz.io.ServerConfigFile;
import com.softfz.model.Oiltax;
import com.softfz.model.SystemUser;
import com.softfz.resources.Resources;
import com.softfz.service.INetService;
import com.softfz.service.ISystemService;
import com.softfz.ui.mainUI.BackManagerFrame;
import com.softfz.utils.CheckUtil;


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
public class Login extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7765392931992166806L;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel_Triangle;
	private JTextField jTextField_Port;
	private JTextField jTextField_Ip;
	private JTextField jTextField_Name;
	private JButton jButton_Save;
	private JButton jButton_Cancel;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JPasswordField jPasswordField;
	private JButton jButton_Login;
	private JLabel jLabel4;
	private JLabel jLabel3;
	private Boolean showRight = false;
	private WelcomeUI wUI;
	private BackManagerFrame backManager;
	private int errorNum;

	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Login inst = new Login();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public Login() {
		super();
		initGUI();
		listener();
		initData();
	}
	
	private void initData() {
		//读取server.properties配置文件中的数据显示到界面上
	}
	
	
	private void listener(){
		MyListener m = new MyListener();
		jLabel_Triangle.addMouseListener(m);
		jButton_Cancel.addMouseListener(m);
		jButton_Save.addMouseListener(m);
		jButton_Login.addMouseListener(m);
	}
	
	class MyListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			JLabel jLabelPress = null;
			JButton jButtonPress = null;
			if(e.getSource() instanceof JLabel){
				jLabelPress = (JLabel)e.getSource();
			}else if(e.getSource() instanceof JButton){
				jButtonPress = (JButton)e.getSource();
			}
			int mouseButton = e.getModifiers();
			
			if (mouseButton == InputEvent.BUTTON1_MASK){//按下鼠标左键判断
				if(jLabelPress == jLabel_Triangle){
					if(!showRight){
						jLabel_Triangle.setIcon(new ImageIcon("./picture/sanjiao2.png"));
						Login.this.setSize(600, 300);
						jTextField_Ip.setEnabled(true);
						jTextField_Port.setEnabled(true);
						showRight = true;
					}
					else{
						jLabel_Triangle.setIcon(new ImageIcon("./picture/sanjiao1.png"));
						Login.this.setSize(350, 300);
						jTextField_Ip.setEnabled(false);
						jTextField_Port.setEnabled(false);
						showRight = false;
					}
				}
				
				if(jButtonPress == jButton_Login){
					//按下登陆按钮
					
					//校验用户名密码
					String username=jTextField_Name.getText();
					String password= jPasswordField.getText();
					try {
						checkIsError(CheckUtil.checkSysUsername(username));
						checkIsError(CheckUtil.checkPwd(password));
					} catch (Exception e2) {
						// TODO 自动生成的 catch 块
						e2.printStackTrace();
						JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
								e2.getMessage(), "一个令人难过的通知", JOptionPane.INFORMATION_MESSAGE); 
						return;
					}
					//获取管理员对象
					SystemUser systemUser=null;
					ISystemService systemService=RMIFactory.getService();
					try {
						systemUser=systemService.login(username, password);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
								"可能是远程服务器未开启，请联系服务器管理员！", "一个令人难过的通知", JOptionPane.INFORMATION_MESSAGE); 
						e1.printStackTrace();
					}
					
					if (systemUser != null) {
						//保存当前登陆的系统管理端对象
						SystemContext.LOGIN_SYSTEMUSER = systemUser;					
								
						backManager = new BackManagerFrame();
						backManager.setLocationRelativeTo(null);
						//显示出加载界面
						wUI = new WelcomeUI();
						//wUI.show();//用JWindows时使用
						wUI.setVisible(true);
						TimerTask task = new TimerTask() {  
				            public void run() {  
				              wUI.dispose();
				              backManager.setVisible(true);
				            }  
				          };  
				        Timer timer = new Timer();  
				        timer.schedule(task, 3500); 
						
						Login.this.setVisible(false);
					}else {
						JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
								"密码错误", "一个令人难过的通知", JOptionPane.INFORMATION_MESSAGE); 
					}
				}else if(jButtonPress == jButton_Save){
					//按下保存按钮
					String RMI_IP=jTextField_Ip.getText();
					String RMI_PORT=jTextField_Port.getText();	
					try {
						checkIsError(CheckUtil.checkIp(RMI_IP));
						checkIsError(CheckUtil.checkPort(RMI_PORT));
						ServerConfig.RMI_IP=RMI_IP;
						ServerConfig.RMI_PORT=RMI_PORT;
						ServerConfigFile.saveServerConfig();
						JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
								"RMI配置保存成功啦！", "一个很温馨的通知", JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
						JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
								e1.getMessage(), "一个令人难过的通知", JOptionPane.INFORMATION_MESSAGE); 
					}
				}else if(jButtonPress == jButton_Cancel){
					//按下取消按钮
					int exitFlag = JOptionPane.showConfirmDialog(Login.this, "确认退出 ？", "退出确认", JOptionPane.YES_NO_OPTION);
					if(exitFlag == 0){
						System.exit(0);
					}
				}
			
			}
			
		}
		
	}
	
	/**
	 * 从核查类中取得核查的错误信息
	 * @param errorNum
	 * @throws Exception
	 */
	private void checkIsError(int errorNum) throws Exception{
		if(errorNum != CheckUtil.OK){
			String errorInfo = CheckUtil.ERRMsgMap.get(errorNum);
			throw new Exception(errorInfo);
		}
	}
	
	private void initGUI() {
		try {
			//读取配置文件信息到系统
			ServerConfigFile.readServerConfig();
			
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setTitle("\u540e\u53f0\u7ba1\u7406\u767b\u9646");
			this.setResizable(false);//禁止窗口拉伸
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("\u8d26\u53f7\uff1a");
				jLabel1.setBounds(62, 65, 60, 30);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("\u5bc6\u7801\uff1a");
				jLabel2.setBounds(62, 126, 60, 30);
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setText("\u670d\u52a1\u5668\u5730\u5740\uff1a");
				jLabel3.setBounds(368, 65, 72, 30);
				jLabel3.setSize(85, 30);
			}
			{
				jLabel4 = new JLabel();
				getContentPane().add(jLabel4);
				jLabel4.setText("\u670d\u52a1\u5668\u7aef\u53e3\uff1a");
				jLabel4.setBounds(368, 126, 72, 30);
				jLabel4.setSize(85, 30);
			}
			{
				jButton_Login = new JButton();
				getContentPane().add(jButton_Login);
				jButton_Login.setText("\u767b\u9646");
				jButton_Login.setBounds(51, 186, 86, 30);
				jButton_Login.setIcon(Resources.getKuser());
			}
			{
				jButton_Cancel = new JButton();
				getContentPane().add(jButton_Cancel);
				jButton_Cancel.setText("\u53d6\u6d88");
				jButton_Cancel.setBounds(207, 186, 86, 30);
				jButton_Cancel.setIcon(Resources.getStopIcon());
			}
			{
				jButton_Save = new JButton();
				getContentPane().add(jButton_Save);
				jButton_Save.setText("\u4fdd\u5b58");
				jButton_Save.setBounds(405, 186, 86, 30);
				jButton_Save.setIcon(Resources.getSaveIcon());
			}
			{
				jTextField_Name = new JTextField();
				getContentPane().add(jTextField_Name);
				jTextField_Name.setBounds(116, 65, 150, 30);
			}
			{
				jTextField_Ip = new JTextField();
				getContentPane().add(jTextField_Ip);
				jTextField_Ip.setText(ServerConfig.RMI_IP);
				jTextField_Ip.setBounds(453, 65, 80, 30);
				jTextField_Ip.setEnabled(false);
			}
			{
				jTextField_Port = new JTextField();
				getContentPane().add(jTextField_Port);
				jTextField_Port.setText(ServerConfig.RMI_PORT);
				jTextField_Port.setBounds(453, 126, 80, 30);
				jTextField_Port.setEnabled(false);				
			}
			{
				jLabel_Triangle = new JLabel();
				getContentPane().add(jLabel_Triangle);
				jLabel_Triangle.setBounds(260, 233, 107, 39);
				jLabel_Triangle.setIcon(new ImageIcon("./picture/sanjiao1.png"));
				jLabel_Triangle.setText("\u9ad8\u7ea7\u9009\u9879");
				jLabel_Triangle.setFont(new java.awt.Font("Microsoft YaHei",0,11));
			}
			{
				jPasswordField = new JPasswordField();
				getContentPane().add(jPasswordField);
				jPasswordField.setBounds(117, 127, 150, 30);
			}
			{
				jLabel6 = new JLabel();
				getContentPane().add(jLabel6);
				jLabel6.setText("--\u540e\u53f0\u7ba1\u7406");
				jLabel6.setBounds(181, 43, 95, 17);
			}
			{
				jLabel5 = new JLabel();
				getContentPane().add(jLabel5);
				jLabel5.setText("\u822a\u7a7a\u552e\u7968\u7cfb\u7edf");
				jLabel5.setFont(new java.awt.Font("Microsoft YaHei",1,18));
				jLabel5.setBounds(115, 7, 114, 47);
			}
			pack();
			this.setSize(350, 300);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
