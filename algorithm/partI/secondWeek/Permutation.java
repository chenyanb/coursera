import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args) {
        int num = Integer.parseInt(args[0]);
        RandomizedQueue<String> arr = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            arr.enqueue(str);
        }
        Iterator<String> iter = arr.iterator();
        while (iter.hasNext()) {
            if (num <= 0) {
                break;
            }
            System.out.println(iter.next());
            num--;
        }

    }

}
