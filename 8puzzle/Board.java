/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class Board {

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    private int[][] board;

    public Board(int[][] tiles) {
        this.board = tiles;
    }

    // string representation of this board
    public String toString() {

    }

    // board dimension n
    public int dimension() {
        return this.board.length;
    }

    // number of tiles out of place
    public int hamming() {
        int misplacedPieces = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (i == board.length -1 && j == board.length - 1) {
                    return misplacedPieces;
                }
                if (this.board[i][j] !=  ((i+1) + (j * this.board.length))) {
                    misplacedPieces++;
                }
            }
        }
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int manhattanDistance = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if(this.board[i][j] != 0) {
                    manhattanDistance += this.properRowDistance(this.board[i][j], j);
                    manhattanDistance += this.properColDistance(this.board[i][j], i);
                }
            }
        }
        return manhattanDistance;
    }

    private int properRowDistance(int number, int currentRow) {
        int properRow;
        if (number == 0) {
             properRow = this.board.length -1;
        } else {
             properRow = (number - 1) / this.board.length;
        }
        return Math.abs(currentRow - properRow);
    }

    private int properColDistance(int number, int currentCol) {
        int properCol;
        if (number == 0) {
             properCol = this.board.length -1;
        } else {
             properCol = (number - 1) % this.board.length;
        }
        return Math.abs(currentCol - properCol);
    }


    // is this board the goal board?
    public boolean isGoal() {
        if (this.hamming() == 0) {
            return true;
        } else {
            return false;
        }
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (!(y instanceof int[][])) {
            return false;
        }
        if (this.board.length != y.length) {
           return false;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (this.board[i][j] != y[i][j]) {
                   return false;
                }
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        //create a list of all numbers touching the 0 or blank space
        //create a list of of all boards where you switch the 0 with a number touching the blank space

    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {

    }

    // unit testing (not graded)
    public static void main(String[] args) {

    }


}
0