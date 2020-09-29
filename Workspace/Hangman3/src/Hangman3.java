import java.util.Scanner;/*17 lines when crammed together,127 when "formatted" */public class Hangman3{
static  boolean TM=true; static boolean T=true;static boolean F=false;public static void main(String[]args){boolean wTKP=T;int GP=1;Scanner in=new 
Scanner(System.in);for(int s=2,g=10,iG=0;(GP<=20&&wTKP);GP++){boolean dC=T;do{out("Select dificulty: Easy(e),Intermediate(i),Hard(h):");String diff=in
.nextLine();if(diff.charAt(0)=='h'||diff.charAt(0)=='i'||diff.charAt(0)=='e'){dC=T;if(diff.charAt(0)!='h'){g=(diff.charAt(0)=='i')?12:15;s=(diff.charAt
(0)=='i')?3:4;}}else{out("Invalid.");dC=F;}}while(!dC);String sW=RandomWord.newWord(),dW="";int gR=g; for(byte j=0;j<sW.length();j++)dW=dW+"-";if(TM)
out("Secret word is:"+sW);out("The word is:"+dW);while(gR>0&&!dW.equals(sW)){String gS;char gC=' ';boolean sGC=F,gIS=F,inIsL=F;while(!inIsL){out(
"Please guess a letter or \"solve\":");gS=(in.nextLine()).toLowerCase();gC=gS.charAt(0);if(Character.isDigit(gC)){inIsL=F;out("\nThe guess must be a "
+"letter or \"solve\"\nGuesses remaining:"+gR);}else if(gS.equals("solve")){gIS=T;inIsL=T; out("\nSolve the word");String solveGuess=(in.nextLine()).
trim();sGC=(solveGuess.equals(sW)?T:F);}else inIsL=T;}if(!gIS)out("What spaces do you want to check?");String sS="";boolean sCP=F;while(!sCP&&!gIS){
out("Please enter "+s+" numbers seperated by spaces");sS=in.nextLine().trim();sS+=" ";boolean kC=T;for(int l=0;l<sS.length()&&kC;l+=2)if(!Character.
isDigit(sS.charAt(l))||(sS.charAt(l+1)!=' ')||Character.getNumericValue(sS.charAt(l))>sW.length()-1||sS.length()>(s*2))kC=F;if(kC)sCP=T;} boolean 
gIW=F;for(int i=0,k=0;k<s&&!gIS;i+=2,k++){iG=Character.getNumericValue(sS.charAt(i));if(sW.charAt(iG)==gC){gIW=T;dW=dW.substring(0,iG)+gC+dW.substring
(iG+1);}}if(!gIS){if(gIW)out("Your guess is in the word!");else{out("Your guess was not found");gR--;}out("The updated word is:"+dW+"\nGuesses "
+"remaining:"+gR);}if(gIS){if(sGC){out("You guessed the word");dW=sW; }else if(!sGC){gR--;inIsL=F;gIS=F;out("Incorrect\nGuesses remaining:"+gR);}}}
for(;;){if(dW.equals(sW))out("You win!\nPlay again? Yes(y) or No(n)");else if(gR==0)out("You have failed to guess the word.\n Play again? Yes(y) or "
+"No(n)");String kPB=in.nextLine();if(kPB.charAt(0)=='y'||kPB.charAt(0)=='n'){wTKP=(kPB.charAt(0)=='y')?T:F;break;}else out("Invalid");}}if(GP==20)
out("Only 20 games allowed");else out("Thank you for playing. Goodbye.");in.close();return;}static void out(String m){System.out.println(m);}}