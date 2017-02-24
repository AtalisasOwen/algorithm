package search.SymbolTable;

import ADT.Queue;

/**
 * Created by 顾文涛 on 2017/2/24.
 */
public class ArraysST<Key,Value> {
    Key[] keys;
    Value[] values;
    int n=0;
    public final int SIZE= 8;
    public ArraysST(){
        keys=(Key[]) new Object[SIZE];//因为Key泛型还未定义，没有构造方法
        values=(Value[])new Object[SIZE];
    }
    public void put(Key key,Value value){
        //如果key 已经存在，则删除
        delete(key);

        if (n >= values.length) resize(2*n);

        //增加新的元素
        values[n] = value;
        keys[n] = key;
        n++;
    }
    public Value get(Key key){
        for(int i=0;i<n;i++)
            if (keys[i].equals(key)) return values[i];
        return null;
    }

    public void delete(Key key) {
        for (int i = 0; i < n; i++) {
            if (key.equals(keys[i])) {
                keys[i] = keys[n - 1];
                values[i] = values[n - 1];
                keys[n - 1] = null;
                values[n - 1] = null;
                n--;
                if (n > 0 && n == keys.length / 4) resize(keys.length / 2);
                return;
            }
        }
    }

    public boolean contains(Key key){
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    public boolean isEmpty(Key key){
        return n==0;
    }

    public int size(){
        return n;
    }

    public Iterable<Key> keys(){
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < n; i++)
            queue.enqueue(keys[i]);
        return queue;
    }

    private void resize(int capacity) {
        /*调整数组大小
        确保有1/4-1/2的数组不为空
        重新弄一个数组，用于把元素复制进来
        然后把旧数组的指针指向新数组
        JVM自动回收旧数组
         */
        Key[]   tempk = (Key[])   new Object[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < n; i++)
            tempk[i] = keys[i];
        for (int i = 0; i < n; i++)
            tempv[i] = values[i];
        keys = tempk;
        values = tempv;
    }


}
