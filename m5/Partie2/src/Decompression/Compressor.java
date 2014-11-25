import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;


public class Compressor {
	private OutputBitStream out;
	HashMap<Character,boolean[]> map;
	private String filename;
	public Compressor(String filename, HashMap<Character,boolean[]> map, String filenameOut){
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
				out.write(bo[i/2]);
			out.write(false);//delimiter
			out.write(true);
			out.write(c);
		}
		out.write(true);//dernier delim
		out.write(false);
	}
	private void writeTab(boolean[] bo) throws IOException{
		for(boolean b: bo)
			out.write(b);
	}
	public void writeFile(){
		Scanner sc;
		try{
			writeHeader();
			sc=new Scanner(filename);
			String line="";
			while(sc.hasNext()){
				line=sc.nextLine();
				for(int i=0; i<line.length(); i++){
					writeTab(map.get(line.charAt(i)));
				}
			}
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
}

