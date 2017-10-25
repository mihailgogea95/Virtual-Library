package Biblioteca;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import Database_console.DBConnect;
import Models.User;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;

public class RegisterScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8012761883914799667L;
	private JPanel contentPane;
	private JTextField username;
	private JTextField password;
	private String File = "";

	/**
	 * Create the frame.
	 * @param userFile 
	 */
	public RegisterScreen(String file, String typeOfUser) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, Constants.width, Constants.height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		File = file;
		Writer writer = null;
		File check = new File(File);
		if (check.exists()) {

			// Checks if the file exists. will not add anything if the file does
			// exist.
		} else {
			try {
				File texting = new File(File);
				writer = new BufferedWriter(new FileWriter(texting));
				writer.write("message");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 153, 102));
		panel.setBounds(0, 0, Constants.width, Constants.height);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel Image = new JLabel("");
		Image.setBounds(453, 28, 509, 512);
		panel.add(Image);
		
		Image libraryImg = new ImageIcon(this.getClass().getResource("/libraryRegisterImage.png")).getImage();
		libraryImg = libraryImg.getScaledInstance(968, 512, 0);
		Image.setIcon( new ImageIcon(libraryImg) );
		
		JLabel lblInsertUsername = new JLabel("Insert Username:");
		lblInsertUsername.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize));
		lblInsertUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblInsertUsername.setBounds(114, 88, 236, 33);
		panel.add(lblInsertUsername);
		
		JLabel lblInsertPassword = new JLabel("Insert Password:");
		lblInsertPassword.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize));
		lblInsertPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblInsertPassword.setBounds(114, 186, 236, 33);
		panel.add(lblInsertPassword);
		
		username = new JTextField();
		username.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize1));
		username.setHorizontalAlignment(SwingConstants.CENTER);
		username.setBounds(114, 119, 236, 39);
		panel.add(username);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize1));
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setBounds(114, 217, 236, 39);
		panel.add(password);
		password.setColumns(10);
		
		JButton createUserButton = new JButton("Create User");
		createUserButton.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize1));
		createUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				User user = new User(username.getText(), password.getText());
				if (user.Username.equals("") || user.Password.equals("")) {
					JOptionPane.showMessageDialog(null, "Please insert Username and Password");
				}
				else if( typeOfUser == StaticNames.admin ) {
					
					if( DBConnect.existsAdmin(user) == false ) {
						DBConnect.addAdmin(user);
						JOptionPane.showMessageDialog(null, "Account has been created.");
						dispose();
						(new LoginScreen()).init();
					} else {
						
						JOptionPane.showMessageDialog(null, "Username is already in use");
						username.setText("");
						password.setText("");
						username.requestFocus();
					}
				} else {
					
					if( DBConnect.existsUser(user) == false ) {
						DBConnect.addUser(user);
						JOptionPane.showMessageDialog(null, "Account has been created.");
						dispose();
						(new LoginScreen()).init();
					} else {
						
						JOptionPane.showMessageDialog(null, "Username is already in use");
						username.setText("");
						password.setText("");
						username.requestFocus();
					}
				}
						
			}
		});
		createUserButton.setBounds(114, 284, 236, 41);
		panel.add(createUserButton);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize1));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				(new LoginScreen()).init();
			}
		});
		backButton.setBounds(114, 443, 236, 41);
		panel.add(backButton);
		
		JLabel lblNewLabel = new JLabel("New "+ typeOfUser);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(114, 28, 236, 33);
		panel.add(lblNewLabel);
		
		this.setVisible(true);
	}
}
