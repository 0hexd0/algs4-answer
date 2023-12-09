/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class LocalMin {
    public static int find(int[] a) {
        int left = 0;
        int right = a.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid > 0 && a[mid] > a[mid - 1]) {
                right = mid - 1;
            }
            else if (mid < a.length - 1 && a[mid] > a[mid + 1]) {
                left = mid + 1;
            }
            else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int[] a = in.readAllInts();
        // 1. 假设N个整数互不相同
        Stopwatch timer = new Stopwatch();
        StdOut.println(find(a));
        StdOut.println("elapsed time = " + timer.elapsedTime());
    }
}
