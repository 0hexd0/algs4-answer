/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class AutoBoxingCostInt {
    private static final int MAXIMUM_INTEGER = 1000000;

    /**
     * Returns the amount of time to call {@code ThreeSum.count()} with <em>n</em>
     * random 6-digit integers.
     *
     * @param n the number of integers
     * @return amount of time (in seconds) to call {@code ThreeSum.count()}
     * with <em>n</em> random 6-digit integers
     */
    public static double timeTrial(int n) {
        FixedCapacityStackOfInts stack = new FixedCapacityStackOfInts(n);

        Stopwatch timer = new Stopwatch();
        for (int i = 0; i < n; i++) {
            stack.push(StdRandom.uniformInt(-MAXIMUM_INTEGER, MAXIMUM_INTEGER));
        }
        for (int i = 0; i < n; i++) {
            stack.pop();
        }
        for (int i = 0; i < n; i++) {
            stack.push(StdRandom.uniformInt(-MAXIMUM_INTEGER, MAXIMUM_INTEGER));
            stack.pop();
        }
        return timer.elapsedTime();
    }

    /**
     * Prints table of running times to call {@code ThreeSum.count()}
     * for arrays of size 250, 500, 1000, 2000, and so forth, along
     * with ratios of running times between successive array sizes.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        double prev = timeTrial(150);
        for (int n = 300; true; n += n) {
            double time = timeTrial(n);
            StdOut.printf("%7d %7.1f %5.1f\n", n, time, time / prev);
            prev = time;
        }
    }
}
