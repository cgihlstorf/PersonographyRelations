package deduplication.sqf;

/**
 * TODO
 */
public class Main {

    public static void main(String[] args) {
    	DataReader test = new DataReader("/Users/caroline/git/cs106-lab7-krussman-gihlstorf/vote_files/SWVF_1_22_short.txt");//ask about this in office hours.
    	//System.out.println(test.allPairsDeduplication());
    	//System.out.println(test.hashLinearDeduplication());
    	System.out.println(test.deduplicateSorted(test.quicksortDeduplication()));

        // TODO

    }
}
