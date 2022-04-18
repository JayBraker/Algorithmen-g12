public class BinTree {
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

	public void insert(int x) {
		Node node = new Node(x);
		if (root == null)
			root = node;
		else
			insertAfter(root, node);		
	}

	public void insertAfter(Node node, Node newNode) {
		if (newNode.data == node.data) {
			throw new ArithmeticException("Value is already present in tree.");
		} else if (newNode.data > node.data) {
			if (node.right != null) {
				insertAfter(node.right, newNode);
				return;
			}
			node.right = newNode;
		} else {
			if (node.left != null) {
				insertAfter(node.left, newNode);
				return;
			}
			node.left = newNode;
		}
	}

	public Node getNode(Node r, int x) {
		if (r.data == x)
			return r;
		else if (r.left != null && r.data > x)
			return getNode(r.left, x);
		else if (r.right !=  null && r.data < x)
			return getNode(r.right, x);
		return null;
	}

	public Node getNode(int x) {
		return getNode(root, x);
	}

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

	public Node getParentNode(int x) {
		return getParentNode(root, x);
	}

	public void clear() {
		root = null;
	}

	public void remove(int x) {
		Node parentNode = getParentNode(x);
		Node node = getNode(x);

		if (node == null)
			throw new ArithmeticException();
		if (parentNode != null) {
			if (parentNode.left == node)
				parentNode.left = null;
			else
				parentNode.right = null;
			if (node.right != null)
				insertAfter(parentNode, node.right);
			if (node.left != null)
				insertAfter(parentNode, node.left);
		} else {
			root = node.right;
			insertAfter(root, node.left);
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
