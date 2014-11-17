
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeMap;

public class Dictionnaire {
	/**
	 * @author Baptiste Degryse, Wojciech Grynczel, Jerome Bertaux
	 */
	private String[] ordreTitre;
	private boolean[] ordreAsc;
	private TreeMap<String,Entree> map = new TreeMap<String, Entree>();

	/**
	 * cree un journal en lisant un fichier.
	 * La premiere ligne represente les titres des champs
	 * @param indexCle : commence a zero, index de la cle primaire
	 * @param filename : nom du fichier a lire (ou filepath)
	 * @param delimiter : delimiter entre chaque valeurs
	 */
	public Dictionnaire(int indexCle, String filename, String delimiter){
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
						//if(index==11326)
						//	System.out.println("yo");
						for(int i=0;i<cle.length && i+decalage<tokens.length;i++){
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

						map.put(tokens[indexCle], new Entree(cle,tokens, this));
						
					}
					catch(NullPointerException e){
						System.out.println("Erreur : "+index+" "+line);
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
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

	/**
	 * change l'ordre de tri dans les paramtres
	 * @param titres: Contient l'ordre de priorite de tri
	 * @param ordres: Contient un boolean par titre, true=ascendant
	 */
	public void changeTri(String titres[], boolean asc[]){
		if(asc==null){
			boolean asc2[]=new boolean[titres.length];
			for(int i=0;i<asc2.length;i++)
				asc2[i]=true;
		}
		else if(asc.length!=titres.length){
			boolean asc2[]=new boolean[titres.length];
			for(int i=0;i<asc2.length;i++)
				asc2[i]=true;
			for(int i=0;i<asc.length;i++)
				asc2[i]=asc[i];
		}
		ordreTitre=titres;
		ordreAsc=asc;
	}

	public String[] getOrdreTitre(){
		return ordreTitre;
	}
	public boolean[] getAsc(){
		return ordreAsc;
	}
	public void test(){
		//Entree e=map.get(map.firstKey());
		NavigableSet<String> ks=map.descendingKeySet();
		Iterator<String> iter=ks.iterator();
		while(iter.hasNext()){
			System.out.println(map.get(iter.next()));
		}
	}


	public Map<String, Entree> getMap() {
		return map;
	}
}
