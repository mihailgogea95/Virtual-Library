package Biblioteca;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Database_console.DBConnect;
import Models.User;

import java.awt.Font;

public class LoginScreen {

	JFrame frame;
	private JTextField username;
	private JTextField password;

	/**
	 * Create the application.
	 */
	public LoginScreen() {
		initialize();
	}
	
	public void init() {
		
		try {
			LoginScreen window = new LoginScreen();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, Constants.width, Constants.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 153, 102));
		panel.setForeground(Color.BLACK);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		JButton loginUserButton = new JButton("Login user");
		loginUserButton.setForeground(Color.BLACK);
		loginUserButton.setBounds(230, 361, 253, 41);
		loginUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				User user = new User(username.getText(), password.getText());
				if (user.Username.equals("") || user.Password.equals("")) {
					
					JOptionPane.showMessageDialog(null, "Please insert Username and Password");
				}
				else if( DBConnect.loginUser( user ) == true ) {
					
					new MenuScreenUser(user.Username , StaticNames.user);
					frame.dispose();
				} else {
					
					JOptionPane.showMessageDialog(null, "Wrong Username / Password");
					username.setText("");
					password.setText("");
					username.requestFocus();
				}
			}
		});
		loginUserButton.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize1));
		panel.setLayout(null);
		panel.add(loginUserButton);
		
		JButton registerUserButton = new JButton("Register user");
		registerUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RegisterScreen(StaticNames.userFile, StaticNames.user);
				frame.dispose();
			}
		});
		registerUserButton.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize1));
		registerUserButton.setBounds(509, 361, 239, 41);
		panel.add(registerUserButton);
		
		JButton loginAdminButton = new JButton("Login admin");
		loginAdminButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*try {
					File file = new File(StaticNames.adminFile);
					Scanner scan = new Scanner(file);
					;

					String usertxt = " ";
					String passtxt = " ";
					String puname = username.getText();
					String ppaswd = password.getText();

					if (puname.equals("") && ppaswd.equals("")) {
						JOptionPane.showMessageDialog(null, "Please insert Username and Password");
						scan.close();
						return;
					} else
						while (scan.hasNext()) {

							usertxt = scan.nextLine();
							passtxt = scan.nextLine();

							if (puname.equals(usertxt) && ppaswd.equals(passtxt)) {
								new MenuScreen(usertxt , StaticNames.admin);
								frame.dispose();
								scan.close();
								return;
							}
						}
					scan.close();

					JOptionPane.showMessageDialog(null, "Wrong Username / Password");
					username.setText("");
					password.setText("");
					username.requestFocus();

				} catch (IOException d) {
					d.printStackTrace();
				}*/
				User user = new User(username.getText(), password.getText());
				if (user.Username.equals("") || user.Password.equals("")) {
					
					JOptionPane.showMessageDialog(null, "Please insert Username and Password");
				}
				else if( DBConnect.loginAdmin( user ) == true ) {
					
					new MenuScreen(user.Username , StaticNames.admin);
					frame.dispose();
				} else {
					
					JOptionPane.showMessageDialog(null, "Wrong Username / Password");
					username.setText("");
					password.setText("");
					username.requestFocus();
				}
			}
		});
		loginAdminButton.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize1));
		loginAdminButton.setBounds(230, 430, 253, 41);
		panel.add(loginAdminButton);
		
		JButton registerAdminButton = new JButton("Register admin");
		registerAdminButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RegisterScreen(StaticNames.adminFile,  StaticNames.admin);
				frame.dispose();
			}
		});
		registerAdminButton.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize1));
		registerAdminButton.setBounds(509, 430, 239, 41);
		panel.add(registerAdminButton);
		
		username = new JTextField();
		username.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize1));
		username.setHorizontalAlignment(SwingConstants.CENTER);
		username.setBounds(509, 227, 239, 39);
		panel.add(username);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize1));
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setBounds(509, 294, 239, 39);
		panel.add(password);
		password.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize));
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(230, 230, 253, 33);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize));
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(230, 294, 253, 39);
		panel.add(lblPassword);
		
		JLabel Image = new JLabel("");
		Image libraryImg = new ImageIcon(this.getClass().getResource("/LibraryImage.jpg")).getImage();
		libraryImg = libraryImg.getScaledInstance(916, 186, 0);
		Image.setIcon( new ImageIcon(libraryImg) );
		Image.setBounds(26, 28, 916, 171);
		panel.add(Image);
	}
}
