import edu.princeton.cs.algs4.Merge;

/**
 * 2.2.21 一式三份。
 * 给定三个列表，每个列表中包含 N 个名字，编写一个线性对数级别的算法来判定三份列表中是否含有公共的名字，如果有，返回第一个被找到的这种名字。
 */
public class TripartiteOffering {
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * 查找第一个公共位置
     */
    public static String findFirstSame(String[] l1, String[] l2, String[] l3) {
        Merge.sort(l2);
        Merge.sort(l3);
        for (int i = 0; i < l1.length; i++) {
            if (canFind(l2, l1[i]) && canFind(l3, l1[i])) {
                return l1[i];
            }
        }
        return null;
    }

    /*
     * find str in sorted l
     */
    public static boolean canFind(String[] l, String str) {
        int lo = 0;
        int hi = l.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (less(str, l[mid])) hi = mid - 1;
            else if (less(l[mid], str)) lo = mid + 1;
            else return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String[] l1 = {"hello", "张三", "李四"};
        String[] l2 = {"李四", "hello", "张三"};
        String[] l3 = {"张三", "hello", "张三"};
        String res = findFirstSame(l1, l2, l3);
        assert "hello".equals(res);
    }
}
