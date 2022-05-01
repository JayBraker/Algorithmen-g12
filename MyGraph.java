import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;

/**
 * Jedes Objekt dieser Klasse dient der Speicherung eines Graphen
 * mit Hilfe von Adjazenzlisten wie in der Vorlesung definiert.<br>
 * Loesung zu algo-h06 im SS 2022.
 */
public class MyGraph extends Graph {
  //TODO: Die noetigen Konstruktoren implementieren
  //TODO: Implementation der im Aufgabentext gewuenschten Operationen.

	public MyGraph(int v) {
		super(v);
		//TODO Auto-generated constructor stub
	}

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

	public ArrayList<Integer> getVertexList() {
		ArrayList<Integer> vertexList = new ArrayList<>();
		vertexList.add(this.getVertexCount());
		vertexList.add(this.getEdgeCount());
		
		for (int i = 1; i <= this.getEdgeCount(); i++) {
			vertexList.add(this.ausgangsgrad(i));
			for (Iterator<Integer> j = this.getIt(i); j.hasNext();)
				vertexList.add(j.next());
		}
		return vertexList;
	}

	public int[][] getAdjacencyMatrix() {
		int [][] adjMatr = new int[this.getVertexCount()][this.getVertexCount()];

		for (int i = 1; i <= this.getEdgeCount(); i++) {
			for (Iterator<Integer> j = this.getIt(i); j.hasNext();)
				adjMatr[i][j.next()-1] = 1;
		}
		return adjMatr;
	}

	public ArrayList<Integer> bfs(int start) {
		ArrayList<Integer> vertexList = new ArrayList<>();
		boolean[] visited = new boolean[this.getVertexCount()+1];
		ArrayDeque<Integer> myQueue = new ArrayDeque<Integer>();
	
	
		myQueue.add(start);
		visited[start] = true;
		while(!myQueue.isEmpty()) {
			int curr = myQueue.poll();
			visited[curr] = true;
			for (Iterator<Integer> j = this.getIt(curr); j.hasNext();) {
				int subCurr = j.next();
				if (!visited[subCurr])
					myQueue.add(subCurr);
			}
			vertexList.add(curr);
		}
		return vertexList;
	}

	public void dfs(int start, ArrayList<Integer> traversed, boolean[] visited) {
		traversed.add(start);
		for (Iterator<Integer> j = this.getIt(start); j.hasNext();) {
			int curr = j.next();
			if (!visited[curr])
				dfs(curr, traversed);
		}
	}

	public ArrayList<Integer> dfs(int start) {
		ArrayList<Integer> vertexList = new ArrayList<>();
		boolean[] visited = new boolean[this.getVertexCount()];

		dfs(vertexList, start);
		
		return vertexList;
	}

	public ArrayList<Integer> getUnreachableVertices(int start) {
		ArrayList<Integer> vertexList = new ArrayList<>();
		boolean[] visited = new boolean[this.getVertexCount()];
		dfs(new ArrayList<Integer>(), visited);
		for(int i = 0; i < this.getVertexCount(); i++) {
			if(!visited[i])
				vertexList.add(i+1);
		}

		return vertexList;
	}
}

