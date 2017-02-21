package ADT;

import java.util.Iterator;

/**
 * Created by 顾文涛 on 2017/2/21.
 */
public class Queue<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int N;
    private class Node{
        Item item;
        Node next;
    }
    public boolean isEmpty(){
        return N == 0;
    }
    public void enqueue(Item item){
        Node oldlast=last;
        last=new Node();
        last.item=item;
        last.next=null;
        if (isEmpty()) first = last;
        else oldlast.next=last;
        N++;
    }
    public Item dequeue(){
        Item item=first.item;
        first=first.next;
        if (isEmpty()) last=null;
        N--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node current=first;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                Item item=current.item;
                current=current.next;
                return item;
            }
        };
    }
}
