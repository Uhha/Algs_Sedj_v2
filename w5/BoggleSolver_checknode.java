



public class BoggleSolver_checknode
{
	private Trie_mod<Integer> dic;
	
    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver_checknode(String[] dictionary){
    	
    	dic = new Trie_mod<Integer>();
    	for (int i = 0; i < dictionary.length; i++) {
			dic.put(dictionary[i], 1);
		}
    	
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board){
    	SET<String> words = new SET<>();
		int cnt = 0;
    	int[][] adj = new int[board.rows()][board.cols()];
    	for (int i = 0; i < adj.length; i++) {
			for (int j = 0; j < adj[0].length; j++) {
				adj[i][j] = cnt;
				cnt++;
			}
		}
    	boolean[][] bol = new boolean[board.rows()][board.cols()];
    	for (int i = 0; i < adj.length; i++) {
			for (int j = 0; j < adj[0].length; j++) {
				dfs(i, j, adj, bol, new Stack<Integer>(), board, words, null);
			}
		}
    	return words;
	}
    
    private void dfs(int i, int j, int[][]adj, boolean[][] bol, Stack<Integer> stack, BoggleBoard board, SET<String> words, Trie_mod.Node checknode) {
		stack.push(adj[i][j]);
		bol[i][j] = true;
		StringBuffer sb = new StringBuffer();
		
		for (Integer x : stack) {
			int i0 = x/adj[0].length;
			int j0 = x-(adj[0].length*i0);
			char ch = board.getLetter(i0, j0);
			if ("Q".equals(Character.toString(ch))){
				sb.append("U");
				sb.append(ch);
			} else {
			sb.append(ch);
			}
		}
		String sbrev = sb.reverse().toString();
		
		boolean pass = false;
    	if(checknode == null){
    		checknode = dic.keysWithPrefix5(sb.toString());
    	} else {
    		checknode = dic.keysWithPrefix5_support(checknode, Character.toString(board.getLetter(i, j)));
    	}
    	
    	if(checknode != null){
    		pass = true;
    	}
    	if (pass){
			
		if(j < adj[0].length-1 && !(bol[i][j+1])) {dfs(i, j+1, adj, bol, stack, board, words, checknode);}
		if(j > 0 && !(bol[i][j-1])) {dfs(i, j-1, adj, bol, stack, board, words, checknode);}
		if(i < adj.length-1 && !(bol[i+1][j])) {dfs(i+1, j, adj, bol, stack, board, words, checknode);}
		if(i > 0 && !(bol[i-1][j])) {dfs(i-1, j, adj, bol, stack, board, words, checknode);}
		if(i < adj.length-1 && j < adj[0].length-1 && !(bol[i+1][j+1])) {dfs(i+1, j+1, adj, bol, stack, board, words, checknode);}
		if(i > 0 && j > 0 && !(bol[i-1][j-1])) {dfs(i-1, j-1, adj, bol, stack, board, words, checknode);}
		if(i < adj.length-1 && j > 0 && !(bol[i+1][j-1])) {dfs(i+1, j-1, adj, bol, stack, board, words, checknode);}
		if(i > 0 && j < adj[0].length-1 && !(bol[i-1][j+1])) {dfs(i-1, j+1, adj, bol, stack, board, words, checknode);}
		
		}
		
		if (dic.contains(sbrev) && sbrev.length() > 2) {words.add(sbrev);}
		
		int x = stack.pop();
		int i1 = x/adj[0].length;
		int j1 = x-(adj[0].length*i1);
		bol[i1][j1] = false;
		
	}

	
    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word){
		
    	if (dic.contains(word)) {
			switch (word.length()) {
			case 3:
				return 1;
			case 4:
				return 1;
			case 5:
				return 2;
			case 6:
				return 3;
			case 7:
				return 5;
			case 8:
				return 11;
			}
			if (word.length() > 8) {
				return 11;
			}
			return 0;
		} else {
			return 0;
		}
    }
    
    public static void main(String[] args)
    {
        In in = new In("dictionary-algs4.txt");
        String[] dictionary = in.readAllStrings();
        BoggleSolver_checknode solver = new BoggleSolver_checknode(dictionary);
        BoggleBoard board = new BoggleBoard("board4x4.txt");
        int score = 0;
        int cnt = 0;
        for (String word : solver.getAllValidWords(board))
        {
            cnt++;
        	StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
    	System.out.println("Total of " + cnt + " words" );
        
      Stopwatch st = new Stopwatch();
      
      for (int i = 0; i < 100000; i++) {
      	board = new BoggleBoard();
          solver.getAllValidWords(board);
		}
      System.out.println(st.elapsedTime());
    	
    	
    }
}