import dsa.Inversions;
import dsa.LinkedQueue;
import stdlib.In;
import stdlib.StdOut;

// A data type to represent a board in the 8-puzzle game or its generalizations.
public class Board {
    // 2d array of the tiles
    int [][] tiles;
    // Board size
    int n;
    // hamming distance
    int hamming;
    // manhattan distance
    int manhattan;
    // position of the blank
    int blankPos;

    // Constructs a board from an n x n array; tiles[i][j] is the tile at row i and column j, with 0
    // denoting the blank tile.
    public Board(int[][] tiles) {
        this.n = tiles.length;
        this.tiles = new int[n][n];
        // counter for blank position


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // copy into this.tiles...
                this.tiles[i][j] = tiles[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // checking the blank pos
                if (tiles[i][j] == 0) {
                    blankPos = n * i + j + 1;
                }
                // checking if the # is out of place
                if (tiles[i][j] != i * n + j + 1 && tiles[i][j] != 0) {
                    this.hamming++;
                    // row and column gap
                    int row = (tiles[i][j] - 1) / n;
                    int column = (tiles[i][j] - 1) % n;
                    // distance for manhattan
                    int dis = Math.abs(i - row) + Math.abs(j - column);
                    this.manhattan += dis;
                }
            }
        }
    }
    // Returns the size of this board.
    public int size() {
        return n;
    }

    // Returns the tile at row i and column j of this board.
    public int tileAt(int i, int j) {
        return tiles[i][j];
    }

    // Returns Hamming distance between this board and the goal board.
    public int hamming() {
        return hamming;
    }

    // Returns the Manhattan distance between this board and the goal board.
    public int manhattan() {
        return manhattan;
    }

    // Returns true if this board is the goal board, and false otherwise.
    public boolean isGoal() {
        return hamming() == 0;
    }

    // Returns true if this board is solvable, and false otherwise.
    public boolean isSolvable() {
        int [] d = new int [n * n];
        int c = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                d[c] = tiles[i][j];
                c++;
            }
        }
        return Inversions.count(d) % 2 == 0;
    }

    // Returns an iterable object containing the neighboring boards of this board.
    public Iterable<Board> neighbors() {
         // clones do it for you so you dont have to loop
        LinkedQueue<Board> q = new LinkedQueue<Board>();
        // neighbors to copy the array
        int[][] neighbors;
        // row
        int i = (blankPos - 1) / n;
        // column
        int j = (blankPos - 1) % n;

        // checking up down left and right :O
        if (i > 0) {
            neighbors = cloneTiles();
            // temp value
            int x = neighbors[i - 1][j];
            // swapping
            neighbors[i - 1][j] = neighbors[i][j];
            neighbors[i][j] = x;
            Board board = new Board(neighbors);
            q.enqueue(board);
        }
        if (j < n - 1) {
            neighbors = cloneTiles();
            int x = neighbors[i][j + 1];
            neighbors[i][j+1] = neighbors[i][j];
            neighbors[i][j] = x;
            Board board = new Board(neighbors);
            q.enqueue(board);
        }
        if (i < n - 1) {
            neighbors = cloneTiles();
            int x = neighbors[i + 1][j];
            neighbors[i + 1][j] = neighbors[i][j];
            neighbors[i][j] = x;
            Board board =  new Board(neighbors);
            q.enqueue(board);
        }
        if (j > 0) {
            neighbors = cloneTiles();
            int x = neighbors[i][j - 1];
            neighbors[i][j - 1] = neighbors[i][j];
            neighbors[i][j] = x;
            Board board = new Board(neighbors);
            q.enqueue(board);
        }
        return q;

    }

    // Returns true if this board is the same as other, and false otherwise.
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != ((Board) other).tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // Returns a string representation of this board.
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2s", tiles[i][j] == 0 ? " " : tiles[i][j]));
                if (j < n - 1) {
                    s.append(" ");
                }
            }
            if (i < n - 1) {
                s.append("\n");
            }
        }
        return s.toString();
    }

    // Returns a defensive copy of tiles[][].
    private int[][] cloneTiles() {
        int[][] clone = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                clone[i][j] = tiles[i][j];
            }
        }
        return clone;
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tiles[i][j] = in.readInt();
            }
        }
        Board board = new Board(tiles);
        StdOut.printf("The board (%d-puzzle):\n%s\n", n, board);
        String f = "Hamming = %d, Manhattan = %d, Goal? %s, Solvable? %s\n";
        StdOut.printf(f, board.hamming(), board.manhattan(), board.isGoal(), board.isSolvable());
        StdOut.println("Neighboring boards:");
        for (Board neighbor : board.neighbors()) {
            StdOut.println(neighbor);
            StdOut.println("----------");
        }
    }
}

// Test tonight