package deduplication.sqf;

/**
 * TODO
 */
public class Main {

    public static void main(String[] args) {
    	DataReader test = new DataReader(args[0]);
    	//System.out.println(test.allPairsDeduplication());
    	//System.out.println(test.hashLinearDeduplication());
    	//System.out.println(test.quicksortDeduplication());
    	
    	System.out.println("Records given:" + test.getVoterEntries().size());
    	System.out.println("Attributes checked:FIRST_NAME,LAST_NAME");
    	System.out.println("Duplicates found:" + (test.getVoterEntries().size() - test.quicksortDeduplication().size()));
    	
//    	Plotting newPlot = new Plotting();
//    	
//    	double[] xValues = new double[51];
//		double[] yValues = new double[51];
//		for(int n = 0; n <= 50; n++) {
//			double start = newPlot.startTimer();
//			test.quicksortDeduplication();
//			double end = newPlot.endTimer();
//			double timeSec = newPlot.secondsElapsed(start, end);
//			xValues [n] = n;
//			yValues [n] = timeSec;
//		}
//		newPlot.plotData(xValues, yValues);
    }
}
