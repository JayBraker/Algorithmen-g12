package ha8;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Damenproblem {

	public static void damenProblem(int brettgroesse) {
		int[] brett_mit_damen = new int[brettgroesse];
		if (!damenProblem_rekursion(brett_mit_damen, 0)) {
			System.out.println("no solution found");
		}
		else {
			System.out.println(Arrays.toString(brett_mit_damen));
		}

//		boolean[][] brett = new boolean[brettgroesse][brettgroesse];
//
//		if (damenProblem_rekursion(brettgroesse, 0, brett)) {
//			System.out.println("found solution");
//			int[] solution = new int[brettgroesse];
//			for (int i = 0; i < brettgroesse; i++) {
//				for (int j = 0; j < brettgroesse; j++) {
//					solution[i] = j;
//				}
//			}
//			System.out.println(Arrays.toString(solution));
//		}
//
//		throw new ArithmeticException("no solution found");

	}

	/**
	 * Hilfsfunktion für die Rekursion
	 * 
	 * @param brett
	 * @param col
	 */
	private static boolean damenProblem_rekursion(int[] brett, int col) {
		if (col >= brett.length) {
			System.out.println("here");
			System.out.println(Arrays.toString(brett));
			return true;
		}
		for (int row = 1; row < brett.length + 1; row++) {
			System.out.println(row);
			System.out.println(Arrays.toString(brett));
			if (checkDamenposition(brett, row, col)) {
				brett[col] = row;
				System.out.println(Arrays.toString(brett));
				if (damenProblem_rekursion(brett, col + 1)) {
					return true;
				}
				brett[col] = 0;
			}
		}
		return false;
	}

//		if (spalte == brettgroesse) {
//			return true;
//		}
//
//		for (int i = 0; i < brettgroesse; i++) {
//			if (checkDamenposition(spalte)) {
//				brett[i][spalte] = true;
//				if (damenProblem_rekursion(brettgroesse, spalte + 1, brett)) {
//					return true;
//				} else {
//					brett[i][spalte] = false;
//					;
//				}
//			}
//
//		}
//		return false;

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
		damenpositionen[col] = row;
		boolean isOK = true;

		for (int i = 0; i < damenpositionen.length; i++) {
			for (int j = 0; j < i; j++) {
				if (i != j && damenpositionen[i] != 0 && damenpositionen[j] != 0) {
					// check for duplicate rows
					if (damenpositionen[i] == damenpositionen[j] && i != 0) {
						System.out.println("duplicate");
						return false;
					}
					// check for diagonals
					if (Math.abs(damenpositionen[i] - damenpositionen[j]) == Math.abs(i - j)) {
						System.out.println("test");
						return false;
					}
				}
			}

		}

		return isOK;
	}

	public static void main(String[] args) {
		damenProblem(6);
	}

}
