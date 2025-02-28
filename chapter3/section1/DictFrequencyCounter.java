/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

package chapter3.section1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Objects;

public class DictFrequencyCounter {
    public static void main(String[] args) {
        String path = args[0];
        In input = new In(path);
        String[] dict = input.readAllStrings();

        BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>(1);
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            int index = indexInDict(word, dict);
            if (index != -1) {
                if (!st.contains(word)) st.put(word, 1);
                else {
                    st.put(word, st.get(word) + 1);
                }
            }
        }
        printFrequencyTable(st);
        printDictTable(st);
    }

    public static int indexInDict(String word, String[] dict) {
        for (int i = 0; i < dict.length; i++) {
            if (Objects.equals(word, dict[i])) {
                return i;
            }
        }
        return -1;
    }

    static class Item implements Comparable<Item> {
        String key;
        Integer rank;

        public Item(String key, Integer rank) {
            this.key = key;
            this.rank = rank;
        }


        public int compareTo(Item o) {
            return rank.compareTo(o.rank);
        }
    }

    // 按频率顺序打印
    public static void printFrequencyTable(BinarySearchST<String, Integer> st) {
        StdOut.println("频率序表");
        Item[] items = new Item[st.size()];
        int counter = 0;
        for (String key : st.keys()) {
            items[counter++] = new Item(key, st.get(key));
        }
        Quick.sort(items);
        StdOut.println("word\ttimes");
        for (int i = 0; i < items.length; i++) {
            String key = items[i].key;
            StdOut.println(key + "\t" + st.get(key));
        }
    }

    // 按字典顺序打印
    public static void printDictTable(BinarySearchST<String, Integer> st) {
        StdOut.println("字典序表");
        StdOut.println("word\ttimes");
        for (String key : st.keys()) {
            StdOut.println(key + "\t" + st.get(key));
        }
    }
}
