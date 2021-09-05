package shell.inputhandler;

import org.jgrapht.Graph;

import graphalgos.graphtests.DemoRun;
import graphalgos.graphtests.GraphOverview;

public abstract class InputHandler<V,E>{
	
	public String instanceName;
	
	public Graph<V, E> g; 
	public V source;
	public V target; 
	
	public int k; 
	
	public GraphOverview overview;

	public GraphOverview setOverview() {
		
		overview = new GraphOverview(g, source, target);
		return overview;
		
	}

}
