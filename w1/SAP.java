import java.util.ArrayList;



public class SAP {

	private Digraph digraph;
	
	// constructor takes a digraph (not necessarily a DAG)
	public SAP(Digraph G) {
		DirectedCycle dc = new DirectedCycle(G);
		if (dc.hasCycle()){throw new IllegalArgumentException();}
		digraph = G;
	}

	// length of shortest ancestral path between v and w; -1 if no such path
	public int length(int v, int w) {
		if(v > digraph.V() || w > digraph.V()){throw new IndexOutOfBoundsException();}
		if(v < 0 || w < 0){throw new IndexOutOfBoundsException();}
		
		int anc = ancestor(v,w);
		if (anc == -1){return -1;}
		BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(digraph, v);
		BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(digraph, w);
		
		if (bfsv.hasPathTo(w)){return Math.min(bfsv.distTo(anc) + bfsw.distTo(anc), bfsv.distTo(w));}
		if (bfsw.hasPathTo(v)){return Math.min(bfsv.distTo(anc) + bfsw.distTo(anc), bfsw.distTo(v));}
		
		
		return bfsv.distTo(anc) + bfsw.distTo(anc);
		
		
		
		
	}

	// a common ancestor of v and w that participates in a shortest ancestral
	// path; -1 if no such path
	public int ancestor(int v, int w) {
		if(v > digraph.V() || w > digraph.V()){throw new IndexOutOfBoundsException();}
		BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(digraph, w);
		BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(digraph, v);
		ArrayList<Integer> lookup = new ArrayList<>();
		lookup.add(v);
		while (lookup.size() > 0){
			ArrayList<Integer> lookupRemover = new ArrayList<>();
			ArrayList<Integer> lookupAdder = new ArrayList<>();
			for (Integer integer : lookup) {
				for (Integer vertex : digraph.adj(integer)) {
					lookupAdder.add(vertex);
				}
				if (!bfsw.hasPathTo(integer)){
					lookupRemover.add(integer);
				} else {
					return integer;
				}
				if (bfsv.hasPathTo(w)){
					return w;
				}
			}
			lookup.addAll(lookupAdder);
			lookup.removeAll(lookupRemover);
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
		String a  = "w1/wordnet/digraph2.txt";
		//String a  = "w1/wordnet/digraph-wordnet.txt";
		In in = new In(a);
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);
		System.out.println(G.toString());
		while (!StdIn.isEmpty()) {
			int v = StdIn.readInt();
			int w = StdIn.readInt();
			int length = sap.length(v, w);
			int ancestor = sap.ancestor(v, w);
			//int length = 0;
			StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
		}
		
		System.out.println(sap.length(1, 5));
	}
}
