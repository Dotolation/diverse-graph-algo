package shell.abst;

import graphalgos.graphtests.CleanKBestRun;
import graphalgos.graphtests.DemoRun;
import shell.inputhandler.InputHandler;

public abstract class CleanKBestShell extends AbstractShellTest{
	
	public static <V,E> void run(InputHandler<V, E> h) {
		
		DemoRun algorun = new CleanKBestRun(h.cleanG, h.source, h.target, h.k, h.overview.preprocessElapsed);	
		abstractRun("KBest", h, algorun);
		
	}
}
