/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // private boolean[] grid;
    // private int numOpen;
    // private int length;
    public WeightedQuickUnionUF unionData;
    private boolean[] grid;
    private int gridLength;
    private int openSites;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Value too small (" + n + ")");
        }

        //virtual site values:  n*n and n*n +1
        this.unionData = new WeightedQuickUnionUF(n*n + 2);
        //connect the first row to the virtual point at the top
        //connect the last row to the virtual point at the bottom
        for(int i = 0; i < n; i++) {
            this.unionData.union(i,n* n);
            this.unionData.union(n*n -1 - i, n*n +1);
        }
        this.grid = new boolean[n*n];
        this.openSites = 0;
        this.gridLength = n;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!this.isOpen(row, col)) {
            this.grid[gridLocation(row,col)] = true;
            this.openSites++;
            if(row ==1) {
                //union join square below if open
                if(this.isOpen(row +1, col)){
                    this.unionData.union(gridLocation(row, col), gridLocation(row +1, col));
                }
            }else if(row == gridLength) {
                //union join square above if open
                if(this.isOpen(row -1, col)){
                    this.unionData.union(gridLocation(row, col), gridLocation(row -1, col));
                }
            }else {
                //unionjoin open squares above and below if open
                if(this.isOpen(row -1, col)){
                    this.unionData.union(gridLocation(row, col), gridLocation(row -1, col));
                }
                if(this.isOpen(row +1, col)){
                    this.unionData.union(gridLocation(row, col), gridLocation(row +1, col));
                }
            }

            if(col ==1) {
                //union join square to right if open
                if(this.isOpen(row, col +1)) {
                    this.unionData.union(gridLocation(row, col), gridLocation(row, col +1));
                }
            }else if(col == gridLength) {
                //union join square to the left if open
                if(this.isOpen(row, col -1)) {
                    this.unionData.union(gridLocation(row, col), gridLocation(row, col -1));
                }
            }else {
                //unionjoin open squares to to left and right if open
                if(this.isOpen(row, col -1)){
                    this.unionData.union(gridLocation(row, col), gridLocation(row, col -1));
                }
                if(this.isOpen(row, col +1)) {
                    this.unionData.union(gridLocation(row, col), gridLocation(row, col +1));
                }
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
       return this.grid[gridLocation(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        //check if row and column is connected to the top
        return this.unionData.connected(gridLocation(row, col),gridLength * gridLength);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.openSites;
    }

    // does the system percolate?
    public boolean percolates(){
        return this.unionData.connected(gridLength * gridLength, gridLength*gridLength + 1);
    }

    public int gridLocation(int row, int col){
        return (row - 1) * gridLength + (col -1);
    }

    // test client (optional)
    public static void main(String[] args){
        Percolation percolation = new Percolation(5);
        percolation.open(1,1);
        percolation.open(2,1);
        percolation.open(3,1);
        percolation.open(4,1);
        percolation.open(4,2);
        percolation.open(5,2);
        // percolation.open(5,1);
        if(percolation.percolates()){
            System.out.println("percolates!");
        }else{
            System.out.println("does not percolate");
        }
    }
}