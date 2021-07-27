package graphalgos;

import java.util.function.Supplier;

import org.jgrapht.Graph;
import org.jgrapht.generate.GraphGenerator;
import org.jgrapht.generate.GridGraphGenerator;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.util.SupplierUtil;

public class Simulation {

	public static void main(String[] args) {
		
		int x = 3;
		int y = 3;
		
		Supplier<String> defaultvSupplier = new Supplier<String>()
        {
            private int id = 0;

            @Override
            public String get()
            {
            	return String.format("<%s|%s>", (id%x) + 1, id++/x + 1);
            }
        };
		
		Graph<String, DefaultWeightedEdge> g = new DefaultDirectedWeightedGraph<>(defaultvSupplier, SupplierUtil.createDefaultWeightedEdgeSupplier());
		GraphGenerator<String, DefaultWeightedEdge, String> gridGenerator = new GridGraphGenerator<>(x, y);
		gridGenerator.generateGraph(g);
		
		System.out.println(g.toString());
		
		DiverseShortestPaths divAlgo = new DiverseShortestPaths(g);
		
		divAlgo.edgeRemoval(g, "<1|1>", "<3|3>");
		
		System.out.println(g.toString());
		

	}

}
