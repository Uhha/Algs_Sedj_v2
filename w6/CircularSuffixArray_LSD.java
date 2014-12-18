import java.util.Arrays;




public class CircularSuffixArray_LSD {
	
	private String data;
	private int[] suffixes;
	
	// circular suffix array of s
    public CircularSuffixArray_LSD(String s){
    	if (s.equals(null)) throw new NullPointerException();
    	data = s;
    	suffixes = new int[s.length()];
    	sort(s, s.length());
    	//printer(strar);
    	//System.out.println(Arrays.toString(suffixes));
    	
    }
    
    private void sort(String a, int W) {
    	String doubleA = a+a;
        int N = a.length();
        int R = 256;   // extend ASCII alphabet size
        
        int[] auxint = new int[N];
        
        for (int i = 0; i < auxint.length; i++) {
			suffixes[i] = i;
		}
        
        for (int d = 0; d < N; d++) {
            // sort by key-indexed counting on dth character

            // compute frequency counts
            int[] count = new int[R+1];
           
            for (int i = 0; i < N; i++){
                count[doubleA.charAt((N-(d+1))+suffixes[i]) + 1]++;
            	
            }

            // compute cumulates
            for (int r = 0; r < R; r++)
                count[r+1] += count[r];

            // move data
            for (int i = 0; i < N; i++){
            	//System.out.println(N);
            	//System.out.println(suffixes[i]);
                int num = count[doubleA.charAt((N-(d+1))+suffixes[i])]++;
            	//aux[num] = a[i];
            	auxint[num] = suffixes[i];
            }

            // copy back
            for (int i = 0; i < N; i++){
                //a[i] = aux[i];
            	suffixes[i] = auxint[i];
            	
            }
            //System.out.println(Arrays.toString(suffixes));
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
    	CircularSuffixArray_LSD csa = new CircularSuffixArray_LSD("ABRACADABRA!");
    	//CircularSuffixArray csa = new CircularSuffixArray("BACD");
    	
    }
}