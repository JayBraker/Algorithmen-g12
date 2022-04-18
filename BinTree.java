/**
 * Algorithmen Hausaufgaben 4
 * Implementation of a binary searchtree.
 * 
 * @author Josha Bartsch, Laura Mey, Chrisitan Thelen
 */
public class BinTree {
	/**
	 * Subclass representing a binary tree node.
	 */
	private class Node {
		int data;
		Node right;
		Node left;

		Node(int data) {
			this.data = data;
		}
		
		public String toString() {
			return String.valueOf(data);
		}
	}

	private Node root;

	/**
	 * Insert an integer into the tree beginning at the root.
	 * 
	 * @param x
	 */
	public void insert(int x) {
		Node node = new Node(x);
		if (root == null)
			root = node;
		else
			insert(root, node);		
	}

	/**
	 * Insert a note into a given subtree.
	 * Throws an ArithmeticException if the value is already present in the tree.
	 *
	 * @param node root to start the insertion at
	 * @param newNode node to be inserted
	 */
	public void insert(Node node, Node newNode) {
		if (newNode.data == node.data) {
			throw new ArithmeticException("Value is already present in tree.");
		} else if (newNode.data > node.data) {
			if (node.right != null) {
				insert(node.right, newNode);
				return;
			}
			node.right = newNode;
		} else {
			if (node.left != null) {
				insert(node.left, newNode);
				return;
			}
			node.left = newNode;
		}
	}

	/**
	 * Search for a node beginning at the provided node.
	 * 
	 * @param r "root" of this search
	 * @param x int value to find the parent to
	 * @return node containing x or null if x is not in the tree
	 */
	public Node getNode(Node r, int x) {
		if (r == null)
			return null;
		if (r.data == x)
			return r;
		else if (r.left != null && r.data > x)
			return getNode(r.left, x);
		else if (r.right !=  null && r.data < x)
			return getNode(r.right, x);
		return null;
	}

	/**
	 * Search for a node beginning at the trees root.
	 * 
	 * @param x int value to find the parent to
	 * @return node 
	 */
	public Node getNode(int x) {
		return getNode(root, x);
	}

	/**
	 * Search for a nodes parent beginning at the provided node.
	 * 
	 * @param r "root" of this search
	 * @param x int value to find the parent to
	 * @return parent or null if not present (root contains x or x not present in tree)
	 */
	public Node getParentNode(Node r, int x) {
		if (r.data == x)
			return null;
		else if (r.left != null && r.data > x) {
			if (r.left.data == x)
				return r;
			return getParentNode(r.left, x);
		} else if (r.right !=  null && r.data < x) {
			if (r.right.data == x)
				return r;
			return getParentNode(r.right, x);
		}
		return null;
	}

	/**
	 * Search for a nodes parent beginning at the root.
	 * 
	 * @param x int value to find the parent to
	 * @return node 
	 */
	public Node getParentNode(int x) {
		return getParentNode(root, x);
	}

	/**
	 * Clears the binary search tree by deleting its root value
	 */
	public void clear() {
		root = null;
	}

	/**
	 * Looks up the node containing the value x and its parents.
	 * Inserts all child nodes after removing the node in question from its parent.
	 * 
	 * @param x    value of the node to be removed
	 */
	public void remove(int x) {
		Node parentNode = getParentNode(x);
		Node node;
		if (parentNode == null)
			node = getNode(x);
		else 
			node = getNode(parentNode, x);

		if (node == null)
			throw new ArithmeticException();
		if (parentNode != null) {
			if (parentNode.left == node)
				parentNode.left = null;
			else
				parentNode.right = null;
			if (node.right != null)
				insert(parentNode, node.right);
			if (node.left != null)
				insert(parentNode, node.left);
		} else {
			root = node.right;
			insert(root, node.left);
		}
	}

	public static void main(String[] args) {
		BinTree tree = new BinTree();
		tree.insert(20);
		tree.insert(10);
		tree.insert(30);
		tree.insert(50);
		int[] testcases = { 30, 35, 50 };
		for (int testcase : testcases) {
			Node node = tree.getNode(testcase);
			if (node == null) {
				System.out.println("Knoten " + testcase + " nicht gefunden.");
			} else {
				System.out.println("Knoten " + testcase + " gefunden: " + node.data);
			}
		}
		tree.remove(30);
		System.out.println("Knoten geloescht: 30");
		System.out.println("Elternknoten von 50: " + tree.getParentNode(50).data);// 20 }
	}
}
