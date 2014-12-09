import java.util.Arrays;


public class test_v1 {

	private int i = 0;
	
	public void rec(boolean[][] a){
		i++;
		a[i][i] = true;
		boolean[][] b = a.clone();
		if (i < 2) rec(b);
		b = a.clone();
		rec(b);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test_v1 tv = new test_v1();
		tv.rec(new boolean[3][3]);
	}

}
