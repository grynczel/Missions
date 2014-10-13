import java.util.Map;

/**
 * Interpret an Expression
 * 
 * @param variables
 *            : list of variables saved
 * @return the result the interpreted Expression, as defined in PostScript
 */
interface Expression {
	public String interpret();
}