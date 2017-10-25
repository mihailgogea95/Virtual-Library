package Biblioteca;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Database_console.DBConnect;

public class MenuScreenUser extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4207765307198501550L;
	private JPanel contentPane;

	String Username;
	/**
	 * Create the frame.
	 */
	public MenuScreenUser(String userName, String typeOfUser) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, Constants.width, Constants.height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Username = userName;
		
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
		
		JLabel lblListOfBooks = new JLabel("List of books:");
		lblListOfBooks.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize));
		lblListOfBooks.setHorizontalAlignment(SwingConstants.CENTER);
		lblListOfBooks.setBounds(408, 28, 280, 33);
		panel.add(lblListOfBooks);
		
		JScrollPane bookScrollPane = new JScrollPane();
		bookScrollPane.setBounds(418, 89, 240, 395);
		panel.add(bookScrollPane);
		
		JPanel panel_1 = new JPanel();
		bookScrollPane.setViewportView(panel_1);
		panel_1.setLayout(null);
		
		JList<?> bookList = new JList<Object>(displayBooks());
		bookList.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize1));
		bookList.setBackground(new Color(255, 255, 204));
		bookList.setBounds(0, 0, 238, 393);
		panel_1.add(bookList);
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
		
		JButton refreshButton = new JButton("Refresh");
		refreshButton.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize1));
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MenuScreenUser(userName, typeOfUser);
			}
		});
		refreshButton.setBounds(79, 374, 232, 41);
		panel.add(refreshButton);
		
		JButton borrowBookButton = new JButton("Borrow");
		borrowBookButton.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize1));
		borrowBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if( (String)bookList.getSelectedValue() != null && DBConnect.existsBorrowedBook((String)bookList.getSelectedValue(), Username) == false) {
					
					DBConnect.borrowBook(Username, (String)bookList.getSelectedValue(), LocalDateTime.now().plusDays(7));
					refreshButton.doClick();
				}
			}
		});
		borrowBookButton.setBounds(79, 130, 232, 41);
		panel.add(borrowBookButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(699, 89, 233, 395);
		panel.add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		scrollPane.setViewportView(panel_2);
		panel_2.setLayout(null);
		
		JList<?> borrowedBookList = new JList<Object>(displayBorrowedBooks());
		borrowedBookList.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize1));
		borrowedBookList.setBackground(new Color(255, 255, 204));
		borrowedBookList.setBounds(0, 0, 231, 393);
		panel_2.add(borrowedBookList);
		
		JButton returnBookButton = new JButton("Return book");
		returnBookButton.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize1));
		returnBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if( (String)borrowedBookList.getSelectedValue() != null ) {
					
					DBConnect.returnBook(Username, (String)borrowedBookList.getSelectedValue());
					refreshButton.doClick();
				}
			}
		});
		returnBookButton.setBounds(79, 265, 232, 41);
		panel.add(returnBookButton);
		
		JLabel lblSelectABook = new JLabel("Select a book to:");
		lblSelectABook.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize));
		lblSelectABook.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectABook.setBounds(79, 94, 232, 33);
		panel.add(lblSelectABook);
		
		JLabel lblNewLabel = new JLabel("Select a borrowed book to:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(26, 199, 366, 63);
		panel.add(lblNewLabel);
		
		JLabel lblBorrowedBooks = new JLabel("Borrowed books:");
		lblBorrowedBooks.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize));
		lblBorrowedBooks.setHorizontalAlignment(SwingConstants.CENTER);
		lblBorrowedBooks.setBounds(689, 28, 243, 33);
		panel.add(lblBorrowedBooks);
		
		this.setVisible(true);
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
