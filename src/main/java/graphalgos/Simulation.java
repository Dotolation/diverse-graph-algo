package graphalgos;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;

public class Simulation {

	public static void main(String[] args) {
		
		SimpleGrid grid = new SimpleGrid(3,2);
		Graph<String, DefaultWeightedEdge> g = grid.graph;
		
		System.out.println(g.toString());
		
		DiverseShortestPaths divAlgo = new DiverseShortestPaths(g);
		
		
		divAlgo.edgeRemoval(grid.source, grid.target);
		System.out.println(g.toString());
		
		Graph<String, DefaultWeightedEdge> gMulti = divAlgo.kDuplication(2);
		System.out.println(gMulti.toString());
		
		Graph<String, DefaultWeightedEdge> flowNet = divAlgo.minCostFlow(gMulti, grid.source, grid.target, 2);
		System.out.println(flowNet);

	}

}
