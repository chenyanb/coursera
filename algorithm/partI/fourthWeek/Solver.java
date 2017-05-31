import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private SearchNode last;
    
    private boolean isSovable=false,isTwinSovable=false;
    
    private class SearchNode implements Comparable<SearchNode>{
         private Board board;
         private SearchNode privious;        
         private int manhattan;
         private int moves;
         private int priority;
         
         private SearchNode(Board b){
             this.board = b;
             this.manhattan = b.manhattan();
             
             this.privious = null;//No father Node default            
             this.moves = 0;//no move default
             this.priority = this.manhattan;
         }
        @Override
        public int compareTo(SearchNode o) {
            if(this.priority > o.priority){
                return 1;
            }else if(this.priority < o.priority){ 
                return -1;
            }else{
                return 0;
            }
        }
        
    }
    
    private boolean solveStep(MinPQ<SearchNode> minPQ){
        SearchNode delSearchNode = minPQ.delMin();
        
        if(delSearchNode.board.isGoal()){
            last = delSearchNode;
            minPQ = null;
            return true;
        }else{
            Iterable<Board> neighbors = delSearchNode.board.neighbors();
            for(Board board:neighbors){
                if( delSearchNode.privious == null || !(board.equals(delSearchNode.privious.board)) ){
                    SearchNode temp = new SearchNode(board);
                    temp.moves = delSearchNode.moves + 1;
                    temp.priority = temp.manhattan + temp.moves;
                    temp.privious = delSearchNode;
                    minPQ.insert(temp);
                }                                      
            }
        }       
        return false;        
    }
    public Solver(Board initial){// find a solution to the initial board (using the A* algorithm)
        
        if(initial == null)    throw new NullPointerException("the initial board is null.");
        
        MinPQ<SearchNode> minPQ = new MinPQ<SearchNode>();
        MinPQ<SearchNode> minPQ_twin = new MinPQ<SearchNode>();
        
        
        SearchNode start = new SearchNode(initial);
        SearchNode startTwin = new SearchNode(initial.twin());
        
        minPQ.insert(start);
        minPQ_twin.insert(startTwin);
        
        while( !(this.isSovable) && !(this.isTwinSovable) ){
            this.isSovable = this.solveStep(minPQ);
            this.isTwinSovable = this.solveStep(minPQ_twin);
        }
    }
    
    
    public boolean isSolvable(){// is the initial board solvable?       
        return this.isSovable;     
    }
    public int moves(){// min number of moves to solve initial board; -1 if unsolvable
        if(this.isSovable){
            return last.moves; 
        }else{
            return -1;
        }
             
    }
 
    public Iterable<Board> solution(){// sequence of boards in a shortest solution; null if unsolvable
        if(this.isSovable){
            Stack<Board> s = new Stack<Board>();
            SearchNode search = last;
            while( search != null ){
                s.push(search.board);
                search = search.privious;
            }
            return s;
        }else{
            return null;
        }
                
    }
    
    
    public static void main(String[] args){// solve a slider puzzle (given below)
        String c = new String("E:\\8puzzle-testing\\8puzzle\\puzzle2x2-unsolvable1.txt");
        In in = new In(c);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        StdOut.println("moves"+ solver.moves());
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}