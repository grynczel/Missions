

import static org.junit.Assert.*;

import org.junit.Test;

public class JournalTest {
	/**
	 * @author Jerome Bertaux
	 */

	@Test
	public void test() {
		Journal journal=new Journal(1,"Journals.csv", ",");
		Entree e = journal.get("Journal of Global Business Management");
		assertEquals("C", e.get("Rank"));
		
	}
	
	@Test
	public void testVirguleTitre(){
		Journal journal=new Journal(1,"Journals.csv", ",");
		Entree e = journal.get("Genes, Brain and Behavior");
		assertEquals("B", e.get("Rank"));
		
	}
	
	@Test
	public void testVirguleFoR(){
		Journal journal=new Journal(1,"Journals.csv", ",");
		Entree e = journal.get("The Review of Financial Studies");
		assertEquals("Banking, Finance and Investment", e.get("FoR1 Name"));
		
	}

}
