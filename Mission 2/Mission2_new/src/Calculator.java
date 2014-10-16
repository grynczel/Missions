

public class Calculator {
	/**
	 * @author Baptiste Degryse
	 */
	
	public static LinkedBinaryTree<String> derive(LinkedBinaryTree<String> t){
		LinkedBinaryTree<String> t2=null;
		
		if(t.element().equals("+"))
			t2= new LinkedBinaryTree<String>("+",derive(t.leftTree()),derive(t.rightTree()));
			
		else if(t.element().equals("-"))
				t2= new LinkedBinaryTree<String>("-",derive(t.leftTree()),derive(t.rightTree()));
		
		else if(t.element().equals("*"))
			t2= new LinkedBinaryTree<String>("+",
					new LinkedBinaryTree<String>("*",derive(t.leftTree()),t.rightTree()),
					new LinkedBinaryTree<String>("*",t.leftTree(),derive(t.rightTree())));
		
		else if(t.element().equals("/"))
					t2= new LinkedBinaryTree<String>("/",
				new LinkedBinaryTree<String>("-",
					new LinkedBinaryTree<String>("*",derive(t.leftTree()),t.rightTree()),
					new LinkedBinaryTree<String>("*",t.leftTree(),derive(t.rightTree()))),
				new LinkedBinaryTree<String>("*",t.rightTree(),t.rightTree()));
		
		else if(t.element().equals("^"))
					t2= 
					new LinkedBinaryTree<String>("*",
							t.rightTree(),
							new LinkedBinaryTree<String>("^",
							t.leftTree(),
							new LinkedBinaryTree<String>("-",t.rightTree(),
								new LinkedBinaryTree<String>("1",null,null))));
		
		else if(t.element().equals("sin"))
				t2= new LinkedBinaryTree<String>("*",
				new LinkedBinaryTree<String>("cos",t.leftTree(),null),
				derive(t.leftTree()));
		
		else if(t.element().equals("cos"))
				t2= new LinkedBinaryTree<String>("*",
			new LinkedBinaryTree<String>("-1",null,null),
			new LinkedBinaryTree<String>("*",
				new LinkedBinaryTree<String>("sin",t.leftTree(),null),
				derive(t.leftTree())));
		
		else if(t.element().equals("x"))
			t2= new LinkedBinaryTree<String>("1",null,null);
		
		else if(t.element().matches("-?\\d+"))//nombre
			t2= new LinkedBinaryTree<String>("0",null,null);
		
		else
			System.out.println("element inconnu : "+ t.element());
		
		
		return t2;
		
	}
}
