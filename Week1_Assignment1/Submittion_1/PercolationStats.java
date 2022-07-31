import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("wrong");
        }
        double[] result = new double[trials];
        for (int i = 0; i < trials; i++) {
            result[i] = trial(n);
        }
        mean = StdStats.mean(result);
        stddev = StdStats.stddev(result);
        double tmp = 1.96 * stddev / Math.sqrt(trials);
        confidenceLo = mean - tmp;
        confidenceHi = mean + tmp;
    }

    private  double trial(int n) {
        Percolation pc = new Percolation(n);
        int[] a = new int[n*n];
        for (int i = 0; i < n * n; i++) {
            a[i] = i;
        }
        StdRandom.shuffle(a);
        for (int i = 0; i < n * n; i++) {
            int row = a[i] / n + 1;
            int col = a[i] % n + 1;
            pc.open(row, col);
            if (pc.percolates()) {
                break;
            }
        }
        return (double) pc.numberOfOpenSites() / (n * n);
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return confidenceHi;
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats pcs = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        StdOut.println("mean                    = " + pcs.mean());
        StdOut.println("stddev                  = " + pcs.stddev());
        StdOut.println("95% confidence interval = [" + pcs.confidenceLo() + ", " + pcs.confidenceHi() + "]");
    }

}