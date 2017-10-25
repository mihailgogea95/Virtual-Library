package Biblioteca;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Database_console.DBConnect;

import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class MenuScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4487454168934992087L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 * @param user 
	 * @param usertxt 
	 */
	public MenuScreen(String userName, String typeOfUser) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, Constants.width, Constants.height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 153, 102));
		panel.setBounds(0, 0, Constants.width, Constants.height);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblUser = new JLabel(typeOfUser+":");
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize));
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setBounds(79, 28, 115, 33);
		panel.add(lblUser);
		
		JLabel usernameLabel = new JLabel(userName);
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize));
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setBounds(182, 28, 200, 33);
		panel.add(usernameLabel);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize1));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new LoginScreen()).init();;
				dispose();
			}
		});
		btnLogout.setBounds(79, 443, 232, 41);
		panel.add(btnLogout);
		
		//if( typeOfUser == StaticNames.admin ) {

			JButton btnNewButton = new JButton("Add books");
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize1));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					new AddBookScreen(userName , typeOfUser);
				}
			});
			btnNewButton.setBounds(79, 89, 232, 41);
			panel.add(btnNewButton);
		
			JLabel lblListOfUsers = new JLabel("List of users:");
			lblListOfUsers.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize));
			lblListOfUsers.setHorizontalAlignment(SwingConstants.CENTER);
			lblListOfUsers.setBounds(716, 28, 226, 33);
			panel.add(lblListOfUsers);
			
			JScrollPane userScrollPane = new JScrollPane();
			userScrollPane.setBounds(726, 89, 216, 395);
			panel.add(userScrollPane);
			
			JPanel panel_2 = new JPanel();
			userScrollPane.setViewportView(panel_2);
			panel_2.setLayout(null);
			
			JList<?> userList = new JList<Object>(displayUsers());
			userList.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize1));
			userList.setBackground(new Color(255, 255, 204));
			userList.setBounds(0, 0, 214, 393);
			panel_2.add(userList);
		//}
		
		JLabel lblListOfBooks = new JLabel("List of books:");
		lblListOfBooks.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize));
		lblListOfBooks.setHorizontalAlignment(SwingConstants.CENTER);
		lblListOfBooks.setBounds(408, 28, 280, 33);
		panel.add(lblListOfBooks);
		
		JScrollPane bookScrollPane = new JScrollPane();
		bookScrollPane.setBounds(418, 89, 270, 395);
		panel.add(bookScrollPane);
		
		JPanel panel_1 = new JPanel();
		bookScrollPane.setViewportView(panel_1);
		panel_1.setLayout(null);
		
		JList<?> bookList = new JList<Object>(displayBooks());
		bookList.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize1));
		bookList.setBackground(new Color(255, 255, 204));
		bookList.setBounds(0, 0, 268, 393);
		bookList.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {

		        @SuppressWarnings("unchecked")
				JList<String> list = (JList<String>)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            // Double-click detected
					if( (String)list.getSelectedValue() != null ) {
						
						new BookInfoSreen((String)list.getSelectedValue());
					}		            
		        }
		    }
		});
		panel_1.add(bookList);
		
		JButton userStatusButton = new JButton("See user status");
		userStatusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if( (String)userList.getSelectedValue() != null ) {
					dispose();
					new BorrowedBooks(userName, (String)userList.getSelectedValue());
				}	
			}
		});
		userStatusButton.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize1));
		userStatusButton.setBounds(79, 307, 232, 41);
		panel.add(userStatusButton);
		
		JLabel lblNewLabel = new JLabel("Select a book to:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(79, 158, 232, 33);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Select a user to:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(79, 270, 232, 33);
		panel.add(lblNewLabel_1);
		
		JButton refreshButton = new JButton("Refresh");
		refreshButton.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize1));
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MenuScreen(userName, typeOfUser);
			}
		});
		refreshButton.setBounds(77, 376, 234, 41);
		panel.add(refreshButton);
		
		JButton removeBookButton = new JButton("Remove book");
		removeBookButton.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize1));
		removeBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if( (String)bookList.getSelectedValue() != null ) {
					
					DBConnect.removeBook((String)bookList.getSelectedValue());
					refreshButton.doClick();
				}
			}
		});
		removeBookButton.setBounds(79, 197, 232, 41);
		panel.add(removeBookButton);
		
		this.setVisible(true);
	}
	
	private String[] displayUsers( ) {

        List<String> books = DBConnect.takeAllUsers();
		
        String[] data = new String[books.size()+ 1];
        Iterator<String> it = books.iterator();
        int i = 0;
        while( it.hasNext() ) {
        	
        	data[i++] = it.next();
        }
		return data;
    }
    
    private String[] displayBooks( ) {
    	
        List<String> books = DBConnect.takeAllBooks();
		
        String[] data = new String[books.size()+ 1];
        Iterator<String> it = books.iterator();
        int i = 0;
        while( it.hasNext() ) {
        	
        	data[i++] = it.next();
        }
		return data;
    }
}
