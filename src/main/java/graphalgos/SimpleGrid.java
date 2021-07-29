package graphalgos;

import java.util.function.Supplier;

import org.jgrapht.Graph;
import org.jgrapht.generate.GraphGenerator;
import org.jgrapht.generate.GridGraphGenerator;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.util.SupplierUtil;

public class SimpleGrid {
	
	public int x;
	public int y;
	
	public String source = "<1|1>";
	public String target;
	
	protected Graph<String, DefaultWeightedEdge> graph;
	
	public SimpleGrid(int x, int y) {
		
		this.x = x;
		this.y = y;
		
		target = String.format("<%s|%s>", x, y);
		
		Supplier<String> defaultvSupplier = new Supplier<String>()
        {
            private int id = 0;

            @Override
            public String get()
            {
            	return String.format("<%s|%s>", (id%x) + 1, id++/x + 1);
            }
        };
        
        graph = new DefaultDirectedWeightedGraph<>(defaultvSupplier, SupplierUtil.createDefaultWeightedEdgeSupplier());
		GraphGenerator<String, DefaultWeightedEdge, String> gridGenerator = new GridGraphGenerator<>(x, y);
		gridGenerator.generateGraph(graph);
		
	}

}
