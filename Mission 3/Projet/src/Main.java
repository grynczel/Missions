
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String titre;
		char q;
		Journal journal=new Journal(1,"Journals.csv", ",");

		JournalTest jT=new JournalTest();
		jT.test();
		jT.testVirguleFoR();
		jT.testVirguleTitre();
		//System.out.println(journal.getMap());
		
		//System.out.println(journal.getMap().get("Journal of Global Business Management"));
		do{	
			System.out.print("Entrez le nom d'un journal : ");
			titre = scan.nextLine();
			System.out.println(journal.get(titre));
			System.out.print("Continuer (y/n) ? ");
			q = scan.nextLine().charAt(0);
			
		}while(q == 'y' || q == 'Y');
		
		scan.close();
	}
}
