/**
 * @author Grynczel Wojciech
 */

class Value implements Expression {
	private String name;

	public Value(String name) {
		this.name = name;
	}

	public String interpret() {
		return "";
	}

	@Override
	public String toString() {
		return "";
	}
}

class Operand implements Expression {
	private double number;

	public Operand(double number) {
		this.number = number;
	}

	public String interpret() {
		return "";
	}

	@Override
	public String toString() {
		return "";
	}
}
