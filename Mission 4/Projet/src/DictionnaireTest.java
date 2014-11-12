import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class DictionnaireTest {
	/**
	 * @author Jerome Bertaux && Baptiste Degryse && Grynczel Wojciech
	 */
	private Dictionnaire dictionnaire;

	public DictionnaireTest() {
		//long startTime = System.nanoTime();
		dictionnaire = new Dictionnaire(1, "Journals.csv", ",");
		//long endTime = System.nanoTime();
		//long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		//System.out.println("Init time : " + duration/1000000 +"ms" );
	}

	@Test
	public void testNumberOfJournals() {
		// TODO pk on a 20707 elements et pas 20714?
		assertEquals(dictionnaire.getMap().size(), 20707);
	}

	@Test
	public void testFindJournalCorrectTitle() {
		Entree e = dictionnaire.get("Fragmenta Faunistica");
		assertEquals(e.get("Rank"), "C");
		assertEquals(e.get("Title"), "Fragmenta Faunistica");
		assertEquals(e.get("FoR1"), "608");
		assertEquals(e.get("FoR1 Name"), "Zoology");
		assertEquals(e.get("FoR2"), "none");
		assertEquals(e.get("FoR2 Name"), "none");
		assertEquals(e.get("FoR3"), "none");
		assertEquals(e.get("FoR3 Name"), "none");
	}

	@Test
	public void testFindJournalInvalidTitle() {
		Entree e = dictionnaire.get("Blabla bla ");
		assertEquals(e, null);
	}

	@Test
	public void testFindJournalTitleWithComma() {
		Entree e = dictionnaire.get("Genes, Brain and Behavior");
		assertEquals("B", e.get("Rank"));
	}

	@Test
	public void testFindJournalFoR1WithComma() {
		Entree e = dictionnaire.get("The Review of Financial Studies");
		assertEquals("Banking, Finance and Investment", e.get("FoR1 Name"));
	}

	@Test
	public void testGetListSortedByTitleAscending() {
		List<Entree> list = dictionnaire.getSortedList("Title", true);
		boolean isSorted = true;
		for (int i = 0; i < list.size() - 1; i++) {
			if (list.get(i).get("Title")
					.compareTo(list.get(i + 1).get("Title")) > 0) {
				isSorted = false;
				break;
			}
		}
		assertEquals(isSorted, true);
	}
	
	@Test
	public void testGetListSortedByTitleDescending() {
		List<Entree> list = dictionnaire.getSortedList("Title", false);
		boolean isSorted = true;
		for (int i = 0; i < list.size() - 1; i++) {
			if (list.get(i).get("Title")
					.compareTo(list.get(i + 1).get("Title")) < 0) {
				isSorted = false;
				break;
			}
		}
		assertEquals(isSorted, true);
	}

	@Test
	public void testGetListSortedByRank() {
		List<Entree> list = dictionnaire.getSortedList("Rank", true);
		boolean isSorted = true;
		for (int i = 0; i < list.size() - 1; i++) {
			if (list.get(i).get("Rank").compareTo(list.get(i + 1).get("Rank")) > 0) {
				isSorted = false;
				break;
			}
		}
		assertEquals(isSorted, true);
	}
	
	@Test
	public void testGetListSortedByTitleForFor1Type202() {
		List<Entree> list = dictionnaire.getSortedList("Title",true, "FoR1", "2004");
		boolean isSorted = true;
		for (int i = 0; i < list.size() - 1; i++) {
			if (list.get(i).get("Title").compareTo(list.get(i + 1).get("Title")) > 0) {
				isSorted = false;
				break;
			}
		}
		assertEquals(isSorted, true);
	}
	
	@Test
	public void testGetListSortedByTitleForFor1NameTypeLinguistics() {
		List<Entree> list = dictionnaire.getSortedList("Title",true, "FoR1 Name", "Linguistics");
		boolean isSorted = true;
		for (int i = 0; i < list.size() - 1; i++) {
			if (list.get(i).get("Title").compareTo(list.get(i + 1).get("Title")) > 0) {
				isSorted = false;
				break;
			}
		}
		assertEquals(isSorted, true);
	}
	
	@Test
	public void testGetListSortedByTitleForInexistingCategory() {
		List<Entree> list = dictionnaire.getSortedList("Title",true, "BLABLA", "abcd");
		assertEquals(list.size(), 0);
	}
	
	@Test
	public void testGetListSortedByTitleRankTypeAAscending() {
		List<Entree> list = dictionnaire.getSortedList("Title",true, "Rank", "A");
		boolean isSorted = true;
		for (int i = 0; i < list.size() - 1; i++) {
			if (list.get(i).get("Title").compareTo(list.get(i + 1).get("Title")) > 0) {
				isSorted = false;
				break;
			}
		}
		assertEquals(isSorted, true);
	}
	
	@Test
	public void testGetListSortedByTitleRankTypeADescending() {
		List<Entree> list = dictionnaire.getSortedList("Title",false, "Rank", "A");
		boolean isSorted = true;
		for (int i = 0; i < list.size() - 1; i++) {
			if (list.get(i).get("Title").compareTo(list.get(i + 1).get("Title")) < 0) {
				isSorted = false;
				break;
			}
		}
		assertEquals(isSorted, true);
	}
}
