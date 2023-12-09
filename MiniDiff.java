/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

public class MiniDiff {
    public static void print(double a[]) {
        double miniDiff = Double.POSITIVE_INFINITY;
        int miniIdx = 0;
        for (int i = 1; i < a.length; i++) {
            double diff = Math.abs(a[i] - a[i - 1]);
            if (diff < miniDiff) {
                miniDiff = diff;
                miniIdx = i;
            }
        }
        StdOut.println(a[miniIdx - 1]);
        StdOut.println(a[miniIdx]);
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        double[] a = in.readAllDoubles();
        Stopwatch timer = new Stopwatch();
        Arrays.sort(a);
        print(a);
        StdOut.println("elapsed time = " + timer.elapsedTime());
    }
}
