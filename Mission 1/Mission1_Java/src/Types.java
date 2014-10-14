import java.util.Map;

class BoolTok implements Expression {
	private String bool;

	public BoolTok(String number) {
		this.bool = number;
	}

	/**
	 * Interpret a Boolean token
	 * 
	 * @param variables
	 *            : the Map of the defined variables
	 * @return the boolean
	 */
	public String interpret(Map<String, Expression> variables)
			throws InterpreterException {
		return bool;
	}

	@Override
	public String toString() {
		return bool;
	}
}

class Variable implements Expression {
	private String name;

	public Variable(String name) {
		this.name = name;
	}

	/**
	 * Interpret a Variable token
	 * 
	 * @param variables
	 *            : the Map of the defined variables
	 * @return the value of the variable stored in the Map
	 */
	public String interpret(Map<String, Expression> variables)
			throws InterpreterException {
		if (null == variables.get(name))
			throw new InterpreterException(name + " is not define.");
		return variables.get(name).interpret(variables);
	}

	@Override
	public String toString() {
		return Interpreter.getValue(name);
	}
}

class Number implements Expression {
	private String number;

	public Number(String number) {
		this.number = number;
	}

	/**
	 * Interpret a Number token
	 * 
	 * @param variables
	 *            : the Map of the defined variables
	 * @return the number
	 */
	public String interpret(Map<String, Expression> variables)
			throws InterpreterException {
		return number;
	}

	@Override
	public String toString() {
		return Double.parseDouble(number) + "";
	}
}
