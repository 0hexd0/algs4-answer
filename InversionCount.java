/**
 * 2.2.19 倒置。
 * 编写一个线性对数级别的算法统计给定数组中的“倒置”数量（即插入排序所需的交换次数，请见 2.1 节）。
 * 这个数量和 Kendal1 tau 距离有关，请见 2.5 节。
 */
public class InversionCount {
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static int count(Comparable[] arr) {
        int counter = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (less(arr[j], arr[i])) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        String str = "EXAMPLE";
        Character[] charArray = new Character[str.length()];

        for (int i = 0; i < str.length(); i++) {
            charArray[i] = str.charAt(i);
        }

        assert count(charArray) == 11 : "倒置数量错误";
    }
}
