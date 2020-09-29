package edu.uga.cs4300.StudentDatabase;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/**
 * Servlet implementation class ThoughtBankServlet
 */
@WebServlet("/StudentDatabaseServlet")
public class StudentDatabaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Configuration cfg = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentDatabaseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
private String templateDir = "/WEB-INF/templates";
	
    /**
     * @see HttpServlet#HttpServlet()
     */


    
    public void init() {
    	cfg = new Configuration(Configuration.VERSION_2_3_25);
    	
    	cfg.setServletContextForTemplateLoading(getServletContext(), templateDir);
    	
    	//shows stack trace in browser for debuggin, change to rethrowHandler after?
    	cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);

    }
    

   private void addUser(String fname, String lname, String email, String uname, String password){
	  Connection con = DatabaseAccess.connect();
	   System.out.println("Adding user");
	   String query = "INSERT INTO users (f_name, l_name, email, username, password)\n"
	   		+ "VALUES (?,?,?,?,?);";
	   
	   try {
		PreparedStatement preparedStmt = con.prepareStatement(query);
	
	   preparedStmt.setString(1,  fname);
	   preparedStmt.setString(2,  lname);
	   preparedStmt.setString(3,  email);
	   preparedStmt.setString(4,  uname);
	   preparedStmt.setString(5,  password);
	   
	   preparedStmt.execute();
	   
	   System.out.println("Added successfully");
	   
	} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Erorr occurred");
	}
	   
	   
	  
   }
   
   private void register(HttpServletRequest request, HttpServletResponse response){
	  
   	/*these are just printing to console to follow the flow*/
   	String fname = request.getParameter("f_name");
	String lname = request.getParameter("l_name");
	String email = request.getParameter("email");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
  
   System.out.println("Adding: "+ fname + lname + email + username + password);
   addUser(fname, lname, email, username, password);
   	
   }
   
   private void signin(HttpServletRequest request, HttpServletResponse response){
	   Connection con = DatabaseAccess.connect();
	  // System.out.println("Accessing database");
	   String user = request.getParameter("uname");
	   String pass = request.getParameter("password");
	  // System.out.println("user: " + user);
	   //System.out.println("pass: " + pass);
	   String query = "SELECT * FROM users WHERE username = '" + user +"';";
	  // System.out.println("query: " + query);
	   ResultSet rs = null;
	   String password="";
	  
	   
	   try {

		   Statement st = con.createStatement();
		   
	   
	  rs = DatabaseAccess.retrieve(con, query);
	
	  while(rs.next())
		   password = rs.getString("password");
	   

	   //System.out.println("pw = " + password);
	   
	   
	   if (password.equals(pass)){
		   System.out.println("successful login");
		   
		   
		   
		   HttpSession session = request.getSession();
		   
		   Integer accessCount = (Integer) session.getAttribute("accessCount");
		   Date creationTime = new Date (session.getCreationTime());
		   Date lastAccess = new Date (session.getLastAccessedTime());
		   String sessionId = session.getId();
		   
		   if (accessCount == null) accessCount = 0;
		   
		   else accessCount += 1;
		   
		   session.setAttribute("accessCount", accessCount);
		   
		   
		   
		   Template template = null;
	    	DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
	    	SimpleHash root = new SimpleHash(df.build());
	    	
	    	root.put("user", user);
	    	root.put("accessCount", accessCount);
	    	root.put("creationTime", creationTime);
	    	root.put("lastAccess", lastAccess);
	    	root.put("sessionId", sessionId);
	    	
	    	
	    	try{
	    		String templateName = "homepage.ftl";
	    		template = cfg.getTemplate(templateName);
	    		response.setContentType("text/html");
	    		PrintWriter out = response.getWriter();
	    		template.process(root, out);
	    		
	    	}
	    	catch (Exception e){
	    		e.printStackTrace();
	    	}
	   }
	   
	} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Erorr occurred");
	}
	   
	   
	   
	  
   }
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String signUp = request.getParameter("signUp");
		String signIn = request.getParameter("signIn");
		String listUsers = request.getParameter("list");
		String register = request.getParameter("register");
		String logOut = request.getParameter("logOut");
		
	if (signUp != null){
		System.out.println("signup");
		 Template template = null;
		   	DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		   	SimpleHash root = new SimpleHash(df.build());
		try{
	   		String templateName = "register.ftl";
	   		template = cfg.getTemplate(templateName);
	   		response.setContentType("text/html");
	   		PrintWriter out = response.getWriter();
	   		template.process(root, out);
	   		
	   	}
	   	catch (Exception e){
	   		e.printStackTrace();
	   	}
		
	}else if (signIn != null){
		//check username against database, if exists then
		//verify password from database
		System.out.println("signin");
		signin (request, response);
		
	}else if (listUsers != null){
		
		Connection con = DatabaseAccess.connect();
		//System.out.println("Connected to database successfully.");
		String query = "select * from users";
		ResultSet rs = DatabaseAccess.retrieve(con, query);
		try {
			while(rs.next()) {
				System.out.println("id: " + rs.getInt("id")
								 + "\nFirst Name: " + rs.getString("f_name") 
								 + "\nLast Name: " + rs.getString("l_name")
								 + "\nEmail: " + rs.getString("email")
								 + "\nUsername: " + rs.getString("username")
								 + "\nPassword: " + rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DatabaseAccess.closeConnection(con);
		
	}else if (register != null){
		
		register(request, response);
		
	}else if (logOut != null){
		System.out.println("logout");
		request.getSession().invalidate();
		//System.out.println("access count: " + (Integer)request.getSession().getAttribute("accessCount"));
	}
		
	}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
