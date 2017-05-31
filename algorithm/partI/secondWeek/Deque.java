import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private int size;
    private Node<Item> first;
    private Node<Item> last;

    public Deque() {// construct an empty deque

    }

    public boolean isEmpty() {// is the deque empty?
        return size == 0;
    }

    public int size() {// return the number of items on the deque
        return size;
    }

    public void addFirst(Item item) {// add the item to the front
        if (item == null) {
            throw new NullPointerException("can not add a null value");
        } else {
            if (size == 0) {
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

    public void addLast(Item item) {// add the item to the end
        if (item == null) {
            throw new NullPointerException("can not add null value");
        } else {
            if (size == 0) {
                last = new Node<Item>(item, null, null);
                first = last;
            } else {
                Node<Item> newNode = new Node<Item>(item, null, last);
                last.next = newNode;
                last = newNode;
            }
            size++;
        }

    }

    public Item removeFirst() {// remove and return the item from the front
        if (size == 0) {
            throw new java.util.NoSuchElementException("can not remove an element from a null deque");
        } else {
            Item data = first.data;
            if (size == 1) {
                first = null;
                last = null;
            } else {
                first = first.next;
                first.pre.next = null;
                first.pre = null;
            }
            size--;
            return data;
        }

    }

    public Item removeLast() {// remove and return the item from the end
        if (size == 0) {
            throw new java.util.NoSuchElementException("can not remove an element from a null deque");
        } else {
            Item data = last.data;
            if (size == 1) {
                last = null;
                first = null;
            } else {
                last = last.pre;
                last.next.pre = null;
                last.next = null;
            }
            size--;
            return data;
        }

    }

    public Iterator<Item> iterator() {// return an iterator over items in order
                                      // from front to end
        return new Iterator<Item>() {
            private Node<Item> cursor = first;

            @Override
            public boolean hasNext() {
                // TODO Auto-generated method stub
                if (cursor != null) {
                    return true;
                }
                return false;
            }

            @Override
            public Item next() {
                if (cursor == null) {
                    throw new java.util.NoSuchElementException("there is no more elements");
                } else {
                    Item data = cursor.data;
                    cursor = cursor.next;
                    return data;
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

        public Node(T data, Node<T> next, Node<T> pre) {
            this.data = data;
            this.next = next;
            this.pre = pre;
        }
    }

    public static void main(String[] args) {// unit testing (optional)
        Deque<String> que = new Deque<>();
        que.addFirst("chen");
        que.addLast("yan");
        que.removeFirst();
        que.removeLast();
        System.out.println(que.size());
        Iterator<String> iter = que.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}