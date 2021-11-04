package shell.abst;

import graphalgos.graphtests.CleanDiverseRun;
import graphalgos.graphtests.DemoRun;
import shell.inputhandler.InputHandler;

public abstract class CleanDiverseShell extends AbstractShellTest{
	
	public static <V,E> void run(InputHandler<V,E> h) {
		
		DemoRun algorun = new CleanDiverseRun(h.cleanG, h.source, h.target, h.k, h.overview.preprocessElapsed);	
		abstractRun("Diverse", h, algorun);
		
	}
	

}
