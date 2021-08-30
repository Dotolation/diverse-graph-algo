package shell;

import org.jgrapht.graph.DefaultWeightedEdge;

import shell.abst.BulkShellTest;
import shell.inputhandler.bulk.BulkTestHandler;
import shell.inputhandler.bulk.FileBulkTestHandler;

public class TestSNAP extends BulkShellTest {

	public static void main(String[] args) {
		BulkTestHandler<Integer, DefaultWeightedEdge> handler = new FileBulkTestHandler("SNAP", args);

		bulkRun(handler);

	}

}
