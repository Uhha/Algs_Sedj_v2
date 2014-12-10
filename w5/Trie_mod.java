
public class Trie_mod<Value> {
    private static final int R = 26;        // extended ASCII

    private Node root = new Node();
    private Node prev = root;
    private String prevst = "";
    
    static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }
   /****************************************************
    * Is the key in the symbol table?
    ****************************************************/
    public boolean contains(String key) {
        return get(key) != null;
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (Value) x.val;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c-65], key, d+1);
    }

   /****************************************************
    * Insert key-value pair into the symbol table.
    ****************************************************/
    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        x.next[c-65] = put(x.next[c-65], key, val, d+1);
        return x;
    }

   
    public boolean keysWithPrefix2(String prefix) {
    	Node x = get(root, prefix, 0);
    	if (x == null) return false;
        else {return true;}
    }

    public boolean keysWithPrefix3(String prefix) {
    	String cutten = prefix.substring(0, prefix.length()-1);
    	Node x;
    	if(prevst.equals(cutten) ){
    		//x = get(prev, prefix.substring(prefix.length()-1, prefix.length()),0);
    		char c = prefix.charAt(prefix.length()-1);
    		x = prev.next[c-65];
    	} else {
    		x = get(root, prefix, 0);
    	}
    	prev = x;
    	prevst = prefix;
    	if (x == null) return false;
        else {return true;}
    }

    public int keysWithPrefix4(String prefix) {
    	return getint(root, prefix, 0);
    	
    }
    
    private int getint(Node x, String key, int d) {
        if (x == null) return -1;
        if (d == key.length()) return 1;
        char c = key.charAt(d);
        return getint(x.next[c-65], key, d+1);
    }
  
    public Node keysWithPrefix5(String prefix) {
    	Node x = get(root, prefix, 0);
    	if (x == null) return null;
        else {return x;}
    }
    public Node keysWithPrefix5_support(Node node, String prefix) {
    	Node x = get(node, prefix, 0);
    	if (x == null) return null;
        else {return x;}
    }
    
    

    // test client
    public static void main(String[] args) {

        // build symbol table from standard input
        Trie_mod<Integer> st = new Trie_mod<Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        // print results
//        for (String key : st.keys()) {
//            StdOut.println(key + " " + st.get(key));
//        }
    }
}
