
public class Main {
	public static void main(String[] args) {
		Huffman huff = new Huffman();
		huff.createHuffman("coucou.txt");
		System.out.println(huff.toString());
	}
}
