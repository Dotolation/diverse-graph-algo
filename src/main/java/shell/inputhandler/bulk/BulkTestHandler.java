package shell.inputhandler.bulk;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import graphalgos.graphtests.GraphOverview;
import shell.inputhandler.InputHandler;

public abstract class BulkTestHandler<V,E> extends InputHandler<V, E> {

	public boolean goodPath;
	public int tests;
	
	private List<V> vList;
	protected static Random rand;

	
	public boolean isUsable() {
		
		ShortestPathAlgorithm<V, E> fw = new DijkstraShortestPath<>(g);
		GraphPath<V,E> path = fw.getPath(source, target);

		if(path==null) { 
			//System.out.println("no such path exists");
			return false;
			
		} else {
			//System.out.println();
			//System.out.println(path);
		}
		
		try {
			GraphOverview o = this.setOverview();
			goodPath = (o.stPathCount >= 3 * k && o.avgPathLength >= 3.0); 
			
		} catch (Exception e) {
			
			System.out.println(e);
			goodPath = false;
			return goodPath;
		}
		
		//System.out.println("Done deciding whether to include this path or not");

		return goodPath;
	}
	
	public void buildBatch() {
		this.vList = new ArrayList<>(g.vertexSet());
		
	}
	
	public void stPick() {
		
		int l = vList.size();
		
		source = vList.get(rand.nextInt(l));
		target = vList.get(rand.nextInt(l));
		
		while(source == target) {
			
			target = vList.get(rand.nextInt(l));
			
		}

	}
	
	public List<V> stPair() {
		
		List<V> p = new LinkedList<>();
		p.add(source);
		p.add(target);
		
		return p;
	}
	
}
