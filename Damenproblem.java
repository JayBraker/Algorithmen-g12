package ha08;

public class Damenproblem {

	public static void damenProblem(int brettgroesse) {
		if (brettgroesse < 1 || brettgroesse == 2 || brettgroesse == 3) {
			System.out.print("Für diese Brettgroesse gibt es keine passende Lösung");
		}
		int[] queenpos = new int[brettgroesse];
		damenProblem(queenpos, 0);
	}

	private static void damenProblem(int[] queenpos, int column) {
		if (column >= queenpos.length) {
			for (int i = 0; i < queenpos.length; i++) {
				System.out.print((queenpos[i]) + 1 + " ");
			}
			System.out.println();
		}

		for (int row = 0; row < queenpos.length; row++) {
			if (isSafe(queenpos, row, column)) {
				queenpos[column] = row;
				damenProblem(queenpos, column + 1);
			}
		}
	}

	private static boolean isSafe(int[] queenpos, int row, int column) {
		boolean isSafe = true;
		for (int i = 0; (i < column && isSafe); i++) {
			if (queenpos[i] == row) {
				isSafe = false;
			}
			if (Math.abs(queenpos[i] - row) == Math.abs(i - column)) {
				isSafe = false;
			}
		}
		return isSafe;
	}

	public static void main(String[] args) {
		damenProblem(4);
	}

}
