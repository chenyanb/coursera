import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private Percolation test;
    private int num; // the number of trails
    private int dimension;

    private double averPro; // storage the result of mean() function
    private double variance; // storage the result of stddev() function
    private double[] eachPro; // storge each trails result

    private boolean hasMean; // whether has been called mean(),if true(default value is false),than will not call it again
    private boolean isStddev; // whether has been called stddev(),if true(default value is false),than will not call it again

    public PercolationStats(int n, int trials) { // perform trials independent experiments on an n-by-n grid                                                  
        if (n > 0 && trials > 0) {
            dimension = n;
            num = trials;
            eachPro = new double[num];
        } else {
            throw new IllegalArgumentException("parameter is wrong. ");
        }
    }

    public double mean() { // sample mean of percolation threshold
        if (hasMean) {
            return averPro;
        } else {
            double pro = 0;
            averPro = 0; // Probability , average Probability
            hasMean = true;

            for (int i = num; i > 0; i--) {
                test = new Percolation(dimension); // Prepare for next trails
                while (!test.percolates()) {
                    int randomRow = StdRandom.uniform(1, dimension + 1); // randomRow and randomCol is between 1 and n                                                                        
                    int randomCol = StdRandom.uniform(1, dimension + 1);
                    test.open(randomRow, randomCol);
                }
                pro = ((double) test.numberOfOpenSites()) / (dimension * dimension);
                eachPro[i - 1] = pro; // storage each trails probability
            }

            for (int j = 0; j < num; j++) { // computer average probability and return                                          
                averPro += eachPro[j];
            }
            averPro = averPro / num;
            return averPro;
        }

    }

    public double stddev() { // sample standard deviation of percolation threshold                            
        if (isStddev) {
            return variance;
        } else {
            isStddev = true;
            if (!hasMean) { // if averPro is null than get it
                averPro = mean();
            }
            for (int i = 0; i < num; i++) {
                double temp = eachPro[i] - averPro;
                variance += (temp * temp);
            }
            variance = Math.sqrt(variance / (num - 1));
            return variance;
        }

    }

    public double confidenceLo() { // low of 95% confidence interval
        if (!isStddev) {
            variance = stddev();
        }
        double lowBoundary = averPro - 1.96 * variance / (Math.sqrt((double) num));
        return lowBoundary;
    }

    public double confidenceHi() { // high endpoint of 95% confidence interval
        if (!isStddev) {
            variance = stddev();
        }
        double highBoundary = averPro + 1.96 * variance / (Math.sqrt((double) num));
        return highBoundary;
    }

    /**
     * T independent computational experiments (discussed above) on an n-by-n
     * grid,
     * 
     * @param args
     */
    public static void main(String[] args) { // test client (described below)
        int n = StdIn.readInt();
        int t = StdIn.readInt();
        System.out.println("n=  " + n + "  t= " + t);
        PercolationStats percolation = new PercolationStats(n, t);
        System.out.println("mean \t" + percolation.mean());
        System.out.println("stddev \t" + percolation.stddev());
        System.out.println("95% confidence interval \t" + percolation.confidenceLo() + " ," + percolation.confidenceHi());

    }
}