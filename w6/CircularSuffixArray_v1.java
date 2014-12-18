import java.util.Arrays;




public class CircularSuffixArray_v1 {
	
	private String data;
	private int[] suffixes;
	
	// circular suffix array of s
    public CircularSuffixArray_v1(String s){
    	if (s.equals(null)) throw new NullPointerException();
    	data = s;
    	suffixes = new int[s.length()];
    	String[] strar = new String[s.length()];
    	String doubleStr = s+s;
    	char[] cha = doubleStr.toCharArray();
    	for (int i = 0; i < strar.length; i++) {
    		strar[i] = String.copyValueOf(Arrays.copyOfRange(cha, i, i+s.length()));
		}
    	
    	//printer
    	//for (int i = 0; i < strar.length; i++) {
		//	System.out.println(strar[i]);
		//}
    	
    	sort(strar, s.length());
    	printer(strar);
    	
    	
    }
    
    private void sort(String[] a, int W) {
        int N = a.length;
        int R = 256;   // extend ASCII alphabet size
        String[] aux = new String[N];
        
        int[] auxint = new int[N];
        for (int i = 0; i < auxint.length; i++) {
			suffixes[i] = i;
		}
        for (int d = W-1; d >= 0; d--) {
            // sort by key-indexed counting on dth character

            // compute frequency counts
            int[] count = new int[R+1];
            for (int i = 0; i < N; i++)
                count[a[i].charAt(d) + 1]++;

            // compute cumulates
            for (int r = 0; r < R; r++)
                count[r+1] += count[r];

            // move data
            for (int i = 0; i < N; i++){
                int num = count[a[i].charAt(d)]++;
            	aux[num] = a[i];
            	auxint[num] = suffixes[i];
            }

            // copy back
            for (int i = 0; i < N; i++){
                a[i] = aux[i];
            	suffixes[i] = auxint[i];
            }
        }
        
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
    	CircularSuffixArray_v1 csa = new CircularSuffixArray_v1("ABRACADABRA!");
    	
    }
}