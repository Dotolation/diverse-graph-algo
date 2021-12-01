package graphalgos;

import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

import org.jgrapht.Graph;
import org.jgrapht.alg.flow.mincost.CapacityScalingMinimumCostFlow;
import org.jgrapht.alg.flow.mincost.MinimumCostFlowProblem;
import org.jgrapht.alg.flow.mincost.MinimumCostFlowProblem.MinimumCostFlowProblemImpl;
import org.jgrapht.alg.interfaces.MinimumCostFlowAlgorithm;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;

public class DiverseBipartiteMatching <V, E> {

	public Graph<V, E> g;
	public Set<V> A;
	public Set<V> B;
	
	public int k;
	public int p;
	
	
	public V source; 
	public V sink;
	
	public Graph<V, DefaultWeightedEdge> gPrime;
	
	public DiverseBipartiteMatching (Graph<V,E> g, Set<V> A, Set<V> B, int k, int p) {
		
		this.g = g;
		this.A = A;
		this.B = B;
		
		this.k = k;
		this.p = p;
		
		//initialization
		gPrime = new DirectedWeightedMultigraph<>(DefaultWeightedEdge.class);
		
	}
	
	private void kDuplicate() {
		
		g.edgeSet().forEach(edge ->{
			
			V u = g.getEdgeSource(edge);
			V v = g.getEdgeTarget(edge);
			double w = g.getEdgeWeight(edge);
			
			if(A.contains(u) && B.contains(v)) {
				
				gPrime.addVertex(u);
				gPrime.addVertex(v);
				
				for (int i=1; i <=k; i++) {
					
					DefaultWeightedEdge eDuplicate = gPrime.addEdge(u, v);
					gPrime.setEdgeWeight(eDuplicate, -(w * (k - (2*i) + 1)));
					
				}
				
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
		
		source = vertexSupplier.get();
		//new Source node
		gPrime.addVertex(source);
		
		for (V a : A) {
			
			DefaultWeightedEdge edge = gPrime.addEdge(source, a);
			gPrime.setEdgeWeight(edge, 0d);
			
		}
		A.add(source);
		
		
		
		//new Sink node
		sink = vertexSupplier.get();
		gPrime.addVertex(sink);
		for (V b : B) {
			
			DefaultWeightedEdge edge = gPrime.addEdge(b, sink);
			gPrime.setEdgeWeight(edge, 0d);
			
		}
		B.add(sink);
		
		Function<DefaultWeightedEdge, Integer> minCapacity = edge -> 0; 
		
		Function<DefaultWeightedEdge, Integer> maxCapacity = edge -> {
			
			if (gPrime.getEdgeSource(edge) == source || gPrime.getEdgeTarget(edge) == sink) {
				
				return k;
				
			}
			
			return 1;
		};
		
		
		Function<DefaultWeightedEdge, Double> arcCost = edge -> gPrime.getEdgeWeight(edge);
		
		Function<V, Integer> supplyDemand = vertex -> {
			
			if(vertex.equals(source)) {
				
				return p * k;
				
			} else if (vertex.equals(sink)) {
				
				return -(p * k);
						
			}
			
			return 0;
			
		};
		
		MinimumCostFlowProblem<V, DefaultWeightedEdge> problem = new MinimumCostFlowProblemImpl<>(gPrime, supplyDemand, maxCapacity, minCapacity, arcCost);  
		MinimumCostFlowAlgorithm<V, DefaultWeightedEdge> solver = new CapacityScalingMinimumCostFlow<>();
		Map<DefaultWeightedEdge, Double> flowMap = solver.getMinimumCostFlow(problem).getFlowMap();

		flowMap.keySet().forEach(edge -> {
			
			double flow = flowMap.get(edge);
			
			if(!(flow > 0.0d)) {
				gPrime.removeEdge(edge);

			} 
			
		});		
		
		gPrime.removeVertex(source);
		gPrime.removeVertex(sink);
		
	}
	
	
	
	
}
