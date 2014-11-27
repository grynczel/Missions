import java.io.IOException;
import java.util.HashMap;


public class Decompress {
	/**
	 * @author Bertaux Jerome
	 */
	private InputBitStream in;
	private String outputFile;
	private String contentCompress="";
	private HashMap<String, Character> mapConvertion = new HashMap<String, Character>();

	public Decompress(String fileCompress,String outputFile){
		try {
			in = new InputBitStream(fileCompress);
			this.outputFile = outputFile;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readFile(){
		try {
			// Lecture bit à bit du flux d'entrée et impression du bit lu
			while (true) {
				boolean bit1 = in.readBoolean();
				contentCompress += (bit1 ? 1 : 0);
			}
		}catch (IOException e) { // Exception lancée notamment en fin de fichier
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int creationMap(){
		//Creation hashmap
		String tmp="";
		String key = "";
		int character;
		int compt = 0;
		char bit1;
		char bit2;
		
		while(!tmp.equals("10")){
			tmp = "";
			bit1 = contentCompress.charAt(compt);
			tmp += bit1;
			bit2 = contentCompress.charAt(compt+1);
			tmp += bit2;
			
			if(tmp.equals("00")){
				key += "0";
				compt += 2;
			}else if(tmp.equals("11")){
				key += "1";
				compt += 2;
			}else if(tmp.equals("01")){
				compt += 2;
				character = Integer.parseInt(contentCompress.substring(compt, compt+16),2);
				mapConvertion.put(key, (char) character);
				
				key = "";
				compt += 16;
			}else{
				compt += 2;
			}
		}
		return compt;
	}
	
	public String decompressContent(int positionDebutStream){
		String result = "";
		int compt = positionDebutStream;
		String key = "";
		Character valeur = new Character('\0');
		
		while(!"~".equals(valeur.toString())){
			
			key += contentCompress.charAt(compt);
			
			valeur = mapConvertion.get(key);
			while(null == valeur){
				compt ++;
				key += contentCompress.charAt(compt);
				valeur = mapConvertion.get(key);
			}
			if(!valeur.toString().equals("~")){
				result += valeur.toString();
			}
			key = "";
			compt++;
			
		}
		
		
		return result;
	}

	public void decompressFile(){
		readFile(); //lire tout le fichier d'un coup
		String finalT = decompressContent(creationMap());
		ReadWrite.mWrite(outputFile, finalT);
	}
	
	public void test(){
		try {
			OutputBitStream out = new OutputBitStream("TESTDECOMP.txt");
			
			//key
			out.write(true);
			out.write(true);
			out.write(false);
			out.write(false);
			out.write(false);
			out.write(false);
			out.write(true);
			out.write(true);
			
			//delim 01
			out.write(false);
			out.write(true);
			
			//char
			out.write('J');
			
			//key EOF
			out.write(true);
			out.write(true);
			out.write(true);
			out.write(true);
			out.write(true);
			out.write(true);
			
			//delim 01
			out.write(false);
			out.write(true);
			
			//char
			out.write('~');
			
			//delim 10
			out.write(true);
			out.write(false);
			
			//content
			out.write(true);
			out.write(false);
			out.write(false);
			out.write(true);
			out.write(true);
			out.write(false);
			out.write(false);
			out.write(true);
			
			//EOF
			out.write(true);
			out.write(true);
			out.write(true);
			out.write(true);
			out.write(true);
			out.write(true);
			
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		if(args.length == 2){
			System.out.println("Start decompress : "+args[0]);
			Decompress d = new Decompress(args[0],args[1]);
			d.decompressFile();
			System.out.println("Finish decompress to : "+args[1]);
		}else{
			System.out.println("Usage: java Decompress <Compress file> <Output file>");
		}

	}

}
