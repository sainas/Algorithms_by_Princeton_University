import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private static final byte BLOCK = 0;
  private static final byte OPEN = (byte) 0b001;
  private static final byte BOTTOM = (byte) 0b011;  // connect to bottom, open, not full
  private static final byte FULL = (byte) 0b111;
  private final byte[] grid;
  private final int gridSize;
  private int numberOfOpenSites;
  private final WeightedQuickUnionUF ufFull;
  private final int[] moves = {0, 1, 0, -1, 1, 0, -1, 0};
  private boolean isPercolated;


  // in join keep track of bottom
  // if bottom connect to full

  // creates n-by-n grid, with all sites initially blocked
  public Percolation(int n) {
    /* Check if a grid is percolated.*/
    if (n > 0) {
      this.grid = new byte[n * n];
      this.gridSize = n;
      this.numberOfOpenSites = 0;
      this.ufFull = new WeightedQuickUnionUF(n * n);
      this.isPercolated = false;


    } else {
      throw new IllegalArgumentException("n must be positive");
    }
  }
  // opens the site (row, col) if it is not open already

  public void open(int row, int col) {

    if (!this.isOpen(row, col)) {
      int index = this.index(row, col);
      this.grid[index] = OPEN;
      if (row == this.gridSize) {
        this.grid[index] = BOTTOM;
      }
      if (row == 1) {
        if (this.grid[index] == BOTTOM) {
          this.isPercolated = true;
        }
        this.grid[index] = FULL;
      }

      this.numberOfOpenSites++;

      for (int i = 0; i < this.moves.length; i += 2) {
        int x = row + this.moves[i];
        int y = col + this.moves[i + 1];
        if (!this.validSite(x, y)) {
          continue;
        }
        int node = this.index(x, y);
        if (this.grid[node] == BLOCK) {
          continue;
        }
        int p = this.ufFull.find(node);


        if (!this.isPercolated) {
          if (this.grid[p] + this.grid[index] == BOTTOM + FULL) {
            this.isPercolated = true;
          }
        }

        this.grid[index] |=  this.grid[p];

        this.ufFull.union(p, index);

      }
      int finalParent = this.ufFull.find(index);
      this.grid[finalParent] = this.grid[index];
    }
  }

  // is the site (row, col) open?
  public boolean isOpen(int row, int col) {
    if (validSite(row, col)) {
      return this.grid[this.index(row, col)] > 0;
    } else {
      throw new IllegalArgumentException("Out of range");
    }
  }

  // is the site (row, col) full?
  public boolean isFull(int row, int col) {
    if (!isOpen(row, col)) {
      return false;
    }
    int index = this.index(row, col);
    if (this.grid[index] == FULL) {
      return true;
    }
    int p = this.ufFull.find(index);
    if (this.grid[p] == FULL) {
      this.grid[index] = FULL;
      return true;
    }
    return false;
  }

  // returns the number of open sites
  public int numberOfOpenSites() {
    return this.numberOfOpenSites;
  }

  // does the system percolate?
  public boolean percolates() {
    return this.isPercolated;
  }

  private boolean validSite(int row, int col) {
    return (row >= 1 && row <= this.gridSize && col >= 1 && col <= this.gridSize);
  }

  private int index(int row, int col) {
    return (row - 1) * this.gridSize + col - 1;
  }

  // test client (optional)
  public static void main(String[] args) {
    StdOut.println("Hello World");
    Percolation pc = new Percolation(3);
    pc.open(1, 3);
    pc.open(2, 3);
    pc.open(3, 3);
    pc.open(3, 1);
    StdOut.println(pc.percolates());
    StdOut.println(pc.isFull(3, 3));
    StdOut.println(pc.isFull(3, 1));
    StdOut.println("--------------");
    pc = new Percolation(2);
    pc.open(1, 1);
    pc.open(2, 2);
    StdOut.println(pc.percolates());
    StdOut.println(pc.isFull(2, 2));
    pc.open(2, 1);
    StdOut.println(pc.isFull(2, 2));
    StdOut.println(pc.percolates());
    StdOut.println(pc.isFull(1, 2));
  }
}