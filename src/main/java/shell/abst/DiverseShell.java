package shell.abst;

import graphalgos.graphtests.DemoRun;
import graphalgos.graphtests.GridDiverseRun;
import shell.inputhandler.InputHandler;

public abstract class DiverseShell extends AbstractShellTest{
	
	public static <V,E> void run(InputHandler<V,E> h) {
		
		DemoRun algorun = new GridDiverseRun(h.cleanG, h.source, h.target, h.k);	
		abstractRun("Diverse", h, algorun);
		
	}
	

}
