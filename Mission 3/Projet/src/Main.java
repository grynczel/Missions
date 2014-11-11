
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		long startTime, endTime;

		startTime = System.nanoTime();

		Scanner scan = new Scanner(System.in);
		String titre;
		char q;
		Journal journal=new Journal(1,"Journals.csv", ",");
		
		journal=new Journal(1,"Journals.csv", ",");
		JournalTest jT=new JournalTest();
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
