import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

/**
 * 
 * @author Administrator
 *
 *         Corner cases. Throw a java.lang.NullPointerException either the
 *         argument to the constructor is null or if any point in the array is
 *         null. Throw a java.lang.IllegalArgumentException if the argument to
 *         the constructor contains a repeated point.
 */
public class FastCollinearPoints {
    private Point[] points;
  //whether had  got the lineSegment
    private boolean isGetSegment = false;
  //if a line has 5 or greater than 5 points,than can't include the subline. It is the flag.
    private boolean isExit = false;
  //the count of line, which storaged in lineSegment array.
    private int num = 0;
    private LineSegment[] lineSegment;

    public FastCollinearPoints(Point[] points) {
        /**
         * get the copy of the argument in the constructure, 
         * in order to avoid  the constructor argument mutated in outer operations 
         * mustn't keep a reference of the arguments in constructure, 
         * because the argument may mutate.in that case, if you keep a referencr of the argument,
         *  then the operation in this class may make a fuse
         * So keep a copy of the argument firstly.
         */
        this.points = new Point[points.length];
        for (int j = 0; j < points.length; j++) {
            this.points[j] = points[j];
        }
        
        if (this.points == null) {
            throw new java.lang.NullPointerException("the points[] is null.");
        } else {
            Arrays.sort(this.points, 0, this.points.length, this.comparatorPoint());
            for (int i = 0; i <= this.points.length - 2; i++) {
                if (this.points[i] == null) {
                    throw new java.lang.NullPointerException("the points in the array are less than 4.");
                }
                if (this.points[i].compareTo(this.points[i + 1]) == 0) {
                    throw new java.lang.IllegalArgumentException(
                            "the argument to the constructor contains a repeated point");
                }
            }
          //initialize the  count of lines is 10.
            lineSegment = new LineSegment[10];
        }
    }

    private Comparator<Point> comparatorPoint() {
        return new Comparator<Point>() {

            @Override
            public int compare(Point o1, Point o2) {
                return o1.compareTo(o2);
            }

        };
    }

    private void resize(int num) {//resize
        if (num >= lineSegment.length){
            LineSegment[] newArr = new LineSegment[2 * num];
            for (int y = 0; y < this.lineSegment.length; y++) {
                newArr[y] = this.lineSegment[y];
            }
            this.lineSegment = newArr;
        }
    }

    private void sort(int count, int i, int j, int num) {
        Point[] temp = new Point[count];
        temp[0] = points[i];
        int index = 1;
        for (int k = j - count + 1; k < j; k++) {
            temp[index++] = points[k];
        }
        Arrays.sort(temp, 0, temp.length, this.comparatorPoint());

        double slope = temp[0].slopeTo(temp[temp.length - 1]);
        for (int k = i - 1; k >= 0; k--) {
            if (temp[0].slopeTo(this.points[k]) == slope) {
                isExit = true;
                break;
            }
        }
        if (!isExit){
            resize(num);
            lineSegment[num] = new LineSegment(temp[0], temp[temp.length - 1]);
        }

    }

    public int numberOfSegments() {
        if (isGetSegment) {
            return num;
        }else{
            num = 0;
            for (int i = 0; i <= points.length - 4; i++) {
              //sort according to the slope between points[i] and other points
                Arrays.sort(points, i + 1, points.length, points[i].slopeOrder());
                double currentSlope = points[i].slopeTo(points[i + 1]);
             // two points already in the line,points[i] and points[i+1]
                int count = 2;                              
                for (int j = i + 2; j < points.length; j++) {
                    if (currentSlope == points[i].slopeTo(points[j])) {
                        count++;
                        if (j == points.length - 1) {
                            if (count >= 4) {
                                sort(count, i, j + 1, num);
                                if (!isExit) {
                                    num++;
                                }else{
                                    isExit = false;
                                }
                            }
                        }
                    } else {
                        if (count >= 4) {
                            sort(count, i, j, num);
                            if (!isExit) {
                                num++;
                            }else{
                                isExit = false;
                            }
                        }
                        currentSlope = points[i].slopeTo(points[j]);
                        count = 2;
                    }
                }
            }
            lineSegment = Arrays.copyOfRange(lineSegment, 0, num);
            isGetSegment = true;
            return num;
        }

    }

    public LineSegment[] segments() {
        if (!isGetSegment) {
            numberOfSegments();
        }
        LineSegment[] newArr = Arrays.copyOfRange(lineSegment, 0, lineSegment.length);
      /**
       * mustn't return a reference of lineSegment,which will lead to confusion 
       * if users chang the array by the reference you return 
       */      
        return newArr;
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        System.out.println(collinear.segments().length);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}