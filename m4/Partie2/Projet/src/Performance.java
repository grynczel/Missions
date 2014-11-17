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
		long meanList, sumList=0; //variable pour le find
		List<Entree> list=null;

		//pour écrire dans le fichier il me faut un printwriter:
		try {
			PrintWriter printOrderedList = ReadWrite.openOutput("performance.csv");
			PrintWriter printFind = ReadWrite.openOutput("performanceFind.csv");


			for(i=0; i<20500 ; i = i+100){
				dico = new Dictionnaire(1, "Journals.csv", ",", i+1); // créer un dictionnaire avec i éléments .. 
				for(j=0;j<10;j++){ // calculer le temps 10 fois pour avoir une bonne moyenne
					startTime = System.nanoTime();
					list = dico.getSortedList("Title",true); 
					endTime = System.nanoTime();
					sum += endTime-startTime;
				}
				mean = sum/10; // on a sommé tous les temps donc on divise pour faire la moyenne
				printOrderedList.println(i + "," + mean); // on écris dans le fichier csv
				sum = 0; // on remet la somme à 0
				
				Object listEntry[] = dico.getMap().keySet().toArray();//permet d'avoir une liste de toutes le clé
				String entry;
				for(j=0;j<i && j<listEntry.length;j++){
					entry = listEntry[j].toString();
					startTime = System.nanoTime();
					dico.containsKey(entry); 
					endTime = System.nanoTime();
					sumList += endTime-startTime;
				}
				meanList = sumList/(j+1);
				sumList=0;
				printFind.println(i + "," + meanList);
	
			}
			printOrderedList.close();
			printFind.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
