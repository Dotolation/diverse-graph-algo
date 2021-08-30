package shell.inputhandler.bulk;

import org.jgrapht.graph.DefaultWeightedEdge;

import graphgenerators.SimpleGnmRandom;

public class RandomBulkTestHandler extends BulkTestHandler<Integer, DefaultWeightedEdge> {
	
	public RandomBulkTestHandler(String[] args) {
		
		int n = Integer.parseInt(args[0]);
	    int m = Integer.parseInt(args[1]);
	    long seed = Long.parseLong(args[2]);
	    
	    k = Integer.parseInt(args[3]);
	    tests = Integer.parseInt(args[4]);
	    
	    instanceName = String.format("GnmRand%d_%d", n, m);
	    
	    g = new SimpleGnmRandom(n, m, seed).graph;
		
	}

}
