
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
			size = 0;// pas d'element dans la liste ..
		else
			size = 1; // si il y a un element la taille est de 1
		if (left != null)
			size += left.size(); // rajoute de la taille des enfants
		if (right != null)
			size += right.size();
	}

	/*
	 *  @post: implementation de l'interface position 30
	 */
	@Override
	public E element() {
		return element;
	}

	/*
	 * @post: implementation de l'interface RBinaryTree, renvoie true si l'arbre
	 * est vide
	 */
	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	/*
	 * @post: implementation de l'interface RBinaryTree, renvoie la taille
	 * de l'arbre 
	 */
	@Override
	public int size() {
		return size;
	}

	/*
	 * @post: implementation de l'interface RBinaryTree, renvoie la position de
	 * root.  Mais vu que nous implementons le fait qu'un arbre est soit
	 * vide, soit il contient un noeud racine avec un fils gauche et un
	 * fils droite.  Des lors, chaque noeud est root, nous utiliserons donc
	 * cette fonction pour obtenir la position d'un element. 
	 */
	@Override
	public Position<E> root() {
		return this;
	}

	/*
	 *  @post : implementation de l'interface RBinaryTree, renvoie true si
	 * l'arbre ne possede pas d'enfant 
	 */
	@Override
	public boolean isLeaf() {
		return ((left == null) && (right == null));
	}

	/*
	 * @post : implementation de l'interface RBinaryTree, renvoie le fils
	 * gauche 
	 */
	@Override
	public LinkedBinaryTree<E> leftTree() {
		return left;
	}

	/*
	 *  @post : implementation de l'interface RBinaryTree, renvoie le fils
	 * droit 
	 */
	@Override
	public LinkedBinaryTree<E> rightTree() {
		return right;
	}

	/*
	 * @post : implementation de l'interface RBinaryTree, permet de
	 * modifier l'element 83
	 */@Override
	public void setElement(E o) {
		element = o;
	}

	/*
	 * @post : implementation de l'interface RBinaryTree, permet de
	 * remplacer/ ajouter un fils gauche 
	 */
	@Override
	public void setLeft(RBinaryTree<E> tree) {
		left = (LinkedBinaryTree<E>) tree;
	}

	/*
	 * @post : implementation de l'interface RBinaryTree, permet de
	 * remplacer/ ajouter un fils droit 
	 */
	@Override
	public void setRight(RBinaryTree<E> tree) {
		right = (LinkedBinaryTree<E>) tree;

	}

	// Iterateur d'elements grace a un iterateur de position:
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

	// fonction de "tri" "non ordonne" recursive de la liste:
	private void inorderSubtree(LinkedBinaryTree<E> p,
			List<Position<E>> snapshot) {
		if (p.leftTree() != null)
			inorderSubtree(p.leftTree(), snapshot);
		snapshot.add(p.root());
		if (p.rightTree() != null)
			inorderSubtree(p.rightTree(), snapshot);

	}

	// traversee non ordonnee de la liste:
	public Iterable<Position<E>> inorder() {
		List<Position<E>> snapshot = new ArrayList<Position<E>>(); // pk rien ds le <> ?
		if (!isEmpty()) {
			inorderSubtree(this, snapshot);
		}
		return snapshot; 
	}

	/*
	 * @post : implementation de RBinaryTree, permt de renvoyer un iterateur de
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
