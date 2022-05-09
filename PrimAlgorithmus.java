/**
 * HA7
 * 
 * Berechnet einen Minimal Spanning Tree mit dem Prim Algorithmus und erzeugt
 * die Beispielausgabe
 * 
 * @author Laura Mey, Josha Bartsch, Christian Thelen
 *
 */
public class PrimAlgorithmus {

	/**
	 * berechnet einen Minimal Spanning Tree (MST)
	 * 
	 * @param edges int[][] Adjazenzmatrix eines ungerichteten Graphen
	 * @return int minimale Kosten bei Durchlauf des MST
	 */
	public static int getMST(int[][] edges) {
		int vertexcount = edges.length;
		int total_mincost = 0;
		boolean[] inMST = new boolean[vertexcount]; //speichert, ob ein Knoten schon im MST ist oder nicht
		inMST[0] = true; //setze Startknoten
		System.out.println("waehle 1 als Wurzel");
		
		int edge_count = 0; // Kantenzahl -> fuege max. eine Kante weniger hinzu, als es Knoten gibt
		while (edge_count < vertexcount - 1) {
			int min = Integer.MAX_VALUE;
			int targetv = 0;
			int startv = 0;
			
			//finde Kante mit minimalen Kosten zu einem Zielknoten, der noch nicht in MST enthalten ist, und speichere Ergebnis
			for (int i = 0; i < vertexcount; i++) {
				for (int j = 0; j < vertexcount; j++) {
					if ((edges[i][j] < min) && (edges[i][j] != 0) && ((inMST[i] == true && inMST[j] == false))) { //
						min = edges[i][j];
						startv = i;
						targetv = j;
					}
				}
			}
			
			//Ausgabe und Anpassung nachdem eine Kante zugefuegt wurde
			System.out.println(String.format("Kante hinzugefuegt von %d zu %d", startv + 1, targetv + 1));
			edge_count++;
			total_mincost += min;
			inMST[targetv] = true;

		}
		return total_mincost;

	}


	/**
	 * Testklasse laut Aufgabenstellung
	 */
	public static void main(String[] args) {
		int[][] adjazenzmatrix = { { 0, 3, 0, 2, 0, 0 }, { 3, 0, 2, 0, 3, 0 }, { 0, 2, 0, 1, 6, 0 },
				{ 2, 0, 1, 0, 0, 0 }, { 0, 3, 6, 0, 0, 5 }, { 0, 0, 0, 0, 5, 0 } };
		System.out.println("resultierende kosten: " + getMST(adjazenzmatrix));
	}

}
