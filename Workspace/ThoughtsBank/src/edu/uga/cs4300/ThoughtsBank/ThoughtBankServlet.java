package edu.uga.cs4300.ThoughtsBank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/**
 * Servlet implementation class ThoughtBankServlet
 */
@WebServlet("/ThoughtBankServlet")
public class ThoughtBankServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private String user;
	Configuration cfg = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThoughtBankServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
private String templateDir = "/WEB-INF/templates";
	private ThoughtBank tbank;
	
    /**
     * @see HttpServlet#HttpServlet()
     */


    
    public void init() {
    	cfg = new Configuration(Configuration.VERSION_2_3_25);
    	
    	cfg.setServletContextForTemplateLoading(getServletContext(), templateDir);
    	
    	//shows stack trace in browser for debuggin, change to rethrowHandler after?
    	cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
    	tbank = new ThoughtBank();
    	user = null;
    }
    
    public void login(HttpServletRequest request, HttpServletResponse response){
    	
    	// this is for freemarker hashmap, don't need it for the java version
    	Template template = null;
    	DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
    	SimpleHash root = new SimpleHash(df.build());
    	
    	user = request.getParameter("uname");
		String password = request.getParameter("password");
		
		
		if (user == null || password == null){
			try{
	    		String templateName = "errorIndex.ftl";
	    		template = cfg.getTemplate(templateName);
	    		response.setContentType("text/html");
	    		PrintWriter out = response.getWriter();
	    		template.process(root, out);
	    		
	    	}
	    	catch (Exception e){
	    		e.printStackTrace();
	    	}
			
		}
		
		else{
			if (user != null)
				user = user.replace(user.charAt(0), Character.toUpperCase(user.charAt(0)));
		}
		
		//this is the linking of the ftl tag to the java variable
    	root.put("user", user);
    	root.put("password", password);
    	
    	
    	try{
    		String templateName = "home.ftl";
    		template = cfg.getTemplate(templateName);
    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		template.process(root, out);
    		
    	}
    	catch (Exception e){
    		e.printStackTrace();
    	}
    }
    
    private void addThought(HttpServletRequest request, HttpServletResponse response){
    	// this is for freemarker hashmap, don't need it for the java version
    	Template template = null;
    	DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
    	SimpleHash root = new SimpleHash(df.build());
    	
    	Thought thought = new Thought(request.getParameter("thought"));
		//String user = request.getParameter("uname");
		//this is the linking of the ftl tag to the java variable
    	
    	
    	//need to get the username from somewhere
    	if(thought != null && !request.getParameter("thought").equals(""))
		tbank.addThought(user, thought);
    	root.put("user", user);
    	

    	try{
    		String templateName = "home.ftl";
    		template = cfg.getTemplate(templateName);
    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		template.process(root, out);
    		
    	}
    	catch (Exception e){
    		e.printStackTrace();
    	}
    	
    }
    
    private void viewThoughts(HttpServletRequest request, HttpServletResponse response){
    	// this is for freemarker hashmap, don't need it for the java version
    	Template template = null;
    	DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
    	SimpleHash root = new SimpleHash(df.build());
    	
    	//need to get username from...?? somewhere
    	
    	List<Thought> thoughts = tbank.getThoughts(user);
    	
    	if (thoughts == null){
    		
    		root.put("user", user);
    		try{
        		String templateName = "noThoughts.ftl";
        		template = cfg.getTemplate(templateName);
        		response.setContentType("text/html");
        		PrintWriter out = response.getWriter();
        		template.process(root, out);
        		
        	}
        	catch (Exception e){
        		e.printStackTrace();
        	}
    		
    	}
    
    	else{	
	    	root.put("thoughts", thoughts);
	    	root.put("user", user);
	    	root.put("count",tbank.getThoughts(user).size());
	    	try{
	    		String templateName = "viewThoughts.ftl";
	    		template = cfg.getTemplate(templateName);
	    		response.setContentType("text/html");
	    		PrintWriter out = response.getWriter();
	    		template.process(root, out);
	    		
	    	}
	    	catch (Exception e){
	    		e.printStackTrace();
	    	}
    	
    	}
    }
    
    private void clearThoughts(HttpServletRequest request, HttpServletResponse response){
    	Template template = null;
    	DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
    	SimpleHash root = new SimpleHash(df.build());
    	tbank.clearThoughts(user);
    	
	    	root.put("user", user);
	    	
	    	try{
	    		String templateName = "noThoughts.ftl";
	    		template = cfg.getTemplate(templateName);
	    		response.setContentType("text/html");
	    		PrintWriter out = response.getWriter();
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
		
		
		
		String signin = request.getParameter("login");
		String addThought = request.getParameter("addthought");
		String viewThought = request.getParameter("viewthought");
		String clearThoughts = request.getParameter("clearthought");
		
		if (signin != null) {
			login(request, response);
		}
		else if (addThought != null){
			addThought(request, response);
			
		}
		else if (viewThought != null){
			viewThoughts(request, response);
			
		}
		else if (clearThoughts != null){
			clearThoughts(request, response);
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
