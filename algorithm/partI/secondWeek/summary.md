#summary
##tips
Actually,it can be more convenient to implement the second task, RandomizedQueue, in array. It is more easy for array to Iterator in random order, you canupset the array firstly, which there is a achieved function and then iterator it in order. As for delete, produce a random index, than exchange arr[index] with arr[size-1] and then delete arr[size-1],which is equal to delete  arr[index] directly as the array is not ask for storaging in order.
    Implete in arrar is more efficient and convenient.

## generic implement in array
```java
public class RandomizedQueue<Item> implements Iterable<Item> {
        private Item[] a;//declare a varible a, it is a reference of an array, the type of the array is Item
        private int n;// number of elements on array

        public RandomizedQueue() {
        a = (Item[]) new Object[2];//the key of implementing array generic, rather than a = new Item[2],which is not allowed
        n = 0;
                                 }
        public void push(Item item){
             ................

             a[n++] = item;//a is equal to the reference of generic array
                    
             .............
                                     }

```

