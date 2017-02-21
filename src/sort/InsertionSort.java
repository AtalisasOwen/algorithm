package sort;

/**
 * Created by 顾文涛 on 2017/2/10.
 */
public class InsertionSort extends Sort {
    @Override
    public void sort(Comparable[] a) {
        int n=a.length;
        for(int i=1;i<n;i++){
            for(int j=i;j>0 && less(a[j],a[j-1]);j--) {
                exchanged(a,j,j-1);
            }
        }
    }
}
