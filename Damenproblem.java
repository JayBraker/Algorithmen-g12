package ha8;

import java.util.Arrays;

public class Damenproblem {

	public static void damenProblem(int brettgroesse) {
		int[] brett = new int[brettgroesse];
		damenProblem_rekursion(brett, 0);
	}

	/**
	 * Hilfsfunktion für die Rekursion
	 * 
	 * @param brett
	 * @param col
	 */
	private static void damenProblem_rekursion(int[] brett, int col) {
		if (col >= brett.length) {
			//System.out.println("here");
			System.out.println(Arrays.toString(brett));
			//return true;
		}
		for (int row = 0; row < brett.length; row++) {
			//System.out.println(row);
			//System.out.println(Arrays.toString(brett));
			if (checkDamenposition(brett, row, col)) {
				brett[col] = row;
				//System.out.println(Arrays.toString(brett));
				damenProblem_rekursion(brett, col + 1);
					//return true;
				
				//brett[col] = 0;
			}
		}
		//return false;
	}

	/**
	 * checks if given solution works so that no queen can beat each other (neither
	 * vertically/horizontally nor diagonally)
	 * 
	 * @param damenpositionen int array of row number where queens are placed in
	 *                        each column
	 * @return boolean true iff the solution works, false if queens can beat each
	 *         other
	 */
	public static boolean checkDamenposition(int[] damenpositionen, int row, int col) {
		//damenpositionen[col] = row;
		boolean isOK = true;

		for (int i = 0; i < col ; i++) { //damenpositionen.length
			//for (int j = 0; j < i; j++) {
				//if (i != j && damenpositionen[i] != 0 && damenpositionen[j] != 0) {
					// check for duplicate rows
				if (damenpositionen[i] == row) { //damenpositionen[j] && i != 0) {
					//System.out.println("duplicate");
					isOK = false;
					break;
				}
				// check for diagonals
				if (Math.abs(damenpositionen[i] - row) == Math.abs(row-col)) { //[j]) == Math.abs(i - j)) {
					//System.out.println("test");
					isOK = false;
					break;
				}
				//}
			//}

		}

		return isOK;
	}

	public static void main(String[] args) {
		damenProblem(6);
	}

}
