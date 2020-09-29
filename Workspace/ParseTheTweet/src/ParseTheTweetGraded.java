/*
* [Ruijie Xu].java
* Author: [Ruijie Xu]
* Submission Date: [08/30/2017]
*
* Purpose: A brief paragraph description of the
* program. What does it do?
* This programs allows information extracts from the tweets efficiently by divde
* info in a tweet into five categories of data. This can help information organized 
* quickly during a disaster.
* Statement of Academic Honesty:
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
* of Computer Science at the University of Georgia. 
*/
import java.util.Scanner;
public class ParseTheTweetGraded {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		String tweet = keyboard.nextLine();
		String type, loca, detail, latit, longit;
		
		int startTyp, finishTyp;
		startTyp = tweet. indexOf("#"); //# is the start of the type substring
		finishTyp = tweet. indexOf(";");//; is the end index, but won't be included in the substring
		type = tweet.substring(startTyp, finishTyp);
		String typeValue;
		typeValue = type.substring(5, finishTyp);
		typeValue = typeValue.toUpperCase();
		System.out.println("Type:\t\t" + typeValue);
		
		
		String tweetNoTyp;
		tweetNoTyp = tweet.substring(finishTyp+2);/* starting from finishTyp 
		counting for index, StartDet starts two places after finishTyp from index
		number 2, which is after the semicolon and the whitespace.*/
		
		int startDet, finishDet;
		startDet = tweetNoTyp. indexOf("#");
		finishDet = tweetNoTyp.indexOf(";");
		detail = tweetNoTyp.substring(startDet, finishDet);// #det value
		String detValue;
		detValue = detail.substring(5,finishDet);// value only
		detValue = detValue.replace(",","-");
		System.out.println ("Detail:\t\t" + detValue);
		
		
		String tweetNoDet; /* this step is for discard the string type
		and string detail.*/
		tweetNoDet = tweetNoTyp.substring(finishDet+2);
		
		/* repeating the coding methods used in string detail and string type. 
		 * starting with discard the string already can have value printed out and start 
		 * with rest of the string as working on a new string
		 */
		
		int startLoc, finishLoc; // variable for start and end indices
		startLoc = tweetNoDet. indexOf("#");
		finishLoc = tweetNoDet.indexOf(";");
		loca = tweetNoDet.substring(startLoc, finishLoc);//int indices use for substring
		String locValue;
		locValue = loca.substring(5,finishLoc);
		locValue = locValue.replace(",", "-");//prevent obstacle for further processing
		System.out.println ("Location:\t" + locValue);
		
		String tweetNoLoc;
		tweetNoLoc = tweetNoDet.substring(finishLoc+2);
		
		int startLat, finishLat;
		startLat = tweetNoLoc. indexOf("#");
		finishLat = tweetNoLoc.indexOf(";");
		latit = tweetNoLoc.substring(startLat, finishLat);
		String latValue;
		latValue = latit.substring(5,finishLat);
		System.out.println ("Latitude:\t" + latValue);
		
		String tweetNoLat;
		tweetNoLat = tweetNoLoc.substring(finishLat+2);
		
		int startLong, finishLong;
		startLong = tweetNoLat. indexOf("#");
		finishLong = tweetNoLat.indexOf(";");
		longit = tweetNoLat.substring(startLong, finishLong);
		String longValue;
		longValue = longit.substring(5,finishLong);
		System.out.println ("Longitude:\t" + longValue);
		

	}

	

}
