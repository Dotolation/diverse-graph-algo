package graphalgos.graphtests;

import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;

import graphalgos.DiverseShortestPaths;

public class DiverseAlgoRun extends DemoRun {
	
	
	public <V, E> DiverseAlgoRun(Graph<V, E> g, V source, V target, int k, String message) {
		
		/*
		 * Diverse Algorithm. 
		 * 		
		 */
		
		System.out.println(String.format("---Diverse Algo: %s---", message));
		
		startWatch();
		
		DiverseShortestPaths<V, E> divAlgo = new DiverseShortestPaths<>(g, source, target, k);
		List<Set<E>> edgeSetLists = divAlgo.paths();
		
		stopWatch();
		DiverseMeasure.compute(edgeSetLists, g);
		
	}
	
	

}
