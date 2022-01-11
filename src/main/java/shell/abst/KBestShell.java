package shell.abst;

import graphalgos.graphtests.DemoRun;
import graphalgos.graphtests.GridKBestRun;
import shell.inputhandler.InputHandler;

public abstract class KBestShell extends AbstractShellTest{
	
	public static <V,E> void run(InputHandler<V, E> h) {
		
		DemoRun algorun = new GridKBestRun(h.cleanG, h.source, h.target, h.k);	
		abstractRun("KBest", h, algorun);
		
	}
}
