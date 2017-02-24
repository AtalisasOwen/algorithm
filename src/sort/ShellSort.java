package sort;

/**
 * Created by 顾文涛 on 2017/2/21.
 */
public class ShellSort extends Sort {

    @Override
    public void sort(Comparable[] a) {
        int N=a.length;
        int h=1;
        while(h<N/3){
            h=3*h+1;// 1、4、13、40...
        }
        //多次插入排序，以...40,13,4,1为h 子数组排序（跳着h插入排序）
        //数组越大，速度优势越明显
        while(h>=1){

            for(int i=h;i<N;i++){
                for(int j=i;j>=h&&less(a[j],a[j-h]);j-=h){
                    exchanged(a,j,j-h);
                }
            }
            h=h/3;
        }
    }
}

