package shell;

import org.jgrapht.graph.DefaultWeightedEdge;

import shell.abst.BulkShellTest;
import shell.inputhandler.bulk.BulkTestHandler;
import shell.inputhandler.bulk.RandomBulkTestHandler;

public class TestRandom extends BulkShellTest {
	
public static void main(String[] args) {

		BulkTestHandler<Integer, DefaultWeightedEdge> handler = new RandomBulkTestHandler(args);

		bulkRun(handler);
		
	}

}
