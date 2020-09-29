package edu.uga.cs4300.ThoughtsBank;

import java.sql.Timestamp;

public class Thought {

	private String thought;
	private Timestamp timestamp;
	
	public Thought() {
		this("my mind is wondering");
	}
	
	public Thought(String thought){
		this.thought = thought;
		timestamp = new Timestamp(System.currentTimeMillis());
	}


	public String getThought() {
		return thought;
	}


	public void setThought(String thought) {
		this.thought = thought;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}
	
	
	
}
