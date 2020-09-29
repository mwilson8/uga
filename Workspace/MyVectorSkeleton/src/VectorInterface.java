
public abstract interface VectorInterface {
	
	public boolean 	add (MP3File file);
	public void 	add(int index, MP3File file);
	public int 		capacity();
	public void 	clear();
	public boolean 	contains(MP3File file);
	public void 	ensureCapacity(int minCapacity);
	public boolean 	equals(MyVector anotherVector);
	public MP3File 	get(int index);
	public boolean 	isEmpty();
	public MP3File 	remove(int index);
	public int 		size();
	public String 	toString();
	public void 	trimToSize();

	

}
