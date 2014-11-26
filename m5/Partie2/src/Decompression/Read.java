import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Read {
	BufferedReader reader = null;
	public Read(String DocName){
		try{
			reader = new BufferedReader(new FileReader(DocName));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	/*
	 * @pre
	 * @post retourne un String contenant la ligne, ou null si le fichier est vide
	 */
	public String getLine(){
		String text=null;
		try{
			text=reader.readLine();
		}catch(IOException e){
			e.printStackTrace();
		}
		return text;
	}
	
	public void close(){
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
