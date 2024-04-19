import edu.princeton.cs.algs4.StdRandom;

/**
 * 2.3.18 三取样切分。
 * 为快速排序实现正文所述的三取样切分（参见 2.3.3.2 节）。运行双倍测试来确认这项改动的效果。
 */
public class MedianOfThreeQuick {
    public static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * 改进快速排序性能的第二个办法是使用子数组的一小部分元素的中位数来切分数组。这样做得
     * 到的切分更好，但代价是需要计算中位数。人们发现将取样大小设为 3 并用大小居中的元素切分的
     * 效果最好（请见练习 2.3.18 和练习 2.3.19）。我们还可以将取样元素放在数组末尾作为“哨兵”来
     * 去掉 partition() 中的数组边界测试。
     *
     * @return
     */
    public static int findMid(Comparable[] a, int lo, int hi) {
        if (hi - lo < 2) {
            return lo;
        }
        int r1 = lo;
        int r2 = StdRandom.uniformInt(lo + 1, hi);
        int r3 = hi;
        if (less(a[r1], a[r2])) {
            if (less(a[r2], a[r3])) {
                return r2;
            } else {
                return less(a[r1], a[r3]) ? r3 : r1;
            }
        } else {
            if (less(a[r3], a[r2])) {
                return r2;
            } else {
                return less(a[r1], a[r3]) ? r1 : r3;
            }
        }
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
        int mid = findMid(a, lo, hi);
        exch(a, mid, lo);
        Comparable v = a[lo];
        int i = lo, j = hi + 1;
        while (true) {
            while (less(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }
            while (less(v, a[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void main(String[] args) {
        Integer[] arr = SortUtils.getRandomIntArr(100);
        SortUtils.printIntegerArr(arr);
        sort(arr);
        SortUtils.printIntegerArr(arr);
        assert SortUtils.isSorted(arr, 0, arr.length) : "未全量排序";
    }
}
