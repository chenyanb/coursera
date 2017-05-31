import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Node<Item> first, last;

    public RandomizedQueue() {// construct an empty randomized queue

    }

    public boolean isEmpty() {// is the queue empty?
        return size == 0;
    }

    public int size() {// return the number of items on the queue
        return size;
    }

    public void enqueue(Item item) {// add the item from the head of the link
        if (item == null) {
            throw new java.lang.NullPointerException("can not add a null item");
        } else {
            if (size == 0) {
                // Node<Item> newNode = new Node<Item>(item, null, null);
                // first = newNode;
                // last = newNode;
                first = new Node<Item>(item, null, null);
                last = first;
            } else {
                Node<Item> newNode = new Node<Item>(item, first, null);
                first.pre = newNode;
                first = newNode;
            }
            size++;
        }

    }

    public Item dequeue() {// remove and return a random item
        if (size == 0) {
            throw new java.util.NoSuchElementException("can not remove a random from a null queue");
        } else {
            int index = StdRandom.uniform(0, size);// return an integer between
                                                   // 0 and size-1
            Node<Item> delNode = getDesNode(index);
            Item data = delNode.data;
            if (index == 0) {// delete the first node
                if (size != 1) {// if only one element left, than set
                                // first = null is ok.
                    first = delNode.next;
                    first.pre = null;
                    delNode.next = null;
                    delNode.data = null;// first has no pre
                } else {
                    first = null;
                    last = null;
                }
            } else if (index == size - 1) {// delete the last node
                last = delNode.pre;
                last.next = null;
                delNode.pre = null;
                delNode.data = null;// the last node has no next;
            } else {// delete the middle node
                delNode.pre.next = delNode.next;
                delNode.next.pre = delNode.pre;
                delNode.pre = null;
                delNode.next = null;
                delNode.data = null;
            }
            size--;
            return data;
        }

    }

    private Node<Item> getDesNode(int i) {
        if (i <= size / 2) {
            Node<Item> temp = first;
            for (; i > 0; i--) {
                temp = temp.next;
            }
            return temp;
        } else {
            Node<Item> temp = last;
            for (int j = 0; j < size - 1 - i; j++) {
                temp = temp.pre;
            }
            return temp;
        }

    }

    public Item sample() {// return (but do not remove) a random item
        if (size == 0) {
            throw new java.util.NoSuchElementException("can not remove a random from a null queue");
        } else {
            int index = StdRandom.uniform(0, size);// return an integer between
                                                   // 0 and size-1
            return getDesNode(index).data;
        }

    }

    public Iterator<Item> iterator() {// return an independent iterator over
                                      // items in random order
        return new Iterator<Item>() {
            boolean[] random = new boolean[size];
            int i = 0;

            @Override
            public boolean hasNext() {
                if (i < size) {
                    return true;
                }
                return false;
            }

            @Override
            public Item next() {
                if (size == 0 || i >= size) {
                    throw new java.util.NoSuchElementException("no next element");
                } else {
                    int index = StdRandom.uniform(0, size);// return an integer
                                                           // between 0 and
                                                           // size-1
                    if (!random[index]) {
                        i++;
                        random[index] = true;
                        return getDesNode(index).data;
                    } else {
                        return (next());
                    }
                }

            }

            public void remove() {
                throw new java.lang.UnsupportedOperationException("not support remove function");
            }

        };

    }

    private class Node<T> {
        private T data;
        private Node<T> next;
        private Node<T> pre;

        private Node(T data, Node<T> next, Node<T> pre) {
            this.data = data;
            this.next = next;
            this.pre = pre;
        }
    }

    public static void main(String[] args) {// unit testing (optional)
        // RandomizedQueue<String> que = new RandomizedQueue<>();
        // que.enqueue("chen");
        // que.enqueue("yan");
        // que.enqueue("bin");
        // que.enqueue("2016");
        // que.enqueue("coursera");
        // Iterator<String> iter = que.iterator();
        // que.dequeue();
        // que.dequeue();
        // que.dequeue();
        // que.dequeue();
        // que.dequeue();
        // System.out.println("1111111111111");
        // while (iter.hasNext()) {
        // System.out.println(iter.next());
        // }
    }
}