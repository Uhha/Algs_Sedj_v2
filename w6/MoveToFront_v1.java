
public class MoveToFront_v1 {
    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode(){
    	int[] R = new int[256];
    	for (int i = 0; i < R.length; i++) {
			R[i] = i;
		}
    	while (!BinaryStdIn.isEmpty()){
    		int ch = BinaryStdIn.readChar();
    		BinaryStdOut.write(R[ch], 8);
    		int cnt = 0;
    		for (int i = 0; i < R.length; i++) {
				if(R[i] < R[ch]) {R[i]++; cnt++;}
				if(R[ch] < cnt) break;
			}
    		R[ch] = 0;
    	}
    	BinaryStdOut.flush();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode(){
    	int[] R = new int[256];
    	for (int i = 0; i < R.length; i++) {
			R[i] = i;
		}
    	int[] s = new int[]{41, 42, 52, 02, 44, 01, 45, 01, 04, 04, 02, 26};
    	int cnt = 0;
    	
    	while (cnt < 12){
    		int ch = s[cnt];
    		int tempstore = R[ch];
    		char x = (char) R[ch];
    		System.out.print(R[ch] + " ");;
    		for (int i = ch ; i > 0; i--) {
    			R[i] = R[i-1];
    		}
    		R[0] = tempstore;
    		cnt++;
    	}
    	//correct one 41 42 52 41 43 41 44 41 42 52 41 21 
    				//41 42 52 41 43 41 44 41 42 52 41 21  
    	BinaryStdOut.flush();
    }

    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args){
    	decode();
    	//if      (args[0].equals("-")) encode();
        //else if (args[0].equals("+")) decode();
        //else throw new IllegalArgumentException("Illegal command line argument");
    }
}