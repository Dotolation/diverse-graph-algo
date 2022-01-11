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

import graphalgos.Preprocess;

public class GridKBestRun extends DemoRun {
	
	public <V,E> GridKBestRun(Graph<V, E> g, V source, V target, int k) {
	
		List<GraphPath<V,DefaultWeightedEdge>> kPaths = new ArrayList<>();
		Graph<V, DefaultWeightedEdge> newG = (Graph<V,DefaultWeightedEdge>) g;
		
		startWatch();
		
		Iterator<GraphPath<V, DefaultWeightedEdge>> epp = 
				new EppsteinShortestPathIterator<>(newG, source, target);
		
		int counter = 0;
		while(epp.hasNext() && counter < 5000000) {
			kPaths.add(epp.next());
			counter++;
			if (counter == k) measureFinalTime();

		}
		
		Random rnd = new Random(2021);
		
		while (kPaths.size() > k) {
			int idToRemove = rnd.nextInt(kPaths.size());
			kPaths.remove(idToRemove);
		}
		
		
		List<Set<DefaultWeightedEdge>> edgeSets = kPaths.stream().map(path -> new HashSet<>(path.getEdgeList())).collect(Collectors.toList());
		//edgeSets.forEach(edgeSet -> System.out.println(edgeSet));

		calculateD(edgeSets, newG);

	}

}
