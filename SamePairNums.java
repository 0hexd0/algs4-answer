/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

public class SamePairNums {
    public static void main(String[] args) {
        In in = new In(args[0]);
        int a[] = in.readAllInts();

        Stopwatch timer = new Stopwatch();
        int count = count(a);
        StdOut.println("elapsed time = " + timer.elapsedTime());
        StdOut.println(count);
    }

    public static int count(int a[]) {
        int n = a.length;
        Arrays.sort(a);
        int counter = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[j] == a[i]) {
                    counter++;
                }
                else {
                    break;
                }
            }
        }
        return counter;
    }
}
