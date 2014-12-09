import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import net.datastructures.AdjacencyMapGraph;
import net.datastructures.Edge;
import net.datastructures.Graph;
import net.datastructures.Vertex;

public class KruskalAlgorithm {

	private static Graph<Integer, Integer> graph = new AdjacencyMapGraph<Integer, Integer>(
			false);
	public static void main(String args[]) {

		List<String> list = new ArrayList<String>();

		list.add("0	6	8");
		list.add("1	3	743");
		list.add("2	3	576");
		list.add("3	5	169");
		list.add("4	2	449");
		list.add("5	1	221");

		for (String item : list) {
			String[] fields = item.split("\t", -1);

			for (int i = 0; i < fields.length; ++i) {
				if ("".equals(fields[i]))
					fields[i] = null;
			}

			Vertex v = graph.insertVertex(Integer.parseInt(fields[0]));
			Vertex w = graph.insertVertex(Integer.parseInt(fields[1]));
			int weight = Integer.parseInt(fields[2]);

			graph.insertEdge(v, w, weight);
		}
		
		kruska(graph);
		// System.out.println(graph);
	}

	public static int kruska(Graph G) {

		
		PriorityQueue<Edge<Integer>> pq = new PriorityQueue<Edge<Integer>>();

        for (Edge<Integer> e : graph.edges()) {
            pq.add(e);
        }
        
		

		return 0;
	}

}
