import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        if (k == 0) return;
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        String next;
        double kf = (double) k;
        double count = 0;
        while (!StdIn.isEmpty()) {
            next = StdIn.readString();
            count++;
            if (rq.size() == k) {
                if (StdRandom.bernoulli(kf / count)) {
                    rq.dequeue();
                    rq.enqueue(next);
                }
            } else {
                rq.enqueue(next);
            }

        }
        for (String s : rq) {
            StdOut.println(s);
        }
    }
}