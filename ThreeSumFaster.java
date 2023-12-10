/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

public class ThreeSumFaster {
    private ThreeSumFaster() {
    }

    public static int count(int[] a, int target, int startIdx, int endIdx) {
        int count = 0;
        int i = startIdx;
        int j = endIdx;
        while (i < j) {
            if (a[i] + a[j] == target) {
                count++;
                i++;
                j--;
            }
            else if (a[i] + a[j] < target) {
                i++;
            }
            else {
                j--;
            }
        }
        return count;
    }

    public static int count(int[] a) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            count += count(a, -a[i], i + 1, a.length - 1);
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
