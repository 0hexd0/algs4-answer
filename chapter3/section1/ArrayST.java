/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

package chapter3.section1;


import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

public class ArrayST<Key, Value> {
    public static void main(String[] args) {
        ArrayST<String, Integer> st;
        st = new ArrayST<String, Integer>(2);
        String[] strs = { "S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E" };
        for (int i = 0; i < strs.length; i++) {
            String key = strs[i];
            st.put(key, i);
        }
        for (String key : st.keys()) {
            StdOut.println(key + " " + st.get(key));
        }
    }

    public ArrayST(int capacity) {
        _keys = (Key[]) new Object[capacity];
        _values = (Value[]) new Object[capacity];
    }

    private Key[] _keys;
    private Value[] _values;
    private int N;

    void resize(int max) {
        Key[] tempKeys = (Key[]) new Object[max];
        Value[] tempValues = (Value[]) new Object[max];
        for (int i = 0; i < N; i++) {
            tempKeys[i] = _keys[i];
            tempValues[i] = _values[i];
        }
        _keys = tempKeys;
        _values = tempValues;
    }

    void put(Key key, Value value) {
        if (value == null) {
            delete(key);
            return;
        }
        // 存在key
        for (int i = 0; i < N; i++) {
            if (key.equals(_keys[i])) {
                _values[i] = value;
                return;
            }
        }
        // 新增key
        if (N == _keys.length) {
            // 数组满，扩容
            resize(N * 2);
        }
        _keys[N] = key;
        _values[N] = value;
        N++;
    }

    Value get(Key key) {
        for (int i = 0; i < N; i++) {
            if (key.equals(_keys[i])) {
                return _values[i];
            }
        }
        return null;
    }

    void delete(Key key) {
        if (isEmpty()) {
            throw new IllegalArgumentException("表为空，无法删除");
        }
        for (int i = 0; i < N; i++) {
            if (key.equals(_keys[i])) {
                // 从i往后，按照i=i+1赋值
                for (int j = i; j < N - 1; j++) {
                    _keys[j] = _keys[j + 1];
                    _values[j] = _values[j + 1];
                }
                // 删除最后一项
                _keys[N - 1] = null;
                _values[N - 1] = null;
                N--;
                if (N == _keys.length / 4) {
                    resize(N / 2); // 缩容
                }
                return;
            }
        }
        throw new IllegalArgumentException("不存在的key");
    }

    boolean contains(Key key) {
        for (int i = 0; i < N; i++) {
            if (key.equals(_keys[i])) {
                return true;
            }
        }
        return false;
    }

    boolean isEmpty() {
        return N == 0;
    }

    int size() {
        return N;
    }

    Iterable<Key> keys() {
        List<Key> list = new ArrayList<Key>();
        for (int i = 0; i < _keys.length; i++) {
            if (_keys[i] != null) {
                list.add(_keys[i]);
            }
        }
        return list;
    }
}
