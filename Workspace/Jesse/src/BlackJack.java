

/*
* Blackjack.java
* Author: Jesse Eldell
* Submission Date: Late April 2017
*
* Purpose: The Deck is verbatim the deck. This class
*  organizes all the cards and controls how the cards
*  are shuffled before they are dealt.
*
* Statement of Academic Honesty:
*
* The following code represents my own work. I have neither
* received nor given inappropriate assistance. I have not copied
* or modified code from any source other than the course webpage
* or the course textbook. I recognize that any unauthorized
* assistance or plagiarism will be handled in accordance with
* the University of Georgia's Academic Honesty Policy and the
* policies of this course. I recognize that my work is based
* on a programming project created by the Department of
* Computer Science at the University of Georgia. Any publishing
* of source code for this project is strictly prohibited without
* written consent from the Department of Computer Science.
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
	public BlackJack() 
	{
		player=new Player();
		dealer=new Dealer();
		deck=new Deck();
		 deck.shuffle();
		player.getHand().addCard(deck.draw()); //Draw for player once
		dealer.getHand().addCard(deck.draw()); // Draw for Dealer once
		player.getHand().addCard(deck.draw()); //Draw for player twice
		dealer.getHand().addCard(deck.draw()); //Draw for Dealer twice
		
		
	}
	/**
	 * Restarts in a few steps
	 * 1. The Player and dealer return their cards to the deck.
	 * 2. The deck is shuffled.
	 * 3. Both the player and the dealer receive two cards drawn form the top of the deck.
	 */
	public void restart() 
	{
		player.returnCardstoDeck(deck);
		dealer.returnCardstoDeck(deck);
		deck.shuffle();
		player.getHand().addCard(deck.draw()); //Draw for player once
		dealer.getHand().addCard(deck.draw()); // Draw for Dealer once
		player.getHand().addCard(deck.draw()); //Draw for player twice
		dealer.getHand().addCard(deck.draw()); //Draw for Dealer twice
		
	}
	/**
	 * Returns the value of a card in a standard game of Blackjack based on the type of the card
	 * ex. An Ace would return 1, a 2 would return 2... 
	 * @param c card whose value is extracted
	 * @return value of the card
	 */
	public static int getValueOfCard(Card c) 
	{
	  c.getType();
	  int value = 0;
		if (c.getType()==Card.Type.ACE)
		{
			value=1;
		}
		else if (c.getType()==Card.Type.TWO)
		{
			value=2;
		}
		else if (c.getType()==Card.Type.THREE)
		{
			value=3;
		}
		else if (c.getType()==Card.Type.FOUR)
		{
			value=4;
		}
		else if (c.getType()==Card.Type.FIVE)
		{
			value=5;
		}
		else if (c.getType()==Card.Type.SIX)
		{
			value=6;
		}
		else if (c.getType()==Card.Type.SEVEN)
		{
			value=7;
		}
		else if (c.getType()==Card.Type.EIGHT)
		{
			value=8;
		}
		else if (c.getType()==Card.Type.NINE)
		{
			value=9;
		}
		else if (c.getType()==Card.Type.JACK||(c.getType()==Card.Type.KING)||(c.getType()==Card.Type.QUEEN))
		{
			value=10;
		}
				
		return value;
	}
	/**
	 * Returns the maximum value of the hand that does not result in a bust
	 * @param h Hand whose value is returned
	 * @return value of h
	 */
	public static int getValueOfHand(Hand h) 
	{
		Card[] y=h.getCards();
		int s=0;
		Card e = null;
		 for (int i=0; i<h.size();i++)
		 {
			 if (BlackJack.getValueOfCard(y[i])>=1)
			 {
				 s+=BlackJack.getValueOfCard(y[i]);
			 }
		 }
		  if (s>21)
		  {
			  for (int i=0;i<h.size();i++)
			  {
				  if ((s>21)&&(e.getType()==Card.Type.ACE))
				  {
					  s+=1;
				  }
				  else 
				  {
					 s=0; 
				  }
			  }
		  }
        
		return s;
	}

	/**
	 * @return Deck used to play
	 */
	public Deck getDeck() 
	{
		return deck;
	}
	
	/**
	 * @return Dealer of the game
	 */
	public Dealer getDealer() 
	{
		return dealer;
	}
	
	/**
	 * @return Player playing the blackjack game
	 */
	public Player getPlayer()
	{
		return player;
	}

}
