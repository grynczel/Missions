
import java.util.HashMap;


public class Entree {
	/**
	 * @author Baptiste Degryse
	 */
	private HashMap<String, String> contenu=new HashMap<String,String>();
	private String []cle;
	public static String empty="";
	
	/**
	 * Cree une entree
	 * @param cle: Contient les cles associees aux valeurs.
	 * @param valeur: Contient les valeurs associees aux cle. La taille de valeur doit etre plus petit ou egale a la taille de cle.
	 */
	public Entree(String[] cle, String[] valeur){
		for(int i=0;i<valeur.length;i++){
			contenu.put(cle[i],valeur[i]);
		}
		for(int i=valeur.length;i<cle.length;i++){
			contenu.put(cle[i],empty);
		}
		this.cle=cle;
	}
	/**
	 * Retourne la valeur associee au champ
	 * @param cle : champ de la valeur qui est retournee
	 * @return retourne le champs de l'entree associe a la cle
	 */
	public String get(String cle){
		return contenu.get(cle);
	}
	//@override
	public String toString(){
		String s="";
		for(int i=0;i<cle.length;i++){
			s+= cle[i] + ": "+ contenu.get(cle[i]) +"\n";
		}
		return s;
	}
}
