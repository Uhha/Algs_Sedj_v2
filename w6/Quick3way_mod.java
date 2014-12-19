
public class Quick3way_mod {
    private static final int CUTOFF =  0;   // cutoff to insertion sort

    // sort the array a[] of strings
    public static void sort(String a, int[] suffixes) {
        //StdRandom.shuffle(a);
        sort(a, 0, a.length()-1, 0, suffixes);
        //assert isSorted(a);
    }

    // return the dth character of s, -1 if d = length of s
    private static int charAt(String s, int stringNum, int d, int[] suffixes) { 
        assert d >= 0 && d <= s.length();
        if (d == s.length()) return -1;
        String dubS = s+s;
       // return dubS.charAt(s.length()- suffixes[stringNum] + d);
        return dubS.charAt(stringNum + d);
    }


    // 3-way string quicksort a[lo..hi] starting at dth character
    private static void sort(String a, int lo, int hi, int d, int[] suffixes) { 

        // cutoff to insertion sort for small subarrays
        if (hi <= lo + CUTOFF) {
            insertion(a, lo, hi, d, suffixes);
            return;
        }

        int lt = lo, gt = hi;
        int v = charAt(a,suffixes[lo], d, suffixes);
        int i = lo + 1;
        while (i <= gt) {
            int t = charAt(a,suffixes[i], d, suffixes);
            if      (t < v) exch(a, lt++, i++, suffixes);
            else if (t > v) exch(a, i, gt--, suffixes);
            else              i++;
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]. 
        sort(a, lo, lt-1, d, suffixes);
        if (v >= 0) sort(a, lt, gt, d+1, suffixes);
        sort(a, gt+1, hi, d, suffixes);
    }

    // sort from a[lo] to a[hi], starting at the dth character
    private static void insertion(String a, int lo, int hi, int d, int[] suffixes) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a, j, j-1, d, suffixes); j--)
                exch(a, j, j-1, suffixes);
    }

    // exchange a[i] and a[j]
    private static void exch(String a, int i, int j, int[] suffixes) {
        int temp = suffixes[i];
        suffixes[i] = suffixes[j];
        suffixes[j] = temp;
    }

    // is v less than w, starting at character d
    // DEPRECATED BECAUSE OF SLOW SUBSTRING EXTRACTION IN JAVA 7
    // private static boolean less(String v, String w, int d) {
    //    assert v.substring(0, d).equals(w.substring(0, d));
    //    return v.substring(d).compareTo(w.substring(d)) < 0; 
    // }

    // is v less than w, starting at character d
    private static boolean less(String s, int v, int w, int d, int[] suffixes) {
        //assert v.substring(0, d).equals(w.substring(0, d));
    	String dubS = s+s;
    	for (int i = d; i < Math.min(suffixes.length, suffixes.length); i++) {
            if (dubS.charAt(v + i) < dubS.charAt(w + i)) return true;
            if (dubS.charAt(v + i) > dubS.charAt(w + i)) return false;
        }
        //return v.length() < w.length();
        System.out.println("FAILED");
        return true;
    }

    // is the array sorted
    private static boolean isSorted(String[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i].compareTo(a[i-1]) < 0) return false;
        return true;
    }


    public static void main(String[] args) {

        // read in the strings from standard input
        //String[] a = StdIn.readAllStrings();
    	String[] a = new String[5];
    	a[0] = "dfgk";
    	a[1] = "xvcbsf";
    	a[2] = "yrhg";
    	a[3] = "noieed";
    	a[4] = "odfdds";
        int N = a.length;

        // sort the strings
        //sort(a);

        // print the results
        for (int i = 0; i < N; i++)
            StdOut.println(a[i]);
    }
}
