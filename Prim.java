import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Implementation of Prims algorithm
 * 
 * @author Christian Thelen, Laura Mey, Josha Bartsch
 */
public class Prim {
	/**
	 * Data wrapper allowing to access origin and destination to edges, keyable by their weight.
	 */
	private static class Edge implements Comparable<Edge> {
		protected int weight;
		protected int vertexA;
		protected int vertexB;

		/**
		 * 
		 * @param weight
		 * @param vertexA
		 * @param vertexB
		 */
		public Edge(int weight, int vertexA, int vertexB) {
			this.weight = weight;
			this.vertexA = vertexA;
			this.vertexB = vertexB;
		}

		/**
		 * When comparing two edges, account for their weight.
		 * 
		 * @param e
		 * @return
		 */
		public int compareTo(Edge e) {
			return Integer.valueOf(this.weight).compareTo(e.weight);
		}
	}

	/**
	 * Helper: Default root vertex to "1" (index 0)
	 * 
	 * @param int[][] edges
	 */
	public static int getMST(int[][] edges) {
		return getMST(edges, 0);
	}

	/**
	 * Get a minimal span tree for any given adjacency matrix.
	 * 
	 * @param int[][] edges
	 * @param int index for the root vertex
	 */
	public static int getMST(int[][] edges, int start) {
		PriorityQueue<Edge> edgeQ = new PriorityQueue<>();
		HashSet<Integer> vertices = new HashSet<>();
		int index = start;
		int total = 0;

		System.out.println(String.format("waehle %d als Wurzel", index + 1));
		for (int i = 0; i < edges.length; i++)
			vertices.add(i); // Fill the set with an corresponding integer for every vertex present

		/*
		 * As the edges represent a non-directional graph, we assume every vertex is connected by at
		 * least 1 edge. Thus we loop until every vertex was transferred into the spantree.
		 */
		while (!vertices.isEmpty()) {
			for (int i = 0; i < edges[index].length; i++) {
				if (vertices.contains(i) && edges[index][i] > 0) {
					Edge e = new Edge(edges[index][i], index, i);
					edgeQ.add(e);
				}
			}
			Edge vertex = edgeQ.poll(); // Get the cheapest edge
			while (!vertices.contains(vertex.vertexB)) // If the destination is already present in the
																									// span tree
				vertex = edgeQ.poll(); // Skip to the next cheapest edge.
			total += vertex.weight;
			System.out.println(
					String.format("Kante hinzugefuegt von %d zu %d", vertex.vertexA + 1, vertex.vertexB + 1));
			index = vertex.vertexB;
			vertices.remove(vertex.vertexA); // Make sure origin AND destination vertices are remove from
																				// the set
			vertices.remove(vertex.vertexB);
		}
		return total;
	}

	public static void main(String[] args) {
		int[][] adjazenzmatrix = {{0, 3, 0, 2, 0, 0}, {3, 0, 2, 0, 3, 0}, {0, 2, 0, 1, 6, 0},
				{2, 0, 1, 0, 0, 0}, {0, 3, 6, 0, 0, 5}, {0, 0, 0, 0, 5, 0}};
		int[][] adjazenzmatrix2 =
				{{0, 3, 0, 2, 6, 0, 0, 0, 0}, {3, 0, 5, 4, 0, 5, 0, 0, 4}, {0, 5, 0, 3, 0, 0, 0, 0, 0},
						{2, 4, 3, 0, 0, 0, 0, 0, 0}, {6, 0, 0, 0, 0, 7, 0, 0, 0}, {0, 5, 0, 0, 7, 0, 8, 0, 0},
						{0, 0, 0, 0, 0, 8, 0, 2, 3}, {0, 0, 0, 0, 0, 0, 2, 0, 1}, {0, 4, 0, 0, 0, 0, 3, 1, 0}};

		System.out.println("resultierende kosten: " + Prim.getMST(adjazenzmatrix));
		System.out.println("resultierende kosten: " + Prim.getMST(restart
		));
	}
}
