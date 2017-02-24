package sort;

/**
 * Created by 顾文涛 on 2017/2/21.
 */
public class ReverseMergeSort extends Sort {
    //TODO:自底而上的归并排序
    private static Comparable[] aux;
    @Override
    public void sort(Comparable[] a) {
        int N=a.length;
        aux=new Comparable[N];
        /*循环调用顺序，以sort(0,15)为例
                        sz=1
                        merge(a,0,0,1)
                        merge(a,2,2,3)
                        merge(a,4,4,5)
                        merge(a,6,6,7)
                        merge(a,8,8,9)
                        merge(a,10,10,11)
                        merge(a,12,12,13)
                        merge(a,14,14,15)
                    sz=2
                    merge(a,0,1,3)
                    merge(a,4,5,7)
                    merge(a,8,9,11)
                    merge(a,12,13,15)
                sz=4
                merge(a,0,3,7)
                merge(a,8,11,15)
            sz=8
            merge(a,0,7,15)
         */
        for(int sz=1;sz<N;sz*=2){
            for(int low=0;low<N-sz;low+=sz+sz){
                int mid=low+sz-1;
                int high=Math.min(low+sz+sz-1,N-1);
                merge(a,low,mid,high);
            }
        }
    }

    public static void merge(Comparable[] a,int low,int mid,int high){
        int i=low;
        int j=mid+1;
        for(int k=low;k<=high;k++){
            aux[k]=a[k];
        }
        for(int k=low;k<=high;k++){
           /* 1.左半边用完，则加右半边元素
              1.右半边用完，则加左半边元素
              1.右半边首元素小，则加右半边元素
              1.左半边首元素小，则加左半边元素
              */
            if(i>mid)                       a[k]=aux[j++];
            else if (j>high)               a[k]=aux[i++];
            else if (less(aux[j],aux[i]))  a[k]=aux[j++];
            else                           a[k]=aux[i++];
        }

    }
}
