package modele;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Journal {
	/**
	 * @author Baptiste Degryse, Wojciech Grynczel, Jerome Bertaux
	 */
	private Map<String,Entree> map = new HashMap<String, Entree>();

	public Journal(int indexCle, String filename, String delimiter){
		BufferedReader fileReader = null;
		char escapeChar='"';
		String[] tokens;
		int index=1;
		try {
			String line = "";
			// Create the file reader
			fileReader = new BufferedReader(new FileReader(filename));
			line = fileReader.readLine();
			if(line==null)
				return;
			String[] cle = line.split(delimiter);
			int size=cle.length;

			// Read the file line by line
			while ((line = fileReader.readLine()) != null) {
				index++;
				// Get all tokens available in line
				if(!line.equals("")){
					tokens = line.split(delimiter);
					if(tokens.length>size){
						String []to=new String[size];
						int decalage=0;
						for(int i=0;i<cle.length && i+decalage<tokens.length;i++){
							to[i]=tokens[i+decalage];
							if(tokens[i+decalage].charAt(0)==escapeChar){
								int j=1;
								to[i]=tokens[i+decalage].substring(1);
								while(tokens[i+decalage+j].charAt(tokens[i+decalage+j].length()-1)!=escapeChar){
									to[i]=to[i]+","+tokens[i+decalage+j];
									j++;
								}
								to[i]=to[i]+","+tokens[i+decalage+j].substring(0, tokens[i+decalage+j].length()-2);
								decalage+=j;
							}
						}
						tokens=to;
					}
					//for(int i=0; i<tokens.length; i++)
					//	System.out.print(tokens[i] + " ");
					//System.out.println();
					try{
						map.put(tokens[indexCle], new Entree(cle,tokens));
					}
					catch(Exception e){
						System.out.println("Erreur : "+index+" "+line);
						e.printStackTrace();
					}
				}
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
	}

	public Entree get(String cle){
		return map.get(cle);
	}

	public Map<String, Entree> getMap() {
		return map;
	}

}