import chapter2.Exe_2_3_27;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * The {@code DoublingRatio} class provides a client for measuring
 * the running time of a method using a doubling ratio test.
 * <p>
 * For additional documentation, see <a href="https://algs4.cs.princeton.edu/14analysis">Section
 * 1.4</a>
 * of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class DoublingRatio {
    private static final int MAXIMUM_INTEGER = 1000000;

    // This class should not be instantiated.
    private DoublingRatio() {
    }

    /**
     * Returns the amount of time to call {@code ThreeSum.count()} with <em>n</em>
     * random 6-digit integers.
     *
     * @param n the number of integers
     * @return amount of time (in seconds) to call {@code ThreeSum.count()}
     * with <em>n</em> random 6-digit integers
     */
    public static double timeTrial(int n, int times) {
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniformInt(-MAXIMUM_INTEGER, MAXIMUM_INTEGER);
        }
        Stopwatch timer = new Stopwatch();
        for (int i = 0; i < times; i++) {
            // int ignore = ThreeSumFast.count(a);
            // Exe_2_3_18.sort(a);ß
            // Exe_2_3_6.sort(a);
            // Exe_2_3_22.sort(a);
            // Exe_2_3_23.sort(a);
            // Exe_2_3_25.sort(a, 7);
            Exe_2_3_27.sort(a, 7);
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
        int times = Integer.parseInt(args[0]);
        double prev = timeTrial(125, times);
        StdOut.printf("N time time/prev\n");
        for (int n = 250; true; n += n) {
            double time = timeTrial(n, times);
            StdOut.printf("%7d %7.1f %5.1f\n", n, time, time / prev);
            prev = time;
        }
    }
}
