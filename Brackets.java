package ha1;

import java.util.ArrayDeque;
import java.util.Arrays;

/* 
 * class for Hausaufgabenblatt1
 * 
 * checks given String for 
 * valid opening and closing brackets
 * 
 * author: Laura Mey
 * */
public class Brackets {
	
	/*
	 * check if an array contains a specific character
	 */
	public static boolean charArrContains(char[] arr, char c) {
		for (char arrc : arr) {
			if (arrc == c) {
				return true;
			}
		}
		return false;
	}

	/*
	 * check if the brackets in the given String s are valid
	 */
	public static boolean isValid(String s) {
		
		char[] opening_brackets = new char[] { '(', '[', '{' };
		char[] closing_brackets = new char[] { ')', ']', '}' };
		ArrayDeque<Character> brackets = new ArrayDeque<Character>();
		
		for (int i = 0; i < s.length(); i++) {
			if (charArrContains(opening_brackets, s.charAt(i))) {
				brackets.add(s.charAt(i));
			} else if (charArrContains(closing_brackets, s.charAt(i))) {
				if (brackets.size() > 0) {
					char brac = brackets.pollLast();
					//detect matching closing bracket to last opened one
					int index = Arrays.asList(new Character[] { '(', '[', '{' }).indexOf(brac);
					if (closing_brackets[index] == s.charAt(i)) {
						continue;
					} else {
						return false;
					}
				} else {
					return false;
				}

			}
		}
		
		// There should be no open brackets left
		if (brackets.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		System.out.println(isValid("(([[]]))"));
		System.out.println(isValid("([)]"));
		System.out.println(isValid("([[])"));
		System.out.println(isValid("(()))"));
		System.out.println(isValid("(()"));
		System.out.println(isValid("( { [ ] ) }"));
		System.out.println(isValid("adfkjlskdf"));
		System.out.println(isValid("as[df(asdfsdf)k{s}l]d"));
		System.out.println(isValid("asdf(asdfsdf)ksl[d"));
	}
}
