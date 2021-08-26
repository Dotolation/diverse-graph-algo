package shell;

import graphgenerators.FileToGraph;
import shell.abst.KBestShell;

public class KBestSNAP extends KBestShell {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String fileName = args[0];
		int s = Integer.parseInt(args[1]);
		int t = Integer.parseInt(args[2]);
		int k = Integer.parseInt(args[3]);
		
		run(fileName, FileToGraph.read("src/SNAP/" + fileName, FileToGraph::readSNAP), s, t, k);

	}

}
