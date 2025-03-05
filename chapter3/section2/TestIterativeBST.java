/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

package chapter3.section2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Objects;

public class TestIterativeBST {
    public static void main(String[] args) {
        IterativeBST<String, Integer> st;
        st = new IterativeBST<>();
        System.out.print("请输入您的Key（用空格或逗号分隔）: ");
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (!st.contains(word)) st.put(word, 1);
            else {
                st.put(word, st.get(word) + 1);
            }
            if (StdIn.readChar() == '\n') {
                break;
            }
        }

        String fn = "";
        if (args.length > 0) {
            fn = args[0].trim();
            execTest(st, fn);
            return;
        }

        while (true) {
            StdOut.print("请输入您要测试的方法：");
            fn = StdIn.readString().trim();
            StdIn.readLine();
            if (Objects.equals(fn, "exit")) {
                break;
            }
            else {
                execTest(st, fn.trim());
            }
        }
    }

    public static void execTest(IterativeBST<String, Integer> st, String fn) {
        switch (fn) {
            case "keys": {
                StdOut.println("keys():");
                for (String s : st.keys()) {
                    StdOut.println(s + " " + st.get(s));
                }
                break;
            }
            case "min": {
                StdOut.println("min(): " + st.min());
                break;
            }
            case "max": {
                StdOut.println("max(): " + st.max());
                break;
            }
            case "floor": {
                StdOut.print("请输入floor入参：");
                String key = StdIn.readString();
                StdIn.readLine(); // 清空无效输入
                StdOut.println("floor(" + key + "): " + st.floor(key));
                break;
            }
            case "ceiling": {
                StdOut.print("请输入ceiling入参：");
                String key = StdIn.readString();
                StdIn.readLine(); // 清空无效输入
                StdOut.println("ceiling(" + key + "): " + st.ceiling(key));
                break;
            }
            case "select": {
                StdOut.print("请输入select入参：");
                int index = StdIn.readInt();
                StdIn.readLine(); // 清空无效输入
                StdOut.println("select(" + index + "): " + st.select(index));
                break;
            }
            case "rank": {
                StdOut.print("请输入rank入参：");
                String key = StdIn.readString();
                StdIn.readLine(); // 清空无效输入
                StdOut.println("rank(" + key + "): " + st.rank(key));
                break;
            }
            case "delete": {
                StdOut.print("请输入delete入参：");
                String key = StdIn.readString();
                StdIn.readLine(); // 清空无效输入
                st.delete(key);
                StdOut.println("delete(" + key + ")");
                break;
            }
            case "deleteMin": {
                StdOut.println("deleteMin()");
                st.deleteMin();
                break;
            }
            case "deleteMax": {
                StdOut.println("deleteMax()");
                st.deleteMax();
                break;
            }
        }
    }
}
