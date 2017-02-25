import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

public class Board {
    private char[][] arr;
    private int dimmension;
    private Stack<Board> s;
    private int blankRow,blankColumn;
    private boolean up=false,down=false,right=false,left=false;
    
    private boolean isIterator=false;
    
    public Board(int[][] blocks){   // construct a board from an n-by-n array of blocks
        this.dimmension = blocks.length;
        this.arr=new char[dimmension][dimmension];// (where blocks[i][j] = block in row i, column j)
        for(int i=0;i<this.dimmension;i++)
            for(int j=0;j<this.dimmension;j++){
                this.arr[i][j] = (char)blocks[i][j];
            }        
    }
    
    private Board(char[][] blocks){   // construct a board from an n-by-n array of blocks
        this.dimmension = blocks.length;
        this.arr=new char[dimmension][dimmension];// (where blocks[i][j] = block in row i, column j)
        for(int i=0;i<this.dimmension;i++)
            for(int j=0;j<this.dimmension;j++){
                this.arr[i][j] = blocks[i][j];
            }        
    }
                                           
    public int dimension(){// board dimension n
        return this.dimmension;     
    }
    
    public int hamming() {// number of blocks out of place
        int hamming=0;
        for(int row=0;row<this.dimmension;row++) 
            for(int column=0;column<this.dimmension;column++){
                //when the element is in place,the value of arr[row][column] is: row*dimmension+column+1
                if(row == this.dimmension-1 && column == this.dimmension-1 ) continue;
                if(arr[row][column] != (row*this.dimmension+column+1) ){
                    hamming++;
                }
            }
        return hamming;     
    }
    public int manhattan(){// sum of Manhattan distances between blocks and goal
        int manhattan=0;
        for(int row=0;row<this.dimmension;row++)
            for(int column=0;column<this.dimmension;column++){
                if (arr[row][column] == 0 )   continue;
                if( arr[row][column] != (row*this.dimmension+column+1) ){
                    int tempRow = (this.arr[row][column]-1) / this.dimmension;
                    int tempColumn = ((this.arr[row][column]-1) % this.dimmension);
                    manhattan += ( Math.abs(row-tempRow) + Math.abs(column-tempColumn) );
                }
            }            
        return manhattan;       
    }
    
    public boolean isGoal(){// is this board the goal board?
        for(int row=0;row<this.dimmension;row++)
            for(int column=0;column<this.dimmension;column++){
                if(this.arr[row][column] != (row*this.dimmension+column+1) ){
                    if(row == this.dimmension-1 && column == this.dimmension-1){
                        return(this.arr[row][column]==0);
                    }else{
                        return false;
                    }
                }
            }
        return true;
    }
    
    
    public Board twin(){// a board that is obtained by exchanging any pair of blocks
        Board temp = new Board(this.arr);
        for(int row=0;row<temp.dimmension;row++){
            for(int column=0;column<temp.dimmension-1;){
                if(temp.arr[row][column] == 0 || temp.arr[row][column+1] ==0){
                    break;
                }else{
                    exchange(temp,row,column,row,column+1);                  
                    return temp;
                }
            }
        }     
        return temp;
    }
    public boolean equals(Object y){// does this board equal y?
        if(y == this)   return true;
        
        if(y == null)   return false;
        
        if(y.getClass() != this.getClass()) return false;
        
        Board that = (Board)y;
        
        if(that.dimmension != this.dimmension)  return false;
        
        for(int row=0;row<this.dimmension;row++)
            for(int column=0;column<this.dimmension;column++){
                if(that.arr[row][column] != this.arr[row][column] ){
                    return false;
                }
            }
        return true;    
    }
//////////////////////////////////////////////////////////////////////
    private void exchange(Board b,int i,int j,int row,int column){
        char temp = b.arr[i][j];
        b.arr[i][j] = b.arr[row][column];
        b.arr[row][column] = temp;       
    }
    
    
    private void whichBlank(Board temp){
        for(int row=0;row<temp.dimmension;row++)
            for(int column=0;column<temp.dimmension;column++){
                if(temp.arr[row][column] == 0){
                    temp.blankRow = row;
                    temp.blankColumn = column;
                    return;
                }
            }       
    }
    
    
    
    private void countNeighboring(Board tem,int row,int column){
      
        
        if( tem.blankRow+1 < tem.dimmension  ){
            tem.down=true;
        }
        
        if( tem.blankRow-1 >= 0 ){
            tem.up=true;
        }
        
        if( tem.blankColumn-1 >= 0 ){
            tem.left=true;
        }
        
        if( tem.blankColumn+1 < tem.dimmension ){
            tem.right=true;
        }
    }
////////////////////////////////////////////////////    
    
    public Iterable<Board> neighbors(){// all neighboring boards
        if(isIterator)  return s;
        
        if(s==null){
             whichBlank(this);
             this.countNeighboring(this,this.blankRow,this.blankColumn);
             this.s = new Stack<Board>();
        }      
        
        while(this.up || this.down || this.left || this.right){           
            if(this.up){
                Board temp1 = new Board(this.arr);
                temp1.exchange(temp1,this.blankRow,this.blankColumn,this.blankRow-1,this.blankColumn);
                this.up=false;
                s.push(temp1);
            }else if(this.down){
                Board temp1 = new Board(this.arr);
                temp1.exchange(temp1,this.blankRow,this.blankColumn,this.blankRow+1,this.blankColumn);
                this.down=false;
                s.push(temp1);
            }else if(this.right){
                Board temp1 = new Board(this.arr);
                temp1.exchange(temp1,this.blankRow,this.blankColumn,this.blankRow,this.blankColumn+1);
                this.right=false;
                s.push(temp1);
            }else if(this.left){
                Board temp1 = new Board(this.arr);
                temp1.exchange(temp1,this.blankRow,this.blankColumn,this.blankRow,this.blankColumn-1);
                this.left=false;
                s.push(temp1);
            }
        }  
        isIterator=true;
        return s;
}
    
    public String toString(){// string representation of this board (in the output format specified below)
        StringBuilder s = new StringBuilder();
        s.append(this.dimmension+"\n");
        for(int i=0;i<this.dimmension;i++){
            for(int j=0;j<this.dimmension;j++){
                s.append(" ");
                s.append(String.format("%4d",(int)this.arr[i][j]));       
        }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args){// unit tests (not graded)
        //  C:\Users\12475\Desktop\8puzzle-testing\8puzzle\puzzle3x3-00
        String c = new String("E:\\8puzzle-testing\\8puzzle\\puzzle05.txt");
        In in = new In(c);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        //Iterable<Board> itr = initial.neighbors();
        System.out.print(initial);
//        for(Board board:initial.neighbors()){
//            System.out.print(board);
//        }
//        System.out.println(initial.toString());
//        // solve the puzzle
//        Solver solver = new Solver(initial);
//
//        // print solution to standard output
//        if (!solver.isSolvable())
//            StdOut.println("No solution possible");
//        else {
//            StdOut.println("Minimum number of moves = " + solver.moves());
//            for (Board board : solver.solution())
//                StdOut.println(board);
//        }
    }
}
