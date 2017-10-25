package Biblioteca;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Database_console.DBConnect;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;

public class BorrowedBooks extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3024149206522669466L;
	private JPanel contentPane;

	String Username;
	/**
	 * Create the frame.
	 */
	public BorrowedBooks(String adminName, String userName) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, Constants.width, Constants.height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Username = userName;
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 153, 102));
		panel.setBounds(0, 0, Constants.width, Constants.height);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(userName+" borrowed this books:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize));
		lblNewLabel.setBounds(26, 28, 614, 33);
		panel.add(lblNewLabel);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize1));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new MenuScreen(adminName, StaticNames.admin);
			}
		});
		backButton.setBounds(50, 443, 239, 41);
		panel.add(backButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 65, 239, 368);
		panel.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(null);
		
		JList<?> list = new JList<Object>(displayBorrowedBooks( ));
		list.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize1));
		list.setBackground(new Color(255, 255, 204));
		list.setBounds(0, 0, 237, 366);
		panel_1.add(list);
		
		JLabel Image = new JLabel("");
		Image libraryImg = new ImageIcon(this.getClass().getResource("/libraryRegisterImage.png")).getImage();
		libraryImg = libraryImg.getScaledInstance(600, 416, 0);
		Image.setIcon( new ImageIcon(libraryImg) );
		Image.setBounds(342, 68, 600, 416);
		panel.add(Image);
		
		this.setVisible(true);
	}
	
    private String[] displayBorrowedBooks( ) {
    	
        List<String> books = DBConnect.takeBorrowedBooks(this.Username);
		
        String[] data = new String[books.size()+ 1];
        Iterator<String> it = books.iterator();
        int i = 0;
        while( it.hasNext() ) {
        	
        	data[i++] = it.next();
        }
		return data;
    }
}
