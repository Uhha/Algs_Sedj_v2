
public class BoggleSolver
{
	private TST<Integer> dic;
	private SET<String> words;
    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary){
    	words = new SET<>();
    	dic = new TST<Integer>();
    	for (int i = 0; i < dictionary.length; i++) {
			dic.put(dictionary[i], 1);
		}
    	
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board){
		for (int i = 0; i < board.rows(); i++) {
			for (int j = 0; j < board.cols(); j++) {
				boolean[][] visited = new boolean[board.rows()][board.cols()];
				dfs(i,j,visited, new StringBuffer(), board);
			}
		}
    	return words;
	}
    
    private void dfs(int i, int j, boolean[][] visited, StringBuffer sb, BoggleBoard board){
    	visited[i][j] = true;
    	sb.append(board.getLetter(i, j));
    	if (dic.contains(sb.toString()) && sb.toString().length() > 2) {words.add(sb.toString());}
    	
    	if (!prefixCheck(dic.prefixMatch(sb.toString()))){
    		if(j < board.cols()-1 && !visited[i][j+1]) dfs(i, j+1, cloner(visited), new StringBuffer(sb), board);
    		if(j > 0 && !visited[i][j-1]) dfs(i, j-1, cloner(visited), new StringBuffer(sb), board);
    		if(i < board.rows()-1 && !visited[i+1][j]) dfs(i+1, j, cloner(visited), new StringBuffer(sb), board);
    		if(i > 0 && !visited[i-1][j]) dfs(i-1, j, cloner(visited), new StringBuffer(sb), board);
    		if(i < board.rows()-1 && j < board.cols()-1 && !visited[i+1][j+1]) dfs(i+1, j+1, cloner(visited), new StringBuffer(sb), board);
    		if(i > 0 && j > 0 && !visited[i-1][j-1]) dfs(i-1, j-1, cloner(visited), new StringBuffer(sb), board);
    		if(i < board.rows()-1 && j > 0 && !visited[i+1][j-1]) dfs(i+1, j-1, cloner(visited), new StringBuffer(sb), board);
    		if(i > 0 && j < board.cols()-1 && !visited[i-1][j+1]) dfs(i-1, j+1, cloner(visited), new StringBuffer(sb), board);
    	}
    }
    
    private boolean[][] cloner(boolean[][] visited){
		boolean[][] ret = new boolean[visited.length][visited[0].length];
		for (int i = 0; i < ret.length; i++) {
			for (int j = 0; j < ret.length; j++) {
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
		switch(word.length()){
			case 3: return 1;
			case 4: return 1;
			case 5: return 2;
			case 6: return 3;
			case 7: return 5;
			case 8: return 11;
		}
		if (word.length() > 8){
			return 11;
		}
		return 0;
    }
    
    public static void main(String[] args)
    {
        In in = new In("dictionary-yawl.txt");
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard("board-dodo.txt");
        int score = 0;
        for (String word : solver.getAllValidWords(board))
        {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
    	
//    	 In in = new In("dictionary-algs4.txt");
//         String[] dictionary = in.readAllStrings();
//         BoggleSolver solver = new BoggleSolver(dictionary);
//         BoggleBoard board = new BoggleBoard("board-points100.txt");
//         for (String word : solver.getAllValidWords(board))
//           {
//               StdOut.println(word);
//           }

    }
}