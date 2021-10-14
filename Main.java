package relations;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.opencsv.CSVReaderHeaderAware;

/**
 * This class uses a CSV reader to read a list of data about each person listed and
 * uses methods in the Person and Relationship objects and in this class to format 
 * and print a list of people and their relationships, if any, to other people on the list.
 * 
 * @author Caroline Gihlstorf
 *
 */
public class Main {
	public static void main (String[] args) throws FileNotFoundException, IOException {
		CSVReaderHeaderAware reader = new CSVReaderHeaderAware(new FileReader("/Users/caroline/eclipse-workspace/PersonographyRelationships/src/main/java/relations/Ticha Names - Sheet1.csv"));
		ArrayList<String[]> myData = new ArrayList<String[]>(reader.readAll());
		reader.close();
		Person[] personArray = new Person[myData.size()];
		for (int i = 0; i < personArray.length; i++) {
			Person person = new Person(myData.get(i));
			personArray[i] = person;
		}
		readAddRelations("/Users/caroline/eclipse-workspace/PersonographyRelationships/src/main/java/relations/TichaRelationshipsCSV - Sheet1.csv", personArray);
		for(Person p:personArray) {//for each person, create an ArrayList of their relationships
			ArrayList<Relation> r = p.getRelations();
			System.out.println(p.getName() + "(" + "ID: " + p.getID() + ")" + ":");
			if (r.size() > 0) {//if the person has at least one relationship, print their relationships
				for(int i = 0; i < r.size(); i++) {
					System.out.println(r.get(i));//it still prints the header...
				}
				System.out.println("");//I can put IDs for everyone in the relations string too
			}
			else {
				System.out.println("No relations to show yet (this doesn't mean there aren't any)");
				System.out.println("");
			}
		}
	}
	
	/** 
	 * This method takes in the IDs of two people the relationship of the first person relative to the 
	 * second person (i.e.: person1 is the [relationship] of person2), and an array of Person objects.
	 * It searches through the array of Person objects and assigns person1 to the object that shares the first ID entered.
	 * It assigns person2 to the object that shares the second ID entered. If both person1 and person2 have values,
	 * the addRelation function is called with person1 and person2 as the arguments, respectively.
	 * 
	 * @param ID1
	 * @param relation
	 * @param ID2
	 * @param people
	 */
	public static void addRel(String ID1, String relation, String ID2, Person[] people) {//rename method?
		Person person1 = null;//you could use a hash map for this...
		Person person2 = null;
		for (Person p: people) {
			if(p.getID().equals(ID1)) {
				person1 = p;
			}
			if(p.getID().equals(ID2)) {
				person2 = p;
			}
		}
		if(person1 != null && person2 != null) {//if both person1 and person2 have values, add person2 to person1's relationships
			person1.addRelation(person2, relation);
		}
	}
	
	/**
	 * This method reads a CSV file and inputs each row of data into an ArrayList of String arrays.
	 * For each String array in the ArrayList, the addRel function is called using the Strings in the array as parameters.
	 * The people listed in the CSV file are then given relationships to one another according to the data iin the file.
	 * @param file
	 * @param personArray
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void readAddRelations(String file, Person[] personArray) throws FileNotFoundException, IOException{//rename method
		CSVReaderHeaderAware relationReader = new CSVReaderHeaderAware(new FileReader(file));
		ArrayList<String[]> fileData = new ArrayList<String[]>(relationReader.readAll());
		relationReader.close();
		for(int i = 0; i < fileData.size(); i++) {//for each line of the data file, 
			//add the relations specified for the people listed using the addRel() method
			addRel(fileData.get(i)[0], fileData.get(i)[1], fileData.get(i)[2], personArray);
		}
	}
}
