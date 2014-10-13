public class FormatExpressionTreeImpl implements FormalExpressionTree {

	/**
	 * @author Grynczel Wojciech
	 */
	// TODO Map?
	private final String VALID_SYMBOLS = "()1234567890/*+-\t ";
	private String unprocessed;
	private String infix;
	private String postfix;

	public FormatExpressionTreeImpl(String s) {
		s = s.replaceAll(" ", "");
		boolean error = false;
		StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < s.length(); i++) {
			char token = s.charAt(i);
			char nexttoken = 0;
			boolean lastchar = false;
			boolean isTokenDigit = false;
			boolean isNextTokenDigit = false;

			if (i == s.length() - 1) {
				lastchar = true;
			} else {
				nexttoken = s.charAt(i + 1);
			}

			if (VALID_SYMBOLS.indexOf(token) < 0) {
				System.out.println("Illegal Character.");
				error = true;
				break;
			}

			if (!lastchar) {
				isTokenDigit = Character.isDigit(token);
				isNextTokenDigit = Character.isDigit(nexttoken);
				if ((isTokenDigit && nexttoken == '(')
						|| (token == ')' && isNextTokenDigit) || (token == ')')
						&& (nexttoken == '(')
						|| (token == ')' && nexttoken == '^')) {
					buffer.append(token + "*" + nexttoken);
					i++;

				} else {
					buffer.append(token);
				}
			} else if (lastchar) {
				buffer.append(token);
			}
		}
		if (error)
			infix = postfix = "NA";
		else
			infix = buffer.toString();
	}

	public FormatExpressionTreeImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public FormalExpressionTree derive() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "";
	}
}
