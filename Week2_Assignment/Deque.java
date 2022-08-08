import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private static final int INIT_CAPACITY = 8;
    private Item[] q;
    private int n;
    private int first;
    private int last;

    // construct an empty deque

    public Deque() {
        this.q = (Item[]) new Object[INIT_CAPACITY];
        this.n = 0;
        this.first = 3;
        this.last = 3;
    }

    private void resize(int capacity) {
        assert capacity >= n;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = q[(first + i) % q.length];
        }
        q = copy;
        first = 0;
        last = n;
    }


    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (n == q.length) {
            resize(2 * q.length);
        }
        first--;
        if (first == -1) first = q.length - 1;
        q[first] = item;
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (n == q.length) {
            resize(2 * q.length);
        }
        q[last++] = item;
        n++;
        if (last == q.length) last = 0;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = q[first];
        q[first] = null;
        first++;
        n--;
        if (q.length > 8 && n > 0 && n == q.length / 4) {
            resize(q.length / 2);
        }
        if (first == q.length) first = 0;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        n--;
        last--;
        if (last == -1) last = q.length - 1;
        Item item = q[last];
        q[last] = null;
        if (q.length > 8 && n > 0 && n == q.length / 4) {
            resize(q.length / 2);
        }
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new CustomIterator();
    }

//    public String hack(){
//        return Arrays.toString(this.q);
//    }

    private class CustomIterator implements Iterator<Item> {
        private int i = 0;

        public boolean hasNext() {
            return i < n;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = q[(i + first) % q.length];
            i++;
            return item;
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> dq = new Deque<>();
        int i = 100;

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            StdOut.println(item);
            if (item.equals("J")) dq.addFirst(i++);
            if (item.equals("K")) dq.addLast(i++);
            if (item.equals("H")) dq.removeFirst();
            if (item.equals("L")) dq.removeLast();
        }
        StdOut.println("(" + dq.size() + " left on stack)");
    }

}