package FromC;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Created by 顾文涛 on 2017/3/3.
 *
 * 写写看循环列表
 */
public class CircularLinkedList<Item>{
    private  Node first;
    private int length = 0;
    private class Node{
        Item item;
        Node next;
    }

    public CircularLinkedList(){
        first = new Node();
        first.next=first;
    }

    public Item get(int i){
        Node p = first;
        for (int j=1;j<i+1;j++){
            p = p.next;
        }
        return p.item;

    }


    public boolean insert(Item item,int i){
        if (i > length+1) throw
            new NoSuchElementException("you can insert item to the index before "+(length+1));
        Node temp = new Node();
        temp.item = item;

        if (first.next == first){
            first.next = temp;
            temp.next = first;
        }else{
            Node p = first;
            for(int j=1;j<i;j++){
                p = p.next;
            }
            temp.next = p.next;
            p.next = temp;

        }


        length++;
        return true;
    }

    public int size(){
        return length;
    }

    public static void main(String[] args){
        CircularLinkedList<String> cl =new CircularLinkedList<>();
        cl.insert("A",1);
        cl.insert("B",2);
        cl.insert("C",3);
        cl.insert("D",4);
        cl.insert("E",2);

        for (int i=1;i<cl.size()+1;i++){
            System.out.print(cl.get(i));
        }

    }

}
