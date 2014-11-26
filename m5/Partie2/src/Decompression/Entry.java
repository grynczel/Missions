
public class Entry implements Comparable{
	public char c;
	public int freq;
	
	public Entry(char c, int freq){
		this.c = c;
		this.freq = freq;
	}
	
	public int compareTo(Object o){
		return this.freq - ((Entry)o).freq;
	}
	
	public String toString(){
		return "[" + c+ ":" + freq+ "]";
	}
}
