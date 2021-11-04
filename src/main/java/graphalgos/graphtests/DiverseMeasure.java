package graphalgos.graphtests;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class DiverseMeasure {
	
	public static <V> double compute(List<Set<DefaultWeightedEdge>> es, Graph<V,DefaultWeightedEdge> g, BiFunction<Set<DefaultWeightedEdge>, Graph<V,DefaultWeightedEdge>, Double> weightFunc) {
		
		double weight = 0.0;
		
		for(int i=0; i < es.size(); i++) {
			
			Set<DefaultWeightedEdge> a = es.get(i);
			//System.out.println(a);
			
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
				
				weight += weightFunc.apply(symDiff, g);
				
			}
			
		}
		
		//System.out.println(String.format("Diverse Measure: %f", weight));
		
		return weight; 
		
	}
	
	public static <V> double computeMinOrMax(List<Set<DefaultWeightedEdge>> es, Graph<V,DefaultWeightedEdge> g, boolean min, BiFunction<Set<DefaultWeightedEdge>, Graph<V,DefaultWeightedEdge>, Double> weightFunc) {
		
		double weight = min ? Double.MAX_VALUE : Double.MIN_VALUE;
		
		for(int i=0; i < es.size(); i++) {
			
			Set<DefaultWeightedEdge> a = es.get(i);
			
			for(int j=i; j < es.size(); j++) {
				
				if (i!=j) {

					Set<DefaultWeightedEdge> b = es.get(j);
				
					//if(a.equals(b)) System.out.println(String.format("Path #%d and #%d are identical.", i, j ));
					
					//Compute the intersection first 
					Set<DefaultWeightedEdge> overlap = new HashSet<>(a);
			        overlap.retainAll(b);
					
			        //remove the intersection to compute the symmetric difference 
			        Set<DefaultWeightedEdge> symDiff = new HashSet<>(a);
					symDiff.addAll(b);
					symDiff.removeAll(overlap);
					
					double currWeight = weightFunc.apply(symDiff, g); 
					
					weight = min ? Double.min(weight, currWeight): Double.max(weight, currWeight);
				
				}
			}
			
		}
		
		return weight;
		
	}
	

	
	public static <V> double weightedSum(Set<DefaultWeightedEdge> e, Graph<V,DefaultWeightedEdge> g) {
		
		double sum = 0.0d;
		
		for(DefaultWeightedEdge edge : e) {
			sum += g.getEdgeWeight(edge);

		}
		
		return sum;
		
	}
	
	public static <V> double sum(Set<DefaultWeightedEdge> e, Graph<V,DefaultWeightedEdge> g) {
		
		return e.size();
		
	}

}
