package relations;

import java.util.ArrayList;
//this is the most updated version if this code!
import java.util.Arrays;

/**
 * This class takes in either a String array of information about a person or individual variables storing
 * that same information, and stores them. When printed, the Person object lists these attributes.
 * @author Caroline Gihlstorf
 *
 */
public class Person {
	//FIELDS
	private String name;//getters and setters!
	private String ID;
	private ArrayList<String> textsIncluding;
	private String description;
	private String assumedSex;//enum???-->make sure to do something with this
	private ArrayList<Relation> relationships;
	
	//CONSTRUCTOR
	public Person(String[] peopleData) {//this data will come from a CSV reader in Main
		name = peopleData[0];
		ID = peopleData[1];
		textsIncluding = new ArrayList<String>();
		String[] tempTexts = peopleData[3].split(",");//split the String of text IDs into separate text IDs to add individually to textsIncluding
		for (int i = 0; i < tempTexts.length; i++) {
			textsIncluding.add(i, tempTexts[i]);
		}
		description = peopleData[4];
		assumedSex = peopleData[5];
		relationships = new ArrayList<Relation>();
	}
	public Person (String name, String ID, ArrayList<String> textsIncluding, String description, String assumedSex) {//constructor taking in data for a specific individual
		this.name = name;
		this.ID = ID;
		this.textsIncluding = textsIncluding;
		this.description = description;
		this.assumedSex = assumedSex;
		relationships = new ArrayList<Relation>();
	}
	//METHODS
	/**
	 * This method takes in a Person object and a description of a relationship, and uses the 
	 * add() function as defined in the Relation class to store a relationship between this Person
	 * object and the other Person object in the format thisPerson is the [relation] of otherPerson.
	 * @param person2
	 * @param relation
	 */
	public void addRelation(Person person2, String relation) {//adds the person who the current person is the [relationship] of
		Relation newRelation = new Relation (this, relation, person2);
		relationships.add(newRelation);
	}
	/**
	 * This method takes in the ID of a text the Person appears in and adds it 
	 * to the textsIncluding ArrayList of this Person.
	 * @param textName
	 */
	public void addTextAppearence(String textName) {//adds the name of a text to the arrayList of texts where this person is mentioned
		textsIncluding.add(textName);
	}
	
	/**
	 * This method takes another Person object as an argument and returns any relationship between the
	 * two people that has been logged. If no relationship is found, the method returns the String
	 * "No Relationship Found".
	 * @param person2
	 * @return relationship between the two people, else return "No Relationship Found"
	 */
	public String getRelation(Person person2) {
		for (int i = 0; i < relationships.size(); i++) {//search for two instances: one in which person1 is the [relation] of person2,
			//and one in which person2 is the [relation] of person1
			if (relationships.get(i).getPerson1().getID().equals(this.ID) && relationships.get(i).getPerson2().getID().equals(person2.getID())){
				return relationships.get(i).toString();
			}
			if(relationships.get(i).getPerson2().getID().equals(this.ID) && relationships.get(i).getPerson1().getID().equals(person2.getID())) {
				return relationships.get(i).toString();
		    }
		}
		return "No Relationship Found";
	}
	
	/**
	 * Returns a String listing all instance variable values stored in this object.
	 */
	public String toString() {
		return "Name: " + name + '\n' + "ID: " + ID + '\n' + "Texts where the person is mentioned: " + textsIncluding + '\n' 
	    + "Description of individual: " + description + '\n'+ "Assumed sex: " + assumedSex + '\n' + "Relationships: " + relationships + '\n';
	}
	//getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getID() {
		return ID;
	}
	public void setID(String ID) {
		this.ID = ID;
	}
	public ArrayList<String> getTextsIncluding() {
		return textsIncluding;
	}
	public ArrayList<Relation> getRelations() {
		return relationships;
	}
	//add setters
}
