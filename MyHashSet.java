import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Collections;

public class MyHashSet<K> {
	private Hashtable<Integer, ArrayList<K>> myHashTable;
	private int totalCount;

	public MyHashSet () {
		this(10);
	}

	public MyHashSet (int fieldCount) {
		myHashTable = new Hashtable<>(fieldCount);
		for(int i = 0; i<fieldCount; i++) {
			myHashTable.put(i, new ArrayList<>());
		}
	}

	public boolean add(K element) {
		if (contains(element)) {
			return false;
		} else {
			ArrayList<K> temp = myHashTable.get(hashFunction(element));
			temp.add(element);
			totalCount++;
			rehash();
			return true;
		}
	}

	public boolean delete(K element) {
		if (!contains(element)) {
			return false;
		} else {
			ArrayList<K> temp = myHashTable.get(hashFunction(element));
			temp.remove(element);
			totalCount--;
			return true;
		}
	}

	private void rehash() {
		if (((double) totalCount / myHashTable.size()) > 2) {
			MyHashSet<K> temp = new MyHashSet<>(myHashTable.size()*2);
			for (K el : getElements())
				temp.add(el);
			myHashTable = temp.myHashTable;
		}
	}

	private int hashFunction(K element) {
		return Math.abs(element.hashCode() % myHashTable.size());
	}

	public boolean contains(K element) {
		ArrayList<K> temp = myHashTable.get(hashFunction(element));
		return temp.contains(element);
	}

	public ArrayList<K> getElements() {
		ArrayList<K> ret = new ArrayList<>();
		
		myHashTable.forEach(
			(key, elements) -> ret.addAll(elements)
		);
		return ret;
	}

	public static void main(String [] args) {
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
