package ADT;

import java.util.Iterator;

/**
 * Created by 顾文涛 on 2017/2/21.
 */
public class Bag<Item> implements Iterable<Item> {
    private Node first;
    private class Node{
        Item item;
        Node next;
    }
    public void add(Item item){
        Node oldFirst=first;
        first=new Node();
        first.item=item;
        first.next=oldFirst;
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
