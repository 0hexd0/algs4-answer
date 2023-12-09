/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class MaxDiff {
    public static double findMax(double a[]) {
        double max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        return max;
    }

    public static double findMin(double a[]) {
        double min = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] < min) {
                min = a[i];
            }
        }
        return min;
    }

    public static void print(double a[]) {
        StdOut.println(findMax(a) - findMin(a));
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        double[] a = in.readAllDoubles();
        Stopwatch timer = new Stopwatch();
        print(a);
        StdOut.println("elapsed time = " + timer.elapsedTime());
    }
}
