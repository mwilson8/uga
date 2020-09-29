/*
* [Stat].java (version 2)
* 
* 	Author: 		 [Mitch Wilson]
* 	
* 	Date Created: 	 [Apr 12 2016] 
* 	Last Updated: 	 [Apr 19 2016]
* 
*	Purpose: This program is designed as a further step with OOP,
*				and a refresher on reference types and their modi-
*				fication. 
*				
*  *
* Statement of Academic Honesty: *
* The following code represents my own work. I have neither
* received nor given inappropriate assistance. I have not copied
* or modified code from any source other than the course webpage
* or the course textbook. I recognize that any unauthorized
* assistance or plagiarism will be handled in accordance with
* the University of Georgia's Academic Honesty Policy and the
* policies of this course. I recognize that my work is based
* on an assignment created by the Department of Computer
* Science at the University of Georgia. Any publishing
* or posting of source code for this project is strictly
* prohibited unless you have written consent from the Department
* of Computer Science at the University of Georgia. */

import java.util.Scanner;
public class Stat {

	
	private double [] data = null;
	
	
	public Stat()
	{
	
		this.reset();
	}
	
	public Stat(double [] d)
	{
		if (d != null)
		{	
			double [] temp = new double [d.length];
			
			for (int i = 0; i<d.length; i++)
				temp[i] = d[i];	
			
			this.data = temp;
		}
		
		else
			this.reset();
	}
	
	public Stat(float [] f)
	{
		if (f != null)
		{
			double [] temp = new double [f.length];
			
			for (int i = 0; i < f.length; i++)
				temp[i] = (double) f[i];
			
			this.data = temp;
		}
		
		else
			this.reset();
	}
	
	public Stat(int [] i)
		{
			if (i != null)
			{
				double [] temp = new double [i.length];
				
				for (int x = 0; x < i.length; x++)
					temp[x] = (double) i[x];
			
				
				this.data = temp;
			}
			
			else
				this.reset();
		}
		
	public Stat(long [] lo)
	{
		if (lo != null)
		{
			double [] temp = new double [lo.length];
						
			for (int i = 0; i < lo.length; i++)
				temp[i] = (double) lo[i];
								
			this.data = temp;
		}
		
		else
			this.reset();
	}
			
		public void setData(int[] i)
		{
			
			if (i == null){
				this.reset();
				return;
			}
			
			double [] temp = new double [i.length];
			
			for (int x = 0; x < i.length; x++)
				temp [x] = (double) i[x];
			
			this.data = temp;
			
		}
		
		public void setData(long[] lo)
		{
			if (lo == null){
				this.reset();
				return;
			}
			
			double [] temp = new double [lo.length];
			
			for (int i = 0; i < lo.length; i++)
				temp [i] = (double) lo[i];
			
			this.data = temp;
		}
	
		public void setData(float[] f)
		{
			if (f == null){
				this.reset();
				return;
			}
			
			double [] temp = new double [f.length];
			
			for (int i = 0; i < f.length; i++)
				temp [i] = (double) f[i];
			
			this.data = temp;
		}
		
		public void setData(double[] d)
		{
			if (d == null){
				this.reset();
				return;
			}
			
			double [] temp = new double [d.length];
			
			for (int i = 0; i < d.length; i++)
				temp [i] = d [i];
			
			this.data = temp;
		}

	public double[] getData()
	{
		double [] temp = new double [data.length];
		
		for (int i = 0; i < data.length; i++)
			temp [i] = data[i];
		
		return temp;		
	}
	
	public boolean equals(Stat s)
	{
		if (s == null || s.isEmpty())
			return false;
		
		for (int i = 0; i < this.data.length; i++)
		{
			if (s.data[i] != this.data[i])
				return false;
		}
		
		return true;
		
	}
		
		public void reset()
		{
			data = new double[] {0.0};
		}
		
		public void append (double [] d)
		{
			if (d != null)
			{
				int count = 0;
				double [] temp = new double [this.data.length + d.length];
				
				//both "i"s are local and control for the input array
				//count keeps the place in the new array
				
				for (int i = 0; i < this.data.length; i++, count++)
					temp[count] = this.data[i];
				
				for (int i = 0; i < d.length; i++, count++)
					temp[count] = d[i];
						
				this.data = temp;
			}
		}
		
		public void append (int [] i)
		{
			if (i != null)
			{
				int count = 0;
				double [] temp = new double [this.data.length + i.length];
				
				//both "i"s are local and control for the input array
				//count keeps the place in the new array
				
				for (int x = 0; x < this.data.length; x++, count++)
					temp[count] = this.data[x];
				
				for (int x = 0; x < i.length; x++, count++)
					temp[count] = i[x];
				
				this.data = temp;
			}
		}
	
		public void append (long [] lo)
		{
			if (lo != null)
			{
				int count = 0;
				double [] temp = new double [this.data.length + lo.length];
				
				//both "i"s are local and control for the input array
				//count keeps the place in the new array
				
				for (int i = 0; i < this.data.length; i++, count++)
					temp[count] = this.data[i];
				
				for (int i = 0; i < lo.length; i++, count++)
					temp[count] = lo[i];
				
				this.data = temp;
			}
		}
		
		public void append (float [] f)
	{
		if (f != null)
		{
			int count = 0;
			double [] temp = new double [this.data.length + f.length];
			
			//both "i"s are local and control for the input array
			//count keeps the place in the new array
			
			for (int i = 0; i < this.data.length; i++, count++)
				temp[count] = this.data[i];
			
			for (int i = 0; i < f.length; i++, count++)
				temp[count] = f[i];
			
			this.data = temp;
		}
	}
	
	public boolean isEmpty()
	{
		return this.data.length == 0;
	}
	
	public String toString()
	{
		String s = "[";
		
		for (int i = 0; i < this.data.length; i++){
			if (i != 0)
			s += " "; 
			
			s += this.data[i];
			
			if (i != data.length-1)
				s+=",";
		}
		
		s += "]";
		
		return s;
	}
	
	public double min()
	{
		
		if (this.isEmpty())
			return Double.NaN;
		
		double min = this.data[0];
		
		for (int i = 1; i < this.data.length; i++)
		{
			if (this.data[i] < min)
				min = this.data[i];
		}
		
		return min;
	}
	
	public double max()
	{
		
		if (this.isEmpty())
			return Double.NaN;
		
		double max = this.data[0];
		
		for (int i = 1; i < this.data.length; i++)
		{
			if (this.data[i] > max)
				max = this.data[i];
		}
		
		return max;
	}
	
	public double average()
	{
		
		if (this.isEmpty())
			return Double.NaN;
		
		int count = 0;
		double sum = 0.0;
		
		for (count = 0; count < this.data.length; count ++)
			sum += this.data[count];	
		
		double average = sum / count;
		return average;
	}
	
	public double mode()
	{
	
		if (this.isEmpty())
			return Double.NaN;
		
		int maxCount = 0;
		double mode = 0.0;

    
         

	    for (int i = 0; i < this.data.length; i ++) 
	    {	
	        int count = 0;
	        
	        for (int j = 0; j < this.data.length; j ++) 
	        {
	            if (this.data[j] == this.data[i]) 
	            	count ++;
	        }

	        if (count > maxCount)
	        {
	            maxCount = count;
	            mode = this.data[i];
	        }
	       
	    }

	    return mode;
	}
	
	public double variance() 
	{
		if (this.isEmpty())
			return Double.NaN;
		
		double sum = 0.0;
		for (int i = 0; i < this.data.length; i++)
		
			sum += Math.pow((this.average() - this.data[i]), 2);
		
		
		return (sum/this.data.length);
	}
	
	public double standardDeviation()
	{
		if (this.isEmpty())
			return Double.NaN;
		
		return Math.sqrt(this.variance());
	}
		
	
}
