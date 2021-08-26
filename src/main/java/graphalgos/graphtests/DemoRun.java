package graphalgos.graphtests;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;

public abstract class DemoRun {

	private Instant start;
	private Instant finish;
	
	public long elapsed = 0;
	public double diversityMeasure = 0d; 
	
	
	protected void startWatch() {
		
		start = Instant.now();
		//startTime = System.currentTimeMillis();
		
	}
	
	protected void stopWatch() {
		
		finish = Instant.now();
		//endTime = System.currentTimeMillis();
		
		//long duration = endTime - startTime;
		elapsed = Duration.between(start, finish).toMillis();
		//System.out.println(String.format("Execution Time: %d miliseconds", elapsed));
		
	}
	
	protected <V, E> void calculateD(List<Set<E>> edgeSets, Graph<V,E> g) {
		
		diversityMeasure = DiverseMeasure.compute(edgeSets, g);
		
	}
	
}
