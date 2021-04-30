package deduplication.sqf;

import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.LinePlot;
import tech.tablesaw.*;

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
			//System.out.println(n + ": " + timeSec);
		}
		plotData(xValues, yValues);
		
	}
	public static void plotData(double[] xVals, double[] yVals) {
		DoubleColumn column1 = DoubleColumn.create("n-value", xVals);//come back to this
		DoubleColumn column2 = DoubleColumn.create("Runtime", yVals);

		Table table = Table.create("Data Table");
		table.addColumns(column1, column2);
		Plot.show(LinePlot.create("n-value versus Runtime", table, "n-value", "Runtime"));
	}
	
	public static double startTimer() {
		return System.currentTimeMillis()/1000;
	}
	
	public static int fib(int n) {
		if(n == 0 || n ==1)
			return 1;
		else {
			//System.out.println(n);
			return fib(n - 2) + fib(n - 1);
		}
	}
	
	public static double endTimer() {
		return System.currentTimeMillis()/1000;
	}
	
	public static double secondsElapsed(double start, double end) {
		return end - start;
	}

}
