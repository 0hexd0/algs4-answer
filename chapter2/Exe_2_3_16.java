/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

package chapter2;

import edu.princeton.cs.algs4.StdOut;

public class Exe_2_3_16 {
    public static void main(String[] args) {
        Integer[] arr = getArr(10);
        for (int i = 0; i < arr.length; i++) {
            StdOut.printf("%d ", arr[i]);
        }
    }

    // 构造使得快排性能最好的数组
    public static Integer[] getArr(int n) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        resetPivot(arr, 0, arr.length - 1);
        return arr;
    }

    // 将数组正中间元素和切分位（本例即lo）交换
    public static void resetPivot(Integer[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int centerPos = lo + (hi - lo) / 2; // 计算中间位置
        exch(arr, lo, centerPos); // 交换
        // 重新令左切分有序
        // 递归处理切分数组
        resetPivot(arr, lo + 1, centerPos);
        resetPivot(arr, centerPos + 1, hi);
    }

    public static void exch(Integer[] arr, int i, int j) {
        // StdOut.printf("exch %d with %d\n", i, j);
        Integer temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
