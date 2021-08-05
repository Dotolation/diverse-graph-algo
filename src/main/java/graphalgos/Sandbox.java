package graphalgos;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.DepthFirstIterator;

/**
 * Hello world!
 *
 */
public class Sandbox 
{
    public static void main( String[] args )
    {
    	Graph<String, DefaultEdge> g = new DefaultDirectedGraph<>(DefaultEdge.class);

        String google = "http://www.google.com";
        String wikipedia = "http://www.wikipedia.org";
        String jgrapht = "http://www.jgrapht.org";

        // add the vertices
        g.addVertex(google);
        g.addVertex(wikipedia);
        g.addVertex(jgrapht);

        // add edges to create linking structure
        g.addEdge(jgrapht, wikipedia);
        g.addEdge(google, jgrapht);
        g.addEdge(google, wikipedia);
        g.addEdge(wikipedia, google);
        
        String start = g.vertexSet().stream().filter(str -> str.equals("http://www.jgrapht.org")).findAny().get();
        System.out.println(start);
        
        Iterator<String> iterator = new DepthFirstIterator<>(g, start);
        while (iterator.hasNext()) {
            String str = iterator.next();
            System.out.println(str);
        }
        
        Set<Integer> hSet = new HashSet<>();
        
        for(int i=0; i < 90000000; i++) {
        	hSet.add(i);
        }
        
        System.out.println(hSet.add(33));
    
        
    }
}
