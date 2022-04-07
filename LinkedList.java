import java.util.Iterator;

public class LinkedList<T> implements IList<T>, Iterable<T> {
	private Node<T>head;
	private int count;

	private class Node<T> {
		public Node<T>next;
		public final T content;

		public Node(T element) {
			this.content = element;
			this.next = null;
		}
	}

	public LinkedList() {
		this.head = null;
		this.count = 0;
	}

	@Override
	public void insertAt(int index, T element) {
		if (index > count+1)
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

	public void insertAfter(Node<T> pointer, T element) {
		Node<T> new_node = new Node<>(element);
		Node<T> after = pointer.next;
		new_node.next=after;
		pointer.next = new_node;
		count++;
	}

	@Override
	public T removeAt(int index) {
		if (index >= count)
			throw new ArrayIndexOutOfBoundsException();
		Node<T> node = nodeAt(index);
		if (index > 0) {
			Node<T> prev_node = nodeAt(index-1);
			prev_node.next = node.next;
		} else if (index == 0)
			head = null;
		count--;
		return node.content;
	}

	@Override
	public T getAt(int index) {
		if (index >= count)
			throw new ArrayIndexOutOfBoundsException();
		Node<T> pointer = head;
		for (int i = 0; i < index; i++){
			pointer = pointer.next;
		}
		return pointer.content;
	}

	public Node<T> nodeAt(int index) {
		if (index > count)
			throw new ArrayIndexOutOfBoundsException();
		Node<T> pointer = head;
		for (int i = 0; i < index; i++){
			pointer = pointer.next;
		}
		return pointer;
	}

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

	@Override
	public void clear() {
		head = null;
		count = 0;
	}

	@Override
	public int getCount() {
		return count;
	}

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