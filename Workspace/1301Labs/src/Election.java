
public class Election {

	public static String presidentCandidate1;
	public static String presidentCandidate2;
	public static String vicePresidentCandidate1;
	public static String vicePresidentCandidate2;
	public static int numVotesPres1 = 0;
	public static int numVotesPres2 = 0;
	public static int numVotesVicePres1 = 0;
	public static int numVotesVicePres2 = 0;
	private java.util.Scanner in = new java.util.Scanner(System.in);
	
	public Election(String p1, String p2, String vp1, String vp2){
		presidentCandidate1 = p1;
		presidentCandidate2 = p2;
		vicePresidentCandidate1 = vp1;
		vicePresidentCandidate2 = vp2;
	}
	public int getVoteForPresident(){
		System.out.println("Who's your vote for president");
		System.out.println(getPresidentCandidates());
		return in.nextInt();
	}
	
	public int getVoteForVicePresident(){
		System.out.println("Who's your vote for vice president");
		System.out.println(getVicePresidentCandidates());
		return in.nextInt();
	}
	
	public static int getNumberVotes(String candidate){
		if (candidate.equalsIgnoreCase(presidentCandidate1))
			return numVotesPres1;
		else if (candidate.equalsIgnoreCase(presidentCandidate2))
			return numVotesPres2;
		else if (candidate.equalsIgnoreCase(vicePresidentCandidate1))
			return numVotesVicePres1;
		else if (candidate.equalsIgnoreCase(vicePresidentCandidate2))
			return numVotesVicePres2;
		else 
			return -1;
	}
	
	public static void countVoteForPresident(int vote){
		switch(vote){
		case 1 : 
			numVotesPres1 ++; 
			break;
		case 2: 
			numVotesPres2 ++; 
			break;
		default: System.out.println("error");
		}
	}
	
	public static void countVoteForVicePresident(int vote){
		switch(vote){
		case 1 : 
			numVotesVicePres1 ++; 
			break;
		case 2 : 
			numVotesVicePres2 ++; 
			break;
		default: System.out.println("error");
		}
	}
	public static String getPresidentCandidates(){
		return "1:" + presidentCandidate1 + "\n2:" + presidentCandidate2;
	}
	
	public static String getVicePresidentCandidates(){
		return "1:" + vicePresidentCandidate1 + "\n2" + vicePresidentCandidate2;
	}
	
	public void getNewVote(){
		countVoteForPresident(getVoteForPresident());
		countVoteForVicePresident(getVoteForVicePresident());
	}
	
	public static void getResults(){
		System.out.println(presidentCandidate1 + " recieved " + getNumberVotes(presidentCandidate1));
		System.out.println(presidentCandidate2 + " recieved " + getNumberVotes(presidentCandidate2));
		System.out.println(vicePresidentCandidate1 + " recieved " + getNumberVotes(vicePresidentCandidate1));
		System.out.println(vicePresidentCandidate2 + " recieved " + getNumberVotes(vicePresidentCandidate2));
	}
	
	public static void main(String[] args){
		Election e = new Election("pres1", "pres2", "vp1", "vp2");
		
		for (int i = 0; i < 2; i ++){
			e.getNewVote();
		}
		
		Election.getResults();
	}
}
