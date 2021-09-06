package graphalgos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm.SingleSourcePaths;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class Preprocess {
	
	public static <V,E> Graph<V,DefaultWeightedEdge> clean(Graph<V, E> g, V s, V t){
		
		//System.out.println(String.format("(Before) vertices: %d edges: %d", g.vertexSet().size(), g.edgeSet().size()));
		
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

        Graph<V, DefaultWeightedEdge> firstPass = vertexClean(graph, s, t);
        //System.out.println(String.format("(firstPass) verticces: %d edges: %d", firstPass.vertexSet().size(), firstPass.edgeSet().size()));
        
        
		/*
		 * //SecondPass
		 * 
		 * Graph<V, DefaultWeightedEdge> flipped = flipEdges(firstPass);
		 * SingleSourcePaths<V, DefaultWeightedEdge> shortestRev = new
		 * DijkstraShortestPath<>(flipped).getPaths(t);
		 * 
		 * Graph<V, DefaultWeightedEdge> graph2 = new
		 * SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		 * 
		 * flipped.edgeSet().forEach(e -> {
		 * 
		 * V u = flipped.getEdgeSource(e); V v = flipped.getEdgeTarget(e); double weight
		 * = flipped.getEdgeWeight(e);
		 * 
		 * double uDist = shortestRev.getWeight(u); double vDist =
		 * shortestRev.getWeight(v);
		 * 
		 * if (uDist + weight <= vDist) { graph2.addVertex(u); graph2.addVertex(v);
		 * DefaultWeightedEdge edge = graph2.addEdge(u, v); graph2.setEdgeWeight(edge,
		 * weight);
		 * 
		 * }
		 * 
		 * });
		 * 
		 * Graph<V, DefaultWeightedEdge> gPrime = vertexClean(graph2, s); gPrime =
		 * flipEdges(gPrime);
		 * 
		 * System.out.println(String.format("(After) verticces: %d edges: %d",
		 * gPrime.vertexSet().size(), gPrime.edgeSet().size()));
		 */
		
		return firstPass; 
		
	}
	
	private static <V> Graph<V, DefaultWeightedEdge> vertexClean(Graph<V,DefaultWeightedEdge> g, V s, V t) {
		
		boolean breakLoop = false; 
		
		while(!breakLoop) {
			
			List<V> vList = new ArrayList<>(g.vertexSet());
			breakLoop = true;
			
			for (V v: vList) {
				
				if(g.outDegreeOf(v) < 1 && !v.equals(t) && !v.equals(s)) { 
					g.removeVertex(v);
					breakLoop = false;
				}
				
			}

		}
		
		return g; 
		
		
	}
	
	private static <V> Graph<V, DefaultWeightedEdge> flipEdges(Graph<V,DefaultWeightedEdge> g) {
		
		List<DefaultWeightedEdge> original = new LinkedList<>(g.edgeSet());
		Graph<V, DefaultWeightedEdge> backup = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		original.forEach(e ->{
			
			V u = g.getEdgeSource(e);
			V v = g.getEdgeTarget(e);
			double weight = g.getEdgeWeight(e);
			
			DefaultWeightedEdge edge = g.addEdge(v, u);
			
			if(edge == null) {
				
				backup.addVertex(v);
				backup.addVertex(u);
				DefaultWeightedEdge edge2 = backup.addEdge(v, u);
				backup.setEdgeWeight(edge2, weight);
				
			} else {
				
				g.setEdgeWeight(edge, weight);
				
			}


		});
		
		g.removeAllEdges(original);
		
		backup.edgeSet().forEach(e ->{
			
			V u = backup.getEdgeSource(e);
			V v = backup.getEdgeTarget(e);
			double weight = backup.getEdgeWeight(e);
			
			DefaultWeightedEdge edge = g.addEdge(u, v);
			g.setEdgeWeight(edge, weight);
			
			
		});
		
		return g; 
		
		
	}

}
