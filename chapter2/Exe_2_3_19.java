/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

package chapter2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 五取样切分
 */
public class Exe_2_3_19 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        StdRandom.shuffle(arr);
        sort(arr);
        StdOut.printf("2NlnN is %f", 2 * n * Math.log(n));
    }

    public static void sort(Comparable a[]) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable a[], int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    public static int partition(Comparable a[], int lo, int hi) {
        int i = lo;
        int j = hi + 1;

        int midP = getMedium(a);
        exch(a, midP, lo); // 将取样中位数置于切分位
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

    public static boolean less(Comparable[] a, int i, int j) {
        return a[i].compareTo(a[j]) < 0;
    }

    public static void exch(Comparable a[], int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /** 五个随机索引的中位数 **/
    public static int getMedium(Comparable a[]) {
        int i = StdRandom.uniformInt(a.length);
        int j = StdRandom.uniformInt(a.length);
        int m = StdRandom.uniformInt(a.length);
        int n = StdRandom.uniformInt(a.length);
        int k = StdRandom.uniformInt(a.length);

        int sij, bij, smn, bmn;
        if (less(a, i, j)) {
            sij = i;
            bij = j;
        }
        else {
            sij = j;
            bij = i;
        }
        if (less(a, m, n)) {
            smn = m;
            bmn = n;
        }
        else {
            smn = n;
            bmn = m;
        }
        int r1, r2, r3, r4;
        if (less(sij, smn)) {
            r1 = sij;
        }
        else {
            r1 = smn;
        }
        if (less(bij, bmn)) {
            r4 = bmn;
        }
        else {
            r4 = bij;
        }

        for (int c = 0; c < 4; c++) {
            // if (less())
        }
    }
}
