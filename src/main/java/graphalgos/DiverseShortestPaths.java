package graphalgos;

import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.alg.shortestpath.FloydWarshallShortestPaths;
import org.jgrapht.alg.flow.mincost.*;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;


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
	
	private Graph<String, DefaultWeightedEdge> kDuplication(){
		DefaultWeightedEdge hmm = new DefaultWeightedEdge();
		hmm.getSource();
		
		
	}
	
	public GraphPath<String, DefaultWeightedEdge> getkPaths(String source, String target, int k){
		
		
		
		
		
		
	}
	

}
