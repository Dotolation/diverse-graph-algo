package graphalgos.graphtests;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class DiverseMeasure {
	
	public static <V> double compute(List<Set<DefaultWeightedEdge>> es, Graph<V,DefaultWeightedEdge> g) {
		
		double weight = 0.0;
		
		for(int i=0; i < es.size(); i++) {
			
			Set<DefaultWeightedEdge> a = es.get(i);
			
			for(int j=i; j < es.size(); j++) {
				
				if (i==j) continue;

				Set<DefaultWeightedEdge> b = es.get(j);
				
				//Compute the intersection first 
				Set<DefaultWeightedEdge> overlap = new HashSet<>(a);
		        overlap.retainAll(b);
				
		        //remove the intersection to compute the symmetric difference 
		        Set<DefaultWeightedEdge> symDiff = new HashSet<>(a);
				symDiff.addAll(b);
				symDiff.removeAll(overlap);
				
				weight += weightSum(symDiff, g);
				
			}
			
		}
		
		//System.out.println(String.format("Diverse Measure: %f", weight));
		
		return weight; 
		
	}
	
	private static <V> double weightSum(Set<DefaultWeightedEdge> e, Graph<V,DefaultWeightedEdge> g) {
		
		double sum = 0.0d;
		
		for(DefaultWeightedEdge edge : e) {
			sum += g.getEdgeWeight(edge);
		}
		
		return sum;
		
	}

}
