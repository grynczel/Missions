
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static Dictionnaire dictionnaire;
	public static void main(String[] args) {
		long startTime, endTime;

		startTime = System.nanoTime();

		Scanner scan = new Scanner(System.in);
		String titre;
		char q;
		Fenetre fen=new Fenetre();
		//Dictionnaire journal=new Dictionnaire(1,"Journals.csv", ",");
		
		//journal=new Dictionnaire(1,"Journals.csv", ",");
		DictionnaireTest jT=new DictionnaireTest(dictionnaire);
		jT.test();
		jT.testVirguleFoR();
		jT.testVirguleTitre();

		//System.out.println(journal.getMap());
		endTime = System.nanoTime();
		long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("Init time : " + duration/1000000 +"ms" );
		//201
		ArrayList<Entree> list = dictionnaire.getSortedList("Title", "FoR2 Name", "Business and Management");
		System.out.println(list);
		fen.printResult(list);
		
//		//System.out.println(journal.getMap().get("Journal of Global Business Management"));
//		do{	
//			System.out.print("Entrez le nom d'un journal : ");
//			titre = scan.nextLine();
//			
//			Entree entree = journal.get(titre);
//			if(entree == null){
//				System.out.println("Not found!");
//			}else{
//				System.out.println(journal.get(titre));
//			}
//			System.out.print("Continuer (y/n) ? ");
//			q = scan.nextLine().charAt(0);
//		}while(q == 'y' || q == 'Y');
//		scan.close();

	}
	public static void setDictionnaire(Dictionnaire dico){
		dictionnaire=dico;
	}
	public static Dictionnaire getDictionnaire(){
		return dictionnaire;
	}
}
