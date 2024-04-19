public class BestQuickArray {
    public static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void preposMid(Comparable[] a) {
        preposMid(a, 0, a.length - 1);
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void preposMid(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
//        int j = partition(a, lo, hi);
        int mid = lo + (hi - lo) / 2;
        exch(a, lo, mid);
        preposMid(a, lo, mid - 1);
        preposMid(a, mid + 1, hi);
    }

//    public static int partition(Comparable[] a, int lo, int hi) {
//        Comparable v = a[lo];
//        int i = lo, j = hi + 1;
//        while (true) {
//            while (less(a[++i], v)) {
//                if (i == hi) {
//                    break;
//                }
//            }
//            while (less(v, a[--j])) {
//                if (j == lo) {
//                    break;
//                }
//            }
//            if (i >= j) {
//                break;
//            }
//            exch(a, i, j);
//        }
//        exch(a, lo, j);
//        return j;
//    }

    public static void main(String[] args) {
        Integer[] arr = SortUtils.getIntArr(10);
        SortUtils.printIntegerArr(arr);
        preposMid(arr);
        SortUtils.printIntegerArr(arr);
        assert SortUtils.isSorted(arr, 0, arr.length) : "未全量排序";
    }
}
