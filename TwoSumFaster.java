/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

public class TwoSumFaster {
    private TwoSumFaster() {
    }

    public static int count(int[] a) {
        int n = a.length;
        int count = 0;
        int i = 0;
        int j = n - 1;
        while (i < j && a[i] <= 0) {
            if (a[i] + a[j] == 0) {
                count++;
                i++;
                j--;
            }
            else if (a[i] + a[j] < 0) {
                i++;
            }
            else {
                j--;
            }
        }
        return count;
    }

    /**
     * Reads in a sequence of integers from a file, specified as a command-line argument;
     * counts the number of triples sum to exactly zero; prints out the time to perform
     * the computation.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        int[] a = in.readAllInts();
        // 1. 假设数组已已升序排列
        // 2. 假设数组元素各不相同
        Arrays.sort(a);
        Stopwatch timer = new Stopwatch();
        int count = count(a);
        StdOut.println("elapsed time = " + timer.elapsedTime());
        StdOut.println(count);
    }
}
