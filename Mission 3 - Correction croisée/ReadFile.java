import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/*
 * Class used to read a file with a Scanner
 * @author : Thomas Bollen
 */

public class ReadFile {

	Scanner s; // Scanner used to read the file
	
	public ReadFile(String fileName){
		
		try{
			s = new Scanner(new FileReader(fileName));
		}catch (FileNotFoundException e) {
            System.out.println("An error occurred while initializing the Scanner");
            this.s=null;
        }

	}
	
	/*
	 * Function that returns true if the file has a next line and false otherwise
	 */
	public boolean hasNextLine(){
		return this.s.hasNextLine();
	}
	
	/*
	 * Function that reads the next line of the file
	 * Returns the next line if the file has a next line, otherwise returns null
	 */
	
	public String readNextLine(){
		
		if(this.s.hasNextLine()){
			return this.s.nextLine();
		}
		else{
			return null;
		
		}
		
	}
	
	/*
	 * Function that reads the next line of the file and splits it with the delimiter regex
	 * @pre : regex is not null and is a valid regular expression
	 * @post : returns an array of String (the one returned by the split function)
	 */
	
	public String[] readNextLineSplit(String regex){
		
		if(this.s.hasNextLine()){
			return (this.s.nextLine()).split(regex);
		}
		else{
			return null;
		}
	}
	
	/*
	 * Function that closes the Scanner
	 */

	public void closeScanner(){
		s.close();
	}
	
	/*
	 * Function that returns the Scanner
	 */
	
	public Scanner getScanner(){
		return this.s;
	}
	
	/*
	 * Function that sets the Scanner
	 */
	
	public void setScanner(Scanner s){
		this.s = s;
	}

}
