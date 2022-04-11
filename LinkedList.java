
public class LinkedList implements IList {

	Node first;
	int elementCount;

	private class Node {
		int data;
		Node next;

		public Node(int data) {
			this.data = data;
		}
	}
	
	public LinkedList() {
		this.elementCount = 0;
		this.first = null;
	}
	
	
	public void insertAt(int pos, int i) {
		Node n = new Node(i);
	
		if (pos < 0 || pos > elementCount) {
			throw new ArrayIndexOutOfBoundsException();
		}
		if (pos == 0) {
				n.next = first;
				first = n;
				elementCount++;
		}
		else {
			int counter = 0;
			Node s;
			for (s = first; s.next!= null; s = s.next) {
				if (counter == pos) {
					break;
				}
				counter++;
			}
			Node temp = s.next;
			s.next = n;
			n.next = temp;
			elementCount++;
		}
	}
	
	
	public void removeAt(int pos) {
		if( pos < 0 || pos >= this.elementCount) {
			throw new ArrayIndexOutOfBoundsException();
		}
		
		if(pos == 0) {
			if(this.first != null) {
				this.first = first.next;
				this.elementCount--;
			}
		} else if (pos == this.elementCount-1) {
			int counter = 0;
			for(Node n = first; n != null; n = n.next ) {
				if(pos == counter+1) {
					n.next = null;
					this.elementCount--;
					break;
				}
				counter++;
			}
		} else {
		int counter = 0;
		for(Node n = first; n != null; n = n.next ) {
			if(pos == counter+1) {
				n.next = n.next.next;
				this.elementCount--;
				break;
			}
			counter++;
		}
		}
	}

	
	
	public int getAt(int pos) {
		if(pos < 0 || pos >= elementCount) {
			throw new ArrayIndexOutOfBoundsException();
		}
		if(this.first == null) {
			throw new ArrayIndexOutOfBoundsException("head is not defined");
		}
		Node iterator = this.first;
		for (int i = 0; i < pos ; i++){
			iterator = iterator.next;
		}
		return iterator.data;
	}
	
	/**
	 * @return Returns -1 if the Integer is not part of the list, else the position is returned
	 */
	public int search(int s) {
		int counter = 0;
		for(Node n = first; n != null; n = n.next) {
			if(n.data == s) {
				return counter;
			}
			counter++;
		}
		return -1;
		
	}
	
	public void clear() {
		this.first = null;
		this.elementCount = 0;
	}
	
	public int getCount() {
		return elementCount;
	}
}
