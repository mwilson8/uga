package edu.uga.cs1302.txtbuff;

public class TextLineIndexOutOfBoundsException extends IndexOutOfBoundsException{
	
	public TextLineIndexOutOfBoundsException ()
	{
		super();
	}
	
	public TextLineIndexOutOfBoundsException (String errMsg)
	{
		super(errMsg);
	}
	
	public TextLineIndexOutOfBoundsException (int index)
	{
		super("TextLine index out of range " + index);
	}

}
