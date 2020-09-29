package edu.uga.cs1302.txtbuff;

public class TextLine {
	
	public final int DEFAULT_SIZE = 80;
	protected char[] cArray;

	/**
	 * Creates a new textline with default size
	 */
	public TextLine()
	{
		cArray = new char [DEFAULT_SIZE];
	}
	/**
	 * Creates a new textline with default size, filled with @param line
	 */
	public TextLine(String line)
	{
		if (line.length() <= DEFAULT_SIZE)
		{
			cArray = new char [DEFAULT_SIZE];
		}
		
		else if (line.length() % DEFAULT_SIZE == 0)
		{
			cArray = new char [line.length()];
		}
		
		else
		{
			cArray = new char [((line.length()/DEFAULT_SIZE) + 1) * DEFAULT_SIZE];
		}
		
		for (int i = 0; i < line.length(); i++)
		{
			cArray[i] = line.charAt(i);
		}
		
	}
	
	/**
	 * Returns the index of first occurence of the fragment
	 *
	 */
	public int indexOf(String fragment)
	{
		return this.indexOf(fragment, 0);
	}
	
	/**
	 * returns the first occurence of the fragment, starting at the given index
	 */
	public int indexOf(String fragment, int fromIndex)
	{
		int firstOccurence = fromIndex;
		boolean containsEntireFragment = true;
		for (int i = fromIndex; i < cArray.length; i++)
		{
			if (this.cArray[i] == fragment.charAt(0))
			{
				firstOccurence = i;
				
				for (int j = i, k= 0; k < fragment.length() && containsEntireFragment; j++, k++)
				{
					if (cArray[j] == fragment.charAt(k))
						containsEntireFragment = true;
					
					else
						containsEntireFragment = false;
				}
				
				if (containsEntireFragment)
					break;
			}
		}
		if (containsEntireFragment)
			return firstOccurence;
				
		else
			return -1;
	}
	
	/**
	 * returns the length of the text line
	 * @return
	 */
	public int length()
	{
		int length = 0;
		for (int i = 0; i < this.cArray.length; i++)
		{
			if (Character.isAlphabetic(cArray[i]) || Character.isWhitespace(cArray[i]))
				length ++;
		}
		return length;
	}
	
	/**
	 * returns the free space of the buffer
	 * 
	 */
	public int capacity()
	{
		return (this.cArray.length - this.length());
	}
	
	/**
	 * returns if 2 text lines have the same characters
	 */
	public boolean equals(Object anObject)
	{
		boolean isEqual = true;
		if (anObject instanceof TextLine)
		{
			
			for (int i =0; i < this.cArray.length && isEqual; i++)
			{
				if (((TextLine) anObject).cArray[i] == this.cArray[i])
					isEqual = true;
				
				else
					return false;
			}
			
		}
		
		return isEqual;
	}
	
	/**
	 * converts contents of the text line to String format
	 */
	public String toString()
	{
		String s="";
		
		for (int i = 0; i < this.cArray.length; i++)
		{
			s+= this.cArray[i];
		}
		
		return s;
	}

}
