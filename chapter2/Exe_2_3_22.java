/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

package chapter2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Exe_2_3_22 {

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        StdRandom.shuffle(arr);
        sort(arr);
        assert isSorted(arr) : "数组有序";
        assert arr.length == n : "数组数量正确";

        for (int i = 0; i < arr.length; i++) {
            StdOut.printf("%d ", arr[i]);
        }
        StdOut.println();
    }

    public static void sort(Comparable a[]) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable a[], int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int i = lo + 1;
        int j = hi;

        int p = lo + 1;
        int q = hi;


        Comparable v = a[lo];
        // 和v相等元素置于两端
        while (i <= j) {
            // 右侧和切分元素相同的元素
            int jcmp = a[j].compareTo(v);
            if (jcmp == 0) {
                exch(a, j, q);
                j--;
                q--;
            }
            int icmp = a[i].compareTo(v);
            if (icmp == 0) {
                exch(a, p, i);
                i++;
                p++;
            }
            else if (icmp < 0) {
                i++;
            }
            else {
                exch(a, j, i);
                j--;
            }
        }
        // 将和v相等的元素交换到正确位置
        int t = lo;
        for (int k = p; k < i; k++) {
            exch(a, k, t++);
        }
        t = hi;
        for (int k = j + 1; k <= q; k++) {
            exch(a, k, t--);
        }
        // 递归
        sort(a, lo, lo + i - p);
        sort(a, hi - (q - j) + 1, hi);
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
