package main;

import javax.swing.SwingUtilities;

import com.softfz.ui.ServerJFrame;

public class ServerApp {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ServerJFrame inst = new ServerJFrame();
				inst.setLocationRelativeTo(null);  //將窗口置于屏幕的中央
				inst.setVisible(true);
			}
		});
	}
}
