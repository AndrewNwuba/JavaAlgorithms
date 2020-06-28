import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        if(args.length == 0) {
            throw new IllegalArgumentException("Must input number of strings to print");
        }
        int stringsToPrint;
        stringsToPrint = Integer.valueOf(args[0]);
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            randomizedQueue.enqueue(s);
        }
        for (int i = 0; i < stringsToPrint; i++) {
            StdOut.println(randomizedQueue.dequeue());
        }
    }
}
