/** Ein binaerer Suchbaum mit ganzen Zahlen als Datensatz:
  * Vorlage fuer die A1 von algo-pr05.
  * Als Operationen stehen `contains' und `insert' zur Verfuegung
  */
	public class BinarySearchTree {

		/** Die Knotenklasse als statische innere Klasse. */
		public static class TreeNode {
			private int value;
			private TreeNode left;
			private TreeNode right;
			private int branchSum;
			private int branchSize;
	
			public TreeNode(int value) {
				this.value = value;
				this.branchSum = value;
				this.branchSize = 1;
			}
	
			public String toString() {
				return this.value + " ";
			}
	
			public int getValue() {
				return this.value;
			}
	
			public TreeNode getLeft() {
				return this.left;
			}
	
			public TreeNode getRight() {
				return this.right;
			}
	
			public int getBranchSum() {
				return this.branchSum;
			}
	
			public int getBranchSize() {
				return this.branchSize;
			}
	
			public void setValue(int value) {
				this.value = value;
			}
	
			public void setLeft(TreeNode node) {
				this.left = node;
			}
	
			public void setRight(TreeNode node) {
				this.right= node;
			}
	
			public void setBranchSum(int data) {
				this.branchSum = data;
			}
	
			public void setBranchSize(int data) {
				this.branchSize = data;
			}
		}
	
		/** Baumwurzel */
		protected TreeNode root;
	
		/**
		 * Herausfinden, ob ein gewisser Datensatz schon im binaeren Suchbaum enthalten ist.
		 *
		 * @param   data  zu suchender Datensatz
		 * @return        true: Datensatz ist vorhanden; false: Datensatz ist nicht vorhanden.
		 */
		public boolean contains(int data) {
			TreeNode temp = root;
			while(temp != null) {
				if (temp.getValue() == data) {
					return true;
				}
				if (temp.getValue() > data) {
					temp = temp.getLeft();
				} else {
					temp = temp.getRight();
				}
			}
			return false;
		}
	
		/**
		 * Einen neuen Datensatz in den binaeren Suchbaum einfuegen.
		 *
		 * @param   data  einzufuegender Datensatz
		 * @return        true: Datensatz wurde eingefuegt; false: Datensatz war schon vorhanden.
		 */
		public boolean insert(int data) {
			if (root == null) {
				root = new TreeNode(data);
				return true;
			}
			if (!contains(data)) {
				TreeNode temp = root;
				while(temp.getValue() != data) {
					temp.setBranchSize(temp.getBranchSize() + 1);
					temp.setBranchSum(temp.getBranchSum() + data);
					if (temp.getValue() > data) {
						if(temp.getLeft() == null) {
							temp.setLeft(new TreeNode(data));
							return true;
						}
						temp = temp.getLeft();
					} else {
						if (temp.getRight() == null) {
							temp.setRight(new TreeNode(data));
							return true;
						}
						temp = temp.getRight();
					}
				}
			}
			return false;
		}
	
		public static void main(String[] args) {
			BinarySearchTree tree = new BinarySearchTree();
			for (int i = 0; i < 20; i++) {
				int x = (int) (Math.random() * 50);
				System.out.println(x);
				tree.insert(x);
			}
			for (int i = 0; i < 50; i++) {
				System.out.println(i + ": " + tree.contains(i));
			}
		}
	}
	
	