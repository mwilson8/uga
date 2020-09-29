import java.io.*;
import java.util.Scanner;
import java.util.HashMap;

/**
 * A driver to simulate a MP3 Player. Assuming you implemented your MyVector
 * correctly, you won't need to modify this file. Don't modify it right away,
 * at least until you're sure how it works. You won't need to submit this file
 * so after looking around, feel free to modify it. At this time, playlists and 
 * the library are not stored to the hard drive, so they only exist while the 
 * program is running. If you are familiar with File I/O you could implement
 * the ability to store playlists after the program terminates.
 * 
 * Don't edit the MP3Player class, or you risk losing the ability to play songs
 * 
 * 
 * @author MitchWilson
 *
 */
public class MP3PlayerDriver {

	static {
		System.out.println("Initializing..");
	}
	
	//this is the backbone of the class: dynamic storage of MP3Files
    private static MyVector mp3s = new MyVector();
    
    //this MP3Player object is used to play the files, it handles all the threading
	private static MP3Player player = new MP3Player();
	
	//we've seen Scanner before
	private static Scanner keyboard = new Scanner(System.in);
	
	//a HashMap is how we are saving a MyVector into a data structure with a String as 
	//it's "variable name" --- if you want to edit this driver class, you should 
	//understand the basics of how a HashMap works
	private static HashMap<String, MyVector> playlists = new HashMap<String, MyVector>(); 
   
	//this int tracks which file we are currently viewing/want to play while in the main library
	private static int currentFile = 0;
   
    
    /*
	 * a relative path will cause a null pointer exception
	 * the path must be the full path (right click the music folder, and copy
	 * the full path from under "location")
	 */
    /////////////////////////////////////////////////////////////////////////
    /////// **** change this to match your own folder's location **** ///////
	///////	   						 ||								  ///////
	///////							 ||								  ///////
	///////							_||_							  ///////
	///////							\  /							  ///////	
	///////							 \/								  ///////
    private static final String SONG_DIRECTORY = 
    		
    		"/Users/MitchWilson/Desktop/Workspace/MyVector/src/sample_music";
    /////////////////////////////////////////////////////////////////////////
    /////// ^^^^ change this to match your own folder's location ^^^^ ///////
    /////////////////////////////////////////////////////////////////////////
    
	public static void main (String[] args)
	{

		//check to see if the SONG_DIRECTORY was changed
		/*if (SONG_DIRECTORY.equals("/Users/MitchWilson/Desktop/Workspace/MyVector/src/sample_music")){
			System.out.println("You need to change the song directory before executing");
			System.exit(-1);
		}
		*/
		
		//make a new File with all the files in it, from the directory above
	    File mp3Dir = new File(SONG_DIRECTORY);

	    //convert all the files in mp3Dir to a File array
	    File[] files = mp3Dir.listFiles();
	    
	    //make sure the vector is large enough to hold all the possible files
	    mp3s.ensureCapacity(files.length);
	    
	    //go through the given directory and add any MP3 files to the vector
	    for(File f: files){
	    	
	    	//this is a way of error handling that you will learn in 1302
	    	//you'll see a good bit of this in this file
	    	//File I/O necessitates a try-catch block
	    	try {
		        if(f.getName().endsWith(".mp3") || f.getName().endsWith(".MP3")){
		            mp3s.add(new MP3File(f.getPath()));
		        }
		        
		    //this accompanies the "try" line from above
	    	}catch(Exception e){
	    		System.out.println("There was a problem adding \"" + f.getName() + "\"");
	    	}
	

	    }
	    

	    //check what all files were added
	    //if you don't want this initial print, you can comment out or delete this loop
	    for (int i = 0; i < mp3s.size(); i++){
	    	System.out.println("Info about: " + mp3s.get(i).getPath());
	    	System.out.println("Title:      " + mp3s.get(i).getTitle());
	    	System.out.println("Author:     " + mp3s.get(i).getAuthor() + "\n");
	    }

	    //because MP3's as a data-type are large, we want to trim the vector to conserve space
	    mp3s.trimToSize();
	    
	    boolean playing = false;
	    String commandString;
	    char command;
	    
	    /*by taking the input as a string and then parsing it to a char, there are 
	     *multiple options for the menu
	     *although this music player could grow to be fairly complicated, and it would
	     *benefit from a graphical interface, it will be run as a simple text menu
	     *for now. 
	     *
	     *NOTE: the main library cannot be changed in any way other than adding or removing 
	     *from the file hierarchy. In that case, the library should be refreshed. Playlists 
	     *can be created and removed. To re-order a playlist, you have to remove and re-add 
	     *a song. Songs can be added at any order or removed from any order of the playlist
	     *
	     * 
	     */
	    System.out.println("----------------------------------");
	    System.out.println("--------BEGIN MAIN PROGRAM--------");
	    System.out.println();
	    
	    //program runs until it's quit
	    while(true){

	    	System.out.println("----------------------------------");
	    	System.out.println("Current song: \"" + mp3s.get(currentFile).getTitle());
	    	System.out.print("--> ");commandString = keyboard.nextLine();
	    	System.out.println();
	    	
	    	
	    	//if the user just hits enter, start playing the current file
	    	if (commandString.length() == 0){
	    		if (!playing){
	    		player.play(mp3s.get(currentFile).getPath());
	    		System.out.println("PLAY: " + mp3s.get(currentFile).getTitle());
	    		playing = true;
	    		}
	    		continue;
	    		
	    	}else if (commandString.equalsIgnoreCase("playlist") ||
			    		commandString.equalsIgnoreCase("playlists")){
			    		//switch to playlist menu
			    	
	    		//if it's playing, we have to stop it before switching to playlist view
	    		//this has to do with threading and is outside the scope of intro classes
	    		if (playing){
	    			System.out.println("**due to how the program handles threads, the current \n\t"
	    					+ "playing song must be stopped before moving to playlist editing**");
	    			System.out.println("<<<---------->>>");
	    			player.stop();
	    			playing = false;
	    		}
	    		
	    		//this private method does all the playlist stuff, it's at the bottom of this file
	    		playlistMenu();

	    		continue;
			}
	    	
	    	command = commandString.charAt(0);
	    	switch(command){
	    	
	    	//play or stop the playing file, depending on the boolean variable
	    	case 'p': 
	    		if (!playing){
		    		player.play(mp3s.get(currentFile).getPath());
		    		System.out.println("PLAY: " + mp3s.get(currentFile).getTitle());
		    		playing = true;
	    		}else {
	    			player.stop();
	    			System.out.println("STOP");
	    			playing = false;
	    		}
	    	break;
	    	
	    	//list the songs in the library: simple for loop, printing only titles
	    	case 'l': 
	    		for (int i = 0; i < mp3s.size(); i++)
	    			System.out.println(i + "| " + mp3s.get(i).getTitle());
	    		System.out.println();
	    	break;
	    	
	    	case 'm': printMainMenu();
	    	break;
	    	
	    	//refresh shouldn't be needed at all, but in case it is, it does work
	    	case 'r': 
	    		//this should be used if a song is added/removed from the source folder while 
	    		//the program is still running, failure to do so will cause a null pointer exception
	    		refreshLibrary();
	    		break;
	    		
	    	//move to the next file, if available
	    	case 'f': 
	    		if (currentFile < mp3s.size()-1){
	    			currentFile ++;
	    			System.out.println("FORWARD");
	    		}
	    	break;
	    	
	    	//move to the previous file, if available
	    	case 'b': 
	    		if (currentFile > 1) {
	    			currentFile --;
	    			System.out.println("BACK");
	    		}
	    	break;	
	    	
	    	//main is a void method, return will cause it to exit (similar to System.exit(int))
	    	case 'q': System.out.println("EXIT"); return;

	    	}//switch
	    	
	    }
	    
	   
	   
	}
	
	/**
	 * Private helper method that prints the options to the user when on the main menu
	 */
	private static void printMainMenu(){
		   System.out.println("\"playlist(s)\": go to playlist editing menu");
		   System.out.println("p: play/stop current file (pause not supported)"); 
		   System.out.println("f: go forward to the next song");
		   System.out.println("b: go backward to the previous song");
		   System.out.println("m: print menu");
		   System.out.println("l: list all songs in the library");
		   System.out.println("r: refresh library");
		   System.out.println("q: quit");
		   System.out.println();
		   
	}//printMainMenu()
	
	/**
	 * Private helper method that prints the option to the user when on the playlist menu
	 */
	private static void printPlaylistMenu(){
		   System.out.println("\"main\": go to main library menu");
		   System.out.println("a: add a song to this playlist");
		   System.out.println("r: remove a song from this playlist");
		   System.out.println("R: delete this playlist");
		   System.out.println("p: play/stop current file (pause not supported)"); 
		   System.out.println("f: go forward to the next song");
		   System.out.println("b: go backward to the previous song");
		   System.out.println("m: print menu");
		   System.out.println("l: list all songs in the playlist");
		   // System.out.println("o: re-order a song in the playlist");
		   System.out.println();
		   
	}//printPlaylistMenu()
	
	/**
	 * Private method to handle all of the playlist menu interactions.. 
	 * This method isn't the prettiest style-wise, but it works
	 */
	private static void playlistMenu(){
		//duplicate the variables from the main method so we don't change main library 
		boolean playing = false;
		int currentFile = 0;
		
		//this variable acts as the current playlist, in MyVector form
		//it will hold the songs from the Map when they're pulled and allow us to work
		//with an editable version of the playlist and play them 
		MyVector usingPlaylist = new MyVector();
		
		
		
		System.out.println("Enter a playlist name to edit it, or a new one to create it");
		System.out.print("p--> ");
		
		//this is the title of the playlist, in String form
		//that we use anytime we need a String representation of it
		String currentPlaylist = keyboard.nextLine();
		
		System.out.println();
		
		//search the Map for the given playlist name
		//if it's in there, we load it into "usingPLaylist" for playing/editing
		if(playlists.containsKey(currentPlaylist)){
			System.out.println("***Editing \"" + currentPlaylist + "\"***");
			usingPlaylist = playlists.get(currentPlaylist);
			
		}else{
			System.out.println("***Creating \"" + currentPlaylist + "\"***");
			
		}
		System.out.println("---------------");
		
		printPlaylistMenu();
		
		while(true){
			System.out.println("---------------");
			System.out.println("Current playlist: [" + currentPlaylist + "]");
			
			//if "usingPlaylist" is not empty, print current song 
			//(this avoids a null pointer exception)
			if (!usingPlaylist.isEmpty())
				System.out.println("Current song: \"" + usingPlaylist.get(currentFile).getTitle());
			else
				System.out.println("Current song: <playlist is empty>");
			
			System.out.print("p--> ");String commandString = keyboard.nextLine();
			System.out.println();
			
			
			
			
			
			//if change this to use the same "playing" var from main, could it avoid the 
			//having to stop the song when switching to playlist?
			//i.e. use a static player and a static boolean..?
			
			
			
			
			
			//if command is return to main, stop the player (if playing)
			//save the playlist into the Map with the String key
			if (commandString.equalsIgnoreCase("main")) {
					if (playing)
						player.stop();
					playlists.put(currentPlaylist, usingPlaylist);
					System.out.println("--->MAIN--->");
					//MP3PlayerDriver.currentFile = 0;
					return;
				}
				
			
			//if the command is just "enter" then start playing
			if (commandString.length() == 0 && usingPlaylist.get(currentFile) != null){
				if (!playing){
		    		player.play(mp3s.get(currentFile).getPath());
		    		System.out.println("PLAY: " + mp3s.get(currentFile).getTitle());
		    		playing = true;
		    		}
		    		continue;
			}
			char command = commandString.charAt(0);
			
			switch(command){
			
			//add a song to the current playlist
			//currently can only add one at a time
			//possibly change to take a command line-like argument and add multiple 
			//songs are added by their index from the main library (mp3s)
			case 'a':
				System.out.println("\nADD");
				//print the library of songs for options to user
				System.out.println("-----library-----");
				for (int i = 0; i < mp3s.size(); i++)
	    			System.out.println(i + "| " + mp3s.get(i).getTitle());
	    		System.out.println();
	    		
				System.out.println("Enter the index of the song to add to " + currentPlaylist);
				System.out.print("p--> ");
				
				try{
					
				int index = keyboard.nextInt();
				keyboard.nextLine();
				
				//check for duplicate song in playlist
				if (usingPlaylist.contains(mp3s.get(index))){
					System.out.println("Playlist already contains that song... add duplicate? (y/n)");
					System.out.print("p--> "); 
					
					switch(keyboard.nextLine().charAt(0)){
					
					case 'y':
					case 'Y': 
						usingPlaylist.add(mp3s.get(index));
						System.out.println("ADDED");
						break;
						
					case 'n':
					case 'N':
						continue;
						
					}
					
				}else{
					usingPlaylist.add(mp3s.get(index));
					System.out.println("ADDED");
				}
				//keyboard.nextLine();
				
				}catch (Exception e){System.out.println("Error adding song");}
				
				break;
				
			//remove a song from the playlist	
			case 'r':
				
				System.out.println("\nREMOVE");
				//print the playlist songs to aid the user
				System.out.println("-----" + currentPlaylist + "-----");
				for (int i = 0; i < usingPlaylist.size(); i++)
	    			System.out.println(i + "| " + usingPlaylist.get(i).getTitle());
	    		System.out.println();
				
	    		System.out.println("Enter the index of the song to remove from " + currentPlaylist);
	    		System.out.print("p--> ");
	    		try{
					
				usingPlaylist.remove(keyboard.nextInt());
				keyboard.nextLine();
				System.out.println("REMOVED");
				
				}catch (Exception e){System.out.println("Error removing song");}
				
				break;
				
			//delete a playlist
			case 'R':
				System.out.println("Delete this playlist (\"" + currentPlaylist + "\") (y/n)");
				
				switch(keyboard.nextLine().charAt(0)){
				
					case 'y':
					case 'Y':
						playlists.remove(currentPlaylist);
						//here, we still have the local copies of "usingPLaylist"
						//and "currentPlaylist".. we can either set them to default and 
						//resume the loop or just return to the main menu
						//at the time of writing, returning to main was chosen
						System.out.println("Returning to MAIN");
						return;
					
					case 'n':
					case 'N':
						continue;
				}
				
				break;
				
			//list the songs in this playlist	
			case 'l':
				System.out.println("LIST");
				System.out.println("-----" + currentPlaylist + "-----");
				
				//if empty, say so
				if (usingPlaylist.isEmpty()) System.out.println("EMPTY");
				
				else
				for (int i = 0; i < usingPlaylist.size(); i++)
					System.out.println(i + ") " + usingPlaylist.get(i).getTitle());
				
				break;
			
			//move to next song, use local variable of "currentFile"
			case 'f': 
	    		if (currentFile < mp3s.size()-1){
	    			currentFile ++;
	    			System.out.println("FORWARD");
	    		}
	    	break;
	    	
	    	//move to previous song, use local variable of "currentFile"
	    	case 'b': 
	    		if (currentFile > 1) {
	    			currentFile --;
	    			System.out.println("BACK");
	    		}
	    	break;	
	    	
	    	//play/stop current song
	    	case 'p': 
	    		if (!playing){
		    		player.play(mp3s.get(currentFile).getPath());
		    		System.out.println("PLAY: " + mp3s.get(currentFile).getTitle());
		    		playing = true;
	    		}else {
	    			player.stop();
	    			System.out.println("STOP");
	    			playing = false;
	    		}
	    	break;
				
	    	//this could be implemented with a swap method in myVector
	    	/*case 'o':
	    		System.out.println("REORDER");
	    		System.out.println("Enter the index of the song to move");
	    		System.out.println("p-->");
	    		int songIndex = keyboard.nextInt();
	    		System.out.println("Enter the index to move it to");
	    		System.out.println("p-->");
	    		int newIndex = keyboard.nextInt();
	    		//usingPlaylist.swap(songIndex, newIndex);
	    		
	    		break;*/
				
			}//switch
		}//while	
		
	}//playlistMenu
	
	/**
	 * This private method should be called if and when the source folder with the music 
	 * files is modified from outside the program (a new song is added/removed while the
	 * program is running)
	 * The main library MyVector (mp3s) is cleared and then re-filled with all songs from the 
	 * source folder and then trimmed to size to save space
	 * It's up to the user to know when to refresh the library, the program (at this time)
	 * has no way of knowing if the folder has been changed
	 * It may be possible for the program to know, if modCount was implemented correctly in 
	 * MyVector and the number of modifications was tracked in the program as well. In that case,
	 * refreshing should happen when numberOfModifications != modCount
	 */
	private static void refreshLibrary(){
		File mp3Dir;
		File[] files;

		try{
			
		mp3Dir = new File(SONG_DIRECTORY);
		
	    files = mp3Dir.listFiles();
		}catch(Exception e){
			System.out.println("---Error: Refresh aborted");
			return;
		}
	
		//clear the "old" songs from the library
	    mp3s.clear();
	    
	    for(File f: files){
	    	try {
		        if(f.getName().endsWith(".mp3") || f.getName().endsWith(".MP3")){
		        		mp3s.add(new MP3File(f.getPath()));
		        		
		        }
	    	}catch(Exception e){
	    		System.out.println("---There was a problem adding \"" + f.getName() + "\"");
	    	}
	    }

	    mp3s.trimToSize();
		System.out.println("-------Refresh complete-----------");
		
	}//refresh 
	
	//private static void sychronizePlaylists(){
		/*
		 * this method was imagined to check all of the playlists and see if 
		 * two of them were equal, and if they were, delete one in order to 
		 * save space. It uses the MyVector equals method but must iterate 
		 * over all the keys from the HashMap.. feel free to try this implementation
		 * on your own
		 *
		 */
	//}
	
}//end MP3PlayerDriver
