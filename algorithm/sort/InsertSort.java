package sort;

import java.util.Date;

public class InsertSort {
    public static void insertSort(Comparable[] arr){
        for(int i=0;i<arr.length;i++){           
            for(int j=i;j-1>=0;j--){
                if(arr[j-1].compareTo(arr[j])>0){
                    Comparable temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }else{
                    break;
                }
            }
        }       
    }
    
    public static void main(String[] args){
        Double[] arr = new Double[20000];
        for(int i=0;i<arr.length;i++){
            arr[i] = (0.35+Math.random())*39;
        }
        Date start = new Date();
        InsertSort.insertSort(arr);
        Date end = new Date();
        long time = end.getTime() - start.getTime();
        System.out.println(time);
//        for(Double data : arr){
//            System.out.println(data.toString());
//        }
    }
}
