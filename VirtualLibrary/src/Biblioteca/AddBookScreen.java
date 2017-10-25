package Biblioteca;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import Database_console.DBConnect;
import Models.Book;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class AddBookScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1019911795918356611L;
	private JPanel contentPane;
	private JTextField authorName;
	private JTextField bookName;
	private JTextField ImageNameField;
	private JTextArea descriptionField;

	/**
	 * Create the frame.
	 * @param typeOfUser 
	 * @param userName 
	 */
	public AddBookScreen(String userName, String typeOfUser) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, Constants.width, Constants.height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, Constants.width, Constants.height);
		panel.setBackground(new Color(204, 153, 102));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblAuthorName = new JLabel("Author Name:");
		lblAuthorName.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize));
		lblAuthorName.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuthorName.setBounds(26, 28, 207, 33);
		panel.add(lblAuthorName);
		
		JLabel lblBookName = new JLabel("Book Name:");
		lblBookName.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize));
		lblBookName.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookName.setBounds(26, 89, 207, 33);
		panel.add(lblBookName);
		
		authorName = new JTextField();
		authorName.setHorizontalAlignment(SwingConstants.CENTER);
		authorName.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize1));
		authorName.setBounds(229, 25, 236, 39);
		panel.add(authorName);
		authorName.setColumns(10);
		
		bookName = new JTextField();
		bookName.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize1));
		bookName.setHorizontalAlignment(SwingConstants.CENTER);
		bookName.setBounds(229, 86, 236, 39);
		panel.add(bookName);
		bookName.setColumns(10);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				if( typeOfUser == StaticNames.admin)
					new MenuScreen(userName, typeOfUser);
				else
					new MenuScreenUser(userName, typeOfUser);
			}
		});
		backButton.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize1));
		backButton.setBounds(229, 443, 171, 41);
		panel.add(backButton);
		
		JButton addBookButton = new JButton("Add Book");
		addBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Book book = new Book(AddBookScreen.this.bookName.getText(),authorName.getText(),descriptionField.getText(),ImageNameField.getText());
				if (book.Title.equals("") || book.Author.equals("") || book.Description.equals("") || book.Image.equals("") ) {
					JOptionPane.showMessageDialog(null, "You have empty fields!");
				} else if( DBConnect.existsBook( book ) == false ) {
					
					DBConnect.addBook(book);
					JOptionPane.showMessageDialog(null, "Book has been added.");
				} else {
					
					JOptionPane.showMessageDialog(null, "Book already exists!");
					AddBookScreen.this.bookName.setText("");
					authorName.setText("");
					descriptionField.setText("");
					ImageNameField.setText("");
					AddBookScreen.this.bookName.requestFocus();
				}
			}
		});
		addBookButton.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize1));
		addBookButton.setBounds(229, 374, 171, 41);
		panel.add(addBookButton);
		
		JLabel Image = new JLabel("");
		Image libraryImg = new ImageIcon(this.getClass().getResource("/AddBookImage.png")).getImage();
		libraryImg = libraryImg.getScaledInstance(447, 512, 0);
		Image.setIcon( new ImageIcon(libraryImg) );
		Image.setBounds(516, 28, 447, 512);
		panel.add(Image);
		
		JLabel lblImageName = new JLabel("Image Name:");
		lblImageName.setHorizontalAlignment(SwingConstants.CENTER);
		lblImageName.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblImageName.setBounds(26, 158, 207, 33);
		panel.add(lblImageName);
		
		ImageNameField = new JTextField();
		ImageNameField.setHorizontalAlignment(SwingConstants.CENTER);
		ImageNameField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		ImageNameField.setColumns(10);
		ImageNameField.setBounds(229, 155, 236, 39);
		panel.add(ImageNameField);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblDescription.setBounds(26, 219, 207, 33);
		panel.add(lblDescription);
		
		descriptionField = new JTextArea();
		//descriptionField.setHorizontalAlignment(SwingConstants.CENTER);
		descriptionField.setLineWrap(true);
		descriptionField.setWrapStyleWord(true);
		descriptionField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		descriptionField.setColumns(10);
		descriptionField.setBounds(36, 256, 429, 106);
		panel.add(descriptionField);
		
		this.setVisible(true);
	}
}
