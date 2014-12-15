



public class BoggleSolver{
	private Trie_mod<Integer> dic;
	
    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary){
    	
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
				dfs(i, j, bol, new Stack<Character>(), board, words);
			}
		}
    	return words;
	}
    
    private void dfs(int i, int j, boolean[][] bol, Stack<Character> stack2, BoggleBoard board, SET<String> words) {
		stack2.push(board.getLetter(i, j));
		bol[i][j] = true;
		
		
	//	String prefix = new StringBuilder(stack2.toString()).reverse().toString();
	//	System.out.println(prefix);
		StringBuffer sb = new StringBuffer();
		for (Character x : stack2) {
			//int i0 = x/adj[0].length;
			//int j0 = x-(adj[0].length*i0);
			//char ch = board.getLetter(i0, j0);
			
			if ("Q".equals(Character.toString(x))){
				sb.append("U");
				sb.append(x);
			} else {
			sb.append(x);
			}
		}
		String prefix = sb.reverse().toString();
		
//		boolean pass = false;
//    	if(checknode == null){
//    		checknode = dic.keysWithPrefix5(sbrev);
//    	} else {
//    		if (Character.toString(board.getLetter(i, j)).equals("Q")){
//    			checknode = dic.keysWithPrefix5_support(checknode, Character.toString(board.getLetter(i, j))+"U");
//        		
//    		} else {
//    		checknode = dic.keysWithPrefix5_support(checknode, Character.toString(board.getLetter(i, j)));
//    		}
//    	}
//    	
//    	if(checknode != null){
//    		pass = true;
//    	}
//    	if (pass){
		boolean wasin = false;
		if (dic.keysWithPrefix2(prefix)){
			wasin = true;
		if(j < bol[0].length-1 && !(bol[i][j+1])) {dfs(i, j+1, bol, stack2, board, words);}
		if(j > 0 && !(bol[i][j-1])) {dfs(i, j-1, bol, stack2, board, words);}
		if(i < bol.length-1 && !(bol[i+1][j])) {dfs(i+1, j, bol, stack2, board, words);}
		if(i > 0 && !(bol[i-1][j])) {dfs(i-1, j, bol, stack2, board, words);}
		if(i < bol.length-1 && j < bol[0].length-1 && !(bol[i+1][j+1])) {dfs(i+1, j+1, bol, stack2, board, words);}
		if(i > 0 && j > 0 && !(bol[i-1][j-1])) {dfs(i-1, j-1, bol, stack2, board, words);}
		if(i < bol.length-1 && j > 0 && !(bol[i+1][j-1])) {dfs(i+1, j-1, bol, stack2, board, words);}
		if(i > 0 && j < bol[0].length-1 && !(bol[i-1][j+1])) {dfs(i-1, j+1, bol, stack2, board, words);}
		
		}
		
		if (dic.contains(prefix) && prefix.length() > 2 && wasin == true) {words.add(prefix);}
		
		stack2.pop();
		bol[i][j] = false;
		
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
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard("board-q.txt");
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