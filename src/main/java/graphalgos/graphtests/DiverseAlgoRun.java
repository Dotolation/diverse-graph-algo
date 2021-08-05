package graphalgos.graphtests;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.KShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.EppsteinKShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import graphalgos.DiverseShortestPaths;

public class DiverseAlgoRun implements DemoRun {
	
	
	public DiverseAlgoRun(Graph<String, DefaultWeightedEdge> g, String source, String target, int k) {
		
		/*
		 * Diverse Algorithm. 
		 * 		
		 */
		
		System.out.println("---Diverse Algo---");	
		
		DiverseShortestPaths divAlgo = new DiverseShortestPaths(g, source, target, k);
		
		
	}
	
	

}
