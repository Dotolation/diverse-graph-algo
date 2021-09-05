package graphgenerators;

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
		
		Supplier<Integer> defaultvSupplier = new Supplier<Integer>()
        {
            private int id = 0;

            @Override
            public Integer get()
            {
            	return ++id;
            }
        };

        graph = new SimpleDirectedWeightedGraph<>(defaultvSupplier, SupplierUtil.createDefaultWeightedEdgeSupplier());
		GraphGenerator<Integer, DefaultWeightedEdge, Integer> gnmGenerator = new GnmRandomGraphGenerator<>(n, m, seed);
		gnmGenerator.generateGraph(graph);
		
	}

}
