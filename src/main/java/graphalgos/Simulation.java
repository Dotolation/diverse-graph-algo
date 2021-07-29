package graphalgos;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;

public class Simulation {

	public static void main(String[] args) {
		
		SimpleGrid grid = new SimpleGrid(3,4);
		Graph<String, DefaultWeightedEdge> g = grid.graph;
		
		System.out.println(g.toString());
		
		DiverseShortestPaths divAlgo = new DiverseShortestPaths(g);
		System.out.println(g.toString());
		
		divAlgo.edgeRemoval(grid.source, grid.target);
		Graph<String, DefaultWeightedEdge> gMulti = divAlgo.kDuplication(4);
		System.out.println(gMulti.toString());
		
		divAlgo.minCostFlow(gMulti, grid.source, grid.target, 4);

	}

}
