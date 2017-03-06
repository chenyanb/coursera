import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
/**
 * that represents a set of points in the unit square.
 * @author Administrator
 *
 */
public class PointSET {
    private SET <Point2D> set;
    
    
    public PointSET() {// construct an empty set of points
        set = new SET<Point2D>();
    }

    public boolean isEmpty() {// is the set empty?
        return set.isEmpty();
    }

    public int size() {// number of points in the set
        return set.size();
    }

    public void insert(Point2D p) {// add the point to the set (if it is not already in the set)
        if(p == null)   throw new NullPointerException();
        set.add(p);
    }

    public boolean contains(Point2D p) {// does the set contain point p?
        if( p == null )   return false;
        return set.contains(p);
    }

    public void draw() {// draw all points to standard draw
        for(Point2D p:set){
            p.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {// all points that are inside the rectangle
        if (rect == null)   throw new NullPointerException();
        
        SET <Point2D> setTemp = new SET<Point2D>();
        for(Point2D p:set){
           if(p.x() >= rect.xmin() && p.x() <= rect.xmax() && p.y() >= rect.ymin() && p.y() <= rect.ymax()){
               setTemp.add(p);
           }
        }
        return setTemp;
    }

    public Point2D nearest(Point2D p) {// a nearest neighbor in the set to point p; null if the set is empty
        Point2D small = null;
        if (set == null)    return null;
        for(Point2D point : set){
            if(small == null){
                small = point;
                continue;
            }
            if( point.distanceTo(p) < small.distanceTo(p) ){
                small = point;
            }         
        }        
        return small;
    }

    public static void main(String[] args) { // unit testing of the methods (optional)
       
    }
}