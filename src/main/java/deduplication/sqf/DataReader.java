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
	    	//System.out.println(csvEntries.size());
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
		boolean duplicate = false;
		ArrayList<Voter> deduplicatedEntries = new ArrayList<Voter>();
		for(int i = 0; i < voterEntries.size(); i++) {
			for(int j = 0; j < deduplicatedEntries.size(); j++) {//create new ArrayList, add if not same person
				if(voterEntries.get(i).compareTo(deduplicatedEntries.get(j)) == 0) {
					duplicate = true;
					//System.out.println(voterEntries.get(i));
					break;
				}
			}	
			if(duplicate == false)
				deduplicatedEntries.add(voterEntries.get(i));
			duplicate = false;
		}
		return deduplicatedEntries;
	}
	
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
	
	ArrayList<Voter> temp = new ArrayList<Voter>();
	
	public ArrayList<Voter> quicksortDeduplication(){//is this correct?
		ArrayList<Voter> quicksortOutput = new ArrayList<Voter>();
		ArrayList<Voter> l = new ArrayList<Voter>();
		ArrayList<Voter> e = new ArrayList<Voter>();
		ArrayList<Voter> g = new ArrayList<Voter>();
		if (l.size() == 0 && e.size() == 0 && g.size() == 0) {
			temp = voterEntries;
		}
		Voter pivotVoter = temp.get(temp.size() - 1);
		for (int i = 0; i < temp.size() -1 ; i++) {
			if (pivotVoter.compareTo(temp.get(i)) < 0) {
				g.add(temp.get(i));
			}else if(pivotVoter.compareTo(temp.get(i)) > 0) {
				l.add(temp.get(i));
			}else
				e.add(temp.get(i));
		}
		
		if(l.size() > 1 && l.size() < voterEntries.size() - 1 - g.size() - 1 - e.size() - 1) {
			temp = l;
			quicksortDeduplication();
		} else if(g.size() > 1 && g.size() < voterEntries.size() - 1 - l.size() - 1 - e.size() - 1) {
			temp = g;
			quicksortDeduplication();
		}
		
		for (int i = 0; i < l.size(); i++) {
			quicksortOutput.add(l.get(i));
		}
		for (int i = 0; i < e.size(); i++) {
			quicksortOutput.add(e.get(i));
		}
		for (int i = 0; i < g.size(); i++) {
			quicksortOutput.add(g.get(i));
		}
		deduplicateSorted(quicksortOutput);
		return quicksortOutput;//why id this originally work for us?
	}

	public ArrayList<Voter> deduplicateSorted (ArrayList<Voter> quicksortOutput){//do we really need this method, also why aren't things printing?
		//System.out.println(quicksortOutput.size());
		for (int i = 0; i < quicksortOutput.size() -1; i++) {
			if (quicksortOutput.get(i).compareTo(quicksortOutput.get(i + 1)) == 0){
				quicksortOutput.remove(i);
			}
		}
		//System.out.println(quicksortOutput.size());
		return quicksortOutput;
	}
	
}























