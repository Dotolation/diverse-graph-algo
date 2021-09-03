package graphalgos;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm.SingleSourcePaths;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class Preprocess {
	
	public static <V,E> Graph<V,DefaultWeightedEdge> clean(Graph<V, E> g, V s, V t){
		
		System.out.println(String.format("(Before) vertices: %d edges: %d", g.vertexSet().size(), g.edgeSet().size()));
		
		Graph<V, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
        SingleSourcePaths<V, E> shortest = new DijkstraShortestPath<>(g).getPaths(s);
		
        g.edgeSet().forEach(e -> {
        	
			V u = g.getEdgeSource(e);
			V v = g.getEdgeTarget(e);
			double weight = g.getEdgeWeight(e);
			
			double uDist = shortest.getWeight(u);
			double vDist = shortest.getWeight(v);

			if (uDist + weight <= vDist) {
				graph.addVertex(u);
			    graph.addVertex(v);
			    DefaultWeightedEdge edge = graph.addEdge(u, v);
			    graph.setEdgeWeight(edge, weight);
				
			}	
		    

			
		});
		
        
        Graph<V, DefaultWeightedEdge> gPrime = vertexClean(graph, t);
		
		System.out.println(String.format("(After) verticces: %d edges: %d", gPrime.vertexSet().size(), gPrime.edgeSet().size()));
		
		return gPrime; 
		
	}
	
	private static <V> Graph<V, DefaultWeightedEdge> vertexClean(Graph<V,DefaultWeightedEdge> g, V t) {
		
		boolean breakLoop = false; 
		
		while(!breakLoop) {
			
			List<V> vList = new ArrayList<>(g.vertexSet());
			breakLoop = true;
			
			for (V v: vList) {
				
				if(g.outDegreeOf(v) < 1 && !v.equals(t)) { 
					g.removeVertex(v);
					breakLoop = false;
				}
				
			}

		}
		
		return g; 
		
		
	}

}
