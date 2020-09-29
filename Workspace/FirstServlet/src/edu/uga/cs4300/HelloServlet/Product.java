package edu.uga.cs4300.HelloServlet;

public class Product {

	private String name;
	private String url;
	
	
	public Product(String name, String url) {
		this.name = name;
		this.url = url;
		
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
