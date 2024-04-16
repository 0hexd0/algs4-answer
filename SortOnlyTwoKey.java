/**
 * 2.3.5 给出一段代码将已知只有两种主键值的数组排序。
 */
public class SortOnlyTwoKey {
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    public static void sort(Comparable[] a) {
        int i = 1;
        Comparable v = a[0];
        int j = a.length;
        while (j > 0 && i < j) {
            if (less(a[i], v)) {
                exch(a, i, 0);
                v = a[0];
            } else if (less(v, a[i])) {
                exch(a, i, --j);
            } else {
                i++;
            }
        }
    }

    public static void main(String[] args) {
        int[] intArray = {1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2, 2, 1};
        Integer[] integerArray = new Integer[intArray.length];
        for (int i = 0; i < intArray.length; i++) {
            integerArray[i] = Integer.valueOf(intArray[i]);
        }
        sort(integerArray);
        SortUtils.printIntegerArr(integerArray);
    }
}
