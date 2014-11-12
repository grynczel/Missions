import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String titre;
		char q;
		Dictionnaire journal=new Dictionnaire(1,"Journals.csv", ",");
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
