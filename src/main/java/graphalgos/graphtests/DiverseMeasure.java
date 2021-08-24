package graphalgos.graphtests;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;

public class DiverseMeasure {
	
	public static <V,E> void compute(List<Set<E>> es, Graph<V,E> g) {
		
		double weight = 0.0;
		
		for(int i=0; i < es.size(); i++) {
			
			Set<E> a = es.get(i);
			
			for(int j=i; j < es.size(); j++) {
				
				if (i==j) continue;

				Set<E> b = es.get(j);
				
				//Compute the intersection first 
				Set<E> overlap = new HashSet<>(a);
		        overlap.retainAll(b);
				
		        //remove the intersection to compute the symmetric difference 
		        Set<E> symDiff = new HashSet<>(a);
				symDiff.addAll(b);
				symDiff.removeAll(overlap);
				
				weight += weightSum(symDiff, g);
				
			}
			
		}
		
		System.out.println(String.format("Diverse Measure: %f", weight));
		
	}
	
	private static <V,E> double weightSum(Set<E> e, Graph<V,E> g) {
		
		double sum = 0.0d;
		
		for(E edge : e) {
			sum += g.getEdgeWeight(edge);
		}
		
		return sum;
		
	}

}
