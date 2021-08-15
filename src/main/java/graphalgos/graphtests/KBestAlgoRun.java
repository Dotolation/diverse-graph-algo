package graphalgos.graphtests;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.KShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.EppsteinKShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;

import graphalgos.DiverseMeasure;

public class KBestAlgoRun extends DemoRun {
	
public KBestAlgoRun(Graph<String, DefaultWeightedEdge> g, String source, String target, int k) {

		
		System.out.println("---k-best enumeration---");
		
		startWatch();
		
		KShortestPathAlgorithm<String, DefaultWeightedEdge> eppstein = new EppsteinKShortestPath<>(g);
		List<GraphPath<String, DefaultWeightedEdge>> kPaths = eppstein.getPaths(source, target, k);
		
		List<Set<DefaultWeightedEdge>> edgeSets = new ArrayList<>();
		
		for(GraphPath<String, DefaultWeightedEdge> path : kPaths) {
			
			Set<DefaultWeightedEdge> edgeSet = new HashSet<>(path.getEdgeList());
			
			edgeSets.add(edgeSet);
			
		}
		
		stopWatch();
		DiverseMeasure.compute(edgeSets, g);

	}

}
