
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

//	static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/employees"
			//+ "?autoReconnect=true&useSSL=false";

	static final String DB_CONNECTION_USERNAME = "root";

	static final String DB_CONNECTION_PASSWORD = "root";


	/**
	 * Open a connection to the database supplied from the variables in the @DatabaseAccess class
	 * @return the Connection object
	 * @author Mitch Wilson
	 */
	private static Connection connect(final String CONNECTION_URL) {
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

		Connection con = connect("temp");
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

	public static boolean initialize(final String database){
		Connection con = connect(database);

		TupleGeneratorImpl tg = new TupleGeneratorImpl();
		
		tg.addRelSchema ("Student",
                "id name address status",
                "Integer String String String",
                "id",
                null);

        tg.addRelSchema ("Professor",
                "id name deptId",
                "Integer String String",
                "id",
                null);

        tg.addRelSchema ("Course",
                "crsCode deptId crsName descr",
                "String String String String",
                "crsCode",
                null);

        tg.addRelSchema ("Teaching",
                "crsCode semester profId",
                "String String Integer",
                "crsCode semester",
                new String [][] {{ "profId", "Professor", "id" },
                        { "crsCode", "Course", "crsCode" }});

        tg.addRelSchema ("Transcript",
                "studId crsCode semester grade",
                "Integer String String String",
                "studId crsCode semester",
                new String [][] {{ "studId", "Student", "id"},
                        { "crsCode", "Course", "crsCode" },
                        { "semester", "Teaching", "semester" }});
		
		Comparable[][][] comp = tg.generate(new int [] {/*10,000 Student tuples*/10000,
														/*1,000 Professor tuples*/ 1000,
														/*2,000 Course tuples*/ 2000,
														/*5,000 Transcript tuples*/ 5000,
														/*5,000 Teaching tuples*/ 5000});
		
		
		
		String[] tableNames = { "Student", "Professor", "Course", "Transcript", "Teaching" };
		
		Statement stmt;
		try{
			stmt = con.createStatement();
		} catch (Exception e){
			System.err.println("Error connecting to database");
			return false;
		}
		
		for (int i = 0; i < comp.length; i++){
			
			

			for (int j = 0; j < comp[i].length; j++){
				String insert = "INSERT INTO " + tableNames[i] + " VALUES (";
				
				for (int k = 0; k < comp[i][j].length; k++){
					insert += '\'' + comp[i][j][k].toString() + "\', ";
				}
				
				insert = insert.substring(0, insert.length()-2);
				insert += ");";
				
				try{
					
					stmt.executeUpdate(insert);
					
					if (database.contains("no_key"))
						System.out.println("no_key: " + insert);
					else
						System.out.println("with_key: " + insert);
					
				} catch (SQLException sqle){
					System.err.println(insert + " -> " + sqle.getMessage());
					sqle.printStackTrace();
					return false;

				} catch (Exception e){
					System.err.println(insert + " -> " + e.getMessage());
					e.printStackTrace();
					return false;

				}

			}
			
			
			
			
		}
		return true;
	}//initialize
	
}
