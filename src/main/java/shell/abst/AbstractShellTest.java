package shell.abst;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import graphalgos.graphtests.DemoRun;
import graphalgos.graphtests.GraphOverview;
import shell.inputhandler.InputHandler;

public abstract class AbstractShellTest {
	
	
	public static <V,E> void abstractRun(String algoType,InputHandler<V, E> h,  DemoRun algo) {
		
		GraphOverview o = h.overview;
		
		if(o == null) {
			
			o = h.setOverview();
			
		}
		
		V s = h.source;
		V t = h.target;
		Integer k = h.k;
		
		exportCSV("test_result.csv", h.instanceName, algoType, 
				  s.toString(), t.toString(), Integer.toString(k),
				  Integer.toString(o.vCount), Integer.toString(o.eCount),
				  Integer.toString(o.vCountPaths), Integer.toString(o.eCountPaths),
				  Integer.toString(o.stPathCount), Double.toString(o.avgPathLength),
				  Long.toString(algo.elapsed),Double.toString(algo.diversityMeasure));
		
		
		
	}
	
	public static void exportCSV (String fileName, String... args) {
		
		String toPrint = String.join(",", args);
		System.out.println(toPrint);
		
		try {
			
			String path = "target/" + fileName;
			boolean exists = new File(path).exists();
			
			BufferedWriter out = new BufferedWriter(new FileWriter(path, true));
			if(!exists) {
				out.write("Instance_Name,Algo_Type,Source,Target,k,"
					+ "#V,#E,#V_stPaths,#E_stPaths,"
					+ "#stPaths,Avg_stPath_Length,"
					+ "Milliseconds,Diversity");
				
				out.newLine();
			}

			out.write(toPrint);
			out.newLine();
			out.close();
			
		} catch (IOException e) {
			
			System.out.println(e);
			
		}
		
	}

}
