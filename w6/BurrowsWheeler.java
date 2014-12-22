

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
    	String s = BinaryStdIn.readString();
    	
    }

    // if args[0] is '-', apply Burrows-Wheeler encoding
    // if args[0] is '+', apply Burrows-Wheeler decoding
    public static void main(String[] args){
    	
    }
}