package inClassExamples;
import java.util.Random;
import java.util.Vector;

public class LotteryTicket {

	public static int numTickets;
	//all numbers on the ticket will be 0-99
	//all tickets are square tickets, equal to # of winningNumbers.length
	private int [][] ticket;
	private int [] winningNumbers;
	
	
	public LotteryTicket(int numWinningNumbers, boolean isWinner){
		Random r = new Random();
		winningNumbers = new int [numWinningNumbers];
		ticket = new int [numWinningNumbers][numWinningNumbers];
		
		for (int i = 0; i < winningNumbers.length; i++)
			winningNumbers[i] = r.nextInt(99);
		
		if (!isWinner){
			//if it's not a winning ticket, fill it with numbers that aren't the winning ones
			
			for (int i = 0; i < ticket.length; i++){
				for (int j = 0; j < ticket[i].length; j++){
					
					 //generate new random number
					int x = r.nextInt(99);
					
					//if it is not a winning number, add to ticket
					if (!isWinningNumber(x)) ticket[i][j] = x; 
					
					//if it is a winning number, do this index again
					else j--; 
				}//for
			}//for
			
		}else {
			//if it is winning, it must have (at least) one winning number 
			int rowWin = r.nextInt(ticket.length);
			int colWin = r.nextInt(ticket[0].length);
			
			for (int i = 0; i < ticket.length; i++){
				for (int j = 0; j < ticket[i].length; j++){
					if (i == rowWin && j == colWin){ // put a winning number in our randomly selected place
						ticket[i][j] = winningNumbers[r.nextInt(winningNumbers.length)];
					}
					else {
						int x = r.nextInt(99); //generate new random number
						if (!isWinningNumber(x)) ticket[i][j] = x; //if it is not a winning number, add to ticket
						else j--; //if it is a winning number, do this index again
					}
				}
			}
			
		}
		numTickets ++;
	}
	
	
	
	private boolean isWinningNumber(int i){
		for (int x: winningNumbers)
			if (x == i) return true;
		
		return false;
	}
	
	public String toString(){
		String s = "";
		for (int i = 0; i < winningNumbers.length; i++){
			s += winningNumbers[i] + " ";
			if (winningNumbers[i] < 10) s += " ";
		}
		s += "\n";
		for (int i = 0; i < (winningNumbers.length * 2 + winningNumbers.length - 1); i ++) 
		s += "-";
		s += "\n";
		for (int i = 0; i < ticket.length; i++){
			for (int j = 0; j < ticket[i].length; j++){
				s += ticket[i][j]; 
				if (ticket[i][j] < 10) s += " "; // to help with alignment
				if (j != ticket[i].length - 1)
					s += " ";
			}
			if (i != ticket.length - 1)
				s += "\n";
		}
		return s;
	}
	
	
	
	public boolean isWinner(){
		for (int i = 0; i < ticket.length; i++){
			for (int j = 0; j < ticket[i].length; j++){
				if (isWinningNumber(ticket[i][j])) return true;
			}
		}
		return false;
	}
	
	
	public static void main (String[] args){
		
		Random r = new Random();
		Vector <LotteryTicket> vector = new Vector<LotteryTicket>();
		
		for (int i = 0; i < 5; i ++)
			vector.add(new LotteryTicket(4, r.nextBoolean()));
		
		for (LotteryTicket ticket: vector){
			System.out.println(ticket);
			System.out.println(ticket.isWinner() + "\n");;
			
		}
	}
	
	
}
