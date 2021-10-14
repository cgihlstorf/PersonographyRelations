package relations;

/**
 * This class takes in two Person objects and a String defining their relationship to one another.
 * Creates a Relation object to store the relationship between the two people using the format
 * person1 is the [relation] of person2.
 * 
 * @author Caroline Gihlstorf
 *
 */
public class Relation {
	//fields
	private Person person1;
	private Person person2;
	private String relation;
	
	//constructor
	public Relation(Person person1, String relation, Person person2) {//format: person1 is the [relation] of person2
		this.person1 = person1;
		this.relation = relation;
		this.person2 = person2;
	}
	//methods
	public String toString() {
		String output = person1.getName() + " is the " + relation + " of " + person2.getName() + "(ID: " + person2.getID() + ")";
		return output;
	}
	
	public Person getPerson1() {
		return person1;
	}
	public Person getPerson2() {
		return person2;
	}
	//getters/setters?
}
