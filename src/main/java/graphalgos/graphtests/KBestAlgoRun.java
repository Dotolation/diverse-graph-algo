package graphalgos.graphtests;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.KShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.EppsteinKShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class KBestAlgoRun implements DemoRun {
	
public KBestAlgoRun(Graph<String, DefaultWeightedEdge> g, String source, String target, int k) {
		
		/*
		 * k-best enumeration. 
		 * 
		 */
		
		System.out.println("---k-best enumeration---");
		
		KShortestPathAlgorithm<String, DefaultWeightedEdge> eppstein = new EppsteinKShortestPath<>(g);
		List<GraphPath<String, DefaultWeightedEdge>> kPaths = eppstein.getPaths(source, target, k);
		
		Graph<String, DefaultWeightedEdge> kBestGraph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		for(GraphPath<String, DefaultWeightedEdge> path : kPaths) {
			System.out.println(path.getEdgeList());
			
		}

	}

}
