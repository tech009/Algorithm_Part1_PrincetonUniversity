/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        double count = 0.0;
        String word, champion = null;
        while (!StdIn.isEmpty()) {
            word = StdIn.readString();
            count++;
            if (StdRandom.bernoulli(1 / count))
                champion = word;
        }
        System.out.println(champion);
    }
}
