public class Test {
	public static void testList() {
		LinkedList<String> list = new LinkedList<>();
		assert list.getCount() == 0;
		list.insertAt(0, "Josha");
		list.insertAt(1, "Christian");
		list.insertAt(0, "Laura");
		assert list.getCount() == 3;
		for (String name : list) {System.out.println(name);}
		list.removeAt(2);
		assert list.getCount() == 2;
		System.out.println(list.search("Christian"));
	}
	public static void main(String[] args) {
		testList();
	}
}
