

public class Calculator {

	public Calculator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static LinkedBinaryTree<String> derive(LinkedBinaryTree<String> t){
		LinkedBinaryTree<String> t2=null;
		switch(t.element()){
		case "+": t2= new LinkedBinaryTree<String>("+",derive(t.leftTree()),derive(t.rightTree()));
			break;
		case "-": t2= new LinkedBinaryTree<String>("-",derive(t.leftTree()),derive(t.rightTree()));
			break;
		case "*": t2= new LinkedBinaryTree<String>("+",
				new LinkedBinaryTree<String>("*",derive(t.leftTree()),t.rightTree()),
				new LinkedBinaryTree<String>("*",t.leftTree(),derive(t.rightTree())));
			break;
		case "/": t2= new LinkedBinaryTree<String>("/",
				new LinkedBinaryTree<String>("-",
					new LinkedBinaryTree<String>("*",derive(t.leftTree()),t.rightTree()),
					new LinkedBinaryTree<String>("*",t.leftTree(),derive(t.rightTree()))),
				new LinkedBinaryTree<String>("*",t.rightTree(),t.rightTree()));
			break;
		case "^": t2= new LinkedBinaryTree<String>("*",
					new LinkedBinaryTree<String>("^",
						t.leftTree(),
						new LinkedBinaryTree<String>("-",t.rightTree(),
													new LinkedBinaryTree<String>("1",null,null))),
					t.rightTree());
			break;
		case "sin": t2= new LinkedBinaryTree<String>("*",
				new LinkedBinaryTree<String>("cos",t.leftTree(),null),
				derive(t.leftTree()));
			break;
		case "cos": t2= new LinkedBinaryTree<String>("*",
			new LinkedBinaryTree<String>("-1",null,null),
			new LinkedBinaryTree<String>("*",
				new LinkedBinaryTree<String>("sin",t.leftTree(),null),
				derive(t.leftTree())));
			break;
		}
		return t2;
		
	}
}
