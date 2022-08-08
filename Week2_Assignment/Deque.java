import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int n;         // number of elements on queue
    private Node first;    // beginning of queue
    private Node last;     // end of queue

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    // construct an empty deque

    public Deque() {
        first = null;
        last  = null;
        n = 0;
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
        Node node = new Node();
        node.item = item;
        node.next = first;
        if (first != null) {
            first.prev = node;
        }
        first = node;
        if (isEmpty()) last = node;
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node node = new Node();
        node.item = item;
        node.prev = last;
        if (last != null) {
            last.next = node;
        }
        last = node;
        if (isEmpty()) first = node;
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        if (first != null) {
            first.prev = null;
        }
        n--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = last.item;
        last = last.prev;
        if (last != null) {
            last.next = null;
        }
        n--;
        if (isEmpty()) first = null;   // to avoid loitering
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new CustomIterator();
    }

    private class CustomIterator implements Iterator<Item> {
        private Node cur = first;

        public boolean hasNext() {
            return cur != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = cur.item;
            cur = cur.next;
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
            if (item.equals("j")) dq.addFirst(i++);
            if (item.equals("k")) dq.addLast(i++);
            if (item.equals("h")) dq.removeFirst();
            if (item.equals("l")) dq.removeLast();
            StdOut.print("\n[ ");
            for (Integer n: dq) {
                StdOut.print(n + ", ");
            }
            StdOut.println();
        }
        StdOut.println("(" + dq.size() + " left on stack)");
    }

}