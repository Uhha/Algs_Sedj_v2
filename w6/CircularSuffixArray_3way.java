import java.util.Arrays;




public class CircularSuffixArray_3way {
	
	private String data;
	private int[] suffixes;
	
	public int[] getSuffixes() {
		return suffixes;
	}


	// circular suffix array of s
    public CircularSuffixArray_3way(String s){
    	if (s.equals(null)) throw new NullPointerException();
    	data = s;
    	suffixes = new int[s.length()];
    	for (int i = 0; i < s.length(); i++) {
			suffixes[i] = i;
		}
        
    	
    	Quick3way_mod.sort(s, suffixes);
    	//sort(s, s.length());
    	//printer(strar);
    	//System.out.println(Arrays.toString(suffixes));
    	
    }
    
    
    // length of s
    public int length(){
		return data.length();
    	
    }                  
    // returns index of ith sorted suffix
    public int index(int i){
		if (i < 0 || i > data.length()-1) throw new IndexOutOfBoundsException();
		return suffixes[i];
    	
    }      
    
    private void printer(String[] strar){
    	for (int i = 0; i < strar.length; i++) {
			System.out.println(strar[i]);
		}
    	StdOut.print(Arrays.toString(suffixes));
    }
    // unit testing of the methods (optional)
    public static void main(String[] args){
    	CircularSuffixArray_3way csa = new CircularSuffixArray_3way("causcaus");
    	//CircularSuffixArray csa = new CircularSuffixArray("BACD");
    	System.out.println(Arrays.toString(csa.suffixes));
    }
}