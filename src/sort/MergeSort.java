package sort;

/**
 * Created by 顾文涛 on 2017/2/21.
 *
 */
public class MergeSort extends Sort {
    //TODO:自顶而下的归并排序
    private static Comparable[] aux;

    @Override
    public void sort(Comparable[] a) {
        aux=new Comparable[a.length];
        sort(a,0,a.length-1);
    }

    private static void sort(Comparable[] a,int low,int high){
        /*递归调用顺序，以sort(0,15)为例
        sort(a,0,15)
            sort(a,0,7)
                sort(a,0,3)
                    sort(a,0,1)
                        merge(a,0,0,1)
                    sort(a,2,3)
                        merge(a,2,2,3)
                    merge(a,0,1,3)
                sort(a,4,7)
                    sort(a,4,5)
                        merge(a,4,4,5)
                    sort(a,6,7)
                        merge(a,6,6,7)
                    merge(a,4,5,7)
                merge(a,0,3,7)//左半边完成
            sort(a,8,15)
                sort(a,8,11)
                    sort(a,8,9)
                        merge(a,8,8,9)
                    sort(a,10,11)
                        merge(a,10,10,11)
                    merge(a,8,9,11)
                sort(a,12,15)
                    sort(a,12,13)
                        merge(a,12,12,13)
                    sort(a,14,15)
                        merge(a,14,14,15)
                    merge(a,12,13,15)
                merge(a,8,11,15)
            merge(a,0,7,15)//右半边完成
        merge(a,0,7,15)//归并结果

        可以归并分到最小数组为15 时，用插入排序处理
        这样比归并排序还要快一点
         */


        if(high<=low) return;
        int mid=low+(high-low)/2;
        sort(a,low,mid);
        sort(a,mid+1,high);
        merge(a,low,mid,high);

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
