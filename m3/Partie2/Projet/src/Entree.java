


public class Entree implements Comparable<Entree>{
	/**
	 * @author Baptiste Degryse
	 */
	//private HashMap<String, String> contenu=new HashMap<String,String>();
	private String []valeur;
	private String []cle;
	public static String empty="none";
	private Dictionnaire dico;
	
	/**
	 * Cree une entree
	 * @param cle: Contient les cles associees aux valeurs.
	 * @param valeur: Contient les valeurs associees aux cle. La taille de valeur doit etre plus petit ou egale a la taille de cle.
	 */
	public Entree(String[] cle, String[] valeur,Dictionnaire dico){
		this.dico=dico;
		this.valeur=new String[cle.length];
		int i;
		for(i=0;i<valeur.length && valeur[i]!=null;i++){
			this.valeur[i]=valeur[i];
		}
		for(;i<cle.length;i++){
			this.valeur[i]=empty;
		}
		this.cle=cle;
	}
	/**
	 * Retourne la valeur associee au champ
	 * @param cle : champ de la valeur qui est retournee
	 * @return retourne le champs de l'entree associe a la cle
	 */
	public String get(String cle){
		for(int i=0;i<this.cle.length;i++){
			if(this.cle[i].equals(cle))
				return valeur[i];
		}
		return null;
	}
	//@override
	public String toString(){
		String s="";
		for(int i=0;i<cle.length;i++){
			s+= cle[i] + ": "+ valeur[i] +"\n";
		}
		return s;
	}
	@Override
	public int compareTo(Entree e) {
		String titre[]=dico.getOrdreTitre();
		boolean asc[]=dico.getAsc();
		for(int i=0;i<titre.length;i++){
			if(!get(titre[i]).equals(e.get(titre[i]))){
				int comp=get(titre[i]).compareTo(e.get(titre[i]));
				if(asc[i])
					return comp;
				else
					return -comp;
			}
		}
		return 0;
	}
}