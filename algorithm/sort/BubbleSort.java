package sort;

public class BubbleSort {
    public static void mergeSort(Comparable[] arr) {
        boolean needSort;
        for (int i = 0; i < arr.length - 1; i++) {
            needSort = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    needSort = true;//set the flag whic indicate the array still need another loop
                    Comparable temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!needSort) {// if it is ordered in an inner loop which mean the
                             // needSorted's value is still false after an inner
                            // loop , then the array is already sorted
                break;
            }
        }

    }

    public static void main(String[] args) {
        Double[] arr = new Double[15];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (Math.random()+Math.random())*25;
        }
        BubbleSort.mergeSort(arr);
        for (double j : arr) {
            System.out.println(j);
        }
    }
}
