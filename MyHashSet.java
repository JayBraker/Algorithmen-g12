import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

/**
 * Algorithmen & Datenstrukturen - Hausaufgabe 2
 * 
 * This class implements a Hashset, using a Hashtable with ArrayLists 
 * and the division algorithm for the hash function of objects.
 * 
 * @author lmey
 *
 * @param <K> data type of the elements of the set
 */
public class MyHashSet<K> {
	private Hashtable<Integer, ArrayList<K>> myHash;
	private int listcounter; // number of lists in the hashtable
	private int elementcounter; //total number of elements saved in the hashtable

	// Constructor (initial size, i.e. number of lists, is set to 10)
	public MyHashSet() {
		this.myHash = new Hashtable<Integer, ArrayList<K>>(10);
		this.listcounter = 10;
		for (int i = 0; i < 10; i++) {
			myHash.put(i, new ArrayList<K>());
		}
		this.elementcounter = 0;
	}

	/**
	 * Hashfunction, calculates modulo of hashcode by number of lists
	 * 
	 * @param object o - object the should be hashed
	 * @return int number that is the key where the object will be saved
	 * 
	 */
	public int hashfunction(Object o) {
		return Math.abs(o.hashCode() % this.listcounter);
	}

	/**
	 * indicates if a rehash is necessary
	 * 
	 * @return true if the filling grade is greater than 2, i.e. a rehash is necessary
	 */
	public boolean needRehash() {
		return ((1.0 * this.elementcounter / this.listcounter) > 2);
	}

	/**
	 * Rehash the Hashset by doubling the number of lists and re-adding all 
	 * elements
	 *  
	 */
	public void rehash() {
		ArrayList<K> allelements = this.getElements();
		this.listcounter *= 2;
		this.myHash = new Hashtable<Integer, ArrayList<K>>(this.listcounter);
		for (int i = 0; i < this.listcounter; i++) {
			myHash.put(i, new ArrayList<K>());
		}
		this.elementcounter = 0;
		for (K e : allelements) {
			this.add(e);
		}
	}

	/**
	 * Add element to hashset
	 * @param element to be added
	 * @return true if the element already exists
	 */
	public boolean add(K element) {
		if (contains(element)) {
			return true;
		}
		elementcounter++;
		if (needRehash()) {
			rehash();
		}
		int fcn = hashfunction(element);
		myHash.get(fcn).add(element);

		return false;
	}

/**
	 * Removes elment from hashset, without changing the number of lists
	 * 
	 * @param element to be deleted
	 * @return true if the element existed
	 */
	public boolean delete(K element) {
		if (myHash.get(hashfunction(element)).contains(element)) {
			myHash.get(hashfunction(element)).remove(element);
			return true;
		}
		return false;
	}

	// Gibt zurück, ob das Element existiert.
	public boolean contains(K element) {
		if (myHash.get(hashfunction(element)).contains(element)) {
			return true;
		}
		return false;
	}

	/**
	 * Get all elements in the hashset
	 * @return ArrayLists of all elements
	 */
	public ArrayList<K> getElements() {
		ArrayList<K> allelements = new ArrayList<>();
		for (ArrayList<K> list : myHash.values()) {
			for (K i : list)
				allelements.add(i);
		}
		return allelements;
	}

	/**
	 * Testcode (as provided)
	 */
	public static void main(String[] args) {
		MyHashSet<Integer> myHash = new MyHashSet<>();
		for (int i = 0; i < 30; i++) {
			myHash.add(i);
		}
		System.out.println(myHash.contains(5)); // true
		myHash.delete(5);
		System.out.println(myHash.contains(5)); // false
		ArrayList<Integer> el = myHash.getElements();
		System.out.println(el); // Zahlen 0..29 ausser der 5 unsortiert
		Collections.sort(el);
		System.out.println(el); // 0,1,2,3,4,6,7,....,29
	}

}
