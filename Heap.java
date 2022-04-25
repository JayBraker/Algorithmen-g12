package ha05;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * @author Josha Bartsch,Christian Thelen,Laura Mey
 * 
 * Implementation of a Heap using an ArrayList.
 *
 */
public class Heap {

	private ArrayList<Integer> heapList;

	public Heap() {
		heapList = new ArrayList<Integer>();
		heapList.add(0);
	}
	/**
	 * This function checks if the Heap is empty
	 * @return true, if empty
	 * 	
	 */
	public boolean isEmpty() {
		if (this.heapList.size() == 1) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param i int value to be inserted into the Heap
	 */
	public void add(int i) {
		if (isEmpty()) {
			heapList.add(i);
		} else {
			heapList.add(i);
			this.upheap();
		}
	}
	/**
	 * removes and then returns the largest Value of the Heap
	 * @return largest Value
	 * @throws IndexOutOfBoundsException if the Heap is empty
	 */
	public int getMax() {
		if (!isEmpty()) {
			int max = heapList.get(1);
			this.downHeap();
			return max;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	/**
	 * helper function to rearrange elements after adding an int value
	 */
	private void upheap() {
		int pos = heapList.size() - 1;
		while (pos > 1 && heapList.get(pos) > heapList.get(pos / 2)) {
			if (heapList.get(pos) <= heapList.get((pos) / 2)) {
				break;
			}
			if (heapList.get(pos) > heapList.get((pos) / 2)) {
				Collections.swap(heapList, pos, (pos) / 2);
				pos = pos / 2;
			}
		}
	}

	/**
	 * helper function to rearrange elements after removing the largest int value
	 */
	private void downHeap() {
		Collections.swap(heapList, 1, heapList.size() - 1);
		heapList.remove(heapList.size() - 1);
		int pos = 1;
		while (pos * 2 < heapList.size()) {
			int biggerChild = pos * 2;
			if (biggerChild < heapList.size() - 1 && heapList.get(biggerChild + 1) > heapList.get(biggerChild)) {
				biggerChild++;
			}
			if (heapList.get(biggerChild) > heapList.get(pos)) {
				Collections.swap(heapList, biggerChild, pos);
				pos = biggerChild;
			} else {
				break;
			}
		}
	}
	/**
	 * @return String representation of the Heap
	 */
	public String toString() {
		if(isEmpty()) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder("");
		sb.append("[");
		for(int i = 1; i < heapList.size(); i++) {
		sb.append(heapList.get(i));
		sb.append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append("]");
		return sb.toString();
	}
}
