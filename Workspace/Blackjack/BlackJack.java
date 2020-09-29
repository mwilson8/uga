
/**
 * Class representing a single player blackjack game
 */
public class BlackJack {
	
	private Deck deck;
	private Dealer dealer;
	private Player player;


	/**
	 * Constructs and prepares for a new game of BlackJack.
	 * Creates player, dealer and deck objects then shuffles
	 * the deck and gives both the dealer and player two cards.
	 */
	public BlackJack() {
		deck = new Deck();
		dealer = new Dealer();
		player = new Player();
		deck.shuffle();
		
		dealTwo();
		
	}
	/**
	 * Restarts in a few steps
	 * 1. The Player and dealer return their cards to the deck.
	 * 2. The deck is shuffled.
	 * 3. Both the player and the dealer receive two cards drawn form the top of the deck.
	 */
	public void restart() {
		player.returnCardstoDeck(deck);
		dealer.returnCardstoDeck(deck);
		deck.shuffle();
		
		dealTwo();
	}
	/**
	 * Returns the value of a card in a standard game of Blackjack based on the type of the card
	 * ex. An Ace would return 1, a 2 would return 2... 
	 * @param c card whose value is extracted
	 * @return value of the card
	 */
	public static int getValueOfCard(Card c) {
	
		switch( c.getType() ){
		
		case ACE: 	return 1;
		case TWO: 	return 2;  
		case THREE: return 3;  
		case FOUR:	return 4;  
		case FIVE: 	return 5;  
		case SIX: 	return 6;  
		case SEVEN: return 7;  
		case EIGHT: return 8;  
		case NINE:	return 9;  
		case TEN:	
		case JACK:	 
		case QUEEN:	
		case KING:	return 10; 
		  
		
		default: return 0; 
		
		}
		
	}
	/**
	 * Returns the maximum value of the hand that does not result in a bust
	 * @param h Hand whose value is returned
	 * @return value of h
	 */
	public static int getValueOfHand(Hand h) {
		int value = 0, aces = 0;
		for (int i = 0; i < h.size(); i++){
			if (h.getCards()[i].getType() == Card.Type.ACE){
				aces ++;
				continue;
			}
			value += getValueOfCard(h.getCards()[i]);
		}
		if (aces == 1){ /* if 1 ace */
			if (value + 11 <= 21) value += 11; /* 11 preferred, otherwise worth 1 total */
			else value += 1;
			
		}else if (aces == 2){ /* if 2 aces */
			if (value + 12 <= 21) value += 12; /* 12 preferred (11 & 1), otherwise 2 total (both 1) */
			else value += 2;
			
		}else if (aces == 3){ /* if 3 aces ...? */
			if (value + 13 <= 21) value += 13; /* 13 preferred (11 & 1 & 1), otherwise 3 total (all 1) */
			else value += 3;
			
		}else if (aces == 4){ /* if 4 aces ......? */
			if (value + 14 <= 21) value += 14; /* 14 preferred (11 & 1 & 1 & 1), otherwise 4 total (all 1) */
			else value += 4;
		}
		
		return value;
	}

	/**
	 * @return Deck used to play
	 */
	public Deck getDeck() {
		return deck;
	}
	
	/**
	 * @return Dealer of the game
	 */
	public Dealer getDealer() {
		return dealer;
	}
	
	/**
	 * @return Player playing the blackjack game
	 */
	public Player getPlayer() {
		return player;
	}
	
	private void dealTwo(){
		player.getHand().addCard(deck.draw());
		dealer.getHand().addCard(deck.draw());
		player.getHand().addCard(deck.draw());
		dealer.getHand().addCard(deck.draw());
	}

}
