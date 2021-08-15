package graphalgos.graphtests;

import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import graphalgos.DiverseMeasure;
import graphalgos.DiverseShortestPaths;

public class DiverseAlgoRun extends DemoRun {
	
	
	public DiverseAlgoRun(Graph<String, DefaultWeightedEdge> g, String source, String target, int k, String message) {
		
		/*
		 * Diverse Algorithm. 
		 * 		
		 */
		
		System.out.println(String.format("---Diverse Algo: %s---", message));
		
		startWatch();
		
		DiverseShortestPaths divAlgo = new DiverseShortestPaths(g, source, target, k);
		List<Set<DefaultWeightedEdge>> edgeSetLists = divAlgo.paths();
		
		stopWatch();
		DiverseMeasure.compute(edgeSetLists, g);
		
	}
	
	

}
