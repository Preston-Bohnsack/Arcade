import java.util.*;

public class GenericBoard{
  protected Cell[][] solvedBoard;
  protected Cell[][] board;
  protected int height;
  protected int width;
  protected final Scanner in = new Scanner(System.in);;
  
  protected void start(){
    play();
  }
  
  private void play(){
    while(true){
      print();
      userInput();
      if(isFinished()){
        endingMessage();
        printSolvedBoard();
        System.out.println();
        return;
      }
    }
  }

  protected void print(){
    for(Cell[] cellArr : board){
      for(Cell c : cellArr){
        System.out.print(((c == null) ? "-" : c));
      }
      System.out.println();
    }
  }
  
  protected void userInput(){
    /*
      user can input coordinates in the following format
      x y empty
      x y cross
      x y fill
    */
    System.out.println("Please type in the coordinates of the point and what you want to change it to.");
    System.out.println("  X Y Type eg. 1 1 fill or 5 5 cross or 2 3 empty");
    
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
          board[y][x] = Cell.UNMARKED;
          return;
        case "cross":
          board[y][x] = Cell.CROSSED_OUT;
          return;
        case "fill":
          board[y][x] = Cell.FILLED;
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

  protected boolean isFinished(){
    for(int row = 0; row < board.length; row++){
      for(int col = 0; col < board[0].length; col++){
        if(!solvedBoard[row][col].solvedEquals(board[row][col])){
          return false;
        }
      }
    }
    return true;
  }

  protected void endingMessage(){
    System.out.println("You Solved It!");
  }

  protected void printSolvedBoard(){
    for(int row = 0; row < height; row++){
      for(int col = 0; col < width; col++){
        String s = (solvedBoard[row][col].isFilled()) ? "#" : " ";
        System.out.print(s);
      }
      System.out.println();
    }
  }
}