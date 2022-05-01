import java.util.List;

/**
 * Klasse mit vorgefertigten Testfunktionen fuer die Klasse {@link Graph}.<br>
 * Vorgabe zur Loesung zu algo-h06 im SS 2022.
 */
public class GraphTest {

  /** Kantenliste fuer den ersten Testgraphen, auf den alle Methoden angewandt werden */
  private static int[] vlist = { 6, 10,       // 6 Knoten, 10 Kanten
                                 1, 5, 1, 4,  // alle Knotenpaare fuer Kanten mit 1 als Anfangsknoten
                                 2, 3, 2, 6,  // ...
                                 3, 4, 3, 5,  // ...
                                 4, 5, 4, 6,  // ...
                                 5, 6,        // ...
                                 6, 4 };      // alle Knotenpaare fuer Kanten mit 6 als Anfangsknoten
  // Den Graphen kann man sich so vorstellen:
  //
  //        +---------3<----2
  //        |         |     |
  //        |   1     |     |
  //        |   |\    |     |
  //        |   | \   |     |
  //        |   |  \  |     |
  //        |   |   \ |     |
  //        |   v    vv     v
  //        +-->5<----4<--->6<-+
  //            |              |
  //            +--------------+

  /** Pseudokantenliste fuer den 2. Testgraphen, dessen Kanten zufaellig bestimmt werden muessen. */
  private static int[] vlist2 = { 5, 20 };    // 5 Knoten, 20 Zufallskanten

  private static List<int[]> kantenlisten = List.of(vlist, vlist2);

  /**
   * die Grundausstattung der Klasse testen und die Liste der benutzten Kantenlisten liefern
   *
   * @return  Liste der Kantenlisten der getesteten Graphen
   */
  public static List<int[]> minimaltest() {
    Graph g = new Graph(0);
    System.out.println();
    System.out.println("Test der Grundausstattung der `" + g.getClass() + "':");
    for (int[] vlist: kantenlisten) {
      if (vlist.length < vlist[1])          // Pseudokantenliste (ohne Kantendefinitionen)?
        g = new Graph(vlist[0], vlist[1]);  // = Graph mit .. Knoten und .. Zufallskanten
      else                                  // echte Kantenliste?
        g = new Graph(vlist);               // = Graph aus Kantenliste
      System.out.println("g = " + g);
    }
    return kantenlisten;
  }

  /**
   * Die Klasse {@link Graph} moeglichst komplett testen.
   *
   * @param  args  was dem Programmaufruf uebergeben wurde
   */
  public static void main(String[] args) {
    minimaltest();
  }
}

