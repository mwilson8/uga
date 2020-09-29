import java.util.Scanner; 

public abstract class Player {
	private String name;
	
	public boolean equals(Object o){
		if (o instanceof Player){
			if (((Player) o).getName().equals(this.name)){
				return true;
			}
		}
		return false;
	}
	
	public String getName(){
		return name;
	}
}
