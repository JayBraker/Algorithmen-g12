import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.PatternSyntaxException;

/**
 * @author Laura Mey, Christian Thelen, Josha Bartsch
 */
public class PatternSearch {
	
	/**
	 * Given any pattern, produces a nested data structure later on used by testSearch.
	 * Patterns may consist of literals (almost any AASCII Character), exceptions are the literals '[', ']', '.','\':
	 *  * Squared brackets present a sequence of possible literals the are valid for the next char.
	 *  * The dot is a wildcard and matches any valid Character
	 *  * The Backslash may be used to represent dot, square-bracket or backslash-literals ("\\" or "\.")
	 * 
	 * @param pattern String pattern, not PCRE compliant
	 * @return ArrayList, each entry is a hashset of allowed char literals at that index. Empty Strings represent the wildcard (dot).
	 */
	private static ArrayList<HashSet<String>> buildPatternList(String pattern) {
		ArrayList<HashSet<String>> sequence = new ArrayList<>();
		char[] letters = pattern.toCharArray();

		for (int i = 0; i < letters.length;) {
			HashSet<String> chars = new HashSet<>();
			/*
			 * When iterating over each char of the pattern, a sequece is either [ab], a single valid char
			 * or an escaped char. Each time a valid sequence was found it is wrapped in a HashSet (for
			 * long multi-char sequences this might improve runtime, duplicate entries will be rightfully
			 * ignored).
			 * 
			 * Special character '.' is substituted with an empty String as any other char should remain
			 * usable.
			 */
			if (letters[i] == '\\') {
				// Incase of a single backslash, the following character is to be
				i++;
				chars.add(String.valueOf(letters[i]));
				// Begin of sequence.
			} else if (letters[i] == '[') {
				i++;
				// In doubt iterate to the end of pattern.
				while (i < letters.length) {
					if (letters[i] == ']')
						break;
					chars.add(String.valueOf(letters[i]));
					i++;
				}
				// If the last char of the pattern was read and it was not ']', i equals letters.length and
				// the sequence was not terminated properly!
				if (i == letters.length)
					throw new PatternSyntaxException("Die Zeichenfolge wurde nicht terminiert.", pattern, i);
			} else if (letters[i] == '.') {
				chars.add("");
			} else {
				chars.add(String.valueOf(String.valueOf(letters[i])));
			}
			sequence.add(chars);
			i++;
		}
		return sequence;
	}

	/**
	 * Like buildPatternList but build a single HashSet which contains any char matched by the pattern exactly once. 
	 * @param pattern
	 * @return
	 */
	private static HashSet<String> buildHashSet(String pattern) {
		HashSet<String> unis = new HashSet<>();
		char[] letters = pattern.toCharArray();
		for (int i = 0; i < letters.length;) {
			if (letters[i] == '\\') {
				i++;
				unis.add(String.valueOf(letters[i]));
			} else if (letters[i] == '.') {
				unis.add(""); // Save Wildcard as an empty String
			} else if (letters[i] == '[' || letters[i] == ']') {
				i++; 
				continue; // Just skip brackets.
			} else {
				unis.add(String.valueOf(letters[i]));
			}
			i++;
		}
		return unis;
	}

	/**
	 * Apply the bad character heuristic from Boyer-Moore's algorithm to find all occuring matches on the given text.
	 * Collect the start index of every match in the basket.
	 * 
	 * @param text Text match the pattern on
	 * @param pattern Pattern as prepared by buildPatternList
	 * @param basket Basket collecting all indices at which a match began
	 * @param uniChars HashSet containing all chars from the pattern
	 * @param skipTable Skip table (see Boyer-Moore-Algorithm "bad character heuristic")
	 * @param offset Offset at which to begin the matching
	 */
	private static void findAllOccurrances(String text, ArrayList<HashSet<String>> pattern,
			ArrayList<Integer> basket, HashSet<String> uniChars, HashMap<String, Integer> skipTable,
			int offset) {
		int patternLen = pattern.size();
		if (offset + patternLen > text.length())
			return;
		int i = patternLen - 1;

		/*
		 * i iterates the pattern indices backwards
		 * Compare the text char at position offset+i with all valid chars in the pattern at index i
		 */
		for (; i >= 0; i--) {
			String n = String.valueOf(text.charAt(i + offset));
			if (pattern.get(i).contains("")) { // Just decrement i if the pattern gives a wildcard
				continue;
			} else if (!uniChars.contains(n)) { // If the text char is not part of the pattern and i is the last index of the patter, move offset += length of the pattern.
																					// If i is not the last index, move the offset by length of the pattern - i
				findAllOccurrances(text, pattern, basket, uniChars, skipTable, (i == patternLen - 1) ? offset + patternLen : offset + patternLen - i);
				break;
			} else if (!pattern.get(i).contains(n)) {	// If the text char is part of the pattern but not at the current index: If i is the last index of the pattern, move offset += skipTable for text char.
																								// If i is not the last index, move the offset by length of the pattern - i
				findAllOccurrances(text, pattern, basket, uniChars, skipTable, (i == patternLen - 1) ? offset + skipTable.get(n) : offset + patternLen - i);
				break;
			}
		}
		// If the for-loop was exited upon matching the whole pattern, i == -1. Otherwise the loop was terminated by a mismatch
		if (i == -1) {
			// In this case the offset var holds the position of the first matching char
			basket.add(offset);
			// As we intend to find ALL occurances restart the search, moving the offset by 1.
			findAllOccurrances(text, pattern, basket, uniChars, skipTable, offset + 1);
		}
	}

	/**
	 * Create a Skip table (see Boyer-Moore-Algorithm "bad character heuristic") for the prepared pattern.
	 * 
	 * @param pattern Output from buildPatternList
	 * @return HashMap mapping each character from the pattern to its minimum distance to the patterns last index.
	 */
	private static HashMap<String, Integer> mkBadCharTable(ArrayList<HashSet<String>> pattern) {
		HashMap<String, Integer> ret = new HashMap<>();
		for (int i = 0; i < pattern.size(); i++) {
			for (String s : pattern.get(i)) {
				ret.put(s, pattern.size() - 1 - i);
			}
		}
		return ret;
	}

	/**
	 * Execute a text search on any String by providing a pattern.
	 * Patterns may consist of literals (almost any AASCII Character), exceptions are the literals '[', ']', '.','\':
	 *  * Squared brackets present a sequence of possible literals the are valid for the next char.
	 *  * The dot is a wildcard and matches any valid Character
	 *  * The Backslash may be used to represent dot, square-bracket or backslash-literals ("\\" or "\.")
	 * 
	 * @param text
	 * @param pattern
	 * @return List of match indices
	 */
	public static ArrayList<Integer> textSearch(String text, String pattern) {
		ArrayList<Integer> ret = new ArrayList<>();
		ArrayList<HashSet<String>> pat = buildPatternList(pattern);
		HashSet<String> uniChars = buildHashSet(pattern);

		HashMap<String, Integer> badChars = mkBadCharTable(pat);

		findAllOccurrances(text, pat, ret, uniChars, badChars, 0);

		return ret;
	}

	public static void main(String[] args) {
		System.out.println(textSearch("Hoola-Hoola girls like Hooligans", "Hooligan"));
		System.out.println(textSearch("xyxxx", "xx"));
		System.out.println(textSearch("abcabcdababdc.", "ab")); // 0, 3, 7, 9
		System.out.println(textSearch("abcabcdababdc.", "c.")); // 2, 5, 12
		System.out.println(textSearch("abcabcdababdc.", "c\\.")); // 12
		System.out.println(textSearch("abcabcdababdc.", "b[cd]")); // 1,4,10
		System.out.println(textSearch("abcabcdababdc.", "a....c")); // 0,7
		System.out.println(textSearch("a[aababa][ab]a", "a[ab]a")); // 3,5
		System.out.println(textSearch("a[aababa][ab]a", "a.\\[a")); // 7
	}
}
