/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

package chapter3.section1;


import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

public class SelfOrganizingArrayST<Key, Value> {
    public static void main(String[] args) {
        SelfOrganizingArrayST<String, Integer> st;
        st = new SelfOrganizingArrayST<String, Integer>(2);
        String[] strs = { "S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E" };
        for (int i = 0; i < strs.length; i++) {
            String key = strs[i];
            st.put(key, i);
        }
        for (String key : st.keys()) {
            StdOut.println(key + " " + st.get(key));
        }
    }

    public SelfOrganizingArrayST(int capacity) {
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

    void put(Key key, Value val) {
        if (val == null) {
            delete(key);
            return;
        }
        // 查找key
        for (int i = 0; i < N; i++) {
            if (key.equals(_keys[i])) {
                // 命中key
                // 前移编码：将命中项置于最前，中间项后移
                Key tempKey = _keys[i];
                // 从后往前移动元素
                frontMove(i, tempKey, val);
                return;
            }
        }
        // 新增key
        if (N == _keys.length) {
            // 数组满，扩容
            resize(N * 2);
        }
        _keys[N] = key;
        _values[N] = val;
        N++;
    }

    void frontMove(int i, Key key, Value val) {
        // 从后往前移动元素
        for (int j = i; j > 0; j--) {
            _keys[j] = _keys[j - 1];
            _values[j] = _values[j - 1];
        }
        _keys[0] = key;
        _values[0] = val;
    }

    Value get(Key key) {
        for (int i = 0; i < N; i++) {
            if (key.equals(_keys[i])) {
                // 命中key
                // 前移编码：将命中项置于最前，中间项后移
                Value tempVal = _values[i];
                Key tempKey = _keys[i];
                // 从后往前移动元素
                frontMove(i, tempKey, tempVal);
                return tempVal;
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
                    resize(_keys.length / 2); // 缩容
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
