package deduplication.sqf;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReaderHeaderAware;

public class DataReader {
	CSVReaderHeaderAware reader;
	private ArrayList<Voter> voterEntries = new ArrayList<Voter>();
	
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
	
	public DataReader(String csv, int end) {
		try {
			reader = new CSVReaderHeaderAware(new FileReader(csv));
			ArrayList<String[]> csvEntries = new ArrayList<String[]>(reader.readAll());//create new arraylist
	    	reader.close(); 
	    	for (int i = 0; i < end; i++) {
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
	
	public void setVoterEntries(ArrayList<Voter> voterEntries) {
		this.voterEntries = voterEntries;
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
			voterMap.put(voterEntries.get(i).toString(), voterEntries.get(i));
		}
		ArrayList<Voter> outputList = new ArrayList<Voter>();
		for (int i = 0; i < voterMap.size(); i++) {
			outputList.add(voterMap.get(voterEntries.get(i).toString()));
		}
		return outputList;
	}
	
	public ArrayList<Voter> quicksortDeduplication(){
		ArrayList<Voter> newList = new ArrayList<Voter>();
		quickSort(0, voterEntries.size() - 1);
		for (int i = 0; i < voterEntries.size() - 1; i++) {
			if (voterEntries.get(i).compareTo(voterEntries.get(i + 1)) != 0){
				newList.add(voterEntries.get(i));
			}
		}
		return newList;
	}
	
	public void quickSort(int index1, int index2) {	
		if(index1 < index2) {
			int pivotIndex = partition(index1, index2);
			quickSort(index1, pivotIndex - 1);
			quickSort(pivotIndex + 1, index2);
		}
	}
	
	public int partition (int lowIndex, int highIndex) {
		Voter pivotElement = voterEntries.get(highIndex);
		while(highIndex != lowIndex) {
			if ((voterEntries.get(highIndex).compareTo(pivotElement) == 0|| voterEntries.get(lowIndex).compareTo(pivotElement) == 0) 
					&& highIndex - lowIndex < 0) {//if lowIndex is greater than/to the right of highIndex
				if (voterEntries.get(highIndex).compareTo(voterEntries.get(lowIndex)) < 0){//highIndex less than lowIndex
					swap(highIndex, lowIndex);
					int tempIndex = highIndex;
					highIndex = lowIndex;
					lowIndex = tempIndex;
					pivotElement = voterEntries.get(highIndex);
				}
				else {//if (highIndex > 0 && voterEntries.get(highIndex).compareTo(voterEntries.get(lowIndex)) > 0) {
					lowIndex--;
				}
			}
			else if((voterEntries.get(highIndex).compareTo(pivotElement) == 0|| voterEntries.get(lowIndex).compareTo(pivotElement) == 0) 
					&& highIndex - lowIndex > 0) {//if lowIndex is less than/to the left of highIndex
				if (voterEntries.get(highIndex).compareTo(voterEntries.get(lowIndex)) < 0){
					swap(highIndex, lowIndex);
					int tempIndex = highIndex;
					highIndex = lowIndex;
					lowIndex = tempIndex;
					pivotElement = voterEntries.get(highIndex);
				}
				else {//if (voterEntries.get(highIndex).compareTo(voterEntries.get(lowIndex)) > 0) {
					lowIndex++;
				}
			}
		}
		return highIndex;
	}
	
	public void swap (int index1, int index2) {
		Voter tempVoter = voterEntries.get(index1);
		voterEntries.set(index1, voterEntries.get(index2));
		voterEntries.set(index2, tempVoter);
	}
}























