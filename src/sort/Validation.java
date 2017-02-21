package sort;

/**
 * Created by 顾文涛 on 2017/2/9.
 */
public class Validation {

    public static Integer[] test={6,99,32,12,34,55,23,34,-55,0x233,65,75,88,-1,-29,34};
    public static Character[] reverse={'从','前','有','座'};

    public static void main(String[] args){

        Sort sort=new InsertionSort();

        sort.sort(test);
        Sort.isSorted(test);


    }




}
