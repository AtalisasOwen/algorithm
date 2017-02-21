package sort;

/**
 * Created by 顾文涛 on 2017/2/9.
 */
public class SelectionSort extends Sort{

    @Override
    public void sort(Comparable[] a) {
        int n=a.length;
        for(int i=0;i<n;i++){
            int min=i;
            for(int j=i+1;j<n;j++){
                //遍历数组，找出最小元素
                if (less(a[j],a[min])) min=j;
            }
            exchanged(a,i,min);

        }
    }
}
