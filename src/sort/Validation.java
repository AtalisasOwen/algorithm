package sort;

/**
 * Created by 顾文涛 on 2017/2/9.
 */
public class Validation {

    public static Integer[] test=
                    {6,99,32,12,34,55, 45,98,149,13,162,
                    23,34,-55,66,17,146516,1617,1612,16,6,
                    -54,63,15,19,161,18919,1616,826,1792,0,
                    1616,156,167,235,1891,1912,19187,19162,
                    168,119,654,65,187,894,323,17,23,-262,
                    75,88,-1,-29,34,117,-16,28918,-256,896};
    public static Character[] reverse={'从','前','有','座'};

    public static void main(String[] args){

        Sort sort=new QuickSort();

        sort.sort(test);
        Sort.isSorted(test);


    }




}
