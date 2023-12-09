/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

/** 有理数 */
class RationalNumber {
    /** 分子 */
    int numerator;
    /** 分母 */
    int denominator;

    public RationalNumber(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public String toString() {
        return this.numerator + " / " + this.denominator;
    }
}


public class FractionBSearch {

    // find p/q
    public static RationalNumber bruteFind(int N, double x) {
        double min_diff = 1.0;
        int p_closest = 0;
        int q_closest = 0;
        for (int p = 1; p < N; p++) {
            for (int q = p + 1; q < N; q++) {
                double val = (double) p / q;
                double diff = Math.abs(val - x);
                if (diff < min_diff) {
                    min_diff = diff;
                    p_closest = p;
                    q_closest = q;
                }
            }
        }
        return new RationalNumber(p_closest, q_closest);
    }

    // Stern–Brocot tree 是二叉树，所以复杂度是lgN
    public static RationalNumber fareyFind(int N, double x) {
        int startP = 0;
        int startQ = 1;
        int endP = 1;
        int endQ = 1;
        while (startQ + endQ < N) {
            int middleP = startP + endP;
            int middleQ = startQ + endQ;
            double middleVal = (double) middleP / middleQ;
            if (middleVal == x) {
                return new RationalNumber(middleP, middleQ);
            }
            else if (middleVal < x) {
                startP = middleP;
                startQ = middleQ;
            }
            else {
                endP = middleP;
                endQ = middleQ;
            }
        }
        double startError = Math.abs((double) startP / startQ - x);
        double endError = Math.abs((double) endP / endQ - x);
        if (startError < endError) {
            return new RationalNumber(startP, startQ);
        }
        else {
            return new RationalNumber(endP, endQ);
        }
    }

    public static void main(String[] args) {
        RationalNumber r1 = bruteFind(10000, 11.0 / 999);
        StdOut.println("brutePrint:");
        StdOut.println(r1);
        StdOut.println("fareyPrint:");
        RationalNumber r2 = fareyFind(10000, 11.0 / 999);
        StdOut.println(r2);
    }
}
