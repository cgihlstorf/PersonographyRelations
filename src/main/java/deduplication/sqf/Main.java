package deduplication.sqf;

/**
 * TODO
 */
public class Main {

    public static void main(String[] args) {
    	DataReader test = new DataReader("/Users/jennakrussman/git/cs106-lab7-krussman-gihlstorf/vote_files/SWVF_1_22_short.txt");//ask about this in office hours.
    	//System.out.println(test.allPairsDeduplication());
    	//System.out.println(test.hashLinearDeduplication());

    	System.out.println(test.quicksortDeduplication());
    	
    	Plotting newPlot = new Plotting();
    	
    	double[] xValues = new double[51];
		double[] yValues = new double[51];
		for(int n = 0; n <= 50; n++) {
			double start = newPlot.startTimer();
			test.allPairsDeduplication();
			double end = newPlot.endTimer();
			double timeSec = newPlot.secondsElapsed(start, end);
			xValues [n] = n;
			yValues [n] = timeSec;
		}
		newPlot.plotData(xValues, yValues);



    }
}
