
package edu.uga.cs4300.persistlayer;

import edu.uga.cs4300.objects.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import java.util.ArrayList;
//import java.util.List;

public class DatabaseUsers {

	static final String DRIVE_NAME = "com.mysql.jdbc.Driver";

	static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/bakery"
			+ "?autoReconnect=true&useSSL=false";

	static final String DB_CONNECTION_USERNAME = "root";

	static final String DB_CONNECTION_PASSWORD = "root";

	/**
	 * 
	 * @param username the username to check in the database
	 * @param password the given password to check in the database
	 * @return true if the given username has the given password on file, false
	 *         otherwise
	 */
	public static boolean validateLogin(String username, String password) {

		String query = "SELECT * FROM users" + " WHERE username = '" + username + "';";

		Connection con = connect();
		ResultSet rs = retrieve(con, query);
		String databasePassword = null;

		try {
			while (rs.next()) {
				databasePassword = rs.getString("password");
			}

		} catch (SQLException e) {
			System.out.println("Error retrieving password");
			return false;
		} finally {
			closeConnection(con);
		}

		return databasePassword.equals(password);
	}

	/**
	 * 
	 * @param user the User to be added to the database
	 * @return true if successfully added, false otherwise
	 */
	public static boolean addUser(User user) {

		Connection con = connect();

		String query = "INSERT INTO users (first_name, last_name, username, password, email)" + "VALUES (?,?,?,?,?);";

		try {
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, user.getFirstName());
			preparedStmt.setString(2, user.getLastName());
			preparedStmt.setString(3, user.getUsername());
			preparedStmt.setString(4, user.getPassword());
			preparedStmt.setString(5, user.getEmail());

			preparedStmt.execute();

		} catch (SQLException e) {
			System.out.println("Erorr adding user");
			return false;

		} finally {
			closeConnection(con);
		}
		return true;
	}

	public static boolean deleteUser(int userId) {

		Connection con = connect();
		String query = "DELETE FROM users WHERE id = " + userId + ";";

		try {
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.execute();

		} catch (SQLException e) {

			System.out.println("Erorr deleting user");
			return false;

		} finally {

			closeConnection(con);
		}

		return true;

	}
	
	public static User getUser(String username){
		
		String query = "SELECT * FROM users" + 
		" WHERE username = '" + username + "';";
		
		Connection con = connect();
		ResultSet rs = retrieve(con, query);

		//User u = null;
		
		int id = -1;
		String first_name = null, last_name = null, password = null, email = null;
		
		try {
			while (rs.next()) {
				id = rs.getInt("id");
				first_name = rs.getString("first_name");
				last_name = rs.getString("last_name");
				password = rs.getString("password");
				email = rs.getString("email");
			}

		} catch (SQLException e) {
			System.out.println("Error retrieving user");
			return null;

		} finally {
			closeConnection(con);
		}
		
		return new User(first_name, last_name, username, 
			password, email, id);
		
	
	}

	/*
	 * ########################################################
	 * 
	 * Converted DatabaseAccess to 2 different classes for 2 different databases,
	 * allowing servlets to contain less SQL code (ideally contain no SQL code)
	 * Below here is the newly created private methods
	 * 
	 * ##########################################################
	 */

	private static Connection connect() {
		Connection con = null;
		try {
			Class.forName(DRIVE_NAME);
			con = DriverManager.getConnection(CONNECTION_URL, DB_CONNECTION_USERNAME, DB_CONNECTION_PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	} // end of connect

	private static ResultSet retrieve(Connection con, String query) {
		ResultSet rset = null;
		try {
			Statement stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			return rset;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rset;
	}// end of retrieve

	private static void closeConnection(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // end of closeConnection

}
