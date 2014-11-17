
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		long startTime, endTime;

		startTime = System.nanoTime();

		Scanner scan = new Scanner(System.in);
		String titre;
		char q;
		/*
<<<<<<< HEAD
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
=======
*/
		Dictionnaire journal=new Dictionnaire(1,"Journals.csv", ",");
		
		journal=new Dictionnaire(1,"Journals.csv", ",");
		DictionnaireTest jT=new DictionnaireTest();
		jT.test();
		jT.testVirguleFoR();
		jT.testVirguleTitre();

		do{	
			System.out.print("Entrez le nom d'un journal : ");
			titre = scan.nextLine();
			
			Entree entree = journal.get(titre);
			if(entree == null){
				System.out.println("Not found!");
			}else{
				System.out.println(journal.get(titre));
			}
			System.out.print("Continuer (y/n) ? ");
			q = scan.nextLine().charAt(0);
		}while(q == 'y' || q == 'Y');
		scan.close();

	}
}
