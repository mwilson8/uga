/*
* [GraphicsTester].java
* 
* 	Author: 		 [Mitch Wilson]
* 	
* 	Submission Date: [Apr 8 2016] 
* 
* 
*	Purpose: This program is the driver for the first time using
*				graphics and more experience with classes and methods
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

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Scanner;

public class GraphicsTester extends JPanel {
	
    public void paintComponent(Graphics g) {
        //This is where you call your MyGraphics methods to actually paint them...	

    	 MyGraphics art = new MyGraphics();

	 System.out.println("What do you want to draw?");
	 System.out.println("\t0. A line");
         System.out.println("\t1. A house");//house
         System.out.println("\t2. A Smile");//smile
         System.out.println("\t3. A flower");
         
           
         Scanner keyboard = new Scanner(System.in);
         int userChoice = keyboard.nextInt(); //get user input

         if(userChoice == 0)
       	 	art.drawLine(g);
         else if(userChoice==1)		
       	 	art.drawHouse(g);
         else if(userChoice == 2)
       	 	art.drawSmile(g);
         else if(userChoice == 3)
        	art.drawFlower(g);
         else if(userChoice <0)
       	 	System.exit(0);		
         else
       	 	System.out.println("Error");  
    }

    public static void main(String[] args) {
   
        JFrame frame = new JFrame(); //creates a frame for your program
        frame.add(new GraphicsTester()); //allows you to "paint" on the frame
        frame.setSize(500, 500); //You can change your window size here
        frame.setVisible(true); //displays frame
        
        while(true)
        {
        		frame.getComponent(0).repaint();
        }
    }
}
