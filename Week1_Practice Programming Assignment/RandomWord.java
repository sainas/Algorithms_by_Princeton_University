import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {

    public static void main(String[] args) {
        // Knuth’s method
        String champion = null;
        double count = 0;
        String next;

        while (!StdIn.isEmpty()) {
            count += 1;
            next = StdIn.readString();
            if (StdRandom.bernoulli(1/count)) {
                champion = next;
            }

        }
        StdOut.println(champion);
    }

}