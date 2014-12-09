import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.datastructures.AdjacencyMapGraph;
import net.datastructures.Edge;
import net.datastructures.Entry;
import net.datastructures.Graph;
import net.datastructures.HeapPriorityQueue;
import net.datastructures.LinkedPositionalList;
import net.datastructures.Map;
import net.datastructures.Partition;
import net.datastructures.Position;
import net.datastructures.PositionalList;
import net.datastructures.PriorityQueue;
import net.datastructures.ProbeHashMap;
import net.datastructures.Vertex;

public class KruskalAlgorithm {

	private Graph<Integer, Integer> graph;
	private ArrayList<String> ar;

	public KruskalAlgorithm(ArrayList<String> ar) {
		this.ar = ar;
		graph = new AdjacencyMapGraph<Integer, Integer>(false);
	}

	// private static HashMap<Integer, String> m;
	//
	// public static void main(String args[]) {
	//
	// m = new HashMap<Integer, String>();
	//
	// m.put(new Integer(1), "A");
	// m.put(new Integer(2), "B");
	// m.put(new Integer(3), "C");
	// m.put(new Integer(4), "D");
	// m.put(new Integer(5), "E");
	// m.put(new Integer(6), "F");
	// m.put(new Integer(7), "G");
	//
	// List<String> list = new ArrayList<String>();
	//
	// list.add("1	2	7");
	// list.add("1	4	5");
	// list.add("2	4	9");
	// list.add("2	3	8");
	// list.add("2	5	7");
	// list.add("3	5	5");
	// list.add("4	5	15");
	// list.add("4	6	6");
	// list.add("6	7	11");
	// list.add("6	5	8");
	// list.add("7	5	9");
	//
	// for (String item : list) {
	// String[] fields = item.split("\t", -1);
	//
	// for (int i = 0; i < fields.length; ++i) {
	// if ("".equals(fields[i]))
	// fields[i] = null;
	// }
	//
	// Vertex v = graph.insertVertex(Integer.parseInt(fields[0]));
	// Vertex w = graph.insertVertex(Integer.parseInt(fields[1]));
	// int weight = Integer.parseInt(fields[2]);
	//
	// graph.insertEdge(v, w, weight);
	// }
	//
	// kruskaAlgorithm(graph);
	// }

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
			//System.out.println(v.getElement() + " -- " + w.getElement() + "[label=" + weight + ",weight="+ weight + "];");
			graph.insertEdge(v, w, weight);
		}
		return graph;
	}

	public PositionalList<Edge<Integer>> kruskaAlgorithm() {
		buildGraph();		
		PositionalList<Edge<Integer>> tree = new LinkedPositionalList<>();

		Map<Vertex<Integer>, Integer> positions = new ProbeHashMap<>();

		for (Vertex<Integer> v : graph.vertices())
			positions.put(v, v.getElement());

		PriorityQueue<Integer, Edge<Integer>> pq = new HeapPriorityQueue<>();

		for (Edge<Integer> e : graph.edges())
			pq.insert(e.getElement(), e);

		int size = graph.numVertices();

		UF unionFind = new UF(size);

		while (tree.size() != size - 1 && !pq.isEmpty()) {
			Entry<Integer, Edge<Integer>> entry = pq.removeMin();
			Edge<Integer> edge = entry.getValue();
			Vertex<Integer>[] endpoints = graph.endVertices(edge);

			int v = positions.get(endpoints[0]);
			int w = positions.get(endpoints[1]);

			if (!unionFind.connected(v, w)) {
			System.out.println(v + " -- " + w + "[label=" + entry.getKey() + ",weight="+ entry.getKey() + "];");
				
				
//				System.out.println("Add edge : " + v + " - > " + w + " ("
//						+ entry.getKey() + ")");
				tree.addLast(edge);
				unionFind.union(v, w);
			} else {
//				System.out.println("Not add edge : " + v + " - > " + w
//						+ entry.getKey() + ")");
			}
		}

		
		
		return tree;
	}
}
