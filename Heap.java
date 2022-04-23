package ha05;

import java.util.ArrayList;
import java.util.Collections;

public class Heap {

	private ArrayList<Integer> heapList;

	public Heap() {
		heapList = new ArrayList<Integer>();
		heapList.add(0);
	}

	public boolean isEmpty() {
		if (this.heapList.isEmpty()) {
			return true;
		}
		return false;
	}

	public void add(int i) {
		if (isEmpty()) {
			heapList.add(i);
		} else {
			heapList.add(i);
			this.upheap();
		}
	}
	
	public int getMax() {
		if (!isEmpty()) {
			int max = heapList.get(1);
			this.downHeap();
			return max;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

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

	public String toString() {
		ArrayList<Integer> copy = new ArrayList<>(heapList);
		copy.remove(0);
		return copy.toString();
	}
}
