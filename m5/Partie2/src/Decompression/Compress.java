import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;


public class Compress {
	/**
	 * @author Baptiste Degryse
	 */
	private OutputBitStream out;
	HashMap<Character,boolean[]> map;
	private String filename;
	public Compress(String filename, HashMap<Character,boolean[]> map, String filenameOut){
		try {
			out = new OutputBitStream(filenameOut);
		} catch (IOException e) {

			e.printStackTrace();
		}
		this.map=map;
		this.filename=filename;
	}
	private void writeHeader() throws IOException{
		for(char c :map.keySet()){
			boolean[] bo=map.get(c);
			for(int i=0;i<bo.length*2;i++)
				write(bo[i/2]);
			write(false);//delimiter 01
			write(true);
			write(c);
		}
		write(true);//dernier delim 10
		write(false);
	}
	private void writeTab(boolean[] bo) throws IOException{
		for(boolean b: bo)
			write(b);
	}
	private void write(boolean b) throws IOException{
		System.out.print(b?1:0);
		out.write(b);
	}
	private void write(char c) throws IOException{
		System.out.println(c);
		out.write(c);
	}
	public void writeFile(){
		Scanner sc;
		try{
			writeHeader();
			sc=new Scanner(new File(filename));
			String line="";
			while(sc.hasNext()){
				line=sc.nextLine();
				for(int i=0; i<line.length(); i++){
					boolean b[]=map.get((Character)line.charAt(i));
					if(b!=null)
						writeTab(b);
					else
						System.out.println("erreur, char inconnu: "+line.charAt(i));
				}
			}
			writeTab(map.get((Character)'~'));
			out.close();
		}
		catch(IOException e){
			e.printStackTrace();
			try {
				out.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		if(args.length == 2){
			String fileToCompress 	= args[0];
			String fileCompress		= args[1];
			
	        Huffman huff = new Huffman();
			huff.createHuffman(fileToCompress);
			huff.getHashMap();
			
			Compress c = new Compress(fileToCompress,huff.getHashMap(),fileCompress);
			c.writeFile();			
		}else{
			System.out.println("Usage: java Compress <Compress file> <Output file>");
		}

	}
}

