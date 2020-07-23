/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Queue;

public class Board {

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    private int[][] board;

    public Board(int[][] tiles) {
        this.board = tiles;
    }

    // string representation of this board
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.board.length + "\n");
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board.length; j++) {
                s.append(String.format("%2d ", board[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
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
                if (i != board.length - 1 && j != board.length - 1) {
                    if (this.board[i][j] != ((i + 1) + (j * this.board.length))) {
                        misplacedPieces++;
                    }
                }
            }
        }
        return misplacedPieces;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int manhattanDistance = 0;
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board.length; j++) {
                if (this.board[i][j] != 0) {
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
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board.length; j++) {
                if (i != this.board.length -1 && j != this.board.length -1) {
                    if (this.board[i][j] != ((i+1) + (j * this.board.length))) {
                        return false;
                    }
                } else {
                    if (this.board[i][j] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) {
            return true;
        }
        if (y == null) {
            return false;
        }
        if (y.getClass() != this.getClass()) {
            return false;
        }
        if (board.length != ((Board) y).board.length) {
           return false;
        }
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board.length; j++) {
                if (this.board[i][j] != ((Board) y).board[i][j]) {
                   return false;
                }
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        int zeroRow = 0;
        int zeroCol = 0;
        Board neighbor;
        Queue<Board> q = new Queue<>();
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board.length; j++) {
                if (this.board[i][j] == 0) {
                    zeroRow = i;
                    zeroCol = j;
                    break;
                }
            }
        }
        if (zeroRow == 0) {
            neighbor = new Board(board);
            swap(neighbor, zeroRow, zeroCol, zeroRow +1, zeroCol);
            q.enqueue(neighbor);
        } else if (zeroRow == this.board.length -1) {
            neighbor = new Board(board);
            swap(neighbor, zeroRow, zeroCol, zeroRow -1, zeroCol);
            q.enqueue(neighbor);
        } else {
            neighbor = new Board(board);
            swap(neighbor, zeroRow, zeroCol, zeroRow +1, zeroCol);
            q.enqueue(neighbor);
            neighbor = new Board(board);
            swap(neighbor, zeroRow, zeroCol, zeroRow -1, zeroCol);
            q.enqueue(neighbor);
        }

        if (zeroCol == 0) {
            neighbor = new Board(board);
            swap(neighbor, zeroRow, zeroCol, zeroRow, zeroCol +1);
            q.enqueue(neighbor);
        } else if (zeroCol == this.board.length -1) {
            neighbor = new Board(board);
            swap(neighbor, zeroRow, zeroCol, zeroRow, zeroCol -1);
            q.enqueue(neighbor);
        } else {
            neighbor = new Board(board);
            swap(neighbor, zeroRow, zeroCol, zeroRow, zeroCol +1);
            q.enqueue(neighbor);
            neighbor = new Board(board);
            swap(neighbor, zeroRow, zeroCol, zeroRow, zeroCol -1);
            q.enqueue(neighbor);
        }
        return q;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        Board twin = new Board(this.board);
        if (this.board[0][0] != 0 && this.board[0][1] != 0) {
            this.swap(twin,
                      0,
                      0,
                      0,
                      1);
        } else {
            this.swap(twin,
                      1,
                      0,
                      1,
                      1);
        }
        return twin;
    }


    private void swap(Board initialBoard, int initialRow, int initialCol, int finalRow, int finalCol) { // exchange two elements in the array
        int temp = initialBoard.board[initialRow][initialCol];
        initialBoard.board[finalRow][finalCol] = initialBoard.board[initialRow][initialCol];
        initialBoard.board[initialRow][initialCol] = temp;
    }

    // unit testing (not graded)
    public static void main(String[] args) {

    }


}
