package edu.uga.cs4300.MovieDatabase;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
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
@WebServlet("/MovieDatabaseServlet")
public class MovieDatabaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Configuration cfg = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieDatabaseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
private String templateDir = "/WEB-INF/templates";
	private int movieID = -1;
    /**
     * @see HttpServlet#HttpServlet()
     */


    
    public void init() {
    	cfg = new Configuration(Configuration.VERSION_2_3_25);
    	
    	cfg.setServletContextForTemplateLoading(getServletContext(), templateDir);
    	
    	//shows stack trace in browser for debuggin, change to rethrowHandler after?
    	cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);

    }

 
   
   private void showMovies (String genre, HttpServletRequest request, HttpServletResponse response){
	   Connection con = DatabaseAccess.connect();
		  // System.out.println("Accessing database");
		  // System.out.println("user: " + user);
		   //System.out.println("pass: " + pass);
		   String query = "SELECT * FROM movies m"
		   		+ " JOIN movies_genres mg ON mg.movie_id = m.id"
		   		+ " WHERE mg.genre = '" + genre +"';";
		  System.out.println("query: " + query);
		   ResultSet rs = null;
		   List<Movie> movies = new ArrayList<Movie>();
		   
		   String movieTitle = "";
		   int movieId, year, rank;
		   try {

			   
			   
		   
		  rs = DatabaseAccess.retrieve(con, query);
		
		  while(rs.next()){
			   movieTitle = rs.getString("name");
			   movieId = rs.getInt("id");
			   year = rs.getInt("year");
			   rank = rs.getInt("rank");
			   
			   System.out.println("movie title: " + movieTitle);
			   
			   Movie m = new Movie (movieId, movieTitle, year, rank);
			   movies.add(m);
		  }
		  
		   }
		   catch (SQLException e){
			   System.out.println("error");
		   }

		   Template template = null;
		   	DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		   	SimpleHash root = new SimpleHash(df.build());
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
		   
	   
   }
   
   private void searchForReviews (HttpServletRequest request, HttpServletResponse response){
	   boolean needID = false;
		String movieSearchGo = request.getParameter("movieSearchGo");
		String movieSearch = request.getParameter("movieSearch");
		String movieIDString = request.getParameter("movieID");
		String movieIDGo = request.getParameter("movieIDGo");
		System.out.println("add review");
		
		if (movieSearchGo != null && movieSearch != null){
			System.out.println("searched for movie with keyword: " + movieSearch);
			// searched for a movie
			//movieSearch should have movie title
			//return values that contain the search values
			needID = true;
			
			 Connection con = DatabaseAccess.connect();
			   String query = "SELECT * FROM movies"
			   		+ " WHERE name LIKE '%" + movieSearch +"%';";
			  System.out.println("query: " + query);
			   ResultSet rs = null;
			   List<Movie> movies = new ArrayList<Movie>();
			   
			   String movieTitle = "";
			   int movieID, year, rank;
			   try {
 
			  rs = DatabaseAccess.retrieve(con, query);
			
			  while(rs.next()){
				   movieTitle = rs.getString("name");
				   movieID = rs.getInt("id");
				   year = rs.getInt("year");
				   rank = rs.getInt("rank");
				   
				   System.out.println("id: " + movieID + "movie title: " + movieTitle);
				   
				   Movie m = new Movie (movieID, movieTitle, year, rank);
				   movies.add(m);
			  }
			  
			   }
			   catch (SQLException e){
				   System.out.println("error");
			   }
			   Template template = null;
			   	DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			   	SimpleHash root = new SimpleHash(df.build());
			try{
		   		String templateName = "reviewSearch.ftl";
		   		template = cfg.getTemplate(templateName);
		   		response.setContentType("text/html");
		   		PrintWriter out = response.getWriter();
		   		
		   		root.put("needID", needID);
		   		root.put("movies", movies);
		   		
		   		template.process(root, out);
		   		
		   	}
		   	catch (Exception e){
		   		e.printStackTrace();
		   	}
			
			
			
			
			
			
			
			
		
		} else if (movieIDGo != null && movieIDString != null){
			System.out.println("searched for movie using ID: " + movieIDString);
			//searched for a movie using movie ID
			//movieId should have movie ID
			//show add review page for the specified movie
			
			Connection con = DatabaseAccess.connect();
			   String query = "SELECT * FROM movies m"
			   		+ " JOIN reviews r ON m.id = r.movie_id"
			   		+ " WHERE m.id = " + movieIDString + ";";
			  System.out.println("query: " + query);
			   ResultSet rs = null;
			  List<MovieReview> reviews = new ArrayList<MovieReview>();
			   String movieTitle = "";
			   MovieReview review;
			   int year = 0;
			   try {

			  rs = DatabaseAccess.retrieve(con, query);
			
			  while(rs.next()){
				   movieTitle = rs.getString("name");
				   this.movieID = rs.getInt("id");
				   year = rs.getInt("year");
				   
				   System.out.println("id: " + this.movieID + "movie title: " + movieTitle);
			  }
			  
			  con = DatabaseAccess.connect();
			  query = "SELECT * FROM reviews r"
			   		+ " JOIN movies m ON r.movie_id = m.id"
			   		+ " WHERE r.movie_id = " + movieIDString + ";";
			  System.out.println("query: " + query);
			   rs = null;
			  
			  rs = DatabaseAccess.retrieve(con, query);
			  String r; int r_id;
			  while(rs.next()){
				   
				  r = rs.getString("review");
				  r_id = rs.getInt("id");
				  
				  review = new MovieReview(r, r_id);
				  reviews.add(review);
				  
				  System.out.println("r id: " + r_id + " review: " + review.getReview());
			  }
			  
			  
			  
			   }
			   catch (SQLException e){
				   System.out.println("error");
			   }
			   
			   
			   Template template = null;
			   	DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			   	SimpleHash root = new SimpleHash(df.build());
			try{
		   		String templateName = "movieDetails.ftl";
		   		template = cfg.getTemplate(templateName);
		   		response.setContentType("text/html");
		   		PrintWriter out = response.getWriter();
		   		
		   		root.put("needID", needID);
		   		root.put("movieTitle", movieTitle);
		   		root.put("movieID", this.movieID);
		   		root.put("movieYear", year);
		   		root.put("reviews", reviews);
		   		
		   		template.process(root, out);
		   		
		   	}
		   	catch (Exception e){
		   		e.printStackTrace();
		   	}
			
			
			needID = false;
			
		} else {
			
			Template template = null;
		   	DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		   	SimpleHash root = new SimpleHash(df.build());
		try{
	   		String templateName = "reviewSearch.ftl";
	   		template = cfg.getTemplate(templateName);
	   		response.setContentType("text/html");
	   		PrintWriter out = response.getWriter();
	   		
	   		root.put("needID", needID);
	   		
	   		template.process(root, out);
	   		
	   	}
	   	catch (Exception e){
	   		e.printStackTrace();
	   	}
			
			
		}
		 
		
	
   }
   
  
   
   
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String viewMovies = request.getParameter("viewMovies");
		String movieReviewDelete = request.getParameter("movieReviewDelete");
		String viewMoviesGo = request.getParameter("viewMoviesGo");
		String addReview = request.getParameter("addReview");
		String movieSearchGo = request.getParameter("movieSearchGo");
		String movieSearch = request.getParameter("movieSearch");
		String movieID = request.getParameter("movieID");
		String movieIDGo = request.getParameter("movieIDGo");
		String movieReviewGo = request.getParameter("movieReviewGo");
		String deleteMovie = request.getParameter("movieDelete");
		String addMovie = request.getParameter("addMovie");
		String addNewMovie = request.getParameter("addNewMovie");
		
	if (viewMovies != null){
		System.out.println("view movies");
		 Template template = null;
		   	DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		   	SimpleHash root = new SimpleHash(df.build());
		try{
	   		String templateName = "movieGenreSelection.ftl";
	   		template = cfg.getTemplate(templateName);
	   		response.setContentType("text/html");
	   		PrintWriter out = response.getWriter();
	   		template.process(root, out);
	   		
	   	}
	   	catch (Exception e){
	   		e.printStackTrace();
	   	}
		
	}else if (viewMoviesGo != null){
		String genre = request.getParameter("movieGenre");
		System.out.println("showing movies for genre: " + genre);
		
		showMovies (genre, request, response);
		
	}else if (addReview != null || movieSearchGo != null 
			|| movieSearch != null || movieID != null
			|| movieIDGo != null){
		
		searchForReviews(request, response);
		
	}else if (movieReviewGo != null){
		String newReview = request.getParameter("movieReview");
		 Connection con = DatabaseAccess.connect();
		   System.out.println("Adding movie review");
		   String query = "INSERT INTO reviews (movie_id, review)"
		   		+ " VALUES (?,?);";
		   
		   try {
			PreparedStatement preparedStmt = con.prepareStatement(query);
		
		   preparedStmt.setInt(1, this.movieID);
		   preparedStmt.setString(2,  newReview);
		   
		   System.out.println("statement: " + preparedStmt);

		   preparedStmt.execute();
		   PrintWriter out = response.getWriter();
		   out.println("Added successfully");
		   
		   System.out.println("Added successfully");
		   
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erorr occurred");
		}
		   
		   
		   
		   
	}else if (movieReviewDelete != null){
		String movieReviewID = request.getParameter("movieReviewID");
		
		Connection con = DatabaseAccess.connect();
		   System.out.println("Deleting movie review");
		   String query = "DELETE FROM reviews WHERE movie_id = "
				   + this.movieID + " AND id = " + movieReviewID + ";";
		   
		   try {
			PreparedStatement preparedStmt = con.prepareStatement(query);

		   
		   System.out.println("statement: " + preparedStmt);

		   preparedStmt.execute();
		   PrintWriter out = response.getWriter();
		   out.println("Deleted successfully");
		   
		   System.out.println("Deleted successfully");
		   
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erorr occurred");
		}
		
		
	} else if (deleteMovie != null){
		Connection con = DatabaseAccess.connect();
		   System.out.println("Deleting all movie reviews");
		   String query = "DELETE FROM reviews WHERE movie_id = "
				   + this.movieID + ";";
		   
		   try {
			PreparedStatement preparedStmt = con.prepareStatement(query);

		   
		   System.out.println("statement: " + preparedStmt);

		   preparedStmt.execute();
		   PrintWriter out = response.getWriter();
		   System.out.println("reviews cleared");
		   
		   query = "DELETE FROM movies WHERE id = "
				   + this.movieID + ";";
		System.out.println("movie deleted");
		out.print("Deleted successfully");
		
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erorr occurred");
		}
	
	} else if (addMovie != null){
		
		Template template = null;
	   	DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
	   	SimpleHash root = new SimpleHash(df.build());
	try{
   		String templateName = "newMovie.ftl";
   		template = cfg.getTemplate(templateName);
   		response.setContentType("text/html");
   		PrintWriter out = response.getWriter();
   		template.process(root, out);
   		
   	}
   	catch (Exception e){
   		e.printStackTrace();
   	}
	}else if (addNewMovie != null){
		
		String movieTitle = request.getParameter("movieTitle");
		int movieYear = Integer.parseInt(request.getParameter("movieYear"));
		int movieRank = Integer.parseInt(request.getParameter("movieRank"));
		
		 Connection con = DatabaseAccess.connect();
		   System.out.println("Adding movie review");
		   String query = "INSERT INTO movies (name, year, rank)"
		   		+ " VALUES (?,?,?);";
		   
		   try {
			PreparedStatement preparedStmt = con.prepareStatement(query);
		
		   preparedStmt.setString(1, movieTitle);
		   preparedStmt.setInt(2, movieYear);
		   preparedStmt.setInt(3, movieRank);
		   
		   System.out.println("statement: " + preparedStmt);

		   preparedStmt.execute();
		   PrintWriter out = response.getWriter();
		   out.println("Added successfully");
		   
		   System.out.println("Added successfully");
		   
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erorr occurred");
		}
		   
		
		
		
		
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
