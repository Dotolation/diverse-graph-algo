package shell;

import org.jgrapht.graph.DefaultWeightedEdge;

import shell.abst.DiverseShell;
import shell.inputhandler.GridInputHandler;
import shell.inputhandler.InputHandler;

public class DiverseGrid extends DiverseShell {

	public static void main(String[] args) {
		
		InputHandler<String, DefaultWeightedEdge> handler = new GridInputHandler(args);

		run(handler);

	}

}
