package dataStrucrure;

import java.util.Arrays;
import java.util.Iterator;

/**
 * PriorityQueue: always delete the max or the smallest element
 * using binary heap to implement priority queue
 * 
 * the performance of all operations
 * 
 * insert  delMax  getMax
 * logN    logN    logN
 * @author 12475
 *
 */
public class PriorityQueue<key extends Comparable<key>> {
    private key[] arr;
    private final int initSize=5;
    private int size=0;//the number of elements in the tree
    
    //Constructor
    public PriorityQueue() {
        arr=(key[])new Comparable[initSize];
    }
    public PriorityQueue(key[] a){
      //storage the data from arr[1],arr[0] is not used
        arr=(key[])new Comparable[a.length+1];
        for(int i=0;i<a.length;i++){          
            arr[i+1]=a[i];
        }       
        size=a.length;
        sortInHeapOrder();//sort the tree in heap order
    }
    public PriorityQueue(int capacity){
      //storage the data from arr[1],arr[0] is not used
        arr=(key[])new Comparable[capacity+1];
    }
    
    private boolean less(key a,key b){
        return (a.compareTo(b)<0);
    }
    
    public void exchange(int k1,int k2){
        key temp = arr[k1];
        arr[k1] = arr[k2];
        arr[k2] = temp;
    }
    
    /**
     * when the parent node is less than one or both child,then sink the parentNode, make the tree in heap order
     * @param parentNode
     * @param size
     */
    private void sink(int parentNode){
        if(parentNode>size){
            throw new NullPointerException("the parameter is out of boundary");
        }
        if(parentNode>size/2){
          //when the node has no child,the node is always in heap order
            return;
        }else{
            //arr[k] has one child at least
            while(parentNode<=size/2){
                int leftChild=2*parentNode;
                int rightChild=leftChild+1;           
                if(rightChild<=size){
                    //the node has two children,both left and right child
                    if(less(arr[leftChild],arr[parentNode]) && less(arr[rightChild],arr[parentNode])){
                        //the parentNode is greater than both child,then it is in heap order already
                        break;
                    }
                    
                    if(less(arr[leftChild],arr[rightChild])){
                        //the right child is greater than the left child
                        if(less(arr[parentNode],arr[rightChild])){
                            //the parent node is less than the greater child(the left child)
                            exchange(parentNode,rightChild);
                            parentNode=rightChild;
                        }
                    }else{
                        //the left child is greater than the right child
                        if(less(arr[parentNode],arr[leftChild])){
                            //the parent node is less than the greater child(the left child)
                            exchange(parentNode,leftChild);
                            parentNode=leftChild;
                        }
                    }
                }else{
                    //the node only has left child,only occur in the bottom level
                    if(less(arr[parentNode],arr[leftChild])){
                        //the parent node is less than his only (left) child only happen in the bottom line
                        exchange(parentNode,leftChild);
                        parentNode=leftChild;
                    }else{
                      //the parent node is greater than his only (left) child only happen in the bottom line
                        break;
                    }
                }
            }
            
        }
        
    }
    
    /**
     * when insert a child node in the end of the tree,than make the insert node in place 
     * @param k:the node's index in the array,the node you insert recently
     * @param size:the size of the tree
     */
//    private void swim(int cursor){
//        if(cursor>size){
//            throw new NullPointerException("the index is out of boundary");
//        }
//        int parentNode = cursor/2;
//        while(parentNode>=1){           
//            if(less(arr[parentNode],arr[cursor])){
//                //the value of insert node is greater than his parentNode's value
//                exchange(parentNode,cursor);               
//                cursor = parentNode;
//                parentNode=cursor/2;            
//            }else{
//                break;
//            }            
//        }        
//    }
    
    //more concise implementation
    private void swim(int cursor){
        while(cursor>1 && less(arr[cursor/2],arr[cursor])){
            exchange(cursor/2,cursor);
            cursor = cursor/2;
        }
    }
    
    private void sortInHeapOrder(){
        for(int i=size/2;i>=1;i--){
            sink(i);
        }
    }
    
    private void reSize(){
        key[] newArr = (key[])new Comparable[2*size+1];
        for(int i=1;i<=size;i++){
            newArr[i] = arr[i];
        }
        arr = newArr;
    }
    
    public int getSize(){
        return size;
    }
    
    public void setSize(int size){
        this.size = size;
    }
    
    public void insert(key data){
        if(size+1>=arr.length){
            reSize();
        }
        arr[++size] = data;
        //put the element in the end of the tree in place
        swim(size);
    }
    
    public key getMax(){
        return arr[1];
    }
    
    public key delMax(){
        if(size==0){
            throw new NullPointerException("can not delete element from an empty object");
        }
        key temp = arr[1];
        arr[1] = arr[size];      
        arr[size--] = null;
        sink(1);
        return temp;
        
    }
    
    public key[] heapSort(){
        int leng = size;
        while(size>1){
            exchange(1,size--);
            sink(1);
        }
        size = leng;
        return arr;        
    }
    public Iterator<key> iterator(){
        return new Iterator<key>(){
            int cursor = 1;
            @Override
            public boolean hasNext() {
                if(cursor<=PriorityQueue.this.size){
                    return true;
                }else{
                    return false;
                }
                
            }

            @Override
            public key next() {               
                return arr[cursor++];
            }
            
        };
    }
    public static void main(String[] args){
        Integer[] arr = new Integer[15];
        for(int i=0;i<arr.length;i++){
            arr[i] = (int)((1+Math.random())*45);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(arr); 
        pq.heapSort();
        Iterator<Integer> iter = pq.iterator();
        while(iter.hasNext()){
            System.out.println(iter.next());
        }
        
    }
}
