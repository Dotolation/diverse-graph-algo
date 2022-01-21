package graphalgos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.concurrent.ThreadPoolExecutor;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm.SingleSourcePaths;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.shortestpath.BellmanFordShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.util.ConcurrencyUtil;
import org.jgrapht.alg.shortestpath.DeltaSteppingShortestPath;

public class Preprocess {
	
	public static <V,E> Graph<V,DefaultWeightedEdge> clean(Graph<V, E> g, V s, V t){
		
		//System.out.println(String.format("(Before) vertices: %d edges: %d", g.vertexSet().size(), g.edgeSet().size()));        
		
		ThreadPoolExecutor th = ConcurrencyUtil.createThreadPoolExecutor(8);
		
		Graph<V, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		
		SingleSourcePaths<V, E> shortest = new DeltaSteppingShortestPath<>(g,th).getPaths(s);
		th.shutdown();
		

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
        
        //Island Removal
        ConnectivityInspector<V, DefaultWeightedEdge> cinspct = new ConnectivityInspector<>(firstPass);
        List<Set<V>> comps = cinspct.connectedSets();
        int islandCountPre = comps.size();

        
        if(islandCountPre > 1) {
        	
        	for (Set<V> component : comps) {
        		
        		if(!component.contains(s)) {
        			
        			firstPass.removeAllVertices(component);
        		}
        		
        	}
        	
        }
//        
//        
//		
//		  //SecondPass
//		  
//		  Graph<V, DefaultWeightedEdge> flipped = flipEdges(firstPass);
//		  SingleSourcePaths<V, DefaultWeightedEdge> shortestRev = new
//		  DijkstraShortestPath<>(flipped).getPaths(t);
//		  
//		  Graph<V, DefaultWeightedEdge> graph2 = new
//		  SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
//		  
//		  flipped.edgeSet().forEach(e -> {
//		  
//		  V u = flipped.getEdgeSource(e); V v = flipped.getEdgeTarget(e); double weight
//		  = flipped.getEdgeWeight(e);
//		  
//		  double uDist = shortestRev.getWeight(u); double vDist =
//		  shortestRev.getWeight(v);
//		  
//		  if (uDist + weight <= vDist) { graph2.addVertex(u); graph2.addVertex(v);
//		  DefaultWeightedEdge edge = graph2.addEdge(u, v); graph2.setEdgeWeight(edge,
//		  weight);
//		  
//		  }
//		  
//		  });
//		  
//		  Graph<V, DefaultWeightedEdge> gPrime = vertexClean(graph2, t, s); 
//		  gPrime = flipEdges(gPrime);
//		  
//		  System.out.println(String.format("(After) verticces: %d edges: %d",
//		  gPrime.vertexSet().size(), gPrime.edgeSet().size()));
		 
		
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
