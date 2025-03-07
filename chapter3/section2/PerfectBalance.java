/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

package chapter3.section2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.stream.StreamSupport;

// 完美平衡
public class PerfectBalance {
    public static void main(String[] args) {
        BST<String, Integer> keySt = new BST<>();
        System.out.print("请输入您的Key（用空格或逗号分隔）: ");
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (word.trim().isEmpty()) {
                continue;
            }
            if (!keySt.contains(word)) keySt.put(word, 1);
            else {
                keySt.put(word, keySt.get(word) + 1);
            }
            if (StdIn.readChar() == '\n') {
                break;
            }
        }
        String[] keys = StreamSupport.stream(keySt.keys().spliterator(), false)
                                     .toArray(String[]::new);
        BST<String, Integer> pbSt = new BST<>();
        addMiddle(pbSt, keys, 0, keySt.size() - 1);
        StdOut.println(pbSt.keys());
        pbSt.draw();
    }

    public static void addMiddle(BST<String, Integer> st, String[] keys, int lo, int hi) {
        if (lo > hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        StdOut.println("mid" + mid);
        // 插入中间节点
        String word = keys[mid];
        if (!st.contains(word)) st.put(word, 1);
        else {
            st.put(word, st.get(word) + 1);
        }
        // 插入左侧节点
        addMiddle(st, keys, lo, mid - 1);
        // 插入右侧节点
        addMiddle(st, keys, mid + 1, hi);
    }
}
