import java.util.HashMap;


public class Main {
	public static void main(String[] args){
		HashMap<Character,boolean []> hm=new HashMap<Character,boolean[]>();
		hm.put('A', new boolean[]{true,true,true});
		hm.put('B', new boolean[]{false,false,false});
		Compressor c=new Compressor("test.txt",hm,"binOut.txt");
		c.writeFile(); // test réussi :D
	}
}
