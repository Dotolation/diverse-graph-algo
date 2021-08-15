package graphalgos;

import graphalgos.graphtests.DemoRun;
import graphalgos.graphtests.DiverseAlgoRun;
import graphalgos.graphtests.KBestAlgoRun;

public class Simulation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleGrid grid = new SimpleGrid(5,5);
		int k = 2;
		String msg = String.format("Grid Graph (%s, %s), k = %d", grid.x, grid.y, k);
		
		DemoRun gridTestKBest = new KBestAlgoRun(grid.graph, grid.source, grid.target, k, msg);
		DemoRun gridTestDiverse = new DiverseAlgoRun(grid.graph, grid.source, grid.target, k, msg);

	}

}
