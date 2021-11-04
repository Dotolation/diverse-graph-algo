package graphalgos.graphtests;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.EppsteinShortestPathIterator;
import org.jgrapht.graph.DefaultWeightedEdge;

import graphalgos.Preprocess;

public class KBestAlgoRun extends DemoRun {
	
	public <V,E> KBestAlgoRun(Graph<V, E> g, V source, V target, int k, String message) {
	
		List<GraphPath<V,DefaultWeightedEdge>> kPaths = new ArrayList<>();
		
		startWatch();
		
		Graph<V,DefaultWeightedEdge> cleaned = Preprocess.clean(g, source, target);
		
		measurePreprocessingTime(); //Time taken to remove vertices/

		Iterator<GraphPath<V, DefaultWeightedEdge>> epp = new EppsteinShortestPathIterator<>(cleaned, source, target);
		
		int counter = 0;
		while(epp.hasNext() && counter < k) {
			kPaths.add(epp.next());
			counter++;
		}
		
		measureFinalTime();
		
		List<Set<DefaultWeightedEdge>> edgeSets = kPaths.stream().map(path -> new HashSet<>(path.getEdgeList())).collect(Collectors.toList());
		//edgeSets.forEach(edgeSet -> System.out.println(edgeSet));

		calculateD(edgeSets, cleaned);

	}

}
