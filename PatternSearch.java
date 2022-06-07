import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.PatternSyntaxException;

public class PatternSearch {
	public static ArrayList<HashSet<String>> buildPatternList(String pattern) {
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

	private static HashSet<String> buildHashSet(String pattern) {
		HashSet<String> unis = new HashSet<>();
		char[] letters = pattern.toCharArray();
		for (int i = 0; i < letters.length;) {
			if (letters[i] == '\\') {
				i++;
				unis.add(String.valueOf(letters[i]));
			} else if (letters[i] == '.') {
				unis.add("");
			} else if (letters[i] == '[' || letters[i] == ']') {
				i++;
				continue;
			} else {
				unis.add(String.valueOf(letters[i]));
			}
			i++;
		}
		return unis;
	}

	public static void findAllOccurrances(String text, ArrayList<HashSet<String>> pattern,
			ArrayList<Integer> basket, HashSet<String> uniChars, HashMap<String, Integer> skipTable,
			int offset) {
		int patternLen = pattern.size();
		if (offset + patternLen > text.length())
			return;
		int i = patternLen - 1;
		for (; i >= 0; i--) {
			String n = String.valueOf(text.charAt(i + offset));
			if (pattern.get(i).contains("")) {
				continue;
			} else if (!uniChars.contains(n)) {
				findAllOccurrances(text, pattern, basket, uniChars, skipTable, (i == patternLen - 1) ? offset + patternLen : offset + patternLen - i);
				break;
			} else if (!pattern.get(i).contains(n)) {
				findAllOccurrances(text, pattern, basket, uniChars, skipTable, (i == patternLen - 1) ? offset + skipTable.get(n) : offset + patternLen - i);
				break;
			}
		}
		if (i == -1) {
			basket.add(offset);
			findAllOccurrances(text, pattern, basket, uniChars, skipTable, offset + 1);
		}
	}

	private static HashMap<String, Integer> mkBadCharTable(ArrayList<HashSet<String>> pattern) {
		HashMap<String, Integer> ret = new HashMap<>();
		for (int i = 0; i < pattern.size(); i++) {
			for (String s : pattern.get(i)) {
				ret.put(s, pattern.size() - 1 - i);
			}
		}
		return ret;
	}

	public static ArrayList<Integer> textSearch(String text, String pattern) {
		ArrayList<Integer> ret = new ArrayList<>();
		ArrayList<HashSet<String>> pat = buildPatternList(pattern);
		HashSet<String> uniChars = buildHashSet(pattern);

		HashMap<String, Integer> badChars = mkBadCharTable(pat);

		findAllOccurrances(text, pat, ret, uniChars, badChars, 0);

		return ret;
	}

	public static void main(String[] args) {
		System.out.println(textSearch("Hoola-Hoola girls like Hooligans", "Hooligan")); // 0, 3, 7, 9
		System.out.println(textSearch("abcabcdababdc.", "ab")); // 0, 3, 7, 9
		System.out.println(textSearch("abcabcdababdc.", "c.")); // 2, 5, 12
		System.out.println(textSearch("abcabcdababdc.", "c\\.")); // 12
		System.out.println(textSearch("abcabcdababdc.", "b[cd]")); // 1,4,10
		System.out.println(textSearch("abcabcdababdc.", "a....c")); // 0,7
		System.out.println(textSearch("a[aababa][ab]a", "a[ab]a")); // 3,5
		System.out.println(textSearch("a[aababa][ab]a", "a.\\[a")); // 7
	}
}
