import java.util.Stack;

/**
 * @author Grynczel Wojciech
 */
public class FormalExpressionTreeImpl implements FormalExpressionTree {

	public static int FUNCTION_COS = 1;
	public static int FUNCTION_SIN = 2;

	public static char[] operators = { '+', '/', '*', '-', '(', ')', '^' };
	private LinkedBinaryTree lbt;
	

	public FormalExpressionTreeImpl(LinkedBinaryTree<String> lbt) {
		this.lbt = lbt;
	}

	public FormalExpressionTreeImpl(String expression) {
		lbt = parse(expression);
	}

	public FormalExpressionTreeImpl() {
		lbt = new LinkedBinaryTree(null, null, null);
	}

	@Override
	public FormalExpressionTree derive() {
		FormalExpressionTreeImpl tDerive = new FormalExpressionTreeImpl(Calculator.derive(lbt));
		return tDerive;
	}

	@Override
	public String toString() {
		return lbt.toString();
	}

	private LinkedBinaryTree parse(String expression) {
		Stack<Object> operators = new Stack<Object>();
		Stack<Object> operands = new Stack<Object>();
		int size = expression.length();
		for (int i = 0; i < size; i++) {
			char token = expression.charAt(i);
			int fnct = isFonction(token, i, expression);

			// // TODO
			// try {
			// validExpression(expression);
			// } catch (InvalidExpressionException e) {
			// // Message d'erreur?
			// continue;
			// }

			if (isOperator(token) || fnct != -1) {

				LinkedBinaryTree right = null;
				LinkedBinaryTree left = null;

				if (fnct == FUNCTION_COS) {
					operators.push("cos");
					// D�placement du curseur
					i = i + 2;
				} else if (fnct == FUNCTION_SIN) {
					operators.push("sin");
					// D�placement du curseur
					i = i + 2;
				} else if (token == ')') {
					// Double parentheses -> Do nothing
					if (operators.size() == 0) {
						continue;
					}
					// cos or sin -> 1 pop()
					if (operators.peek().equals("cos")
							|| operators.peek().equals("sin")) {
						left = (LinkedBinaryTree) operands.pop();
					} else {
						right = (LinkedBinaryTree) operands.pop();
						left = (LinkedBinaryTree) operands.pop();
					}

					LinkedBinaryTree lbt = new LinkedBinaryTree(
							operators.pop() +"", left, right);
					operands.push(lbt);
				} else if (token == '(') {
					continue;
				} else {
					operators.push(token);
				}
			} else {

				// Test si le nombre est n�gatif
				boolean isNegative = false;
				if (i > 0 && expression.charAt(i - 1) == '-') {
					isNegative = isNegative(token, i, expression);
					// Remove '-' from operators stack
					operators.pop();
				}

				String operand;
				if (isNegative) {
					operand = "-";
				} else {
					operand = "";
				}

				if (Character.isDigit(expression.charAt(i))) {
					for (int j = i; j < size; j++) {
						if (Character.isDigit(expression.charAt(j))) {
							operand += expression.charAt(j);
							i = j;
						} else {
							operands.push(new LinkedBinaryTree(operand + "", null,
									null));
							i = j - 1;
							break;
						}
					}

					if (i == size - 1) {
						// Variable 1, 15 etc.
						operands.push(new LinkedBinaryTree(operand +"", null, null));
					}
				} else {
					// Variable x,y,z etc.
					operands.push(new LinkedBinaryTree(token +"", null, null));
				}

				if (operands.size() == 2 && i > 0
						&& expression.charAt(i - 1) != '(' && i + 1 <= size
						&& expression.charAt(i + 1) != ')') {
					LinkedBinaryTree right = (LinkedBinaryTree) operands.pop();
					LinkedBinaryTree left = (LinkedBinaryTree) operands.pop();
					LinkedBinaryTree lbt = new LinkedBinaryTree(
							operators.pop()+"", left, right);
					operands.push(lbt);
				}

			}
		}
		return (LinkedBinaryTree) operands.pop();
	}

	private boolean isNegative(char token, int i, String s) {
		if (i == 0) {
			return true;
		}
		if (i > 0)
			if (isOperator(s.charAt(i - 1)) && s.charAt(i - 1) != ')')
				return true;

		return false;
	}

	private boolean validExpression(String s) throws InvalidExpressionException {
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

	private int isFonction(char token, int position, String s) {
		if (position + 2 <= s.length()) {
			if (token == 'c' && s.charAt(position + 1) == 'o'
					&& s.charAt(position + 2) == 's') {
				return FUNCTION_COS;
			}
			if (token == 's' && s.charAt(position + 1) == 'i'
					&& s.charAt(position + 2) == 'n') {
				return FUNCTION_SIN;
			}
		}
		return -1;
	}

	private boolean isOperator(char c) {
		for (int i = 0; i < operators.length; i++) {
			if (operators[i] == c) {
				return true;
			}
		}
		return false;
	}
}
