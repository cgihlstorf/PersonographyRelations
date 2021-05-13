package deduplication.sqf;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReaderHeaderAware;

/**
 * This class contains two constructors — one which takes in a String of a csv file and reads the file into an ArrayList of Voters, 
 * and another which takes in a String of a csv file and an integer dictating how much of the csv file will be read. This class has
 * three different methods which iterate through the ArrayList of Voters to identify any duplicate Voters in the ArrayList (done using
 * the compareTo method in the Voter class). Each method then returns an ArrayList without any of the duplicate Voters;
 * are then removed from the list 
 * 
 * @authors Caroline Gihlstorf and Jenna Krussman
 * @version May 11, 2021
 *
 */

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
	
	public void setVoterEntries(ArrayList<Voter> voterEntries) {
		this.voterEntries = voterEntries;
	}
	
	/**
	 * This method uses a nested for loop to iterate through each element of voterEntries and compare each element to the remaining elements
	 * of the ArrayList to search for duplicates. When a duplicate is found (identified using the compareTo method in the Voter class),
	 * a boolean variable is set to true. If no duplicate is found at the end of a loop, the element is added to a new ArrayList containing
	 * no duplicates, which is returned at the end of the method.
	 * 
	 * @return deduplicatedEntries
	 */
	public ArrayList<Voter> allPairsDeduplication() { 
		boolean duplicate = false;
		ArrayList<Voter> deduplicatedEntries = new ArrayList<Voter>();
		for(int i = 0; i < voterEntries.size(); i++) {
			for(int j = 0; j < deduplicatedEntries.size(); j++) {//create new ArrayList, add if not same person
				if(voterEntries.get(i).compareTo(deduplicatedEntries.get(j)) == 0) {
					duplicate = true;
					break;
				}
			}	
			if(duplicate == false)
				deduplicatedEntries.add(voterEntries.get(i));
			duplicate = false;
		}
		return deduplicatedEntries;
	}
	
	/**
	 * This method stores elements of the voterEntries ArrayList in a HashMap. The method then creates a
	 * new ArrayList of Voters and iterates through the HashMap, adding the Voter at each key to the new ArrayList.
	 * The method then returns this ArrayList.
	 * 
	 * @return outputList
	 */
	public ArrayList<Voter> hashLinearDeduplication(){
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
	
	/**
	 * This method creates a new ArrayList of Voters and calls the quicksort() method on voterEntries.
	 * The method then iterates through the sorted voterEntries ArrayList and compares each element to the
	 * element following to check for duplicates. If the two elements are not duplicates, the first element
	 * is added to the new ArrayList. The method returns this ArrayList.
	 * 
	 * @return newList
	 */
	public ArrayList<Voter> quicksortDeduplication(){
		ArrayList<Voter> newList = new ArrayList<Voter>();
		quickSort(0, voterEntries.size() - 1);
		for (int i = 0; i < voterEntries.size() - 1; i++) {
			if (voterEntries.get(i).compareTo(voterEntries.get(i + 1)) != 0){
				newList.add(voterEntries.get(i));
			}
		}
		newList.add(voterEntries.get(voterEntries.size()-1));
		return newList;
	}
	
	/**
	 * This method takes in two indices and shorts them using the quick sort recursion method
	 * 
	 * @param index1 the first index
	 * @param index2 the last index
	 */
	public void quickSort(int index1, int index2) {	
		if(index1 < index2) {
			int pivotIndex = partition(index1, index2);
			quickSort(index1, pivotIndex - 1);
			quickSort(pivotIndex + 1, index2);
		}
	}
	
	/**
	 * This method looks at the pivot and organizes the elements so that the elements to the left are less then the pivot
	 * and the elements to the right are greater than the pivot
	 * 
	 * @param lowIndex the low index of the section of the array being sorted
	 * @param highIndex the end index of the section of the array being sorted
	 */
	public int partition (int lowIndex, int highIndex) {
		Voter pivotElement = voterEntries.get(highIndex);
		while(highIndex != lowIndex) {
			if (voterEntries.get(lowIndex).compareTo(pivotElement) == 0) {//if lowIndex is greater than/to the right of highIndex
				if (voterEntries.get(highIndex).compareTo(voterEntries.get(lowIndex)) < 0){//highIndex less than lowIndex
					swap(highIndex, lowIndex);
				}
				else {
					highIndex--;
				}
			}
			else if(voterEntries.get(highIndex).compareTo(pivotElement) == 0) {//if lowIndex is less than/to the left of highIndex
				if (voterEntries.get(highIndex).compareTo(voterEntries.get(lowIndex)) < 0){
					swap(highIndex, lowIndex);
				}
				else {
					lowIndex++;
				}
			}
		}
		return highIndex;
	}
	
	/**
	 * This method takes in two integer indices and swaps the elements at each index. 
	 * 
	 * @param index1
	 * @param index2
	 */
	public void swap (int index1, int index2) {
		Voter tempVoter = voterEntries.get(index1);
		voterEntries.set(index1, voterEntries.get(index2));
		voterEntries.set(index2, tempVoter);
	}
	
}