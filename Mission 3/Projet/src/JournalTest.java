

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

}
