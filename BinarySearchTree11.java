package ha11;

import java.util.LinkedList;
import java.util.Queue;

import ha11.BinarySearchTree.TreeNode;

/**
 * HA 11, Aufgabe 1 (schriftlich)
 * 
 * An den Knoten des Suchbaums sollte zusätzlich die Summe aller 
 * Unterknoten sowie die Anzahl aller Unterknoten gespeichert werden.
 * Daraus kann dann, wenn der Knoten (die Wurzel des Unterbaums) bekannt
 * ist, einfach über Summe/Anzahl der Mittelwert berechet werden. 
 * Beim Löschen/Einfügen von Knoten muss also der Wert der Summe in allen
 * darüber liegenden Elternknoten angepasst werden (Löschen: Schlüssel des 
 * neuen Knotens wird subtrahiert, Einfügen: addiert), ebenso wie die 
 * Anzahl (Einfügen: +1, Löschen: -1). * 
 * 
 */

/**
 * Erweiterung der Binary Search Tree Klasse aus vorherigen Übungen bzw. der
 * Vorlage um die Möglichkeit, Mittelwert der Schlüssel in einem beliebigen
 * Unterbaum zu bestimmen
 * 
 * @author Laura Mey, Christian Thelen, Josha Bartsch
 *
 */
public class BinarySearchTree11 {

	/**
	 * Die Knotenklasse als statische innere Klasse. Angepasst: Attribut "treesum",
	 * das mit value initalisiert wird und entsprechende Getter- und Setter- Methode
	 * *
	 * 
	 */
	public static class TreeNode {
		private int value;
		private TreeNode left;
		private TreeNode right;
		private int treesum;
		private int treesize;

		public TreeNode(int value) {
			this.value = value;
			this.treesum = value;
			this.treesize = 0;
		}

		public String toString() {
			return this.value + " ";
		}

		public int getValue() {
			return this.value;
		}

		public TreeNode getLeft() {
			return this.left;
		}

		public TreeNode getRight() {
			return this.right;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public void setLeft(TreeNode node) {
			this.left = node;
		}

		public void setRight(TreeNode node) {
			this.right = node;
		}

		public int getTreesum() {
			return this.treesum;
		}

		public void setTreesum(int treesum) {
			this.treesum = treesum;
		}

		public int getTreesize() {
			return this.treesize;
		}

		public void setTreesize(int treesize) {
			this.treesize = treesize;
		}
	}

	/** Baumwurzel */
	protected TreeNode root;

	/**
	 * sucht den Knoten mit Wert val und gibt den Mittelwert aller Knoten des
	 * Unterbaums zurück
	 * 
	 * @param val Wert des gesuchten Knotens
	 * @return Mittelwert der Knoten des Unterbaums
	 */
	public double getAverageOfSubtree(int val) {
		if (!this.contains(val)) {
			throw new NoSuchElementException("Wert konnte im Baum nicht gefunden werden.");
		}
		TreeNode rootOfSubtree = this.getNode(val);
		return rootOfSubtree.getTreesum() * 1.0 / rootOfSubtree.getTreesize();
	}

	/**
	 * Einen neuen Datensatz in den binaeren Suchbaum einfuegen. Angepasst: treesum
	 * & treesize updaten
	 *
	 * @param data einzufuegender Datensatz
	 * @return true: Datensatz wurde eingefuegt; false: Datensatz war schon
	 *         vorhanden.
	 */
	public boolean insert(int data) {
		if (root == null) {
			root = new TreeNode(data);
			return true;
		}

		TreeNode temp = root;
		while (temp.getValue() != data) {
			if (temp.getValue() > data) {
				if (temp.getLeft() == null) {
					temp.setLeft(new TreeNode(data));
					return true;
				}
				temp.setTreesize(temp.getTreesize() + 1); // HA 11
				temp.setTreesum(temp.getTreesize() + data);// HA 11
				temp = temp.getLeft();
			} else {
				if (temp.getRight() == null) {
					temp.setRight(new TreeNode(data));
					return true;
				}
				temp.setTreesize(temp.getTreesize() - 1); // HA 11
				temp.setTreesum(temp.getTreesize() + data);// HA 11
				temp = temp.getRight();
			}
		}
		return false;
	}

	/**
	 * Loescht den Knoten mit dem Wert n iterativ, falls vorhanden.
	 *
	 * @param n Wert, dessen Knoten geloescht werden soll
	 * @throws ArithmeticException wenn der Wert nicht im Baum enthalten ist
	 */
	public void remove(int n) {

		TreeNode parent = null; // Zeigerpaar aus Knoten und seinem
		TreeNode node = this.root; // Elter, beginnend bei der Wurzel

		while (node != null) {
			if (node.getValue() == n) { // Knoten mit n drin gefunden?
				remove(node, parent); // diesen Knoten aus dem Baum entfernen
				return; // Knoten entfernt => Methode beenden
			}
			parent = node; // erstmal neuen Elter setzen
			node = nextNode(node, n); // im richtigen Teilbaum weitersuchen
		}
		// regulaeres Schleifenende => Knoten nicht gefunden, Ausnahme:
		throw new ArithmeticException("Knoten " + n + " gibt es nicht!");
	}

	// Hilfsmethoden fuer `remove(int n')

	/**
	 * Hilfsmethode fuer `remove(int n)': den Knoten `node' mit dem Elternknoten
	 * `parent' aus dem Baum entfernen.
	 *
	 * @param node   aus dem Baum zu entfernender Knoten
	 * @param parent Elter des zu entfernenden Knotens
	 */
	protected void remove(TreeNode node, TreeNode parent) {

		if (isLeaf(node)) // 1. Fall: Blatt
			removeLeaf(node, parent);
		else if (isInnerNode(node)) // 2. Fall: zwei Kinder
			removeTwoChildren(node);
		else // 3. Fall: ein Kind
			removeOneChild(node);
	}

	/**
	 * Herausfinden, ob der gegebene Knoten ein Blatt ist.
	 *
	 * @param baum zu pruefender Baumknoten
	 * @return Ist der Knoten ein Blatt?
	 */
	public static boolean isLeaf(TreeNode baum) {
		return baum.getLeft() == null && baum.getRight() == null;
	}

	/**
	 * Melden, ob der uebergebene Knoten 2 Kinder hat.
	 *
	 * @param baum zu pruefender Baumknoten
	 * @return Hat der uebergebene Knoten 2 Kinder?
	 */
	public static boolean isInnerNode(TreeNode baum) {
		return baum.getLeft() != null && baum.getRight() != null;
	}

	/**
	 * Hilfsmethode fuer `remove(int n)': weiter zum Teilbaum, wo Knoten n liegen
	 * muss
	 *
	 * @param node aktueller Knoten
	 * @param n    gesuchter Knoteninhalt
	 * @return Teilbaum, in dem der gesuchte Knoten liegen muss
	 */
	protected TreeNode nextNode(TreeNode node, int n) {
		return (n < node.getValue()) ? node.getLeft() : node.getRight();
	}

	/**
	 * Den Blattknoten `node' mit `parent' als Elter aus dem Baum entfernen, falls
	 * nur ein Blatt entfernt werden muss.
	 *
	 * @param node   aus dem Baum zu entfernender Knoten
	 * @param parent Elter des zu entfernenden Knotens
	 */
	private void removeLeaf(TreeNode node, TreeNode parent) {
		if (parent == null) // kein Elter vorhanden?
			clear(); // => Baum nun leer
		else if (parent.getLeft() == node) { // Knoten ist linkes Kind?
			parent.setLeft(null); // => nun kein linkes Kind mehr da
			parent.setTreesize(parent.getTreesize() - 1); // HA11
			parent.setTreesum(parent.getTreesize() - node.getTreesum()); // HA11
		} else {// Knoten ist rechtes Kind?
			parent.setRight(null); // => nun kein rechtes Kind mehr da
			parent.setTreesize(parent.getTreesize() - 1);// HA11
			parent.setTreesum(parent.getTreesize() - node.getTreesum());// HA11
		}
		recalcTree(parent);//HA11
	}

	/**
	 * Den Knoten `node' aus dem Baum entfernen, falls der Knoten nur ein Kind hat.
	 *
	 * @param node aus dem Baum zu entfernender Knoten
	 */
	private void removeOneChild(TreeNode node) {
		TreeNode kind = (node.getLeft() != null) ? node.getLeft() : node.getRight();
		node.setTreesize(kind.getTreesize());// HA11
		node.setTreesum(kind.getTreesum());// HA11
		node.setValue(kind.getValue()); // Inhalt des Kindes in den zu loeschenden
		node.setLeft(kind.getLeft()); // Knoten kopieren, der damit faktisch
		node.setRight(kind.getRight()); // verschwunden ist
		recalcTree(node); //HA11
	}

	/**
	 * den Knoten `node' aus dem Baum entfernen, falls der Knoten ein normaler
	 * innerer Knoten mit 2 Kindern ist.
	 *
	 * @param node aus dem Baum zu entfernender Knoten
	 */
	protected void removeTwoChildren(TreeNode node) { // TODO

		// den Ersatzknoten fuer den zu entfernenden Knoten suchen, also
		// den naechstgroesseren im rechten Teilbaum, gleichzeitig auch
		// noch den Elternknoten des Ersatzknotens:
		TreeNode elter = node;
		TreeNode ersatz = elter.getRight(); // vom rechten Kind des zu
		for (; // loeschenden aus so weit
				ersatz.getLeft() != null; // weit nach links gehen wie
				elter = ersatz, // moeglich, dabei einen
				ersatz = elter.getLeft()) // Elternknoten mitfuehren
			;

		// Der Ersatzknoten tritt nun an die Stelle des zu loeschenden Knotens:
		node.setValue(ersatz.getValue()); // Daten aus Ersatzknoten uebernehmen

		// Der Ersatzknoten kann nun ausgehaengt werden:
		if (elter == node) // Ist er Kind des zu entfernenden Knotens?
			elter.setRight(ersatz.getRight()); // => neues rechtes Kind im Elternknoten

		else // Schleife mindestens einmal durchlaufen?
			elter.setLeft(ersatz.getRight()); // => neues linkes Kind im Elternknoten
		
		recalcTree(node); //HA11
		recalcTree(elter); //HA11

	}

	// HA11
	/**
	 * Berechne Anzahl und Summe von Unterbäumen für alle Knoten auf dem Weg zu dem übergebenen Knoten
	 * neu aus
	 * @param node
	 */
	public void recalcTree(TreeNode node) {
		if (node == root) {
			return;
		}
		TreeNode temp = root;
		while (node.getValue() != temp.getValue()) {
			if (temp.getValue() > node.getValue()) {
				temp.setTreesize(temp.getTreesize() + 1); // HA 11
				temp.setTreesum(temp.getTreesize() + node.getValue());// HA 11
				temp = temp.getLeft();
			} else {
				temp.setTreesize(temp.getTreesize() - 1); // HA 11
				temp.setTreesum(temp.getTreesize() + node.getValue());// HA 11
				temp = temp.getRight();
			}
		}

	}

	/**
	 * Herausfinden, ob ein gewisser Datensatz schon im binaeren Suchbaum enthalten
	 * ist.
	 *
	 * @param data zu suchender Datensatz
	 * @return true: Datensatz ist vorhanden; false: Datensatz ist nicht vorhanden.
	 */
	public boolean contains(int data) {
		TreeNode temp = root;
		while (temp != null) {
			if (temp.getValue() == data) {
				return true;
			}
			if (temp.getValue() > data) {
				temp = temp.getLeft();
			} else {
				temp = temp.getRight();
			}
		}
		return false;
	}

	/** den Baum leeren */
	public void clear() {
		this.root = null;
	}

	/** den Baum levelorder ausgeben */
	public void show() {
		System.out.print("Baum levelorder = [ ");
		if (this.root != null) {
			Queue<TreeNode> schlange = new LinkedList<>();
			schlange.add(this.root);
			while (!schlange.isEmpty()) {
				TreeNode k = schlange.remove();
				System.out.print(k + " ");
				if (k.getLeft() != null)
					schlange.add(k.getLeft());
				if (k.getRight() != null)
					schlange.add(k.getRight());
			}
		}
		System.out.println("]");
	}

	/**
	 * Sucht nach dem Wert x im Baum (iterativ).
	 *
	 * @param x zu suchender Wert
	 * @return Knoten, der den Wert x enthaelt, oder null
	 */
	public TreeNode getNode(int x) {
		TreeNode node = this.root;
		while (node != null && node.getValue() != x) {
			if (x < node.getValue())
				node = node.getLeft();
			else
				node = node.getRight();
		}
		return node;
	}

}
