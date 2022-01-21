package shell.inputhandler;

import org.jgrapht.Graph;

import graphalgos.graphtests.DemoRun;
import graphalgos.graphtests.GraphOverview;

public abstract class InputHandler<V,E>{
	
	public String instanceName;
	
	public Graph<V, E> g; 
	public Graph<V, E> cleanG; 
	public V source;
	public V target; 
	
	public int k; 
	
	public GraphOverview overview;

	public GraphOverview setOverview() {
		
		overview = new GraphOverview();
		cleanG = overview.getGraph(g, source, target);
		return overview;
		
	}
	
	public GraphOverview snapOverview() {
		
		overview = new GraphOverview();
		cleanG = overview.getGraph(g, source, target, true);
		return overview;
		
	}
	

}
