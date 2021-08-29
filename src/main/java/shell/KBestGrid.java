package shell;

import org.jgrapht.graph.DefaultWeightedEdge;

import shell.abst.KBestShell;
import shell.inputhandler.GridInputHandler;
import shell.inputhandler.InputHandler;

public class KBestGrid extends KBestShell {

	public static void main(String[] args) {
		
		InputHandler<String, DefaultWeightedEdge> handler = new GridInputHandler(args);

		run(handler);

	}

}
