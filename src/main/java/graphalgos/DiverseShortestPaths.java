package graphalgos;

import java.util.Deque;
import java.util.LinkedList;
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
import org.jgrapht.alg.shortestpath.BellmanFordShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;


public class DiverseShortestPaths {
	
	private Graph<String, DefaultWeightedEdge> g;
	public int stPathsSize;
	
	public DiverseShortestPaths(Graph<String, DefaultWeightedEdge> g) {
		this.g = g;
	}
		
	//#1: 不要な辺を削除
	public Graph<String, DefaultWeightedEdge> edgeRemoval(String source, String target){
		
		/*initialization of shortest path class (Bellman-Ford)
		  https://jgrapht.org/javadoc/org.jgrapht.core/org/jgrapht/alg/shortestpath/package-summary.html
		  for other shortest path algorithms. 
		*/
		SingleSourcePaths<String, DefaultWeightedEdge> shortest = new BellmanFordShortestPath<>(g).getPaths(source);
		
		////System.out.println("Done with single source paths generation");
		
		//initialization
		Deque<String> vertexQ = new LinkedList<>();
		vertexQ.add(source);
		
		//スタートから各頂点までの最短経路ではない辺を削除。DFS
		while(!vertexQ.isEmpty()) {
			
			String u = vertexQ.poll();
			Set<DefaultWeightedEdge> uTov = g.outgoingEdgesOf(u);
			
			if(uTov == null || uTov.size() < 1) continue;
			
			List<DefaultWeightedEdge> purgeList = new LinkedList<>();
			
			uTov.forEach(edge -> {
				
				String v = g.getEdgeTarget(edge);
				//System.out.println("U: " + u + " V: " + v);
				
				double uDist = shortest.getWeight(u);
				
				//shortest.getPathWeight()メソッドでsourceからｖまで最短距離計算
				
				//System.out.println("Shortest distance: " + shortest.getWeight(v));
				//System.out.println("This paths's distance: " + (uDist + g.getEdgeWeight(edge)));
				if(uDist + g.getEdgeWeight(edge) > shortest.getWeight(v)) {
					purgeList.add(edge);
					//System.out.println(String.format("Mercilessly Purging : (%s, %s) ", u, v));
				} else {
					//System.out.println("Adding the next vertex to the queue: " + v);
					vertexQ.addFirst(v);
				}
				
			});
			
			for(DefaultWeightedEdge unneeded : purgeList) {
				g.removeEdge(unneeded);
			}
			
		}
		
		//System.out.println("Done with the DFS");
		
		this.stPathsSize = g.edgeSet().size();
		
		return g;

	}
	
	//#2 k-duplication
	public Graph<String, DefaultWeightedEdge> kDuplication(int k){
		
		
		Graph<String, DefaultWeightedEdge> kDuplicate = new DirectedWeightedMultigraph<>(DefaultWeightedEdge.class);
		
		g.edgeSet().forEach(edge -> {
			
			String pre = g.getEdgeSource(edge);
			String succ = g.getEdgeTarget(edge);
			
			kDuplicate.addVertex(pre);
			kDuplicate.addVertex(succ);
			
			double weight = g.getEdgeWeight(edge);
			
			for (int i = 1; i <= k; i++) {
				
				DefaultWeightedEdge copy = kDuplicate.addEdge(pre, succ);
				kDuplicate.setEdgeWeight(copy, weight - 2*i + 1);
			    
			}
			
		});
		
		return kDuplicate;
		
		
	}
	
	//#3 minCostFlowでFlow Networkを求める
	public Graph<String, DefaultWeightedEdge> minCostFlow(Graph<String, DefaultWeightedEdge> gMulti, String source, String target, int k){
		
		Function<DefaultWeightedEdge, Integer> minCapacity = edge -> 0;
		Function<DefaultWeightedEdge, Integer> maxCapacity = edge -> 1;
		Function<DefaultWeightedEdge, Double> arcCost = edge -> -gMulti.getEdgeWeight(edge);
		Function<String, Integer> supplyDemand = vertex -> {
			
			if(vertex.equals(source)) {
				
				return k;
				
			} else if (vertex.equals(target)) {
				
				return -k;
						
			}
			
			return 0;
			
		};
		
		//mincostflow 計算
		MinimumCostFlowProblem<String, DefaultWeightedEdge> problem = new MinimumCostFlowProblemImpl<>(gMulti, supplyDemand, maxCapacity, minCapacity, arcCost);  
		MinimumCostFlowAlgorithm<String, DefaultWeightedEdge> solver = new CapacityScalingMinimumCostFlow<>();
		Map<DefaultWeightedEdge, Double> flowMap = solver.getMinimumCostFlow(problem).getFlowMap();
		
		
		Graph<String, DefaultWeightedEdge> flowNet = new DirectedWeightedMultigraph<>(DefaultWeightedEdge.class);
		
		flowMap.keySet().forEach(edge -> {
			
			double flo = flowMap.get(edge);
			if(flo > 0.0d) {
				
				String u = gMulti.getEdgeSource(edge);
				String v = gMulti.getEdgeTarget(edge);
				
				flowNet.addVertex(u);
				flowNet.addVertex(v);
				DefaultWeightedEdge networkEdge = flowNet.addEdge(u, v);
				flowNet.setEdgeWeight(networkEdge, g.getEdgeWeight(g.getEdge(u, v)));
				
				System.out.println(String.format("edge: %s, flow: %s", edge.toString(), flo));
			}
			
		});
		
		return flowNet;
		
	}
	
	
	

}
