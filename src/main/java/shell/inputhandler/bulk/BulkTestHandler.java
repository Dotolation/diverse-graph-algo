package shell.inputhandler.bulk;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import graphalgos.graphtests.GraphOverview;
import shell.inputhandler.InputHandler;

public abstract class BulkTestHandler<V,E> extends InputHandler<V, E> {

	public boolean goodPath;
	public int tests;
	
	private List<V> vList;
	
	public boolean isUsable() {
		
		try {
			GraphOverview o = this.setOverview();
			goodPath = (o.stPathCount >= 25 && o.avgPathLength >= 3.5); 
			
		} catch (Exception e) {
			
			System.out.println(e);
			goodPath = false;
			return goodPath;
		}

		return goodPath;
	}
	
	public void stPick() {
		
		Random rand = new Random();
		
		if(vList==null) vList = new ArrayList<>(g.vertexSet());
		
		source = vList.get(rand.nextInt(vList.size()));
		target = vList.get(rand.nextInt(vList.size()));
		
		while(source.equals(target)) {
			target = vList.get(rand.nextInt(vList.size()));
		}

	}
}
