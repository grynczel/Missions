

public class Main {
	/*
	public static void main(String[] args) {
		//lire le fichier
		ArrayList<String> file = ReadWrite.mRead(args[0]);
		for(String formule : file){
			LinkedBinaryTree<String> arbre = new LinkedBinaryTree<String>(formule, null, null);
			System.out.println(arbre.toString());
		}
	}
	*/
	public static void main(String[] args){
		LinkedBinaryTree<String> t2= new LinkedBinaryTree<String>("*",
				new LinkedBinaryTree<String>("-1",null,null),
				new LinkedBinaryTree<String>("*",
					new LinkedBinaryTree<String>("sin",new LinkedBinaryTree<String>("x",null,null),null),
					new LinkedBinaryTree<String>("1",null,null)));
		System.out.println(t2);
		System.out.println(Calculator.derive(t2));
		
		LinkedBinaryTree<String> t3= new LinkedBinaryTree<String>("/",
				new LinkedBinaryTree<String>("2",null,null),
				new LinkedBinaryTree<String>("x",null,null));
		System.out.println(t3);
		System.out.println(Calculator.derive(t3));
		
		LinkedBinaryTree<String> t4= new LinkedBinaryTree<String>("^",
				new LinkedBinaryTree<String>("x",null,null),
				new LinkedBinaryTree<String>("5",null,null));
		System.out.println(t4);
		System.out.println(Calculator.derive(t4));
		
	}
}
