import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		//lire le fichier
		ArrayList<String> file = ReadWrite.mRead(args[0]);
		for(String formule : file){
			LinkedBinaryTree<String> arbre = new LinkedBinaryTree<String>(formule, null, null);
			System.out.println(arbre.toString());
		}
	}
}
