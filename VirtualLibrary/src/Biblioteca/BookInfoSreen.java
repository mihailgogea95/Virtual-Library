package Biblioteca;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Database_console.DBConnect;
import Models.Book;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class BookInfoSreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3665594328887883762L;
	private JPanel contentPane;

	String Title;
	/**
	 * Create the frame.
	 */
	public BookInfoSreen( String title) {

		setBounds(150, 150, Constants.width - 50, Constants.height - 50);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204,153,102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Title = title;
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204,153,102));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel titleBookLabel = new JLabel("Book Name: ");
		titleBookLabel.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize-5));
		titleBookLabel.setBounds(26, 28, 406, 33);
		panel.add(titleBookLabel);
		
		JLabel authorLabel = new JLabel("Author name: ");
		authorLabel.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize-5));
		authorLabel.setBounds(26, 89, 406, 33);
		panel.add(authorLabel);
		
		JLabel descriptionLabel = new JLabel("<html>Description:</html>");
		descriptionLabel.setVerticalAlignment(SwingConstants.TOP);
		descriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, Constants.fontSize - 10));
		descriptionLabel.setBounds(26, 150, 406, 274);
		panel.add(descriptionLabel);
		
		JLabel ImageBook = new JLabel("");
		ImageBook.setBounds(458, 28, 424, 396);
		panel.add(ImageBook);
		
		displayBookInfo(titleBookLabel,authorLabel,descriptionLabel,ImageBook);
		
		this.setVisible(true);
	}
	
	private void displayBookInfo(JLabel titleBookLabel, JLabel authorLabel, JLabel descriptionLabel, JLabel imageBook) {
		// TODO Auto-generated method stub
		Book book = DBConnect.takeBookInfo(Title);
		
        final String dir = System.getProperty("user.dir") + "\\Img\\";
        System.out.println("current dir = " + dir);
		
		titleBookLabel.setText("Book name: " + book.Title );
		authorLabel.setText( "Author name: " + book.Author );
		descriptionLabel.setText( "<html>Description: " + book.Description + "</html>" );
		
		Image libraryImg = new ImageIcon(this.getClass().getResource("/" + book.Image )).getImage();
		libraryImg = libraryImg.getScaledInstance(imageBook.getWidth(),imageBook.getHeight(),0);
		imageBook.setIcon( new ImageIcon(libraryImg) );
	}
}
