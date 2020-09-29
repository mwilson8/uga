import java.sql.*;
import java.util.*;


/**
 * A class for driving the @DatabaseAccess class, contains a private helper class, Table
 * which aids in the storing and printing of the data from the query
 * @author Mitch Wilson
 *
 */
public class Main {

	public static void main (String[] args) throws SQLException{
		
		Scanner in = new Scanner (System.in);
		
		while (true){
		
			//built in queries
			System.out.println("(1) Query 1 : List department(s) with maximum ratio of average female salaries to average men salaries");
			System.out.println("(2) Query 2 : List manager(s) who holds office for the longest duration");
			System.out.println("(3) Query 3 : For each department, list number of employees born in each decade and their average salaries");
			System.out.println("(4) Query 4 : List employees, who are female, born before Jan 1, 1990, makes more than 80K annually and hold a manager position");
			System.out.println("(5) Query 5a : 1 degree of separation ");
			System.out.println("(6) Query 5b : 2 degrees of separation ");
			System.out.println("Select a query, enter your own, or (q)uit");
			System.out.print("-> ");
			String query = in.nextLine();
			
			if (query.equals(""))
				continue;
			
			//if quit
			if (query.toLowerCase().charAt(0) == 'q' || query.equalsIgnoreCase("quit")){
				System.out.println("Exiting..");
				System.exit(0);
			} else if (Character.isDigit(query.charAt(0))){
				switch (query.charAt(0)){
				case '1':
					query = "SELECT      "
							+ "departments.dept_no, departments.dept_name, sal_ratio FROM     "
							+ "employees.departments AS departments,     (SELECT          "
							+ "a.dept_no, (a.female_salary / b.male_salary) AS sal_ratio     "
							+ "FROM         (SELECT          D.dept_no, AVG(S.salary) AS female_salary     "
							+ "FROM         employees.salaries AS S, employees.dept_emp AS D, employees.employees AS E     "
							+ "WHERE         (D.emp_no = S.emp_no)             AND (S.emp_no = E.emp_no)             "
							+ "AND (E.gender = 'F')     GROUP BY D.dept_no) a, (SELECT          D.dept_no, AVG(S.salary) "
							+ "AS male_salary     FROM         employees.salaries AS S, employees.dept_emp AS D, employees.employees AS E     "
							+ "WHERE         (D.emp_no = S.emp_no)             AND (S.emp_no = E.emp_no)             AND (E.gender = 'M')     "
							+ "GROUP BY D.dept_no) b     WHERE         (a.dept_no = b.dept_no)     GROUP BY a.dept_no) c WHERE     (departments.dept_no = c.dept_no)         "
							+ "AND (c.sal_ratio = (SELECT              MAX(c.sal_ratio)         FROM             "
							+ "(SELECT                  a.dept_no, (a.female_salary / b.male_salary) AS sal_ratio             "
							+ "FROM                 (SELECT                  D.dept_no, AVG(S.salary) AS female_salary             FROM                 "
							+ "employees.salaries AS S, employees.dept_emp AS D, employees.employees AS E             WHERE                 "
							+ "(D.emp_no = S.emp_no)                     AND (S.emp_no = E.emp_no)                     AND (E.gender = 'F')             "
							+ "GROUP BY D.dept_no) a, (SELECT                  D.dept_no, AVG(S.salary) AS male_salary             FROM                 "
							+ "employees.salaries AS S, employees.dept_emp AS D, employees.employees AS E             WHERE                 (D.emp_no = S.emp_no)                     "
							+ "AND (S.emp_no = E.emp_no)                     AND (E.gender = 'M')             GROUP BY D.dept_no) b             WHERE                 (a.dept_no = b.dept_no)            "
							+ " GROUP BY a.dept_no) c)) LIMIT 0, 1000";
					
					break;
				case '2':
					query = "SELECT      E.emp_no,     first_name,     last_name,     from_date,     to_date,     "
							+ "DATEDIFF(to_date, from_date) as time_in_office FROM     employees.employees AS E,     "
							+ "employees.dept_manager AS D WHERE     (E.emp_no = D.emp_no)         AND DATEDIFF(D.to_date, D.from_date) = (SELECT             "
							+ " MAX(DATEDIFF(D.to_date, D.from_date))         FROM             employees.dept_manager AS D) LIMIT 0, 1000";
					break;
				case '3':
					query = "SELECT * FROM titles";
					
					break;
				case '4':
					query = "SELECT      E.first_name, E.last_name, E.birth_date, S.salary FROM     employees AS E         "
							+ "NATURAL JOIN     salaries AS S WHERE     emp_no IN (SELECT              emp_no         FROM             dept_manager)         "
							+ "AND S.to_date = '9999-01-01'         AND S.salary > 80000         AND E.birth_date < '1990-01-01'         AND E.gender = 'f' ORDER BY E.last_name LIMIT 0, 1000";
					
					break;
				case '5':
					query = "SELECT      D1.emp_no,     D2.emp_no,     D1.dept_no,     d2.dept_no,     D1.to_date,     D1.from_date,     D2.to_date,     D2.from_date FROM     "
							+ "employees.dept_emp AS D1,     employees.dept_emp AS D2 WHERE     (D1.emp_no != D2.emp_no)         AND (D1.dept_no = D2.dept_no)         AND (D1.from_date <= D2.from_date)         "
							+ "AND (D1.to_date >= D2.from_date) LIMIT 0, 1000";
					break;
				case '6':
					query = "SELECT 'incomplete' FROM employees LIMIT 5";
					
					break;
				}
			}
			
			/*
			if (query.indexOf("LIMIT") == -1)
				query = query.concat(" LIMIT 100");
			*/
			
			int limit = 100;
			

			//take the query and retrieve the results. create a new table with the 2D List
			//handle any errors
			try{
				Table table = new Table (DatabaseAccess.retrieve(query, limit), limit);
				table.print();
			} catch (SQLException sqle){
				System.err.println("Invalid query");;
			} catch (OutOfMemoryError oome){
				System.err.println("Query returned a result too large to be processed\n"
						+ "please narrow your search request");
			}catch (Exception e){
				System.err.println("Unknown error");
			}
			
		}//while
		
		
		
	}
	
	
	
	
	/**
	 * A private helper class to deal with the results from the query 
	 * The constructor takes in a 2d List of Objects, which must be parsed from the ResultSet
	 * @author MitchWilson
	 *
	 *	methods include a constructor and print method
	 */
	private static class Table{
		List<List<Object>> table;
		int count;
		long time;
		int limit;
		
		Table(List<List<Object>> table, int limit){
			this.table = table;
			count = (int)this.table.get(0).get(0);
			time = (long)this.table.get(0).get(1);
			this.table.remove(0);
			this.limit = limit;
		}
		
		void print(){
			
			System.out.print("\n" + count + ((count == 1) ? " entry" : " entries") + " found in " + time/1000000000.0 + "s");
			if (count > limit)
				System.out.print(" (first " + limit + " entries shown)");
			
			System.out.println();
			printLongLine();
			
			int [] widths = widths();
			
			for (int i = 0; i < table.size(); i++){
				for (int j = 0; j < table.get(i).size(); j++){
					
						System.out.printf("| %-" + (widths[j] + 1) + "s", table.get(i).get(j));
				}
				System.out.println("|");
				
				if (i == 0){
					printLongLine();
				}
			}
			
			printLongLine();
		}//print
		
		/**
		 * Used to determine the widest entry that is in this table, used
		 * as a helper to the print method - to determine padding 
		 * @param table
		 * @return
		 */
		void printLongLine(){

			int [] widths = widths();
			System.out.print('+');
			for (int i = 0; i < widths.length; i++){
				for (int j = 0; j < widths[i] + 2; j++){
					System.out.print('-');
				}
				System.out.print('+');
			}
			System.out.println();
			
		}//printLongLine
		
		int [] widths(){
			
			int [] width = new int [table.get(0).size()];
			for (int i = 0; i < table.size(); i ++){
				for (int j = 0; j < table.get(i).size(); j++){
					if (width[j] < table.get(i).get(j).toString().length())
						width[j] = table.get(i).get(j).toString().length();
				}
			}
			
			return width;
		}
	}//Table class
}
