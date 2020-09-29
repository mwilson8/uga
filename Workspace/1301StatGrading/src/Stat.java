/*
* 

[CSCI 1301].java
* Author:  [Aaron Xiao] 
* Submission Date:  [04/13/18]
*
* Purpose: The purpose of this lab is to have us pracTice writing methods for a class without 
* being given the skeleton code as well as to provide practice with how to code to avoid privacy
* leaks. This lab focuses on arrays and come equipped with various methods that had us perform
* Mathematical operations using the contents of the arrays, comparing arrays, and printing the 
* contents of the arrays to the user in a formatted fashion.
*
* Statement of Academic Honesty:
*
* The following code represents my own work. I have neither
* received nor given inappropriate assistance. I have not 
copied
* or modified code from any source other than the course webpage
* or the course textbook. I recognize that any unauthorized
* assistance or plagiarism will be handled in accordance with
* the University of Georgia's Academic Honesty Policy and the
* policies of this course. I recognize that my work is based
* on an assignment created by the Department of Computer
* Science at the University of Georgia. Any publishing 
* or posting of source code for this project is strictly
* prohibited unless you h
ave written consent from the Department 
* of Computer Science at the University of Georgia.  
*/
public class Stat {

	private double[] data;
	
	public Stat() {
		
		double arr[]= {0.0};
	}
	public Stat(double[] d) {
		
		double [] s =new double[d.length];
		for(int i=0;i<d.length;i++)
		{
			s[i]=d[i];
		}
	data=s;
	}
	public double[] getData() {
		double[] array=new double[data.length];
		for(int i=0;i<array.length;i++)
		{
			array[i]=data[i];
		}
		return array;
	
	}
	public void setData(double[] d) {
		double [] s =new double[d.length];
		for(int i=0;i<d.length;i++)
		{
			s[i]=d[i];
		}
		data=s;
		//double []setD=data;
		
	}
	public boolean equals(Stat s) {
		boolean equal =true;
		for(int i=0;i<this.getData().length;i++)
		{
			if(this.getData()[i]!=s.getData()[i])
			{
				equal=false;
			}
		}
	
		return equal;
	}
	public String toString(){
		String s = "[";
		double [] array= this.getData();
		for(int i =0;i<array.length;i++)
		{
				s+=array[i]+", ";
		}
		return s.substring(0,s.length()-2)+"]";
	}
	public double min() {
		double [] array= this.getData();
		double min = array[0];
		for(int i =1;i<array.length;i++)
		{
			if(array[i]<min)
			{
				min=array[i];
			}
		}
		
		return min;
	}
	public double max() {
		double [] array= this.getData();
		double max = array[0];
		for(int i =1;i<array.length;i++)
		{
			if(array[i]>max)
			{
				max=array[i];
			}
		}
		return max;
	}
	public double average() {
		double [] array= this.getData();
		double sum=0;
		for(int i=0;i<array.length;i++)
		{
			sum+=array[i];
		}
		double average=sum/array.length;
		return average;
	}
	public void append(double [] d) {
		double [] newArray= new double[d.length+data.length];
		if(!(d.equals(null)))
		{
			for(int i=0; i<(this.getData()).length;i++)
			{
				newArray[i]=this.getData()[i];
			}
			for(int i=this.data.length;i<newArray.length;i++ )
			{
				newArray[i]=d[i-data.length];
			}
		}
		data=newArray;
	}
	public double variance() {
		double [] arr= this.getData();
		double sum=0, total=0;
		if(!(data.equals(null)))
		{
		for(int i =0; i<arr.length;i++)
		{
			sum+=arr[i];
		}
		double mean=sum/arr.length;
		for(int i=0;i<arr.length;i++)
		{
			total+=Math.pow(arr[i]-mean, 2.0);
		}
		double variance = total/arr.length;
		return variance;
	}
		else
		{
			return Double.NaN;
		}
	}
	public double standardDeviation() {
		if(!(data.equals(null)))
		{
			return Math.sqrt(this.variance());
		}
		else
		{
			return Double.NaN;
		}
	}
	public double mode() {
		double mode=data[0];
		int temp=0;
		for(int i=0;i<data.length;i++)
		{
			int count=0;
			
			for(int n=0;n<data.length;n++)
			{
				if(data[i]==data[n])
				{
					count++;
				}
			}
			if(count>temp)
			{
				temp=count;
				mode=data[i];
			}
			else if(temp==count)
			{
				mode = Double.NaN;
			}
			
		}
		return mode;
	}
}
