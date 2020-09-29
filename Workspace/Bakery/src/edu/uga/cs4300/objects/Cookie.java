package edu.uga.cs4300.objects;

public class Cookie extends Product {

	private String name, description;
	private final String type = "cookie";
	private double price;
	int ID;
	private String SRC = "https://www.getupandgobaked.com/wp-content/uploads/2015/03/smart-cookie-pic-copy.jpg";
	
	public Cookie(String name, String description, double price, int ID, String SRC) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.ID = ID;
		if(SRC != null)
		this.SRC = SRC;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getType(){
		return type;
	}
	
	public int getID(){
		return ID;
	}
	
	public void setID(int ID){
		this.ID = ID;
	}
	
	public String getSRC(){
		return SRC;
	}
}
