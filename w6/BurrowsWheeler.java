

public class BurrowsWheeler {
    // apply Burrows-Wheeler encoding, reading from standard input and writing to standard output
    public static void encode(){
    	
    	String s = BinaryStdIn.readString();
    	
    	//String s = "ABRACADABRA!";
    	
    	int num = 0;
    	CircularSuffixArray csa = new CircularSuffixArray(s);
    	StringBuffer sb = new StringBuffer();
    	for (int i = 0; i < s.length(); i++) {
			if(csa.index(i) == 0){
				num = i;
				sb.append(s.charAt(s.length()-1));
			}
			else {
				sb.append(s.charAt(csa.index(i)-1));
			}
		}
    	//System.out.println(num + " " + res);
    	BinaryStdOut.write(num);
    	BinaryStdOut.write(sb.toString());
    	BinaryStdOut.flush();
    }

    
    
    // apply Burrows-Wheeler decoding, reading from standard input and writing to standard output
    public static void decode(){
    	int num = BinaryStdIn.readInt();
    	String s = BinaryStdIn.readString();
    	//int num = 3;
    	//String s = "ARD!RCAAAABB";
    	StringBuffer sb = new StringBuffer();
    	
    	int[] next = new int[s.length()];
    	int[] R = new int[256+1];
    	for (int i = 0; i < s.length(); i++) {
			R[s.charAt(i)+1]++;
		}
    	for (int i = 1; i < R.length; i++) {
			R[i] += R[i-1];
		}
    	for (int i = 0; i < s.length(); i++) {
			int charAt = s.charAt(i);
    		if(charAt == num) num = i;
    		next[i] = R[charAt];
			R[charAt]++;
		}
    	int cnt = 0;
    	while (cnt < s.length()){
    		sb.append(s.charAt(num));
    		num = next[num];
    		cnt++;
    	}
    	BinaryStdOut.write(sb.reverse().toString());
    	BinaryStdOut.flush();
    	
    }

    // if args[0] is '-', apply Burrows-Wheeler encoding
    // if args[0] is '+', apply Burrows-Wheeler decoding
    public static void main(String[] args){
    	 if      (args[0].equals("-")) encode();
         else if (args[0].equals("+")) decode();
         else throw new IllegalArgumentException("Illegal command line argument");
    }
}