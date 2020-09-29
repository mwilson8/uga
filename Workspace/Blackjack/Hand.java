
/**
 * 
 * @author ghyzel
 *
 */
public class Hand {
	
	/**
	 * The cards in the hand
	 */
	private Card [] cards;
	
	/**
	 * Creates an empty hand
	 */
	public Hand() {
		//Initializing an empty array. 
		//Calling cards.length on this array would return 0
		cards = new Card[0];		
	}

	/**
	 * Adds Card c to the hand
	 * 
	 * @param c card to be added
	 */
	public void addCard(Card c) {
	
		Card [] temp = new Card[cards.length+1];
		
		for(int i = 0; i < cards.length; i++)
			temp[i] = cards[i];
		
		temp[temp.length-1] = c;
		
		cards = temp;
		
	}
	
	/**
	 * @return number of cards in the hand
	 */
	public int size() {
		return cards.length;
	}
	
	/**
	 * Returns an array of all the cards in the hand
	 * 
	 * @return the cards in the hand
	 */
	public Card[] getCards() {
		 Card [] temp = new Card[cards.length];
		 
		 for (int i = 0; i < cards.length; i++)
			 temp[i] = cards[i];
		 
		 return temp;
	}
	
	/**
	 * Empties the hand, and returns an array containing the discarded cards.
	 * 
	 * @return the discarded cards
	 */
	public Card[] emptyHand() {
		Card [] temp = new Card[cards.length];
		 
		 for (int i = 0; i < cards.length; i++)
			 temp[i] = cards[i];
		 
		 cards = new Card [0];
		 return temp;
	}
	
	/**
	 * Returns a String representation of the hand
	 * 
	 * E.g.
	 * 
	 * "Empty Hand"
	 * "1. ACE OF SPADES\n2. QUEEN OF HEARTS"
	 * 
	 * @return a String representing the hand
	 */
	@Override
	public String toString() {
		if (cards.length == 0) return "Empty hand";
		String s = "";
		
		for (int i = 0; i < cards.length; i++)
			s += i + "." + cards[i].toString() + "\n";

		return s;
	}
}
