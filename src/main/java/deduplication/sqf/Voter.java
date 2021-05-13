package deduplication.sqf;

/**
 * This class creates a Voter object that stores the first and last name of a voter. The class' constructor takes in a String[] and 
 * sets the first and last name of the voter to the data at certain indices of the String[]
 * 
 * @authors Caroline Gihlstorf and Jenna Krussman
 *
 */

public class Voter implements Comparable<Voter> {
	String[] voterInfo;
	String lastName;
	String firstName;

	public Voter(String[] voterInfo) {
		this.voterInfo = voterInfo;
		lastName =voterInfo[3];
		firstName = voterInfo[4];
	}

	/**
	 * This method compares the first and last name of two Voters. If the last names are different, the last name of the
	 * current Voter is returned. If the last name is the same but the first names are different, the first name of the
	 * current Voter is returned. If both the first and last name are the same, the method returns 0.
	 * 
	 * @return last name if last names are different; first name if first names are different; 
	 * 0 if both first and last names are identical
	 */
	@Override
	public int compareTo(Voter otherVoter) {
		if(this.lastName.compareTo(otherVoter.lastName) > 0 || this.lastName.compareTo(otherVoter.lastName) < 0)
			return this.lastName.compareTo(otherVoter.lastName);
		else
			if(this.firstName.compareTo(otherVoter.firstName) > 0 || this.firstName.compareTo(otherVoter.firstName) < 0)
				return this.firstName.compareTo(otherVoter.firstName);
			else
				return 0;
	}
	
	public String toString() {
		return firstName + " " + lastName;
	}
}