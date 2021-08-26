package shell;

import graphgenerators.SimpleGrid;
import shell.abst.DiverseShell;

public class DiverseGrid extends DiverseShell {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int x = Integer.parseInt(args[0]);
		int y = Integer.parseInt(args[1]);
		int k = Integer.parseInt(args[2]);
		
		SimpleGrid grid = new SimpleGrid(x, y);
		
		run(String.format("Grid%d_%d", x, y), grid.graph, grid.source, grid.target, k);
		
		
		
	}

}
