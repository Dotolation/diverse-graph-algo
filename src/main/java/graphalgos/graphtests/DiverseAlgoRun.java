package graphalgos.graphtests;

import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import graphalgos.DiverseShortestPaths;
import graphalgos.Preprocess;

public class DiverseAlgoRun extends DemoRun {
	
	
	public <V, E> DiverseAlgoRun(Graph<V, E> g, V source, V target, int k, String message) {
		
		/*
		 * Diverse Algorithm. 
		 * 		
		 */
		
		//System.out.println(String.format("---Diverse Algo: %s---", message));
		
		startWatch();
		
		DiverseShortestPaths<V, E> divAlgo = new DiverseShortestPaths<>(g, source, target, k);
		
		divAlgo.gPrime = Preprocess.clean(divAlgo.g, source, target);
		measurePreprocessingTime();
		
		divAlgo.kDuplication(divAlgo.gPrime);
		divAlgo.minCostFlow();
		
		List<Set<DefaultWeightedEdge>> edgeSetLists = divAlgo.paths();
		measureFinalTime();
		
		calculateD(edgeSetLists, divAlgo.gPrime);
		
	}
	
	

}
