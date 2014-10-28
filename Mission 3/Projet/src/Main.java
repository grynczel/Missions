

public class Main {

	public static void main(String[] args) {
		Journal journal=new Journal(1,"Journals.csv", ",");
		System.out.println(journal.map.get("Journal of Global Business Management"));
	}
}
