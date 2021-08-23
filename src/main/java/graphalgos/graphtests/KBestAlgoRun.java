package graphalgos.graphtests;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.KShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.EppsteinKShortestPath;

import graphalgos.DiverseMeasure;

public class KBestAlgoRun extends DemoRun {
	
	public <V,E> KBestAlgoRun(Graph<V, E> g, V source, V target, int k, String message) {
	
		System.out.println(String.format("---k-best enumeration: %s ---", message));
		
		startWatch();
		
		KShortestPathAlgorithm<V, E> eppstein = new EppsteinKShortestPath<>(g);
		List<GraphPath<V, E>> kPaths = eppstein.getPaths(source, target, k);
		
		List<Set<E>> edgeSets = kPaths.stream().map(path -> new HashSet<>(path.getEdgeList())).collect(Collectors.toList());
		//edgeSets.forEach(edgeSet -> System.out.println(edgeSet));

		
		stopWatch();
		DiverseMeasure.compute(edgeSets, g);

	}

}
