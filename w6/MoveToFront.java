


public class MoveToFront {
    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode(){
    	//System.setIn(new FileInputStream("w6/burrows/zebra.txt"));

    	int[] R = new int[256];
    	for (int i = 0; i < 256; i++) {
			R[i] = i;
		}
    	while (!BinaryStdIn.isEmpty()){
    		int ch = BinaryStdIn.readChar();
    		//BinaryStdOut.write(R[ch], 8);
			
    		if(R[0] == ch){
				BinaryStdOut.write(0, 8);
			} else {
    		int prev = R[0];
    		
    		for (int i = 1; i < R.length; i++) {
    			int running = R[i];
    			if (running == ch){
    				BinaryStdOut.write(i, 8);
    				R[i] = prev;
    				break;
    			} else {
    				int temp = R[i];
    				R[i] = prev;
    				prev = temp;
    			}
			}
    		R[0] = ch;
    		
			}
    	}
    	BinaryStdOut.flush();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode(){
    	//System.setIn(new FileInputStream("w6/burrows/encodedSecretMessage.txt"));

    	int[] R = new int[256];
    	for (int i = 0; i < R.length; i++) {
			R[i] = i;
		}
    	while (!BinaryStdIn.isEmpty()){
    		int ch = BinaryStdIn.readChar();
    		int tempstore = R[ch];
    		char x = (char) R[ch];
    		BinaryStdOut.write(x);
    		for (int i = ch ; i > 0; i--) {
    			R[i] = R[i-1];
    		}
    		R[0] = tempstore;
    	}
    	BinaryStdOut.flush();
    }

    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args){
    	if      (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
        else throw new IllegalArgumentException("Illegal command line argument");
    	
    	
    }
}