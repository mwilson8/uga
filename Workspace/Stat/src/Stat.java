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
	
		data = new double [] {0.0};
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
		
		else{
		boolean isEqual = false;
		
		for (int i = 0; i < this.data.length && isEqual; i++)
		{
			if (s.data[i] == this.data[i])
				isEqual = true;
			
			else
				return false;
		}
		
		return isEqual;
		}
	}
		
		public void reset()
		{
			double [] temp = new double [0];
			
			this.data = temp;
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
		
		for (int i = 0; i < this.data.length; i++)
			s += " " + this.data[i] + ",";
		
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

        
        /*
         *  both for loops go over the array
         *  loop "j" compares the entire array to the first value (held by i)
         *  if the value is the same, increase "count"
         *  if count is larger than the previous maxCount, set the new count to maxCount
         *  and set the data point to the new mode
         *  when both loops have completed, return the mode
         *  
         *  /*
	    	 * I can't for the life of me make the multi-modal set
	    	 * to return a NaN, but this code returns the mode
	    	 */
         

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
	
	
	
	
	public static void main(String[] args)
	{
		
		do {
		System.out.println("What test to run? (1-6)");
		Scanner keyboard = new Scanner(System.in);
		int test = keyboard.nextInt();
		
		/*
			if (test == 1)
			{
				double[] data = {-5.3, 2.5, 88.9, 0, 0.0, 28, 16.5, 88.9, 109.5, -90, 88.9}; double[] data2 = {100.34, 50.01, 50.01, -8};
				Stat stat1 = new Stat();
				System.out.println("stat1 data = " + stat1.toString()); stat1 = new Stat(data);
				System.out.println("stat1 has been altered."); System.out.println("stat1 data = " + stat1.toString());
				System.out.println("stat1 min = " + stat1.min()); System.out.println("stat1 max = " + stat1.max()); System.out.println("stat1 average = " + stat1.average()); System.out.println("stat1 mode = " + stat1.mode() + "\n");
				Stat stat2 = new Stat(); stat2.setData(data2);
				Stat stat3 = new Stat(stat1.getData());
				System.out.println("stat2 data = " + stat2.toString());
				System.out.println("stat3 data = " + stat3.toString());
				System.out.println();
				System.out.println("stat1 is equal to stat2 using \"equals()\"? " +
				stat1.equals(stat2));
				System.out.println("stat1 is equal to stat3 using \"equals()\"? " +
				stat1.equals(stat3));
				System.out.println("stat1 is equal to stat3 using \"==\"? " + (stat1 == stat3));
				System.out.println("stat 2 mode should be 50.01: "+ stat2.mode());
				System.out.println("stat 3 mode should be 88.9: "+ stat3.mode());
				System.out.println("\n");
			}
			
			else if (test == 2)
			{	
				double[] data = {10.0, 20.0, 30.0}; Stat stat1 = new Stat(data);
				data[0] = 100.0; data[1] = 200.0; data[2] = 300.0;
				Stat stat2 = new Stat(data);
				System.out.println("stat1 data = " + stat1.toString());
				System.out.println("stat2 data = "  + stat2.toString());
				System.out.println("The two arrays should be different");
			}		
			
			else if (test == 3)
			{
				double[] data1 = {10.0, 20.0, 30.0}; Stat stat1 = new Stat(data1);
				double[] data2 = stat1.getData();
				System.out.println("The arrays are identical: " + (data1 == data2));
			}
			
			else if (test == 4)
			{
				double[] data1 = {10.0, 20.0, 30.0}; Stat stat1 = new Stat(); stat1.setData(data1);
				Stat stat2 = new Stat(data1); double[] data2 = stat1.getData();
				System.out.println("The arrays are identical: " + (data1 == data2)); System.out.println("stat2 equals stat1: " +
				stat2.equals(stat1));
				System.out.println("stat1 equals stat2: " + stat2.equals(stat1));
			}
			
			else if (test == 5)
			{
				Stat stat1 = new Stat();
				System.out.println("stat1 data = " + stat1.toString()); 
				System.out.println("stat1 min = " + stat1.min()); 
				System.out.println("stat1 max = " + stat1.max()); 
				System.out.println("stat1 average = " + stat1.average());
				System.out.println("stat1 mode = " + stat1.mode());
				System.out.println("stat1 data = " + stat1.toString());
			}
			
			else if (test == 6)
			{
				double[] data = {1,2,2,3,3,4}; Stat stat1 = new Stat(data);
				System.out.println("stat1 data = " + stat1.toString());
				System.out.println("stat1 min = " + stat1.min()); 
				System.out.println("stat1 max = " + stat1.max()); 
				System.out.println("stat1 average = " + stat1.average()); 
				System.out.println("stat1 mode = " + stat1.mode()); 
				System.out.println("stat1 data = " + stat1.toString());

			}
			*/
		
			if (test == 1)
			{
				double[] data1 = {};
				Stat stat1 = new Stat(data1);
				System.out.println("stat1 data = " + stat1.toString()); 
				System.out.println("stat1 min = " + stat1.min());
				System.out.println("stat1 max = " + stat1.max());
				System.out.println("stat1 average = " + stat1.average()); 
				System.out.println("stat1 mode = " + stat1.mode());
				System.out.println("stat1 variance = " + stat1.variance()); 
				System.out.println("stat1 standard deviation = " + stat1.standardDeviation()); 
				System.out.println("stat1 is empty = " + stat1.isEmpty() + "\n");
			}
			
			else if (test == 2)
			{
				double[] data1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
				Stat stat1 = new Stat(data1);
				System.out.println("stat1 data = " + stat1.toString()); 
				System.out.println("stat1 min = " + stat1.min());
				System.out.println("stat1 max = " + stat1.max());
				System.out.println("stat1 average = " + stat1.average()); 
				System.out.println("stat1 mode = " + stat1.mode());
				System.out.println("stat1 variance = " + stat1.variance()); 
				System.out.println("stat1 standard deviation = " + stat1.standardDeviation()); 
				System.out.println("stat1 is empty = " + stat1.isEmpty() + "\n");
				stat1.reset();
				System.out.println("stat1 data = " + stat1.toString()); 
				System.out.println("stat1 min = " + stat1.min());
				System.out.println("stat1 max = " + stat1.max());
				System.out.println("stat1 average = " + stat1.average()); 
				System.out.println("stat1 mode = " + stat1.mode());
				System.out.println("stat1 variance = " + stat1.variance()); 
				System.out.println("stat1 standard deviation = " + stat1.standardDeviation()); 
				System.out.println("stat1 is empty = " + stat1.isEmpty() + "\n");
			}
			
			else if (test == 3)
			{
				float[] data1 = {10.0F,10.0F};
				Stat stat1 = new Stat(data1);
				System.out.println("stat1 data = " + stat1.toString());
				System.out.println("stat1 min = " + stat1.min());
				System.out.println("stat1 max = " + stat1.max());
				System.out.println("stat1 average = " + stat1.average());
				System.out.println("stat1 mode = " + stat1.mode());
				System.out.println("stat1 variance = " + stat1.variance());
				System.out.println("stat1 standard deviation = " + stat1.standardDeviation() + "\n");
				long[] data2 = {80L, 60L};
				stat1.append(data2);
				System.out.println("stat1 data = " + stat1.toString());
				System.out.println("stat1 min = " + stat1.min());
				System.out.println("stat1 max = " + stat1.max());
				System.out.println("stat1 average = " + stat1.average());
				System.out.println("stat1 mode = " + stat1.mode());
				System.out.println("stat1 variance = " + stat1.variance());
				System.out.println("stat1 standard deviation = " + stat1.standardDeviation());
			}
			
			else if (test == 4)
			{
				double[] data = {-5.3, 2.5, 88.9, 0, 0.0, 28, 16.5, 88.9, 109.5, -90, 88.9}; Stat stat1 = new Stat();
				System.out.println("stat1 data = " + stat1.toString()); 
				stat1.append(data);
				System.out.println("stat1 has been altered.");
				System.out.println("stat1 data = " + stat1.toString());
				System.out.println("stat1 min = " + stat1.min());
				System.out.println("stat1 max = " + stat1.max());
				System.out.println("stat1 average = " + stat1.average());
				System.out.println("stat1 mode = " + stat1.mode());
				System.out.println("stat1 variance = " + stat1.variance());
				System.out.println("stat1 standard deviation = " + stat1.standardDeviation() + "\n");
			}
			
			else if (test == 5)
			{
				double[] data1 = {50.0, 60.0}; 
				float[] data2 = {70.0F, 80.0F}; 
				int[] data3 = {90, 100}; 
				long[] data4 = {100L, 110L};
				
				 Stat stat1 = new Stat(); 
				 
				 System.out.println("stat1 data = " + stat1.toString());
				 stat1.setData(data1);
				 System.out.println("stat1 data = " + stat1.toString());
				 stat1.setData(data2);
				 System.out.println("stat1 data = " + stat1.toString());
				 stat1.setData(data3);
				 System.out.println("stat1 data = " + stat1.toString());
				 stat1.setData(data4);
				 System.out.println("stat1 data = " + stat1.toString());
				 data1 = null;
				 stat1.setData(data1);
				 System.out.println("stat1 data = " + stat1.toString());

			}
			
			else if (test == 6)
			{
				double[] data1 = {50.0, 60.0}; 
				float[] data2 = {70.0F, 80.0F}; 
				int[] data3 = {90, 100};
				long[] data4 = {100L, 110L};
				Stat stat1 = new Stat(); 
				
				System.out.println("stat1 data = "+ stat1.toString());
				stat1.append(data1); 
				System.out.println("stat1 data = "+ stat1.toString());
				stat1.append(data2);
				System.out.println("stat1 data = "+ stat1.toString());
				stat1.append(data3); 
				System.out.println("stat1 data = "+ stat1.toString());
				stat1.append(data4); 
				System.out.println("stat1 data = "+ stat1.toString());
				data1 = null;
				stat1.append(data1);
				System.out.println("stat1 data = " + stat1.toString());
				System.out.println("stat1 min = " + stat1.min());
				System.out.println("stat1 max = " + stat1.max());
				System.out.println("stat1 average = " + stat1.average());
				System.out.println("stat1 mode = " + stat1.mode());
				System.out.println("stat1 variance = " + stat1.variance());
				System.out.println("stat1 standard deviation = " + stat1.standardDeviation() + "\n");
			}
			
			else if (test == 7)
			{
				double [] data1 = {10,10};
				int[] data2 = {10,10};
				Stat stat1 = new Stat(data1);
				Stat stat2 = new Stat(data2);
				Stat stat3 = new Stat();
				Stat stat4 = null;
				System.out.println("stat1 data = "+ stat1.toString());
				System.out.println("stat2 data = "+ stat2.toString());
				System.out.println("stat2 data = "+ stat2.toString());
				System.out.println("stat1 equals stat2 = "+ stat1.equals(stat2));
				System.out.println("stat1 equals stat3 = "+ stat1.equals(stat3));
				System.out.println("stat1 equals stat4 = "+ stat1.equals(stat4));
			}
			
			else if (test == 8)
			{
				double[] data1 = {}; 
				double[] data2 = { 25 }; 
				float[] data3 = {}; 
				float[] data4 = { 25 }; 
				int[] data5 = {};
				int[] data6 = { 50 }; 
				long[] data7 = {}; 
				long[] data8 = { 12 };
				Stat stat1 = new Stat(); 
				stat1.append(data1); 
				stat1.append(data2); 
				stat1.append(data3); 
				stat1.append(data4); 
				stat1.append(data5); 
				stat1.append(data6); 
				stat1.append(data7); 
				stat1.append(data8); 
				data1 = null; 
				stat1.append(data1);

				System.out.println("stat1 data = " + stat1.toString());
				System.out.println("stat1 min = " + stat1.min());
				System.out.println("stat1 max = " + stat1.max());
				System.out.println("stat1 average = " + stat1.average());
				System.out.println("stat1 mode = " + stat1.mode());
				System.out.println("stat1 variance = " + stat1.variance());
				System.out.println("stat1 standard deviation = " + stat1.standardDeviation() + "\n");

			}
			
	} while (true);
		
	}

	
	
	
}
