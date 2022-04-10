/**
 * Algorithmen HA3
 * 
 * Interface with different list operations.
 * Implemented by LinkedList class.
 * 
 * @author Christian Thelen, Joshua Bartsch, Laura Mey
 *
 */
public interface IList {
	
	//insert value at position
	public void insertAt(int position, int value);
	
	//remove value at position
	public void removeAt(int position);
	
	//return value at position
	public int getAt(int position);
	
	//search for value and return its position
	public int search(int value);
	
	//clear the list to make it empty
	public void clear();
	
	//return the number of items in the list
	public int getCount();
}
