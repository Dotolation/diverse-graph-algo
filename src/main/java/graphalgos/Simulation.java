package graphalgos;

import graphalgos.graphtests.DemoRun;
import graphalgos.graphtests.DiverseAlgoRun;
import graphalgos.graphtests.KBestAlgoRun;

public class Simulation {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// Gridgraph test
		SimpleGrid grid = new SimpleGrid(150,150);
		int k = 5;
		String msg = String.format("Grid Graph (%s, %s), k = %d", grid.x, grid.y, k);
		
		DemoRun gridTestKBest = new KBestAlgoRun(grid.graph, grid.source, grid.target, k, msg);
		DemoRun gridTestDiverse = new DiverseAlgoRun(grid.graph, grid.source, grid.target, k, msg);

	}

}
