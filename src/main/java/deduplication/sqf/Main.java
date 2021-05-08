package deduplication.sqf;

import java.util.ArrayList;

/**
 * TODO
 */
public class Main {

    public static void main(String[] args) {
    	DataReader test = new DataReader(args[0]);
    	//System.out.println(test.allPairsDeduplication());
    	//System.out.println(test.hashLinearDeduplication());
    	//System.out.println(test.quicksortDeduplication());
    	
//    	System.out.println("Records given:" + test.getVoterEntries().size());
//    	System.out.println("Attributes checked:FIRST_NAME,LAST_NAME");
//    	System.out.println("Duplicates found:" + (test.getVoterEntries().size() - test.quicksortDeduplication().size()));
    	
    	Plotting newPlot = new Plotting();
    	
    	double[] xValues = new double[501];
		double[] yValues = new double[501];
		for(int n = 0; n <= 500; n++) {
			test = new DataReader(args[0]);
			for(int i = test.getVoterEntries().size()-1; i > n; i--) {
				test.getVoterEntries().remove(i);
			}
			System.out.println(test.getVoterEntries().size());
			double start = newPlot.startTimer();
			test.hashLinearDeduplication();
			double end = newPlot.endTimer();
			double timeSec = newPlot.secondsElapsed(start, end);
			//System.out.println(n + ":" + n + timeSec);
			xValues [n] = n;
			yValues [n] = timeSec;
		}
		newPlot.plotData(xValues, yValues);
    }
}
