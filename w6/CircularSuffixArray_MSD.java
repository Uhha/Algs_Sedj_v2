import java.util.Arrays;




public class CircularSuffixArray_MSD {
	
	private String data;
	private int[] suffixes;
	
	private static final int R      = 256;   // extended ASCII alphabet size
    private static final int CUTOFF =  15;   // cutoff to insertion sort
    
	// circular suffix array of s
    public CircularSuffixArray_MSD(String s){
    	if (s.equals(null)) throw new NullPointerException();
    	data = s;
    	suffixes = new int[s.length()];
    	for (int i = 0; i < s.length(); i++) {
			suffixes[i] = i;
		}
        
    	
    	sort();
    	//sort(s, s.length());
    	//printer(strar);
    	//System.out.println(Arrays.toString(suffixes));
    	
    }
    
    public void sort() {
        int N = length();
        int[] aux = new int[N];
        sort(0, N-1, 0, aux);
    }

    // return dth character of s, -1 if d = length of string
    private int charAt(int stringNum, int d) {
        //assert d >= 0 && d <= s.length();
        if (d == length()) return -1;
        String dubS = data+data;
        //return dubS.charAt(length() + suffixes[stringNum] + d);
        return dubS.charAt(stringNum+d);
        
    }

    // sort from a[lo] to a[hi], starting at the dth character
    private void sort(int lo, int hi, int d, int[] aux) {

        // cutoff to insertion sort for small subarrays
        if (hi <= lo + CUTOFF) {
            insertion(lo, hi, d);
            return;
        }

        // compute frequency counts
        int[] count = new int[R+2];
        for (int i = lo; i <= hi; i++) {
            int c = charAt(suffixes[i], d);
            count[c+2]++;
        }

        // transform counts to indicies
        for (int r = 0; r < R+1; r++)
            count[r+1] += count[r];

        // distribute
        for (int i = lo; i <= hi; i++) {
            int c = charAt(suffixes[i], d);
            aux[count[c+1]++] = suffixes[i];
        }

        // copy back
        for (int i = lo; i <= hi; i++) 
        	suffixes[i] = aux[i - lo];


        // recursively sort for each character
        for (int r = 0; r < R; r++)
            sort(lo + count[r], lo + count[r+1] - 1, d+1, aux);
    }


    // return dth character of s, -1 if d = length of string
    private void insertion(int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(suffixes[j], suffixes[j-1], d); j--)
                exch(j, j-1);
    }

    // exchange a[i] and a[j]
    private void exch(int i, int j) {
        int temp = suffixes[i];
        suffixes[i] = suffixes[j];
        suffixes[j] = temp;
    }

    // is v less than w, starting at character d
    private boolean less(int v, int w, int d) {
        //assert v.substring(0, d).equals(w.substring(0, d));
        //return v.substring(d).compareTo(w.substring(d)) < 0;
    	String dubS = data+data;
    	
        for (int i = d; i < suffixes.length; i++) {
            if (dubS.charAt(v + i) < dubS.charAt(w + i)) return true;
            if (dubS.charAt(v + i) > dubS.charAt(w + i)) return false;
        }
        //return v.length() < w.length();
        System.out.println("FAILED");
        return true;
    }
             
    
    
    // returns index of ith sorted suffix
    public int index(int i){
		if (i < 0 || i > data.length()-1) throw new IndexOutOfBoundsException();
		return suffixes[i];
    	
    }      
    public int length(){
		return data.length();
    	
    }  
    
    // unit testing of the methods (optional)
    public static void main(String[] args){
    	
    	CircularSuffixArray_MSD csa = new CircularSuffixArray_MSD("ABRACADABRA!");
    	//CircularSuffixArray csa = new CircularSuffixArray("BACD");
    	System.out.println(Arrays.toString(csa.suffixes));
    }
}