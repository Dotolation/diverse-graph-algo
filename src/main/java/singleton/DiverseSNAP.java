package singleton;

import org.jgrapht.graph.DefaultWeightedEdge;

import shell.abst.DiverseShell;
import shell.inputhandler.FileInputHandler;
import shell.inputhandler.InputHandler;

public class DiverseSNAP extends DiverseShell {

	public static void main(String[] args) {
		
		InputHandler<Integer, DefaultWeightedEdge> handler = new FileInputHandler("SNAP", args);

		run(handler);

	}

}
