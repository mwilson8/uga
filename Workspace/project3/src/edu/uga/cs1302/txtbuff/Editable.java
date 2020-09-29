package edu.uga.cs1302.txtbuff;

public interface Editable {
	
	public void append(String fragment);
	
	public void insert(int index, String fragment) 
			throws TextLineIndexOutOfBoundsException;

	public void replace(int start, int end, String fragment) 
			throws TextLineIndexOutOfBoundsException;
	
	
}
