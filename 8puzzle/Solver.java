/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private final MinPQ<SearchNode> priorityQueue = new MinPQ<>(1);
    private final MinPQ<SearchNode> twinPQ = new MinPQ<>(1);

    private class SearchNode implements Comparable<SearchNode> {
        private Board board;
        private SearchNode prev;
        private int numMoves;

        public SearchNode(Board initial, SearchNode previousNode, int moves) {
            numMoves = moves;
            board = initial;
            prev = previousNode;
        }


        public int compareTo(SearchNode searchNode) {
            return this.board.manhattan() - searchNode.board.manhattan();
        }
    }
    private SearchNode goalNode;
    public Solver(Board initial) {
        SearchNode start = new SearchNode(initial, null, 0);
        SearchNode twinStart = new SearchNode(initial.twin(), null, 0);
        // MinPQ<SearchNode> priorityQueue = new MinPQ<>(1);
        // MinPQ<SearchNode> twinPQ = new MinPQ<>(1);
        this.twinPQ.insert(twinStart);
        System.out.println(twinPQ.size());
        this.priorityQueue.insert(start);
        SearchNode minNode = new SearchNode(null, null, 0);
        SearchNode twinMinNode = new SearchNode(null, null, 0);
        while (true) {
            System.out.println("Removed a node");
            minNode = this.priorityQueue.delMin();
            twinMinNode = this.twinPQ.delMin();
            if (minNode.board.manhattan() == 0) {
                this.goalNode = minNode;
                break;
            }
            if (twinMinNode.board.manhattan() == 0) {
                this.goalNode = null;
                break;
            }
            for (Board neighbor: minNode.board.neighbors()) {
                priorityQueue.insert(new SearchNode(neighbor, minNode, (minNode.numMoves) + 1));
            }
            for (Board twinNeighbor: twinMinNode.board.neighbors()) {
                priorityQueue.insert(new SearchNode(twinNeighbor, twinMinNode, 0));
            }
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        if (this.goalNode == null) {
            return false;
        } else {
            return true;
        }
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (goalNode == null) {
            return -1;
        } else {
            return goalNode.numMoves;
        }
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        Queue<Board> solutionOrder;
        if (this.goalNode == null) {
            return null;
        }
        else {
            solutionOrder = new Queue<>();
            SearchNode node = this.goalNode;
            solutionOrder.enqueue(node.board);
            while (node.prev != null) {
                node = node.prev;
                solutionOrder.enqueue(node.board);
            }
        }
        return solutionOrder;
    }

    // test client (see below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
