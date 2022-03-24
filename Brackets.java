import java.util.HashMap;
import java.util.ArrayDeque;

public class Brackets {

	public static boolean isValid(String s) {
		/*
		 * Erstelle eine Hashmap die schließende
		 + und oeffnende Klammern vom gleichen Typ miteinander assoziert.
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

	public static void main(String[] args) {
		String[] stringArr = {"( ( [ [ ] ] ) )", "( [ ) ]", "( [ ] ] )", "( ( ) ) )", "( ( )", "( { [ ] ) }", "Josha Bartscch"};
		for(String s: stringArr){
			System.out.println(String.format("%-15s: %15s", s, String.valueOf(isValid(s))));
		}
	}
}
