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
		ArrayList<Voter> newList = new ArrayList<Voter>();
		System.out.println("test");
		quickSort(0, voterEntries.size() - 1);
		for (int i = 0; i < voterEntries.size() - 1; i++) {
			if (voterEntries.get(i).compareTo(voterEntries.get(i + 1)) != 0){
				newList.add(voterEntries.get(i));
			}
		}
		return newList;
	}
	
	public void quickSort(int index1, int index2) {	
		int pivotIndex = partition(index1, index2);
		if(index1 < index2) {
			quickSort(index1, pivotIndex - 1);
			quickSort(pivotIndex + 1, index2);
		}
	}
//		for(int i = 0; i < voterEntries.size() - 1; i++) {	
//			if(voterEntries.get(i).compareTo(voterEntries.get(i+1)) > 0){
//				if(index2 - index1 == 1) {
//					if(voterEntries.get(index1).compareTo(voterEntries.get(index2)) > 0) {
//						swap(voterEntries.get(index1), voterEntries.get(index2));
//						swap(index1, index2);
//					}
//					break;
//				}
//				int pivotIndex = partition(index1, index2);
////				else if(pivotIndex == index1 && pivotIndex == index2) break;//see if this is needed
//				if(pivotIndex-1 == index1 && pivotIndex+1 == index2) break;
//				else if (pivotIndex-1 == index1 && pivotIndex+1 != index2) quickSort(pivotIndex + 1,index2);
//				else if (pivotIndex-1 != index1 && pivotIndex+1 == index2) quickSort(index1, pivotIndex - 1);
////				else {
//				}
//			}
//		}

	
	public int partition (int pivotIndex, int otherIndex) {
		Voter pivotElement = voterEntries.get(pivotIndex);
		while(pivotIndex != otherIndex) {
			if ((voterEntries.get(pivotIndex).equals(pivotElement) || voterEntries.get(otherIndex).equals(pivotElement)) && pivotIndex - otherIndex < 0) {//if otherIndex is greater than/to the right of pivotIndex
				if (voterEntries.get(pivotIndex).compareTo(voterEntries.get(otherIndex)) < 0){//pivotIndex less than otherIndex
					swap(voterEntries.get(pivotIndex), voterEntries.get(otherIndex));
					swap(pivotIndex, otherIndex);
				}
				else if (voterEntries.get(pivotIndex).compareTo(voterEntries.get(otherIndex)) > 0) {
					pivotIndex--;
				}
			}
			else if((voterEntries.get(pivotIndex).equals(pivotElement) || voterEntries.get(otherIndex).equals(pivotElement)) && pivotIndex - otherIndex > 0) {//if otherIndex is less than/to the left of pivotIndex
				if (voterEntries.get(pivotIndex).compareTo(voterEntries.get(otherIndex)) < 0){
					otherIndex++;
					
				}
				else if (voterEntries.get(pivotIndex).compareTo(voterEntries.get(otherIndex)) > 0) {
					swap(voterEntries.get(pivotIndex), voterEntries.get(otherIndex));
					swap(pivotIndex, otherIndex);
				}
			}
		}
		return pivotIndex;
	}
	
	public void swap (Voter voter1, Voter voter2) {
		int voter1Index = voterEntries.indexOf(voter1);
		voterEntries.set(voterEntries.indexOf(voter2), voter1);
		voterEntries.set(voter1Index, voter2);
	}
	
	public void swap (int index1, int index2) {
		int temp = index1;
		index1 = index2;
		index2 = temp;
	}
	
}























