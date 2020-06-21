/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
    private double[] data;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0) {
            throw new IllegalArgumentException("Value too small (" + n + ")");
        }
        if (trials <= 0) {
            throw new IllegalArgumentException("Trials too small (" + trials + ")");
        }
        this.data = new double[trials];
        Percolation percolation;
        for (int i = 0; i < trials; i++) {
            percolation = new Percolation(n);
            while (!percolation.percolates()) {
                percolation.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n +1));
            }
            this.data[i] = (double) percolation.numberOfOpenSites() / (n*n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(data);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(data);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return StdStats.mean(data) - 2* StdStats.stddev(data);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return StdStats.mean(data) + 2* StdStats.stddev(data);
    }


    // test client (see below)
    public static void main(String[] args) {
        PercolationStats stats = new PercolationStats(200, 100);
        System.out.println("Mean: " + stats.mean());
        System.out.println("Standard Deviatian: " + stats.stddev());
        System.out.println("95% confidence interval: [ " + stats.confidenceLo() + ", " + stats.confidenceHi() + "]" );
    }

}

