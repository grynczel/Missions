import java.util.ArrayList;
import java.util.Timer;

import net.datastructures.AdjacencyMapGraph;
import net.datastructures.Edge;
import net.datastructures.Entry;
import net.datastructures.Graph;
import net.datastructures.HeapPriorityQueue;
import net.datastructures.LinkedPositionalList;
import net.datastructures.Map;
import net.datastructures.PositionalList;
import net.datastructures.PriorityQueue;
import net.datastructures.ProbeHashMap;
import net.datastructures.Vertex;

public class KruskalAlgorithm {

	/**
	 * @author Grynczel Wojciech
	 */

	private Graph<Integer, Integer> graph;
	private ArrayList<String> ar;

	public KruskalAlgorithm(ArrayList<String> ar) {
		this.ar = ar;
		graph = new AdjacencyMapGraph<Integer, Integer>(false);
	}

	public Graph<Integer, Integer> buildGraph() {
		for (String item : ar) {
			String[] fields = item.split("\t", -1);

			for (int i = 0; i < fields.length; ++i) {
				if ("".equals(fields[i]))
					fields[i] = null;
			}

			Vertex v = graph.insertVertex(Integer.parseInt(fields[0]));
			Vertex w = graph.insertVertex(Integer.parseInt(fields[1]));
			int weight = Integer.parseInt(fields[2]);
			// System.out.println(v.getElement() + " -- " + w.getElement() + "[label=" + weight + ",weight="+ weight + "];");
			graph.insertEdge(v, w, weight);
		}
		return graph;
	}

	public PositionalList<Edge<Integer>> kruskaAlgorithm() {
		long startTime = System.currentTimeMillis();
		buildGraph();
		int totalWeight = 0, size = graph.numVertices();
		
		PositionalList<Edge<Integer>> tree 			= new LinkedPositionalList<>();
		PriorityQueue<Integer, Edge<Integer>> pq 	= new HeapPriorityQueue<>();
		UF unionFind 								= new UF(size);
		
		for (Edge<Integer> e : graph.edges())
			pq.insert(e.getElement(), e);

		while (tree.size() != size - 1 && !pq.isEmpty()) {
			
			Entry<Integer, Edge<Integer>> entry = pq.removeMin();
			Edge<Integer> edge = entry.getValue();
			Vertex<Integer>[] endpoints = graph.endVertices(edge);
			
			int v = endpoints[0].getElement();
			int w = endpoints[1].getElement();

			if (!unionFind.connected(v, w)) {
				//System.out.println(v + " -- " + w + "[label=" + entry.getKey() + ",weight=" + entry.getKey() + "];");
				totalWeight += entry.getKey();
				tree.addLast(edge);
				unionFind.union(v, w);
			}
		}
		System.out.println("Total weight : " + totalWeight);
		long endTime = System.currentTimeMillis();
	    System.out.println("Total execution time: " + (endTime-startTime) + "ms"); 
		return tree;
	}
}
