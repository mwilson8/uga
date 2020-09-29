
public class Movie {
	
	public enum MPAA {G, PG, PG13, R};
	
	private String name;
	private int runTime;
	private int [] ratings = new int [4];
	private MPAA rating;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setRating(MPAA rating){
		this.rating = rating;
	}
	
	public MPAA getRating(){
		return rating;
	}
	
	public void setRuntime(int runTime){
		this.runTime = runTime;
	}
	
	public int getRuntime(){
		return runTime;
	}
	
	public void addRating(MPAA rating){
		switch (rating){
		
		case G: ratings[0] ++; break;
		case PG: ratings[1] ++; break;
		case PG13: ratings[2] ++; break;
		case R: ratings[3] ++; break;
		}
		
	}
	
	public int getCountForRating(MPAA rating){
		switch (rating){
		case G: return ratings[0];
		case PG: return ratings[1] ++; 
		case PG13: return ratings[2] ++;
		case R: return ratings[3] ++;
		default: return -1;
		}
		
	}
	
	public int getAverageRating(){
		int sum = 0, count = 0;
		for (int i = 0; i < ratings.length; i++){
			sum += (ratings[i] * i + 1);
			count ++;
		}
		return sum/count;
	}
	
}
