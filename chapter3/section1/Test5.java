/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

package chapter3.section1;

import edu.princeton.cs.algs4.StdOut;

import java.util.Objects;

public class Test5 {
    static class Time implements Comparable<Time> {
        int hour;
        int min;
        int sec;

        public Time(int hour, int min, int sec) {
            this.hour = hour;
            this.min = min;
            this.sec = sec;
        }

        public int compareTo(Time o) {
            if (this.hour - o.hour == 0) {
                if (this.min - o.min == 0) {
                    return this.sec - o.sec;
                }
                else {
                    return this.min - o.min;
                }
            }
            else {
                return this.hour - o.hour;
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Time time = (Time) obj;
            return this.hour == time.hour && this.min == time.min && this.sec == time.sec;
        }

        public int hashCode() {
            return Objects.hash(hour, min, sec);
        }

        public String toString() {
            return String.format("%02d", hour) + ":" + String.format("%02d", min) + ":"
                    + String.format("%02d", sec);
        }
    }

    public static void main(String[] args) {
        SequentialSearchST<Time, String> st;
        st = new SequentialSearchST<Time, String>();
        st.put(new Time(9, 0, 0), "Chicago");
        st.put(new Time(9, 0, 3), "Phoenix");
        st.put(new Time(9, 0, 13), "Houston");
        st.put(new Time(9, 0, 59), "Chicago");
        st.put(new Time(9, 1, 10), "Houston");
        st.put(new Time(9, 3, 13), "Chicago");
        st.put(new Time(9, 10, 11), "Chicago");
        st.put(new Time(9, 10, 25), "Chicago");
        st.put(new Time(9, 14, 25), "Chicago");
        st.put(new Time(9, 19, 32), "Chicago");
        st.put(new Time(9, 19, 46), "Chicago");
        st.put(new Time(9, 21, 5), "Chicago");
        st.put(new Time(9, 22, 43), "Chicago");
        st.put(new Time(9, 22, 54), "Chicago");
        st.put(new Time(9, 25, 52), "Chicago");
        st.put(new Time(9, 35, 21), "Chicago");
        st.put(new Time(9, 36, 14), "Chicago");
        st.put(new Time(9, 37, 44), "Chicago");
        StdOut.println("min " + st.min());
        StdOut.println("get(09:00:13) " + st.get(new Time(9, 0, 13)));
        StdOut.println("floor(09:05:00) " + st.floor(new Time(9, 5, 0)));
        StdOut.println("select(7) " + st.select(7));
        StdOut.println(
                "keys(09:15:00, 09:25:00) " + st.keys(new Time(9, 15, 0), new Time(9, 25, 0)));
        StdOut.println("ceiling(09:30:00) " + st.ceiling(new Time(9, 30, 0)));
        StdOut.println("max() " + st.max());
        StdOut.println(
                "size(09:15:00, 09:25:00) " + st.size(new Time(9, 15, 0), new Time(9, 25, 0)));
        StdOut.println(
                "rank(09:10:25) " + st.rank(new Time(9, 10, 25)));
    }
}
