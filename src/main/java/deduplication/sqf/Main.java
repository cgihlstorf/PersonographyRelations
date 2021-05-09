package deduplication.sqf;

import java.util.ArrayList;

import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.LinePlot;

/**
 * TODO
 */
public class Main {

    public static void main(String[] args) {
    	//DataReader test = new DataReader(args[0]);
    	//System.out.println(test.allPairsDeduplication());
    	//System.out.println(test.hashLinearDeduplication());
    	//System.out.println(test.quicksortDeduplication());
    	
//    	System.out.println("Records given:" + test.getVoterEntries().size());
//    	System.out.println("Attributes checked:FIRST_NAME,LAST_NAME");
//    	System.out.println("Duplicates found:" + (test.getVoterEntries().size() - test.hashLinearDeduplication().size()));
    	
    	double[] xValues = new double[11];
		double[] yValues = new double[11];
		int index = 0;
		for(int n = 0; n <= 10000; n=n+1000) {
			DataReader test = new DataReader(args[0], n);
			double start = startTimer();
			test.hashLinearDeduplication();
			double end = endTimer();
			double timeSec = secondsElapsed(start, end);
			//System.out.println(n + ":" + timeSec);
			xValues [index] = n;
			yValues [index] = timeSec;
			index ++;
		}
		plotData(xValues, yValues);
    }
    
	public static void plotData(double[] xVals, double[] yVals) {
		DoubleColumn column1 = DoubleColumn.create("n-value", xVals);//come back to this
		DoubleColumn column2 = DoubleColumn.create("Time in Seconds", yVals);

		Table table = Table.create("Data Table");
		table.addColumns(column1, column2);
		Plot.show(LinePlot.create("n-value versus Runtime", table, "n-value", "Time in Seconds"));
	}
	
	public static double startTimer() {
		return System.currentTimeMillis();
	}
	
	public static double endTimer() {
		return System.currentTimeMillis();
	}
	
	public static double secondsElapsed(double start, double end) {
		return end - start;
	}
}
