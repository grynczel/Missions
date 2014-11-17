import java.util.Scanner;

/*
 * Main class of the mission used to carry out the research in the hash table
 * @author : Thomas Bollen
 */
public class Main {

	public static void main(String[] args) {
		
		tests();
		
		HashTableJournals ht = new HashTableJournals();
		
		int err = ht.fillHashTableJournals("Journals.csv");

		if(err==-1){
			System.out.println("Error while reading the file");
			return;
		}
		
		Scanner reader = new Scanner(System.in);
		
		boolean done = false;
		
		while(!done){
			
			System.out.println("Please enter the name of a journal or Stop to stop the program");
			
			String journal=reader.nextLine();
			
			if(journal.equals("Stop")){
				done = true;
			}
			else{
				
				Journal foundJournal = ht.getHashTable().get(journal);
				
				if(foundJournal!=null){
					System.out.println("The rank of this journal is : " + foundJournal.getRank());
				}
				else{
					System.out.println("No such journal found");
				}
			}
			
		}
		
		System.out.println("End of the program");
		
		reader.close();
	}
	
	public static void tests(){
		
		HashTableJournals ht = new HashTableJournals();
		
		int err = ht.fillHashTableJournals("Journals.csv");
	
		if(err==-1){
			System.out.println("Error while reading the file");
			return;
		}
		
		String journal1 = "Holocaust Studies: a journal of culture and history"; //rank = C
		String journal2 = "\"International Journal of the Computer, the Internet and Management\"";//rank = C
		String journal3 = "International Journal of Medical Engineering and Informatics"; //not ranked
		String journal4 = "\"Journal of Critical Psychology, Counselling, and Psychotherapy\""; //rank  = C
		String journal5 = "International Review of Financial Analysis"; // rank B
		String journal6 = "Monthly Notices of the Royal Astronomical Society: Letters"; //rank A*
		String journal7 = "International Review of Financial Analysis"; //rank B
				
		if(ht.getHashTable().get(journal1) == null || !ht.getHashTable().get(journal1).getRank().equals("C")){
			System.out.println("Journal1's rank was not correct or Journal1 was not found");
		}
		else{
			System.out.println("Test for journal 1 : OK");
		}
		
		if(ht.getHashTable().get(journal2) == null || !ht.getHashTable().get(journal2).getRank().equals("C")){
			System.out.println("Journal2's rank was not correct or Journal1 was not found");
		}
		else{
			System.out.println("Test for journal 2 : OK");
		}
		
		if(ht.getHashTable().get(journal3) == null || !ht.getHashTable().get(journal3).getRank().equals("Not ranked")){
			System.out.println("Journal3's rank was not correct or Journal1 was not found");
		}
		else{
			System.out.println("Test for journal 3 : OK");
		}
		
		if(ht.getHashTable().get(journal4) == null || !ht.getHashTable().get(journal4).getRank().equals("C")){
			System.out.println("Journal4's rank was not correct or Journal1 was not found");
		}
		else{
			System.out.println("Test for journal 4 : OK");
		}
		
		if(ht.getHashTable().get(journal5) == null || !ht.getHashTable().get(journal5).getRank().equals("B")){
			System.out.println("Journal5's rank was not correct or Journal1 was not found");
		}
		else{
			System.out.println("Test for journal 5 : OK");
		}
		
		if(ht.getHashTable().get(journal6) == null || !ht.getHashTable().get(journal6).getRank().equals("A*")){
			System.out.println("Journal6's rank was not correct or Journal1 was not found");
		}
		else{
			System.out.println("Test for journal 6 : OK");
		}
		
		if(ht.getHashTable().get(journal7) == null || !ht.getHashTable().get(journal7).getRank().equals("B")){
			System.out.println("Journal7's rank was not correct or Journal1 was not found");
		}
		else{
			System.out.println("Test for journal 7 : OK");
		}
	
		
	}

}
