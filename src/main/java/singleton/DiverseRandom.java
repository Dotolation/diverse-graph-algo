package singleton;

import org.jgrapht.graph.DefaultWeightedEdge;

import shell.abst.DiverseShell;
import shell.inputhandler.InputHandler;
import shell.inputhandler.RandomInputHandler;

public class DiverseRandom extends DiverseShell {
	
	public static void main(String[] args) {
		
		InputHandler<Integer, DefaultWeightedEdge> handler = new RandomInputHandler(args);

		run(handler);

	}

}
