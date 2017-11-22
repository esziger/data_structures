package algorithms;

import java.util.Arrays;

/**
 * Created by esziger on 2017-11-22.
 */
//O(n*logn) time, always!
//O(n) space - this is the drawback
//It uses a temp array!
public class MergeSort {

    public void mergeSort(int[] array) {
        mergeSort(array,new int[array.length], 0, array.length -1);
    }

    public void mergeSort(int[] array,int [] tmp, int left, int right){

        if(left >=right) {
            return;
        }
        else{
            int halfIndex = (left + right) / 2;

            mergeSort(array, tmp, left, halfIndex);
            mergeSort(array, tmp,  halfIndex +1, right);
            mergeHalves(array,tmp, left, right);
        }

    }
    
    //Walking through each half of the element and copying over the smaller element
    private void mergeHalves(int[] array,int [] tmp, int leftStart, int rightEnd) {

        int leftEnd = (rightEnd + leftStart) /2;
        int rightStart = leftEnd + 1;
        int size = rightEnd - leftStart + 1;

        int left = leftStart;
        int right = rightStart;
        int index = leftStart;

        while(left <= leftEnd && right<= rightEnd){
            if(array[left] <= array[right]){
                tmp[index] = array[left];
                left++;
            }else{
                tmp[index] = array[right];
                right++;
            }
            index++;
        }

        //Copy over the remainder elements
        System.arraycopy(array, left, tmp, index, leftEnd - left +1);
        System.arraycopy(array, right, tmp, index, rightEnd - right +1);
        System.arraycopy(tmp, leftStart, array, leftStart, size);
    }


    public static void main(String[] args) {

        MergeSort mergeSort = new MergeSort();

        int[] array = new int[]{3,7,1,5,2,4};

        mergeSort.mergeSort(array);

        Arrays.stream(array).forEach(e -> System.out.print(e + " "));
    }
}
