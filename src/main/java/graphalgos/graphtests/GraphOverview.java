package graphalgos.graphtests;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm.SingleSourcePaths;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.shortestpath.EppsteinShortestPathIterator;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import graphalgos.Preprocess;

public class GraphOverview {
	
	public int stPathCount;
	public double avgPathLength;
	
	public int vCount;
	public int eCount;
	
	public int vCountPaths;
	public int eCountPaths; 
	
	public <V,E> GraphOverview(Graph<V, E> g, V s, V t) {
		
		vCount = g.vertexSet().size();
		eCount = g.edgeSet().size();
		overview(Preprocess.clean(g,s,t), s, t);
		
	}
	
	
	

	private <V,E> void overview(Graph<V, E> g, V source, V target) {

		Set<E> edgeSet = new HashSet<>();
		Set<V> vertexSet = new HashSet<>();
		
		Iterator<GraphPath<V, E>> epp = new EppsteinShortestPathIterator<>(g, source, target);
		if(!epp.hasNext()) return ;

		double w = epp.next().getWeight(); 
		
		int count = 1;
		int pathLengthSum = 0;
		
		while(epp.hasNext()) {

			GraphPath<V, E> next = epp.next();
			if(next.getWeight() > w || count > 10000 ) break;
			
			count++;
			pathLengthSum += next.getLength();
			edgeSet.addAll(next.getEdgeList());
			
			
		}
		stPathCount = count; 
		avgPathLength = (double)pathLengthSum / count;
		//System.out.println(String.format("count: %d, avg.length: %f", stPathCount, avgPathLength));
		
		eCountPaths = edgeSet.size();
		
		edgeSet.forEach(e -> {
			
			vertexSet.add(g.getEdgeSource(e));
			vertexSet.add(g.getEdgeTarget(e));
		});

		vCountPaths = vertexSet.size();
		
		//System.out.println(String.format("(Eppstein) vertices: %d, edges: %d", vCountPaths, eCountPaths));
	}

}
