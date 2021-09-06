package shell.abst;

import java.util.HashSet;
import java.util.List;

import shell.inputhandler.bulk.BulkTestHandler;

public abstract class BulkShellTest extends AbstractShellTest {
	
	public static <V,E> void bulkRun(BulkTestHandler<V, E> h){
		
		int count = 0;
		int iters = 0;
		int ITER_LIMIT = 200000;
		
		HashSet<List<V>> visited = new HashSet<>(); 
		
		h.buildBatch();
		
		while (count < h.tests && iters < ITER_LIMIT) {
			h.stPick();
			List<V> pair = h.stPair();
			
			if(!h.isUsable() || visited.contains(pair)) { 
				continue; 
			}
			count++;
			iters++;
			visited.add(pair);
			
			DiverseShell.run(h);
			KBestShell.run(h);
			
		}
		
	}

}
