package ADT;

import java.util.Iterator;

/**
 * Created by 顾文涛 on 2017/2/21.
 */
public class ResizingArrayStack<Item> implements Iterable<Item>{
    private Item[] a=(Item[])new Object[1];
    private int N=0;

    public boolean isEmpty(){
        return N==0;
    }

    public int size(){
        return N;
    }

    private void resize(int max){
        Item[] temp=(Item[])new Object[max];
        for (int i=0;i<N;i++){
            temp[i]=a[i];
        }
        a=temp;
    }

    public void push(Item item){
        if(N==a.length) resize(2*a.length);
        a[N++]=item;
    }

    public Item pop(){
        Item item=a[--N];
        a[N]=null;//避免对象游离：取消引用，JVM会回收内存
        if(N>0 && N == a.length/4) resize(a.length/2);
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            @Override
            public boolean hasNext() {
                return N>0;
            }

            @Override
            public Item next() {
                return a[--N];
            }
        };
    }
}
