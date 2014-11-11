
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String titre;
		char q;
		Dictionnaire dico=new Dictionnaire(1,"Journals.csv", ",");
		dico.changeTri(new String[]{"rank","title","FoR2"} ,null);
		//dico.test();

		DictionnaireTest jT=new DictionnaireTest();
		jT.test();
		jT.testVirguleFoR();
		jT.testVirguleTitre();
		
		do{	
			System.out.print("Entrez le nom d'un journal : ");
			titre = scan.nextLine();
			System.out.println(dico.get(titre));
			System.out.print("Continuer (y/n) ? ");
			q = scan.nextLine().charAt(0);
			
		}while(q == 'y' || q == 'Y');
		
		scan.close();
	}
}
