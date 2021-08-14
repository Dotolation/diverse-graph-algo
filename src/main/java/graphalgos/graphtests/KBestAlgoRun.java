package graphalgos.graphtests;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.KShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.EppsteinKShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class KBestAlgoRun extends DemoRun {
	
public KBestAlgoRun(Graph<String, DefaultWeightedEdge> g, String source, String target, int k) {

		
		System.out.println("---k-best enumeration---");
		
		startWatch();
		
		KShortestPathAlgorithm<String, DefaultWeightedEdge> eppstein = new EppsteinKShortestPath<>(g);
		List<GraphPath<String, DefaultWeightedEdge>> kPaths = eppstein.getPaths(source, target, k);
		
		Graph<String, DefaultWeightedEdge> kBestGraph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		
		for(GraphPath<String, DefaultWeightedEdge> path : kPaths) {
			
			path.getEdgeList().forEach(e -> {
				
				String u = g.getEdgeSource(e);
				String v = g.getEdgeTarget(e);
				
				kBestGraph.addVertex(u);
				kBestGraph.addVertex(v);
				kBestGraph.addEdge(u, v, e);

				
			});
			
		}
		
		System.out.println(kBestGraph.edgeSet().size());
		
		stopWatch();

	}

}
