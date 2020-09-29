package edu.uga.cs4300.MovieDatabase;

public class Actor {
	private String fname, lname;
	public Actor(String fname, String lname) {
		this.fname = fname;
		this.lname = lname;
	}
	
	public String firstName(){
		return fname;
	}
	
	public String lastName(){
		return lname;
	}
	
	public String toString(){
		
		return fname + " " + lname;
	}

}
