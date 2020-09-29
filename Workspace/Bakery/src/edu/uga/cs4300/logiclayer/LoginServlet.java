package edu.uga.cs4300.logiclayer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uga.cs4300.objects.Product;
import edu.uga.cs4300.objects.User;
import edu.uga.cs4300.persistlayer.DatabaseProducts;
import edu.uga.cs4300.persistlayer.DatabaseUsers;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//important vars
	Configuration cfg = null;
	private String templateDir = "/WEB-INF/templates";
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    public void init() {
    	cfg = new Configuration(Configuration.VERSION_2_3_25);
    	cfg.setServletContextForTemplateLoading(getServletContext(), templateDir);
    	//shows stack trace in browser for debuggin, change to rethrowHandler after?
    	cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
    }
    
    
    private void continueAsGuest(HttpServletRequest request, HttpServletResponse response){
    	
    	/* send "guest" as user to main servlet */
    	System.out.println("Continue as guest..");
    	
    	User guest = new User (); //default values for no parameter User are
    								//all null, except boolean isGuest = true
    	
    	MainServlet.setUser(guest);
    	
    	/* send home page */
    	 sendHomePage(request, response, guest);
    }
    
    private void login(HttpServletRequest request, HttpServletResponse response){
    	
    	/* username & password validated with javascript, won't be null */
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	
    	if (username == null || username.equals("") || password == null || password.equals("")){
    		//if either is null, resend with error boolean
    	 	Template template = null;
    	   	DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
    	   	SimpleHash root = new SimpleHash(df.build());
    	try{
       		String templateName = "index.ftl";
       		template = cfg.getTemplate(templateName);
       		response.setContentType("text/html");
       		PrintWriter out = response.getWriter();

       		root.put("valid", false);
       		template.process(root, out);
       		

       	}
       	catch (Exception e){
       		
       	}
    	}

    	try{ //if error, resend with error boolean
    	if (DatabaseUsers.validateLogin(username, password)){
    		
    		/* get user's info (for header & shopping cart if needed) */
    		User u = DatabaseUsers.getUser(username);
    		
    			MainServlet.setUser(u);
    		
    		
    		
    		/* send home page */
        	sendHomePage(request, response, u);
	    		
    	}
    	}catch(Exception e){
    	 	Template template = null;
    	   	DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
    	   	SimpleHash root = new SimpleHash(df.build());
    	try{
       		String templateName = "index.ftl";
       		template = cfg.getTemplate(templateName);
       		response.setContentType("text/html");
       		PrintWriter out = response.getWriter();

       		root.put("valid", false);
       		template.process(root, out);
       		

       	}
       	catch (Exception e2){
       		
       	}
    	}
    	
    	
    		
    }
    	
    
    
    private void register(HttpServletRequest request, HttpServletResponse response){
    	
    	/* send registration page */
    	/* validate registration through javascript */
    	System.out.println("Send registration page");
    	
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
    	
    }
    
    private void addUser(HttpServletRequest request, HttpServletResponse response){
    	
    	/* create new user based on all parameters */
    	String firstName = request.getParameter("f_name");
    	String lastName = request.getParameter("l_name");
    	String userName = request.getParameter("u_name");
    	String password = request.getParameter("password");
    	String email = request.getParameter("email");
    	
    	User u = new User(firstName, lastName, userName, password, email);
    	/* userDatabase.add */
    	
    	DatabaseUsers.addUser(u);
    	
    	/* send back to login page? */
    	
    	Template template = null;
	   	DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
	   	SimpleHash root = new SimpleHash(df.build());
	try{
   		String templateName = "index.ftl";
   		template = cfg.getTemplate(templateName);
   		response.setContentType("text/html");
   		PrintWriter out = response.getWriter();

   		
   		template.process(root, out);
   		

   	}
   	catch (Exception e){
   		e.printStackTrace();
   	}
    	
    }
    
    
    private void sendHomePage(HttpServletRequest request, HttpServletResponse response, User u /*,List<Product> productList*/){
    	Template template = null;
	   	DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
	   	SimpleHash root = new SimpleHash(df.build());
	try{
   		String templateName = "home.ftl";
   		template = cfg.getTemplate(templateName);
   		response.setContentType("text/html");
   		PrintWriter out = response.getWriter();
   		
   		MainServlet.setUser(u);
   		
   		root.put("user", u);

   		List<Product> list = DatabaseProducts.getProducts();
   		//this won't work because it doesn't recognize "ProductDatabase"..?
   		
   		root.put("productList", list);
   		root.put("products", list);
   		root.put("inCart", false);
   		
   		template.process(root, out);

   	}
   	catch (Exception e){
   		e.printStackTrace();
   	}
		
}
    	
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String guestLogin = request.getParameter("guestLogin");
    	String register = request.getParameter("register");
    	String completeRegistration = request.getParameter("completeRegistration");
    	
		if (login != null){
			login(request, response);
			
		} else if (guestLogin != null){
			continueAsGuest(request, response);
			
		} else if (register != null){
			System.out.println("register");
			register(request, response);
			
		} else if (completeRegistration != null){
			addUser(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
}
