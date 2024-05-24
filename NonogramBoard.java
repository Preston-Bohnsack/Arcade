import java.util.*;

/**
 * Used to start and play a Nonogram.
 * 
 * @author Preston Bohnsack
 * @version Unreleased
 */
public class NonogramBoard extends GenericBoard{
  private final ConsecutiveLine[] rowRuns; // this array stores the runs for each row
  private final ConsecutiveLine[] colRuns; // this array stores the runs for each column

  /**
   * Starts a Nonogram with a specific board.
   * 
   * @param num The number corresponding to which board the player will play on. There are 4 boards so
   * num should be 1-4 (inclusive).
   */
  public static void startGame(int num){
    new NonogramBoard(num);
  }

  /**
   * Starts a Nonogram with a random board.
   */
  public static void startGame(){
   this(((int)(Math.random() * 4)) + 1);
  }
  
  private NonogramBoard(int num){
    { // selects the board to play on
      Cell f = Cell.FILLED;
      Cell e = Cell.UNMARKED;
      if(num == 1){
        solvedBoard = new Cell[][]{ // heart
          {e,f,e,f,e},
          {f,f,f,f,f},
          {f,f,f,f,f},
          {e,f,f,f,e},
          {e,e,f,e,e}
        };  
      }
      else if(num == 2){
        solvedBoard = new Cell[][]{ // plane
          {e,e,f,e,e},
          {e,f,f,f,e},
          {f,f,f,f,f},
          {e,e,f,e,e},
          {e,f,f,f,e}
        };
      }
      else if(num == 3){
        solvedBoard = new Cell[][]{ // skull
          {e,f,f,f,e},
          {f,f,f,f,f},
          {f,e,f,e,f},
          {f,f,f,f,f},
          {e,f,e,f,e}
        };
      }
      else{
        solvedBoard = new Cell[][]{ // smile
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
    rowRuns = new ConsecutiveLine[height];
    colRuns = new ConsecutiveLine[width];

    {
      Cell[][] rowColFlippedBoard = new Cell[height][width];
      for(int row = 0; row < height; row++){
        rowRuns[row] = new ConsecutiveLine(solvedBoard[row]);
        for(int col = 0; col < width; col++){
          board[row][col] = Cell.UNMARKED;
          rowColFlippedBoard[col][row] = solvedBoard[row][col];
          if(row == height - 1){
            colRuns[col] = new ConsecutiveLine(rowColFlippedBoard[col]);
          }
        }
      }
    }
    
    start();
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
    int champ = 0;
    for(ConsecutiveLine lineRun : colRuns){
      if(lineRun.getNumRuns() > champ){
        champ = lineRun.getNumRuns();
      }
    }
    return champ;
  }
  
  private int getMostRowRuns(){
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