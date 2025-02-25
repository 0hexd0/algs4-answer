/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

package chapter3.section1;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Test1 {
    public static void main(String[] args) {
        ST<String, Double> st;
        st = new ST<String, Double>();
        st.put("A+", 4.33);
        st.put("A", 4.00);
        st.put("A", 3.67);
        st.put("B+", 3.33);
        st.put("B", 3.00);
        st.put("B-", 2.67);
        st.put("C+", 2.33);
        st.put("C", 2.00);
        st.put("C-", 1.67);
        st.put("D", 1.00);
        st.put("F", 0.00);
        String[] scores = StdIn.readAllStrings();
        double sum = 0.0;
        for (String s : scores) {
            sum += st.get(s);
        }
        StdOut.printf("GPA is %f", sum / scores.length);
    }
}
