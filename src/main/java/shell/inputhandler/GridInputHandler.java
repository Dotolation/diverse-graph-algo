package shell.inputhandler;

import org.jgrapht.graph.DefaultWeightedEdge;

import graphgenerators.SimpleGrid;

public class GridInputHandler extends InputHandler<String, DefaultWeightedEdge> {
	
	public GridInputHandler(String[] args) {
		
		int x = Integer.parseInt(args[0]);
		int y = Integer.parseInt(args[1]);
		k = Integer.parseInt(args[2]);
		
		instanceName = String.format("Grid%d_%d", x, y);
		SimpleGrid grid = new SimpleGrid(x, y);
		g = grid.graph;
		source = grid.source;
		target = grid.target;

	}

}
