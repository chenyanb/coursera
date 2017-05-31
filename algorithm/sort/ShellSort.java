package sort;

import java.util.Date;

public class ShellSort {
    public static void shellSort(Comparable[] arr) {
        int h = 1;
        while (h < (arr.length - 1) / 3) {
            h = h * 3 + 1;
        }
        
        while(h>=1){
            for (int i = h; i < arr.length; i++) {
            //System.out.println("h: " + h);
            for (int j = i; j - h >= 0; j -= h) {
                if (arr[j - h].compareTo(arr[j]) > 0) {
                    Comparable temp = arr[j];
                    arr[j] = arr[j - h];
                    arr[j - h] = temp;
                } else {
                    break;
                }
            }
        }
            h = (h-1)/3;
        }
        

    }

    public static void main(String[] args) {
        Double[] arr = new Double[200000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (0.4 + Math.random()) * 39;
        }
        Date start = new Date();
        ShellSort.shellSort(arr);
        Date end = new Date();
        long time = end.getTime() - start.getTime();
        System.out.println("time:  " + time);
//        for (Double data : arr) {
//            System.out.println(data.toString());
//        }
    }
}
