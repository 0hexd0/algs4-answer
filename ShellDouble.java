/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ShellDouble {
    public static void sort(double[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) h = 3 * h + 1; // 1, 4, 13, 40, 121, 363, 1093
        int counter = 0;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    counter++;
                    exch(a, j, j - h);
                }
            }

            h = h / 3;
        }
        StdOut.println("ratio is " + counter / (1.0 * N));
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(double[] a, int i, int j) {
        double t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i] + " ");
        }
        StdOut.println();
    }

    private static void show(int[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i] + " ");
        }
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // String[] a = StdIn.readStrings();
        for (int i = 2; i < 100; i++) {
            double[] arr = new double[(int) Math.pow(10, i)];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = StdRandom.uniformDouble();
            }
            sort(arr);
        }
        // sort(a);
        // assert isSorted(a);
        // StdOut.println("show a");
        // show(a);
    }
}
