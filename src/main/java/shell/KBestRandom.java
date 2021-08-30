package shell;

import org.jgrapht.graph.DefaultWeightedEdge;

import shell.abst.KBestShell;
import shell.inputhandler.FileInputHandler;
import shell.inputhandler.InputHandler;
import shell.inputhandler.RandomInputHandler;

public class KBestRandom extends KBestShell {
	
	public static void main(String[] args) {
		
		InputHandler<Integer, DefaultWeightedEdge> handler = new RandomInputHandler(args);

		run(handler);
		
	}

}
