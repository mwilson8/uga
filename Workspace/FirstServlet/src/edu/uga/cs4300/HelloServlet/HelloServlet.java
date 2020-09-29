package edu.uga.cs4300.HelloServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.*;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Configuration cfg = null;
	
	private String templateDir = "/WEB-INF/templates";
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    public void init() {
    	cfg = new Configuration(Configuration.VERSION_2_3_25);
    	
    	cfg.setServletContextForTemplateLoading(getServletContext(), templateDir);
    	
    	//shows stack trace in browser for debuggin, change to rethrowHandler after?
    	cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
    
    }
    
    public void runTemplate(HttpServletRequest request, HttpServletResponse response){
    	
    	// this is for freemarker hashmap, don't need it for the java version
    	Template template = null;
    	DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
    	SimpleHash root = new SimpleHash(df.build());
    	
    	String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
    	String userName = fname + " " + lname;
    	root.put("user", userName);
    	
    	Product p = new Product("Computer", "/product/systems.html");
    	
    	root.put("latestProduct", p);
    	
    	
    	try{
    		String templateName = "product.ftl";
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
		runTemplate(request, response);
		
		/*
		PrintWriter out = response.getWriter();
		
		//the parameter arguments must match the names given in html
		
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		
		response.setContentType("text/html");
		out.println("<!DOCTYPE html> \n"
				+ "<html>\n"
				+ "<body>\n"
				+ "<h1>Hello</h1>"
				+ "<h4>" + fname + "</h4>"
				+ "<h4>" + lname + "</h4>");
		*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
