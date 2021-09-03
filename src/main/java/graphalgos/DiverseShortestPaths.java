package graphalgos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.flow.mincost.CapacityScalingMinimumCostFlow;
import org.jgrapht.alg.flow.mincost.MinimumCostFlowProblem;
import org.jgrapht.alg.flow.mincost.MinimumCostFlowProblem.MinimumCostFlowProblemImpl;
import org.jgrapht.alg.interfaces.MinimumCostFlowAlgorithm;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm.SingleSourcePaths;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.shortestpath.EppsteinShortestPathIterator;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import graphalgos.graphtests.DemoRun;


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
		
		//initialization
		kDuplicate = new DirectedWeightedMultigraph<>(DefaultWeightedEdge.class);

		preprocess();
		//eppsteinPreprocess();

		minCostFlow();

		
	}
		
	//#1: 最短距離ではない辺を削除 + kDuplication 
	private void preprocess(){
		
		/*initialization of shortest path class (Djikstra)
		  https://jgrapht.org/javadoc/org.jgrapht.core/org/jgrapht/alg/shortestpath/package-summary.html
		  for other shortest path algorithms. 
		*/
		SingleSourcePaths<V, E> shortest = new DijkstraShortestPath<>(g).getPaths(source);
		
		
		g.edgeSet().forEach(e -> {
			V u = g.getEdgeSource(e);
			V v = g.getEdgeTarget(e);
			double weight = g.getEdgeWeight(e);
			
			double uDist = shortest.getWeight(u);
			double vDist = shortest.getWeight(v);
			
			//k-duplication.
			if (uDist + weight <= vDist) addKCopies(e);
			
		});

	}
	
	//#1-alternative: sからｔまでのShortest Pathにはない辺を「全部」削除。 そしてkDuplication 
	//全部削除しますので、minCostFlow()のスピードが速くなります。
	private void eppsteinPreprocess() {

		Set<E> edgeSet = new HashSet<>(); //必要な辺（Shortest Pathに含まれた辺）
		
		Iterator<GraphPath<V, E>> epp = new EppsteinShortestPathIterator<>(g, source, target);
		if(!epp.hasNext()) return ;

		double w = epp.next().getWeight(); 
		int count = 1;
		
		while(epp.hasNext()) {
			
			//Shortest Pathsが多すぎ（１万以上）場合は、既存のpreprocessingが早いです。
			//その場合、このメソッドを止めて既存のものを実行します。
			if(count >= 10000) { 
				preprocess();
				return ;
			}
			
			GraphPath<V, E> next = epp.next();
			
			//このパスの長さがShortestではない時
			if(next.getWeight() > w) break;
			
			//このパスの長さはShortestと同じ。
			count++;
			edgeSet.addAll(next.getEdgeList());
			
			
		}
		edgeSet.forEach(e -> addKCopies(e));
		
		
	}
	
	//#1のK-Duplicationです。G*を作ります。
	private void addKCopies(E e) {
		
		//選択した辺の情報
		V u = g.getEdgeSource(e);
		V v = g.getEdgeTarget(e);
		double w = g.getEdgeWeight(e);
		
		//Duplication
		kDuplicate.addVertex(u);
		kDuplicate.addVertex(v);
		
		for (int i = 1; i <= k; i++) {
			DefaultWeightedEdge newEdge = kDuplicate.addEdge(u, v);
			kDuplicate.setEdgeWeight(newEdge, w - 2*i + 1); //w'の公式
        }

	}
	
	//#2 minCostFlowでFlow Networkを求める
	private void minCostFlow(){
		
		//0 <= edge capacity <= 1
		Function<DefaultWeightedEdge, Integer> minCapacity = edge -> 0; 
		Function<DefaultWeightedEdge, Integer> maxCapacity = edge -> 1;
		
		//最大化のため, w'ではなく-w'そ使用
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
	
	//#3. Flow Networkからパス（実はパス内の辺の集合）たちを求める
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
