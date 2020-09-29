/*
* [Lamp].java
* 
* 	Author: 		 [Mitch Wilson]
* 	
* 	Submission Date: [Apr 12 2016] 
* 	Last Updated: 	 [Apr 12 2016]
* 
*	Purpose: This program is designed to accompany a driver program
*				as more practice for us in OOP and the implemetation
*				of classes and methods
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

public class Lamp {

	/*
	 * Instance variables and methods go here. Your methods
	 * should work with the method invocations in 
	 * InteractiveFictionTesting.java
	 */
	
	private boolean isLit;

	
	/**
	 * 
	 * @param isLit
	 */
	public void setIsLit (boolean isLit)
	{
		this.isLit = isLit;
	}
	
	
	/**
	 * 
	 * @return if the lamp is lit
	 */
	public boolean getIsLit()
	{
		return this.isLit;
	}
	

	
	
}

