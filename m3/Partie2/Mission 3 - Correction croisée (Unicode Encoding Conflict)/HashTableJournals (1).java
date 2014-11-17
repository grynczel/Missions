import java.util.Hashtable;

/*
 * Class used to store the objects of type Journal in a hash table
 * @author : Thomas Bollen
 */

public class HashTableJournals {

	Hashtable<String, Journal> journals; //Hashtable containing the journals
	
	//The following parameters may be edited to improve the performances 
	
	int numJournals = 20714; //number of journals to be stored
	
	float loadFactor = 0.75f; //Hashtable's load factor
	
	float initialCapacityFactor = 1.25f; //Hashtable's initial capacity = initialCapacityFactor*numJournals
	
	//Don't edit below
	
	int size = Math.round((float)numJournals*initialCapacityFactor); //Hashtable's initial capacity
	
	public HashTableJournals(){
		this.journals = new Hashtable<String,Journal>(size,loadFactor);
	}
	
	/*
	 * Function that fills a hash table with the journals read in fileName
	 * @pre : fileName is not null and is an existing file name
	 * @post : the hash table has been filled with the journals
	 * Returns -1 if an error occurred, otherwise returns 1 
	 */
	
	public int fillHashTableJournals(String fileName){
		
		ReadFile rf = new ReadFile(fileName);
		
		if(rf.getScanner()==null){
			return -1;
		}
		
		rf.readNextLine(); //skip the first line
				
		while(rf.hasNextLine()){
			
			String values[] = rf.readNextLineSplit(",(?=\\S)"); //Split the line with delimiter
																// comma followed by a character

			if(values.length>0){
				//if values.length==0; doesn't create a new Journal

				Journal newJournal = new Journal(values);
				journals.put(newJournal.getName(), newJournal);
			}
			
		}

		rf.closeScanner();
		return 1;
	}
	
	/*
	 * Function that returns journals
	 */
	
	public Hashtable<String,Journal> getHashTable(){
		return journals;
	}
	
	/*
	 * Function that sets journals with the value "journals"
	 */
	
	public void setHashTable(Hashtable<String,Journal> journals){
		this.journals = journals;
	}
}
