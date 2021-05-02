package deduplication.sqf;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReaderHeaderAware;

public class DataReader {
	CSVReaderHeaderAware reader;
	ArrayList<Voter> voterEntries = new ArrayList<Voter>();
	
	public DataReader(String csv) {
		try {
			reader = new CSVReaderHeaderAware(new FileReader(csv));
			ArrayList<String[]> csvEntries = new ArrayList<String[]>(reader.readAll());//create new arraylist
	    	reader.close(); 
	    	for (int i = 0; i < csvEntries.size(); i++) {
	    		Voter voter = new Voter (csvEntries.get(i));
	    		voterEntries.add(voter);
	    		//System.out.println(voter.toString());
	    	}
	    	System.out.println(csvEntries.size());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch bloc
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Voter> getVoterEntries(){
		return voterEntries;
	}
	
	public ArrayList<Voter> allPairsDeduplication() { 
		boolean noDuplicate = true;
		ArrayList<Voter> deduplicatedEntries = new ArrayList<Voter>();
		for(int i = 0; i < voterEntries.size(); i++) {
			for(int j = 0; j < deduplicatedEntries.size(); j++) {//create new ArrayList, add if not same person
				if(voterEntries.get(i).compareTo(deduplicatedEntries.get(j)) == 0) {
					noDuplicate = false;
					//System.out.println(voterEntries.get(i));
					//post on Piazza about this?
					break;
				}
			}
			if(noDuplicate == true)
				deduplicatedEntries.add(voterEntries.get(i));
		}
		return deduplicatedEntries;
	}
	//ask about what the project part means
	public ArrayList<Voter> hashLinearDeduplication(){//make sure this works
		ProbeHashMap<String, Voter> voterMap = new ProbeHashMap<String, Voter>(1000003);
		for (int i = 0; i < voterEntries.size(); i++) {
			voterMap.bucketPut(i, voterEntries.get(i).toString(), voterEntries.get(i));
		}
		ArrayList<Voter> outputList = new ArrayList<Voter>();
		for (int i = 0; i < voterMap.size(); i++) {
			outputList.add(voterMap.bucketGet(i, voterEntries.get(i).toString()));
		}
		return outputList;
	}
	
	public ArrayList<Voter> quicksortDeduplication(){//is this correct?
		Voter pivotVoter = voterEntries.get(voterEntries.size() - 1);	
		for (int i = 0; i < voterEntries.size() -1 ; i++) {
			if (voterEntries.get(i).compareTo(voterEntries.get(i + 1)) > 0) {
				break;
			}
		}
		if (pivotVoter.compareTo(voterEntries.get(voterEntries.indexOf(pivotVoter) - 1)) < 0) {
			Voter temp = pivotVoter;
			voterEntries.remove(voterEntries.indexOf(pivotVoter));
			voterEntries.set(voterEntries.indexOf(pivotVoter), voterEntries.get(voterEntries.indexOf(pivotVoter) - 1));
			voterEntries.remove(voterEntries.indexOf(pivotVoter) - 1);//can we use indexOf?
			voterEntries.set(voterEntries.indexOf(pivotVoter) - 1, temp);//come back to this!!
			
		}
		
		quicksortDeduplication();
		
		return voterEntries;//?
	}
	
}























