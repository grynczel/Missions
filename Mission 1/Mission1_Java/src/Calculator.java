import java.util.Map;

class Add implements Expression {
	Expression second;
	Expression first;

	public Add(Expression second, Expression first) {
		this.second = second;
		this.first = first;
	}

	/**
	 * Add 2 numbers
	 * 
	 * @param variables
	 *            : the Map of the defined variables
	 * 
	 * @return result of add
	 */
	public String interpret(Map<String, Expression> variables)
			throws InterpreterException {
		// Test if first or second are Number or Variable
		if (second instanceof Number || second instanceof Variable) {
			if (first instanceof Number || first instanceof Variable) {
				// Return result of add
				return (Double.parseDouble(second.interpret(variables)) + Double
						.parseDouble(first.interpret(variables))) + "";
			} else {
				throw new InterpreterException(
						"true or false are not allowed for add.");
			}
		} else {
			throw new InterpreterException(
					"true or false are not allowed for add.");
		}
	}
}

/**
 * Sub 2 numbers
 * 
 * @param variables
 *            : the Map of the defined variables
 * 
 * @return result of sub
 */
class Sub implements Expression {
	Expression second;
	Expression first;

	public Sub(Expression left, Expression right) {
		second = left;
		first = right;
	}

	public String interpret(Map<String, Expression> variables)
			throws InterpreterException {
		// Test if first or second are Number or Variable
		if (second instanceof Number || second instanceof Variable) {
			if (first instanceof Number || first instanceof Variable) {
				return (Double.parseDouble(first.interpret(variables)) - Double
						.parseDouble(second.interpret(variables))) + "";
			} else {
				throw new InterpreterException(
						"true or false are not allowed for sub.");
			}
		} else {
			throw new InterpreterException(
					"true or false are not allowed for sub.");
		}
	}
}

/**
 * Mul 2 numbers
 * 
 * @param variables
 *            : the Map of the defined variables
 * 
 * @return result of Mul
 */
class Mul implements Expression {
	Expression second;
	Expression first;

	public Mul(Expression left, Expression right) {
		second = left;
		first = right;
	}

	public String interpret(Map<String, Expression> variables)
			throws InterpreterException {
		// Test if first or second are Number or Variable
		if (second instanceof Number || second instanceof Variable) {
			if (first instanceof Number || first instanceof Variable) {
				return (Double.parseDouble(first.interpret(variables)) * Double
						.parseDouble(second.interpret(variables))) + "";
			} else {
				throw new InterpreterException(
						"true or false are not allowed for mul.");
			}
		} else {
			throw new InterpreterException(
					"true or false are not allowed for mul.");
		}
	}
}

/**
 * Div 2 numbers
 * 
 * @param variables
 *            : the Map of the defined variables
 * 
 * @return result of Div
 */
class Div implements Expression {
	Expression second;
	Expression first;

	public Div(Expression left, Expression right) {
		second = left;
		first = right;
	}

	public String interpret(Map<String, Expression> variables)
			throws InterpreterException {
		// Test if first or second are Number or Variable
		if (second instanceof Number || second instanceof Variable) {
			if (first instanceof Number || first instanceof Variable) {
				// Division by 0 is not allowed
				if (Double.parseDouble(second.interpret(variables)) == 0) {
					throw new InterpreterException("Division by zero.");
				}
				return (Double.parseDouble(first.interpret(variables)) / Double
						.parseDouble(second.interpret(variables))) + "";
			} else {
				throw new InterpreterException(
						"true or false are not allowed for div.");
			}
		} else {
			throw new InterpreterException(
					"true or false are not allowed for div.");
		}
	}
}

/**
 * Test if the two numbers are equal
 * 
 * @param variables
 *            : the Map of the defined variables
 * 
 * @return result of test (true/false)
 */
class Eq implements Expression {
	Expression second;
	Expression first;

	public Eq(Expression left, Expression right) {
		second = left;
		first = right;
	}

	public String interpret(Map<String, Expression> variables)
			throws InterpreterException {
		return Boolean
				.toString(Double.parseDouble(first.interpret(variables)) == Double
						.parseDouble(second.interpret(variables)));
	}
}

/**
 * Test if the two numbers are not equal
 * 
 * @param variables
 *            : the Map of the defined variables
 * 
 * @return result of test (true/false)
 */
class Ne implements Expression {
	Expression second;
	Expression first;

	public Ne(Expression left, Expression right) {
		second = left;
		first = right;
	}

	public String interpret(Map<String, Expression> variables)
			throws InterpreterException {
		return Boolean
				.toString(Double.parseDouble(first.interpret(variables)) != Double
						.parseDouble(second.interpret(variables)));
	}
}