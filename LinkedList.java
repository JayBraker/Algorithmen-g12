import java.util.Iterator;

/**
 * Class LinkedList implements IList and Iterable interfaces.
 * Implementation of a linked list, items are accessible through indices.
 * 
 * @author Christian Thelen, Laura Mey, Josha Bartsch
 */
public class LinkedList<T> implements IList<T>, Iterable<T> {
	private Node<T>head;
	private int count;

	/**
	 * Sub class representing list elements.
	 */
	private class Node<T> {
		public Node<T>next;
		public final T content;

		public Node(T element) {
			this.content = element;
			this.next = null;
		}
	}

	/**
	 * Initialize with HEAD ref null and count/size 0.
	 */
	public LinkedList() {
		this.head = null;
		this.count = 0;
	}
	/**
	 * @param int index at which the item is to be inserted
	 * @param T value that is to be inserted
	 */
	@Override
	public void insertAt(int index, T element) {
		if (index > count || index < 0)
			throw new ArrayIndexOutOfBoundsException();
		if (index == 0) {
			Node<T>new_node = new Node<>(element);
			new_node.next = head;
			head = new_node;
			count++;
			return;
		}
		Node<T> pointer = nodeAt(index-1);
		insertAfter(pointer, element);
	}

	/**
	 * @param pointer pointer to the ref node after which the element should be inserted.
	 * @param element value to be inserted.
	 */
	public void insertAfter(Node<T> pointer, T element) {
		Node<T> new_node = new Node<>(element);
		Node<T> after = pointer.next;
		new_node.next=after;
		pointer.next = new_node;
		count++;
	}

	/**
	 * To delete a node we remove any reference to it - gc will take care of it.
	 * @param int index of the node to be removed
	 */
	@Override
	public T removeAt(int index) {
		if (index >= count || index < 0)
			throw new ArrayIndexOutOfBoundsException();
		Node<T> node = nodeAt(index);
		if (index > 0) {
			Node<T> prev_node = nodeAt(index-1);
			prev_node.next = node.next;
		} else if (index == 0)
			head = node.next;
		count--;
		return node.content;
	}

	/**
	 * Return node value present at index
	 * 
	 * @param int index
	 */
	@Override
	public T getAt(int index) {
		if (index >= count || index < 0)
			throw new ArrayIndexOutOfBoundsException();
		Node<T> pointer = head;
		for (int i = 0; i < index; i++){
			pointer = pointer.next;
		}
		return pointer.content;
	}

	/**
	 * Get node object at index
	 * @param index
	 * @return node ref at index
	 */
	public Node<T> nodeAt(int index) {
		if (index > count || index < 0)
			throw new ArrayIndexOutOfBoundsException();
		Node<T> pointer = head;
		for (int i = 0; i < index; i++){
			pointer = pointer.next;
		}
		return pointer;
	}

	/**
	 * traverses through all nodes until it matches the first element.
	 * @param element value to be searched
	 */
	@Override
	public int search(T element) {
		boolean found = false;
		int i = 0;
		for (Node<T> pointer = head; pointer != null; pointer = pointer.next){
			if (pointer.content.equals(element)) {
				found = true;
				break;
			}
			i++;
		}
		if (!found)
			i = -1;
		return i;
	}

	/**
	 * Remove all references to nodes, set size to 0.
	 */
	@Override
	public void clear() {
		head = null;
		count = 0;
	}

	/**
	 * Expose size attribute.
	 */
	@Override
	public int getCount() {
		return count;
	}

	/**
	 * Implementation of Iterable
	 * @return Iterator for LinkedList
	 */
	@Override
	public Iterator<T> iterator() {
    	return new Iterator<T>() {
      		private Node<T> pointer = head;

      		public boolean hasNext() {
        		return pointer != null;
      		}

      		public T next() {
        		T ret = pointer.content;
        		pointer = pointer.next;
        		return ret;
      		}
    	};
  	}
}