package graphgenerators;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.shortestpath.EppsteinShortestPathIterator;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class FileToGraph {

	public static Graph<Integer, DefaultWeightedEdge> read(String filePath,
			BiConsumer<Graph<Integer, DefaultWeightedEdge>, String> graphReader) {

		Graph<Integer, DefaultWeightedEdge> g = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);

		try {
			InputStream f = new FileInputStream(filePath);
			InputStream gzip = new GZIPInputStream(f);
			Reader decoder = new InputStreamReader(gzip, "UTF-8");
			BufferedReader reader = new BufferedReader(decoder);

			String line = reader.readLine();
			//int count = 0;
			while (line != null) {
				//count++;
				//System.out.println(line);
				graphReader.accept(g, line);
				line = reader.readLine();

			}

			reader.close();
			//System.out.println(count);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Set<Integer> toRemove = g.vertexSet().stream().filter(v -> rand.nextDouble() >= ratio).collect(Collectors.toSet());
		//g.removeAllVertices(toRemove);

		return g;

	}

	public static void readSNAP(Graph<Integer, DefaultWeightedEdge> g, String line) {
		//System.out.println(line);

		if (line.charAt(0) == '#')
			return;

		int[] vertices = Arrays.stream(line.split("\\s+")).mapToInt(Integer::parseInt).toArray();
		
		

		g.addVertex(vertices[0]);
		g.addVertex(vertices[1]);
		try {
			g.addEdge(vertices[0], vertices[1]);
		} catch(Exception e) {
			//System.out.println(e);
			return ;
		}

	}

	public static void readDIMACS(Graph<Integer, DefaultWeightedEdge> g, String line) {

		if (line.charAt(0) != 'a') {
			//System.out.println(line);
			return;
		}

		int[] sanitized = Arrays.stream(line.replaceAll("[a-z] ", "").split("\\s+")).mapToInt(Integer::parseInt)
				.toArray();
		
		int u = sanitized[0];
		int v = sanitized[1];

		g.addVertex(u);
		g.addVertex(v);
		DefaultWeightedEdge edge = g.addEdge(u, v);
		//System.out.println(String.format("%d, %d, %d", sanitized[0], sanitized[1], sanitized[2]));
		
		if(edge == null) {
			
			g.setEdgeWeight(g.getEdge(u, v), sanitized[2]);
			
		} else {
			
			g.setEdgeWeight(edge, sanitized[2]);
		}
		

	}

	public static void notMain(String[] args) {
		  
		  Random rand = new Random(2021l);
		
		  Graph<Integer, DefaultWeightedEdge> wiki = read("src/slashdot.txt.gz", FileToGraph::readSNAP);
		  
		  System.out.println(String.format("Vertex Count: %d, Edge Count: %d", wiki.vertexSet().size(), wiki.edgeSet().size()));
		  
		  Set<Integer> S = wiki.vertexSet().stream().
				  filter(v -> (wiki.inDegreeOf(v) >= 5 )&&(wiki.outDegreeOf(v) >= 5)).
				  filter(v -> (wiki.inDegreeOf(v) <= 1000 )&&(wiki.outDegreeOf(v) <= 1000)).
				  filter(v -> rand.nextInt(100) < 2).collect(Collectors.toSet());
		  
		  Set<Integer> T = S.stream().filter(v -> rand.nextBoolean()).collect(Collectors.toSet());
		  S.removeAll(T);
		  
		  System.out.println(S.size() + T.size());
		  System.out.println(String.format("Vertex Count: %d, Edge Count: %d", wiki.vertexSet().size(), wiki.edgeSet().size()));
		  
		  ShortestPathAlgorithm<Integer, DefaultWeightedEdge> star = new DijkstraShortestPath<>(wiki);
		  
		  for(int u : S) {
			  System.out.println("outer");
			  for(int v: T) {
				  
				  
				  double w = star.getPathWeight(u, v);
				  if(w < 4.0 || w >= Double.POSITIVE_INFINITY) { 
					  System.out.println("skip");
					  continue;
				   } 
				 
				  int count = 0; 
				  Iterator<GraphPath<Integer, DefaultWeightedEdge>> eppstein = new EppsteinShortestPathIterator<>(wiki, u, v);
				  
				  while(eppstein.hasNext()) {
					  count++;
					  GraphPath<Integer, DefaultWeightedEdge> next = eppstein.next();
					  //System.out.println(next.getEdgeList());
					  double wNew = next.getWeight();
					  
					  if(wNew > w) break;
					  
					  if(!eppstein.hasNext()) System.out.println("This happens very rarely.");
					 
				  }
	
			      if (count >= 20) System.out.println(String.format("{%d, %d}  w: %f  count: %d", u, v, w, count));

			  }
			  
			  
		  }
		  
		  
		  
		  
			/*
			 * Graph<Integer, DefaultWeightedEdge> hmm = read("src/nyc.gr.gz",
			 * FileToGraph::readDIMACS);
			 * 
			 * int source = 9; int target = 290;
			 * 
			 * Iterator<GraphPath<Integer, DefaultWeightedEdge>> eppstein = new
			 * EppsteinShortestPathIterator<>(hmm, source, target);
			 * 
			 * int pathcount = 0;
			 * 
			 * while(pathcount <=10) { pathcount++;
			 * System.out.println(eppstein.next().getLength()); }
			 * 
			 * System.out.println(pathcount);
			 */

	}

}
