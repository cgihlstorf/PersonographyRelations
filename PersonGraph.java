package relations;

import java.util.ArrayList;

public class PersonGraph {
	//FIELDS
	private String[][] relationsArray;
	private ArrayList<Person> personArray;
	
	//CONSTRUCTOR
	public PersonGraph(ArrayList<Person> personArray) {//each person should already have an array of relations for this to work
		 this.personArray = personArray;
		 relationsArray = new String[personArray.size()][personArray.size()];
	}
	//add a constructor for an array of people?
	
	//METHODS
	public void createGraph() {
		for (int i = 0; i < personArray.size(); i++) {
			for (int j = 0; j < personArray.size(); i++) {
				relationsArray[i][j] = personArray.get(i).getRelation(personArray.get(j));
			}
		}
	}
}
	//create a method to get the relationship between two people
	
	
//	//code from Main: ArrayList<Person> personArrayList = new ArrayList<Person>();
//	for (int i = 0; i < personArrayList.size(); i++) {
//		personArrayList.add(personArray[i]);
//	}
//	PersonGraph graph = new PersonGraph(personArrayList);
//	
//	
//}
