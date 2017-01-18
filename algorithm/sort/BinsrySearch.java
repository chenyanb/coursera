
public class BinsrySearch {
    public static int binarySearch(int[] arr,int obj){
        int first = 0;
        int last = arr.length-1;
        
        while (last >= first){
            int mid = (first + last)/2;//or first + (last - first)/2
            
            if(arr[first] == obj){//add this module maybe be more faster
                return first;
            }else if (arr[last] == obj){
                return last;
            }
            
            if(obj > arr[mid]){
                first = mid+1;
            }else if(obj < arr[mid]){
                last = mid -1;
            }else{
                return obj;
            }            
        }
        return -1;//first>last;
        
    }
}
