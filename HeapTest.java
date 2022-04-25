package ha05;
public class HeapTest {

	public static void main(String[] args) {
		Heap x = new Heap();
		System.out.println(x.toString());
		x.add(1);
		System.out.println(x.toString());
		x.add(6);
		System.out.println(x.toString());
		x.add(8);
		System.out.println(x.toString());
		x.add(18);
		System.out.println(x.toString());
		x.add(23);
		System.out.println(x.toString());
		x.add(5);
		System.out.println(x.toString());
		x.add(17);
		System.out.println(x.toString());
		x.add(20);
		System.out.println(x.toString());
		x.add(26);
		System.out.println(x.toString());
		x.add(21);
		System.out.println(x.toString());
		x.add(9);
		System.out.println(x.toString());
		for(int i = 1; i <= 11; i++) {
			System.out.println(x.getMax());
			System.out.println(x.toString());	
		}
		

	}

}
