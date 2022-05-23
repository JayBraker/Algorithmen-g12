import java.util.Scanner;
import java.util.ArrayList;

public class Waage {
    private final static int[] weights = {1,3,8,20};

    public static void getWeightCombinations(ArrayList<String> basket, int weight) {
        getWeightCombinations(basket, 0, weight, new ArrayList<>(), 0);
    }

    public static void getWeightCombinations(ArrayList<String> basket, int cWeight, int weight, ArrayList<String> comb, int wIndex) {
        if (cWeight == weight) {
            basket.add(String.join(" ", comb));
            return;
        } else if (wIndex < weights.length) {
            ArrayList<String> tList = new ArrayList<>(comb);
            tList.add((comb.size() > 0 ? "+": "") +String.valueOf(weights[wIndex]));
            getWeightCombinations(basket, cWeight+weights[wIndex], weight, tList, wIndex+1);
            
            tList.remove(tList.size() - 1);
            getWeightCombinations(basket, cWeight, weight, tList, wIndex+1);
            
            tList.add(String.valueOf(-1*weights[wIndex]));
            getWeightCombinations(basket, cWeight-weights[wIndex], weight, tList, wIndex+1);
        } else 
            return;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Gewicht des Artikels: ");
        int weight = sc.nextInt();
        sc.close();
        System.out.println();
        ArrayList<String> combinations = new ArrayList<>();
        getWeightCombinations(combinations, weight);

        System.out.printf("Es gibt %d mögliche Gewichts-%s:\n", combinations.size(), combinations.size() > 1 ? "Kombinationen" : "Kombination");
        for (String s : combinations)
            System.out.println(String.format("%s = %d", s, weight));
    }
}
