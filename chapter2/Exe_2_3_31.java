/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

package chapter2;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class Exe_2_3_31 {
    private static final int MAXIMUM_INTEGER = 1000000;
    public static int times = 100;

    public static class SortInfo {
        public SortInfo(int lo, int hi) {
            this.lo = lo;
            this.hi = hi;
        }

        int lo;
        int hi;
    }

    public static double timeTrial(int n, int m) {
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniformInt(-MAXIMUM_INTEGER, MAXIMUM_INTEGER);
        }
        Stopwatch timer = new Stopwatch();
        for (int i = 0; i < times; i++) {
            sort(a, m);
        }
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.setPenRadius(0.005);
        double maxX = T + T - 1;
        double xPadding = maxX * 0.1;
        StdDraw.setXscale(-xPadding, maxX + xPadding);
        double maxY = 0;
        double minY = 100000000;
        for (int i = 0; i < T; i++) {
            double t = timeTrial(N, 1);
            StdOut.printf("t is %f ", t);

            if (t > maxY) {
                maxY = t;
            }
            if (t < minY) {
                minY = t;
            }
            if (minY != maxY) {
                double yPadding = (maxY - minY) * 0.1;
                StdDraw.setYscale(-yPadding, maxY + yPadding);
                StdDraw.filledRectangle(2 * i + 0.5, t / 2.0, 0.5, t / 2.0);
            }

        }
    }

    // 基于栈的快排，防止调用栈溢出
    public static void sort(Comparable a[], int m) {
        Stack<SortInfo> s = new Stack<>();
        s.push(new SortInfo(0, a.length - 1));
        sort(a, s, m);
        assert isSorted(a) : "数组有序";
    }

    public static void insertSort(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    public static void sort(Comparable a[], Stack<SortInfo> s, int m) {
        while (!s.isEmpty()) {
            SortInfo info = s.pop();
            if (info.hi - info.lo <= m) {
                insertSort(a, info.lo, info.hi);
            }
            else {
                // [lo, hi + 1)随机取下标
                int pivot = StdRandom.uniformInt(info.lo, info.hi + 1);
                exch(a, info.lo, pivot);
                int j = partition(a, info.lo, info.hi);
                s.push(new SortInfo(info.lo, j - 1));
                s.push(new SortInfo(j + 1, info.hi));
            }
        }
    }

    public static int partition(Comparable a[], int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while (i < j) {
            while (less(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }
            while (less(v, a[--j])) {
            }
            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void exch(Comparable a[], int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static boolean isSorted(Comparable a[]) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }
}
