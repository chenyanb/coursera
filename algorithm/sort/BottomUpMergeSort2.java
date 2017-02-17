package sort;

public class BottomUpMergeSort2 {
    /**
     * the final sort result is in arr array
     * @param arr : the array including datas you want to sort
     * @param aux : the auxiliary array,whose length must greater or equal to the size of arr.
     * the auxiliary array can be an empty array.
     * @param low
     * @param end : the index range in arr you want to sort, from low to end
     * describe:  divide the array into two parts:from low to mid and from high(mid+1) to end
     *            and auxiliary array storage the sorted array
     */
    private static void merge(Comparable[] arr,Comparable[] aux,int low,int mid,int end){//aux can be an empty array 
        /**
         * why put the copy statements here rather out it in the 46th line?
         * it may be the key or it is a hidden bug
         * you need storage the copy when you begin to merge two sorted part��������
         * Rather than storage the copy in the begining.
         */
        
        for(int j=low;j<=end;j++){
            aux[j] = arr[j];//copy arr to aux when you begin to merge two sorted parts, or you will get the wrong results
        }
        
        
        int high = mid +1;
        for(int k=low; k<=end;k++){
            if(low>mid){
                arr[k] = aux[high++];           
            }else if(high>end){
                arr[k] = aux[low++];
            }else {
                if(aux[low].compareTo(aux[high])<=0){
                    arr[k] = aux[low++];
                }else{
                    arr[k] = aux[high++];
                }
            }
        }
        
    }
    /**
     * 
     * @param arr:the array you want to sort from 0 to arr.length - 1
     * @param arrSize: the size of the array
     */
    public static void sort(Comparable[] arr){
        Comparable[] aux = new Comparable[arr.length];
        for(int size=1;size<arr.length;size = size*2)//lgN loops
            for(int lo=0;lo<=arr.length-size;lo += (size+size)){
              int high=Math.min(lo+size+size-1, arr.length-1);
              int mid=lo+size-1;
              System.out.println("low: "+lo+" end: "+high+" mid: "+mid);
              BottomUpMergeSort2.merge(arr, aux, lo,mid,high );
            }
    }
    
    
    public static void main(String[] args){
        Comparable[] arr = new Double[14];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (Math.random()+Math.random())*90;
        }
        BottomUpMergeSort2.sort(arr);
        for (int j=0;j<arr.length;j++) {
            System.out.println(arr[j]);
        }
    }
}
