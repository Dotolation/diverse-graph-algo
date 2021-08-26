package shell;

import graphgenerators.SimpleGnmRandom;
import shell.abst.DiverseShell;

public class DiverseRandom extends DiverseShell {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int n = Integer.parseInt(args[0]);
		int m = Integer.parseInt(args[1]);
		long seed = Long.parseLong(args[2]);
		int s = Integer.parseInt(args[3]);
		int t = Integer.parseInt(args[4]);
		int k = Integer.parseInt(args[5]);
		
		SimpleGnmRandom gen = new SimpleGnmRandom(n,m,seed);
		
		run(String.format("GnmRand%d_%d", n, m), gen.graph, s, t, k);
		
		
		
	}

}
