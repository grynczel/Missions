import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static java.util.Arrays.asList;

class Interpreter {
	// List of variables <Variable name, value>
	private static Map<String, Expression> variables;
	// Stack
	private LStack<Expression> expressionStack;
	private PrintWriter resul;

	// List of unauthorized variable names
	private ArrayList<String> variablesNotAllowed = new ArrayList<String>(
			asList("pstack", "add", "sub", "mul", "div", "dup", "eq", "ne",
					"def", "pop", "true", "false"));

	public static String getValue(String name) {
		return variables.get(name).toString();
	}

	/**
	 * Create an interpreter
	 * 
	 * @param resul
	 *            : Output file
	 */
	public Interpreter(PrintWriter resul) {
		variables = new HashMap<String, Expression>();
		expressionStack = new LStack<Expression>();
		this.resul = resul;
	}

	/**
	 * Interpret one line of PostScript
	 * 
	 * @param expression
	 *            : one line of PostScript
	 */
	public void interpret(String expression) throws InterpreterException {
		for (String token : expression.split(" ")) {
			if (token.equals("add")) {
				Expression first = pop();
				Expression second = pop();
				Expression subExpression = new Add(first, second);
				// The element calculated by the interpret(variables) function
				// is added to the stack
				expressionStack.push(new Number(subExpression
						.interpret(variables)));
			} else if (token.equals("sub")) {
				Expression first = pop();
				Expression second = pop();
				Expression subExpression = new Sub(second, first);
				expressionStack.push(new Number(subExpression
						.interpret(variables)));
			} else if (token.equals("mul")) {
				Expression first = pop();
				Expression second = pop();
				Expression subExpression = new Mul(second, first);
				expressionStack.push(new Number(subExpression
						.interpret(variables)));
			} else if (token.equals("div")) {
				Expression first = pop();
				Expression second = pop();
				Expression subExpression = new Div(second, first);
				expressionStack.push(new Number(subExpression
						.interpret(variables)));
			} else if (token.equals("dup")) {
				Expression first = pop();
				expressionStack.push(first);
				expressionStack.push(first);
			} else if (token.equals("exch")) {
				Expression first = pop();
				Expression second = pop();
				expressionStack.push(first);
				expressionStack.push(second);
			} else if (token.equals("pstack")) {
				System.out.println(expressionStack.toString());
				resul.println(expressionStack.toString());
			} else if (token.equals("pop")) {
				pop();
			} else if (token.equals("eq")) {
				Expression first = pop();
				Expression second = pop();
				Expression eqExpression = new Eq(second, first);
				expressionStack.push(new BoolTok(eqExpression
						.interpret(variables)));
			} else if (token.equals("ne")) {
				Expression first = pop();
				Expression second = pop();
				Expression neExpression = new Ne(second, first);
				expressionStack.push(new BoolTok(neExpression
						.interpret(variables)));
			} else if (token.startsWith("/")) {
				// To add a variable, the line have to contain only 3 words.
				if (expression.split(" ").length == 3
						&& expression.split(" ")[2].equals("def")) {

					String var = token.substring(1);
					String val = expression.split(" ")[1];

					// Test if variable name is authorized
					if (variablesNotAllowed.contains(token.substring(1))
							|| isNumber(token.substring(1))) {
						throw new InterpreterException("Name of the variable '"
								+ token.substring(1) + "' is not allowed");
					}

					// Test if value of variable is authorized
					if (!isNumber(val)) {
						throw new InterpreterException("Value is invalid : "
								+ val);
					}
					// Adding variable to the 'variables' map
					variables.put(var, new Number(val));
					break;
				} else {
					throw new InterpreterException(
							"Line begins like a definition, but it's not.");
				}
			} else {
				if (token.equals("true") || token.equals("false")) {
					expressionStack.push(new BoolTok(token));
				} else {
					try {
						// double
						Double.parseDouble(token);
						expressionStack.push(new Number(token));
					} catch (NumberFormatException e) {
						if (!variables.containsKey(token)) {
							throw new InterpreterException("Variable '" + token
									+ "' is not defined");
						}
						// not a double
						expressionStack.push(new Variable(token));
					}
				}
			}
		}
	}

	/**
	 * Remove an Expression from the stack
	 * 
	 * @return out : the removed Expression
	 */
	private Expression pop() throws InterpreterException {
		Expression out = expressionStack.pop();
		if (out == null) {
			throw new InterpreterException("Stack is empty, you can not pop.");
		}
		return out;
	}

	/**
	 * Check if a string is a number
	 * 
	 * @return out : the removed Expression
	 */
	private boolean isNumber(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			// not a double
			return false;
		}
	}
}
