import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Dictionnaire {
	/**
	 * @author Baptiste Degryse, Wojciech Grynczel, Jerome Bertaux
	 */
	private Map<String, Entree> map = new TreeMap<String, Entree>();

	/**
	 * cree un journal en lisant un fichier. La premiere ligne represente les
	 * titres des champs
	 * 
	 * @param indexCle
	 *            : commence a zero, index de la cle primaire
	 * @param filename
	 *            : nom du fichier a lire (ou filepath)
	 * @param delimiter
	 *            : delimiter entre chaque valeurs
	 */
	public Dictionnaire(int indexCle, String filename, String delimiter) {
		Iterator<String> it = ReadWrite.mRead(filename).iterator();
		char escapeChar = '"';
		String[] tokens;
		int index = 1;
		
		String line = it.next();
		if (line == null)
			return;

		String[] cle = line.split(delimiter);
		int size = cle.length;

		// Read the file line by line
		while (it.hasNext()) {
			line = it.next();
			index++;
			// Get all tokens available in line

			if (!line.equals("")) {
				tokens = line.split(delimiter);
				try {
					String[] to = new String[size];
					int decalage = 0;
					for (int i = 0; i < cle.length
							&& i + decalage < tokens.length; i++) {
						to[i] = tokens[i + decalage];
						if (tokens[i + decalage].charAt(0) == escapeChar
								&& tokens[i + decalage].charAt(tokens[i
										+ decalage].length() - 1) == escapeChar) {
						}// guillemets mais pas de virgule dedans
						else if (tokens[i + decalage].charAt(0) == escapeChar) {
							int j = 1;
							to[i] = tokens[i + decalage].substring(1);
							while (tokens[i + decalage + j].charAt(tokens[i
									+ decalage + j].length() - 1) != escapeChar) {
								to[i] = to[i] + "," + tokens[i + decalage + j];
								j++;
							}
							to[i] = to[i]
									+ ","
									+ tokens[i + decalage + j]
											.substring(0, tokens[i + decalage
													+ j].length() - 1);
							decalage += j;
						}
					}
					tokens = to;

					map.put(tokens[indexCle], new Entree(cle, tokens));
				} catch (Exception e) {
					System.out.println("Erreur : " + index + " " + line);
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * retourne l'entree ayant comme cle cle
	 * 
	 * @param cle
	 *            : cle de l'entree
	 * @return l'entree de cle cle
	 */
	public Entree get(String cle) {
		return map.get(cle);
	}

	public Map<String, Entree> getMap() {
		return map;
	}

	/**
	 * Retourne la liste triee en fonction de champ "orderBy"
	 * 
	 * @param orderBy
	 *            : titre du champs par ex: "Title" ou "Rank"
	 * @param ascending 
	 * 			  : l'ordre : true = croissant false = décroissant
	 * @return retourne la listre triee
	 */

	public List<Entree> getSortedList(String orderBy, boolean ascending) {
		List<Entree> list = new ArrayList<Entree>(map.values());
		return getSortedList(list, orderBy, ascending);
	}

	/**
	 * Retourne la liste "list" triee en fonction de champ "orderBy"
	 * 
	 * @param list
	 *            : Contient une liste d'entrees
	 * @param orderBy
	 *            : titre du champs par ex: "Title" ou "Rank"
	 * @param ascending 
	 * 			  : l'ordre : true = croissant false = décroissant
	 * @return retourne la listre triee
	 */

	private List<Entree> getSortedList(List<Entree> list, String orderBy,
			boolean ascending) {
		Collections.sort(list, new EntreeComparable(orderBy, ascending));
		return list;
	}

	/**
	 * Retourne la liste filtree en fonction de "field" et "fieldValue" et triee
	 * en fonction de champ "orderBy" Par ex: getSortedList("Title", "Rank",
	 * "A*") -> Retourne une liste triee par le "Title" de toutes les entree qui
	 * ont le "Rank" : "A*"
	 * 
	 * @param orderBy
	 *            : titre du champs par ex: "Title" ou "Rank"
	 * @param ascending 
	 * 			  : l'ordre : true = croissant false = décroissant
	 * @param field
	 *            : titre du champs a filtrer par ex: "Rank"
	 * @param fieldValue
	 *            : valeur du champs a filtrer par ex: "A*"
	 * @return retourne la listre triee
	 */

	public List<Entree> getSortedList(String orderBy, boolean ascending,
			String field, String fieldValue) {
		List<Entree> filteredList = new ArrayList<Entree>();
		for (Entree entree : new ArrayList<Entree>(map.values())) {
			if (entree.containsKey(field))
				if (entree.get(field).compareTo(fieldValue) == 0)
					filteredList.add(entree);
		}
		return getSortedList(filteredList, orderBy, ascending);
	}
}
