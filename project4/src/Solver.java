import dsa.LinkedStack;
import dsa.MinPQ;
import stdlib.In;
import stdlib.StdOut;

// A data type that implements the A* algorithm for solving the 8-puzzle and its generalizations.
public class Solver {
    // instance varialbe # of moves
    int moves;
    // solution
    LinkedStack<Board> solution;

    // Finds a solution to the initial board using the A* algorithm.
    public Solver(Board board) {
        // pq search node
        MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
        // when its empty delete the minimum node
        while (!pq.isEmpty()) {
            SearchNode node = pq.delMin();
        }
    }

    // Returns the minimum number of moves needed to solve the initial board.
    public int moves() {
        // returning the node
        return this.moves;
    }

    // Returns a sequence of boards in a shortest solution of the initial board.
    public Iterable<Board> solution() {
        return solution;
    }

    // A data type that represents a search node in the grame tree. Each node includes a
    // reference to a board, the number of moves to the node from the initial node, and a
    // reference to the previous node.
    private class SearchNode implements Comparable<SearchNode> {
        // The board
        Board board;
        // moves
        int moves;
        // previous node.
        SearchNode previous;

        // Constructs a new search node.
        public SearchNode(Board board, int moves, SearchNode previous) {
            if (board == null) {
                throw new IllegalArgumentException("board is unsolvable");
            }
            this.board = board;
            this.moves = moves;
            this.previous = previous;
        }

        // Returns a comparison of this node and other based on the following sum:
        //   Manhattan distance of the board in the node + the # of moves to the node
        public int compareTo(SearchNode other) {
            return other.previous.compareTo(other.previous);
        }
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
        Board initial = new Board(tiles);
        if (initial.isSolvable()) {
            Solver solver = new Solver(initial);
            StdOut.printf("Solution (%d moves):\n", solver.moves());
            StdOut.println(initial);
            StdOut.println("----------");
            for (Board board : solver.solution()) {
                StdOut.println(board);
                StdOut.println("----------");
            }
        } else {
            StdOut.println("Unsolvable puzzle");
        }
    }
}
