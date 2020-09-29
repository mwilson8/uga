/*
* 					 [Adventure].java
* 
* 	Author: 		 [Mitch Wilson]
* 	
* 	Submission Date: [Apr 12 2016] 
* 	Last Updated: 	 [Apr 12 2016]
* 
*	Purpose: This program is designed to simulate an interactive open-world
*				game and is the driver for multiple other classes. While 
*				incorporating the other classes, we practiced implementing 
*				methods in OOP. The mainframe of the map was provided to us
*				but the logic was to be determined by us
*				
*  *
* Statement of Academic Honesty: *
* The following code represents my own work. I have neither
* received nor given inappropriate assistance. I have not copied
* or modified code from any source other than the course webpage
* or the course textbook. I recognize that any unauthorized
* assistance or plagiarism will be handled in accordance with
* the University of Georgia's Academic Honesty Policy and the
* policies of this course. I recognize that my work is based
* on an assignment created by the Department of Computer
* Science at the University of Georgia. Any publishing
* or posting of source code for this project is strictly
* prohibited unless you have written consent from the Department
* of Computer Science at the University of Georgia. */

import java.util.Scanner;
public class Adventure {
	
	static Room currentRoom;
	static String nextMove;
	static boolean look = false;
	public static void main(String[] args) {

		Player player1 = new Player();
		player1.setX(0);
		player1.setY(0);
		Map map = new Map();
		
		while (true){
		currentRoom = map.getRoom(player1.getX(), player1.getY());
		
		// if the room is dark & the player doesnt have the lamp
		// or if the room is dark, they have the lamp, and it's not lit
		
		if ((currentRoom.isDark() && player1.getLamp() == null)
				|| (currentRoom.isDark() && player1.getLamp() != null) && player1.getLamp().getIsLit() == false )
		{
			System.out.println("It is pitch black, you can't see anything. You may be eaten by a grue!");
			getNextMove();
			if (!nextMove.equalsIgnoreCase("light lamp"))
			{
				System.out.println("You have been eaten by a grue!");
				return;
				
			}
		}
		else
		{
		
		System.out.println(currentRoom.getDescription());
		if (look)
			showDetails();
		
		getNextMove();
				
		}
		
		
		if (nextMove.equalsIgnoreCase("look") && !look)
			look = true;
		
		/*
		 * get lamp
		 */
		else if (nextMove.equalsIgnoreCase("get lamp")) 
			{
				if (currentRoom.getLamp() != null)
				{
					player1.setLamp(currentRoom.getLamp());
					currentRoom.clearLamp();
					System.out.println("OK");
				}
				else
					System.out.println("No lamp present");				
			}
		
		/*
		 * light lamp
		 */
		else if (nextMove.equalsIgnoreCase("light lamp"))
		{
			if (player1.getLamp() != null)
			{
				player1.getLamp().setIsLit(true);
				System.out.println("OK");
			}
			else
				System.out.println("You don't have the lamp to light");
		}
		
		/*
		 * get key
		 */
		else if (nextMove.equalsIgnoreCase("get key"))
		{
			if (currentRoom.getKey() != null)
			{
				player1.setKey(currentRoom.getKey());
				currentRoom.clearKey();
				System.out.println("OK");
			}
			else
				System.out.println("No key present");
		}
		
		/*
		 * open chest
		 */
		else if (nextMove.equalsIgnoreCase("open chest"))
		{
			if (currentRoom.getChest() != null)
			{
				if (!(currentRoom.getChest().isLocked()))
				{
					System.out.println(currentRoom.getChest().getContents());
					System.exit(0);
				}
				
				else
					System.out.println("The chest is locked");
			}
			
			else
				System.out.println("No chest to unlock");
		}
		
		/*
		 * unlock chest
		 */
		else if (nextMove.equalsIgnoreCase("unlock chest"))
		{
			if (currentRoom.getChest() != null)
			{
				if (player1.getKey() != null)
				{
					currentRoom.getChest().unLock(player1.getKey());
				}
				else
					System.out.println("Need a key to do any unlocking");
			}
			
			else
				System.out.println("No chest to unlock");
		}
		
		
		else
		executeNextMove(player1);

		}
	}
	
	private static void showDetails()
	{

		if (currentRoom.getLamp() != null)
			System.out.println("There is an old oil lamp here that was made long ago");
		
		if (currentRoom.getKey() != null)
			System.out.println("You see the outline of a key on a dusty shelf that's covered in dust");
		
		if (currentRoom.getChest() != null)
			System.out.println("There is a large, wooden, massive, oaken chest here with the word \"CHEST\" carved into it");
			
		System.out.print("Exits are: ");
		if (currentRoom.canGoEast())
			System.out.print("EAST ");
		if (currentRoom.canGoWest())
			System.out.print("WEST ");
		if (currentRoom.canGoNorth())
			System.out.print("NORTH ");
		if (currentRoom.canGoSouth())
			System.out.print("SOUTH");
		System.out.println("");
	}
	private static void getNextMove(){
	

		Scanner keyboard = new Scanner(System.in);
		nextMove = keyboard.nextLine();
	}
	
	/**
	 * 
	 * @param player will have X or Y coordinate updated based on user's input
	 */
	private static void executeNextMove(Player player)
	{
		
			if (nextMove.equalsIgnoreCase("east") && currentRoom.canGoEast())	
				player.setY(player.getY() + 1);
			
			else if (nextMove.equalsIgnoreCase("west") && currentRoom.canGoWest())		
				player.setY(player.getY() - 1);
				
			else if (nextMove.equalsIgnoreCase("north") && currentRoom.canGoNorth())		
				player.setX(player.getX() - 1);
			
			else if (nextMove.equalsIgnoreCase("south") && currentRoom.canGoSouth())
				player.setX(player.getX() + 1);
			
			else
				System.out.println("Can't go that way");
			
			look = false;

	}
	
}
