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
	
		startWatch();
		
		List<GraphPath<V,DefaultWeightedEdge>> kPaths = new ArrayList<>();
		Graph<V, DefaultWeightedEdge> newG = (Graph<V,DefaultWeightedEdge>) g;
		
		Iterator<GraphPath<V, DefaultWeightedEdge>> epp = 
				new EppsteinShortestPathIterator<>(newG, source, target);
		
		int counter = 0;
		while(epp.hasNext() && counter < 8000000) {
			kPaths.add(epp.next());
			counter++;
			if (counter == k) measureFinalTime();

		}
		
		List<GraphPath<V,DefaultWeightedEdge>> Answers = new ArrayList<>();
		
		Random rnd = new Random(2021);
		
		while (Answers.size() < k) {
			int idToRemove = rnd.nextInt(kPaths.size());
			Answers.add(kPaths.get(idToRemove));
			kPaths.remove(idToRemove);
		}
		
		
		List<Set<DefaultWeightedEdge>> edgeSets = Answers.stream().map(path -> new HashSet<>(path.getEdgeList())).collect(Collectors.toList());
		//edgeSets.forEach(edgeSet -> System.out.println(edgeSet));

		calculateD(edgeSets, newG);

	}

}
