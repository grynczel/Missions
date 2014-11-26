<<<<<<< HEAD

public class Main {
	public static void main(String[] args) {
		Huffman huff = new Huffman();
		huff.createHuffman("coucou.txt");
		System.out.println(huff.toString());
=======
import java.util.HashMap;


public class Main {
	public static void main(String[] args){
		HashMap<Character,boolean []> hm=new HashMap<Character,boolean[]>();
		hm.put('A', new boolean[]{true,true,true});
		hm.put('B', new boolean[]{false,false,false});
		Compressor c=new Compressor("test.txt",hm,"binOut.txt");
		c.writeFile(); // test r�ussi :D
>>>>>>> 1158082a099bc58d20f3238a7b8ee5b550171560
	}
}
