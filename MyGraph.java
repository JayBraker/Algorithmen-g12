import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;

/**
 * Jedes Objekt dieser Klasse dient der Speicherung eines Graphen mit Hilfe von Adjazenzlisten wie
 * in der Vorlesung definiert.<br>
 * Loesung zu algo-h06 im SS 2022.
 * 
 * @author Josha Bartsch, Christian Thelen, Laura Mey
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
	public MyGraph(int i, int j) {
		super(i, j);
	}
	
	/**
	 * inherited constructor
	 * 
	 * @param list integer list of edges
	 */
	public MyGraph(int[] vlist) {
		super(vlist);
	}

	/**
	 * provides edge list for the graph
	 * 
	 * @return edgelist
	 */
    public ArrayList<Integer> getEdgeList() {
		ArrayList<Integer> edgeList = new ArrayList<>();
		edgeList.add(this.getVertexCount());
		edgeList.add(this.getEdgeCount());
		for (int i = 1; i <= this.getVertexCount(); i++) {
			for (Iterator<Integer> j = this.getIt(i); j.hasNext();) {
				edgeList.add(i);
				edgeList.add(j.next());
			}
		}
		return edgeList;
	}


	/**
	 * provides list of all vertices of the graph
	 * 
	 * @return list of vertices
	 */
	public ArrayList<Integer> getVertexList() {
		ArrayList<Integer> vertexList = new ArrayList<>();
		vertexList.add(this.getVertexCount());
		vertexList.add(this.getEdgeCount());

		for (int i = 1; i <= this.getVertexCount(); i++) {
			vertexList.add(this.ausgangsgrad(i));
			for (Iterator<Integer> j = this.getIt(i); j.hasNext();)
				vertexList.add(j.next());
		}
		return vertexList;
	}

	/**
	 * provides adjacency matrix of the graph (without dummy rows/columns)
	 * 
	 * @return adjacency matrix of the graph
	 */
	public int[][] getAdjacencyMatrix() {
		int[][] adjMatr = new int[this.getVertexCount()][this.getVertexCount()];

		for (int i = 1; i <= adjMatr.length; i++) {
			for (Iterator<Integer> j = this.getIt(i); j.hasNext();)
				adjMatr[i-1][j.next() - 1] = 1;
		}
		return adjMatr;
	}

	/**
	 * broad first search traversal of the graph
	 * 
	 * @param start vertex to start the search at
	 * @return list of vertexes in their order of traversal
	 */
	public ArrayList<Integer> bfs(int start) {
		ArrayList<Integer> vertexList = new ArrayList<>();
		boolean[] visited = new boolean[this.getVertexCount() + 1];
		ArrayDeque<Integer> myQueue = new ArrayDeque<Integer>();


		myQueue.add(start);
		while (!myQueue.isEmpty()) {
			int curr = myQueue.poll();
			if (!visited[curr])	{
				vertexList.add(curr);
				visited[curr] = true;
			}
			for (Iterator<Integer> j = this.getIt(curr); j.hasNext();) {
				int subCurr = j.next();
				if (!visited[subCurr]) {
					myQueue.add(subCurr);
				}
			}
		}
		return vertexList;
	}

	/**
	 * helper function for deep first search
	 * 
	 * @param current vertex to start the search at
	 * @param visited boolean list of which vertices have already been visited (=true)
	 * @param dfs_list list of which vertices have so far been searched, in order of traversal
	 */
	public void dfs(int start, ArrayList<Integer> traversed, boolean[] visited) {
		traversed.add(start);
		visited[start] = true;

		ArrayList<Integer> adj = this.getAdjacent(start);
		Collections.sort(adj);

		//for (Iterator<Integer> j = this.getIt(start); j.hasNext();) {
		//	int curr = j.next();
		for(Integer curr: adj) {
			if (!visited[curr])
				dfs(curr, traversed, visited);
		}
	}

	
	/**
	 * deep first search traversal of the graph
	 * 
	 * @param start vertex to start the search at
	 * @return list of vertexes in their order of traversal
	 */
	public ArrayList<Integer> dfs(int start) {
		ArrayList<Integer> vertexList = new ArrayList<>();
		boolean[] visited = new boolean[this.getVertexCount()+1];

		dfs(start, vertexList, visited);

		return vertexList;
	}

	/**
	 * Get the list of unreachable vertices for any given start vertex
	 * 
	 * @param start vertex from where to start searching for unreachable vertices
	 * @return list of unreachable vertices
	 */
	public ArrayList<Integer> getUnreachableVertices(int start) {
		ArrayList<Integer> vertexList = new ArrayList<>();
		boolean[] visited = new boolean[this.getVertexCount()+1];
		dfs(start, new ArrayList<Integer>(), visited);
		for (int i = 1; i <= this.getVertexCount(); i++) {
			if (!visited[i])
				vertexList.add(i);
		}

		return vertexList;
	}
}

