/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int numItems;
    private Node first;

    private class Node
    {
        Item item;
        Node next;
    }

    // construct an empty deque
    public Deque() {
        this.numItems = 0;
        this.first = new Node();
        this.first.item = null;
        this.first.next = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        if (this.numItems == 0) {
            return true;
        } else {
            return false;
        }
    }

    // return the number of items on the deque
    public int size() {
        return this.numItems;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("No item was passed in!");
        }
        if (isEmpty()) {
            this.first.item = item;
            this.first.next = null;
        } else {
            Node newFirst = new Node();
            newFirst.item = item;
            newFirst.next = this.first;
            this.first = newFirst;
        }
        this.numItems++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("No item was passed in!");
        }
        if (isEmpty()) {
            this.first.item = item;
            this.first.next = null;
        } else {
            Node newLast = new Node();
            newLast.item = item;
            newLast.next = null;
            Node last = first;
            while (last.next != null) {
                last = last.next;
            }
            last.next = newLast;
        }
        this.numItems++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
           throw new NoSuchElementException("There are no elements to remove.");
        }
        Node oldFirst = first;
        first = oldFirst.next;
        this.numItems--;
        return oldFirst.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("There are no elements to remove.");
        }
        if(numItems == 1) {
            numItems--;
            Node oldFirst;
            oldFirst = first;
            this.first = new Node();
            return oldFirst.item;
        } else if (this.first.next.next == null) {
            numItems--;
            Node oldLast =  this.first.next;
            this.first.next = null;
            return oldLast.item;
        } else {
            numItems--;
            Node secondLast;
            secondLast = this.first;
            while (secondLast.next.next != null) {
                secondLast = secondLast.next;
            }
            Node removed = secondLast.next;
            secondLast.next = null;
            return removed.item;
        }

    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }


    private class ListIterator implements Iterator<Item>
    {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove is an invalid operation within the iterator.");
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            if (current == null) {
                throw new NoSuchElementException("Erro: there is no element to return");
            }
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-"))
                StdOut.print(deque.removeLast());
            else
                deque.addLast(s);
        }
        Deque<String> deque2 = new Deque<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-"))
                StdOut.print(deque2.removeLast());
            else
                deque2.addLast(s);
        }
    }


}
