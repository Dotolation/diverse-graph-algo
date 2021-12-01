package graphalgos;

import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;

public class DiverseBipartiteMatching <V, E> {

	public Graph<V, E> g;
	public Set<V> A;
	public Set<V> B;
	public int k;
	
	
	public V source; 
	public V sink;
	
	public Graph<V, DefaultWeightedEdge> gPrime;
	
	public DiverseBipartiteMatching (Graph<V,E> g, Set<V> A, Set<V> B, int k) {
		
		this.g = g;
		this.A = A;
		this.B = B;
		
		this.k = k;
		
		//initialization
		gPrime = new DirectedWeightedMultigraph<>(DefaultWeightedEdge.class);
		
	}
	
	private void kDuplicate() {
		
		g.edgeSet().forEach(edge ->{
			
			V u = g.getEdgeSource(edge);
			V v = g.getEdgeTarget(edge);
			double w = g.getEdgeWeight(edge);
			
			gPrime.addVertex(u);
			gPrime.addVertex(v);
			
			for (int i=1; i <=k; i++) {
				
				DefaultWeightedEdge eDuplicate = gPrime.addEdge(u, v);
				gPrime.setEdgeWeight(eDuplicate, w * (k - (2*i) + 1));
				
			}
			
			
		});
		
	}
	
	public static Supplier<Integer> intSupplier(Set<Integer> vertexSet){
		
		Supplier<Integer> sup = () -> {
			
			Random rand = new Random();
			int randInt = rand.nextInt(Integer.MAX_VALUE);
			
			while(vertexSet.contains(randInt)) {
				
				randInt = rand.nextInt(Integer.MAX_VALUE);
				
			}
			
			System.out.println("IntegerVertexSupplier: " + randInt);
			
			return randInt;
			
		};
		
		return sup;
	}
	
	private void minCostFlow(Supplier<V> vertexSupplier) {
		
		V source = vertexSupplier.get();
		//new Source node
		gPrime.addVertex(source);
		
		for (V a : A) {
			
			DefaultWeightedEdge edge = gPrime.addEdge(source, a);
			gPrime.setEdgeWeight(edge, 0d);
			
		}
		A.add(source);
		
		
		
		//new Sink node
		V sink = vertexSupplier.get();
		gPrime.addVertex(sink);
		for (V b : B) {
			
			DefaultWeightedEdge edge = gPrime.addEdge(b, sink);
			gPrime.setEdgeWeight(edge, 0d);
			
		}
		B.add(sink);
		
		
		

		
	}
	
	
	
	
}
