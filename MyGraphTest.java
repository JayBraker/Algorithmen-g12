import java.util.List;
import java.util.Arrays;

/**
 * Test der Klasse {@link MyGraph}
 */
public class MyGraphTest {

  /**
   * Die 6 zusaetzlich zu implementierenden Methoden der Klasse testen.
   * Dabei werden die Testgraphen aus den gegebenen Kantenlisten erzeugt,
   * die auch nur scheinbare Kantenlisten sein koennen, s. {@link GraphTest}.
   *
   * @param  kantenlisten  Liste von Kantenlisten der zu testenden Graphen
   */
  private static void zusatztest(List<int[]> kantenlisten) {

    MyGraph g = new MyGraph(1, 1);  // erstmal irgendwie erzeugen
    System.out.println();
    System.out.println("Test der fuer `" + g.getClass() + "' zu implementierenden Methoden:");

    int nummer = 1;
    for (int[] vlist: kantenlisten) {
      System.out.println();
      System.out.println("Test des " + nummer + ". Graphen:");
      if (vlist.length < vlist[1])            // Pseudokantenliste (ohne Kantendefinitionen)?
        g = new MyGraph(vlist[0], vlist[1]);  // => Graph mit .. Knoten und .. Zufallskanten
      else                                    // echte Kantenliste?
        g = new MyGraph(vlist);               // => Graph aus Kantenliste
      System.out.println("Kantenliste von g = " + g.getEdgeList());
      System.out.println("Knotenliste von g = " + g.getVertexList());
      weitereTests(g);
      ++nummer;
    }
  }

  private static void weitereTests(MyGraph g) {
    int[][] adjazenzmatrix = g.getAdjacencyMatrix();
    System.out.println("Adjazenzmatrix von g = " + Arrays.deepToString(adjazenzmatrix));
    int start = 1;
    System.out.println("Startknoten fuer Breitensuche = " + start);
    var liste = g.bfs(start);
    System.out.println("Graph g: Liste der durchlaufenen Knoten = " + liste);
    System.out.println("Startknoten fuer Tiefensuche = " + start);
    liste = g.dfs(start);
    System.out.println("Graph g: Liste der durchlaufenen Knoten = " + liste);

    // Wenn man bei Knoten 1 im 1. Testgraphen g startet, sind
    // offensichtlich nur die Knoten 4, 5 und 6 erreichbar, was
    // Breiten- und Tiefensuche bestaetigen. Also sind von 1 aus
    // betrachtet die Knoten 2 und 3 unerreichbar. Das laesst sich
    // auch gut am Bild des Graphen erkennen, da von 2 und 3 nur
    // Kanten ausgehen und nicht umgekehrt.
    System.out.println("Startknoten = " + start);
    liste = g.getUnreachableVertices(start);
    System.out.println("Graph g: Liste der unerreichbaren Knoten = " + liste);
  }

  /**
   * Die Klasse {@link MyGraph} moeglichst komplett testen.
   *
   * @param  args  was dem Programmaufruf uebergeben wurde
   */
  public static void main(String[] args) {
    List<int[]> kantenlisten = GraphTest.minimaltest();
    zusatztest(kantenlisten);
  }
}

