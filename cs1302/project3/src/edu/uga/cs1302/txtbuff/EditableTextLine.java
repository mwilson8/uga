package edu.uga.cs1302.txtbuff;

public class EditableTextLine extends TextLine implements Editable{
	
	public EditableTextLine()
	{
		super();
	}
	
	public EditableTextLine(String line)
	{
		super(line);
	}
	
	/**
	 * Adds a String fragment to the end of this buffer
	 */
	public void append(String fragment)
	{
		char[] temp;
		if (this.capacity() < fragment.length())
		{
			temp = new char[((cArray.length/super.DEFAULT_SIZE) + 1) * super.DEFAULT_SIZE];
			int i;
			for (i = 0; i < cArray.length; i++)
			{
				temp[i] = cArray[i];
			}
			for(int j = 0; i < fragment.length(); i++, j++)
			{
				temp[i] = fragment.charAt(j);
			}
		}
		
		else
		{
			temp = new char [cArray.length];
			int i;
			for (i = 0; i < cArray.length; i++)
			{
				temp[i] = cArray[i];
			}
			for(int j = 0; i < fragment.length(); i++, j++)
			{
				temp[i] = fragment.charAt(j);
			}
		}

		this.cArray = temp;
	}
	
	/**
	 * Inserts a fragment between at a given index
	 */
	public void insert(int index, String fragment) throws TextLineIndexOutOfBoundsException
	{
		char [] temp;
		if (index > this.length() || index < 0)
		{
			throw new TextLineIndexOutOfBoundsException(index);
		}
		
		if (fragment.length() > this.capacity())
		{
			temp = new char [((cArray.length/super.DEFAULT_SIZE)+1) * super.DEFAULT_SIZE];
		}
		else
		{
			temp = new char [cArray.length];
		}
		
		int i = 0;
		for (; i < temp.length; i++)
		{
			if (i == index)
			{
				for (int j = 0; j < fragment.length()-1; j++, i++)
				{
				temp[i] = fragment.charAt(j);
				}
			}
			temp[i] = cArray[i];
		}
		
		cArray = temp;
	}
	
	/**
	 * Inserts a fragment between two given indices
	 */
	public void replace(int start, int end, String fragment) 
			throws TextLineIndexOutOfBoundsException
	{
		char [] temp;
		if (start > this.cArray.length || start < 0)
		{
			throw new TextLineIndexOutOfBoundsException (start);
		}
		if (end > this.cArray.length || end < 0)
		{
			throw new TextLineIndexOutOfBoundsException (end);	
		}
		
		if (fragment.length() > this.capacity())
		{
			temp = new char [((this.cArray.length/super.DEFAULT_SIZE)+1) * super.DEFAULT_SIZE];
		}
		else
		{
			temp = new char [this.cArray.length];
		}
		
		
		for (int i = 0, k=0; i < temp.length-1; i++, k++)
		{
			if (i == start)
			{
				for (int j = 0; j < fragment.length(); j++, i++)
				{
					temp[i] = fragment.charAt(j);
				}
			k=end;
			}
			
			temp[i] = this.cArray[k];
		}
		
		this.cArray = temp;
		
	}
	
	/**
	 * Converts contents of the textline to atring format
	 */
	public String toString()
	{
		String s ="";
		
		for (int i = 0; i < cArray.length; i++)
		{
			s += cArray[i];
		}
		
		return s;
	}

}
