import java.util.ArrayList;
import java.util.Collections;

/**
 * Algorithmen Hausaufgabe #5
 * 
 * Implementation of a heap based on an integer arraylist
 * 
 * @author Josha Bartsch, Christian Thelen, Laura Mey
 *
 */
public class Heap {
	private ArrayList<Integer> data;

	/**
	 * constructor to initiate data arraylist with first (unused) element
	 */
	public Heap() {
		this.data = new ArrayList<Integer>();
		data.add(null);
	}

	/**
	 * check if there are any elements in the heap (apart from the first untouched one)
	 * 
	 * @return true iff heap is empty
	 */
	public boolean isEmpty() {
		return (data.size() == 1);
	}

	/** 
	 * adds an integer value to the heap, using the upheap method to preserve the heap structure
	 * 
	 * @param int value to be added
	 */
	public void add(int i) {
		data.add(i);
		this.upheap();
	}

	/**
	 * removes the largest (=first real) element of the heap using the downheap method and returns it
	 * 
	 * @returns int largest value of the heap
	 * @throws IndexOutOfBoundsException if the heap is empty
	 * 
	 */
	public int getMax() {
		if (this.isEmpty()) {
			throw new IndexOutOfBoundsException("There are not elements in the heap.");
		}
		int max = data.get(1);
		
		data.set(1,  data.get(data.size()-1));
		data.remove(data.size() - 1);

		this.downheap();
		
		return max;	
	
	}

	/**
	 * returns the data list (without the untouched first element) as a String
	 * 
	 * @return String representation of the heap
	 */
	@Override
	public String toString() {
		return "" + data.subList(1, data.size());
	}

	/**
	 * helper method used by add method
	 * 
	 * swaps parent and child nodes if the child is larger than the parent, so that the ascending order of the heap is generated/preserved
	 */
	private void upheap() {
		int pos = data.size() - 1;
		while (pos > 1 && data.get(pos) > data.get(pos / 2)) {
			Collections.swap(data, pos, pos / 2);
			pos = pos / 2;
		}

	}

	/**
	 * helper method used by getMax method to rearrange elements after the largest value is dropped 
	 */
	private void downheap() {
		int p = 1;
		while (p*2 < data.size()) { //check if child exists
			int biggerChild = p*2;
			if (biggerChild < data.size()-1 
					&& data.get(biggerChild+1) > data.get(biggerChild)){ //check if second child exists and if it is bigger than first one
						biggerChild++;//if second child is bigger, set it as biggerChild
					}
			if (data.get(biggerChild) > data.get(p)) { //check if biggest child larger than parent
				Collections.swap(data,  biggerChild,  p);
				p = biggerChild;
			
			}else {
				break;
			}
		}
	}
	
	/**
	 * test method for the rest of the heap class
	 * 
	 * input based on the homework assignment
	 */
	public static void main(String[] args) {
		Heap heap = new Heap();
		int[] test = new int[] {1, 6, 8, 18, 23, 5, 17, 20, 26, 21, 9};
		for (int i : test) {
			heap.add(i);
			System.out.println(heap.toString());
		}
		while (heap.isEmpty() == false) {
			System.out.println(heap.getMax());
			System.out.println(heap.toString());
		}
	}
}


