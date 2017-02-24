package sort;

import java.util.Random;

/**
 * Created by 顾文涛 on 2017/2/21.
 */
public class QuickSort extends Sort {
    //基本的快速排序
    @Override
    public void sort(Comparable[] a) {
        sort(a,0,a.length-1);
    }

    private static void sort(Comparable[]a,int low,int high){
        if(high<=low) return;
        int j=partition(a,low,high);
        sort(a,low,j-1);//递归调用，将左半边排序
        sort(a,j+1,high);//递归调用，将右半边排序


    }

    //切分数组方法，任取一个元素A，使该元素左边的元素都小于A，右边的元素都大与A
    private static int partition(Comparable[]a,int low,int high){
        /*
        取第一个元素v 为切分元素
        i 是指向左边数组的指针，从0 索引开始
        j 是指向右边数组的指针，从最后开始
        i 从左边开始，直到找到一个比v 大的元素，i指针才停止
        然后j 从右边出发，直到找到一个比v 小的元素，交换i 和j 指针指向的元素
        直到i 和j 相遇才停止，然后交换v 元素和j 指向的元素（将v 放到合适的位置）
         */
        int i=low,j=high+1;
        Comparable  v=a[low];
        while(true){
            while(less(a[++i],v)) if(i==high) break;
            while (less(v,a[--j]))if(j==low) break;
            if (i>=j) break;
            exchanged(a,i,j);
        }
        exchanged(a,low,j);
        return j;
    }
}
