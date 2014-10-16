import java.util.ArrayList;
import java.util.Stack;

public class Main {
	/*
	 * public static void main(String[] args) { //lire le fichier
	 * ArrayList<String> file = ReadWrite.mRead(args[0]); for(String formule :
	 * file){ LinkedBinaryTree<String> arbre = new
	 * LinkedBinaryTree<String>(formule, null, null);
	 * System.out.println(arbre.toString()); } }
	 */
	public static void main(String[] args) {

		ArrayList<String> expressions = ReadWrite.mRead("expression.txt");

		for (String expression : expressions) {
			try {
				if (validExpression(expression)) {
					FormalExpressionTree fet = new FormalExpressionTreeImpl(
							expression);
					System.out.println(fet.toString());
				}
			} catch (InvalidExpressionException e) {
				System.out.println(e.getMessage());
				continue;
				// TODO MESSAGE dans la console / fichier?
			}
		}
	}

	private static boolean validExpression(String s)
			throws InvalidExpressionException {
		// Source : http://stackoverflow.com/a/2595277
		int size = s.length();
		Stack stack = new Stack();

		for (int i = 0; i < size; i++) {
			char chr = s.charAt(i);

			if (chr == '(') {
				stack.push('(');
			} else {
				if (chr == ')') {
					if (stack.size() == 0) {
						throw new InvalidExpressionException("Expression : "
								+ s + " n'est pas correcte");
					} else {
						stack.pop(); // from stack
					}
				}
			}
		}
		if (stack.size() != 0)
			throw new InvalidExpressionException("Expression : " + s
					+ " n'est pas correcte");
		// TODO Auto-generated method stub
		return true;
	}
}
