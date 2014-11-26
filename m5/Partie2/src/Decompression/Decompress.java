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
				System.out.print (bit1 ? 1 : 0);
				
				
			}
		}catch (IOException e) { // Exception lancée notamment en fin de fichier
			System.out.println("");
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void decompressFile(){
		readFile(); //lire tout le fichier d'un coup
		
		//Creation hashmap
		String tmp="";
		String oldTmp = "";
		String key = "";
		int character;
		int compt = 0;
		char bit1;
		char bit2;
		while(oldTmp.equals("10") && oldTmp.equals(tmp)){
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
			}else if(tmp.equals("10") && !oldTmp.equals("10")){
				character = Integer.parseInt(contentCompress.substring(compt, compt+8));
				mapConvertion.put(key, (char) character);
				
				key = "";
				compt += 9;
			}
				
			oldTmp = tmp;
		}

	}

	public static void main(String[] args) {
		if(args.length == 2){
			Decompress d = new Decompress(args[0],args[1]);
			d.decompressFile();
		}else{
			System.out.println("Usage: java Decompress <Compress file> <Output file>");
		}

	}

}
