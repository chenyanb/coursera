import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

/**
 * BruteCollinearPoints.java that examines 4 points at a time and checks whether
 * they all lie on the same line segment, returning all such line segments. To
 * check whether the 4 points p, q, r, and s are collinear, check whether the
 * three slopes between p and q, between p and r, and between p and s are all
 * equal.
 * 
 * @author Administrator
 *
 *         Corner cases. Throw a java.lang.NullPointerException either the
 *         argument to the constructor is null or if any point in the array is
 *         null. Throw a java.lang.IllegalArgumentException if the argument to
 *         the constructor contains a repeated point.
 */
public class BruteCollinearPoints{
    private boolean isGetSegment = false;//whether had  got the lineSegment
    private Point[] points;
    private LineSegment[] lineSegment;
    private int num = 0;//the count of line, which storaged in lineSegment array.

    public BruteCollinearPoints(Point[] points){
        this.points = new Point[points.length];//avoid the mutate of the argument in the constructure
        for (int j = 0; j < points.length; j++){
            this.points[j] = points[j];
        }
        if (this.points == null) {
            throw new java.lang.NullPointerException("the points[] is null.");
        } else {
          //conventient to check whether exit same points
            Arrays.sort(this.points, 0, this.points.length, comparator());
            for (int i = 0; i <= this.points.length - 2; i++) {
                if (this.points[i] == null) {
                    throw new java.lang.NullPointerException("the points in the array are less than 4.");
                }
                if (this.points[i].compareTo(this.points[i + 1]) == 0) {
                    throw new java.lang.IllegalArgumentException(
                            "the argument to the constructor contains a repeated point");
                }
            }
            lineSegment = new LineSegment[this.points.length];
        }
    }

    private Comparator<Point> comparator() {
        return new Comparator<Point>() {

            @Override
            public int compare(Point o1, Point o2) {
                return o1.compareTo(o2);
            }

        };

    }

    public int numberOfSegments() {
        if (isGetSegment) {
            return num;
        }else{
            num = 0;
            double k1 = 0, k2 = 0, k3 = 0;
            for (int i = 0; i <= points.length - 4; i++) {
                for (int j = i + 1; j <= points.length - 3; j++) {
                    for (int k = j + 1; k <= points.length - 2; k++) {
                        for (int w = k + 1; w <= points.length - 1; w++) {
                            k1 = points[i].slopeTo(points[j]);
                            k2 = points[i].slopeTo(points[k]);
                            k3 = points[i].slopeTo(points[w]);
                            if (k1 != k2) {
                                break;
                            }
                            if ((k1 == k2) && (k2 == k3)) {
                                lineSegment[num++] = new LineSegment(points[i], points[w]);
                                if (num >= lineSegment.length) {
                                    LineSegment[] newArr = new LineSegment[2 * num];
                                    for (int y = 0; y < lineSegment.length; y++) {
                                        newArr[y] = lineSegment[y];
                                    }
                                    lineSegment = newArr;
                                }
                            }
                        }
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
        return newArr;
    }

    public static void main(String[] args){
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++){
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
