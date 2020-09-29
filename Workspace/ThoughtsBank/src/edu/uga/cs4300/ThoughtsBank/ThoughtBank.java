package edu.uga.cs4300.ThoughtsBank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThoughtBank {

	private Map<String,List<Thought>> tbank;
	
	public ThoughtBank() {
		
		tbank = new HashMap<String, List<Thought>>();
	}
	
	public void addThought(String user, Thought thought){
		List<Thought> thoughts;
		
		if (tbank.get(user)==null){
			thoughts = new ArrayList<Thought>();
		}
		else
			thoughts = tbank.get(user);
		
		thoughts.add(thought);
		
		tbank.put(user, thoughts);
		
	}
	
	public List<Thought> getThoughts(String user){
		
		return tbank.get(user);
	}
	
	public void clearThoughts(String user){
		tbank.remove(user);
		
	}
	
}
