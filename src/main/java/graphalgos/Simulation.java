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
		
		int k = 5;
		System.out.println("ha!");
		//WikiVote Graph Test
		Graph<Integer, DefaultWeightedEdge> vote = FileToGraph.read("src/SNAP/twitter_combined.txt.gz", FileToGraph::readSNAP);
		int source = 34720571;
		int target = 509323; 
		String msg = String.format("Slashdot Graph: %d -> %d", source, target);
		
		simulate(vote, source, target, k, msg);
		
		// Gridgraph test
		SimpleGrid grid = new SimpleGrid(120,120);
		msg = String.format("Grid Graph (%s, %s), k = %d", grid.x, grid.y, k);
		
		//simulate(grid.graph, grid.source, grid.target, k, msg);

	}
	
	@SuppressWarnings("unused")
	private static <V,E> void simulate(Graph<V,E> g, V s, V t, int k, String msg) {
		
		//DemoRun Diverse = new DiverseAlgoRun(g, s, t, k, msg);
		//System.out.println(Diverse.elapsed);
		DemoRun KBest = new KBestAlgoRun(g, s, t, k, msg);
		System.out.println(KBest.elapsed);
		
		
	}
	
	private static void pause() {
		try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
	}

}
