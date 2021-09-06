package graphgenerators;

import java.util.Random;
import java.util.function.Supplier;

import org.jgrapht.Graph;
import org.jgrapht.generate.GnmRandomGraphGenerator;
import org.jgrapht.generate.GraphGenerator;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.util.SupplierUtil;

public class SimpleGnmRandom {

	public Graph<Integer, DefaultWeightedEdge> graph;
	
	public SimpleGnmRandom(int n, int m, long seed) {

        graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        
        for(int i=1; i <= n; i++) graph.addVertex(i);
        
        Random rand = new Random(seed);
        
        int counter = 0;
        while (counter < m) {
        	
        	int u = rand.nextInt(n) + 1;
        	int v = rand.nextInt(n) + 1;
        	double weight = rand.nextInt(3) + 1;
        	
        	if(u==v || graph.getEdge(v, u) != null) continue; 
        	
        	DefaultWeightedEdge e = graph.addEdge(u, v);
        	
        	if(e==null) continue; 
        	
        	graph.setEdgeWeight(e, weight);
        	counter++;
        	
        }
        
        //System.out.println(String.format("Vertices: %d, Edges: %d", graph.vertexSet().size(), graph.edgeSet().size()));
		
	}

}
