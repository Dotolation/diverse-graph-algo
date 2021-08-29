package shell;

import org.jgrapht.graph.DefaultWeightedEdge;

import graphgenerators.SimpleGnmRandom;
import shell.abst.KBestShell;
import shell.inputhandler.FileInputHandler;
import shell.inputhandler.InputHandler;

public class KBestRandom extends KBestShell {
	
	public static void main(String[] args) {
		
		InputHandler<Integer, DefaultWeightedEdge> handler = new FileInputHandler("DIMACS", args);

		run(handler);
		
	}

}
