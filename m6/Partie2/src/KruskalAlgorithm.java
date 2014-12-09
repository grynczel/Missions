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

	private static Graph<Integer, Integer> graph = new AdjacencyMapGraph<Integer, Integer>(
			false);

	private static HashMap<Integer, String> m;

	public static void main(String args[]) {

		m = new HashMap<Integer, String>();

		m.put(new Integer(1), "A");
		m.put(new Integer(2), "B");
		m.put(new Integer(3), "C");
		m.put(new Integer(4), "D");
		m.put(new Integer(5), "E");
		m.put(new Integer(6), "F");
		m.put(new Integer(7), "G");

		List<String> list = new ArrayList<String>();

		list.add("1	2	7");
		list.add("1	4	5");
		list.add("2	4	9");
		list.add("2	3	8");
		list.add("2	5	7");
		list.add("3	5	5");
		list.add("4	5	15");
		list.add("4	6	6");
		list.add("6	7	11");
		list.add("6	5	8");
		list.add("7	5	9");

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

		//kruska(graph);
		
		
		UF s = new UF(10);
		s.union(0, 1);
		
		
		System.out.println(s.connected(0, 1));
		// System.out.println(graph);
	}

	public static PositionalList<Edge<Integer>> kruska(Graph G) {
		// tree is where we will store result as it is computed
		PositionalList<Edge<Integer>> tree = new LinkedPositionalList<>();

		// union-find forest of components of the graph
		// map each vertex to the forest position
		Map<Vertex<Integer>, Position<Vertex<Integer>>> positions = new ProbeHashMap<>();

		Partition<Vertex<Integer>> forest = new Partition<Vertex<Integer>>();
		for (Vertex<Integer> v : graph.vertices())
			positions.put(v, forest.makeCluster(v));

		PriorityQueue<Integer, Edge<Integer>> pq = new HeapPriorityQueue<>();

		for (Edge<Integer> e : graph.edges())
			pq.insert(e.getElement(), e);

		int size = graph.numVertices();
		// while tree not spanning and unprocessed edges remain...
		while (tree.size() != size - 1 && !pq.isEmpty()) {
			Entry<Integer, Edge<Integer>> entry = pq.removeMin();
			Edge<Integer> edge = entry.getValue();
			Vertex<Integer>[] endpoints = graph.endVertices(edge);
			
			Position<Vertex<Integer>> a = forest.find(positions
					.get(endpoints[0]));

			Position<Vertex<Integer>> b = forest.find(positions
					.get(endpoints[1]));

			if (a != b) {
				System.out.println("Add edge : "
						+ m.get(new Integer(endpoints[0].getElement()))
						+ " - > "
						+ m.get(new Integer(endpoints[1].getElement())) + " ("
						+ entry.getKey() + ")");
				tree.addLast(edge);
				forest.union(a, b);
			} else {
				System.out.println("Not add edge : "
						+ m.get(new Integer(endpoints[0].getElement()))
						+ " - > "
						+ m.get(new Integer(endpoints[1].getElement())) + " ("
						+ entry.getKey() + ")");
			}
		}

		return tree;

		// PositionalList<Edge<Integer>> a =
		// net.datastructures.GraphAlgorithms.MST(graph);
		// Iterator<Edge<Integer>> it = a.iterator();
		//
		// while (it.hasNext()) {
		// Edge<java.lang.Integer> edge = (Edge<java.lang.Integer>) it.next();
		// System.out.println(edge.getElement());
		// }
	}
}
