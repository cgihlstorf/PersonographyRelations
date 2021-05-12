package deduplication.sqf;

import java.util.ArrayList;

import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.LinePlot;

/**
 * This class serves as a space to test the correctness of the methods in our DataReader class. It also serves
 * to graph the runtime of the different deduplication functions in DataReader. 
 * 
 * ******should we add to this description?**************
 * 
 *  @authors Caroline Gihlstorf and Jenna Krussman
 *  @version May 12, 2021
 */
public class Main {

    public static void main(String[] args) {
    	//DataReader test = new DataReader(args[0]);
    	//System.out.println(test.allPairsDeduplication());
    	//System.out.println(test.hashLinearDeduplication());
    	//System.out.println(test.quicksortDeduplication());
    	
//    	System.out.println("Records given:" + test.getVoterEntries().size());
//    	System.out.println("Attributes checked:FIRST_NAME,LAST_NAME");
//    	System.out.println("Duplicates found:" + (test.getVoterEntries().size() - test.quicksortDeduplication().size()));
//    }
    	
    	double[] xValues = new double[101];
		double[] yValues = new double[101];
		int index = 1;
		for(int n = 1; n <= 10000; n=n+100) {
			DataReader test = new DataReader(args[0], n);
			double start = startTimer();
			test.quicksortDeduplication();
			double end = endTimer();
			double timeSec = secondsElapsed(start, end);
			//System.out.println(n + ":" + timeSec);
			xValues [index] = n;
			yValues [index] = timeSec;
			index ++;
		}
		plotData(xValues, yValues);
    }
    
    /**
     * This method plots the data of the inputed arrayLists
     * @param xVals the x values of the plot
     * @param yVals the y values of the plot
     */
	public static void plotData(double[] xVals, double[] yVals) {
		DoubleColumn column1 = DoubleColumn.create("n-value", xVals);//come back to this
		DoubleColumn column2 = DoubleColumn.create("Time in Seconds", yVals);

		Table table = Table.create("Data Table");
		table.addColumns(column1, column2);
		Plot.show(LinePlot.create("n-value versus Runtime", table, "n-value", "Time in Seconds"));
	}
	
	/**
	 * This method records the time at the start of the timer
	 * @return The start time
	 */
	public static double startTimer() {
		return System.currentTimeMillis();
	}
	
	/**
	 * This method records the time at the end of the timer
	 * @return The end time
	 */
	public static double endTimer() {
		return System.currentTimeMillis();
	}
	
	/**
	 * This method calculates the milliseconds elapsed by subtracting the start time from the end time
	 * @param start Start time
	 * @param end End time
	 * @return the different between the start and end time
	 */
	public static double secondsElapsed(double start, double end) {
		return end - start;
	}
}
