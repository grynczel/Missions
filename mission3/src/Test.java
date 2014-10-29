import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

	
	public static <E> void main(String[] args) {
		Map<String, Journal> map = new HashMap<String, Journal>();
		Map<String, String> types = new HashMap<String, String>();
		// Input file which needs to be parsed
		String fileToParse = "Journals.csv";
		BufferedReader fileReader = null;

		// Delimiter used in CSV file
		final String DELIMITER = ",";
		try {
			String line = "";
			// Create the file reader
			fileReader = new BufferedReader(new FileReader(fileToParse));

			// Read the file line by line
			while ((line = fileReader.readLine()) != null) {
				// Get all tokens available in line
				String[] tokens = line.split(DELIMITER);

				List t = new ArrayList<E>();
				
				for(int i = 2; i < tokens.length; i++){
					if(i % 2 == 0){
						t.add(tokens[i]);
					}else{
						types.put(tokens[i-1], tokens[i]);
					}
				}
				System.out.println("token[0] : ");
				Journal j = new Journal(tokens[0], tokens[1], t);
				map.put(j.getTitle(), j);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		System.out.println("Size " + map.size());

	}

}
