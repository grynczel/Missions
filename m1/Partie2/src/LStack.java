public class LStack<E> {
	/**
	 * @author Baptiste Degryse
	 */
	private Node head;
	private int size = 0;

	class Node {
		public Node next;
		public E elem;

		public Node(E elem, Node next) {
			this.next = next;
			this.elem = elem;
		}
	}

	/**
	 * cree une nouvelle pile vide
	 */
	public LStack() {
	}

	/**
	 * cree une nouvelle pile avec elem au dessus de la pile
	 * 
	 * @param elem
	 *            : le premier element de la pile
	 */
	public LStack(E elem) {
		head = new Node(elem, null);
		size++;
	}

	/**
	 * rajoute un element sur le sommet de la pile
	 * 
	 * @param elem
	 *            : element ajoute au dessus de la pile
	 */
	public void push(E elem) {
		if (head == null)
			head = new Node(elem, null);
		else {
			head = new Node(elem, head);
		}
		size++;
	}

	/**
	 * retire et renvoie l'element au sommet de la pile. Renvoie null en cas de
	 * pile vide.
	 * 
	 * @return element du sommet de la pile.
	 */
	public E pop() {
		if (head == null)
			return null;
		Node n = head;
		head = n.next;
		size--;
		return n.elem;
	}

	public int size() {
		return size;
	}

	/**
	 * retourne la pile sous forme de String
	 * 
	 * @return String representant la pile
	 */
	public String toString() {
		StringBuffer buf = new StringBuffer("");
		for (Node node = head; node != null; node = node.next) {
			buf.append(node.elem.toString());
			if (node.next != null)
				buf.append(" ");
		}
		buf.append("");
		return buf.toString();
	}
}
