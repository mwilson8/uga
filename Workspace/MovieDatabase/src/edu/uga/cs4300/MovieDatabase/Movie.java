package edu.uga.cs4300.MovieDatabase;

import java.util.ArrayList;
import java.util.List;

public class Movie {

	private int id;
	private String name;
	private int year;
	private int rank;
	private List<String> reviews;
	
	
	
	public Movie(int id, String name, int year, int rank) {
		super();
		this.id = id;
		this.name = name;
		this.year = year;
		this.rank = rank;
		reviews = new ArrayList<String>();
	}
	

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public List<String> getReviews(){
		return reviews;
	}
	public void addReview(String review){
		reviews.add(review);
	}
	public void deleteReview(int pos){
		reviews.remove(pos);
	}
	public void clearReviews(){
		reviews.clear();
	}
	
	
}
