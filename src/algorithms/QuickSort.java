package algorithms;

import java.util.Arrays;

/**
 * Created by esziger on 2017-11-22.
 */
//Runtime average: O(n*log(n)
//Runtime bad case: O(n*n)
public class QuickSort {

    public void quickSort(int [] array, int left, int right){

        if(left < right){
            int pivot = array[(left + right) /2];
            int index = partition(array,left,right,pivot);
            quickSort(array,left,index-1);
            quickSort(array,index, right);
        }
    }

    public int partition(int [] array, int left, int right, int pivot){

        while(left <= right){

            //use compare to for generic elements
            while(array[left] < pivot){
                left++;
            }

            while(array[right] > pivot){
                right--;
            }

            //swap the two elements
            if(left <= right){

                int tmp = array[left];
                array[left] = array[right];
                array[right] = tmp;

                left++;
                right--;
            }
        }

        return left;
    }


    public static void main(String[] args) {

        int [] array = new int[]{4,3,5,2,1};

        final QuickSort qs = new QuickSort();

        qs.quickSort(array, 0, array.length - 1);

        System.out.println("Sorted array:");
        Arrays.stream(array).forEach(element -> System.out.print(element + " "));
    }
}
