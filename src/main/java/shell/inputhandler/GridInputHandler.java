package shell.inputhandler;

import org.jgrapht.graph.DefaultWeightedEdge;

import graphgenerators.SimpleGrid;

public class GridInputHandler extends InputHandler<String, DefaultWeightedEdge> {
	
	public GridInputHandler(String[] args) {
		
		int x = Integer.parseInt(args[0]);
		int y = Integer.parseInt(args[1]);
		k = Integer.parseInt(args[2]);
		
		sharedSetup(x, y, k);	

	}
	
	public GridInputHandler(int dim, int k) {
		
		int x = dim;
		int y = dim;
		
		sharedSetup(x, y, k);
			
	}
	
	private void sharedSetup(int x, int y, int k) {
		
		instanceName = String.format("Grid%d_%d", x, y);
		SimpleGrid grid = new SimpleGrid(x, y);
		g = grid.graph;
		
		source = grid.source;
		target = grid.target;
		this.k = k;
		
		setOverview();
		
	}

}
