import java.util.HashMap;


public class Main {
	public static void main(String[] args){
		String fileToCompress = "test.txt";
		String fileCompress="binOut.txt";
		String fileDecompress="resultTest.txt";
		
		
        Huffman huff = new Huffman();
		huff.createHuffman(fileToCompress);
		huff.getHashMap();
		
		Compress c=new Compress(fileToCompress,huff.getHashMap(),fileCompress);
		c.writeFile(); // test r�ussi :D
		
		System.out.println("");
		System.out.println("----------");
		
		Decompress d= new Decompress(fileCompress, fileDecompress);
		//d.test();
		d.decompressFile();

	}
}
