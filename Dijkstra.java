package ha10;

import java.util.ArrayList;
import java.util.Arrays;

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
		for (int i = 0; i < array.length; i++) {
			if (!array[i] && i != 0) {
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

		int[][] distances = new int[vertexcount + 1][vertexcount + 1];
		for (int i = 0; i < distances.length; i++) {
			for (int j = 0; j < distances[0].length; j++) {
				distances[i][j] = Integer.MAX_VALUE;
			}
		}

		int[][] predecessors = new int[vertexcount + 1][vertexcount + 1];
		boolean[] usedSet = new boolean[vertexcount + 1];

		ArrayList<Integer> vis = new ArrayList<>();

		int vi = 1;

		int counter = 0;

		while (!isAllTrue(usedSet)) {
			System.out.println(Arrays.toString(usedSet));
			System.out.println("" + vi);

			usedSet[vi] = true;
			vis.add(vi);

			for (int i = 1; i < kanten.length - 3; i += 3) {
				if (kanten[i] == vi) {
					distances[vi][kanten[i + 1]] = kanten[i + 2];
					predecessors[vi][kanten[i + 1]] = vi;
				}
			}

			// get all distances from current vi
			for (int i = 1; i < kanten.length; i += 3) {
				if (kanten[i] == vi) {
					distances[vi][kanten[i + 1]] = kanten[i + 2];
					predecessors[vi][kanten[i + 1]] = vi;
				}
			}

			// check for unused min distance
			int min = Integer.MAX_VALUE;
			int min_index = -1;
			for (int i = 0; i < distances[vi].length; i++) {
				if (distances[vi][i] < min && usedSet[i] == false) {
					min = distances[vi][i];
					min_index = i;
				}
			}

			// neu
			int vj = min_index;

			// get all distances from new vi
			for (int i = 1; i < kanten.length; i += 3) {
				if (kanten[i] == vj) {
					distances[vj][kanten[i + 1]] = kanten[i + 2];
					predecessors[vj][kanten[i + 1]] = vj;
				}
			}

			for (int j = 1; j < vertexcount && usedSet[j] == false; j++) {
				if (distances[vi][vj] + distances[vj][j] < distances[vi][j]) {
					distances[vj][j] = distances[vi][j] + distances[vj][j];
					predecessors[vj][j] = vi + 1;
				}
			}

			vi = vj;

			System.out.println(Arrays.toString(usedSet));
			System.out.println("" + vi);
			counter++;
//			if (counter > 3) {
//				break;
//			}
		}
//
//		}
//
		//for (int j = 0; j < (vertexcount + 2); j++) {
		
		//Zeilen j
		System.out.print("vi|  ");
		counter = 0;
		while (counter < 2) {
			for (int i = 2; i < vertexcount + 1; i++) {
				if (i == vertexcount) {
					if (counter == 1) {
						System.out.print("" + i + "|\n");
					} else {
						System.out.print("" + i + "|  ");
					}
				} else {
					System.out.print("" + i + "  ");
				}
			}
			counter++;
		}
		for (int i = 0; i < 2 * (vertexcount - 1) + 2; i++) {
			System.out.print("---");
			if (i == 2 * (vertexcount - 1) + 1) {
				System.out.print("\n");
			}
		}
		for (int j = 0; j < (vertexcount); j++) {
			vi = vis.get(j);
			System.out.print("" + vi + " |");
			for (int vj = 1; vj < vertexcount; vj++) {
				if (vj == vertexcount - 1) {
					if (distances[vi][vj] != Integer.MAX_VALUE) {
						System.out.print("  " + distances[vi][vj] + "|");
					} else {
						System.out.print("  " + "-" + "|");
					}
				} else {
					if (distances[vi][vj] != Integer.MAX_VALUE) {
						System.out.print("  " + distances[vi][vj]);
					} else {
						System.out.print("  " + "-");
					}
				}
			}
		System.out.print("\n");
		}
	
		//predecessor output separately
		for (int j = 0; j < (vertexcount + 2); j++) {
			vi = vis.get(j);
			System.out.print("" + vi + " |");
			for (int vj = 1; vj < vertexcount; vj++) {
				if (vj == vertexcount - 1) {
					if (predecessors[vi][vj] != 0) {
						System.out.print("  " + predecessors[vi][vj] + "|");
					} else {
						System.out.print("  " + "-" + "|");
					}
				} else {
					if (predecessors[vi][vj] != 0) {
						System.out.print("  " + predecessors[vi][vj]);
					} else {
						System.out.print("  " + "-");
					}
				}
			}
		System.out.print("\n");
		}

		}

	/**
	 * Testmethod (copied from the assignment)
	 */
	public static void main(String[] args) {
		printDijkstra(new int[] { 4, 1, 2, 2, 1, 4, 5, 2, 4, 1, 2, 3, 4, 3, 1, 1, 4, 3, 1 });

//		printDijkstra(new int[] { 10, 1, 2, 30, 1, 3, 10, 2, 5, 15, 2, 8, 55, 3, 4, 5, 3, 9, 35, 4, 2, 10, 4, 5, 45, 4,
//				6, 10, 5, 3, 20, 5, 7, 15, 5, 9, 25, 6, 7, 5, 7, 10, 20, 8, 10, 15, 9, 8, 10, 9, 10, 30 });

	}

}
