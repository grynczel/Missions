

import static org.junit.Assert.*;

import org.junit.Test;

public class DictionnaireTest {
	/**
	 * @author Jerome Bertaux && Baptiste Degryse
	 */
	Dictionnaire dictionnaire;
	public DictionnaireTest(Dictionnaire dico){
		if(dico==null)
			this.dictionnaire=new Dictionnaire(1,"Journals.csv", ",");
		else
			dictionnaire=dico;
	}
	@Test
	public void test() {
		System.out.print("Test :");
		Entree e = dictionnaire.get("Journal of Global Business Management");
		assertEquals("C", e.get("Rank"));
		System.out.println("ok");
	}
	
	@Test
	public void testVirguleTitre(){
		System.out.print("Virgule :");
		Entree e = dictionnaire.get("Genes, Brain and Behavior");
		assertEquals("B", e.get("Rank"));
		System.out.println("ok");
	}
	
	@Test
	public void testVirguleFoR(){
		System.out.print("VirguleFoR :");
		Entree e = dictionnaire.get("The Review of Financial Studies");
		assertEquals("Banking, Finance and Investment", e.get("FoR1 Name"));
		System.out.println("ok");
	}

}
