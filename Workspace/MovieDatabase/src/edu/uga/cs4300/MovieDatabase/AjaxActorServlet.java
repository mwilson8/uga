package edu.uga.cs4300.MovieDatabase;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import freemarker.template.Template;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;
/**
 * Servlet implementation class AjaxActorServlet
 */
@WebServlet("/AjaxActorServlet")
public class AjaxActorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxActorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = DatabaseAccess.connect();
		
		String query = "SELECT first_name, last_name FROM actors;";
		  System.out.println("query: " + query);
		   ResultSet rs = null;
		   List<Actor> actors = new ArrayList<Actor>();
		   
		   String fName = "", lName = "";
		   
		   try {

		  rs = DatabaseAccess.retrieve(con, query);
		
		  while(rs.next()){
			   fName = rs.getString("name");
			   lName = rs.getString("id");
			  
			   Actor a = new Actor (fName, lName);
			   actors.add(a);
		  }
		  
		   }
		   catch (SQLException e){
			   System.out.println("error");
		   }
		   
		     JSONObject json = new JSONObject();
		     
		     try{
		   for (Actor a: actors){
			   json.put("firstName", a.firstName());
			   json.put("lastName", a.lastName());
		   }
		     }
		     catch(Exception e){System.out.println("error");}
		 
		     System.out.println(json);
		   /*
		try{
	   		String templateName = "movieGenreList.ftl";
	   		template = cfg.getTemplate(templateName);
	   		response.setContentType("text/html");
	   		PrintWriter out = response.getWriter();
	   		root.put("movieTitles", movies);
	   		root.put("genre", genre);
	   		
	   		
	   		template.process(root, out);
	   		
	   		
	   		
	   	}
	   	catch (Exception e){
	   		e.printStackTrace();
	   	}
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
