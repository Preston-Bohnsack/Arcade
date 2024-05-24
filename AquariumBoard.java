import java.util.*;

/**
 * Used to play the game Aquarium.
 * 
 * @author Preston Bohnsack
 * @version Unreleased
 */
public class AquariumBoard extends GenericBoard{
  private final ThinWall[][] horizontalWalls;
  private final ThinWall[][] verticalWalls;
  private final NumFilledLine[] rowRuns;
  private final NumFilledLine[] colRuns;
  private final AquariumCell[][] board;

  /**
   * Starts a game of Aquarium.
   */

  /*
   * Went with a static method instantiating but not returning an AquariumBoard to prevent memory 
   * leaks by preventing anything from having an alias for it.
   */
  public static void startGame(){
    new AquariumBoard();
  }
  
  private AquariumBoard(){
    {
      Cell a = Cell.FILLED;
      Cell b = Cell.UNMARKED;
      solvedBoard = new Cell[][]{
        {b,b,a,a,a,a},
        {b,b,b,b,b,a},
        {b,b,b,b,a,a},
        {a,a,b,b,b,b},
        {a,a,b,b,b,b},
        {a,b,b,b,b,a}
      };
    
      ThinWall c = ThinWall.FILLED;
      ThinWall d = ThinWall.EMPTY;
      horizontalWalls = new ThinWall[][]{
        {c,c,c,c,c,c},
        {c,d,c,c,c,d},
        {d,d,d,d,c,c},
        {d,d,c,c,c,c},
        {d,d,c,c,c,d},
        {d,c,d,d,d,d},
        {c,c,c,c,c,c}
      };
      // include both the top and bottom line of horizontal walls.
      
      verticalWalls = new ThinWall[][]{
        {c,d,c,d,d,d,c},
        {c,c,d,d,d,c,c},
        {c,c,d,d,c,d,c},
        {c,c,c,d,d,d,c},
        {c,c,c,d,d,c,c},
        {c,c,d,d,d,c,c}
      };
      // include both side walls.
    }

    height = solvedBoard.length;
    width = solvedBoard[0].length;

    board = new AquariumCell[height][width];
    rowRuns = new NumFilledLine[height];
    colRuns = new NumFilledLine[width];
    {
      Cell[][] rowColFlippedBoard = new Cell[height][width];
      for(int row  = 0; row < height; row++){
        rowRuns[row] = new NumFilledLine(solvedBoard[row]);
        for(int col = 0; col < width; col++){
          board[row][col] = new AquariumCell(col, row);
          rowColFlippedBoard[col][row] = solvedBoard[row][col];
          if(row == height - 1){
            colRuns[col] = new NumFilledLine(rowColFlippedBoard[col]);
          }
        }
      }
    }

    AquariumTank.setAquariumBoard(this);
    for(int row = 0; row < height; row++){
      for(int col = 0; col < width; col++){
        new AquariumTank(col, row);
      }
    }

    start();
  }

  public void setAquariumCellsTank(int x, int y, AquariumTank tank){
    board[y][x].setTank(tank);
  }

  public boolean isHWallEmpty(int x, int y){
    return (horizontalWalls[y][x] == null) ? false : horizontalWalls[y][x].isEMPTY();
  }

  public boolean isVWallEmpty(int x, int y){
    return (verticalWalls[y][x] == null) ? false : verticalWalls[y][x].isEMPTY();
  }

  public boolean isValidCoordinate(int x, int y){
    if(x >= 0){
      if(x < width){
        if(y >= 0){
          if(y < height){
            return true;
          }
        }
      }
    }
    return false;
  }

  protected void print(){
    System.out.print(" ");
    for(int col = 0; col < height; col++){
      System.out.print(" " + colRuns[col].getNumFilled());
    }
    System.out.println(" ");

    for(int row = 0; row < width; row++){
      System.out.print(" ");
      for(ThinWall hwall : horizontalWalls[row]){
        String str = hwall.isEMPTY() ? " " : "-";
        System.out.print("+" + str);
      }
      
      System.out.println("+");

      System.out.print(rowRuns[row].getNumFilled());
      for(int col = 0; col < height; col++){
        String str = verticalWalls[row][col].isEMPTY() ? " " : "|";
        System.out.print(str);
        System.out.print(board[row][col]);
      }

      {
        String str = verticalWalls[row][height].isEMPTY() ? " " : "|";
        System.out.println(str);
      }
    }
    
    System.out.print(" ");
    for(ThinWall hwall : horizontalWalls[height - 1]){
      String str = hwall.isEMPTY() ? " " : "-";
      System.out.print("+" + str);
    }
    System.out.println("+");
    System.out.println();
  }
  
  protected void printSolvedBoard(){
    System.out.print(" ");
    for(int col = 0; col < height; col++){
      System.out.print(" " + colRuns[col].getNumFilled());
    }
    System.out.println(" ");

    for(int row = 0; row < width; row++){
      System.out.print(" ");
      for(ThinWall hwall : horizontalWalls[row]){
        String str = hwall.isEMPTY() ? " " : "-";
        System.out.print("+" + str);
      }
      
      System.out.println("+");

      System.out.print(rowRuns[row].getNumFilled());
      for(int col = 0; col < height; col++){
        String str = verticalWalls[row][col].isEMPTY() ? " " : "|";
        System.out.print(str);
        System.out.print((board[row][col].getCell().isFilled()) ? "#" : " ");
      }

      {
        String str = verticalWalls[row][height].isEMPTY() ? " " : "|";
        System.out.println(str);
      }
    }
    
    System.out.print(" ");
    for(ThinWall hwall : horizontalWalls[height - 1]){
      String str = hwall.isEMPTY() ? " " : "-";
      System.out.print("+" + str);
    }
    System.out.println("+");
  }

  protected void endingMessage(){
    System.out.println("You Solved The Aquarium!");
    System.out.println();
  }
  
  protected boolean isFinished(){
    for(int row = 0; row < board.length; row++){
      for(int col = 0; col < board[0].length; col++){
        if(!solvedBoard[row][col].solvedEquals(board[row][col].getCell())){
          return false;
        }
      }
    }
    return true;
  }
  
  protected void userInput(){
    /*
      user can input coordinates in the following format:
      x y empty
      x y cross
      x y fill
    */
    System.out.println("Please type in the coordinates of the point and what you want to change it to.");
    System.out.println("The format is X Y type. Ex. 1 1 fill , 6 6 cross , 2 3 empty");
    
    String str = in.nextLine();
    System.out.println();
    String cellType = "";
    int x = -1;
    int y = -1;
    try{
      cellType = str.substring(4, str.length());
      x = Integer.parseInt(str.substring(0,1)) - 1;
      y = Integer.parseInt(str.substring(2,3)) - 1;
    }
    catch(Exception e){
      System.out.println("Invalid Coordinates. Please type an X and Y coordiante seperated by a single space");
      return;
    }
    
    if(y >= 0 && x >= 0 && y < height && x < width){
      switch (cellType) {
        case "empty":
          board[y][x].setCell(Cell.UNMARKED, true);
          return;
        case "cross":
          board[y][x].setCell(Cell.CROSSED_OUT, true);
          return;
        case "fill":
          board[y][x].setCell(Cell.FILLED, true);
          return;
        default:
          System.out.println("Invalid Cell type. The valid Cell types are empty, cross, and fill");
          return;
      }
    }
    else{
      System.out.println("X should be between 1 and " + width + ", and Y should be between 1 and " + height);
      return;
    }
  }
}