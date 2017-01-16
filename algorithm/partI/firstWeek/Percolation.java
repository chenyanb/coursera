import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF arrBoth;
    private WeightedQuickUnionUF arrTop;
    private int percolationDimension; // the dimension of percolation
    private int amount; // the number of open sites
    private boolean[] isOpen;
    private boolean isPercolation = false;

    public Percolation(int n) {
        if (n > 0) { // n should biger than 0
            percolationDimension = n; // create n-by-n grid, with all sites blocked                                
            amount = 0;
            arrBoth = new WeightedQuickUnionUF(n * n + 2);
            arrTop = new WeightedQuickUnionUF(n * n + 1);
            isOpen = new boolean[n * n + 1]; // storage the state of each site
        } else {
            throw new IllegalArgumentException("input is illegal.");
        }

    }

    public void open(int row, int col) { // open site (row, col) if it is not open already 
        int index = xyTo1D(row, col);
        if (!(isOpen[index])) { // not open already,than open it
            amount++;
            isOpen[index] = true;
            if (row == 1) {
                arrBoth.union(0, index);
                arrTop.union(0, index);
            }
            if (row == percolationDimension) {
                arrBoth.union(index, percolationDimension * percolationDimension + 1);
            }
            union(row, col); // when open one site, than it should connected to it's opened neighbor 
        }
    }

    private void union(int row, int col) {
        int index = xyTo1D(row, col);
        int n = percolationDimension * percolationDimension;

        if ((index - 1 >= 1) && (index - 1) >= (row - 1) * percolationDimension + 1 && isOpen[index - 1]) {
            arrBoth.union(index, index - 1);
            arrTop.union(index, index - 1);
        }

        if ((index + 1 <= n) && (index + 1) <= row * percolationDimension && isOpen[index + 1]) {
            arrBoth.union(index, index + 1);
            arrTop.union(index, index + 1);
        }

        if ((index + percolationDimension) <= n && isOpen[index + percolationDimension]) {
            arrBoth.union(index, index + percolationDimension);
            arrTop.union(index, index + percolationDimension);
        }

        if ((index - percolationDimension) >= 1 && isOpen[index - percolationDimension]) {
            arrBoth.union(index, index - percolationDimension);
            arrTop.union(index, index - percolationDimension);
        }
    }

    public boolean isOpen(int row, int col) { // is site (row, col) open?
        int index = xyTo1D(row, col);
        return isOpen[index];
    }

    public boolean isFull(int row, int col) { // is site (row, col) full?
        if (isOpen(row, col)) {
            int index = xyTo1D(row, col);
            if (arrTop.connected(index, 0)) {
                if (row == percolationDimension) {
                    isPercolation = true; // if the bottom element is full,than it is perlocation                                     
                }
                return true;
            }
        }
        return false;
    }

    public int numberOfOpenSites() { // number of open sites
        return amount;
    }

    public boolean percolates() { // does the system percolate?
        if (isPercolation) {
            return true;
        } else {
            if (arrBoth.connected(0, percolationDimension * percolationDimension + 1)) {
                return true; // whether virtual top connected to virtual bottom
            }
            return false;
            
        }
    }

    /*
     * sure the index is illegal
     */
    private void validate(int row, int col) {
        if ((row <= 0) || (row > percolationDimension) || (col <= 0) || (col > percolationDimension)) {
            throw new IndexOutOfBoundsException("row or col index is illegal");
        }
    }

    /*
     * transfer (row,col) to the index of 1 dimension array
     */
    private int xyTo1D(int row, int col) {
        validate(row, col);
        return (percolationDimension * (row - 1) + col);
    }

    public static void main(String[] args) { // test client (optional)
        int n = StdIn.readInt();
        Percolation per = new Percolation(n);
        per.open(1, 1);
        System.out.println(per.percolates());
        per = null;//every time you don't need a reference,remember to delete it.
    }

}
