import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;



public class Main {
	/**
	 * @author Baptiste Degryse
	 * @param args
	 */
	private static String filename;
	public static void main(String args[]){

		String dir="";
		JFileChooser fc = new JFileChooser();
		// Demonstrate "Open" dialog:
		fc.setCurrentDirectory(new File("."));
		int rVal = fc.showOpenDialog(null);
		if (rVal == JFileChooser.APPROVE_OPTION) {
			dir=fc.getCurrentDirectory().toString()+"/"+fc.getSelectedFile().getName();
		}
		if (rVal == JFileChooser.CANCEL_OPTION) {
			dir="";
		}

		if(!dir.equals(""))
			filename=dir;
		else
			System.exit(1);
		
		ArrayList<String> ar=ReadWrite.mRead(filename);
		
		KruskalAlgorithm ka = new KruskalAlgorithm(ar);
		ka.kruskaAlgorithm();
	}
}
