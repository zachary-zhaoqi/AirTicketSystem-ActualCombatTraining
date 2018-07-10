package com.softfz.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import com.softfz.log.ServerContext;
import com.softfz.resources.Resources;
import com.softfz.server.ServerOperatorImpl;

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
public class MainWinJPanel extends javax.swing.JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9020445890224599324L;
	private JScrollPane jScrollPane1;
	private JButton jButton_Exit;
	private JButton jButton_Stop;
	private JButton jButton_Start;
	private JTextArea jTextArea1;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new MainWinJPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public MainWinJPanel() {
		super();
		initGUI();
		ServerContext.MAINPANEL = MainWinJPanel.this;//把界面对象传给服务实现类，方便客户端对本窗口进行操作
		listener();  //定义并注册三个按钮的监听器
	}
	
	private void listener(){
		ButtonListener m = new ButtonListener(); //实例化监听器对象
		//在三个按钮上（事件源）注册对按钮的点击事件的监听！！
		jButton_Start.addActionListener(m); 
		jButton_Stop.addActionListener(m);
		jButton_Exit.addActionListener(m);
	}
	
	/**
	 * 自定义监听类（内部类）
	 * @author Administrator
	 *
	 */
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand(); //e.getSource() --按钮对象
			if(cmd.equals("启动")){
				//启动服务,若成功改变按钮属性
				if(ServerOperatorImpl.getInstance().start()) { 
					jTextArea1.append("RMI服务已启动！\n");
					jButton_Start.setEnabled(false);
					jButton_Stop.setEnabled(true);
					jButton_Exit.setEnabled(false);
				}else {
					jTextArea1.append("RMI服务启动失败！\n");
				}
				
			}else if(cmd.equals("停止")){
				//停止服务，若成功改变按钮属性
				if(ServerOperatorImpl.getInstance().stop()) {
					jTextArea1.append("RMI服务已停止！\n");
					jButton_Start.setEnabled(true);
					jButton_Stop.setEnabled(false);
					jButton_Exit.setEnabled(true);
				}else {
					jTextArea1.append("RMI服务停止失败！\n");
				}
				
			}else if(cmd.equals("退出")){
				int exitFlag = JOptionPane.showConfirmDialog(MainWinJPanel.this, "确认退出服务器 ？", "退出确认", JOptionPane.YES_NO_OPTION);
				if(exitFlag == 0){
					System.exit(0);
				}
			}	
		}
	}
	
	/**
	 * 把接收来的信息和当前时间组装后，向服务端的信息窗口上显示
	 * @param message
	 */
	public void createMessage(String message){

	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(580, 336));
			this.setSize(580, 336);
			this.setLayout(null);
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setBounds(0, 0, 325, 250);
				jScrollPane1.setSize(400, 333);
				{
					jTextArea1 = new JTextArea();
					jScrollPane1.setViewportView(jTextArea1);
					jTextArea1.setText("欢迎使用航空票务系统服务端\n");
				}
			}
			{
				jButton_Start = new JButton();
				this.add(jButton_Start);
				jButton_Start.setText("\u542f\u52a8");
				jButton_Start.setBounds(445, 45, 86, 39);
				jButton_Start.setSize(86, 40);
				jButton_Start.setIcon(Resources.getStartIcon());
			}
			{
				jButton_Stop = new JButton();
				this.add(jButton_Stop);
				jButton_Stop.setText("\u505c\u6b62");
				jButton_Stop.setBounds(445, 125, 86, 40);
				jButton_Stop.setIcon(Resources.getStopIcon());
				jButton_Stop.setEnabled(false);
			}
			{
				jButton_Exit = new JButton();
				this.add(jButton_Exit);
				jButton_Exit.setText("\u9000\u51fa");
				jButton_Exit.setBounds(445, 204, 86, 40);
				jButton_Exit.setIcon(Resources.getExitIcon());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
