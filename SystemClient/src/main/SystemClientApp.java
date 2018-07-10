package main;

import javax.swing.SwingUtilities;

import com.softfz.ui.Login;

public class SystemClientApp {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Login inst = new Login();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
}
