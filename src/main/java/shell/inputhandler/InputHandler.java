package shell.inputhandler;

import org.jgrapht.Graph;

public abstract class InputHandler<V,E> {
	
	public String instanceName;
	
	public Graph<V, E> g; 
	public V source;
	public V target; 
	
	public int k; 

}
