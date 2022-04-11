import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Algorithmen HA3
 * 
 * Class that tests the class LinkedList with its different operations as 
 * specified in the task description with JUnit Tests. Initalises an example
 * list to test the methods with.

 * @author Christian Thelen, Joshua Bartsch, Laura Mey
 *
 */
class testList {
	LinkedList list;
	
	@BeforeEach
	public void setUp() {
		this.list = new LinkedList();
		list.insertAt(0, 1);
		list.insertAt(1, 2);
		list.insertAt(2, 3);
		list.insertAt(3, 4);
	}

	@Test
	public void testInsertAt() {
		list.insertAt(2, 5);
		assertEquals(5, list.getCount(), "testInsertCount");
		assertEquals(5, list.getAt(2), "testInsert");
	}
	
	@Test
	public void testRemove() {
		list.removeAt(1);
		assertEquals(3, list.getAt(1), "testRemove");
		assertEquals(3, list.getCount(), "testRemoveCount");
	}
	
	@Test
	public void testRemoveFirst() {
		list.removeAt(0);
		assertEquals(2, list.getAt(0), "testRemoveFirst");
		assertEquals(3, list.getCount(), "testRemoveFirstCount");
	}
	
	@Test
	public void testGet() {
		assertEquals(1, list.getAt(0), "testGetFirst");
		assertEquals(4, list.getAt(3), "testGetLast");
		assertEquals(2, list.getAt(1), "testGetCenter");
	}
	
	@Test
	public void testSearch() {
		assertEquals(3, list.search(4), "testSearch");
	}
	
	@Test 
	public void testClear() {
		list.clear();	
		assertEquals(0, list.getCount(), "testClear");
	}
	
	@Test
	public void testGetCount() {
		assertEquals(4, list.getCount(), "testCount");
	}
	
	@Test
	public void testIndexException() {
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			list.insertAt(10, 7);
		}, "InsertException");
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			list.removeAt(10);
		}, "RemoveException");
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			list.getAt(10);
		}, "GetException");
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			list.getAt(-2);
		}, "GetExceptionNegativeNumber");
	}
	


	

}
