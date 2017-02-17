package sort;

import edu.princeton.cs.algs4.StdRandom;

public class QuickSort {
    public static void sort(Comparable[] arr,int lo,int high){
        // Shuffling is needed for performance guarantee
        StdRandom.shuffle(arr);
        quickSort(arr,lo,high);
    }
    private static boolean less(Comparable i,Comparable j){
        return i.compareTo(j)<0;
    }
    
    private static boolean greator(Comparable i,Comparable j){
        return i.compareTo(j)>0;
    }
    
    private static void exchange(Comparable[]arr,int i,int j){
        Comparable temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    private static void quickSort(Comparable[] arr,int lo,int high){
        if(lo>=high){
            return;
        }
        Comparable v= arr[lo];
        int i=lo+1;
        int lt=lo;
        int gt=high;
        while(i<=gt){
            if(greator(arr[i],v)){
                exchange(arr,i,gt--);
            }else if(less(arr[i],v)){
                exchange(arr,i++,lt++);
            }else{
                i++;
            }
        }
        quickSort(arr,lo,lt-1);
        quickSort(arr,gt+1,high);
    }
    
    public static void main(String[] args){
        Integer[] arr = new Integer[15];
        for(int i=0;i<arr.length;i++){
            arr[i] = (int)((0.5+Math.random())*16);
        }
        QuickSort.sort(arr, 0, arr.length-1);
        for(int i:arr){
            System.out.println(i);
        }
    }
}
