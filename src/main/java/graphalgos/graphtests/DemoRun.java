package graphalgos.graphtests;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

public abstract class DemoRun {

	private Instant start;
	private Instant finish;
	
	public long preprocessElapsed;
	public long elapsed;
	
	public double diversityMeasure;
	public double unweightedDiversity;
	
	public double minDiversity;
	public double maxDiversity;
	
	public double minUnweighted;
	public double maxUnweighted;
	
	
	protected void startWatch() {
		
		start = Instant.now();
		
	}
	
	protected void measurePreprocessingTime() {
		
		finish = Instant.now();
		
		preprocessElapsed = Duration.between(start, finish).toMillis();
		
	}
	
	protected void measureFinalTime() {
		
		finish = Instant.now();
		
		elapsed = Duration.between(start, finish).toMillis();
		
	}
	
	protected <V> void calculateD(List<Set<DefaultWeightedEdge>> edgeSets, Graph<V,DefaultWeightedEdge> g) {
		
		diversityMeasure = DiverseMeasure.compute(edgeSets, g, DiverseMeasure::weightedSum);
		System.out.println("unweighted");
		unweightedDiversity = DiverseMeasure.compute(edgeSets, g, DiverseMeasure::sum);
		
		minDiversity = DiverseMeasure.computeMinOrMax(edgeSets, g, true, DiverseMeasure::weightedSum);
		maxDiversity = DiverseMeasure.computeMinOrMax(edgeSets, g, false, DiverseMeasure::weightedSum);
		
		minUnweighted = DiverseMeasure.computeMinOrMax(edgeSets, g, true, DiverseMeasure::sum);
		maxUnweighted = DiverseMeasure.computeMinOrMax(edgeSets, g, false, DiverseMeasure::sum);
		
		
	}
	
	
	
}
