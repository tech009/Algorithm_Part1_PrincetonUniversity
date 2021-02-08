/* *****************************************************************************
 *  Last modified:     7/2/2021
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int n = 0;
    private int top = 0;
    private int bottom;
    private boolean[][] grid;
    private WeightedQuickUnionUF qf;
    private int openSites = 0;

    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();
        this.n = n;
        grid = new boolean[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = false;
        bottom = (n * n) + 1;
        qf = new WeightedQuickUnionUF((n * n) + 2);
    }

    public void open(int row, int col) {
        if (row < 0 || row > n)
            throw new IllegalArgumentException();
        if (col < 0 || col > n)
            throw new IllegalArgumentException();

        grid[row - 1][col - 1] = true;

        if (row == 1) qf.union(getQFIndex(row, col), top);
        if (row == n) qf.union(getQFIndex(row, col), bottom);

        if (row > 1 && isOpen(row - 1, col))
            qf.union(getQFIndex(row, col), getQFIndex(row - 1, col));
        if (row < n && isOpen(row + 1, col))
            qf.union(getQFIndex(row, col), getQFIndex(row + 1, col));

        if (col > 1 && isOpen(row, col - 1))
            qf.union(getQFIndex(row, col), getQFIndex(row, col - 1));
        if (col < n && isOpen(row, col + 1))
            qf.union(getQFIndex(row, col), getQFIndex(row, col + 1));

        openSites++;
    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || row > n)
            throw new IllegalArgumentException();
        if (col < 0 || col > n)
            throw new IllegalArgumentException();

        return grid[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        if (isOpen(row, col))
            if (qf.connected(top, getQFIndex(row, col)))
                return true;
        return false;
    }

    public int numberOfOpenSites() {
        return openSites;
    }


    public boolean percolates() {
        return qf.connected(top, bottom);
    }

    private int getQFIndex(int i, int j) {
        return ((n * (i - 1)) + j);
    }
}
