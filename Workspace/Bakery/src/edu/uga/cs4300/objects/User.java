package edu.uga.cs4300.objects;

/*
import java.util.ArrayList;
import java.util.List;
*/

public class User {

	private String 
					firstName,
					lastName,
					username,
					password,
					email;
	private int ID;
	private boolean guest = false;
	//private List<Product> cart;
	
	public User(){
		guest = true;
	}
	
	public User(String firstName, String lastName, String username, 
			String password, String email, int ID) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.ID = ID;
	}
	
	public User(String firstName, String lastName, String username, 
			String password, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public int getID(){
		return ID;
	}

	public boolean getGuest() {
		return guest;
	}

	
	
	
	//these are all method that could be useful if we add a shopping cart 
	
	/*
	public List<Product> getCart(){
		return cart;
	}
	
	public void updateCart (List<Product> newCart){
		this.cart = newCart;
	}
	
	public void addItemToCart(Product p){
		cart.add(p);
	}
	
	public void removeItemFromCart(int index){
		cart.remove(index);
	}
	
	public void clearCart(){
		cart.clear();
	}
	*/	
	
	
	
}
