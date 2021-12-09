package graphalgos.graphtests;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.EppsteinShortestPathIterator;
import org.jgrapht.graph.DefaultWeightedEdge;

public class CleanKBestRun extends DemoRun {
	
	public <V,E> CleanKBestRun(Graph<V, E> cleanG, V source, V target, int k, long preprocessingTime) {
	
		List<GraphPath<V,DefaultWeightedEdge>> kPaths = new ArrayList<>();
		
		startWatch();
		
		Graph<V, DefaultWeightedEdge> cleaned = (Graph<V, DefaultWeightedEdge>) cleanG;

		Iterator<GraphPath<V, DefaultWeightedEdge>> epp = new EppsteinShortestPathIterator<>(cleaned, source, target);
		
		int counter = 0;
		while(epp.hasNext()) {
			kPaths.add(epp.next());
			counter++;
			if (counter == k) measureFinalTime(preprocessingTime);
		}
		
		Random rnd = new Random(2021);
		
		while (kPaths.size() > k) {
			int idToRemove = rnd.nextInt(kPaths.size());
			kPaths.remove(idToRemove);
		}
		
		
		
		List<Set<DefaultWeightedEdge>> edgeSets = kPaths.stream().map(path -> new HashSet<>(path.getEdgeList())).collect(Collectors.toList());
		//edgeSets.forEach(edgeSet -> System.out.println(edgeSet));

		calculateD(edgeSets, cleaned);

	}

}
