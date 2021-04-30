package deduplication.sqf;

public class Voter implements Comparable<Voter> {
	String[] voterInfo;
	String lastName;
	String firstName;

	public Voter(String[] voterInfo) {
		this.voterInfo = voterInfo;
		lastName =voterInfo[3];
		firstName = voterInfo[4];
	}

	@Override
	public int compareTo(Voter o) {
		if(this.lastName.compareTo(o.lastName) > 0 || this.lastName.compareTo(o.lastName) < 0)
			return this.lastName.compareTo(o.lastName);
		else
			if(this.firstName.compareTo(o.firstName) > 0 || this.firstName.compareTo(o.firstName) < 0)
				return this.firstName.compareTo(o.firstName);
			else
				return 0;
	}

}
