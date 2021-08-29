package shell.inputhandler;

import org.jgrapht.graph.DefaultWeightedEdge;

import graphgenerators.FileToGraph;

public class FileInputHandler extends InputHandler<Integer, DefaultWeightedEdge> {
	
	public FileInputHandler(String type, String args[]) {
		
		instanceName = args[0];
		source = Integer.parseInt(args[1]);
		target = Integer.parseInt(args[2]);
		
		k = Integer.parseInt(args[3]);
		
		if(type.equals("DIMACS")){
			
			g = FileToGraph.read(String.format("src/%s/%s", type, instanceName), FileToGraph::readDIMACS);
		
		} else if(type.equals("SNAP")) {
			
			g = FileToGraph.read(String.format("src/%s/%s", type, instanceName), FileToGraph::readSNAP);
		}
		
	}

}
