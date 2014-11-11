
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class Journal {
	/**
	 * @author Baptiste Degryse, Wojciech Grynczel, Jerome Bertaux
	 */
	private Map<String,Entree> map = new TreeMap<String, Entree>();

	/**
	 * cree un journal en lisant un fichier.
	 * La premiere ligne represente les titres des champs
	 * @param indexCle : commence a zero, index de la cle primaire
	 * @param filename : nom du fichier a lire (ou filepath)
	 * @param delimiter : delimiter entre chaque valeurs
	 */
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
					try{
						String []to=new String[size];
						int decalage=0;
						for(int i=0;i<cle.length && i+decalage+1<tokens.length;i++){
							to[i]=tokens[i+decalage];
							if(tokens[i+decalage].charAt(0)==escapeChar && 
									tokens[i+decalage].charAt(tokens[i+decalage].length()-1)==escapeChar){}//guillemets mais pas de virgule dedans
							else if(tokens[i+decalage].charAt(0)==escapeChar){
								int j=1;
								to[i]=tokens[i+decalage].substring(1);
								while(tokens[i+decalage+j].charAt(tokens[i+decalage+j].length()-1)!=escapeChar){
									to[i]=to[i]+","+tokens[i+decalage+j];
									j++;
								}
								to[i]=to[i]+","+tokens[i+decalage+j].substring(0, tokens[i+decalage+j].length()-1);
								decalage+=j;
							}
						}
						tokens=to;

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
		//Object[] a = map.values().toArray();
	}
	/**
	 * retourne l'entree ayant comme cle cle
	 * @param cle : cle de l'entree
	 * @return l'entree de cle cle
	 */
	public Entree get(String cle){
		return map.get(cle);
	}

	public Map<String, Entree> getMap() {
		return map;
	}
}

