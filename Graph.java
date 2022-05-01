import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;

/**
 * Hier werden die grundlegenden Datenstrukturen und Operationen fuer
 * einen Graphen definiert.
 * Ein solcher Graph beruht dabei auf Adjazenzlisten (s. Vorlesung).<br>
 * Vorgabe zur Loesung zu algo-h06 im SS 2022.
 */
public class Graph {

  /** Speicherung per Adjazenzlisten, Indizes 1..n (n: Knotenanzahl) */
  private ArrayList<Integer>[] adj;
  /** Anzahl der Kanten */
  private int edgeCount;

  /**
   * Erzeugt leeren Graphen mit der angegebenen Anzahl an Knoten
   *
   * @param  v  die Anzahl der Knoten
   */
  public Graph(int v) {
    createGenericArray(v + 1);                  // Index 0 unbenutzt!
    for (int i = 0; i < adj.length; i++) {      // fuer lauter leere
      adj[i] = new ArrayList<Integer>();        // Adjazenzlisten sorgen
    }
    this.edgeCount = 0;                 // => Es gibt noch keine Kanten.
  }

  /**
   * Erzeugt einen Graphen mit v Knoten und e zufaelligen Kanten
   *
   * @param  v  die Anzahl der Knoten
   * @param  e  die Anzahl der zufaelligen Kanten
   */
  public Graph(int v, int e) {
    this(v);
    Random zufall = new Random(9);  // mit Seed => immer dieselben Zufallszahlen
    for (int i = 0; i < e; i++) {   // e Kanten
      int k1 = 1;
      int k2 = 1;
      do {                             // 2 Knoten zufaellig waehlen:
        k1 = zufall.nextInt(v) + 1;    //   k1 aus [1;v]
        k2 = zufall.nextInt(v) + 1;    //   k2 aus [1;v]
      } while (adj[k1].contains(k2));  // bis Kante von k1 nach k2 neu ist
      adj[k1].add(k2);                 // Kante von k1 nach k2 anlegen
    }
    this.edgeCount = e;
  }

  /**
   * Erzeugt einen Graphen aus der angegebenen Kantenliste
   *
   * @param  list  die Kantenliste
   */
  public Graph(int[] list) {
    this(list[0]);                        // => Graph mit list[0] Knoten
    for (int i = 0; i < list[1]; i++) {   // list[1] Kanten definieren
      adj[list[2 + i * 2]].add(list[3 + i * 2]);
    }
    this.edgeCount = list[1];
  }

  // soll dazu dienen, eine derartige Operation auf eine einzige Stelle
  // im Programm zu konzentrieren
  @SuppressWarnings({"unchecked"})
  private void createGenericArray(int n) {
    this.adj = new ArrayList[n];                       // => 2 Warnungen
  }

  /**
   * Gibt die Anzahl der Knoten zurueck
   *
   * @return  die Anzahl der Knoten
   */
  public int getVertexCount() {
    return this.adj.length - 1;                     // Platz 0 unbelegt!
  }

  /**
   * Gibt die Anzahl der Kanten zurueck
   *
   * @return  die Anzahl der Kanten
   */
  public int getEdgeCount() {
    return edgeCount;
  }

  /**
   * Fuegt eine Kante hinzu.
   *
   * @param  from  Anfangsknoten der Kante
   * @param  to    Endknoten     der Kante
   */
  public void addEdge(int from, int to) {
    adj[from].add(to);
    ++edgeCount;
  }

  /**
   * Gibt eine Liste mit allen direkten Nachfolgern des angegebenen Knotens
   * zurueck.
   *
   * @param  v  der Knoten
   * @return    die Liste der direkten Nachfolger des Knotens
   */
  public ArrayList<Integer> getAdjacent(int v) {
    return new ArrayList<Integer>(adj[v]);              // nur als Kopie
  }

  /**
   * Einen Iterator zum Durchlaufen aller direkten Nachfolger des
   * angegebenen Knotens liefern.
   *
   * @param  v  der Knoten
   * @return    Iterator ueber alle direkten Nachfolger von v
   */
  public Iterator<Integer> getIt(int v) {
    return this.adj[v].iterator();
  }

  /**
   * Melden, wieviele Kanten vom genannten Knoten ausgehen.
   *
   * @param   v  gegebener Knoten
   * @return     Anzahl der von v ausgehenden Kanten
   */
  public int ausgangsgrad(int v) {
    return this.adj[v].size();  // Anzahl der Elemente der Adjazenzliste
  }                             // des Knotens mit dem Index v

  /**
   * Der Graph als String: direkte Wiedergabe der internen Darstellung.
   *
   * @return  Stringdarstellung des Graphen
   */
  public String toString() {
    String ret = "[";
    for (int i = 1; i < adj.length; i++) {           // eine Liste aller
      ret = ret + adj[i];                            // Adjazenzlisten
    }
    ret = ret + "]";
    return ret;
  }
}

