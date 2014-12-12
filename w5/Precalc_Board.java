import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;


public class Precalc_Board {

	
	public Bag<int[]> precalc(int k, int l, BoggleBoard board){
		SET<String> words = new SET<>();
		Stack<Integer> stack = new Stack<Integer>();
		int cnt = 0;
    	int[][] adj = new int[k][l];
    	for (int i = 0; i < adj.length; i++) {
			for (int j = 0; j < adj[0].length; j++) {
				adj[i][j] = cnt;
				cnt++;
			}
		}
    	boolean[][] bol = new boolean[k][l];
    	Bag<int[]> bag = new Bag<int[]>();
    	for (int i = 0; i < adj.length; i++) {
			for (int j = 0; j < adj[0].length; j++) {
				dfs(i, j, adj, bol, stack, bag, board, words);
			}
		}
    	return bag;
	}
	
	

	private void dfs(int i, int j, int[][]adj, boolean[][] bol, Stack<Integer> stack, Bag<int[]> bag, BoggleBoard board, SET<String> words) {
		stack.push(adj[i][j]);
		bol[i][j] = true;
		boolean trigger = false;
		if(j < adj[0].length-1 && !(bol[i][j+1])) {trigger = true; dfs(i, j+1, adj, bol, stack, bag, board, words);}
		if(j > 0 && !(bol[i][j-1])) {trigger = true; dfs(i, j-1, adj, bol, stack, bag, board, words);}
		if(i < adj.length-1 && !(bol[i+1][j])) {trigger = true; dfs(i+1, j, adj, bol, stack, bag, board, words);}
		if(i > 0 && !(bol[i-1][j])) {trigger = true; dfs(i-1, j, adj, bol, stack, bag, board, words);}
		if(i < adj.length-1 && j < adj[0].length-1 && !(bol[i+1][j+1])) {trigger = true; dfs(i+1, j+1, adj, bol, stack, bag, board, words);}
		if(i > 0 && j > 0 && !(bol[i-1][j-1])) {trigger = true; dfs(i-1, j-1, adj, bol, stack, bag, board, words);}
		if(i < adj.length-1 && j > 0 && !(bol[i+1][j-1])) {trigger = true; dfs(i+1, j-1, adj, bol, stack, bag, board, words);}
		if(i > 0 && j < adj[0].length-1 && !(bol[i-1][j+1])) {trigger = true; dfs(i-1, j+1, adj, bol, stack, bag, board, words);}
		
		if(!trigger){
			stackCopy(bag, stack);
		}
		int x = stack.pop();
		int i1 = x/adj.length;
		int j1 = x-(adj[0].length*i1);
		bol[i1][j1] = false;
		
	}

	private void stackCopy(Bag<int[]> bag, Stack<Integer> stack){
		int[] a = new int[stack.size()];
		int cnt = 0;
		for (int i : stack) {
			a[cnt] = i;
			cnt++;
		}
		bag.add(a);
	}
	
	
	private void printer(Bag<int[]> bag) {
		for (int[] is : bag) {
			System.out.println(Arrays.toString(is));
			
		}
	}
	
	public static void main(String[] args) {
		Precalc_Board pb = new Precalc_Board();
		
	}

}
