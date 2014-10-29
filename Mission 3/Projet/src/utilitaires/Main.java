package utilitaires;
import java.util.Scanner;

import modele.Journal;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String titre;
		char q;
		Journal journal=new Journal(1,"Journals.csv", ",");
		
		//System.out.println(journal.getMap().get("Journal of Global Business Management"));
		do{	
			System.out.print("Entrez le nom d'un journal : ");
			titre = scan.nextLine();
			System.out.println(journal.get(titre));
			System.out.print("Continuer (Y/N) ? ");
			q = scan.nextLine().charAt(0);
			
		}while(q == 'y');
		
		scan.close();
	}
}
