import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class SortUtils {
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i < hi; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    public static Integer[] getRandomIntArr(int n) {
        int[] intArray = new int[n];
        for (int i = 0; i < n; i++) {
            intArray[i] = i;
        }
        StdRandom.shuffle(intArray, 0, n);
        // 将int数组转换为Integer对象数组
        Integer[] integerArray = new Integer[intArray.length];
        for (int i = 0; i < intArray.length; i++) {
            integerArray[i] = Integer.valueOf(intArray[i]);
        }
        return integerArray;
    }

    public static void printIntegerArr(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            StdOut.printf("%d\t", arr[i]);
        }
        StdOut.println("");
    }

    public static void printIntegerArr(Integer[] arr, int splitLen) {
        for (int i = 0; i < arr.length; i++) {
            if (i > 0 && i % splitLen == 0) {
                StdOut.printf(";\t");
            }
            StdOut.printf("%d\t", arr[i]);
        }
        StdOut.println("");
    }

    public static void printArr(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {
            StdOut.printf("%d\t", arr[i]);
        }
        StdOut.println();
    }

    public static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
