package shell.abst;

import graphalgos.graphtests.DemoRun;
import graphalgos.graphtests.KBestAlgoRun;
import shell.inputhandler.InputHandler;

public abstract class KBestShell extends AbstractShellTest{
	
	public static <V,E> void run(InputHandler<V, E> h) {
		
		DemoRun algorun = new KBestAlgoRun(h.g, h.source, h.target, h.k, "");	
		abstractRun("KBest", h, algorun);
		
	}
}
