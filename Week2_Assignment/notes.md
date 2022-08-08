https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php

## Wrap around
See edu.princeton.cs.algs4.ResizingArrayQueue.java
Means that if one side reaches the end but the array is not full, we could start from the other side, which will not cause trouble

```java
    public void enqueue(Item item) {
        // double size of array if necessary and recopy to front of array
        if (n == q.length) resize(2*q.length);   // double size of array if necessary
        q[last++] = item;                        // add item
        if (last == q.length) last = 0;          // wrap-around
        n++;
    }
```
Just need to be careful when resize, module
```java
    // resize the underlying array
    private void resize(int capacity) {
        assert capacity >= n;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = q[(first + i) % q.length];
        }
        q = copy;
        first = 0;
        last  = n;
    }
```

## Probability
At first in Permutation I dequeue if full and then enqueue
This makes it not uniformly distributed because the last item is guaranteed to add into queue

Thinking of having n = k + 1 items. The last item only has k/(k+1) probability to be in the queue, 
and 1/(k+1) probability we just through it away.

Thinking of having n = k + x items. When we are at the last item into queue, the last item only has k/n probability to be in the queue,
and 1/n probability we just through it away.

So we maintain a count number and after queue is full, the possibility is k / count.

## Choose Array or LinkedList
For DequeArrayNotPass
> Your deque implementation must support each deque operation (including construction) in constant worst-case time.

The resize is a O(N) operation so we can't use array

And besides it has to be double linked list, otherwise the removeLast won't be constant time because you don't know what's before a node.

Simply add prev field to class Node.

For RandomizedQueue

> Your randomized queue implementation must support each randomized queue operation (besides creating an iterator) in constant amortized time.
> Additionally, your iterator implementation must support operations next() and hasNext() in constant worst-case time

The iterator is random, so we can't use linkedlist



