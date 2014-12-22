import java.util.Arrays;




public class CircularSuffixArray {
	
	private String data;
	private int[] suffixes;
	
	
	public int[] getSuffixes() {
		return suffixes;
	}


	// circular suffix array of s
    public CircularSuffixArray(String s){
    	if (s.equals(null)) throw new NullPointerException();
    	data = s;
    	
    	SuffixArrayX_mod sf = new SuffixArrayX_mod(data);
    	suffixes = sf.getIndex();
    	
    	
    	//sort(s, s.length());
    	//printer(strar);
    	//System.out.println(Arrays.toString(suffixes));
    	
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
    	//String s = "layviolenthandsonhim,buttofindsomepleatojustifytothe";
    	//String s = "lhah";
    	String s = "ABRACADABRA!";
    	
    	CircularSuffixArray csa = new CircularSuffixArray(s);
//    	CircularSuffixArray_3way csa3way = new CircularSuffixArray_3way(s);
//    	CircularSuffixArray_LSD csaLSD = new CircularSuffixArray_LSD(s);
//    	int[] res1 = csa.suffixes;
//    	int[] res2 = csaLSD.getSuffixes();
//    	int[] res3 = csa3way.getSuffixes();
//    	System.out.println(Arrays.toString(csa.suffixes) + " Suffix X");
//    	System.out.println(Arrays.toString(csaLSD.getSuffixes()) + " MSD Right One");
//    	
//    	for (int i = 0; i < res1.length; i++) {
//			if(res1[i] != res2[i]){
//				System.out.println("COMp - FAILED");
//				break;
//			}
//		}
//    	
//    	System.out.println(Arrays.toString(csa3way.getSuffixes()) + " 3way");
    	System.out.println(csa.index(0));
    	
    }
}