import java.util.List;

public class Journal {
	private String rank;
	private String title;
	private List types;
	public Journal(String rank, String title, List types) {
		super();
		this.rank = rank;
		this.title = title;
		this.types = types;
	}
	public String getRank() {
		return rank;
	}
	public String getTitle() {
		return title;
	}
	public List getTypes() {
		return types;
	}
}