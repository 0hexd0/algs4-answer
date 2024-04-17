import edu.princeton.cs.algs4.StdRandom;

/**
 * 2.3.17 哨兵。
 * 修改算法 2.5，去掉内循环 while 中的边界检查。由于切分元素本身就是一个哨兵（ v 不
 * 可能小于 a[lo]），左侧边界的检查是多余的。要去掉另一个检查，可以在打乱数组后将数组的
 * 最大元素放在 a[length-1] 中。该元素永远不会移动（除非和相等的元素交换），可以在所有
 * 包含它的子数组中成为哨兵。 注意：在处理内部子数组时，右子数组中最左侧的元素可以作为
 * 左子数组右边界的哨兵。
 */
public class QuickSortWithSentinel {
    public static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        // 查找最大元素下标
        int maxIdx = 0;
        for (int i = 1; i < a.length; i++) {
            if (less(a[maxIdx], a[i])) {
                maxIdx = i;
            }
        }
        // 将数组最大元素置于最右侧，将其作为哨兵
        exch(a, maxIdx, a.length - 1);
        sort(a, 0, a.length - 1);
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    public static int partition(Comparable[] a, int lo, int hi) {
        Comparable v = a[lo];
        int i = lo, j = hi + 1;
        while (true) {
            while (less(a[++i], v)) {
                // v是a[0], i == hi + 1时(取上一次sort的中点)，less判断不会通过所以不会进到这段代码
                // if (i == hi) {
                //   break;
                // }
            }

            while (less(v, a[--j])) {
                // v是a[0], j == lo时，less判断不会通过所以不会进到这段代码
                // if (j == lo) {
                //    break;
                // }
            }
            // i == hi + 1时，对应无哨兵版本是i=hi，此时i>=j仍然成立
            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void main(String[] args) {
        Integer[] arr = SortUtils.getRandomIntArr(10000);
        SortUtils.printIntegerArr(arr);
        sort(arr);
        SortUtils.printIntegerArr(arr);
        assert SortUtils.isSorted(arr, 0, arr.length) : "未全量排序";
    }
}
