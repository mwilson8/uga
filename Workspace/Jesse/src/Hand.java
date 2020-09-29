/*
* Hand.java
* Author: Jesse Eldell
* Submission Date: Late April 2017
*
* Purpose: The Hand class mimics the cards in
* a player or delaer's possession during the game.
* We want to reuse the cards so that the hand can be used again.
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
public class Hand 
{
	
	/**
	 * The cards in the hand
	 */
	private Card [] cards;
	
	/**
	 * Creates an empty hand
	 */
	public Hand() 
	{
		//Initializing an empty array. 
		//Calling cards.length on this array would return 0
		cards = new Card[0];		
	}

	/**
	 * Adds Card c to the hand
	 * 
	 * @param c card to be added
	 */
	public void addCard(Card c) 
	{
		Card[]x=new Card[cards.length+1];
		 for (int i=0;i<cards.length;i++)
		 {
			 x[i]=cards[i];
		 }
       x[x.length-1]=c;		
		cards=x;
	}
	
	/**
	 * @return number of cards in the hand
	 */
	public int size() 
	{
		return cards.length;
	}
	
	/**
	 * Returns an array of all the cards in the hand
	 * 
	 * @return the cards in the hand
	 */
	public Card[] getCards() 
	{
		// Ensure you return reference to the copy of the cards array
		// and not a reference actual cards array!
		Card[]y= new Card[cards.length];
		 for (int i=0;i<cards.length;i++)
		 {
			 y[i]=cards[i];
		 }
		return y;
	}
	
	/**
	 * Empties the hand, and returns an array containing the discarded cards.
	 * 
	 * @return the discarded cards
	 */
	public Card[] emptyHand() 
	{
		Card[]z=new Card[cards.length];
		for (int i=0;i<cards.length;i++)
		{
			z[i]=cards[i];
		}
		cards=new Card[0];
		return z;
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
	public String toString() 
	{
		// HINT: Use the toString() method of the card class
		String s="";
		if (cards.length==0)
		{
			s="Empty Hand";
		}
		else
		{
			for(int i =0; i <cards.length;i++)
			{
				s+=(i+1)+'.'+" "+cards[i].getType()+" OF "+cards[i].getSuit()+"\n";
			}
			
		}
		return s;
	}
}
