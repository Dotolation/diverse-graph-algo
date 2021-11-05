package graphalgos.graphtests;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.EppsteinShortestPathIterator;
import org.jgrapht.graph.DefaultWeightedEdge;

import graphalgos.Preprocess;

public class GraphOverview extends DemoRun {
	
	public int stPathCount;
	public double avgPathLength;
	
	public int vCount;
	public int eCount;
	
	public int vCountPaths;
	public int eCountPaths; 
	
	public <V,E> GraphOverview() {

		
	}
	
	public <V,E> Graph<V,E> getGraph(Graph<V, E> g, V s, V t){
		
		vCount = g.vertexSet().size();
		eCount = g.edgeSet().size();
		
		startWatch();
		Graph<V,E> cleanGraph = (Graph<V,E>) Preprocess.clean(g,s,t); 
		measurePreprocessingTime();
		
		System.out.println("Preprocessing Time (ms): " + Long.toString(preprocessElapsed));
		//System.out.println(String.format("Cleaned vcount: %d, eCount: %d", cleanGraph.vertexSet().size(), cleanGraph.edgeSet().size()));
		
		overview(cleanGraph, s, t);
		
		return cleanGraph;

	} 

	private <V,E> void overview(Graph<V, E> g, V source, V target) {

		Set<E> edgeSet = new HashSet<>();
		Set<V> vertexSet = new HashSet<>();
		
		Iterator<GraphPath<V, E>> epp = new EppsteinShortestPathIterator<>(g, source, target);
		
        GraphPath<V,E> path = epp.next();
        edgeSet.addAll(path.getEdgeList());
        
		double w = path.getWeight(); 
		int count = 1;
		int pathLengthSum = path.getLength();
		
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
