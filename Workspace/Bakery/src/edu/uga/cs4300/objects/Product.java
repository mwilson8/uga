package edu.uga.cs4300.objects;

public abstract class Product {

	String name;
	double price;
	String description;
	int ID;
	String SRC;

	public abstract String getName();
	public abstract double getPrice();
	public abstract String getDescription();
	public abstract String getType();
	public abstract int getID();
	public abstract String getSRC();
	
	public abstract void setName(String name);
	public abstract void setPrice(double price);
	public abstract void setDescription(String description);
	public abstract void setID(int ID);
}
