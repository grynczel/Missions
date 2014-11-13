import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

/*
 * 
 * Author : Charles Jacquet
 * 
 * Le but de cette partie est de faire une un test de performance pour la afficher la liste de manière ordonée
 */


public class Performance {

	public static void Performe(){
		Dictionnaire dico = null;
		int i,j;
		long startTime, endTime, mean, sum=0;
		List<Entree> list;

		//pour écrire dans le fichier il me faut un printwriter:
		try {
			PrintWriter print = ReadWrite.openOutput("performance.csv");


			for(i=1; i<100000 ; i = i+100){
				dico = new Dictionnaire(1, "Journals.csv", ",", i); // créer un dictionnaire avec N éléments .. 
				for(j=0;j<10;j++){ // calculer le temps 10 fois pour avoir une bonne moyenne
					startTime = System.nanoTime();
					list = dico.getSortedList("Title",true); 
					endTime = System.nanoTime();
					sum += endTime-startTime;
				}
				mean = sum/10;
				String s = "";
				s = s + i + "," + mean;
				print.println(s);
				//System.out.println(s);
			}
			print.flush();
			print.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
