
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Mitch Wilson
 *
 */
public class DatabaseAccess {

	static final String DRIVE_NAME = "com.mysql.jdbc.Driver";

	static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/employees"
			+ "?autoReconnect=true&useSSL=false";

	static final String DB_CONNECTION_USERNAME = "root";

	static final String DB_CONNECTION_PASSWORD = "root";


	/**
	 * Open a connection to the database supplied from the variables in the @DatabaseAccess class
	 * @return the Connection object
	 * @author Mitch Wilson
	 */
	private static Connection connect() {
		Connection con = null;
		try {
			Class.forName(DRIVE_NAME);
			con = DriverManager.getConnection(CONNECTION_URL, DB_CONNECTION_USERNAME, DB_CONNECTION_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	} // end of connect

	
	/**
	 * Query the database a return a 2D List of Objects that represents the resulting table
	 * The 2D List is used to create a Table object in the @Main class
	 * @param query - the String query to send to the database
	 * @param LIMIT - the maximum number of tuples to include in the 2D List
	 * @return - a 2D List of Objects containing the results from the query
	 * @throws SQLException - if the query is not valid SQL syntax; may throw OutOfMemoryException
	 * if the query results in a large data set 
	 * @author Mitch Wilson
	 */
	public static List<List<Object>> retrieve(String query, final int LIMIT) 
			throws SQLException{

		Connection con = connect();
		ResultSet rset = null;

		Statement stmt = con.createStatement();
		
		long start = System.nanoTime();
		rset = stmt.executeQuery(query);
		long end = System.nanoTime();


		//ResultSet result = DatabaseAccess.retrieve("SELECT * FROM employees;");
		List <List<Object>> rows = new ArrayList<List<Object>>();


		ResultSetMetaData meta = rset.getMetaData();
		List<Object> attributes = new ArrayList<Object>();

		for (int i = 1; i <= meta.getColumnCount(); i++)
			attributes.add(meta.getColumnName(i));

		rows.add(attributes); //insert the attributes/column names first

		int count = 0;

		while (rset.next() & count++ < LIMIT){
			
			List<Object> tuple = new ArrayList<Object>();

			for (int i = 1; i <= meta.getColumnCount(); i++){
				tuple.add(rset.getObject(i)); //add the entries of the tuple
			}

			rows.add(tuple); //insert all the tuples
		
		}
		List<Object> metaDataForThisSearch = new ArrayList<Object>();
		
		rset.last();
		count = rset.getRow();
		metaDataForThisSearch.add(count);
		metaDataForThisSearch.add(end-start);
		
		rows.add(0, metaDataForThisSearch);

		closeConnection(con);

		return rows;
	}// end of retrieve

	/**
	 * Helper method to close the Connection when the query is completed
	 * @param con - the Conenction to be closed
	 * @author Mitch Wilson
	 */
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
