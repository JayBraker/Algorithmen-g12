package ha10;

import java.util.ArrayList;

public class Djikstra {

	/**
	 * helper method to find the vertex with minimum distance value from a set of
	 * vertexes
	 * 
	 * @param distances
	 * @param inDijkstra
	 * @param vertexcount
	 * @return
	 */
	public static int minDistance(int distances[], boolean usedSet[], int vertexcount) {
		int min = Integer.MAX_VALUE;
		int min_index = -1;

		for (int vertex = 0; vertex < vertexcount; vertex++) {
			if (usedSet[vertex] == false && distances[vertex] <= min) {
				min = distances[vertex];
				min_index = vertex;
			}
		}

		return min_index;
	}

	public static boolean isAllTrue(boolean[] array) {
		for (boolean b : array) {
			if (!b) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param kanten
	 */
	public static void printDijkstra(int[] kanten) {
		int vertexcount = kanten[0];

		int[][] distances = new int[vertexcount][vertexcount - 1];
		for (int i = 0; i < distances.length; i++) {
			for (int j = 0; j < distances[0].length; j++) {
				distances[i][j] = Integer.MAX_VALUE;
			}
		}

		int[][] predecessors = new int[vertexcount][vertexcount - 1];

		boolean[] usedSet = new boolean[vertexcount];
		
		ArrayList<Integer> vis = new ArrayList<>();

		int vi = 1;

		usedSet[vi - 1] = true;
		vis.add(vi);

		for (int i = 1; i < kanten.length; i += 3) {
			if (kanten[i] == vi) {
				distances[vi][kanten[i + 1]] = kanten[i + 2];
				predecessors[vi][kanten[i + 1]] = vi;
			}
		}

		int min_index = -1;

		while (!isAllTrue(usedSet)) {
			min_index = minDistance(distances[vi], usedSet, vertexcount);

			usedSet[min_index] = true;
			vis.add(min_index+1);

			for (int vj = 1; vj < vertexcount && usedSet[vertexcount] == false; vj++) {
				if (distances[vi][vi] + distances[vi][vj] < distances[vi][vj]) {
					distances[vi][vj] = distances[vi][vi] + distances[vi][vj];
					predecessors[vi][vj] = vi + 1;
				}
			}
		

		}

		for (int j = 0; j < (vertexcount + 2); j++) {
			if (j == 0) {
				System.out.print("vi|  ");
				int counter = 0;
				while (counter < 2) {
					for (int i = 2; i < vertexcount; i++) {
						if (i == vertexcount -1) {
							System.out.print(""+i+"|  ");
						} else { 
						System.out.print(""+i+"  ");
						}
					}
					counter++;
				}
			}
			else if (j == 1) {
				for (int i = 0; i < 2*(vertexcount-1)+1; i++) {
					System.out.print("---");
				}
			}
			else {
				System.out.print(""+vis.get(j)+"|");
				for (int vj = 1; vj < vertexcount; vj++) {
					if (vj == vertexcount -1) {
						System.out.print("  " + distances[j][vj]+"|");
					} else {
						System.out.print("  " + distances[j][vj]);
					}
				}
			}

		}

	}

	/**
	 * Testmethod (copied from the assignment)
	 */
	public static void main(String[] args) {
		printDijkstra(new int[] { 4, 1, 2, 2, 1, 4, 5, 2, 4, 1, 2, 3, 4, 3, 1, 1, 4, 3, 1 });

		printDijkstra(new int[] { 10, 1, 2, 30, 1, 3, 10, 2, 5, 15, 2, 8, 55, 3, 4, 5, 3, 9, 35, 4, 2, 10, 4, 5, 45, 4,
				6, 10, 5, 3, 20, 5, 7, 15, 5, 9, 25, 6, 7, 5, 7, 10, 20, 8, 10, 15, 9, 8, 10, 9, 10, 30 });

	}

}

