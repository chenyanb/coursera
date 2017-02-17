package sort;

import edu.princeton.cs.algs4.StdRandom;

public class QuickSortImpro {
    
    public static void quickSort(Comparable[] arr,int lo,int high){
        StdRandom.shuffle(arr);
        sort(arr,lo,high);
    }
    private static boolean less(Comparable[] arr,int i,int j){
        return arr[i].compareTo(arr[j])<0;
    }
    private static void exchange(Comparable[]arr,int i,int j){
        Comparable temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    private static int partition(Comparable[]arr,int lo,int high){
        int i=lo+1;
        while(true){
            while(i<=high && less(arr,i,lo)){
                i++;
            }
            
            while(less(arr,lo,high)){
                high--;
                if(high==lo){
                    break;
                }
            }
            
            if(high<i){
                exchange(arr,lo,high);
                break;
            }else{
                exchange(arr,i++,high--);
            }
            
        }
        return high;
    }
    
    private static void sort(Comparable[] arr,int lo,int high){
        if(lo>=high){
            return;
        }
        int j = partition(arr,lo,high);
        sort(arr,lo,j-1);
        sort(arr,j+1,high);
    }
    
}
