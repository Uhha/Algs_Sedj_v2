import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;


public class test_v1 {

	private int i = 0;
	
	
	public void rec(int[][] a, int lvl){
		TST<Integer> tst = new TST();
		tst.put("NOON", 1);
		tst.put("NOMORE", 1);
		//System.out.println(tst.prefixMatch("NO"));
		int cnt = 0;
		boolean empty = true;
		for (String st : tst.prefixMatch("NO")) {
			cnt++;
			if (cnt > 0){
				empty = false;
				break;
			}
		}
		
		if (empty){
			System.out.println("asdasd");
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test_v1 tv = new test_v1();
		tv.rec(new int[4][4], 1);
	}

}
