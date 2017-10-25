package Biblioteca;

import java.awt.EventQueue;

import Database_console.DBConnect;

public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		DBConnect.init();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen window = new LoginScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
