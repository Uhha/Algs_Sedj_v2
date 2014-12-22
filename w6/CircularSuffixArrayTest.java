import static org.junit.Assert.*;

import java.io.File;
import java.util.Random;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class CircularSuffixArrayTest {
	
	private String[] set;
	private final int length = 100;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		set = new String[length];
		Random rand = new Random();
		File f = new File("w6\\burrows\\aesop.txt");
		Scanner sc = new Scanner(f);
		for (int i = 0; i < length; i++) {
			String a = sc.nextLine().replace(" ", "");
			set[i] = a;
		}
		
		sc.close();
	}

	@Test
	public void test() {
		for (int i = 0; i < length; i++) {
			CircularSuffixArray cs = new CircularSuffixArray(set[i]);
			//CircularSuffixArray_3way cs = new CircularSuffixArray_3way(set[i]);
			//CircularSuffixArray_MSD cs = new CircularSuffixArray_MSD(set[i]);
			CircularSuffixArray_LSD csLSD = new CircularSuffixArray_LSD(set[i]);
			
			Assert.assertArrayEquals(csLSD.getSuffixes(), cs.getSuffixes());
			//System.out.println(set[i]);
			
		}
		
		
		
	}

}
