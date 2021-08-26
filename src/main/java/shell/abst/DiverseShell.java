package shell.abst;

import org.jgrapht.Graph;

import graphalgos.graphtests.DiverseAlgoRun;

public abstract class DiverseShell extends AbstractShellTest{
	
	public static <V,E> void run(String name, Graph<V,E> g, V s, V t, int k) {
		abstractRun(name, "Diverse", g, s, t, k, new DiverseAlgoRun(g, s, t, k, ""));
		
	}
	

}
