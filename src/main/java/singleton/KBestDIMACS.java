package singleton;

import org.jgrapht.graph.DefaultWeightedEdge;

import shell.abst.KBestShell;
import shell.inputhandler.FileInputHandler;
import shell.inputhandler.InputHandler;

public class KBestDIMACS extends KBestShell {

	public static void main(String[] args) {
		
		InputHandler<Integer, DefaultWeightedEdge> handler = new FileInputHandler("DIMACS", args);

		run(handler);

	}

}
