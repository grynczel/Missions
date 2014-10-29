package tests;

import static org.junit.Assert.*;
import modele.Entree;
import modele.Journal;

import org.junit.Test;

public class JournalTest {

	@Test
	public void test() {
		Journal journal=new Journal(1,"Journals.csv", ",");
		Entree e = journal.getMap().get("Journal of Global Business Management");
		assertEquals("C", e.get("Rank"));
		
	}

}
