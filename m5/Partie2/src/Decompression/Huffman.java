import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;


/**
 * Le but est de prendre un texte en entrée et de creer un arbre de huffman
 * @author charles
 *
 */
public class Huffman {
	private Node root;
	private boolean isCreate=false;
	ArrayList<String> string = null; // pour les deux fonctions récursives
	
	//Implémentation des noeuds
	private static class Node implements Comparable<Node>{
		private final Character ch;
		private final int freq;
		public final Node left, right;
		
		Node(Character ch, int freq, Node left, Node right){
			this.ch = ch;
			this.freq = freq;
			this.left = left;
			this.right = right;
		}
		
		public boolean isLeaf(){
			return (this.left == null && this.right == null);
		}
		public int compareTo(Node n){
			return this.freq - n.freq;
		}
		public int getFreq(){
			return freq;
		}
		public Character getC(){
			return ch;
		}
		public String toString(){
			return "[" + ch + ":" + freq+ "]";
		}
	}
	
	//création d'une liste de contenant une lettre et sa fréquence:
	public HashMap<Character, Integer> createListFreq(String fileIn){
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		Read read = new Read(fileIn);
		int i;
		char c;
		String line = null;
		while((line = read.getLine())!=null){
			for(i=0; i<line.length();i++){
				c = line.charAt(i);
				if(map.containsKey(c)){
					int freq = map.remove(c);
					map.put(c,freq+1);
				}
				else{
					map.put(c, 1);
				}
			}
		}
		map.put((Character)'~', 1);
		return map;
	}
	
	
	public void createHuffman(String fileIn){
		//d'abord, il faut récupérer la map:
		HashMap<Character,Integer> map = createListFreq(fileIn);
		Iterator<Character> lettre = map.keySet().iterator();
		ArrayList<Integer> frequences = new ArrayList<Integer>();
		frequences.addAll(map.values());
		PriorityQueue<Node> entre = new PriorityQueue<Node>();
		int i;
		
		for(i=0; i < frequences.size();i++){
			entre.add(new Node(lettre.next(), frequences.get(i),null, null));
		}
		while(entre.size()>1){
			Node node1 = entre.poll();
			Node node2 = entre.poll();
			entre.add(new Node(null, node1.getFreq() + node2.getFreq(),node1, node2));
		}
		root = entre.poll();
		isCreate = true;
	}

	private HashMap<Character,boolean []> hm;
	public HashMap<Character,boolean []> getHashMap(){
		hm =new HashMap<Character,boolean[]>();
		recursiveGetHashMap(this.root, ""); 
		return hm;
	}
	
	private void recursiveGetHashMap(Node n, String s){
		if(n == null){}
		else if(n.isLeaf()){
			//cast du string en un tableau de booléan:
			int i;
			boolean[] bool = new boolean[s.length()];
			for(i=0;i<s.length();i++){
				if(s.charAt(i)=='0'){
					bool[i]= false;
				}
				else{
					bool[i]=true;
				}
			}
			hm.put(n.getC(), bool);
		}
		else{
			recursiveGetHashMap(n.left, s + "0");
			recursiveGetHashMap(n.right, s + "1");
		}
	}
	
	

	public void recursiveToString(Node n, String s){
		if(n == null){}
		else if(n.isLeaf()){
			string.add(s + n.getC());
		}
		else{
			recursiveToString(n.left, s + "0");
			recursiveToString(n.right, s + "1");
		}
	}
	public String toString(){
		if(!isCreate){
			return "Huffman n'a pas été créé";
		}
		string = new ArrayList<String>();
		recursiveToString(root, "");
		return string.toString();
	}
}
