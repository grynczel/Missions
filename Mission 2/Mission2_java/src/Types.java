/**
 * @author Grynczel Wojciech
 */

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
	public String interpret() {
		return null;
	}

	@Override
	public String toString() {
		return "";
	}
}

class Op implements Expression {
	private String number;

	public Op(String number) {
		this.number = number;
	}

	/**
	 * Interpret a Number token
	 * 
	 * @param variables
	 *            : the Map of the defined variables
	 * @return the number
	 */
	public String interpret() {
		return number;
	}

	@Override
	public String toString() {
		return "";
	}
}
