import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.Set;


public class SAP {

	private Digraph digraph;
	
	// constructor takes a digraph (not necessarily a DAG)
	public SAP(Digraph G) {
		digraph = G;
	}

	// length of shortest ancestral path between v and w; -1 if no such path
	public int length(int v, int w) {
		if(v == w){return 0;}
		int length = 1;
		Iterable<Integer> part1 = digraph.adj(v);
		Iterable<Integer> part2 = digraph.adj(w);
		ArrayList<Integer> qu = new ArrayList<Integer>();
		for (Integer integer : part1) {
			qu.add(integer);
		}
		for (Integer integer : part2) {
			qu.add(integer);
		}
		//System.out.println(qu.toString());
		while (!qu.isEmpty()){
			for (int i = 0; i < qu.size(); i++) {
				for (int j = i+1; j < qu.size(); j++) {
					if(qu.get(i) == qu.get(j)){
						System.out.println("here");
						return length;
					}
				}
			}
			ArrayList<Integer> qu2 = new ArrayList<Integer>();
			for (int i = 0; i < qu.size(); i++) {
				Iterable<Integer> ll = digraph.adj(qu.get(i));
				for (Integer integer: ll) {
					qu2.add(integer);
				}
				
			}
			qu.addAll(qu2);
			length++;
		}
		
		return -1;

	}

	// a common ancestor of v and w that participates in a shortest ancestral
	// path; -1 if no such path
	public int ancestor(int v, int w) {
		return w;

	}

	// length of shortest ancestral path between any vertex in v and any vertex
	// in w; -1 if no such path
	public int length(Iterable<Integer> v, Iterable<Integer> w) {
		return 0;

	}

	// a common ancestor that participates in shortest ancestral path; -1 if no
	// such path
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
		return 0;

	}

	// do unit testing of this class
	public static void main(String[] args) {
		String a  = "w1/wordnet/digraph2.txt";
		In in = new In(a);
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);
		System.out.println(G.toString());
		while (!StdIn.isEmpty()) {
			int v = StdIn.readInt();
			int w = StdIn.readInt();
			int length = sap.length(v, w);
			int ancestor = sap.ancestor(v, w);
			StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
		}
		
		
	}
}
