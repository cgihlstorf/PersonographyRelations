package deduplication.sqf;

import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.LinePlot;
import tech.tablesaw.*;

/**
 * This class includes methods to time the execution of different methods and graph their runtimes. 
 * 
 * @author Caroline Gihlstorf and Jenna Krussman
 * @version May 12, 2021
 *
 */

public class Plotting {
	
	public static void main(String args[]) {
		double[] xValues = new double[51];
		double[] yValues = new double[51];
		for(int n = 0; n <= 50; n++) {
			double start = startTimer();
			int result = fib(n);
			double end = endTimer();
			double timeSec = secondsElapsed(start, end);
			xValues [n] = n;
			yValues [n] = timeSec;
		}
		plotData(xValues, yValues);
	}
	
	/**
	 * This method takes in two double[] arrays (one of x-values, which represent the different n-values a program runs for,
	 * and one of y-values, which represent the time in second it takes for the program to run to completion), creates a graph, 
	 * and plots the data at the corresponding x and y values.
	 * 
	 * @param xVals
	 * @param yVals
	 */
	public static void plotData(double[] xVals, double[] yVals) {
		DoubleColumn column1 = DoubleColumn.create("n-value", xVals);
		DoubleColumn column2 = DoubleColumn.create("Time in Seconds", yVals);

		Table table = Table.create("Data Table");
		table.addColumns(column1, column2);
		Plot.show(LinePlot.create("n-value versus Runtime", table, "n-value", "Time in Seconds"));
	}
	
	/**
	 * This method returns the current time in milliseconds.
	 */
	public static double startTimer() {
		return System.currentTimeMillis();
	}
	
	/**
	 * This method takes in an integer n as a parameter and computes the first n numbers of the 
	 * Fibonacci sequence.
	 * 
	 * @param n
	 * @return fib(n - 2) + fib (n - 1)
	 */
	public static int fib(int n) {
		if(n == 0 || n ==1)
			return 1;
		else {
			return fib(n - 2) + fib(n - 1);
		}
	}
	/**
	 * This method returns the current time in milliseconds.
	 */
	public static double endTimer() {
		return System.currentTimeMillis();
	}
	
	/**
	 * This method takes in two double values and returns their difference. This method is used to compute
	 * the difference in the double values returned by endTimer() and startTimer().
	 * 
	 * @param start
	 * @param end
	 * @return end - start
	 */
	public static double secondsElapsed(double start, double end) {
		return (end - start);
	}
}