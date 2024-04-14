import edu.princeton.cs.algs4.StdOut;

public class IndirectMergeSort {
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static int[] sort(Comparable[] a) {
        int[] perm = new int[a.length]; // 间接数组
        for (int i = 0; i < a.length; i++) {
            perm[i] = i;
        }
        int[] aux = perm.clone(); // 辅助数组
        sort(a, 0, a.length - 1, perm, aux);
        return perm;
    }

    public static void sort(Comparable[] a, int lo, int hi, int[] perm, int[] aux) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid, perm, aux);
        sort(a, mid + 1, hi, perm, aux);

        if (less(a[perm[mid + 1]], a[perm[mid]])) {
            merge(a, lo, mid, hi, perm, aux);
        }
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi, int[] perm, int[] aux) {
        int i = lo, j = mid + 1;

        // 值复制到辅助数组中
        for (int k = lo; k <= hi; k++) {
            aux[k] = perm[k];
        }

        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                perm[k] = aux[j++];
            } else if (j > hi) {
                perm[k] = aux[i++];
            } else if (less(a[aux[j]], a[aux[i]])) {
                perm[k] = aux[j++];
            } else {
                perm[k] = aux[i++];
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = SortUtils.getRandomIntArr(100);
        SortUtils.printIntegerArr(arr);
        int[] perm = sort(arr);
        SortUtils.printIntegerArr(arr);
        for (int i = 0; i < perm.length; i++) {
            StdOut.printf("%d\t", arr[perm[i]]);
//            StdOut.printf("%d\t", perm[i]);
        }
    }
}
