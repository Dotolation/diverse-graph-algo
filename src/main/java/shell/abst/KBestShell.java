package shell.abst;

import org.jgrapht.Graph;

import graphalgos.graphtests.KBestAlgoRun;

public abstract class KBestShell extends AbstractShellTest{
	
	public static <V,E> void run(String name, Graph<V,E> g, V s, V t, int k) {
		abstractRun(name, "Eppstein", g, s, t, k, new KBestAlgoRun(g, s, t, k, ""));
		
	}
}
