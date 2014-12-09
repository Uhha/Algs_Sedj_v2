
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
    	return null;
	}
    
    private void dfs(int i, int j, boolean[][] visited, StringBuffer sb, BoggleBoard board){
    	visited[i][j] = true;
    	sb.append(board.getLetter(i, j));
    	if (dic.contains(sb.toString())) {words.add(sb.toString());}
    	
    	if (dic.prefixMatch(sb.toString()) != null){
    		if(j < board.cols()) dfs(i, j+1, visited, sb, board);
    		//WTF? 0_0
    	}
    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word){
		return 0;
    }
    
    public static void main(String[] args)
    {
        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(args[1]);
        int score = 0;
        for (String word : solver.getAllValidWords(board))
        {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
    }
}