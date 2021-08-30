package shell.inputhandler.bulk;

import org.jgrapht.graph.DefaultWeightedEdge;

import graphgenerators.FileToGraph;

public class FileBulkTestHandler extends BulkTestHandler<Integer, DefaultWeightedEdge> {
	
	public FileBulkTestHandler(String type, String args[]) {
		
		instanceName = args[0];
		
		k = Integer.parseInt(args[1]);
		tests = Integer.parseInt(args[2]);
		
		if(type.equals("DIMACS")){
			
			g = FileToGraph.read(String.format("src/%s/%s", type, instanceName), FileToGraph::readDIMACS);
		
		} else if(type.equals("SNAP")) {
			
			g = FileToGraph.read(String.format("src/%s/%s", type, instanceName), FileToGraph::readSNAP);
		}
		
	}

}
