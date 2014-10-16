
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author charles jacquet
 *
 * @param <E>
 */
public class LinkedBinaryTree<E> implements RBinaryTree<E>, Position<E>{
	// variable d'instance:
	private LinkedBinaryTree<E> left;
	private LinkedBinaryTree<E> right;
	private E element;
	private int size;

	// constructeur:
	public LinkedBinaryTree(E elem, LinkedBinaryTree<E> leftChild,
			LinkedBinaryTree<E> rightChild) {
		element = elem;
		left = leftChild;
		right = rightChild;
		if (elem == null)
			size = 0;// pas d'�l�ment dans la liste ..
		else
			size = 1; // si il y a un element la taille est de 1
		if (left != null)
			size += left.size(); // rajoute de la taille des enfants
		if (right != null)
			size += right.size();
	}

	/*
	 * 29 * @post: impl�mentation de l'interface position 30
	 */
	@Override
	public E element() {
		return element;
	}

	/*
	 * @post: impl�mentation de l'interface RBinaryTree, renvoie true si l'arbre
	 * est vide
	 */
	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	/*
	 * 43 * @post: impl�mentation de l'interface RBinaryTree, renvoie la taille
	 * de l'arbre 44
	 */
	@Override
	public int size() {
		return size;
	}

	/*
	 * @post: impl�mentation de l'interface RBinaryTree, renvoie la position de
	 * root. 51 * Mais vu que nous impl�mentons le fait qu'un arbre est soit
	 * vide, soit 52 * il contient un noeud racine avec un fils gauche et un
	 * fils droite. 53 * D�s lors, chaque noeud est root, nous utiliserons donc
	 * cette fonction pour obtenir 54 * la position d'un �l�ment. 55
	 */
	@Override
	public Position<E> root() {
		return this;
	}

	/*
	 * 61 * @post : impl�mentation de l'interface RBinaryTree, renvoie true si
	 * l'arbre ne poss�de pas d'enfant 62
	 */
	@Override
	public boolean isLeaf() {
		return ((left == null) && (right == null));
	}

	/*
	 * 68 * @post : impl�mentation de l'interface RBinaryTree, renvoie le fils
	 * gauche 69
	 */
	@Override
	public LinkedBinaryTree<E> leftTree() {
		return left;
	}

	/*
	 * 75 * @post : impl�mentation de l'interface RBinaryTree, renvoie le fils
	 * droit Page 2LinkedBinaryTree.java 76
	 */
	@Override
	public LinkedBinaryTree<E> rightTree() {
		return right;
	}

	/*
	 * 82 * @post : impl�mentation de l'interface RBinaryTree, permet de
	 * modifier l'�lement 83
	 */@Override
	public void setElement(E o) {
		element = o;
	}

	/*
	 * 89 * @post : impl�mentation de l'interface RBinaryTree, permet de
	 * remplacer/ ajouter un fils gauche 90
	 */
	@Override
	public void setLeft(RBinaryTree<E> tree) {
		left = (LinkedBinaryTree<E>) tree;
	}

	/*
	 * 96 * @post : impl�mentation de l'interface RBinaryTree, permet de
	 * remplacer/ ajouter un fils droit 97
	 */
	@Override
	public void setRight(RBinaryTree<E> tree) {
		right = (LinkedBinaryTree<E>) tree;

	}

	// It�rateur d'�l�ments grace a un it�rateur de position:
	public class ElementIterator implements Iterator<E> {
		Iterator<Position<E>> posIterator = positions().iterator();

		public boolean hasNext() {
			return posIterator.hasNext();
		}

		public E next() {
			return posIterator.next().element();
		}

		public void remove() {
			posIterator.remove();
		}
	}

	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	// fonction de "tri" "non ordonn�" r�cursive de la liste:
	private void inorderSubtree(LinkedBinaryTree<E> p,
			List<Position<E>> snapshot) {
		if (p.leftTree() != null)
			inorderSubtree(p.leftTree(), snapshot);
		snapshot.add(p.root());
		if (p.rightTree() != null)
			inorderSubtree(p.rightTree(), snapshot);

	}

	// travers�e non ordonn�e de la liste:
	public Iterable<Position<E>> inorder() {
		List<Position<E>> snapshot = new ArrayList<Position<E>>(); // pk rien ds le <> ?
		if (!isEmpty()) {
			inorderSubtree(this, snapshot);
		}
		return snapshot; // pourtant ce n'est pas un iterateur qu'on renvoie ???
	}

	/*
	 * @post : impl�mentation de RBinaryTree, permt de renvoyer un it�rateur de
	 * positions
	 */
	@Override
	public Iterable<Position<E>> positions() {
		return inorder();
	}

	public String toString2() {
		Iterator<E> coucou = iterator();
		StringBuffer buf = new StringBuffer();
		while (coucou.hasNext()) {
			buf.append(coucou.next());
		}
		return buf.toString();
	}
	public String toString(){
		if ( this.leftTree()==null )
			return element().toString();
		else if(element().equals("sin") || element().equals("cos"))
			return element() +"(" + leftTree() + ")";
		else
			return "("+ leftTree() + element() + rightTree() +")";
	}
	
	public double derivee(){
		return 0;
	}
}
