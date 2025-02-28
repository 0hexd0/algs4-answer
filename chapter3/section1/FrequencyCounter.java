/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

package chapter3.section1;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FrequencyCounter {
    public static void main(String[] args) {
        int minlen = Integer.parseInt(args[0]);
        SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
        String lastPutWord = "";
        int wordCounter = 0;
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (word.length() < minlen) continue; // 忽略较短单词
            if (!st.contains(word)) st.put(word, 1);
            else {
                st.put(word, st.get(word) + 1);
            }
            lastPutWord = word;
            wordCounter++;
        }
        StdOut.println("The word counter is " + wordCounter);
        StdOut.println("The last put word is " + lastPutWord);
        Queue<String> mostPutWordQ = new Queue<String>();
        int lastMaxTimes = 0;
        for (String word : st.keys()) {
            int curTimes = st.get(word);
            if (curTimes == lastMaxTimes) {
                // 同最大一样，入队
                mostPutWordQ.enqueue(word);
            }
            else if (curTimes > lastMaxTimes) {
                // 超过最大，清空队列后入队
                while (!mostPutWordQ.isEmpty()) {
                    mostPutWordQ.dequeue();
                }
                mostPutWordQ.enqueue(word);
                // 重设最大值
                lastMaxTimes = curTimes;
            }
        }
        // 打印所有最大的key
        StdOut.println("These words have most put times:");
        for (String key : mostPutWordQ) {
            StdOut.println(key + " " + st.get(key));
        }
    }
}
