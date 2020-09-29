package edu.uga.cs4300.persistlayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.uga.cs4300.objects.Cake;
import edu.uga.cs4300.objects.Cookie;
import edu.uga.cs4300.objects.Product;

public class DatabaseProducts {

	static final String DRIVE_NAME = "com.mysql.jdbc.Driver";

	static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/bakery"
			+ "?autoReconnect=true&useSSL=false";
	
	static final String DB_CONNECTION_USERNAME = "root";

	static final String DB_CONNECTION_PASSWORD = "root";

	/**
	 * 
	 * @return List<Product> of all products in the database
	 */
	public static List<Product> getProducts(){
		String query = "SELECT * FROM products;";
		List<Product> list = new ArrayList<Product>();
		
		Connection con = connect();
		ResultSet rs = retrieve(con, query);

		Product p = null;
		
		String name = null, description = null, type = null, src = null; //type is being stored as a string (?)
		double price = 0;
		int id = -1;
		
		try {
			while (rs.next()) {
				id = rs.getInt("id");
				type = rs.getString("type");
				name = rs.getString("name");
				price = rs.getDouble("price");
				description = rs.getString("description");
				src = rs.getString("img_src");

				if (type.equals("cookie")) p = new Cookie (name, description, price, id, src);
				if (type.equals("cake")) p = new Cake (name, description, price, id, src);
				
				list.add(p);
				
			}

		} catch (SQLException e) {
			System.out.println("Error retrieving product list");
			return null;

		} finally {
			closeConnection(con);
		}
		
	return list;
		
	}
	
	public static List<Product> searchForProducts(String search){
		String query = "SELECT * FROM products where name LIKE '%" + search+ "%';";
		List<Product> list = new ArrayList<Product>();
		
		Connection con = connect();
		ResultSet rs = retrieve(con, query);

		Product p = null;
		
		String name = null, description = null, type = null, src = null; //type is being stored as a string (?)
		double price = 0;
		int id = -1;
		
		try {
			while (rs.next()) {
				id = rs.getInt("id");
				type = rs.getString("type");
				name = rs.getString("name");
				price = rs.getDouble("price");
				description = rs.getString("description");
				src = rs.getString("img_src");

				if (type.equals("cookie")) p = new Cookie (name, description, price, id, src);
				if (type.equals("cake")) p = new Cake (name, description, price, id, src);
				
				list.add(p);
				
			}

		} catch (SQLException e) {
			System.out.println("Error retrieving product list");
			return null;

		} finally {
			closeConnection(con);
		}
		
	return list;
		
	}
	
	public static List<Product> getCart(int user_id){
		String query = "SELECT * FROM shopping_cart WHERE user_id = " + user_id + ";";
		List<Product> list = new ArrayList<Product>();
		
		Connection con = connect();
		ResultSet rs = retrieve(con, query);

		Product p = null;
		
		String name = null, description = null, type = null, src = null; //type is being stored as a string (?)
		double price = 0;
		int id = -1;
		try {
			while (rs.next()) {
				id = rs.getInt("id");
				type = rs.getString("type");
				name = rs.getString("name");
				price = rs.getDouble("price");
				description = rs.getString("description");
				src = rs.getString("img_src");

				if (type.equals("cookie")) p = new Cookie (name, description, price, id, src);
				if (type.equals("cake")) p = new Cake (name, description, price, id, src);
				
				list.add(p);
				
			}

		} catch (SQLException e) {
			System.out.println("Error retrieving product list");
			return null;

		} finally {
			closeConnection(con);
		}
		
	return list;
		
	}
	
	/**
	 * 
	 * @param productId the id of the Product to be returned
	 * @return Product with given productId
	 */
	public static Product getProduct(int productId){
		String query = "SELECT * FROM products" + " WHERE id = " + productId + ";";

		Connection con = connect();
		ResultSet rs = retrieve(con, query);

		Product p = null;
		
		String name = null, description = null, type = null, src = null; //type is being stored as a string (?)
		double price = 0;
		int id = -1;
		
		try {
			while (rs.next()) {
				id = rs.getInt("id");
				type = rs.getString("type");
				name = rs.getString("name");
				price = rs.getDouble("price");
				description = rs.getString("description");
				src = rs.getString("img_src");

			}

		} catch (SQLException e) {
			System.out.println("Error retrieving product");

		} finally {
			closeConnection(con);
		}
		
		if (type.equals("cookie")) p = new Cookie (name, description, price, id, src);
		if (type.equals("cake")) p = new Cake (name, description, price, id, src);

		return p;
	}
	
	
	public static boolean addItemToCart(Product product, int user_id) {

		Connection con = connect();

		String query = "INSERT INTO shopping_cart (user_id, type, name, price, description)" 
				+ "VALUES (?,?,?,?,?);";

		try {
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, user_id);
			
			if (product instanceof Cookie)
				preparedStmt.setString(2, "cookie");
			
			if (product instanceof Cake)
				preparedStmt.setString(2, "cake");
			
			preparedStmt.setString(3, product.getName());
			preparedStmt.setDouble(4, product.getPrice());
			preparedStmt.setString(5, product.getDescription());

			preparedStmt.execute();

		} catch (SQLException e) {
			System.out.println("Erorr adding product");
			return false;

		} finally {
			closeConnection(con);
		}
		return true;
	}
	

	/**
	 * 
	 * @param user the Product to be added to the database
	 * @return true if successfully added, false otherwise
	 */
	public static boolean addProduct(Product product) {

		Connection con = connect();

		String query = "INSERT INTO products (type, name, price, description)" 
				+ "VALUES (?,?,?,?);";

		try {
			PreparedStatement preparedStmt = con.prepareStatement(query);

			if (product instanceof Cookie)
				preparedStmt.setString(1, "cookie");
			
			if (product instanceof Cake)
				preparedStmt.setString(1, "cake");
			
			preparedStmt.setString(2, product.getName());
			preparedStmt.setDouble(3, product.getPrice());
			preparedStmt.setString(4, product.getDescription());

			preparedStmt.execute();

		} catch (SQLException e) {
			System.out.println("Erorr adding product");
			return false;

		} finally {
			closeConnection(con);
		}
		return true;
	}

	/**
	 * 
	 * @param productId the id of the product to delete
	 * @return true if successfully deleted, false otherwise
	 */
	public static boolean deleteProduct(int productId) {

		Connection con = connect();
		String query = "DELETE FROM products WHERE id = " + productId + ";";

		try {
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.execute();

		} catch (SQLException e) {

			System.out.println("Erorr deleting product");
			return false;

		} finally {

			closeConnection(con);
		}

		return true;

	}
	
	public static Product getProductFromCart(int user_id, int product_id){
		
		String query = "SELECT * FROM shopping_cart" + " WHERE user_id = " + user_id
				+" AND id = " + product_id + ";";

		Connection con = connect();
		ResultSet rs = retrieve(con, query);

		Product p = null;
		
		String name = null, description = null, type = null, src = null; //type is being stored as a string (?)
		double price = 0;
		int id = -1;
		
		try {
			while (rs.next()) {
				id = rs.getInt("id");
				type = rs.getString("type");
				name = rs.getString("name");
				price = rs.getDouble("price");
				description = rs.getString("description");
				src = rs.getString("img_src");

			}

		} catch (SQLException e) {
			System.out.println("Error retrieving product");

		} finally {
			closeConnection(con);
		}
		
		if (type.equals("cookie")) p = new Cookie (name, description, price, id, src);
		if (type.equals("cake")) p = new Cake (name, description, price, id, src);

		return p;
	}
	
	public static boolean removeProductFromCart(int user_id, int product_id){
		
		Connection con = connect();
		String query = "DELETE FROM shopping_cart WHERE user_id = " + user_id + 
				" AND id = " + product_id + ";";

		try {
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.execute();

		} catch (SQLException e) {

			System.out.println("Erorr deleting product");
			return false;

		} finally {

			closeConnection(con);
		}

		return true;
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
