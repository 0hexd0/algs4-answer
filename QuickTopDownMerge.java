import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class QuickTopDownMerge {
    public static int merge(Comparable[] a, int lo, int mid, int hi, Comparable[] aux) {
        int i = lo, j = hi;

        int counter = 0; // 记录访问数组次数

        // 前半部分值升序复制到辅助数组中
        for (int k = lo; k <= mid; k++) {
            aux[k] = a[k];
        }

        // 后半部分值降序复制到辅助数组中
        for (int k = 0; k <= hi - mid - 1; k++) {
            aux[mid + 1 + k] = a[hi - k];
        }

        counter += (hi - lo + 1) * 2;

        for (int k = lo; k <= hi; k++) {
            if (less(aux[j], aux[i])) {
                a[k] = aux[j--];
            } else {
                a[k] = aux[i++];
            }
            counter += 4;
        }
//        StdOut.printf("counter is %d\n", counter);
        return counter;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }


    public static int sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length]; // 辅助数组
        return sort(a, 0, a.length - 1, aux);
    }

    public static int sort(Comparable[] a, int lo, int hi, Comparable[] aux) {
        int counter = 0;
        if (hi <= lo) return counter;
        int mid = lo + (hi - lo) / 2;
        counter += sort(a, lo, mid, aux);
        counter += sort(a, mid + 1, hi, aux);
        counter += 2;
        if (less(a[mid + 1], a[mid])) {
            counter += merge(a, lo, mid, hi, aux);
        }
        return counter;
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

    public static void main(String[] args) {
        int maxN = 512;
        StdDraw.setCanvasSize(800, 1000);
        StdDraw.setPenRadius(0.01);
        StdDraw.setXscale(-100, maxN + 100);
        StdDraw.setYscale(-1000, maxN * 50);

        for (int n = 1; n <= maxN; n++) {
            Integer[] arr = getRandomIntArr(n);
            int count = sort(arr);
            StdDraw.setPenColor(StdDraw.GREEN);
            StdDraw.point(n, 6 * n * Math.log(n)); // 访问上限 6NlogN
            StdDraw.setPenColor(StdDraw.BOOK_RED);
            StdDraw.point(n, count);
            StdOut.printf("count is %d\n", count);
            for (int j = 0; j < arr.length; j++) {
                StdOut.printf("%d \t", arr[j]);
            }
            StdOut.println();
        }
    }
}
