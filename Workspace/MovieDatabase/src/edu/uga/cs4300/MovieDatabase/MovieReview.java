package edu.uga.cs4300.MovieDatabase;

public class MovieReview {

	private String review;
	private int id;
	
	
	public MovieReview(String review, int id) {
		this.review = review;
		this.id = id;
	}
	
	
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
