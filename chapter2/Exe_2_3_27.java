/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

package chapter2;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class Exe_2_3_27 {
    private static final int MAXIMUM_INTEGER = 1000000;
    public static int times = 1;

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
        int n = Integer.parseInt(args[0]);
        double[] resArr = new double[31];
        for (int m = 0; m < 31; m++) {
            double t = timeTrial(n, m);
            resArr[m] = t;
            StdOut.printf("M is %d, time is %f\n", m, t);
        }
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setPenRadius(0.01);
        double xPadding = 31 * 0.1;
        StdDraw.setXscale(0 - xPadding, 30 + xPadding);
        double min = StdStats.min(resArr);
        double max = StdStats.max(resArr);
        double yPadding = (max - min) * 0.1;
        StdDraw.setYscale(min - yPadding, max + yPadding);
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        for (int i = 1; i < 31; i++) {
            StdDraw.line(i - 1, resArr[i - 1], i, resArr[i]);
        }
    }

    // 基于栈的快排，防止调用栈溢出
    public static void sort(Comparable a[], int m) {
        StdRandom.shuffle(a); // 打乱
        Stack<SortInfo> s = new Stack<>();
        s.push(new SortInfo(0, a.length - 1));
        sort(a, s, m);
        insertSort(a, 0, a.length - 1); // 因为忽略了小数组，补充一次完整的插入排序
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
                return;
            }
            else {
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
}
