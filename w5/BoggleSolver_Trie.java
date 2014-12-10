import java.util.Collections;


public class BoggleSolver_Trie
{
	private Trie_mod<Integer> dic;
	
    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver_Trie(String[] dictionary){
    	
    	dic = new Trie_mod<Integer>();
    	for (int i = 0; i < dictionary.length; i++) {
			dic.put(dictionary[i], 1);
		}
    	
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board){
    	int cnt = 0;
    	int[][] adj = new int[board.rows()][board.cols()];
    	for (int i = 0; i < adj.length; i++) {
			for (int j = 0; j < adj[0].length; j++) {
				adj[i][j] = cnt;
				cnt++;
			}
		}
    	
    	SET<String> words = new SET<>();
    	for (int i = 0; i < board.rows(); i++) {
			for (int j = 0; j < board.cols(); j++) {
				//boolean[][] visited = new boolean[board.rows()][board.cols()];
				dfs(i,j,adj, new StringBuffer(), board, words, new SET<Integer>());
			}
		}
    	return words;
	}
    
    private void dfs(int i, int j, int[][] adj, StringBuffer sb, BoggleBoard board, SET<String> words, SET<Integer> set){
    	set.add(adj[i][j]);
    	char ch = board.getLetter(i, j);
    	if("Q".equals(Character.toString(ch))){
    		sb.append(board.getLetter(i, j));
    		sb.append("U");
    	}
    	else {sb.append(board.getLetter(i, j));}
    	if (dic.contains(sb.toString()) && sb.toString().length() > 2) {words.add(sb.toString());}
    	
    	if (dic.keysWithPrefix2(sb.toString())){
    		if(j < board.cols()-1 && !checkin(i,j+1, set, adj)) dfs(i, j+1, adj, new StringBuffer(sb), board, words, new SET<Integer>().union(set));
    		if(j > 0 && !checkin(i,j-1, set, adj)) dfs(i, j-1, adj, new StringBuffer(sb), board, words, new SET<Integer>().union(set));
    		if(i < board.rows()-1 && !checkin(i+1,j, set, adj)) dfs(i+1, j, adj, new StringBuffer(sb), board, words, new SET<Integer>().union(set));
    		if(i > 0 && !checkin(i-1,j, set, adj)) dfs(i-1, j, adj, new StringBuffer(sb), board, words, new SET<Integer>().union(set));
    		if(i < board.rows()-1 && j < board.cols()-1 && !checkin(i+1,j+1, set, adj)) dfs(i+1, j+1, adj, new StringBuffer(sb), board, words, new SET<Integer>().union(set));
    		if(i > 0 && j > 0 && !checkin(i-1,j-1, set, adj)) dfs(i-1, j-1, adj, new StringBuffer(sb), board, words, new SET<Integer>().union(set));
    		if(i < board.rows()-1 && j > 0 && !checkin(i+1,j-1, set, adj)) dfs(i+1, j-1, adj, new StringBuffer(sb), board, words, new SET<Integer>().union(set));
    		if(i > 0 && j < board.cols()-1 && !checkin(i-1,j+1, set, adj)) dfs(i-1, j+1, adj, new StringBuffer(sb), board, words, new SET<Integer>().union(set));
    	}
    }
    
    private boolean checkin(int i, int j, SET<Integer> set, int[][] adj){
		if (set.contains(adj[i][j])){
			return true;
		}
    	return false;
    	
    }
    
    
    
    private boolean[][] cloner(boolean[][] visited){
		boolean[][] ret = new boolean[visited.length][visited[0].length];
		for (int i = 0; i < ret.length; i++) {
			for (int j = 0; j < ret[0].length; j++) {
				ret[i][j] = visited[i][j];
			}
		}
    	return ret;
    	
    }
    
    private boolean prefixCheck(Iterable<String> iterable){
    	int cnt = 0;
		boolean empty = true;
		for (String st : iterable) {
			cnt++;
			if (cnt > 0){
				empty = false;
				break;
			}
		}
		return empty;
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
        BoggleSolver_Trie solver = new BoggleSolver_Trie(dictionary);
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
      
      for (int i = 0; i < 10000; i++) {
      	board = new BoggleBoard();
          solver.getAllValidWords(board);
		}
      System.out.println(st.elapsedTime());
    	
    	
    }
}