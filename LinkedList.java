import java.util.ArrayList;

/**
 * Algorithmen HA3
 * 
 * Class that implements IList to provide a singly linked list made out of head
 * and nodes and allowing elementary operations such as inserting, removing,
 * getting, clearing and searching the list
 * 
 * References used: - Algorithmen Ü3, -
 * https://www.geeksforgeeks.org/doubly-linked-list/ , -
 * https://www.geeksforgeeks.org/write-a-function-to-get-nth-node-in-a-linked-list/
 * 
 * @author Christian Thelen, Joshua Bartsch, Laura Mey
 *
 * @param <T> type parameter, used as Integer only
 */
public class LinkedList<T> implements IList {
	Node head;
	int size = 0; // number of nodes in the list

	/**
	 * Subclass that provides nodes with data and successors
	 */
	class Node {
		int data;
		Node next;

		Node(int d) { // constructor to put data inside a node object
			data = d;
		}
	}

	/**
	 * Inserts value at the given position, pushing values at later positions back
	 * by one position
	 * 
	 * @param position int index where the value should be inserted
	 * @param value    int to be inserted at the position
	 * @throws ArrayIndexOutOfBoundsException if position is invalid
	 */
	public void insertAt(int position, int value) {
		if (position < 0 || (position > this.getCount())) {
			throw new ArrayIndexOutOfBoundsException("Index out of bounds");
		}

		Node new_Node = new Node(value);

		// in case of empty list, simply put the new node as head
		if (head == null) {
			head = new_Node;
			this.size++;
		}

		else {
			// insert at first position
			if (position == 0) {
				new_Node.next = head;
				head = new_Node;
				// add after the last position
			} else if (position == this.getCount()) {
				Node prev_Node = this.getNode(position - 1);
				prev_Node.next = new_Node;
				new_Node.next = null;
				// insert somewhere in the middle
			} else {
				Node next_Node = this.getNode(position);
				Node prev_Node = this.getNode(position - 1);
				new_Node.next = next_Node;
				prev_Node.next = new_Node;
			}
			this.size++;
		}

	}

	/**
	 * Removes value at the given position
	 * 
	 * @param position int index at which the value should be removed
	 * @throws ArrayIndexOutOfBoundsException if position is invalid
	 */
	public void removeAt(int position) {
		if (position < 0 || position >= this.getCount()) {
			throw new ArrayIndexOutOfBoundsException("Index out of bounds");
		}

		// empty list -> nothing to remove
		if (head == null) {
			return;
		}

		// remove first node
		if (position == 0) {
			head = head.next;
		} else {
			Node prev_Node = this.getNode(position - 1);
			// remove a node between the first and last one
			if ((position + 1) < this.getCount()) {
				prev_Node.next = this.getNode(position + 1);
			}
			// remove last node
			else {
				prev_Node.next = null;
			}
		}
		this.size--;

	}

	/**
	 * Helper method to return a node at a given position Same structure as getAt
	 * method
	 * 
	 * @param int position where to get the node
	 * @return Node node at the position
	 * @throws ArrayIndexOutOfBoundsException if position is invalid
	 */
	public Node getNode(int position) {
		if (position < 0 || position >= this.getCount()) {
			throw new ArrayIndexOutOfBoundsException("Index out of bounds");
		}

		Node current = head;
		int count = 0; // current position

		while (count <= position) {
			if (count == position) {
				return current;
			}
			current = current.next;
			count++;
		}
		return null;
	}

	/**
	 * Returns the value at a position
	 * 
	 * @param int position where to get the value
	 * @return int value at the position
	 * @throws ArrayIndexOutOfBoundsException if position is invalid
	 */
	public int getAt(int position) {
		if (position < 0 || position >= this.getCount()) {
			throw new ArrayIndexOutOfBoundsException("Index out of bounds");
		}

		Node current = head;
		int count = 0; // current position

		while (count <= position) {
			if (count == position) {
				return current.data;
			}
			current = current.next;
			count++;
		}
		return -1;
	}

	/**
	 * Searches the list for a given value
	 * 
	 * @param int value to search for
	 * @return int position (index) where the value was found (-1 if value not
	 *         found)
	 * 
	 */
	public int search(int value) {
		if (this.toArrayList().contains(value)) {
			return this.toArrayList().indexOf(value);
		}
		return -1;

	}

	/**
	 * Clears all values of the list by setting the head node to null *
	 */
	public void clear() {
		head = null;
		size = 0;
	}

	/**
	 * Gets size of the linked list
	 * 
	 * @returns int size (number of nodes/values in the list)
	 */
	public int getCount() {
		return size;
	}

	/**
	 * Helper method to print out linked lists
	 * @return ArrayList<Integer> with values contained in the linked list
	 */
	public ArrayList<Integer> toArrayList() {
		Node node = head;
		ArrayList<Integer> resultlist = new ArrayList<>();
		while (node != null) {
			resultlist.add(node.data);
			node = node.next;
		}
		return resultlist;
	}

}

