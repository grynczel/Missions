

import static org.junit.Assert.*;

import org.junit.Test;

public class DictionnaireTest {
	/**
	 * @author Jerome Bertaux && Baptiste Degryse
	 */
	Dictionnaire journal;
	public DictionnaireTest(){
		this.journal=new Dictionnaire(1,"Journals.csv", ",");
	}
	@Test
	public void test() {
		System.out.print("Test :");
		Entree e = journal.get("Journal of Global Business Management");
		assertEquals("C", e.get("Rank"));
		System.out.println("ok");
	}
	
	@Test
	public void testVirguleTitre(){
		System.out.print("Virgule :");
		Entree e = journal.get("Genes, Brain and Behavior");
		assertEquals("B", e.get("Rank"));
		System.out.println("ok");
	}
	
	@Test
	public void testVirguleFoR(){
		System.out.print("VirguleFoR :");
		Entree e = journal.get("The Review of Financial Studies");
		assertEquals("Banking, Finance and Investment", e.get("FoR1 Name"));
		System.out.println("ok");
	}

}
