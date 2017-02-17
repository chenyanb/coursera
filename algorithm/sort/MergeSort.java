package sort;

public class MergeSort {
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
    private static void merge(Comparable[] arr,Comparable[] aux,int low,int end){//aux can be an empty array 
        /**
         * why put the copy statements here rather out it in the 46th line?
         * it may be the key or it is a hidden bug
         * you need storage the copy when you begin to merge two sorted part£¡£¡£¡£¡
         * Rather than storage the copy in the begining.
         */
        
        for(int j=low;j<=end;j++){
            aux[j] = arr[j];//copy arr to aux when you begin to merge two sorted parts, or you will get the wrong results
        }
        
        
        int mid=low+(end-low)/2;
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
    public static void sort(Comparable[] arr,int low,int end){
        Comparable[] aux = new Comparable[arr.length];
        /**
         * why don't put the copy statements  here
         */
        
//        for(int j=0;j<arr.length;j++){
//            aux[j] = arr[j];//copy arr to aux
//        }
        
        sort(arr,aux,low,end);
    }
    
    private static void sort(Comparable[] arr,Comparable[] aux,int low,int end){
        if(low==end) return;//until it can not be divided, which mean there is only one data in the first or the second part waiting to be sorted 
        int mid = low+(end-low)/2;        
        sort(arr,aux,low,mid);//Recurse,divided the array into two parts until only including one element(can't be divided),then merge.
        sort(arr,aux,mid+1,end);
        MergeSort.merge(arr, aux, low, end);//the first merge is in the two elements ,(low,mid) and (high,end) both include only one element. 
    }
    
    public static void main(String[] args){
        Comparable[] arr = new Double[14];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (Math.random()+Math.random())*90;
        }
        MergeSort.sort(arr, 0, arr.length-1);
        for (int j=0;j<arr.length;j++) {
            System.out.println(arr[j]);
        }
    }
}
