package algorithms;

/**
 * Created by esziger on 2017-11-23.
 */
public class BinarySearch {

    public static void main(String[] args) {
        int [] array = new int[]{1,5,14,123,1511};
        BinarySearch bs = new BinarySearch();
        int searchedItem = 1511;
        int index = bs.binarySearch(array, searchedItem, 0, array.length-1);
        System.out.println(searchedItem + " can be found at index: " + index);
    }

    private int binarySearch(int[] array,int searchedItem, int min, int max) {
        int middleIndex = (min + max)/2;
        int middle = array[middleIndex];

        if(middle < searchedItem){
            return binarySearch(array, searchedItem, middleIndex + 1, max);
        }
        else if(middle > searchedItem){
            return binarySearch(array, searchedItem, min, middleIndex -1);
        }
        else{
            return middleIndex;
        }
    }
}
