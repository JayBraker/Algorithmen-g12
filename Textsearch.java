import java.util.ArrayList;
import java.util.Arrays;

/**
 * Algorithmen & Datenstrukturen HA13
 * 
 * This class provides a nondeterministic finite automate that can check if a
 * provided String can be described by a regular expression that is given as a
 * list of edges of a graph.
 *
 * @author Laura Mey, Christian Thelen, Josha Bartsch
 */
public class NFA {
	private ArrayList<String>[] adj;
	private int vertex_count;
	private int target;

	/**
	 * Constructor that takes an edge list as described in the assignment and
	 * returns an adjacency list
	 * 
	 * @param x String of an edge list
	 */
	public NFA(String x) {
		char[] input = x.toCharArray();

		this.vertex_count = (int) input[0] - 48;
		//System.out.println("vertexcount: " + this.vertex_count);

		this.adj = new ArrayList[vertex_count + 1];
		for (int i = 0; i < vertex_count + 1; i++) {
			this.adj[i] = new ArrayList<String>();
		}

		this.target = (int) input[2] - 48;
		//System.out.println("target: " + target);

		for (int i = 4; i < input.length; i += 6) {
			//System.out.println(i);
			int currentnode = (int) input[i] - 48;
			//System.out.println(currentnode);
			this.adj[currentnode].add("" + input[i + 4] + ((int) input[i + 2] - 48));
			//System.out.println(Arrays.toString(this.adj));
		}
	}

	/**
	 * Tests if String s is matched by the regular expression
	 * 
	 * @param s String s to be checked
	 * @return boolean true iff s is equivalent to the regular expression
	 */
	public boolean testString(String s) {
		boolean[] nodes_occupied = new boolean[vertex_count + 1];
		nodes_occupied[1] = true; // Startknoten
		boolean regex = false;
		char[] input = s.toCharArray();

		for (char c : input) {
			boolean[] nodes_occupied_old = nodes_occupied.clone();
			nodes_occupied = new boolean[vertex_count + 1];
			for (int i = 0; i < vertex_count + 1; i++) {
				if (nodes_occupied_old[i] == true) {
					for (int j = 0; j < adj[i].size(); j++) {
						if (adj[i].get(j).startsWith("" + c)) {
							int nextnode = (int) adj[i].get(j).charAt(1) - 48;
							nodes_occupied[nextnode] = true;
						}
					}
				}
			}
			//System.out.println(Arrays.toString(nodes_occupied));
			if (areAllFalse(nodes_occupied)) {
				regex = false;
				break;
			}

		}
		if (nodes_occupied[target] == true) {
			regex = true;
		}
		return regex;

	}

	/**
	 * helper function to check if all entries in a boolean array are false
	 * 
	 * @param array boolean array to be checked
	 * @return true iff all entries are false
	 */
	public static boolean areAllFalse(boolean[] array) {
		for (boolean b: array) {
			if (b == true) {
				return false;
			}
		}
		return true;
	}

	/**
	 * test method (copied from assignment)
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		NFA nfa_test = new NFA("3,3,1,2,a,1,3,a,2,2,a,2,2,b,2,3,a");
		System.out.println(nfa_test.testString("abba")); // true
		System.out.println(nfa_test.testString("a")); // true
		System.out.println(nfa_test.testString("ab")); // false
	}

}
