/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

package chapter2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Exe_2_3_17 {
    public static int counter = 0;

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        StdRandom.shuffle(arr);
        sort(arr);
        StdOut.printf("%d num actually comparing count is %d\n", n, counter);
        StdOut.printf("2NlnN is %f", 2 * n * Math.log(n));
    }

    public static void sort(Comparable a[]) {
        StdRandom.shuffle(a);
        // 找到最大元素，置于数组最右侧，该元素永远不会移动
        int maxIdx = 0;
        for (int i = 1; i < a.length; i++) {
            if (less(a[maxIdx], a[i])) {
                maxIdx = i;
            }
        }
        exch(a, maxIdx, a.length - 1);
        sort(a, 0, a.length - 1);
        for (int i = 0; i < a.length; i++) {
            StdOut.printf("%d ", a[i]);
        }
        StdOut.printf("\n");
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
        Comparable v = a[lo];
        while (i < j) {
            while (less(a[++i], v)) {
                // 如果数组包含最右侧元素，它永远最大元素，即边界a[i] >= v必然成立
                // 所有的左子数组可使用切分元素作为哨兵
                // 右子数组要么有上一轮迭代的切分元素在最右侧，要么最右侧是最大元素
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
        counter++;
        return a.compareTo(b) < 0;
    }

    public static void exch(Comparable a[], int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
