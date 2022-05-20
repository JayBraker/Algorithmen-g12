package ha9;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * HA 9 
 * 
 * Class that provides solutions to a user who wants to measure a target weight using a pan scale and specified measurement weights. * 
 * 
 * @author Josha Bartsch, Christian Thelen, Laura Mey
 *
 */
public class Balkenwaage3 {

	/**
	 * helper function for a recursive call to check if weights can be used to measure the target weight
	 * 
	 * @param weights list of available measurement weights
	 * @param target weight that should be measured
	 * @param currentweight weight that will increase during recursion to check if target has been matched
	 * @param result list that saves used weights to return the result
	 * @return true iff the target can be measured
	 */
	public static boolean canBeMeasured(ArrayList<Integer> weights, int target, int left, int right, ArrayList<Integer> result) {
		if (left-right == target) {
			System.out.println(result);
			ArrayList<Integer> result_symmetric = new ArrayList<>();
			for (int i : result) {
				result_symmetric.add(i*(-1));
			}
			System.out.println(result_symmetric);
			return true;
		}
		if (weights.isEmpty()) {
			return false;
		}
		else {
			int use_weight = weights.get(weights.size()-1);
			weights.remove(weights.size()-1);
			
			left += use_weight;
			if(canBeMeasured(weights, target, left, right, result)) {
				result.add(use_weight);
			}; 
			left -= use_weight;
			
			right += use_weight;
			if(canBeMeasured(weights, target, left, right, result)) {
				result.add(use_weight);
			}; 
			right -= use_weight;
			
			canBeMeasured(weights, target, left, right, result);
		}
		return false;
	}
	
	/**
	 * prints the result of the call of the recursive function with start weight at 0 and empty start result list
	 * 
	 * @param weights list of available measurement weights
	 * @param target weight that should be measured
	 */
	public static void canBeMeasured(ArrayList<Integer> weights, int target) {
		ArrayList<Integer> result = new ArrayList<>();
		int left = 0;
		int right = 0;
		canBeMeasured(weights, target, left, right, result);
	}

	/**
	 * Main (test) method that provides measurement weights and asks user for input for the target weight
	 * and returns solutions
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Integer> weights = new ArrayList<>();
		weights.add(20);
		weights.add(8);
		weights.add(3);
		weights.add(1);
//		
////		System.out.println("Please enter an integer value for the target weight: ");
////		Scanner sc = new Scanner(System.in);
////		int target = sc.nextInt();
////		sc.close();
////		System.out.println("You have the following weights to perform the measurement: " + weights);
////		System.out.println("Here are the solutions (if there are any): ");
////		
		int target = 16; //test case
		
		canBeMeasured(weights, target);
//		
//		ArrayList<Integer> results = new ArrayList<>();
//		System.out.println(canBeMeasured(weights, 16, 0, results));
//		
		
	}

}