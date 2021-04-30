package deduplication.sqf;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReaderHeaderAware;

public class DataReader {
	CSVReaderHeaderAware reader;
	ArrayList<Voter> voterEntries ;
	
	public DataReader(String csv) {
		try {
			reader = new CSVReaderHeaderAware(new FileReader(csv));
			ArrayList<String[]> csvEntries = new ArrayList<String[]>(reader.readAll());//create new arraylist
	    	reader.close(); 
	    	for (int i = 0; i < csvEntries.size(); i++) {
	    		Voter voter = new Voter (csvEntries.get(i));
	    		voterEntries.add(voter);
	    	}
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
		ArrayList<Voter> deduplicatedEntries = new ArrayList<Voter>();
		for(int i = 0; i < voterEntries.size(); i++) {
			for(int j = 0; j < voterEntries.size(); j++) {//create new ArrayList, add if not same person
				if(voterEntries.get(i) == voterEntries.get(j) || voterEntries.get(i).compareTo(voterEntries.get(j)) == 0) 
					continue;
				else
					deduplicatedEntries.add(voterEntries.get(i));
			}
		}
		return deduplicatedEntries;
	}
}
