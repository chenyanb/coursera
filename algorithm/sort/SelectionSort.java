package sort;

import java.util.Date;

public class SelectionSort {
    public static void selectionSort(Comparable[] arr){
        for(int i=0; i<arr.length-1; i++){
            int min = i;
            for(int j=i+1; j<arr.length;j++){
                if(arr[j].compareTo(arr[min])<0){
                    min = j;
                }
            }
            Comparable temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
         
    }
    
    public static void main(String[] args){
        Double[] arr = new Double[20000];
        for(int i=0;i<arr.length;i++){
            arr[i] = (0.5+Math.random())*39;
        }
        Date start = new Date();
        SelectionSort.selectionSort(arr);
        Date end = new Date();
        long time = end.getTime() - start.getTime();
        System.out.println(time);
//        for(Double data : arr){
//            System.out.println(data.toString());
//        }
    }
}
