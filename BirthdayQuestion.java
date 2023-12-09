import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.HashSet;
import java.util.Set;

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
public class BirthdayQuestion {
    private static final int MAXIMUM_INTEGER = 1000000;

    // This class should not be instantiated.
    private BirthdayQuestion() {
    }

    public static double numTrial(long n, int times) {
        int t = 0;
        for (int i = 0; i < times; i++) {
            Set<Long> s = new HashSet<>();
            while (true) {
                long randomInt = StdRandom.uniformLong(n);
                if (!s.add(randomInt)) {
                    t += s.size();
                    break;
                }
            }
        }
        return (double) t / times;
    }

    public static void main(String[] args) {
        // int times = Integer.parseInt(args[0]);
        // int prev = numTrial(100);
        for (long n = 250; true; n += n) {
            double num = numTrial(n, 1000);
            double predict = Math.sqrt(n / 2.0 * Math.PI);
            StdOut.printf("%7d %7d %7d %5.1f\n", n, Math.round(num), Math.round(predict),
                          predict / num);
        }
    }
}
