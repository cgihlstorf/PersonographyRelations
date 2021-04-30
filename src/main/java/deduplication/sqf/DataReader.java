package deduplication.sqf;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReaderHeaderAware;

public class DataReader {
	CSVReaderHeaderAware reader;
	ArrayList<String[]> myEntries ;
	
	public DataReader(String csv) {
		try {
			reader = new CSVReaderHeaderAware(new FileReader(csv));
			myEntries = new ArrayList<String[]>(reader.readAll());
	    	reader.close(); 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<String[]> getMyEntries(){
		return myEntries;
	}
	
	public ArrayList<String[]> allPairsDeduplication() { //ArrayList<E> ???
		for(int i = 0; i < myEntries.size(); i++) {
			String[] voterInfo = myEntries.get(i);
			Voter voter = new Voter(voterInfo);
			for(int j = 0; j < myEntries.size(); j++) {
				String[] voterInfo2 = myEntries.get(j);
				Voter voter2 = new Voter(voterInfo2);
				if(i == j) 
					continue;
				else
					if(voter.compareTo(voter2) == 0) {
						myEntries.remove(j);
						j--; //MAYBE ???
					}
			}
		}
		
		return myEntries;
	}
}
