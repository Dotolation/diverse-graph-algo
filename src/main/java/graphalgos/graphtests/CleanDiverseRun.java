package graphalgos.graphtests;

import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import graphalgos.DiverseShortestPaths;
import graphalgos.Preprocess;

public class CleanDiverseRun extends DemoRun {
	
	
	public <V, E> CleanDiverseRun(Graph<V, E> cleanG, V source, V target, int k, long preprocessingTime) {
		
		/*
		 * Diverse Algorithm. 
		 * 		
		 */
		
		//System.out.println(String.format("---Diverse Algo: %s---", message));
		
		
		startWatch();
		
		DiverseShortestPaths<V, E> divAlgo = new DiverseShortestPaths<>(cleanG, source, target, k);
		divAlgo.gPrime = (Graph<V, DefaultWeightedEdge>) cleanG;		
		divAlgo.kDuplication(divAlgo.gPrime);
		divAlgo.minCostFlow();
		
		List<Set<DefaultWeightedEdge>> edgeSetLists = divAlgo.paths();
		
		measureFinalTime(preprocessingTime);
		
		calculateD(edgeSetLists, divAlgo.gPrime);
		
	}
	
	

}
