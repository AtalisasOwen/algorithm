package sort.SortX;

/**
 * Created by 顾文涛 on 2017/2/22.
 */

import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *  Compilation:  javac QuickBest.java
 *  Execution:    java QuickBest n
 *  Dependencies: StdOut.java
 *
 *  Generate a best-case input of size n for standard quicksort.
 *
 *  % java QuickBest 3
 *  BAC
 *
 *  % java QuickBest 7
 *  DACBFEG
 *
 *  % java QuickBest 15
 *  HACBFEGDLIKJNMO
 *
 ******************************************************************************/

public class QuickBest {

    //对于没有重复元素的数组，可以找到数组中的中间大小的元素
    //然后就可以用中间元素去切分数组，

    // postcondition: a[lo..hi] is best-case input for quicksorting that subarray
    private static void best(int[] a, int lo, int hi) {

        // precondition:  a[lo..hi] contains keys lo to hi, in order
        for (int i = lo; i <= hi; i++)
            assert a[i] == i;

        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        best(a, lo, mid-1);
        best(a, mid+1, hi);
        exch(a, lo, mid);
    }

    public static int[] best(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = i;
        best(a, 0, n-1);
        return a;
    }

    // exchange a[i] and a[j]
    private static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


    public static void main(String[] args) {
        String alphabet = "DACBFEG";
        int n = 7;
        int[] a = best(n);
        for (int i = 0; i < n; i++)
            //StdOut.println(a[i]);
            StdOut.print(alphabet.charAt(a[i]));
        StdOut.println();
    }
}
