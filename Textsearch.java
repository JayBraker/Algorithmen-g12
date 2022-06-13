import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
//import java.util.regex.PatternSyntaxException;

//Boyer Moore String Matching Algorithm Bad Character Heuristic (geeksforgeeks)

public class Textsearch {

	private static int max(int a, int b) {
		return (a > b) ? a : b;
	}

	private static void badCharHeuristic(char[] str, int size, int badchar[]) {
		for (int i = 0; i < 256; i++) {// 256 = number of chars
			badchar[i] = -1;
		}
		for (int i = 0; i < size; i++) {
			badchar[(int) str[i]] = i;
		}
	}
//	
//	public static ArrayList<Integer> textSearch(String text, String pattern){
//		ArrayList<Integer> result = new ArrayList<>();
//		char[] txt = text.toCharArray();
//		char[] pat = pattern.toCharArray();
//		int badchar[] = new int[256];
//		badCharHeuristic(pat, pat.length, badchar);
//		int shift = 0;
//		while (shift <= (txt.length - pat.length)){
//			int j = pat.length - 1;
//			while (j >= 0 && (pat[j] == txt[shift+j] || pat[j] == '.')) {
//				j--;
//			}
//			if (j < 0) {
//				//System.out.println("Occurance: "+ shift);
//				result.add(shift);
//				shift += (shift+pat.length < txt.length)? pat.length-badchar[txt[shift+pat.length]] : 1;
//			}
//			else {
//				shift += max(1, j - badchar[txt[shift+j]]);
//			}
//		}
//		return result;
//	}

	public static ArrayList<Integer> textSearch(String text, String pattern) {
		ArrayList<Integer> result = new ArrayList<>();
		char[] txt = text.toCharArray();
		char[] pat = pattern.toCharArray();
		int badchar[] = new int[256];
		badCharHeuristic(pat, pat.length, badchar);
		int shift = 0;
		while (shift <= (txt.length - pat.length)) {
			int j = 0;
			while (j < pat.length && (pat[j] == txt[shift + j] || pat[j] == '.' || pat[j] == '[' || pat[j] == '\\')) {

				// Sonderfälle

				if (pat[j] == '\\') {
					if (pat[j + 1] == txt[shift + j]) {
						j = j++;
						continue;
					} else {
						break;
					}

				}

				else if (pat[j] == '.') {
					j++;
					continue;
				}

				else if (pat[j] == '[') {
					HashSet<Character> klammer = new HashSet<>();
					int i = 0;
					while (pat[j+i] != ']') {
						klammer.add(pat[j+i]);
						i++;
					}
					if (i == pat.length - 1) {
						// throw new PatternSyntaxException("Unresolved brackets.");
						throw new IllegalArgumentException("Unresolved brackets.");
					}
					if (klammer.contains(txt[shift + j])) {
						j = j++;
						continue;
					}

				}

				j++;
			}
			if (j >= pat.length) {
				// System.out.println("Occurance: "+ shift);
				result.add(shift);
				shift += (shift + pat.length < txt.length) ? pat.length - badchar[txt[shift + pat.length]] : 1;
			} else {
				shift += max(1, j - badchar[txt[shift + j]]);
			}
		}
		return result;
	}

	/**
	 * Testmethode
	 */
	public static void main(String[] args) {
		System.out.println(textSearch("abcabcdababdc.", "ab")); // 0, 3, 7, 9
		System.out.println(textSearch("abcabcdababdc.", "c.")); // 2, 5, 12
		System.out.println(textSearch("abcabcdababdc.", "c\\.")); // 12
		System.out.println(textSearch("abcabcdababdc.", "b[cd]")); // 1,4,10
		System.out.println(textSearch("abcabcdababdc.", "a....c")); // 0,7
		System.out.println(textSearch("a[aababa][ab]a", "a[ab]a")); // 3,5
		System.out.println(textSearch("a[aababa][ab]a", "a.\\[a")); // 7
	}

}
