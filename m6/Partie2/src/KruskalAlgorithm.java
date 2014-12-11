import java.util.ArrayList;

import net.datastructures.AdjacencyMapGraph;
import net.datastructures.Edge;
import net.datastructures.Entry;
import net.datastructures.Graph;
import net.datastructures.HeapPriorityQueue;
import net.datastructures.LinkedPositionalList;
import net.datastructures.PositionalList;
import net.datastructures.PriorityQueue;
import net.datastructures.Vertex;

public class KruskalAlgorithm {

	/**
	 * @author Grynczel Wojciech
	 */

	private Graph<Integer, Integer> graph;
	private ArrayList<String> ar;
	
	/*
	 * Constructor
	 * 
	 * @pram ar : A list of string. Syntax "<Vertex A> <Vertex B> <Weight>"
	 */

	public KruskalAlgorithm(ArrayList<String> ar) {
		if(ar.size() == 0){
			System.out.println("La liste ne peut pas etre vide");
			return;
		}
		this.ar = ar;
		//Create an empty graph. (false = undirected graph)
		this.graph = new AdjacencyMapGraph<Integer, Integer>(false);
	}


	/**
	 * Creates a graph
	 * 
	 * @return Graph<Integer, Integer>
	 */
	public Graph<Integer, Integer> buildGraph() {
		
		for (String item : ar) {
			String[] fields = item.split("\t", -1);

			for (int i = 0; i < fields.length; ++i) {
				if ("".equals(fields[i]))
					fields[i] = null;
			}

			Vertex<Integer> v = graph.insertVertex(Integer.parseInt(fields[0]));
			Vertex<Integer> w = graph.insertVertex(Integer.parseInt(fields[1]));
			int weight = Integer.parseInt(fields[2]);
			// System.out.println(v.getElement() + " -- " + w.getElement() +
			// "[label=" + weight + ",weight="+ weight + "];");
			graph.insertEdge(v, w, weight);
		}
		return graph;
	}

	public ArrayList<String> kruskaAlgorithm() {
		long startTime = System.currentTimeMillis();
		buildGraph();
		int totalWeight = 0, size = graph.numVertices();

		ArrayList<String> result = new ArrayList<String>();
		PriorityQueue<Integer, Edge<Integer>> pq = new HeapPriorityQueue<>();
		UF unionFind = new UF(size); //Source http://algs4.cs.princeton.edu/15uf/UF.java.html

		for (Edge<Integer> e : graph.edges())
			pq.insert(e.getElement(), e);

		while (result.size() != size - 1 && !pq.isEmpty()) {

			Entry<Integer, Edge<Integer>> entry = pq.removeMin();
			Edge<Integer> edge = entry.getValue();
			Vertex<Integer>[] endpoints = graph.endVertices(edge);

			int v = endpoints[0].getElement();
			int w = endpoints[1].getElement();

			if (!unionFind.connected(v, w)) {
				// System.out.println(v + " -- " + w + "[label=" +
				// entry.getKey() + ",weight=" + entry.getKey() + "];");
				totalWeight += entry.getKey();
				result.add(v + "\t" + w + "\t" +entry.getKey());
				unionFind.union(v, w);
			}
		}
		System.out.println("Kruska - Total weight : " + totalWeight);
		long endTime = System.currentTimeMillis();
		System.out.println("Kruska - Total execution time: "
				+ (endTime - startTime) + "ms");
		return result;
	}
}
