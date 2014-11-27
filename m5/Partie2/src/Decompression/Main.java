import java.util.HashMap;


public class Main {
	public static void main(String[] args){
		String fileToCompress = "test.txt";
		String fileCompress="binOut.txt";
		String fileDecompress="resultTest.txt";
		
		
        Huffman huff = new Huffman();
		huff.createHuffman("test.txt");
		huff.getHashMap();
		
		Compressor c=new Compressor(fileToCompress,huff.getHashMap(),fileCompress);
		c.writeFile(); // test r�ussi :D
		
		Decompress d= new Decompress(fileCompress, fileDecompress);
		d.decompressFile();

	}
}
