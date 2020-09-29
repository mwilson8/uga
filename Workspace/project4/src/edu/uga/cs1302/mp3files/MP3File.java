package edu.uga.cs1302.mp3files;



import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Map;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;

import edu.uga.cs1302.mp3files.MP3Player;

public class MP3File {

	private String 
	path, Author, date, Title, Album;
	
	/**
	 * Creates a new MP3File with default values of null
	 */
	public MP3File ()
	{
		path = null;
		Author = null;
		Title = null;
		Album = null;
		date = null;
	}
	
	/**
	 * Creates a new MP3File with variable values equal to the ID3 tags of the given pathname
	 * @param pathname the pathname of the file to parse ID3 tags from
	 */
	public MP3File (String pathname)
	{
		try {
			FileInputStream fis        = new FileInputStream( pathname );
		    BufferedInputStream bis    = new BufferedInputStream(fis);
		    AudioFileFormat mpegFormat = AudioSystem.getAudioFileFormat(bis);
		    Map properties             = mpegFormat.properties();
		    
		    path = pathname;
		    Author = (String) properties.get( "author" );
		    Album = (String) properties.get( "album" );
		    Title = (String) properties.get( "title" );
		    date = (String) properties.get( "date" );
		    
		}
		
		catch (Exception e){
		System.err.println(e);
		}	
	}
	
	/**
	 * Returns the path variable of this MP3File
	 * @return the path variable of this MP3File
	 */
	public String getPath()
	{
		return path;
	}
	
	/**
	 * Returns the author variable of this MP3File
	 * @return the author variable of this MP3File
	 */
	public String getAuthor()
	{
		return Author;
	}
	
	/**
	 * Returns the date variable of this MP3File
	 * @return the date variable of this MP3File
	 */
	public String getDate()
	{
		return date;
	}
	
	/**
	 * Returns the title variable of this MP3File
	 * @return the title variable of this MP3File
	 */
	public String getTitle()
	{
		return Title;
	}
	
	/**
	 * Returns the album variable of this MP3File
	 * @return the album variable of this MP3File
	 */
	public String getAlbum()
	{
		return Album;
	}
	
	
	/**
	 * Changes the path variable of this MP3File
	 * @param newPath the new path variable for this MP3File
	 */
	public void setPath(String newPath)
	{
		path = newPath;
	}
	
	/**
	 * Changes the author variable of this MP3File
	 * @param newAuthor the new author variable for this MP3File
	 */
	public void setAuthor(String newAuthor)
	{
		Author = newAuthor;
	}
	
	/**
	 * Changes the date variable of this MP3File
	 * @param newDate the new date variable for this MP3File
	 */
	public void setDate(String newDate)
	{
		date = newDate;
	}
	
	/**
	 * Changes the title variable of this MP3File
	 * @param newTitle the new title variable for this MP3File
	 */
	public void setTitle(String newTitle)
	{
		Title = newTitle;
	}
	
	/**
	 * Changes the album variable of this MP3File
	 * @param newAlbum the new album variable for this MP3File
	 */
	public void setAlbum(String newAlbum)
	{
		Album = newAlbum;
	}
	
	/**
	 * Returns the variables of this MP3File in String format
	 */
	public String toString()
	{
	    String s =  "Information about: " + this.getPath() + "\n"
	                        + "-------------------------- " 
	                        + "\nAuthor: " + getAuthor()
	                        + "\nAlbum:  " + getAlbum()
	                        + "\nTitle:  " + getTitle()
	                        + "\nDate:   " + getDate();

	    return s;
	}
	
	/**
	 * @return true if the two MP3Files have equivalent variables
	 */
	public boolean equals(Object o)
	{
		if (o instanceof MP3File)
		{
			if (this.Album == ((MP3File)o).Album
					&& this.Author == ((MP3File)o).Author
					&& this.date == ((MP3File)o).date
					&& this.path == ((MP3File)o).path
					&& this.Title == ((MP3File)o).Title)
				return true;
		}
		return false;
	}
	
	/**
	 * Plays this MP3File
	 */
	public void play()
	{
		MP3Player player = new MP3Player();
		player.play(this.getPath());
		player.waitForPlaybackFinish();
	}
}
