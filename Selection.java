public class Selection {
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i < hi; i++) {
            int min = i;
            for (int j = i + 1; j < hi; j++) {
                if (less(a[j], a[min])) min = j;
            }
            exch(a, i, min);
        }
    }


    public static void main(String[] args) {
        Integer[] arr = SortUtils.getRandomIntArr(99);
        sort(arr, 0, arr.length);
        assert SortUtils.isSorted(arr, 0, arr.length);
    }
}
