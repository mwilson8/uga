import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Map;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;

/** 
 * *
 * * *
 * * * *
 * * * * *
 * You don't need to modify anything in this file
 * * * * *
 * * * *
 * * *
 * *
 */
@SuppressWarnings("rawtypes")
public class MP3File {

	private String 
	path, Author, Title;
	
	/**
	 * Creates a new MP3File with default values of null
	 */
	public MP3File ()
	{
		path = null;
		Author = null;
		Title = null;
	}
	
	/**
	 * This constructor is only used in the tester class
	 * @param title
	 * @param author
	 */
	public MP3File(String title, String author){
		this.Author = author;
		this.Title = title;
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
		    Title = (String) properties.get( "title" );
		    //if you want to get the other properties of the file, you can do so
		    //with a similar expression as what's above
		    //you can run into problems if the actual file doens't have the tags
		    //for them properties (like Album, Year, etc)..almost all songs have Title & Author
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
	 * Returns the title variable of this MP3File
	 * @return the title variable of this MP3File
	 */
	public String getTitle()
	{
		return Title;
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
	 * Changes the title variable of this MP3File
	 * @param newTitle the new title variable for this MP3File
	 */
	public void setTitle(String newTitle)
	{
		Title = newTitle;
	}
	
	
	/**
	 * Returns the variables of this MP3File in String format
	 */
	public String toString()
	{
	    String s =  "Information about:\n" + this.getPath() + "\n"
	                        + "\nAuthor: " + getAuthor()
	                        + "\nTitle:  " + getTitle()
	                        + "\n-------------------------- ";

	    return s;
	}
	
	/**
	 * @return true if the two MP3Files have equivalent variables
	 */
	public boolean equals(Object o)
	{
		
		if (o instanceof MP3File)
		{
			if (this.Author == ((MP3File)o).Author
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
//		player.waitForPlaybackFinish();
	}
}
