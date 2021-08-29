package shell;

import org.jgrapht.graph.DefaultWeightedEdge;

import graphgenerators.FileToGraph;
import shell.abst.DiverseShell;
import shell.inputhandler.FileInputHandler;
import shell.inputhandler.InputHandler;

public class DiverseDIMACS extends DiverseShell {

	public static void main(String[] args) {

		InputHandler<Integer, DefaultWeightedEdge> handler = new FileInputHandler("DIMACS", args);

		run(handler);

	}

}
