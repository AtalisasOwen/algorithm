package ADT;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by 顾文涛 on 2017/2/21.
 */
public class Stack<Item> implements Iterable<Item> {
    private Node first;
    int N;

    private class Node{
        Item item;
        Node next;
    }

    public boolean isEmpty(){
        return N==0;
    }
    public int size(){
        return N;
    }
    public void push(Item item){
        Node oldFirst=first;
        first=new Node();
        first.item=item;
        first.next=oldFirst;
        N++;
    }
    public Item pop(){
        Item item=first.item;
        first=first.next;
        N--;
        return item;
    }

    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return first.item;
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
