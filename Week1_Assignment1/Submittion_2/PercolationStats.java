import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {
  private final double mean;
  private final double stddev;
  private final double delta;

  // perform independent trials on an n-by-n grid
  public PercolationStats(int n, int trials) {
    if (n <= 0 || trials <= 0) {
      throw new IllegalArgumentException("Illegal n or trails");
    }
    double[] results = new double[trials];
    for (int i = 0; i < trials; i++) {
      results[i] = trail(n) / (n * n);
    }
    mean = StdStats.mean(results);
    stddev = StdStats.stddev(results);
    delta = 1.96 * stddev / (Math.sqrt(trials));
  }

  private double trail(int n) {
    Percolation pc = new Percolation(n);
    int row;
    int col;
    while (!pc.percolates()) {
      row = StdRandom.uniform(n);
      col = StdRandom.uniform(n);
      pc.open(row + 1, col + 1);
    }
    return pc.numberOfOpenSites();
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
    return mean - delta;
  }

  // high endpoint of 95% confidence interval
  public double confidenceHi() {
    return mean + delta;
  }

  // test client (see below)
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int trials = Integer.parseInt(args[1]);
    PercolationStats ps = new PercolationStats(n, trials);
    StdOut.println("mean                    =" + ps.mean());
    StdOut.println("stddev                  =" + ps.stddev());
    StdOut.println("95% confidence interval = ["
        + ps.confidenceLo() + ", " + ps.confidenceHi()
        + "]");
  }

}