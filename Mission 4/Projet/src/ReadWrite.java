import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * 
 * 
 * 
 */
public class ReadWrite {

	/**
	 * Creer un ArrayList<String> contenant toutes les lignes du fichier fileIn.
	 * 
	 * @return true si aucune erreur n'a eu lieu, false sinon
	 */
	public static ArrayList<String> mRead(String fileIn) {
		ArrayList<String> lu = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(fileIn));
			String line;
			while ((line = reader.readLine()) != null) {
				lu.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return lu;
	}

	public static PrintWriter openOutput(String pathFileOut)
			throws FileNotFoundException {
		PrintWriter result = null;
		try {
			result = new PrintWriter(pathFileOut);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static void closeOutput(PrintWriter resul) {
		resul.close();
	}
}
