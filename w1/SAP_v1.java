import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class SAP_v1 {

	private Digraph digraph;
	
	// constructor takes a digraph (not necessarily a DAG)
	public SAP_v1(Digraph G) {
		digraph = G;
	}

	// length of shortest ancestral path between v and w; -1 if no such path
	public int length(int v, int w) {
		if (v == w){return 0;}
		int a = length(v,w, true);
		int b = length(w,v, true);
		if (a == -1 && b == -1){return -1;}
		if (a == -1 && b != -1){return b;}
		if (a != -1 && b == -1){return a;}
		return Math.min(a, b);
		
	}
	private int length(int x, int check, boolean a){
		int length = 1;
		ArrayList<Integer> mainar = new ArrayList<Integer>();
		Iterable<Integer> lst = digraph.adj(x);
		for (Integer integer : lst) {
			mainar.add(integer);
		}
		while(mainar.size() > 0){
			ArrayList<Integer> mainar2 = new ArrayList<Integer>();
			for (Integer integer : mainar) {
				if (integer == check){
					return length;
				}
				Iterable<Integer> nextstep = digraph.adj(integer);
				for (Integer integer2 : nextstep) {
					mainar2.add(integer2);
				}
			}
			length++;
			mainar = mainar2;
			
		}
		return -1;
		
	}

	// a common ancestor of v and w that participates in a shortest ancestral
	// path; -1 if no such path
	public int ancestor(int v, int w) {
		if(v == w){return v;}
		Set<Integer> vset = new HashSet<>();
		Set<Integer> wset = new HashSet<>();
		vset.add(v);
		wset.add(w);
		for (Integer integer : digraph.adj(v)) {
			vset.add(integer);
		}
		for (Integer integer : digraph.adj(w)) {
			wset.add(integer);
		}
		boolean deadend_v = false;
		boolean deadend_w = false;
		
		while(!deadend_v || !deadend_w){
			int anc = conteinsCheck(vset, wset);
			if (anc != -1){return anc;}
			Set<Integer> tempset = new HashSet<>();
			for (Integer integer : vset) {
				for (Integer adj : digraph.adj(integer)) {
					tempset.add(adj);
				}
			}
			if (tempset.isEmpty()){deadend_v = true;}
			vset.addAll(tempset);
			tempset.clear();
			//System.out.println(tempset.toString());
			for (Integer integer : wset) {
				for (Integer adj : digraph.adj(integer)) {
					tempset.add(adj);
				}
			}
			if (tempset.isEmpty()){deadend_w = true;}
			wset.addAll(tempset);
			
			
		}
		return -1;

	}

	private int conteinsCheck(Set<Integer> vset, Set<Integer> wset){
		for (Integer integer : vset) {
			for (Integer integer2 : wset) {
				if (integer == integer2){
					return integer;
				}
			}
		}
		
		return -1;
		
	}
	// length of shortest ancestral path between any vertex in v and any vertex
	// in w; -1 if no such path
	public int length(Iterable<Integer> v, Iterable<Integer> w) {
		int retpath = Integer.MAX_VALUE;
		for (Integer integer : v) {
			for (Integer integer2 : w) {
				int path = length(integer, integer2);
				if (path < retpath){
					retpath = path;
				}
			}
		}
		return retpath;

	}

	// a common ancestor that participates in shortest ancestral path; -1 if no
	// such path
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
		int retpath = Integer.MAX_VALUE;
		for (Integer integer : v) {
			for (Integer integer2 : w) {
				int path = ancestor(integer, integer2);
				if (path < retpath){
					retpath = path;
				}
			}
		}
		return retpath;

	}

	// do unit testing of this class
	public static void main(String[] args) {
		String a  = "w1/wordnet/digraph-wordnet.txt";
		In in = new In(a);
		Digraph G = new Digraph(in);
		SAP_v1 sap = new SAP_v1(G);
		System.out.println(G.toString());
		while (!StdIn.isEmpty()) {
			int v = StdIn.readInt();
			int w = StdIn.readInt();
			int length = sap.length(v, w);
			int ancestor = sap.ancestor(v, w);
			//int length = 0;
			StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
		}
		
		//System.out.println(sap.ancestor(0, 3));
		
	}
}
