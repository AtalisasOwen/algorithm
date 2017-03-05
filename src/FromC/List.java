package FromC;

import java.util.NoSuchElementException;

/**
 * Created by 顾文涛 on 2017/3/2.
 * List只是对数组的一种封装
 * 缺点是插入删除需要移动大量元素，难以确定容量
 *
 */
public class List<Item> {
    private final int SIZE = 20;
    private Item[] data = (Item[]) new Object[SIZE];
    private int length = 0;

    //获取元素
    public Item get(int i){
        if (length == 0 || i<1 || i>length) throw new NoSuchElementException("index error");
        return data[i-1];
    }

    //插入元素，默认为表尾

    /* +如果插入的位置不合理，报错
    *  +如果线性表的长度大于等于数组长度，报错或动态增加
    *  +从最后一个元素遍历到第i 个元素，分别将他们向后移动一位
    *  +将要插入的元素填入i位置
    *  +表长加一
     */
    public boolean insert(Item item){
        insert(item,length+1);
        return true;
    }
    public boolean insert(Item item,int i){
        if (length == SIZE) throw new NoSuchElementException("Overflow");
        if (i<1 || i>length+1) throw new NoSuchElementException("index error,you can insert to "+(length+1));
        if (i <= length)
            for (int k = length-1; k>=i-1 ; k--)
                data[k+1] = data[k];

        data[i-1] =item;
        length++;
        return true;
    }

    //删除元素，默认为表尾

    /* +如果删除的位置不合理，则抛出异常
     * +取出删除元素
     * +从删除元素开始遍历到最后一个元素，将他们全部向前移动一位
    *  +表长减1
     */
    public Item delete(){
        return delete(length);
    }
    public Item delete(int i){
        Item result;
        if (length == 0) throw new NoSuchElementException("Empty List");
        if (i<1 || i>length) throw new NoSuchElementException("index error,you can delete the element before "+(length));
        result=data[i-1];
        if (i<length)
            for (int j = i;j<length;j++)
                data[j-1] = data[j];
        length--;
        return result;
    }

    //获取列表长度
    public int size(){
        return length;
    }




    public static void main(String[] args){

        List<String> l =new List<>();
        l.insert("A",1);
        l.insert("B",2);
        l.insert("C",3);
        l.insert("D",4);
        l.insert("E");
        l.insert("F");

        for (int i=0;i<l.size();i++){
            System.out.print(l.get(i+1));
        }

        System.out.println();
        l.delete();

        for (int i=0;i<l.size();i++){
            System.out.print(l.get(i+1));
        }

    }


}
