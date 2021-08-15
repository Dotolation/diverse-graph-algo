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
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.traverse.GraphIterator;
import org.jgrapht.traverse.DepthFirstIterator;


public class DiverseShortestPaths {
	
	private Graph<String, DefaultWeightedEdge> g;
	private String source;
	private String target;
	private int k;
	
	private Graph<String, DefaultWeightedEdge> kDuplicate;
	
	public DiverseShortestPaths(Graph<String, DefaultWeightedEdge> g, String source, String target, int k) {
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
		SingleSourcePaths<String, DefaultWeightedEdge> shortest = new DijkstraShortestPath<>(g).getPaths(source);
		////System.out.println("Done with single source paths generation");

		//initialization
		Graph<String, DefaultWeightedEdge> multiGraph = new DirectedWeightedMultigraph<>(DefaultWeightedEdge.class);
		
		//k-duplication. たが、各頂点までの最短経路ではない辺はDuplicationに含まれない。
		g.edgeSet().forEach(e -> {
			String u = g.getEdgeSource(e);
			String v = g.getEdgeTarget(e);
			double weight = g.getEdgeWeight(e);
			
			double uDist = shortest.getWeight(u);
			double vDist = shortest.getWeight(v);
			
			if (uDist + g.getEdgeWeight(e) <= vDist) {
				
				//k-duplication
				for (int i = 1; i <= k; i++) {
					multiGraph.addVertex(u);
					multiGraph.addVertex(v);
					DefaultWeightedEdge newEdge = multiGraph.addEdge(u, v);
					multiGraph.setEdgeWeight(newEdge, weight - 2*i + 1);
	            }
			}
			
		});

		kDuplicate = multiGraph; 

	}
	
	//#2 minCostFlowでFlow Networkを求める
	private void minCostFlow(){
		
		Function<DefaultWeightedEdge, Integer> minCapacity = edge -> 0;
		Function<DefaultWeightedEdge, Integer> maxCapacity = edge -> 1;
		Function<DefaultWeightedEdge, Double> arcCost = edge -> -kDuplicate.getEdgeWeight(edge);
		Function<String, Integer> supplyDemand = vertex -> {
			
			if(vertex.equals(source)) {
				
				return k;
				
			} else if (vertex.equals(target)) {
				
				return -k;
						
			}
			
			return 0;
			
		};
		
		//mincostflow 計算
		MinimumCostFlowProblem<String, DefaultWeightedEdge> problem = new MinimumCostFlowProblemImpl<>(kDuplicate, supplyDemand, maxCapacity, minCapacity, arcCost);  
		MinimumCostFlowAlgorithm<String, DefaultWeightedEdge> solver = new CapacityScalingMinimumCostFlow<>();
		Map<DefaultWeightedEdge, Double> flowMap = solver.getMinimumCostFlow(problem).getFlowMap();
		
		flowMap.keySet().forEach(edge -> {
			
			double flow = flowMap.get(edge);
			
			if(!(flow > 0.0d)) {
				kDuplicate.removeEdge(edge);

			} 
			
		});
		
	}
	
	public List<Set<DefaultWeightedEdge>> paths(){
		
		List<Set<DefaultWeightedEdge>> pathList = new ArrayList<>();
		for (DefaultWeightedEdge e : kDuplicate.outgoingEdgesOf(source)) pathList.add(new HashSet<>());

		System.out.println(pathList.size() == k);
		
		for(Set<DefaultWeightedEdge> path : pathList) {
			
			String start = source;
			GraphIterator<String, DefaultWeightedEdge> dfs = new DepthFirstIterator<>(kDuplicate, start);
			dfs.addTraversalListener(null);
			
			while(start != target) {
				
				String next = dfs.next();
				kDuplicate.removeEdge(kDuplicate.getEdge(start, next));
				path.add(g.getEdge(start, next));
				
				start = next;
				
			}
			
		}
		
		return pathList;
		
	}

}
