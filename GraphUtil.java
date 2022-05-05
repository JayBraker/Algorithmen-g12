package ha07;

public class GraphUtil {
	/**
	 * This function calculates the minimum spanning tree
	 * @param adjMatrix 
	 * @return the weight of all edges
	 * @author Laura Mey, Josha Bartsch, Christian Thelen
	 */
	public static int getMST(int[][] adjMatrix) {
		int vCount = adjMatrix.length;
		boolean[] visited = new boolean[vCount];
		visited[0] = true;

		int weight = 0;
		// this variable is for counting the amount of already created edges
		int edgeCount = 0;
		
		// there is a maximum amount of vCount-1 edges which need to be created
		while (edgeCount < vCount - 1) {
			int minWeight = Integer.MAX_VALUE;
			int start = 0;
			int target = 0;
			for (int i = 0; i < vCount; i++) {
				if (visited[i] == true) {
					for (int j = 0; j < vCount; j++) {
						if (visited[j] == false && adjMatrix[i][j] > 0 && adjMatrix[i][j] < minWeight) {
							start = i;
							target = j;
							minWeight = adjMatrix[i][j];
						}
					}
				}
			}
			System.out.println("Kante hinzugefuegt von " + (start+1) + " zu " + (target+1));
			weight+= minWeight;
			edgeCount++;
			visited[target] = true; 
		}
		return weight;
	}

	public static void main(String[] args) {
		int[][] adjazenzmatrix = { { 0, 3, 0, 2, 0, 0 }, { 3, 0, 2, 0, 3, 0 }, { 0, 2, 0, 1, 6, 0 },
				{ 2, 0, 1, 0, 0, 0 }, { 0, 3, 6, 0, 0, 5 }, { 0, 0, 0, 0, 5, 0 } };
		System.out.println("resultierende kosten: " + getMST(adjazenzmatrix));
	}
}
