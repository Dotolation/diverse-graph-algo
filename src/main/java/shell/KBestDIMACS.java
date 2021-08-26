package shell;

import graphgenerators.FileToGraph;
import shell.abst.KBestShell;

public class KBestDIMACS extends KBestShell {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String fileName = args[0];
		int s = Integer.parseInt(args[1]);
		int t = Integer.parseInt(args[2]);
		int k = Integer.parseInt(args[3]);
		
		run(fileName, FileToGraph.read("src/DIMACS/" + fileName, FileToGraph::readDIMACS), s, t, k);

	}

}
