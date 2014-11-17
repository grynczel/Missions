import java.util.Map;

/**
 * @author Grynczel Wojciech
 */

class Cos implements Expression {
	Expression second;
	Expression first;

	public Cos(Expression second, Expression first) {
		this.second = second;
		this.first = first;
	}

	public String interpret() {
		return "0";
	}
}