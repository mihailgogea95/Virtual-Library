package Database_console;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Models.Book;
import Models.User;

public class DBConnect {

	private static Connection con = null;
    
	public static void init() {
		
		try {
			//Class.forName("com.sqlserver.jdbc.Driver");
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:3306;databaseName=VirtualLibrary;integratedSecurity=true;");
            if( con != null ) {
            	
            	System.out.println("merge conexiunea");
            }
		} catch ( SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
		}
	}
	
	public static boolean loginAdmin(User user ) {
		
	    Statement stmt = null;
	    String query = "select * from " + "Admins " +
	    				"where " + "Username = '"+ user.Username +
	    				"' and Password = '" + user.Password + "'";
	    try {
	        stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        if( rs.next() ) {
	        	return true;
	        }

	    } catch (SQLException e ) {
	        System.out.println(e.getMessage());
	    } finally {
	        if (stmt != null) { try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} }
	    }
	    return false;
	}
	
	public static boolean loginUser(User user ) {
		
	    Statement stmt = null;
	    String query = "select * from " + "Users " +
	    				"where " + "Username = '"+ user.Username +
	    				"' and Password = '" + user.Password + "'";
	    try {
	        stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        if( rs.next() ) {
	        	return true;
	        }

	    } catch (SQLException e ) {
	        System.out.println(e.getMessage());
	    } finally {
	        if (stmt != null) { try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} }
	    }
	    return false;
	}
	
	public static boolean existsUser(User user ) {
		
	    Statement stmt = null;
	    String query = "select * from " + "Users " +
	    				"where " + "Username = '"+ user.Username + "'";
	    try {
	        stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        
	        if( rs.next() ) {
	        	return true;
	        }

	    } catch (SQLException e ) {
	        System.out.println(e.getMessage());
	    } finally {
	        if (stmt != null) { try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} }
	    }
	    return false;
	}
	
	public static boolean existsAdmin(User user ) {
		
	    Statement stmt = null;
	    String query = "select * from " + "Admins " +
	    				"where " + "Username = '"+ user.Username + "' ";
	    try {
	        stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
 
	        if( rs.next() ) {
		        //System.out.println(rs.getString(1));
	        	return true;
	        }

	    } catch (SQLException e ) {
	        System.out.println(e.getMessage());
	    } finally {
	        if (stmt != null) { try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} }
	    }
	    return false;
	}
	
	public static void addAdmin(User user ) {
		
	    Statement stmt = null;
	    String query = "insert into " + "Admins " +
	    				"(Username,Password) " +
	                   "values ('"+ user.Username + "','" + user.Password + "')";
	    try {
	        stmt = con.createStatement();
	        @SuppressWarnings("unused")
			ResultSet rs = stmt.executeQuery(query);
	        //System.out.println(rs.getString(0));

	    } catch (SQLException e ) {
	        System.out.println(e.getMessage());
	    } finally {
	        if (stmt != null) { try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} }
	    }
	}
	
	public static void addUser(User user ) {
		
	    Statement stmt = null;
	    String query = "insert into " + "Users " +
	    				"(Username,Password) " +
	                   "values ('"+ user.Username + "','" + user.Password + "')";
	    try {
	        stmt = con.createStatement();
	        @SuppressWarnings("unused")
			ResultSet rs = stmt.executeQuery(query);

	    } catch (SQLException e ) {
	        System.out.println(e.getMessage());
	    } finally {
	        if (stmt != null) { try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} }
	    }
	}
	
	public static void addBook(Book book) {
		
	    Statement stmt = null;
	    String query = "insert into " +"Books " +
	    				"(Title,Author,Description,Image) " +
	                   "values ('"+ book.Title + "','" + book.Author +
	                   "','" + book.Description + "','" + book.Image + "')";
	    try {
	        stmt = con.createStatement();
	        @SuppressWarnings("unused")
			ResultSet rs = stmt.executeQuery(query);

	    } catch (SQLException e ) {
	        System.out.println(e.getMessage());
	    } finally {
	        if (stmt != null) { try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} }
	    }
	}
	
	public static void removeBook(String title) {
		
	    Statement stmt = null;
	    String query = "delete from " +"Books " +
	    				"where Title = '" + title + "'";
	    try {
	        stmt = con.createStatement();
	        @SuppressWarnings("unused")
			ResultSet rs = stmt.executeQuery(query);

	    } catch (SQLException e ) {
	        System.out.println(e.getMessage());
	    } finally {
	        if (stmt != null) { try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} }
	    }
	}
	
	public static void borrowBook(String user ,String title, LocalDateTime localDateTime ) {
		
		LocalDateTime auxDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String borrowDate = auxDate.format(formatter);
		String returnDate = localDateTime.format(formatter);
		
	    Statement stmt = null;
	    String query = "insert into " + "Borrows " +
	    				"(Username,Title,BorrowDate,ReturnDate)" +
	                   " values ('"+ user + "','" + title +
	                   "','" + borrowDate + "','" + returnDate + "')";
	    try {
	        stmt = con.createStatement();
	        @SuppressWarnings("unused")
			ResultSet rs = stmt.executeQuery(query);
	        

	    } catch (SQLException e ) {
	        System.out.println(e.getMessage());
	    } finally {
	        if (stmt != null) { try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} }
	    }
	}
	
	public static void returnBook(String user, String title) {
		
	    Statement stmt = null;
	    String query = "delete from " +"Borrows " +
	    				"where Title = '" + title +
	    				"' and Username = '" + user + "'";
	    try {
	        stmt = con.createStatement();
	        @SuppressWarnings("unused")
			ResultSet rs = stmt.executeQuery(query);

	    } catch (SQLException e ) {
	        System.out.println(e.getMessage());
	    } finally {
	        if (stmt != null) { try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} }
	    }
	}
	
	public static List<String> takeAllUsers() {
		
		List<String> listOfUsers = new ArrayList<String>();
	    Statement stmt = null;
	    String query = "select Username " +
	                   "from " +"Users";
	    try {
	        stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	            listOfUsers.add(rs.getString("Username"));
	        }
	    } catch (SQLException e ) {
	        System.out.println(e.getMessage());
	    } finally {
	        if (stmt != null) { try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} }
	    }
		return listOfUsers;
	}
	
	public static List<String> takeAllBooks() {
		
		List<String> listOfBooks = new ArrayList<String>();
	    Statement stmt = null;
	    String query = "select Title " +
	                   "from " +"Books";
	    try {
	        stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	        	listOfBooks.add(rs.getString("Title"));
	        }
	    } catch (SQLException e ) {
	        System.out.println(e.getMessage());
	    } finally {
	        if (stmt != null) { try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} }
	    }
		return listOfBooks;
	}
	
	public static boolean existsBook(Book book ) {
		
	    Statement stmt = null;
	    String query = "select * from " + "Books " +
	    				"where " + "Title = '"+ book.Title + "'";
	    try {
	        stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        
	        if( rs.next() ) {
	        	return true;
	        }

	    } catch (SQLException e ) {
	        System.out.println(e.getMessage());
	    } finally {
	        if (stmt != null) { try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} }
	    }
	    return false;
	}
	
	public static List<String> takeBorrowedBooks(String username) {
		
		List<String> listOfBooks = new ArrayList<String>();
	    Statement stmt = null;
	    String query = "select Title " +
	                   "from " + "Borrows " +
	                   "where Username = '" + username + "'";
	    try {
	        stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	        	listOfBooks.add(rs.getString("Title"));
	        }
	    } catch (SQLException e ) {
	        System.out.println(e.getMessage());
	    } finally {
	        if (stmt != null) { try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} }
	    }
		return listOfBooks;
	}
	
	
	public static boolean existsBorrowedBook(String title , String username) {
		
	    Statement stmt = null;
	    String query = "select * from " + "Borrows " +
	    				"where " + "Title = '"+ title +
	    				"' and Username = '" + username + "'";
	    try {
	        stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        
	        if( rs.next() ) {
	        	return true;
	        }

	    } catch (SQLException e ) {
	        System.out.println(e.getMessage());
	    } finally {
	        if (stmt != null) { try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} }
	    }
	    return false;
	}
	public static Book takeBookInfo( String title) {
		
		Book book = null;
	    Statement stmt = null;
	    String query = "select * " +
	                   "from " + "Books " +
	                   "where Title = '" + title + "'";
	    try {
	        stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        
	       if( rs.next()) {
	    	   System.out.println(rs.getString("Image"));
	        	book = new Book(rs.getString("Title"), rs.getString("Author")
	        			, rs.getString("Description"), rs.getString("Image"));
	       }

	    } catch (SQLException e ) {
	        System.out.println(e.getMessage());
	    } finally {
	        if (stmt != null) { try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} }
	    }
		return book;
	}
}
