package graphalgos;

import graphalgos.graphtests.DemoRun;
import graphalgos.graphtests.DiverseAlgoRun;
import graphalgos.graphtests.KBestAlgoRun;

public class Simulation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SimpleGrid grid = new SimpleGrid(6,8);
		
		
		DemoRun gridTestKBest = new KBestAlgoRun(grid.graph, grid.source, grid.target, 3);
		DemoRun gridTestDiverse = new DiverseAlgoRun(grid.graph, grid.source, grid.target, 3);

	}

}
