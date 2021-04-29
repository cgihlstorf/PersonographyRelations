package deduplication.sqf;

public class Plotting {
	
	public static void main(String args[]) {
		for(int n = 0; n <= 100; n = n+40) {
			double start = startTimer();
			int result = fib(n);
			double end = endTimer();
			double timeSec = secondsElapsed(start, end);
			System.out.println(n + ": " + timeSec);
		}
	}
	
	public static double startTimer() {
		return System.currentTimeMillis()/1000;
	}
	
	public static int fib(int n) {
		if(n == 0 || n ==1)
			return 1;
		else {
			System.out.println(n);
			int num1 = fib(n-1);
			int num2 = fib(n-2);
			return num1 + num2;
		}
	}
	
	public static double endTimer() {
		return System.currentTimeMillis()/1000;
	}
	
	public static double secondsElapsed(double start, double end) {
		return end - start;
	}

}
