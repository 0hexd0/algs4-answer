/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.HashSet;
import java.util.Set;

public class CollectionQuestion {
    private static final int MAXIMUM_INTEGER = 1000000;

    // This class should not be instantiated.
    private CollectionQuestion() {
    }

    public static double harmonic(long n) {
        double sum = 0;
        for (long i = 1; i <= n; i++) {
            sum += 1.0 / i;
        }
        return sum;
    }

    public static double numTrial(long n, int times) {
        int totalTimes = 0;
        Set<Long> s = new HashSet<>();
        for (int i = 0; i < times; i++) {
            s.clear();
            int curTimes = 0;
            while (s.size() < n) {
                long randomInt = StdRandom.uniformLong(n);
                s.add(randomInt);
                curTimes++;
            }
            totalTimes += curTimes;
        }
        return (double) totalTimes / times;
    }

    public static void main(String[] args) {
        for (long n = 250; true; n += n) {
            double num = numTrial(n, 1000);
            double predict = n * harmonic(n);
            StdOut.printf("%7d %7d %7d %5.2f\n", n, Math.round(num), Math.round(predict),
                          predict / num);
        }
    }
}
