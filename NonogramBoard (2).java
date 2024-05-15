import java.util.*;

public class NonogramBoard extends GenericBoard{
  // a class representing the board
  private final ConsecutiveLine[] rowRuns; // this array stores the runs for each row
  private final ConsecutiveLine[] colRuns; // this array stores the runs for each column
  private static NonogramBoard instance; 
  
  public static void startGame(){
    new NonogramBoard();
  }
  
  private NonogramBoard(int num){
    instance = this;
    {
      Cell f = Cell.FILLED;
      Cell e = Cell.UNMARKED;
      if(num == 1){
        solvedBoard = new Cell[][]{
          {e,f,e,f,e},
          {f,f,f,f,f},
          {f,f,f,f,f},
          {e,f,f,f,e},
          {e,e,f,e,e}
        };  
      }
      else if(num == 2){
        solvedBoard = new Cell[][]{
          {e,e,f,e,e},
          {e,f,f,f,e},
          {f,f,f,f,f},
          {e,e,f,e,e},
          {e,f,f,f,e}
        };
      }
      else if(num == 3){
        solvedBoard = new Cell[][]{
          {e,f,f,f,e},
          {f,f,f,f,f},
          {f,e,f,e,f},
          {f,f,f,f,f},
          {e,f,e,f,e}
        };
      }
      else{
        solvedBoard = new Cell[][]{
          {e,f,e,f,e},
          {e,f,e,f,e},
          {f,e,e,e,f},
          {f,f,e,f,f},
          {e,f,f,f,e}
        };
      }
      
      
      
    }

    height = solvedBoard.length;
    width = solvedBoard[0].length;
    board = new Cell[height][width];
    for(Cell[] row : board){
      for(int col = 0; col < width; col++){
        row[col] = Cell.UNMARKED;
      }
    }
    board = solvedBoard;

    rowRuns = new ConsecutiveLine[height];
    for(int row = 0; row < height; row++){
      rowRuns[row] = new ConsecutiveLine(solvedBoard[row]);
    }

    colRuns = new ConsecutiveLine[width];
    for(int col = 0; col < width; col++){
      Cell[] tempCol = new Cell[height];
      for(int row = 0; row < height; row++){
        tempCol[row] = solvedBoard[row][col];
      }
      colRuns[col] = new ConsecutiveLine(tempCol);
    }
    
    start();
    instance = null;
  }

  public static NonogramBoard getInstance(){
    return instance;
  }
  
  protected void print(){
    int rowMargin = getMostRowRuns();
    int colMargin = getMostColRuns();
    for(int row = 0; row < colMargin; row++){
      System.out.print(" ");
      for(int col = 0; col < rowMargin + width; col++){
        if(col < rowMargin){
          System.out.print(" ");
        }
        else{
          ArrayList<Integer> runs = colRuns[col - rowMargin].getRuns();
          if(!(runs.size() < rowMargin - row)){
            System.out.print(runs.get(row - (rowMargin - runs.size())));
          }
          else{
            System.out.print(" ");
          }
        }
      }
      System.out.println();
    }
    
    for(int row = 0; row < height; row++){
      ArrayList<Integer> runs = rowRuns[row].getRuns();
      int spaces = rowMargin - runs.size();
      for(int i = 0; i < spaces; i++){
        System.out.print(" ");
      }
      for(int i = 0; i < runs.size(); i++){
        System.out.print(runs.get(i));
      }
      System.out.print(" ");
      for(int col = 0; col < width; col++){
        System.out.print(board[row][col]);
      }
      System.out.println();
    }
    System.out.println();
  }

  private int getMostColRuns(){
    // gets the most number of runs in a column for margins
    int champ = 0;
    for(ConsecutiveLine lineRun : colRuns){
      if(lineRun.getNumRuns() > champ){
        champ = lineRun.getNumRuns();
      }
    }
    return champ;
  }
  
  private int getMostRowRuns(){
    // gets the most number of runs in a row for margins
    int champ = 0;
    for(ConsecutiveLine lineRun : rowRuns){
      if(lineRun.getNumRuns() > champ){
        champ = lineRun.getNumRuns();
      }
    }
    return champ;
  }

  protected void endingMessage(){
    System.out.println("You Solved The Nonogram!");
  }
}