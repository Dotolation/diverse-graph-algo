package shell.inputhandler;

import org.jgrapht.graph.DefaultWeightedEdge;

import graphgenerators.SimpleGnmRandom;

public class RandomInputHandler extends InputHandler<Integer, DefaultWeightedEdge> {
	
	public RandomInputHandler(String[] args) {
		
		int n = Integer.parseInt(args[0]);
		int m = Integer.parseInt(args[1]);
		long seed = Long.parseLong(args[2]);
		
		source = Integer.parseInt(args[3]);
		target = Integer.parseInt(args[4]);
		
		k = Integer.parseInt(args[5]);

		instanceName = String.format("GnmRand%d_%d", n, m);
		SimpleGnmRandom gen = new SimpleGnmRandom(n,m,seed);
		g = gen.graph;

	}

}
