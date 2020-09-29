package edu.uga.cs1302.mytunes;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class MP3Collection {
	
	
	private List<MP3File> mp3s;
	private MP3Player player;
	
	/**
	 * creates an empty MP3Collection
	 */
	public MP3Collection()
	{
		mp3s = new LinkedList<MP3File>();
		player = new MP3Player();
	}
	
	/**
	 * creates a MP3Collection with mp3s from the given file
	 * @param dir the directory where to find mp3 files
	 */
	public MP3Collection (String dir)
	{
		mp3s = new LinkedList<MP3File>();
		player = new MP3Player();
		
		File mp3Dir = new File(dir);
		 

	    File[] files = mp3Dir.listFiles();

	    for(File f: files){
	        

	        if(f.getName().endsWith(".mp3") || f.getName().endsWith(".MP3"))
	        {
	            mp3s.add(new MP3File(f.getPath()));
	        }
	    }
	}
	
	 
	/**
	 * returns a 2D array of mp3s parsed out to ID3 tags
	 * @return a 2d array of mp3 ID3 tags
	 */
	public Object[][] getTableData()
    {
	Object[][] data = new Object[mp3s.size()][4];
	
	
	    
	    for (int i = 0; i < mp3s.size(); i++)
	    {
	    	data[ i ][ 0 ] = mp3s.get( i ).getTitle();
	    	data[ i ][ 1 ] = mp3s.get( i ).getAuthor();
	    	data[ i ][ 2 ] = mp3s.get( i ).getAlbum();
	    	data[ i ][ 3 ] = mp3s.get( i ).getYear();
	    }

	return data;
    }
	
	/**
	 * 
	 * @return the size of the linked list of mp3 files
	 */
	public int size()
	{
		return mp3s.size();
	}
	
	/**
	 * returns the MP3 files from a given index location in the collection
	 * @param index the index of the file to be returned
	 * @return the MP3 file from the given index
	 * @throws IndexOutOfBoundsException if the given index is out of bound
	 */
	public MP3File getFile(int index) throws IndexOutOfBoundsException
	{
		if (index > mp3s.size())
		{
			throw new IndexOutOfBoundsException("Error " + index);
		}
		
		return mp3s.get(index);
	}
	
	/**
	 * starts playing the file at the given index
	 * @param index the file from the collection to play
	 */
	public void startPlay(int index)
	{
		player.play(mp3s.get(index).getPath());
	}
	
	/**
	 * stops any playing file
	 */
	public void stopPlay()
	{
		player.stop();
	}
}
