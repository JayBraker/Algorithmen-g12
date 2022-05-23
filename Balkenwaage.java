package ha9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

/**
 * HA 9
 * 
 * Class that provides solutions to a user who wants to measure a target weight
 * using a pan scale and specified measurement weights. *
 * 
 * @author Josha Bartsch, Christian Thelen, Laura Mey
 *
 */
public class Balkenwaage_basket {

	/**
	 * helper function for a recursive call to check if weights can be used to
	 * measure the target weight
	 * 
	 * @param weights       list of available measurement weights
	 * @param target        weight that should be measured
	 * @param result        array that saves used weights to return the result and check if it totals the target weight
	 */
	private static void canBeMeasured(ArrayList<Integer> weights, int target, int[] result) {

		if (Arrays.stream(result).sum() == target) {
			// break condition for the recursion - return results
			System.out.print("(");
			for (int i = 0; i < result.length; i++) {
				if (result[i] > 0) {
					System.out.print("+" + result[i]);
					if (i != result.length - 1) {
						System.out.print(",");
					}
				}
				if (result[i] < 0) {
					System.out.print(result[i]);
					if (i != result.length - 1) {
						System.out.print(",");
					}
				}
			}
			System.out.println(")");
			System.out.print("(");
			for (int i = 0; i < result.length; i++) {
				if (result[i] > 0) {
					System.out.print("-" + result[i]);
					if (i != result.length - 1) {
						System.out.print(",");
					}
				}
				if (result[i] < 0) {
					System.out.print("+" + result[i]*-1);
					if (i != result.length - 1) {
						System.out.print(",");
					}
				}
			}
			System.out.println(")");

			return;
		}

		if (!weights.isEmpty()) {
			//as long as there are any left, pick a weight to check
			int use_weight = weights.get(weights.size() - 1);
			int index = weights.size() - 1;
			weights.remove(weights.size() - 1);
			
			//put it left
			result[index] = use_weight;
			canBeMeasured(weights, target, result);

			//put it right
			result[index] = -use_weight;
			canBeMeasured(weights, target, result);

			//decide not to use it
			result[index] = 0;
			canBeMeasured(weights, target, result);

			// backtrack
			weights.add(use_weight);

		}
	}

	/**
	 * call the recursive function with an empty result array to check if target can be weighted
	 * 
	 * @param weights list of available measurement weights
	 * @param target  weight that should be measured
	 */
	public static void canBeMeasured(ArrayList<Integer> weights, int target) {
		int[] result = new int[weights.size()];
		canBeMeasured(weights, target, result);
	}

	/**
	 * Main test method that provides measurement weights and asks user for input
	 * for the target weight and returns solutions
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Integer> weights = new ArrayList<>();
		weights.add(20);
		weights.add(8);
		weights.add(3);
		weights.add(1);

		System.out.println("Please enter an integer value for the target weight: ");
		Scanner sc = new Scanner(System.in);
		int target = sc.nextInt();
		sc.close();
		System.out.println("You have the following weights to perform the measurement: " + weights);
		System.out.println("The solutions are: ");

		canBeMeasured(weights, target);

	}

}
