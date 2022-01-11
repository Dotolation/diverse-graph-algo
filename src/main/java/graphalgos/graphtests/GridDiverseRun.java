package graphalgos.graphtests;

import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import graphalgos.DiverseShortestPaths;
import graphalgos.Preprocess;

public class GridDiverseRun extends DemoRun {
	
	//No Preprocessing.
	//This class is just for testing the grid graph. 
	
	public <V, E> GridDiverseRun(Graph<V, E> g, V source, V target, int k) {

		//System.out.println(String.format("---Diverse Algo: %s---", message));
		DiverseShortestPaths<V, E> divAlgo = new DiverseShortestPaths<>(g, source, target, k);
		
		divAlgo.gPrime = (Graph<V, DefaultWeightedEdge>) g;
		startWatch();
		
		divAlgo.kDuplication(divAlgo.gPrime);
		divAlgo.minCostFlow();
		
		List<Set<DefaultWeightedEdge>> edgeSetLists = divAlgo.paths();
		measureFinalTime();
		
		calculateD(edgeSetLists, divAlgo.gPrime);
		
	}
	
	

}
