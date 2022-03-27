import java.util.HashMap;
import java.util.ArrayDeque;

/**
 * @author: Laura Mey, Christian Thelen, Josha Bartsch
 * class for Hausaufgabenblatt1
 * 
 * checks given String for 
 * valid opening and closing brackets
 * 
 */
public class Brackets {

	/**
	 * check if the brackets in the given String s are valid
	 * @param String String to check on valid brackets
	 * @return True if the String contains a valid bracket sequence else false.
	 */
	public static boolean isValid(String s) {
		/*
		 * Erstelle eine Hashmap die schließende
		 * und oeffnende Klammern vom gleichen Typ miteinander assoziert.
		 */
		HashMap<Character, Character> rbracs = new HashMap<>();
		rbracs.put(')','(');
		rbracs.put(']','[');
		rbracs.put('}','{');
		ArrayDeque<Character> ad = new ArrayDeque<>();

		for(char c: s.toCharArray()) {
			if(rbracs.containsValue(c)) { // Handelt es sich um eine oeffende Klammer? Push it!
				ad.push(c);
			} else if (rbracs.containsKey(c)) { // Handelt es sich um eine schliessende Klammer?
				if (ad.peek() != rbracs.get(c)) // Entspricht die letzte offene Klammer auf dem Stack der schließenden?
					return false; // Wenn nicht, ist der Rhytmus unterbrochen.
				else
					ad.pop(); // Ansonsten entferne die letzte offene Klammer, sie wurde geschlossen.
			}
		}
		if (!ad.isEmpty())
			return false; // Wenn nach durchgehen des String noch Klammern auf dem Stack liegen, wurden nicht alle Klammern geschlossen.
		return true;
	}

	/*
	 * Testing a given list of Strings for validity, print to stdout.
	 */
	public static void main(String[] args) {
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
