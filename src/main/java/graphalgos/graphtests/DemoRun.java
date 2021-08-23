package graphalgos.graphtests;

public abstract class DemoRun {

	private long startTime;
	private long endTime;
	
	protected void startWatch() {
		
		startTime = System.currentTimeMillis();
		
	}
	
	protected void stopWatch() {
		
		endTime = System.currentTimeMillis();
		
		long duration = endTime - startTime;
		System.out.println(String.format("Execution Time: %d miliseconds", duration));
		
	}
}
