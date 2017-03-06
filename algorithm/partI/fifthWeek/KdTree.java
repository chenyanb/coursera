import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class KdTree {
    private Node root;
    private int size;

    private class Node {
        Point2D point;
        private int leval;

        private Node right;
        private Node left;

        private Node(Point2D p) {// TODO Auto-generated constructor stub
            this.point = p;
        }

        private Node(Point2D p, int level) {// TODO Auto-generated constructor
                                            // stub
            this.point = p;
            this.leval = level;
        }
    }

    public KdTree() {
        this.size = 0;
        this.root = null;
    }

    public boolean isEmpty() {// is the set empty?
        return (this.size == 0);
    }

    public int size() {// number of points in the tree
        return this.size;
    }

    private void insert(Node node, Node root) {
        if (root.point.equals(node.point)) {
            this.size--;
            return;
        }
        if (root.leval % 2 == 0) {// the level is even. than compare the value
                                  // of x
            if (node.point.x() <= root.point.x()) {
                if (root.left != null) {
                    insert(new Node(node.point), root.left);
                } else {
                    root.left = new Node(node.point, root.leval + 1);
                }
            } else {
                if (root.right != null) {
                    insert(new Node(node.point), root.right);
                } else {
                    root.right = new Node(node.point, root.leval + 1);
                }
            }
        } else {// the level is odd. than compare the value of y
            if (node.point.y() <= root.point.y()) {
                if (root.left != null) {
                    insert(new Node(node.point), root.left);
                } else {
                    root.left = new Node(node.point, root.leval + 1);
                }
            } else {
                if (root.right != null) {
                    insert(new Node(node.point), root.right);
                } else {
                    root.right = new Node(node.point, root.leval + 1);
                }
            }
        }
    }

    public void insert(Point2D p) {// add the point to the tree (if it is not
                                   // already in the set)
        if (p == null)
            throw new NullPointerException();

        if (this.root == null) {
            this.root = new Node(p, 0);
        } else {
            Node node = new Node(p);
            insert(node, this.root);
        }
        this.size++;
    }

    private boolean find(Point2D point, Node root) {
        if (root == null)
            return false;

        if (root.point.equals(point)) {
            return true;
        } else {
            if (root.leval % 2 == 0) {// when level is even,compare the value of
                                      // x
                if (point.x() <= root.point.x()) {
                    return find(point, root.left);
                } else {
                    return find(point, root.right);
                }
            } else {// when level is odd,compare the value of y
                if (point.y() <= root.point.y()) {
                    return find(point, root.left);
                } else {
                    return find(point, root.right);
                }
            }
        }
    }

    public boolean contains(Point2D p) { // does the set contain point p?
        return find(p, this.root);
    }
    
    private void draw(Node node){
        if(node == null )   return;
        node.point.draw();
        draw(node.left);
        draw(node.right);
    }
    
    public void draw() {// draw all points to standard draw  
        draw(this.root);
    }

    private boolean isIN(RectHV rect, Point2D point) {
        return ( isXIn(rect, point) && isYIn(rect, point) );
    }

    private boolean isXIn(RectHV rect, Point2D point) {
        return ( point.x() >= rect.xmin() && point.x() <= rect.xmax() );
    }

    private boolean isYIn(RectHV rect, Point2D point) {
        return ( point.y() >= rect.ymin() && point.y() <= rect.ymax() );
    }

    private void range(SET<Point2D> set, RectHV rect, Node root) {
        if (root == null)
            return;

        if (root.leval % 2 == 0) {// the even level, compare the value of x
            if (isIN(rect, root.point)) {//the node is within the rectangle
                set.add(root.point);
                range(set, rect, root.left);
                range(set, rect, root.right);
            } else if (isXIn(rect, root.point)) {
                range(set, rect, root.left);
                range(set, rect, root.right);
            } else {// the root node is beyond the range of rectangle
                if (rect.xmax() <= root.point.x()) {// the range of rectangle
                    range(set, rect, root.left);   // lies in the left of root                   
                } else if(rect.xmin() >= root.point.x() ){// the range of rectangle lies in the left of root
                    range(set, rect, root.right);
                }
            }

        } else {// the odd level, compare the value of y
            if (isIN(rect, root.point)) {
                set.add(root.point);// rectangle
                range(set, rect, root.left);
                range(set, rect, root.right); // rectangle
            } else if (isYIn(rect, root.point)) {
                range(set, rect, root.left);
                range(set, rect, root.right); // rectangle
            } else {// the root node is beyond the range of rectangle
                if (rect.ymax() <= root.point.y()) {// the range of rectangle
                    range(set, rect, root.left);   // lies in the left of root                   
                } else if(rect.ymin() >= root.point.y() ) {// the range of rectangle lies in the left of root
                    range(set, rect, root.right);
                }
            }

        }

    }

    public Iterable<Point2D> range(RectHV rect) {// all points that are inside
        if (rect == null)
            throw new NullPointerException(); // the rectangle
        SET<Point2D> set = new SET<Point2D>();
        range(set, rect, this.root);
        return set;

    }

    private Point2D searchNear(Point2D point, Node root, Point2D nearest) {

        if (root == null)
            return nearest;

        if (root.point.distanceTo(point) < nearest.distanceTo(point)) {
            nearest = root.point;
        }

        if (root.leval % 2 == 0) {// compare the value of x
            if (point.x() <= root.point.x()) {// go left of root
                nearest = searchNear(point, root.left, nearest);
                if (new Point2D(root.point.x(), point.y()).distanceTo(point) < nearest.distanceTo(point)) {
                    nearest = searchNear(point, root.right, nearest);
                }
            } else {
                nearest = searchNear(point, root.right, nearest);
                if (new Point2D(root.point.x(), point.y()).distanceTo(point) < nearest.distanceTo(point)) {
                    nearest = searchNear(point, root.left, nearest);
                }
            }
        } else {// compare the value of y
            if (point.y() <= root.point.y()) {// go left of root
                nearest = searchNear(point, root.left, nearest);
                if (new Point2D(point.x(), root.point.y()).distanceTo(point) < nearest.distanceTo(point)) {
                    nearest = searchNear(point, root.right, nearest);
                }
            } else {
                nearest = searchNear(point, root.right, nearest);
                if (new Point2D(point.x(), root.point.y()).distanceTo(point) < nearest.distanceTo(point)) {
                    nearest = searchNear(point, root.left, nearest);
                }
            }
        }
        return nearest;

    }

    public Point2D nearest(Point2D p) {// a nearest neighbor in the set to point
                                       // p; null if the set is empty
        if (p == null || this.size == 0)
            return null;

        Point2D nearest = this.root.point;
        return searchNear(p, this.root, nearest);

    }

}
