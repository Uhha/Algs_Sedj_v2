
public class BoggleSolver_TST
{
	private TST_mod<Integer> dic;
	
    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver_TST(String[] dictionary){
    	dic = new TST_mod<Integer>();
    	for (int i = 0; i < dictionary.length; i++) {
			dic.put(dictionary[i], 1);
		}
    	
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board){
    	SET<String> words = new SET<>();
    	for (int i = 0; i < board.rows(); i++) {
			for (int j = 0; j < board.cols(); j++) {
				boolean[][] visited = new boolean[board.rows()][board.cols()];
				dfs(i,j,visited, new StringBuffer(), board, words);
			}
		}
    	return words;
	}
    
    private void dfs(int i, int j, boolean[][] visited, StringBuffer sb, BoggleBoard board, SET<String> words){
    	visited[i][j] = true;
    	char ch = board.getLetter(i, j);
    	if("Q".equals(Character.toString(ch))){
    		sb.append(board.getLetter(i, j));
    		sb.append("U");
    	}
    	else {sb.append(board.getLetter(i, j));}
    	if (dic.contains(sb.toString()) && sb.toString().length() > 2) {words.add(sb.toString());}
    	
    	//if (!prefixCheck(dic.prefixMatch(sb.toString()))){
    	if (dic.prefixMatch2(sb.toString())){
    		if(j < board.cols()-1 && !visited[i][j+1]) dfs(i, j+1, cloner(visited), new StringBuffer(sb), board, words);
    		if(j > 0 && !visited[i][j-1]) dfs(i, j-1, cloner(visited), new StringBuffer(sb), board, words);
    		if(i < board.rows()-1 && !visited[i+1][j]) dfs(i+1, j, cloner(visited), new StringBuffer(sb), board, words);
    		if(i > 0 && !visited[i-1][j]) dfs(i-1, j, cloner(visited), new StringBuffer(sb), board, words);
    		if(i < board.rows()-1 && j < board.cols()-1 && !visited[i+1][j+1]) dfs(i+1, j+1, cloner(visited), new StringBuffer(sb), board, words);
    		if(i > 0 && j > 0 && !visited[i-1][j-1]) dfs(i-1, j-1, cloner(visited), new StringBuffer(sb), board, words);
    		if(i < board.rows()-1 && j > 0 && !visited[i+1][j-1]) dfs(i+1, j-1, cloner(visited), new StringBuffer(sb), board, words);
    		if(i > 0 && j < board.cols()-1 && !visited[i-1][j+1]) dfs(i-1, j+1, cloner(visited), new StringBuffer(sb), board, words);
    	}
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
        In in = new In("dictionary-zingarelli2005.txt");
        String[] dictionary = in.readAllStrings();
        BoggleSolver_TST solver = new BoggleSolver_TST(dictionary);
        Stopwatch st = new Stopwatch();
        
        for (int i = 0; i < 100; i++) {
        	BoggleBoard board = new BoggleBoard();
            solver.getAllValidWords(board);
		}
        System.out.println(st.elapsedTime());
        
//        int score = 0;
//        int cnt = 0;
//        for (String word : solver.getAllValidWords(board))
//        {
//            cnt++;
//        	StdOut.println(word);
//            score += solver.scoreOf(word);
//        }
//        StdOut.println("Score = " + score);
//    	System.out.println("Total of " + cnt + " words" );
        

    }
}