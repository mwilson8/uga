package edu.uga.cs4300.logiclayer;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//important vars
	private String templateDir = "/WEB-INF/templates";
	private static User user;
	Configuration cfg = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * setUser(User) must be called before this method
     * @param request
     * @param response
     */
    
    private void sendHomePage(HttpServletRequest request, HttpServletResponse response, User u /*,List<Product> productList*/){
    	List<Product> productList = DatabaseProducts.getProducts();
    	Template template = null;
	   	DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
	   	SimpleHash root = new SimpleHash(df.build());
	try{
   		String templateName = "home.ftl";
   		//cfg.setServletContextForTemplateLoading(getServletContext(), "/shoppingCart/test/");
   		template = cfg.getTemplate(templateName);
   		response.setContentType("text/html");
   		PrintWriter out = response.getWriter();
   		
   		root.put("user", u);
   		root.put("products", productList);
   		root.put("inCart", false);

   		//List<Product> list = ProductDatabase.getProducts();
   		//this won't work because it doesn't recognize "ProductDatabase"..?
   		
   		//root.put("productList", list);
   		
   		template.process(root, out);

   	}
   	catch (Exception e){
   		e.printStackTrace();
   	}
		
}
    
    protected static void setUser(User u){
    	user = u;
    }
    
    public void init() {
    	cfg = new Configuration(Configuration.VERSION_2_3_25);
    	
    	cfg.setServletContextForTemplateLoading(getServletContext(), templateDir);
    	
    	//shows stack trace in browser for debuggin, change to rethrowHandler after?
    	cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);

    }
    
    private void viewCart(HttpServletRequest request, HttpServletResponse response){
    	List<Product> cart = DatabaseProducts.getCart(user.getID());
    	if (cart == null) System.out.println("cart is null");
    	else {
    	for (Product p: cart)
    		System.out.println(p.getID() + " " + p.getName());
    	}
    	
    	Template template = null;
	   	DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
	   	SimpleHash root = new SimpleHash(df.build());
	try{
   		String templateName = "cart.ftl";
   		//cfg.setServletContextForTemplateLoading(getServletContext(), "/shoppingCart/test/");
   		template = cfg.getTemplate(templateName);
   		response.setContentType("text/html");
   		PrintWriter out = response.getWriter();
   		
   		root.put("user", user);
   		root.put("products", cart);
   		root.put("inCart", true);

   		//List<Product> list = ProductDatabase.getProducts();
   		//this won't work because it doesn't recognize "ProductDatabase"..?
   		
   		//root.put("productList", list);
   		
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
		// TODO Auto-generated method stub
		//String viewCart = request.getParameter("viewCart");
		String remove = request.getParameter("remove");
		if (request.getParameter("backToHome") != null){
			sendHomePage(request, response, user);
		}
		if (request.getParameter("viewCart") != null){
			viewCart(request, response);
		}
		else if (user.getID() == -1){
			System.out.println("Guests can't access the cart");
			sendHomePage(request, response, user);
		}
		
		else if (request.getParameter("addToCart") != null){
		//String addToCart = request.getParameter("addToCart");
		//System.out.println("add to cart parameter:::: " + addToCart);
		String[] ids = request.getParameterValues("id[]");
		
		String id = "";
		for (String id1: ids)
			if(id1 != null)
			id = id1;
		
		//List<Product> list = DatabaseProducts.getProducts();
		Product p = DatabaseProducts.getProduct(Integer.parseInt(id));
		
		User u = DatabaseUsers.getUser(user.getUsername());
		
		System.out.println("Add " + p.getName() + " to shopping cart for user id " + u.getID());
		//sendHomePage(request, response, u);
		 
		DatabaseProducts.addItemToCart(p, u.getID());
		
		
		
		/* re send the home page */
		sendHomePage(request, response, user);
		}else if (remove != null){
			System.out.println(remove);
			String[] ids = request.getParameterValues("id[]");
			
			String id = "";
			for (String id1: ids)
				if (id1 != null)
				id = id1;
			
			//List<Product> list = DatabaseProducts.getProducts();
			Product p = DatabaseProducts.getProductFromCart(user.getID(),Integer.parseInt(id));
			
			User u = DatabaseUsers.getUser(user.getUsername());
			
			System.out.println("Remove " + p.getName() + " from shopping cart for user " + u.getFirstName());
			
			if (DatabaseProducts.removeProductFromCart(user.getID(), Integer.parseInt(id)))
				System.out.println("Successfully removed");;
				
			viewCart(request, response);
		}else if (request.getParameter("checkout") != null){
			response.getWriter().println("Re-directing to purchasing site (we aren't secure enough to handle payment)");
		
		}else if (request.getParameter("logout") != null){
			
			user = null;
			
		 	Template template = null;
		   	DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		   	SimpleHash root = new SimpleHash(df.build());
		try{
	   		String templateName = "index.ftl";
	   		template = cfg.getTemplate(templateName);
	   		response.setContentType("text/html");
	   		PrintWriter out = response.getWriter();

	   		root.put("valid", true);
	   		template.process(root, out);
	   		

	   	}
	   	catch (Exception e){
	   		e.printStackTrace();
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
