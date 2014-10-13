import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * 
 * @author Thomas Grimée
 * 
 */
public class ReadWrite {

	/**
	 * Creer un ArrayList<String> contenant toutes les lignes du fichier fileIn.
	 * 
	 * @return true si aucune erreur n'a eu lieu, false sinon
	 */
	public static ArrayList<String> mRead(String fileIn) throws IOException {
		ArrayList<String> lu = new ArrayList<String>();
		try (BufferedReader reader = new BufferedReader(new FileReader(fileIn))) {
			String line;
			while ((line = reader.readLine()) != null) {
				lu.add(line);
			}
		} catch (IOException e) {
			throw e;
		}
		return lu;
	}

	public static PrintWriter openOutput(String pathFileOut)
			throws FileNotFoundException {
		PrintWriter resul = null;
		try {
			resul = new PrintWriter(pathFileOut);
		} catch (FileNotFoundException e) {
			throw e;
		}
		return resul;
	}

	public static void closeOutput(PrintWriter resul) {
		resul.close();
	}
}
