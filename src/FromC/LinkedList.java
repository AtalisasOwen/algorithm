package FromC;

import java.util.NoSuchElementException;

/**
 * Created by 顾文涛 on 2017/3/2.
 * 适合于实现一个栈，因为元素删查插都可以很快
 * 不适合实现一个队列，因为要遍历到最后才能，效率慢，可以加一个last节点来改进
 */
public class LinkedList<Item> {
    private Node first = new Node() ;
    private int length = 0;
    private class Node{
        Item item;
        Node next;
    }

    //          *获取一个元素*
    /* +声明一个节点p指向链表的第一个节点，初始化j从1开始
    *  +当j<i时，就遍历链表，让p的指针向后移动，不断指向下一个节点，j+1
    *  +若链表末尾p为空，则说明第i个元素不存在
    *  +否则查找成功，返回节点p的数据
     */
    public Item get(int i){
        if (i>length) throw     //不能获取超过链表长度的元素，否则报错
                new NoSuchElementException("index error, you can get number under "+length);
        Node p = first;         //获取首节点
        for(int j=1;j< i + 1;j++)  //向后遍历，直到找到目标，注意这里：因为有首节点，所以 i+1
            p = p.next;
        return p.item;          //返回节点的数据
    }

    //              *插入元素到链表*
    /*
    *   +申明一节点p 指向链表的第一个节点，初始化j 从1 开始
    *   +当j < i 时，就遍历链表，让p 的指针向后移动，不断指向下一节点，j++
    *   +若到链表末尾p 为空，则说明第i 个元素不存在
    *   +否则查找成功，在系统生成一个空节点temp
    *   +将数据Item赋给temp.data
    *   +标准插入语句:
    *       temp.next = p.next;
    *       p.next = temp;
     */

    public boolean insertFirst(Item item){
        return insert(item,1);
    }
    public boolean insertLast(Item item){
        return insert(item,size()+1);
    }
    public boolean insert(Item item,int i){
        if( i>length+1 ) throw          //判断位置是否合理，1 <= i <= length+1
                new NoSuchElementException("index error ,you can insert to "+(length+1));
        Node p = first;                 //获取首节点
        for(int j=1;j<i;j++)  //向后遍历，直到找到目标
            p = p.next;
        Node temp = new Node();         //创建一个新的节点
        temp.item = item;               //将Item 赋给该节点
        temp.next = p.next;             //插入链表1：该节点指向其原后续链表
        p.next = temp;                  //插入链表2：将前节点指向该节点
        length++;
        return true;
    }

    //          *删除链表元素*
    /*  +声明一个节点p，指向首节点
    *   +当j < i 时，不断将指针向后移动
    *   +若到链表底p 为null ，则说明i 元素不存在
    *   +否则查找成功，删除节点，将要删除的节点p.next 赋值给p
    *   +标准删除语句: p.next = q.next
    *   +将q 节点的数据赋值给e ，并返回
    *   +释放 q 节点
     */
    public Item deleteLast(){
        return delete(length);
    }
    public Item deleteFirst(){
        return delete(1);
    }
    public Item delete(int i){
        if (i > length) throw   //判断位置是否合理
                new NoSuchElementException("there is nothing, you can delete the item bofore "+length);
        Node p = first;         //获取首节点
        for(int j=1;j<i;j++)  //向后遍历，直到找到目标
            p = p.next;
        Item temp = p.next.item;  //获取元素的值，因为他即将成为垃圾
        p.next = p.next.next;    //扔掉一个节点
        length--;                 //千万千万别忘了，将链表长度减一
        return temp;
    }

    //获取链表长度
    public int size(){
        return length;
    }

    public static void main(String[] args){

        LinkedList<String> l =new LinkedList<>();
        l.insert("A",1);
        l.insert("B",2);
        l.insert("C",3);
        l.insert("D",4);

        l.insertFirst("E");
        l.insertFirst("F");

        l.deleteLast();
        l.deleteLast();

        for(int i = 1;i<=l.size();i++){
            System.out.print(l.get(i));
        }





    }

}
