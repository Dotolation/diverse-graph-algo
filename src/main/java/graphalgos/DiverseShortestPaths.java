package graphalgos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.jgrapht.Graph;
import org.jgrapht.alg.flow.mincost.CapacityScalingMinimumCostFlow;
import org.jgrapht.alg.flow.mincost.MinimumCostFlowProblem;
import org.jgrapht.alg.flow.mincost.MinimumCostFlowProblem.MinimumCostFlowProblemImpl;
import org.jgrapht.alg.interfaces.MinimumCostFlowAlgorithm;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm.SingleSourcePaths;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.GraphIterator;


public class DiverseShortestPaths <V,E> {
	
	private Graph<V, E> g;
	private V source;
	private V target;
	private int k;
	
	private Graph<V, DefaultWeightedEdge> kDuplicate;
	
	public DiverseShortestPaths(Graph<V, E> g, V source, V target, int k) {
		this.g = g;
		this.k = k; 
		this.source = source;
		this.target = target; 
		
		preprocess();
		minCostFlow();
		
	}
		
	//#1: 不要な辺を削除 + kDuplication 
	private void preprocess(){
		
		/*initialization of shortest path class (Bellman-Ford)
		  https://jgrapht.org/javadoc/org.jgrapht.core/org/jgrapht/alg/shortestpath/package-summary.html
		  for other shortest path algorithms. 
		*/
		SingleSourcePaths<V, E> shortest = new DijkstraShortestPath<>(g).getPaths(source);
		////System.out.println("Done with single source paths generation");

		//initialization
		kDuplicate = new DirectedWeightedMultigraph<>(DefaultWeightedEdge.class);
		
		//k-duplication. たが、各頂点までの最短経路ではない辺はDuplicationに含まれない。
		g.edgeSet().forEach(e -> {
			V u = g.getEdgeSource(e);
			V v = g.getEdgeTarget(e);
			double weight = g.getEdgeWeight(e);
			
			double uDist = shortest.getWeight(u);
			double vDist = shortest.getWeight(v);
			
			if (uDist + g.getEdgeWeight(e) <= vDist) {
				
				//k-duplication
				for (int i = 1; i <= k; i++) {
					kDuplicate.addVertex(u);
					kDuplicate.addVertex(v);
					DefaultWeightedEdge newEdge = kDuplicate.addEdge(u, v);
					kDuplicate.setEdgeWeight(newEdge, weight - 2*i + 1);
	            }
			}
			
		});

	}
	
	//#2 minCostFlowでFlow Networkを求める
	private void minCostFlow(){
		
		Function<DefaultWeightedEdge, Integer> minCapacity = edge -> 0;
		Function<DefaultWeightedEdge, Integer> maxCapacity = edge -> 1;
		Function<DefaultWeightedEdge, Double> arcCost = edge -> -kDuplicate.getEdgeWeight(edge);
		Function<V, Integer> supplyDemand = vertex -> {
			
			if(vertex.equals(source)) {
				
				return k;
				
			} else if (vertex.equals(target)) {
				
				return -k;
						
			}
			
			return 0;
			
		};
		
		//mincostflow 計算
		MinimumCostFlowProblem<V, DefaultWeightedEdge> problem = new MinimumCostFlowProblemImpl<>(kDuplicate, supplyDemand, maxCapacity, minCapacity, arcCost);  
		MinimumCostFlowAlgorithm<V, DefaultWeightedEdge> solver = new CapacityScalingMinimumCostFlow<>();
		Map<DefaultWeightedEdge, Double> flowMap = solver.getMinimumCostFlow(problem).getFlowMap();

		flowMap.keySet().forEach(edge -> {
			
			double flow = flowMap.get(edge);
			
			if(!(flow > 0.0d)) {
				kDuplicate.removeEdge(edge);

			} 
			
		});
		
	}
	
	@SuppressWarnings("unused")
	public List<Set<E>> paths(){
		
		List<Set<E>> pathList = new ArrayList<>();
		for (DefaultWeightedEdge e : kDuplicate.outgoingEdgesOf(source)) pathList.add(new HashSet<>());
		
		for(Set<E> path : pathList) {
			
			V start = source;
			GraphIterator<V, DefaultWeightedEdge> dfs = new DepthFirstIterator<>(kDuplicate, start);
			
			while(!start.equals(target)) {
				
				V next = dfs.next();
				kDuplicate.removeEdge(kDuplicate.getEdge(start, next));
				path.add(g.getEdge(start, next));
				
				start = next;
				
			}
			
		}
		
		return pathList;
		
	}

}
