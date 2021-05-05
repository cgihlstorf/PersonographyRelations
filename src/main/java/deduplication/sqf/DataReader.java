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
			voterMap.put(voterEntries.get(i).toString(), voterEntries.get(i));
		}
		ArrayList<Voter> outputList = new ArrayList<Voter>();
		for (int i = 0; i < voterMap.size(); i++) {
			outputList.add(voterMap.get(voterEntries.get(i).toString()));
		}
		return outputList;
	}
	
	public ArrayList<Voter> quicksortDeduplication(){
		quickSort(0, voterEntries.size()-1);
		for (int i = 0; i < voterEntries.size() -1; i++) {
			if (voterEntries.get(i).compareTo(voterEntries.get(i + 1)) == 0){
				voterEntries.remove(i);
			}
		}
		return voterEntries;
	}
	
	public void quickSort(int index1, int index2) {
		for(int i = 0; i < voterEntries.size(); i++) {
			if(voterEntries.get(i).compareTo(voterEntries.get(i+1)) > 0){
				int pivotIndex = partition(index1, index2);
				if(pivotIndex == index1 && pivotIndex == index2) break;
				else if (pivotIndex == index1 && pivotIndex != index2) quickSort(pivotIndex,index2);
				else if (pivotIndex != index1 && pivotIndex == index2) quickSort(index1, pivotIndex);
				else {
				quickSort(index1, pivotIndex);
				quickSort(pivotIndex,index2);
				}
			}
		}
	}
	
	public int partition (int index1, int index2) {
		Voter pivotVoter = voterEntries.get(index2);
		int i = 0;
		int j = -1;
		for(i = 0; i < index2-index1-1; i++) {
			if (pivotVoter.compareTo(voterEntries.get(i)) > 0) {
				j++;
				swap(voterEntries.get(i), voterEntries.get(j));
			}
			swap(voterEntries.get(index2), voterEntries.get(j+1));
		}
		return voterEntries.indexOf(pivotVoter);
	}
	
	public void swap (Voter voter1, Voter voter2) {
		int voter1Index = voterEntries.indexOf(voter1);
		voterEntries.set(voterEntries.indexOf(voter2), voter1);
		voterEntries.set(voter1Index, voter2);
	}
	
}























