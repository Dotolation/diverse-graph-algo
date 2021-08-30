package shell.abst;

import shell.inputhandler.bulk.BulkTestHandler;

public abstract class BulkShellTest extends AbstractShellTest {
	
	public static <V,E> void bulkRun(BulkTestHandler<V, E> h){
		
		int count = 0;
		
		h.buildBatch();
		
		while (count < h.tests || !(h.doneTraversing())) {
			h.stPick();
			
			if(!h.isUsable()) { 
				continue; 
			}
			count++;
			
			DiverseShell.run(h);
			KBestShell.run(h);
			
		}
		
	}

}
