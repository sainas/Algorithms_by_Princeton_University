import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final int[][] STEPS = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private final boolean[][] grid;
    private final int gridSize;
    private final WeightedQuickUnionUF uf;
    private int numberOfOpenSites;
    private final int virtualTop;
    private final int virtualBottom;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("wrong n");
        }
        grid = new boolean[n][n];
        gridSize = n;
        uf = new WeightedQuickUnionUF(n * n + 2);
        virtualTop = n * n;
        virtualBottom = virtualTop + 1;

    }

    private int getId(int row, int col) {
        return (row - 1) * gridSize + col - 1;
    }

    private boolean valid(int i) {
        return i > 0 && i <= gridSize;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!valid(row) || !valid(col)) {
            throw new IllegalArgumentException("wrong n");
        }
        if (grid[row - 1][col - 1]) {
            return;
        }
        grid[row - 1][col - 1] = true;
        numberOfOpenSites += 1;
        for (int[] step : STEPS) {
            int newR = row + step[0];
            int newC = col + step[1];
            if (valid(newR) && valid(newC) && isOpen(newR, newC)) {
                uf.union(getId(row, col), getId(newR, newC));
            }
        }
        if (row == 1) {
            uf.union(getId(row, col), virtualTop);
        }
        if (row == gridSize) {
            uf.union(getId(row, col), virtualBottom);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!valid(row) || !valid(col)) {
            throw new IllegalArgumentException("wrong n");
        }
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!valid(row) || !valid(col)) {
            throw new IllegalArgumentException("wrong n");
        }
        return isOpen(row, col) && uf.find(getId(row, col)) == uf.find(virtualTop);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(virtualTop) == uf.find(virtualBottom);
    }

    // test client (optional)
    public static void main(String[] args) {
//        System.out.println("Hello World");
//        int[][] s = new int[][]{{1, 1}, {1, 2}, {2, 3}, {2, 1}, {3, 2}, {3, 3}, {1, 3}};
//        Percolation pc = new Percolation(3);
//        for (int[] a : s) {
//            System.out.println("open " + java.util.Arrays.toString(a));
//            int i = a[0];
//            int j = a[1];
//            pc.open(i, j);
//            System.out.println(java.util.Arrays.deepToString(pc.grid));
//            System.out.println("isFull " + pc.isFull(i, j));
//            System.out.println("percolates " + pc.percolates());
//        }
    }
}