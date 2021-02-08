/* *****************************************************************************
 *  Last modified:     8/2/2021
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private Percolation pr;
    private double[] fr;
    private int expCount;

    public PercolationStats(int N, int T) {

        if (N < 0 || T < 0)
            throw new IllegalArgumentException("Either N or T is given a value less than 0.");

        expCount = T;
        fr = new double[expCount];

        for (int expNum = 0; expNum < expCount; expNum++) {
            pr = new Percolation(N);
            int sitesopen = 0;
            while (!pr.percolates()) {
                int i = StdRandom.uniform(1, N + 1);
                int j = StdRandom.uniform(1, N + 1);
                if (!pr.isOpen(i, j)) {
                    pr.open(i, j);
                    sitesopen++;
                }
            }
            double fraction = (double) sitesopen / (N * N);
            fr[expNum] = fraction;
            pr = null;
        }

    }

    public double mean() {
        return StdStats.mean(fr);
    }

    public double stddev() {
        return StdStats.stddev(fr);
    }

    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(expCount));
    }

    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(expCount));
    }

    public static void main(String[] args) {

        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(N, T);

        String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + confidence);
    }
}
