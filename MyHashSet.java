import java.util.ArrayList;


/**
 * 
 * @author Christian Thelen
 *
 * @param <K> data type for this HashSet
 * 
 * Implementation of a HashSet using an ArrayList of ArrayLists
 */
public class MyHashSet<K> {
	

	private ArrayList<ArrayList<K>> hashList;
	//private ArrayList<K> rehashList;
	private int elementCount;
	private int listCount;
	
	/**
	 * Creates a HashSet of type K
	 */
	public MyHashSet() {
		//rehashList = new ArrayList<K>();
		hashList = new ArrayList<ArrayList<K>>();
		for(int i = 0; i<10;i++) {
			hashList.add(new ArrayList<K>());
		}
		listCount = 10;
	}
	
	/**
	 * 
	 * @param element to be added
	 * @return true, if the element was added and false, if the HashSet already contains the element
	 * 
	 */
	public boolean add(K element) {
		int index = element.hashCode()%listCount;
		if(hashList.get(index).contains(element)) {
			return false;
		}
		hashList.get(index).add(element);
		//rehashList.add(element);
		if(needsRehash()) {
			rehash();
		}
		return true;
	}
	/**
	 * 
	 * @param element to be removed 
	 * @return true, if the element was successfully removed
	 */
	public boolean delete(K element) {
		if(contains(element)) {
			int index = element.hashCode()%listCount;
			hashList.get(index).remove(element);
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @return an ArrayList containing all elements
	 */
	public ArrayList<K> getElements() {
		ArrayList<K> allElements = new ArrayList<>();
		for(int i = 0; i< hashList.size(); i++) {
			for(int j = 0; j < hashList.get(i).size(); j++) {
				allElements.add(hashList.get(i).get(j));
			}
		}
		return allElements;
		
	}
	/**
	 * @return true, if the HashSet contains the element
	 */
	public boolean contains(K element) {
		int index = element.hashCode()%listCount;
		boolean ret = hashList.get(index).contains(element);
		return ret;
	}
	
	private boolean needsRehash() {
		if((double) elementCount/listCount >2) {
			return true;
		}
		return false;
	}
	
	private void rehash() {
		listCount*=2;
		for(int i = listCount/2; i < listCount; i++) {
			hashList.add(new ArrayList<K>());
		}
		for(int i = 0; i< hashList.size(); i++) {
			for(int j = 0; j < hashList.get(i).size(); j++) {
				this.add((hashList.get(i).get(j)));
			}
		}
	}
	
	//public ArrayList<K> getElements(){
			//return rehashList;
		//}
	
	
	//private void rehash() {
		//listCount*=2;
		//hashList.clear();
		//for(int i = 0; i< listCount; i++) {
			//hashList.add(new ArrayList<K>());
		//}
		//for(int i = 0; i<rehashList.size(); i++) {
			//int index = rehashList.get(i).hashCode()%listCount;
			//hashList.get(index).add(rehashList.get(i));
		//}
	//}
	
	
}
