import java.util.*;

/**
 * Used to start and play a game of 2048 (TFE).
 * 
 * @author Preston Bohnsack
 * @version Unreleased
 */
public class TFEBoard{
  private final TFETile[][] board;
  private final Scanner in;
  
  /**
   * Starts a game of 2048
   */

  /*
   * I went with a static method instantiating but not returning a TFEBoard to prevent memory leaks by 
   * preventing anyone from having an alias for a TFEBoard
   */
  public static void start(){
    new TFEBoard();
  }

  private TFEBoard(){
    in = new Scanner(System.in);
    board = new TFETile[4][4];
    
    while(shouldContinue()){
      print();
      userInput();
    }
    System.out.println();
  }
  
  private boolean shouldContinue(){
    int numOfNulls = 0;
    
    for(int row = 0; row < 4; row++){
      for(int col = 0; col < 4; col++){
        if(isEmpty(row, col)){
          numOfNulls++;
        }
        else if(board[row][col].getTier() >= 10){
          endingMessage(true);
          return false;
          // if there is a tier 10 TFETile the player wins, and gets a happy ending message.
        }
      }
    }
    
    if(numOfNulls == 0){
      endingMessage(false);
      print();
      return false;
      // if there is no space for a new TFETile the player loses, and gets a sad ending message.
    }
    else{
      generateNewTile(numOfNulls);
      return true;
      // otherwise the game continues and a new TFETile must be placed on the board.
    }
  }

  private boolean isEmpty(int row, int col){
    return board[row][col] == null;
  }

  private void endingMessage(boolean win){
    System.out.println((win) ? "You Won, You Got A Tier 10 Tile!" : 
      "You Failed, Your Final Score Was: " + getHighestTier());
  }

  private void generateNewTile(int numOfNulls){
    int num = (int)(numOfNulls * Math.random());
    int nulls = 0;
    
    for(int row = 0; row < 4; row++){
      for(int col = 0; col < 4; col++){
        if(isEmpty(row, col)){
          if(num == nulls){
            board[row][col] = TFETile.generate();
            return;
          }
          nulls++;
        }
      }
    }
  }
  
  private void print(){
    for(TFETile[] row : board){
      for(TFETile t : row){
        System.out.print((t != null) ? t : "-");
      }
      System.out.println();
    }
    System.out.println();
  }
  
  private int getHighestTier(){
    int highestTier = 0;
    for(int row = 0; row < 4; row++){
      for(int col = 0; col < 4; col++){
        if((!isEmpty(row,col)) && board[row][col].getTier() > highestTier){
          highestTier = board[row][col].getTier();
        }
      }
    }
    
    return highestTier;
  }
  
  private void userInput(){
    String str = in.nextLine();
    System.out.println();
    
    if(str.equals("w")){
      for(int col = 0; col < 4; col++){
        
        for(int row = 1; row < 4; row++){
          if(!isEmpty(row,col)){ // for every TFETile,
            int lastSpace = row;
            for(int row2 = row - 1; row2 >= 0; row2--){ // go through the row,
              if(isEmpty(row2,col)){
                lastSpace = row2; // find the empty space that the TFETile should move to if it doesn't get merged.
                if(row2 == 0){ // makes sure to move the TFETile if the empty space is the last space checked.
                  board[lastSpace][col] = board[row][col];
                  board[row][col] = null;
                  break;
                }
              }
              else if(board[row][col].equals(board[row2][col])){ // if the TFETile can be merged, than merge it.
                board[row2][col].merge();
                board[row][col] = null;
                break;
              }
              else{ // if the TFETile can't get merged, move it to the appropriate empty space.
                if(lastSpace != row){
                  board[lastSpace][col] = board[row][col];
                  board[row][col] = null;
                }
                
                break;
              } // this continues for all of WASD, just in different directions.
            }
          }
        }
      
      }
    }
    
    else if(str.equals("s")){
      for(int col = 0; col < 4; col++){
        
        for(int row = 2; row >= 0; row--){
          if(!isEmpty(row,col)){
            int lastSpace = row;
            for(int row2 = row + 1; row2 < 4; row2++){
              if(isEmpty(row2,col)){
                lastSpace = row2;
                if(row2 == 3){
                  board[lastSpace][col] = board[row][col];
                  board[row][col] = null;
                  break;
                }
              }
              else if(board[row][col].equals(board[row2][col])){
                board[row2][col].merge();
                board[row][col] = null;
                break;
              }
              else{
                if(lastSpace != row){
                  board[lastSpace][col] = board[row][col];
                  board[row][col] = null;
                }
                
                break;
              }
            }
          }
        }
        
      }
    }
    
    else if(str.equals("a")){
      for(int row = 0; row < 4; row++){
        
        for(int col = 1; col < 4; col++){
          if(!isEmpty(row,col)){
            int lastSpace = col;
            for(int col2 = col - 1; col2 >= 0; col2--){
              if(isEmpty(row,col2)){
                lastSpace = col2;
                if(col2 == 0){
                  board[row][lastSpace] = board[row][col];
                  board[row][col] = null;
                  break;
                }
              }
              else if(board[row][col].equals(board[row][col2])){
                board[row][col2].merge();
                board[row][col] = null;
                break;
              }
              else{
                if(lastSpace != col){
                  board[row][lastSpace] = board[row][col];
                  board[row][col] = null;
                }
                
                break;
              }
            }
          }
        }
        
      }
    }
    
    else if(str.equals("d")){
      for(int row = 0; row < 4; row++){
        
        for(int col = 2; col >= 0; col--){
          if(!isEmpty(row,col)){
            int lastSpace = col;
            for(int col2 = col + 1; col2 < 4; col2++){
              if(isEmpty(row,col2)){
                lastSpace = col2;
                if(col2 == 3){
                  board[row][lastSpace] = board[row][col];
                  board[row][col] = null;
                  break;
                }
              }
              else if(board[row][col].equals(board[row][col2])){
                board[row][col2].merge();
                board[row][col] = null;
                break;
              }
              else{
                if(lastSpace != col){
                  board[row][lastSpace] = board[row][col];
                  board[row][col] = null;
                }
                
                break;
              }
            }
          }
        }
        
      }
    }
    else{
      System.out.println("Invalid Direction, please type W, A, S, or D.");
      System.out.println();
    }
  }
  
}