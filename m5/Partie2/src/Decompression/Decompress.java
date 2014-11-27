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
				//System.out.print (bit1 ? 1 : 0);
				
				
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
		System.out.println(contentCompress);
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
			
			System.out.println(tmp);
			
			if(tmp.equals("00")){
				key += "0";
				compt += 2;
			}else if(tmp.equals("11")){
				key += "1";
				compt += 2;
			}else if(tmp.equals("01")){
				compt += 2;
				//System.out.println(contentCompress.substring(compt, compt+8));
				character = Integer.parseInt(contentCompress.substring(compt, compt+7));
				System.out.println(key+"::"+(char)character);
				mapConvertion.put(key, (char) character);
				
				key = "";
				compt += 8;
			}else{
				compt += 2;
			}
		}

		System.out.println("END HEAD : "+mapConvertion);
		return compt;
	}
	
	public String decompressContent(int positionDebutStream){
		String result = "";
		int compt = positionDebutStream;
		String key = "";
		Character valeur = null;
		
		System.out.println("");
		
		while(compt < contentCompress.length()){
			
			key += contentCompress.charAt(compt);
			
			valeur = mapConvertion.get(key);
			while(null == valeur){
				compt ++;
				key += contentCompress.charAt(compt);
				valeur = mapConvertion.get(key);
				//System.out.println(valeur+"-"+key);
			}
			System.out.println(valeur.toString()+"-"+key);
			result += valeur.toString();
			
		}
		
		
		return result;
	}

	public void decompressFile(){
		readFile(); //lire tout le fichier d'un coup
		String finalT = decompressContent(creationMap());
		//System.out.println(finalT);
		ReadWrite.mWrite(outputFile, finalT);
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
