import java.util.Arrays;
import java.util.Stack;

public class Main2 {

	public static char[] operators = { '+', '/', '*', '-', '(', ')', '^' };

	/*
	 * public static void main(String[] args) { //lire le fichier
	 * ArrayList<String> file = ReadWrite.mRead(args[0]); for(String formule :
	 * file){ LinkedBinaryTree<String> arbre = new
	 * LinkedBinaryTree<String>(formule, null, null);
	 * System.out.println(arbre.toString()); } }
	 */
	public static void main(String[] args) {
		String expression = "(x/12)";
		LinkedBinaryTree lbt = parse(expression);
		System.out.println(lbt.toString() + " : (x/12)");

		expression = "(545/12)*3";
		lbt = parse(expression);
		System.out.println(lbt.toString() + " : (545/12)*3");

		expression = "-4";
		lbt = parse(expression);
		System.out.println(lbt.toString() + " : -4");

		expression = "((10/x)+(2*x))";
		lbt = parse(expression);
		System.out.println(lbt.toString() + " : ((10/x)+(2*x))");

		// TODO ((x^(1-null))/6) => ((x^(-1))/6)
		expression = "((x^(-1))/6)";
		lbt = parse(expression);
		System.out.println(lbt.toString() + " : ((x^(-1))/6)");

		expression = "(10^(x+20))";
		lbt = parse(expression);
		System.out.println(lbt.toString() + " : (10^(x+20))");

		expression = "42";
		lbt = parse(expression);
		System.out.println(lbt.toString() + " : 42");

		expression = "-4*2";
		lbt = parse(expression);
		System.out.println(lbt.toString() + " : -4*2");
	}

	public static LinkedBinaryTree parse(String s) {
		Stack<Object> operators = new Stack<Object>();
		Stack<Object> operands = new Stack<Object>();
		int size = s.length();
		int op = 2;
		for (int i = 0; i < size; i++) {
			char token = s.charAt(i);
			if (isOperator(token)) {
				if (token == ')' && operands.size() > 1) {
					LinkedBinaryTree right = null;
					LinkedBinaryTree left = null;
					if (operands.size() == operators.size()) {
						left = (LinkedBinaryTree) operands.pop();
					} else {
						right = (LinkedBinaryTree) operands.pop();
						left = (LinkedBinaryTree) operands.pop();
					}

					LinkedBinaryTree lbt = new LinkedBinaryTree(
							operators.pop(), left, right);
					operands.push(lbt);
					op--;
				} else if (token == '(') {
					op++;
				} else if (token == ')') {
					op--;
				} else {
					operators.push(token);
				}
			} else {
				String operand = "";
				if (Character.isDigit(s.charAt(i))) {
					for (int j = i; j < size; j++) {
						if (Character.isDigit(s.charAt(j))) {
							operand += s.charAt(j);
							i = j;
						} else {
							operands.push(new LinkedBinaryTree(operand, null,
									null));
							i = j - 1;
							break;
						}
					}

					if (i == size - 1) {
						operands.push(new LinkedBinaryTree(operand, null, null));
					}
				} else {
					operands.push(new LinkedBinaryTree(token, null, null));
				}

				if (operators.size() == 1 && size == 2) {
					LinkedBinaryTree right = null;
					LinkedBinaryTree left = (LinkedBinaryTree) operands.pop();

					LinkedBinaryTree lbt = new LinkedBinaryTree(
							operators.pop(), left, right);
					operands.push(lbt);
				}

				if (operands.size() == op) {
					LinkedBinaryTree right = (LinkedBinaryTree) operands.pop();
					LinkedBinaryTree left = (LinkedBinaryTree) operands.pop();

					LinkedBinaryTree lbt = new LinkedBinaryTree(
							operators.pop(), left, right);
					operands.push(lbt);
				}
			}
		}
		return (LinkedBinaryTree) operands.pop();

	}

	public static boolean isOperator(char c) {
		for (int i = 0; i < operators.length; i++) {
			if (operators[i] == c) {
				return true;
			}
		}
		return false;
	}
}
