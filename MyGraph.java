import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Hausaufgabe 6 Algorithmen & Datenstrukturen
 * 
 * This class inherits from the given Graph class and implments different methods for traversals or calculating attributes.
 * 
 *  @author Josha Bartsch, Christian Thelen, Laura Mey
 */
public class MyGraph extends Graph {

	/**
	 * inherited constructor
	 * 
	 * @param v int number of vertexes
	 */
	public MyGraph(int v) {
		super(v);
	}
	
	/**
	 * inherited constructor
	 * 
	 * @param v int number of vertexes
	 * @param e int number of random edges
	 */
	public MyGraph(int v, int e) {
		super(v, e);
	}

	/**
	 * inherited constructor
	 * 
	 * @param list integer list of edges
	 */
	public MyGraph(int[] list) {
		super(list);
	}

	/**
	 * provides edge list for the graph
	 * 
	 * @return edgelist
	 */
	public ArrayList<Integer> getEdgeList() {
		ArrayList<Integer> edgelist = new ArrayList<>();
		edgelist.add(this.getVertexCount());
		edgelist.add(this.getEdgeCount());
		for (int i = 0; i <= this.getVertexCount(); i++) {
			ArrayList<Integer> adj = this.getAdjacent(i);
			for (int j : adj) {
				edgelist.add(i);
				edgelist.add(j);
			}
		}
		return edgelist;
	}

	/**
	 * provides list of all vertexes of the graph
	 * 
	 * @return list of vertexes
	 */
	public ArrayList<Integer> getVertexList() {
		ArrayList<Integer> vertexlist = new ArrayList<>();
		vertexlist.add(this.getVertexCount());
		vertexlist.add(this.getEdgeCount());
		for (int i = 0; i <= this.getVertexCount(); i++) {
			vertexlist.add(this.ausgangsgrad(i));
			ArrayList<Integer> adj = this.getAdjacent(i);
			for (int j : adj) {
				vertexlist.add(j);
			}
		}
		return vertexlist;
	}

	/**
	 * provides adjacency matrix of the graph (without dummy rows/columns)
	 * 
	 * @return adjacency matrix of the graph
	 */
	public int[][] getAdjacencyMatrix() {
		int[][] adj_matrix = new int[this.getVertexCount()][this.getVertexCount()];
		for (int i = 1; i <= adj_matrix.length; i++) {
			for (int j = 1; j <= adj_matrix[0].length; j++) {
				if (this.getAdjacent(i).contains(j)) {
					adj_matrix[i-1][j-1] = 1;
				}
			}
		}
		return adj_matrix;
	}

	/**
	 * broad first search traversal of the graph
	 * 
	 * @param start vertex to start the search at
	 * @return list of vertexes in their order of traversal
	 */
	public ArrayList<Integer> bfs(int start) {
		boolean[] visited = new boolean[this.getVertexCount()+1];
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		ArrayList<Integer> bfs_list = new ArrayList<>();
		queue.addLast(start);
		visited[start] = true;
		while (queue.size() != 0) {
			start = queue.poll();
			bfs_list.add(start);
			ArrayList<Integer> adj = this.getAdjacent(start);
			for (int j : adj) {
				if (visited[j] == false) {
					visited[j] = true;
					queue.addLast(j);
				}
			}

		}
		return bfs_list;
	}

	/**
	 * helper function for deep first search
	 * 
	 * @param current vertex to start the search at
	 * @param visited boolean list of which vertices have already been visited (=true)
	 * @param dfs_list list of which vertices have so far been searched, in order of traversal
	 */
	private void dfs_recursive(int current, boolean[] visited, ArrayList<Integer> dfs_list) {
		visited[current] = true;
		ArrayList<Integer> adj = this.getAdjacent(current);
		dfs_list.add(current);
		for (int n : adj) {
			if (visited[n] == false) {
				dfs_recursive(n, visited, dfs_list);
			}
		}
	}

	/**
	 * deep first search traversal of the graph
	 * 
	 * @param start vertex to start the search at
	 * @return list of vertexes in their order of traversal
	 */
	public ArrayList<Integer> dfs(int start){
		boolean[] visited = new boolean[this.getVertexCount()+1];
		ArrayList<Integer> dfs_list = new ArrayList<>();
		dfs_recursive(start, visited, dfs_list);
		return dfs_list;
	}
		
	/**
	 * get a list of unreachable vertices
	 * 
	 * @param start vertex from where to start searching for unreachable vertices
	 * @return list of unreachable vertices
	 */
	public ArrayList<Integer> getUnreachableVertices(int start){
		ArrayList<Integer> bfs_list = this.bfs(start);
		ArrayList<Integer> unreachables = new ArrayList<>();
		for (int i = 1; i <= this.getVertexCount(); i++) {
			if (!bfs_list.contains(i)) {
				unreachables.add(i);
			}
		}
		return unreachables;
	}

}

