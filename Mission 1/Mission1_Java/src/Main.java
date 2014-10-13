import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {
	/**
	 * Read the file, interpret it as PostScript, and write the ouput in a new
	 * file
	 */
	public static void main(String[] args) throws IOException {
		ArrayList<String> data = ReadWrite.mRead("data.mps");
		PrintWriter resul = ReadWrite.openOutput("test.txt");
		Interpreter interpreter = new Interpreter(resul);

		for (String expression : data) {
			try {
				interpreter.interpret(expression);
			} catch (InterpreterException e) {
				e.printStackTrace();
			}
		}
		ReadWrite.closeOutput(resul);
	}
}
