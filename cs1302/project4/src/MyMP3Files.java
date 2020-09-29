import java.io.*;
import java.util.Scanner;
import java.util.ListIterator;
import edu.uga.cs1302.mp3files.*;	



public class MyMP3Files {

	
	
	public static void main (String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		
		String dir;
	    System.out.println("Please enter a directory that contains the mp3 files you wish to play.");
	    dir = keyboard.nextLine();
		
	    File mp3Dir = new File(dir);

	    while(!mp3Dir.isDirectory()){
	        System.out.println("The directory you entered does not exist, please enter another directory.");
	        dir = keyboard.nextLine();
	        mp3Dir = new File(dir);
	    }

	    SimpleArrayList<MP3File> mp3s = new SimpleArrayList<MP3File>();

	    File[] files = mp3Dir.listFiles();

	    for(File f: files){
	        

	        if(f.getName().endsWith(".mp3") || f.getName().endsWith(".MP3")){
	            mp3s.add(new MP3File(f.getPath()));
	        }


	    }
	    
	    
	    
	    ListIterator<MP3File> iter =  mp3s.listIterator(0);
	    
	    while (true)
	    {
	    	try {
	    		System.out.println(iter.next().toString());
	    	}
	    	catch (Exception e)
	    	{
	    		break;
	    	}
	    }
	    
	    iter = mp3s.listIterator(0);
	    
	    printMenu();
	    
	    while (true)
	    {
	    	System.out.println("Enter a command: \n");
	    	
	    	char command = keyboard.nextLine().charAt(0);	
		    
		    switch(command) {
		    
		    case 'n': 
		    	if (iter.hasNext())
		    		iter.next();
		    	else
		    		System.out.println("Next file not available");
		    	break;
		    	
		    case 'b':
		    	
		    	if (iter.hasPrevious())
		    		iter.previous();
		    	else
		    		System.out.println("Previous file not available");
		    	break;
		    	
		    case 'i':
		    	try{
		    	System.out.println(iter.next().toString());
		    	iter.previous();
		    	}
		    	 catch (Exception e)
		    	{
		    		 System.err.println(e.getMessage());
		    	}
		    
		    	break;
		    	
		    case 'p':
		    	iter.next().play();
		    	iter.previous();
		    	
		    case 'h':
		    	printMenu();
		    	break;
		    	
		    case 'q':
		    	return;
		    	
		    default:
		    	System.out.println("That is not a valid command.");
		    	break;
		    }
	    }
	    
		
	}
	
	private static void printMenu()
	{
		System.out.println("N: moves to the next file, if available");
		System.out.println("B: moves to the previous file, if available");
		System.out.println("I: prints information about the current file");
		System.out.println("P: plays the current file");
		System.out.println("H: print the menu options");
		System.out.println("Q: terminate the program");
	}
}
