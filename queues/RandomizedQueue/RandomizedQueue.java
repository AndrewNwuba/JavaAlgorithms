/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

package RandomizedQueue;

/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */



import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static edu.princeton.cs.algs4.StdRandom.shuffle;
import static edu.princeton.cs.algs4.StdRandom.uniform;
import static edu.princeton.cs.algs4.StdRandom.permutation;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] array = (Item[]) new Object[1];
    private int N = 0;
    // construct an empty randomized queue
    public RandomizedQueue() {
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        if (this.N == 0) return true;
        else {
            return false;
        }
    }

    // return the number of items on the randomized queue
    public int size() {
        return this.N;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Error: The item added is null");
        }
        if (this.N ==  this.array.length) {
            this.resize(this.array.length*2);
        }
        this.array[this.N++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (this.N == 0) {
            throw new NoSuchElementException("Error: There is no element to remove!");
        }
        shuffle(this.array,
                0,
                this.N);
        Item removedItem = this.array[--this.N];
        if (this.N > 0 && this.N == this.array.length/4) {
            this.resize(this.array.length/2);
        }
        return removedItem;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        return this.array[uniform(0,N)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private void resize(int max) {
        Item[] temp =  (Item[]) new Object[max];
        for (int i = 0; i < this.N; i++) {
            temp[i] = this.array[i];
        }
        this.array = temp;
    }

    private class RandomIterator implements Iterator<Item>
    {
        private int[] accessOrderArr = permutation(N);
        private int iteration = 0;

        public boolean hasNext() {
            return iteration != accessOrderArr.length -1;
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove is an invalid operation within the iterator.");
        }

        public Item next() {
            Item item = array[accessOrderArr[iteration]];
            if (item == null) {
                throw new NoSuchElementException("Error: No element to return");
            }
            iteration++;
            return item;
        }
    }
    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(5);
        queue.enqueue(12);
        queue.enqueue(3);
        StdOut.println(queue.sample());
        StdOut.println(queue.sample());
        StdOut.println(queue.sample());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());
    }

}

