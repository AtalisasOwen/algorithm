package sort;

import java.util.Random;

/**
 * Created by 顾文涛 on 2017/2/21.
 */
public class QuickSort extends Sort {

    @Override
    public void sort(Comparable[] a) {
        sort(a,0,a.length-1);
    }

    private static void sort(Comparable[]a,int low,int high){
        if(high<=low) return;//可改成插入排序
        int j=partition(a,low,high);
        sort(a,low,j-1);
        sort(a,j+1,high);


    }

    private static int partition(Comparable[]a,int low,int high){
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
