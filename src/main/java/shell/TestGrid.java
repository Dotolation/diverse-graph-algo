package shell;

import org.jgrapht.graph.DefaultWeightedEdge;

import shell.abst.DiverseShell;
import shell.abst.KBestShell;
import shell.inputhandler.GridInputHandler;
import shell.inputhandler.InputHandler;

public class TestGrid {

	public static void main(String[] args) {
		
		int dim = Integer.parseInt(args[0]);
		int increment = Integer.parseInt(args[1]);
		
		for(int i = increment; i <= dim; i += increment) {
			
			for(int j=2; j < args.length; j++ ) {
				
				int k = Integer.parseInt(args[j]);
				
				InputHandler<String, DefaultWeightedEdge> h = new GridInputHandler(i, k);
				
				DiverseShell.run(h);
				KBestShell.run(h);
				
			}
			
		}

	}

}
