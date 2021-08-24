package graphalgos;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import graphalgos.graphtests.DemoRun;
import graphalgos.graphtests.DiverseAlgoRun;
import graphalgos.graphtests.KBestAlgoRun;
import graphgenerators.FileToGraph;
import graphgenerators.SimpleGrid;

public class Simulation {

	
	public static void main(String[] args) {
		int k = 10;
		
		//WikiVote Graph Test
		Graph<Integer, DefaultWeightedEdge> vote = FileToGraph.read("src/wiki-Vote.txt.gz", FileToGraph::readSNAP);
		int source = 614;
		int target = 694; 
		String msg = String.format("Wikivote Graph: %d -> %d", source, target);
		
		simulate(vote, source, target, k, msg);
		
		
		// Gridgraph test
		SimpleGrid grid = new SimpleGrid(150,150);
		msg = String.format("Grid Graph (%s, %s), k = %d", grid.x, grid.y, k);
		
		simulate(grid.graph, grid.source, grid.target, k, msg);

	}
	
	@SuppressWarnings("unused")
	private static <V,E> void simulate(Graph<V,E> g, V s, V t, int k, String msg) {
		
		DemoRun gridTestKBest = new KBestAlgoRun(g, s, t, k, msg);
		DemoRun gridTestDiverse = new DiverseAlgoRun(g, s, t, k, msg);
		
	}

}
