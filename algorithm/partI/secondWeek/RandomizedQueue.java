import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] arr;
    private int size;

    public RandomizedQueue() {// construct an empty randomized queue
        arr = (Item[]) new Object[1];// is equal to generic array
        this.size = 0;
    }

    private RandomizedQueue(int size) {//this construct is for iterator()
        arr = (Item[]) new Object[size];
        this.size = size;
    }

    public boolean isEmpty() {
        return size == 0;// is the queue empty?
    }

    public int size() {
        return size;// return the number of items on the queue
    }

    /**
     * Here may be an insidious bug, when you execute enlarge arr's capacity
     * "i<this.size()" is equal to "i<arr.length". But when narrow array's
     * capacity, it must be "i<this.size", rather than "i<arr.length"
     * 
     * @param capacity
     */
    private void resize(int capacity) {
        Item[] newArr = (Item[]) new Object[capacity];
        for (int i = 0; i < this.size(); i++) {
            newArr[i] = arr[i];//make clear the difference between this and newArr = arr.Make clear is very important for further study..
        }
        arr = newArr;
    }

    public void enqueue(Item item) {// add the item
        if (item == null) {
            throw new java.lang.NullPointerException();
        } else {
            if (size == arr.length) {
                resize(2 * size);
            }
            arr[size++] = item;
        }
    }

    /**
     * get the random element in the array, than exchange it with the last
     * element,and delete the last element(equal to delete arr[index]. but
     * delete the last element in the array is more convenient.
     * 
     * @return
     */
    public Item dequeue() {// remove and return a random item
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        } else {
            int index = StdRandom.uniform(0, size);

            Item temp = arr[index];
            arr[index] = arr[size - 1];
            // arr[size - 1] = temp;//tedious operation
            arr[--size] = null;//// delete reference so that the garbage collector can recycle thecmemory make full use of memory
            if (size <= arr.length / 4 && (size != 0)) {
                resize(arr.length / 2);
            }
            return temp;
        }
    }

    public Item sample() {// return (but do not remove) a random item
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }else {
            int index = StdRandom.uniform(0, size);
            return arr[index];// return a random element in the array
        }

    }

    /**
     * storage the arr's state, when client create two nested iterators over the
     * same randomized queue,promise two(or more)iterators are independent,
     * iterator still work.
     */
    public Iterator<Item> iterator() {                                     
        return new Iterator<Item>() {
            RandomizedQueue<Item> queue = new RandomizedQueue<Item>(RandomizedQueue.this.size);
            Item[] arr2 = queue.arr;// create a reference
            private int i = 0;// prepare to iterate in order
            @Override
            public boolean hasNext() {
                if (i < queue.size) {
                    return true;
                }
                return false;
            }

            @Override
            public Item next() {
                if (i >= queue.size || queue.size == 0) {
                    throw new java.util.NoSuchElementException();
                } else {
                    if (i == 0) {
                        for (int j = 0; j <queue.size; j++) {
                            arr2[j] = arr[j];// storage the current sate of arr.
                        }
                        StdRandom.shuffle(arr2, 0, queue.size - 1);// rearrange the array and than iterator it in order                       
                    }
                    Item data = arr2[i++];
                    return data;
                }
            }
        };
    }

    public static void main(String[] args) {// unit testing (optional)

    }

}