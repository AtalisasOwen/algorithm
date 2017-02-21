package sort;

/**
 * Created by 顾文涛 on 2017/2/9.
 */
public abstract class Sort {
    public abstract void sort(Comparable[] a);

    protected static boolean less(Comparable v,Comparable w){
        return v.compareTo(w)<0;
    }

    protected static void exchanged(Comparable[] a,int i,int j){
        Comparable t=a[i];
        a[i]=a[j];
        a[j]=t;
    }
    protected static void show(Comparable[] a){
        for(Comparable b:a){
            System.out.print(b+" ");

        }
        System.out.println();
    }

   public static void isSorted(Comparable[] a){
       for (int i=1;i<a.length;i++){
           if(less(a[i],a[i-1])){
               System.out.println("排序失败");
               show(a);
               return;
           }

       }
       System.out.println("排序成功");
       show(a);
   }
}
