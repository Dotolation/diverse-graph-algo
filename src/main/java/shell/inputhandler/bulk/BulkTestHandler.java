package shell.inputhandler.bulk;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import graphalgos.graphtests.GraphOverview;
import shell.inputhandler.InputHandler;

public abstract class BulkTestHandler<V,E> extends InputHandler<V, E> {

	public boolean goodPath;
	public int tests;
	
	private Queue<List<V>> vQueue;

	
	public boolean isUsable() {
		
		try {
			GraphOverview o = this.setOverview();
			goodPath = (o.stPathCount >= 30 && o.avgPathLength >= 3.0); 
			
		} catch (Exception e) {
			
			System.out.println(e);
			goodPath = false;
			return goodPath;
		}
		
		//System.out.println("Done deciding whether to include this path or not");

		return goodPath;
	}
	
	public void buildBatch() {
		
		Random rand = new Random();
		int length = g.vertexSet().size();
		List<V> vList = g.vertexSet().stream().filter(v -> rand.nextInt(length) < 200).collect(Collectors.toList());
		
		vQueue = new LinkedList<>();
		for(V s : vList) {
			for(V t : vList) {
				
				if(s.equals(t)) continue; 
				
				List<V> l = new LinkedList<>();
				l.add(s);
				l.add(t);
				vQueue.add(l);
				
			}
			
		}
		
		Collections.shuffle((List<List<V>>) vQueue);
		
		//System.out.println("Created a sample batch of source/target pairs. " + vQueue.size());

		
	}
	
	public void stPick() {
		
		List<V> pair = vQueue.poll();
		
		source = pair.get(0);
		target = pair.get(1);

	}
	
	public boolean doneTraversing() {
		
		//if(vQueue.size() % 1000 == 0) System.out.println(String.format("Queue size: %d", vQueue.size()));
		
		return vQueue.isEmpty();
	}
}
