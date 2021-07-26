package graphalgos;

import java.util.HashSet;
import java.util.function.Function;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;


public class DiverseShortestPaths {
	
	public Graph<String, DefaultWeightedEdge> g;
	
	public DiverseShortestPaths(DefaultDirectedWeightedGraph<String,DefaultWeightedEdge> g){
		this.g = g;
	}
	
	private Graph<String, DefaultWeightedEdge> edgeRemoval(String source, String target){
		
		//find all shortest paths
		AllDirectedPaths<String, DefaultWeightedEdge> pathBuilder = new AllDirectedPaths<>(g);
		pathBuilder.getAllPaths(source, target, true, null);
		
		
		
		
		
		return g;
		
	}
	
	private Graph<String, DefaultWeightedEdge> kDuplication(int k){
		
		
		DirectedWeightedMultigraph<String, DefaultWeightedEdge> kDuplicate = new DirectedWeightedMultigraph<>(DefaultWeightedEdge.class);
		
		g.edgeSet().forEach(edge -> {
			
			String pre = g.getEdgeSource(edge);
			String succ = g.getEdgeTarget(edge);
			
			kDuplicate.addVertex(pre);
			kDuplicate.addVertex(succ);
			
			for (int i = 1; i <= k; i++) {
				
				kDuplicate.addEdge(pre, succ);
				
			    
			}
			
			for(DefaultWeightedEdge ) {
				
				
				
			}
			
			
			
			
			double weight = g.getEdgeWeight(edge) + 33;
			
		});
		
		
	}
	
	public GraphPath<String, DefaultWeightedEdge> getkPaths(String source, String target, int k){
		
		Function<DefaultWeightedEdge, Integer> maxCapacity = edge -> 1;
		Function<DefaultWeightedEdge, Integer> minCapacity = edge -> 0;
		
		
		
	}
	

}
