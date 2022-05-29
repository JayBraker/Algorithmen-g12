import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Implementation of Dijkstra's algorithm.
 * 
 * @author Laura Mey, Christian Thelen, Josha Bartsch
 */
public class Dijkstra {

	public final static int MAX_DIGITS  = 2;
	public final static int INDENT  = MAX_DIGITS + 1;

	/**
	 * Datastructure representing a Node in the Graph
	 * and its total distance to the initial node ("1").
	 */
	private static class Node {
		int pos;
		int dist;

		public Node(int pos, int dist) {
			this.pos = pos;
			this.dist = dist;
		}

		public int hashCode() {
			return pos;
		}

		public int getDist() {
			return dist;
		}
	}

	/**
	 * Datastructure representing an edge to some vertex
	 * and the weight assigned.
	 */
	private static class Edge {
		int dest;
		int dist;

		public Edge(int dest, int dist) {
			this.dest = dest;
			this.dist = dist;
		}

		public int hashCode() {
			return dest;
		}
	}

	/**
	 * Helper to create a adjacency list, instead of neighbouring vertices
	 * store edges (neighbour and weight).
	 * 
	 * @param vertices Array Following the pattern (# of vertices),(From vertex, To vertex, weight)+
	 * @return adjacency lists
	 */
	@SuppressWarnings({"unchecked"})
	private static ArrayList<Edge>[] getAdj(int[] vertices) {
		ArrayList<Edge>[] adj = new ArrayList[vertices[0] + 1];;
		int edgeC = vertices.length / 3;

		for (int i = 0; i < adj.length; i++) {
			adj[i] = new ArrayList<Edge>();
		}

		for (int i = 0; i < edgeC; i++) { // list[1] Kanten definieren
			adj[vertices[1 + i * 3]].add(new Edge(vertices[2 + i * 3], vertices[3 + i * 3]));
		}

		return adj;
	}

	/**
	 * Helper to print Array 
	 * @param arr
	 * @return
	 */
	private static String padArr(int[] arr) {
		StringBuilder sb = new StringBuilder();
		for (int i = 2; i < arr.length; i++) {
			if (arr[i] == Integer.MAX_VALUE || arr[i] == -1)
				sb.append(String.format("%"+INDENT+"s", "--"));
			else
				sb.append(String.format("%"+INDENT+"d", arr[i]));
		}
		return sb.toString();
	}

	public static void printDijkstra(int[] kanten) {
		if (kanten.length < 1 || kanten.length % 3 != 1) // Check: Is Kantenliste valid
			throw new IllegalArgumentException("Ungültige Kantenliste");

		int vertexC = kanten[0];

		PriorityQueue<Node> vertices = new PriorityQueue<>(Comparator.comparingInt(Node::getDist));
		HashSet<Integer> fixPositions = new HashSet<>();

		ArrayList<Edge>[] adj = getAdj(kanten);

		int[] dist = new int[vertexC + 1];
		int[] prev = new int[vertexC + 1];

		dist[1] = 0;
		int[] header = new int[vertexC + 1];
		for (int i = 1; i < vertexC + 1; i++) {
			if (i != 1) {
				dist[i] = Integer.MAX_VALUE;
				prev[i] = -1;
			}
			vertices.add(new Node(i, dist[i]));
			header[i] = i;
		}

		// Printing the table head
		System.out.println(String.format("vi|%s|%s|", padArr(header), padArr(header)));
		StringBuilder line = new StringBuilder();
		for (int i = 0; i < (2*(vertexC-1)*INDENT + 3+ INDENT);i++)
			line.append("-");
		System.out.println(line);

		while (!vertices.isEmpty()) {
			Node n = vertices.remove();
			if (fixPositions.contains(n.pos))
				continue;
			fixPositions.add(n.pos);
			for (Edge neigh : adj[n.pos]) {
				if (dist[neigh.dest] > dist[n.pos] + neigh.dist) {
					dist[neigh.dest] = dist[n.pos] + neigh.dist;
					prev[neigh.dest] = n.pos;
					vertices.add(new Node(neigh.dest, dist[neigh.dest]));
				}
			}
			
			String tDist = padArr(dist);
			String tPrev = padArr(prev);
			System.out.println(String.format("%2d|%s|%s|", n.pos, tDist, tPrev));
		}

	}

	public static void main(String[] args) {
		printDijkstra(new int[] {4, 1, 2, 2, 1, 4, 5, 2, 4, 1, 2, 3, 4, 3, 1, 1, 4, 3, 1});

		printDijkstra(new int[] {10,1,2,30,1,3,10,2,5,15,2,8,55,3,4,5,3,9,35,4,2,10,4,5,45,4,6,10,5,3,20,5,7,15,5,9,25,6,7,5,7,10,20,8,10,15,9,8,10,9,10,30});
	}
}
